////////////////////////////////////////////////////////////////////////////////////////////////
// WWAeval ver4.0
// 概要：マクロ文を拡張し、メッセージ内での変数の展開や代入機能、if文等を使用できるようにします。
// 作成者：デデすけ
// 解説ページ：http://asobiba.cocolog-nifty.com/game/wwa/wwaeval/
////////////////////////////////////////////////////////////////////////////////////////////////

import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map.Entry;
import java.lang.reflect.Method;
import java.lang.reflect.Array;
import jp.hishidama.eval.*;
import jp.hishidama.eval.var.MapVariable;
import jp.hishidama.eval.func.InvokeFunction;
import jp.hishidama.eval.ref.RefactorVarName;

//「//」以降の文はコメントになっています。

////////////////////////////////////////////////////////////////////////////////////////////////
//各変数の意味

// Valiable[０～９９]
//システムで自由に使える変数配列です。セーブ＆ロードで記録されるほかＣＧＩでも送信されます。

// Mode = 0		物体パーツや壁に触れたときや連続イベントが起こった時
// Mode = 1		プレーヤーが１歩移動した時
// Mode = 2		マウスやキーボードが押された時
// Mode = 3		画面を描画する時（通常１秒に５０回）

// PlayerX, PlayerY		プレーヤーのＸ座標、Ｙ座標
// PartsObject	プレーヤーが触れた物体パーツ番号
// PartsMap		プレーヤーの現在位置の背景パーツ番号

// Energy, EnergyMax, Strength, Defence, Gold, Step
//生命力、生命力最大値、攻撃力、防御力、ゴールド、移動回数

// ImgChara, ImgButton, ImgEnergy, ImgStrength, ImgDefence, ImgGold, ImgBom, ImgStatusFrame, ImgMainFrame,
//プレーヤー画像、ボタン画像、各種枠画像の位置、画像位置は座標ではなく（Ｘ＋Ｙ×１０）で指定

// ItemBox[０～１１]
//アイテムボックス

// Map[Ｙ座標][Ｘ座標] ＝ パーツ番号
//マップに配置されている背景パーツ
// Object[Ｙ座標][Ｘ座標] ＝ パーツ番号
//マップに配置されている物体パーツ

// AtrMap[背景番号][変数指定] ＝ 各種ステータス
//背景パーツの各種データ
// AtrObject[物体番号][変数指定] ＝ 各種ステータス
//物体パーツの各種データ

// MapWidth, MapPartsMax, ObjectPartsMax, MesNumberMax,
//マップ全体の幅、背景パーツ最大数、物体パーツ最大数、メッセージ番号最大数

// GlobalMessage[メッセージ番号]
// SystemMessage[０～１９]	システム関連メッセージ
//パーツで使用されているメッセージ。背景パーツならメッセージ番号は、AtrMap[背景番号][ATR_STRING]で指定

//メモ
//Global 5:リンク, 6:所持金不足, 7:アイテム持ってない, 8:アイテム使用
//System 0:クリックアイテム取得, 1:アイテム満杯, 2:効果音ロード

// ImgCrop[画像番号]
//４０×４０ドットの画像がはいっています。画像位置は、番号＝Ｘ＋Ｙ×１０に対応しています。
//上から５列目、左から８番目の画像の番号は（５－１）＋（８－１）×１０＝７４になります。

// Gr, GrMap, GrStatus
//画像書き込み用のオブジェクトです。
//「Gr」は５６０×４４０の表画面、「GrMap」は４４０×４４０のマップ裏画面、「GrMap」は１２０×４４０のステータス裏画面です。
//裏画面のGrMapやGrStatusの描画内容が表画面のGrに描画されます。
//ちらちきが生じますので通常は表画面のGrに直接は描画しません。

// bSaveStop, bDefault, bPaintAll, bAnmItem, 
//セーブ禁止フラグ、$defaultフラグ、全画面描画フラグ、アイテム取得時のアニメフラグ

// MusicNumber
//鳴らすサウンドの番号

// TimerCount
//描画毎の待ち時間

// bStopInput
//trueでキー入力の無効化（アニメーションイベントなどに使用）

//ReturnMessage
//返信用のメッセージです。これを記述すると本体のほうでこのメッセージが表示されます。
//別記の「ReturnMessageに関する仕様」も参照してください。

//bReset
//画面の更新（通常は使いません。各種設定変更で更新結果が画面に反映されないときに使用してください）

//bNoExec
//Mode=0の時（パーツに触れた時）そのパーツに設定されたイベントを実行するかを指定します（bNoExec = trueで実行拒否）
//拡張クラス側で本体側のイベントを実行するか制御できます。
//独自に条件判定マクロ文を作成したときなどに使用します。

public class WWAextendSub {

////////////////////////////////////////////////////////////////////////////////////////////////
//パーツデータ用数値定義
//AtrMap[背景番号][変数指定]やAtrObject[物体番号][変数指定]の変数指定に対応します。
//ATR_NUMBERなどはパーツの種類により扱いが変わります。
static final int ATR_CROP1 = 1;		//パーツ画像位置１
static final int ATR_CROP2 = 2;		//パーツ画像位置２
static final int ATR_TYPE = 3;		//パーツの種類
static final int ATR_MODE = 4;		//多用途（物体通行属性、使用型アイテム、扉属性など）
static final int ATR_STRING = 5;		//対応するメッセージ番号
static final int ATR_ENERGY = 10;		//↓パーツのステータス
static final int ATR_STRENGTH = 11;
static final int ATR_DEFENCE = 12;
static final int ATR_GOLD = 13;
static final int ATR_ITEM = 14;		//売り買いするアイテム番号など
static final int ATR_NUMBER = 15;		//多用途（待ち時間、アイテムボックス位置、扉属性など）
static final int ATR_JUMP_X = 16;		//↓ジャンプ先のＸＹ座標
static final int ATR_JUMP_Y = 17;
static final int ATR_SOUND = 19;		//サウンド番号
static final int ATR_MOVE = 16;		//移動属性

//AtrMap[背景番号][変数指定]のパーツの種類（変数指定 = ATR_TYPE）に対応します。
static final int MAP_STREET = 0;		//道
static final int MAP_WALL = 1;		//壁
static final int MAP_LOCALGATE = 2;	//ジャンプゲート
static final int MAP_URLGATE = 4;		//ＵＲＬゲート

//AtrObject[物体番号][変数指定]のパーツの種類（変数指定 = ATR_TYPE）に対応します。
static final int OBJECT_NORMAL = 0;	//通常物体パーツ
static final int OBJECT_MESSAGE = 1;	//メッセージパーツ
static final int OBJECT_URLGATE = 2;	//ＵＲＬゲート
static final int OBJECT_STATUS = 3;	//ステータス変化
static final int OBJECT_ITEM = 4;		//アイテム
static final int OBJECT_DOOR = 5;		//扉
static final int OBJECT_MONSTER = 6;	//モンスター
static final int OBJECT_SCORE = 11;	//スコア表示
static final int OBJECT_SELL = 14;	//物を売る
static final int OBJECT_BUY = 15;		//物を買う
static final int OBJECT_RANDOM = 16;	//ランダム選択
static final int OBJECT_SELECT = 17;	//二者択一
static final int OBJECT_LOCALGATE = 18;	//ジャンプゲート

////////////////////////////////////////////////////////////////////////////////////////////////
//グローバル変数の定義
//ここで定義した変数はセーブ＆ロードでは記録されませんが、システムが終了するまで内容が保持されます。

//$wwaevalが必要かどうかのフラグ
//これをfalseにすると$wwaeval宣言なしでもWWAevalの機能が使えるようになります。
static final boolean WWAeval_needsDeclare = true;

//強制的に全画面描画モードにするかどうかのフラグ
//これをtrueにすると強制的に全画面描画モードになります。
static final boolean WWAeval_forcePaintAll = false;



////////////////////////////////////////////////////////////////////////////////////////////////
// ユーザー拡張スペース

void UserExtension(){

	////////////////////////////////////////////////////////////////////////////////////////////////
	// ↓ここから先の部分で、デフォルトのWWAextendsub.javaとほとんど同じプログラムが使用できます。
	////////////////////////////////////////////////////////////////////////////////////////////////

	//if( Mode == 0 ) {
	//}

	////////////////////////////////////////////////////////////////////////////////////////////////
	// ↑ユーザー拡張スペースはここまで。
	////////////////////////////////////////////////////////////////////////////////////////////////
}


////////////////////////////////////////////////////////////////////////////////////////////////
//メンバー変数の定義
//Mainに渡されたローカル変数をメンバー変数に移動するための準備です。
int Mode, PartsObject, PartsMap, PlayerX, PlayerY, PartsX, PartsY,
Energy, EnergyMax, Strength, Defence, Gold, Step, TimerCount,
GameoverX, GameoverY, ImgChara, ImgButton, bSaveStop, bDefault, bAnmItem, MusicNumber,
ImgEnergy, ImgStrength, ImgDefence, ImgGold, ImgBom, ImgStatusFrame, ImgItemFrame, ImgMainFrame, ImgClickItem,
Key, MouseX, MouseY,
MapWidth, MapPartsMax, ObjectPartsMax, MesNumberMax;

char PlayerDirect;
boolean bPaintAll, bStopInput;
int ItemBox[], Valiable[];
short Map[][], Object[][];
Graphics2D Gr, GrMap, GrStatus;
Image ImgCrop[];
int AtrMap[][], AtrObject[][];
String GlobalMessage[], SystemMessage[];

String ReturnMessage;
boolean bReset, bNoExec;

private static String CopyRight = "WWAeval Ver 4.0";

private static WWAextendSub instance;
//private static Thread authedThread;

////////////////////////////////////////////////////////////////////////////////////////////////
//Main処理
//拡張メソッドを作りやすくするため、インスタンスを作成する方式に変更しています。
public static void Main(
	int Mode, int PartsObject, int PartsMap, int PlayerX, int PlayerY, char PlayerDirect, int PartsX, int PartsY,
	int Energy, int EnergyMax, int Strength, int Defence, int Gold, int Step, int TimerCount,
	int GameoverX, int GameoverY, int ImgChara, int ImgButton, int bSaveStop, int bDefault, boolean bPaintAll, boolean bStopInput, int bAnmItem, int MusicNumber,
	int ImgEnergy, int ImgStrength, int ImgDefence, int ImgGold, int ImgBom, int ImgStatusFrame, int ImgItemFrame, int ImgMainFrame, int ImgClickItem,
	int ItemBox[], int Valiable[],
	short Map[][], short Object[][],
	int Key, int MouseX, int MouseY,
	int MapWidth, int MapPartsMax, int ObjectPartsMax, int MesNumberMax,
	Graphics Gr, Graphics GrMap, Graphics GrStatus, Image ImgCrop[],
	int AtrMap[][], int AtrObject[][],
	String GlobalMessage[], String SystemMessage[]
){

	if(Mode == 4 && instance == null) {
		//authedThread = Thread.currentThread();
		instance = new WWAextendSub();
	}
	
	WWAextendSub me = getInstance();
	
	if(me == null) return;
	
	//変数をフィールドへ
	me.Mode = Mode; me.PartsObject = PartsObject; me.PartsMap = PartsMap;
	me.PlayerX = PlayerX; me.PlayerY = PlayerY; me.PlayerDirect = PlayerDirect;
	me.PartsX = PartsX; me.PartsY = PartsY;
	me.Energy = Energy; me.EnergyMax = EnergyMax; me.Strength = Strength;
	me.Defence = Defence; me.Gold = Gold; me.Step = Step; me.TimerCount = TimerCount;
	me.GameoverX = GameoverX; me.GameoverY = GameoverY;
	me.ImgChara = ImgChara; me.ImgButton = ImgButton;
	me.bSaveStop = bSaveStop; me.bDefault = bDefault; me.bPaintAll = bPaintAll;
	me.bStopInput = bStopInput; me.bAnmItem = bAnmItem; me.MusicNumber = MusicNumber;
	me.ImgEnergy = ImgEnergy; me.ImgStrength = ImgStrength; me.ImgDefence = ImgDefence;
	me.ImgGold = ImgGold; me.ImgBom = ImgBom;
	me.ImgStatusFrame = ImgStatusFrame; me.ImgItemFrame = ImgItemFrame;
	me.ImgMainFrame = ImgMainFrame; me.ImgClickItem = ImgClickItem;
	me.ItemBox = ItemBox; me.Valiable = Valiable;
	me.Map = Map; me.Object = Object;
	me.Key = Key; me.MouseX = MouseX; me.MouseY = MouseY;
	me.MapWidth = MapWidth; me.MapPartsMax = MapPartsMax;
	me.ObjectPartsMax = ObjectPartsMax; me.MesNumberMax = MesNumberMax;
	me.Gr = (Graphics2D)Gr; me.GrMap = (Graphics2D)GrMap; me.GrStatus = (Graphics2D)GrStatus;
	me.ImgCrop = ImgCrop;
	me.AtrMap = AtrMap; me.AtrObject = AtrObject;
	me.GlobalMessage = GlobalMessage; me.SystemMessage = SystemMessage;

	me.Execute();
}

public WWAextendSub() {}

//処理実行用メソッド
void Execute(){
	
	ReturnMessage = null;
	bReset = false;
	bNoExec = false;

	//WWAeval呼び出し
	if(WWAevalApplet.isInterfered()) {
		bStopInput = true;
		System.out.println("干渉を検出 at WWAextendSub");
		ReturnMessage = "干渉を検出しました。\nWWAを停止します。\n\n"
			+ "大変申し訳ありませんが、\n"
			+ "ここからゲームを続行することはできず、現在のゲームの進行状況などのデータは失われます。\n\n"
			+ "このエラーは別のWWAがこのWWAと同じクラスファイルを同時に使用したときに発生します。\n"
			+ "詳しくはWWAevalマニュアルの\n「干渉について」を参照してください。";
	}
	else if(!WWAeval_initialized) {
		WWAeval_init();
	}
	else {
		WWAeval_start();
		WWAeval();
		WWAeval_final();
	}

	//ユーザー拡張プログラム呼び出し
	UserExtension();

	//拡張クラス呼び出し
	//WWAextend wwa = new WWAextend();
	//wwa.ReturnExtend(
	//これってnewしないほうがいいのでは・・・？
	WWAextend.ReturnExtend(
		Mode, PlayerX, PlayerY, PlayerDirect,
		Energy, EnergyMax, Strength, Defence, Gold, Step, TimerCount,
		GameoverX, GameoverY, ImgChara, ImgButton, bSaveStop, bDefault, bPaintAll, bStopInput, bAnmItem, MusicNumber,
		ImgEnergy, ImgStrength, ImgDefence, ImgGold, ImgBom, ImgStatusFrame, ImgItemFrame, ImgMainFrame, ImgClickItem,
		ItemBox, Valiable,
		Map, Object,
		ReturnMessage, CopyRight, bReset, bNoExec,
		AtrMap, AtrObject,
		GlobalMessage, SystemMessage
	);
}


////////////////////////////////////////////////////////////////////////////////////////////////
// ここからWWAevalの中心処理

//フィールドの定義
//もともと競合防止のために先頭にWWAevalと付けてたが…途中から気分であったりなかったり
boolean WWAeval_debug;
boolean WWAeval_debugAlways = false;
boolean WWAeval_quickLoaded = false;
boolean WWAeval_releaseNext = false;
boolean WWAeval_initialized = false;
boolean WWAeval_tempWaited = false;
boolean WWAeval_tempUnWaited = false;
boolean WWAeval_beforeGameStart = true;
boolean WWAeval_imgLoadHasErr = false;
String WWAeval_initErrMes;
long WWAeval_prevProcessTime;
int WWAeval_randomSeed;
int WWAeval_mesCount = 0;
int WWAeval_timer = 0;
static final int WWAeval_timerIndex = 80;//2つ使う
static final int WWAeval_seedIndex = 82;//2つ使う
static final int WWAeval_walkEventIndex = 84;//PARTS_MAX足すと背景扱い
static final int WWAeval_keyEventIndex = 85;//
static final int WWAeval_mouseEventIndex = 86;//
static final int WWAeval_pictIndex = 40;
static final int WWAeval_pictLength = 20;
int[] WWAeval_objEffect;//PARTS_MAX足すと背景扱い
int[] WWAeval_mapEffect;//これも
String[] WWAeval_objName;
String[] WWAeval_mapName;
Map varMap;
Map constMap;
Map WWAeval_objectMap;
Map WWAeval_mapMap;
String keyName;

String[] GlobalMessageTemp;
int mapLocationX;
int mapLocationY;
int mapScrollCount = -1;
int mapScrollDir = 0;

//毎回newするのは辛いので、あらかじめ作っておく
double[] ItemBoxDouble;
double[] ValiableDouble;
double[][] MapDouble;
double[][] ObjectDouble;
double[][] AtrMapDouble;
double[][] AtrObjectDouble;

Integer[] intCache;
Double[] doubleCache;

private static final InvokeFunction WWAevalFunc = new WWAevalFunc();

final int PARTS_MAX = 10000;	//適当な定数

//WWAeval_atrPict用定数
static int P_renban = 0;
static final int P_DEF = P_renban++;	//1or0
static final int P_DISPX = P_renban++;	//
static final int P_DISPY = P_renban++;	//
static final int P_ANIME_DELAY = P_renban++;	//
static final int P_WIDTH = P_renban++;		//
static final int P_HEIGHT = P_renban++;		//
static final int P_OUTLINE = P_renban++;	//
static final int P_COMBINEX = P_renban++;		//
static final int P_COMBINEY = P_renban++;		//
static final int P_SPEEDW = P_renban++;		//
static final int P_SPEEDH = P_renban++;		//
static final int P_ACCELW = P_renban++;	//
static final int P_ACCELH = P_renban++;	//
static final int P_CENTER = P_renban++;	//1or0
static final int P_ALPHA = P_renban++;	//
static final int P_SPEEDA = P_renban++;	//
static final int P_ROTATE = P_renban++;	//
static final int P_SPEEDR = P_renban++;	//
static final int P_BEGIN = P_renban++;	//
static final int P_HIDE = P_renban++;	//
static final int P_END = P_renban++;	//
static final int P_RANDOMX = P_renban++;	//
static final int P_RANDOMY = P_renban++;	//
static final int P_RANDOM_SEED = P_renban++;	//
static final int P_SPEEDX = P_renban++;	//
static final int P_SPEEDY = P_renban++;	//
static final int P_ACCELX = P_renban++;	//
static final int P_ACCELY = P_renban++;	//
static final int P_MOVE_START = P_renban++;	//
static final int P_MOVE_STOP = P_renban++;	//
static final int P_REPEATX = P_renban++;	//
static final int P_REPEATY = P_renban++;	//
static final int P_INTERVALX = P_renban++;	//
static final int P_INTERVALY = P_renban++;	//
static final int P_SHIFTX = P_renban++;	//
static final int P_SHIFTY = P_renban++;	//
static final int P_POLAR = P_renban++;	//1or0
static final int P_ORIGINX = P_renban++;	//
static final int P_ORIGINY = P_renban++;	//
static final int P_POLAR_ROT = P_renban++;	//1or0
static final int P_COLOR = P_renban++;	//
static final int P_ANTIARIAS = P_renban++;	//
static final int P_FILL = P_renban++;	//1or0
static final int P_ONSTATUS = P_renban++;	//
static final int P_HASPARTS = P_renban++;	//PARTS_MAX足すと背景扱い
static final int P_COUNT = P_renban++;	//
static final int P_INITSOUND = P_renban++;	//
static final int P_ENDSOUND = P_renban++;	//
static final int P_WAIT = P_renban++;	//
static final int P_PUT = P_renban++;	//PARTS_MAX足すと背景扱い
static final int P_PUTX = P_renban++;	//
static final int P_PUTY = P_renban++;	//
static final int P_PARAM = P_renban++;	//
static final int P_CONNECT = P_renban++;	//
static final int P_CONNECTING = P_renban++;	//1or0
static final int P_CON_COUNT = P_renban++;	//
static final int P_CON_LIMIT = P_renban++;	//
static final int P_CON_TYPE = P_renban++;	//ビット値になる予定…

double[][] WWAeval_atrPict;

static int PA_renban = 0;
static final int PA_CROPS = PA_renban++;	//
static final int PA_CIRCLES = PA_renban++;	//0:X振幅　1:Y振幅　2:角速度　3:初期角度　...
static final int PA_STRING = PA_renban++;	//0:X　1:Y　2:文字列　...
static final int PA_FONT = PA_renban++;	//0:フォント　1:アンチエイリアス
static final int PA_COLORS = PA_renban++;	//0:背景色　1:文字色
static final int PA_ONPARTS = PA_renban++;	//PARTS_MAX足すと背景扱い
static final int PA_CREATE = PA_renban++;	//0:表示番号　1:画像物体番号　...
ArrayList[][] WWAeval_atrPictList;

static final int GRWIDTH = 440;
static final int GRHEIGHT = 440;

void WWAeval() {
	
	if(WWAeval_tempUnWaited) {
		WWAeval_tempUnWaited = false;
		bStopInput = true;
	}

	if(Mode == 3) {
		if(WWAeval_beforeGameStart) {
			WWAeval_beforeGameStart = false;
			if(WWAeval_initErrMes != null) {
				ReturnMessage = WWAeval_initErrMes;
				WWAeval_initErrMes = null;
				return;
			}
		}
		drawPict();
		return;
	}
	
	if(WWAeval_beforeGameStart)
		bStopInput = false;
	
	if(Mode == 1 || Mode == 2) {
		int partsNo = 0;
		if(Mode == 1) {
			partsNo = Valiable[WWAeval_walkEventIndex];
		}
		else if(!bStopInput) {
			if(WWAeval_mesCount == 0) {
				if(Key != 0) {
					keyName = getKeyName(Key);
					partsNo = Valiable[WWAeval_keyEventIndex];
				}
				else {
					partsNo = Valiable[WWAeval_mouseEventIndex];
				}
			}
			else if(Key == 0 || Key == Event.ENTER || Key == Event.ESCAPE || Key == ' ' || Key == '　')
				WWAeval_mesCount--;
		}
		else {//時間差利用でmesチェンジ
			if(WWAeval_mesCount > 0 && WWAeval_prevProcessTime + TimerCount * 10 < System.currentTimeMillis() 
					&& (Key == 0 || Key == Event.ENTER || Key == Event.ESCAPE || Key == ' ' || Key == '　')) {
				WWAeval_mesCount--;
				WWAeval_tempUnWaited = true;
				bStopInput = false;
			}
		}
		
		if(partsNo % PARTS_MAX != 0) {
			boolean isObj = partsNo < PARTS_MAX;
			partsNo %= PARTS_MAX;
			int[] atr = isObj ? AtrObject[partsNo] : AtrMap[partsNo];
			if(atr[ATR_STRING] != 0)
				ReturnMessage = parseWWAeval(GlobalMessage[atr[ATR_STRING]], partsNo, atr, PlayerX, PlayerY);
		}
	}

	if(Mode != 0 || (PartsObject == 0 && PartsMap == 0))
		return;

	String szMes = null;
	boolean isObj = PartsObject != 0;
	int mesNo = 0;
	int[] atr = isObj ? AtrObject[PartsObject] : AtrMap[PartsMap];
	mesNo = atr[ATR_STRING];
	if(mesNo != 0) szMes = GlobalMessage[mesNo];
	
	//for(int i = 0; i < atr.length; i++) System.out.println(i+":"+atr[i]);
	
	if(szMes == null) {
	}
	else if((WWAeval_needsDeclare && !hasAtLineHead(szMes, "$wwaeval")) ||
		hasAtLineHead(szMes, "$no_wwaeval") ||
		(isObj && atr[ATR_TYPE] != OBJECT_MESSAGE) ||
		(!isObj && atr[ATR_TYPE] != MAP_STREET && atr[ATR_TYPE] != MAP_WALL))
	{
		int[] count = countMes(szMes);
		if(count[0] > 0)
			WWAeval_mesCount += 1 + count[1];
	}
	else {
		bNoExec = true;
		
		//bNoExec=trueの影響でプレーヤーと重なっている物体も消えずに残ってしまうので消す
		if(isObj && bDefault == 0 && PartsX == PlayerX && PartsY == PlayerY &&
				Object[PlayerY][PlayerX] == PartsObject) {
			Object[PlayerY][PlayerX] = 0;
		}
		
		ReturnMessage = parseWWAeval(szMes, isObj ? PartsObject : PartsMap, atr, PartsX, PartsY);
	}
}

String parseWWAeval(String message, int partsNo, boolean isDisplayed) {

	StringBuffer parsedMesSB = new StringBuffer(message.length());
	
	//$debugがあればデバッグフラグを立てる
	if(hasAtLineHead(message, "$debug_always"))
		WWAeval_debugAlways = true;
	WWAeval_debug = WWAeval_debugAlways || hasAtLineHead(message, "$debug");
	
	if(WWAeval_debug) System.out.println("\n◆WWAeval文章解析開始");
	
	//$if・$for用の変数
	int nestDepth = 0;
	boolean[] ignoreFlag = new boolean[10];
	boolean[] alreadyTrue = new boolean[10];
	
	String[] forCountName = new String[10];
	int[] forStartRow = new int[10];
	int[] forEndRow = new int[10];
	int[] forStartNo = new int[10];
	int[] forEndNo = new int[10];
	int[] depthTemp =  new int[10];
	
	//$pictが使われたかどうか
	boolean pictCalled = false;
	
	//eval文内の改行は無視して行分割を行う
	ArrayList lines = splitConsClip(message, "\n", "@\\{.+?\\}@|@<.+?>@");
	
	for (int lineRow = 0; lineRow < lines.size(); lineRow++) {
		String line = (String)lines.get(lineRow);
		int lineRowTemp = lineRow;
		
		if(WWAeval_debug) System.out.println("処理開始:"+(lineRow+1)+"行目:"+line);
		
		if (line.toLowerCase().indexOf("<c>") != -1) {
			if(WWAeval_debug) System.out.println("<c>を発見");
			break;
		}
		
		boolean hasMacro = false;
		boolean hasEqual= false;
		int equalPos = 0;
		String macroName = "";
		
		if(line.length() > 1 && line.charAt(0) == '$') {
			hasMacro = true;
			StringBuffer sb = new StringBuffer();
			int length = line.length();
			for(int i = 1; i < length; i++) {
				char c = Character.toLowerCase(line.charAt(i));
				
				if(c == '_' || ('a' <= c && c <= 'z'))
					sb.append(c);
				else if(c == '=') {
					hasEqual = true; equalPos = i; break;
				}
				else {
					hasMacro = false; break;
				}
			}
			if(hasMacro) macroName = sb.toString();
		}
		
		boolean superIgnoreFlag = false;
		for (int i = 0; i < nestDepth; i++) {
			if(ignoreFlag[i]) {
				superIgnoreFlag = true; break;
			}
		}
		
		//eval処理
		if(!superIgnoreFlag &&
			((!ignoreFlag[nestDepth] && !macroName.equals("elseif")) ||
			(macroName.equals("elseif") && !alreadyTrue[nestDepth]))) { 
		
			line = replaceEval(line, partsNo, "@<.*?>@");
			line = replaceEval(line, partsNo, "@\\{.*?\\}@");
		}
		
		if(WWAeval_debug && !lines.get(lineRowTemp).equals(line)) System.out.println("該当行を置換:"+line);
		
		boolean show = true;
		
		//マクロ処理
		if (hasMacro) {
			String macroValue = hasEqual ? line.substring(equalPos+1) : "";
			String[] param = splitAndTrim(macroValue, ',');
			
			
			//最初にif系の判定
			if(macroName.equals("if") || macroName.equals("elseif")) {
				if(macroName.equals("if")) {
					nestDepth++;
					if(WWAeval_debug) System.out.println("ネストの深さ:"+nestDepth);
					ignoreFlag[nestDepth] = false;
					alreadyTrue[nestDepth] = false;
				}
				if(!superIgnoreFlag && !ignoreFlag[nestDepth - 1]) {
					if(alreadyTrue[nestDepth]) {
						ignoreFlag[nestDepth] = true;
						if(WWAeval_debug) System.out.println("以前のifがすでに真");
					}
					else if(param[0].equals("0") || param[0].equals("")) {
						if(WWAeval_debug) System.out.println(macroName+"の結果:偽");
						ignoreFlag[nestDepth] = true;
					}
					else {
						if(WWAeval_debug) System.out.println(macroName+"の結果:真");
						ignoreFlag[nestDepth] = false;
						alreadyTrue[nestDepth] = true;
					}
				}
				if(superIgnoreFlag || ignoreFlag[nestDepth - 1] || ignoreFlag[nestDepth]) {
					//飛ばすかどうかの判定
					if(WWAeval_debug) System.out.println("条件偽のため処理中止");
						continue;
				}
			}
			else if(macroName.equals("else")) {
				if(alreadyTrue[nestDepth]) {
					ignoreFlag[nestDepth] = true;
				}
				else {
					ignoreFlag[nestDepth] = false;
					alreadyTrue[nestDepth] = true;
				}
			}
			else if(macroName.equals("endif")) {
				if(nestDepth <= 0) {
					System.out.println("ネスト構造がおかしいです");
				}
				else {
					ignoreFlag[nestDepth] = false;
					alreadyTrue[nestDepth] = false;
					nestDepth--;
				}
			}
			else {
				//if系のマクロ判定が終わったので、ここで飛ばすかどうかを判定する
				if(superIgnoreFlag || ignoreFlag[nestDepth]) {
					if(WWAeval_debug) System.out.println("条件偽のため処理中止");
					continue;
				}
				
				//ここからif系ではない独自マクロの定義
				if(macroName.equals("for")) { //for文まわりは黒魔術すぎる…どうしよ
					nestDepth++;
					for(int i = nestDepth; i < depthTemp.length; i++) {
						depthTemp[i] = nestDepth;
					}
					if(WWAeval_debug) System.out.println("ネストの深さ:"+nestDepth);
					forStartRow[nestDepth] = lineRow;
					if(param.length >= 3) {
						forCountName[nestDepth] = param[0];
						forStartNo[nestDepth] = i(param[1]);
						forEndNo[nestDepth] = i(param[2]);
					}
					else {
						forCountName[nestDepth] = "ForCount";
						forStartNo[nestDepth] = i(param[0]);
						forEndNo[nestDepth] = i(param[1]);
					}
					varMap.put(forCountName[nestDepth], doubleValueOfInt(forStartNo[nestDepth]));
					
					//先読みでendfor探索
					int lowerDepth = 0;
					for(int lineRowSub = lineRow + 1; lineRowSub < lines.size(); lineRowSub++) {
						if(((String)lines.get(lineRowSub)).startsWith("$for=")) {
							lowerDepth++;
						}
						else if(((String)lines.get(lineRowSub)).startsWith("$endfor")) {
							if(lowerDepth <= 0) {
								forEndRow[nestDepth] = lineRowSub; break;
							}
							lowerDepth--;
						}
					}
					if(forEndRow[nestDepth] == 0) {
						forEndRow[nestDepth] = lines.size() - 1; //無限ループ回避の応急処置
						System.out.println("endforがないor数がおかしいです at for");
					}
					if(forStartNo[nestDepth] > forEndNo[nestDepth]) {
						lineRow = forEndRow[nestDepth]-1;
					}
				}
				else if(macroName.equals("endfor")) {
					forEndRow[nestDepth] = lineRow;
					nestDepth = depthTemp[nestDepth];
					if(((Double)varMap.get(forCountName[nestDepth])).intValue() >= forEndNo[nestDepth]) {
						forStartRow[nestDepth] = 0;
						forStartNo[nestDepth] = 0;
						forEndNo[nestDepth] = 0;
						forEndRow[nestDepth] = 0;
						nestDepth--;
						if(WWAeval_debug) System.out.println("for文終了");
					}
					else {
						varMap.put(forCountName[nestDepth],
							doubleValueOfInt(((Double)varMap.get(forCountName[nestDepth])).doubleValue() + 1));
						lineRow = forStartRow[nestDepth];
						
						show = false;
						if(WWAeval_debug) System.out.println("for文で繰り返し　"+forCountName[nestDepth]+":"+
							((Double)varMap.get(forCountName[nestDepth])).intValue());
					}
				}
				else if(macroName.equals("continue")) {
					if(forEndRow[depthTemp[nestDepth]] == 0) {
						System.out.println("continueがfor文の外に存在しています");
					}
					else {
						lineRow = forEndRow[depthTemp[nestDepth]] - 1; //endforに投げる
					}
				}
				else if(macroName.equals("break")) {
					if(forEndRow[depthTemp[nestDepth]] == 0) {
						System.out.println("breakがfor文の外に存在しています");
					}
					else {
						lineRow = forEndRow[depthTemp[nestDepth]]; //endforをすっ飛ばす
						nestDepth = depthTemp[nestDepth];
						forStartRow[nestDepth] = 0;
						forStartNo[nestDepth] = 0;
						forEndNo[nestDepth] = 0;
						forEndRow[nestDepth] = 0;
						nestDepth--;
					}
				}
				else if(macroName.equals("exit")) {
					if(WWAeval_debug) System.out.println("exitします");
					break;
				}
				else if(macroName.equals("include")) {
					int partsType = param.length >= 2 ? i(param[1]) : 0;
					int incNo = getNoByNameIfHas(param[0], partsType);
					int mesNo = partsType == 1 ? AtrMap[incNo][ATR_STRING] : AtrObject[incNo][ATR_STRING];
					
					if(mesNo != 0) {
						String mesImp = GlobalMessage[mesNo];
						
						ArrayList linesImp = splitConsClip(mesImp, "\n", "@\\{.+?\\}@|@<.+?>@");
						lines.addAll(lineRow + 1, linesImp);//次の行から
						
						lines.set(lineRow, "$included");//forで重複展開しないようにする
						
						//forとの兼ね合いで
						for (int i = 0; i < nestDepth + 1; i++) {
							if(forEndRow[i] != 0)
								forEndRow[i] += linesImp.size();
						}
						if(WWAeval_debug) System.out.println(linesImp.size()+"行includeしました");
					}
				}
				else if(macroName.equals("walkevent") || macroName.equals("keyevent")
						|| macroName.equals("mouseevent")) {
					int index = macroName.equals("walkevent") ? WWAeval_walkEventIndex
								: macroName.equals("keyevent") ? WWAeval_keyEventIndex
								: WWAeval_mouseEventIndex;
					int partsType = param.length >= 2 ? i(param[1]) : 0;
					Valiable[index] = getNoByNameIfHas(param[0], partsType);
					if(partsType == 1)
						Valiable[index] += PARTS_MAX;
				}
				else if(macroName.equals("saveitembox")) {
					if(param.length >= 2) {
						int origX = i(param[0]);
						int origY = i(param[1]);
						for (int y = 0; y < 4; y++)
							for (int x = 0; x < 3; x++)
								Object[origY + y][origX + x] = (short)ItemBox[y * 3 + x];
					}
					else {
						int stIndex = i(param[0]);
						for (int i = 0; i < ItemBox.length; i++) {
							Valiable[stIndex + i] = ItemBox[i];
						}
					}
				}
				else if(macroName.equals("loaditembox")) {
					
					if(param.length >= 2) {
						int origX = i(param[0]);
						int origY = i(param[1]);
						for (int y = 0; y < 4; y++)
							for (int x = 0; x < 3; x++)
								ItemBox[y * 3 + x] = Object[origY + y][origX + x];
					}
					else {
						int stIndex = i(param[0]);
						for (int i = 0; i < ItemBox.length; i++)
							ItemBox[i] = Valiable[stIndex + i];
					}
				}
				else if(macroName.equals("swapitembox")) {
					int[] tempItemBox = (int[])ItemBox.clone();
					
					if(param.length >= 2) {
						int origX = i(param[0]);
						int origY = i(param[1]);
						for (int y = 0; y < 4; y++) {
							for (int x = 0; x < 3; x++) {
								ItemBox[y * 3 + x] = Object[origY + y][origX + x];
								Object[origY + y][origX + x] = (short)tempItemBox[y * 3 + x];
							}
						}
					}
					else {
						int stIndex = i(param[0]);
						for (int i = 0; i < ItemBox.length; i++) {
							ItemBox[i] = Valiable[stIndex + i];
							Valiable[stIndex + i] = tempItemBox[i];
						}
					}
				}
				else if(macroName.equals("clearitembox")) {
				}
				else if(macroName.equals("sound")) {
					soundPlay(param[0]);
				}
				else if(macroName.equals("pict")) {
					int index = i(param[0]);
					if(0 <= index && index < WWAeval_pictLength) {
						Valiable[WWAeval_pictIndex + index] = 
							param.length >= 2 ? i(param[1]) : 0;
						
						deletePict(index);
						
						if(Valiable[WWAeval_pictIndex + index] != 0) {
							Valiable[WWAeval_pictIndex + WWAeval_pictLength + index] = 
								(param.length >= 3
								? Math.abs(i(param[2]))
								: partsNo);
							
							pictCalled = true;
							//setPict(index, WWAeval_pictIndex + index);
						}
					}
				}
				else {
				}
			}
		}
		else { //マクロ文じゃないとき
			if(superIgnoreFlag || ignoreFlag[nestDepth]) {
				if(WWAeval_debug) System.out.println("条件偽のため処理中止");
				continue;
			}
		}
		
		if(show)
			parsedMesSB.append(line).append("\n");
	}
	
	varMap.clear(); //毎回消す
	
	if(parsedMesSB.length() != 0)
		parsedMesSB.setLength(parsedMesSB.length() - 1);
	
	String parsedMes = parsedMesSB.toString();
	
	if(isDisplayed) {
		int[] count = countMes(parsedMes);
		if(count[0] > 0)
			WWAeval_mesCount += 1 + count[1];
		
		//$pict使用時、表示メッセージが空なら一瞬だけウェイト処理を入れる
		if(pictCalled && !bStopInput && count[0] > 0) {
			WWAeval_tempWaited = true;
			bStopInput = true;
		}
	}
	
	if(WWAeval_debug) System.out.println("\n◆WWAeval文章解析完了\n編集後全文:"+parsedMes+":全文ここまで");
	
	return parsedMes.length() != 0 ? parsedMes : null;
}

//パーツのイベントをエミュレートする…ようにしたい何か
//WWAの仕様に激しく依存
String parseWWAeval(String message, int partsNo, int[] atr, int origX, int origY) {
	if(atr != null) {
		if(atr[ATR_NUMBER] != 0) {
			try {
				Thread.sleep(atr[ATR_NUMBER] * 100);
			} catch(InterruptedException e){}
		}
		for(int i = 20; i < atr.length - 3; i += 4) {
			int callPartsNo = atr[i];
			int callPartsX = atr[i+1];
			int callPartsY = atr[i+2];
			boolean callPartsIsObj = atr[i+3] == 0;
			short[][] target = callPartsIsObj ? Object : Map;
			
			if(callPartsNo == 0 && callPartsX == 0 && callPartsY == 0)
				continue;
			if(9500 <= callPartsX && callPartsX <= 10500)
				callPartsX += origX - 10000;
			else if(callPartsX == 9000)
				callPartsX = PlayerX;
			if(9500 <= callPartsY && callPartsY <= 10500)
				callPartsY += origY - 10000;
			else if(callPartsY == 9000)
				callPartsY = PlayerY;
			
			if(0 <= callPartsX && callPartsX < MapWidth && 0 <= callPartsY && callPartsY < MapWidth)
				target[callPartsY][callPartsX] = (short)callPartsNo;
		}
	}
	String rtMes = parseWWAeval(message, partsNo, true);
	return rtMes;
}

//eval処理を実行するメソッド
String replaceEval(String line, int partsNo, String pattern) {

	if(!varMap.containsKey("ATR_CROP1")) { //適当なもので検証
		//定数と参照渡し集を代入
		varMap.putAll(constMap);
		
		//Temp再定義
		Arrays.fill((double[])varMap.get("Temp"), 0);
		Arrays.fill((String[])varMap.get("TempStr"), "");
		((ArrayList)varMap.get("TempList")).clear();
		((HashMap)varMap.get("TempMap")).clear();
	}
	
	boolean findFlag = false;
	StringBuffer evaledLine = null;
	Matcher m = Pattern.compile(pattern, Pattern.DOTALL).matcher(line);
	
	boolean MapModded = false;
	boolean ObjectModded = false;
	boolean AtrMapModded = false;
	boolean AtrObjectModded = false;
	
	while (m.find()) {
		String evalExpr = m.group().substring(2, m.group().length()-2)
				//longをdoubleに変換
				.replaceAll("(?<![\\d\\w.\"\'])(\\d+)(?![\\d\\w.\"\'])", "$1.0");
		
		if(!findFlag) {
			findFlag = true;
			
			evaledLine = new StringBuffer();
			
			//変数を強引にハッシュへ代入
			varMap.put("PartsObject", doubleValueOfInt(PartsObject));
			varMap.put("PartsMap", doubleValueOfInt(PartsMap));
			varMap.put("PlayerX", doubleValueOfInt(PlayerX));
			varMap.put("PlayerY",doubleValueOfInt(PlayerY));
			varMap.put("PlayerDirect", doubleValueOfInt(PlayerDirect));
			varMap.put("PartsX", doubleValueOfInt(PartsX));
			varMap.put("PartsY", doubleValueOfInt(PartsY));
			varMap.put("Energy", doubleValueOfInt(Energy));
			varMap.put("EnergyMax", doubleValueOfInt(EnergyMax));
			varMap.put("Strength", doubleValueOfInt(Strength));
			varMap.put("Defence", doubleValueOfInt(Defence));
			varMap.put("Gold", doubleValueOfInt(Gold));
			varMap.put("Step", doubleValueOfInt(Step));
			varMap.put("TimerCount", doubleValueOfInt(TimerCount));
			varMap.put("GameoverX", doubleValueOfInt(GameoverX));
			varMap.put("GameoverY", doubleValueOfInt(GameoverY));
			varMap.put("ImgChara", doubleValueOfInt(ImgChara));
			varMap.put("ImgButton", doubleValueOfInt(ImgButton));
			varMap.put("bSaveStop", doubleValueOfInt(bSaveStop));
			varMap.put("bDefault", doubleValueOfInt(bDefault));
			varMap.put("bPaintAll", doubleValueOfInt(bPaintAll ? 1 : 0));
			varMap.put("bStopInput", doubleValueOfInt(bStopInput ? 1 : 0));
			varMap.put("bAnmItem", doubleValueOfInt(bAnmItem));
			varMap.put("MusicNumber", doubleValueOfInt(MusicNumber));
			varMap.put("ImgEnergy", doubleValueOfInt(ImgEnergy));
			varMap.put("ImgStrength", doubleValueOfInt(ImgStrength));
			varMap.put("ImgDefence", doubleValueOfInt(ImgDefence));
			varMap.put("ImgGold", doubleValueOfInt(ImgGold));
			varMap.put("ImgBom", doubleValueOfInt(ImgBom));
			varMap.put("ImgStatusFrame", doubleValueOfInt(ImgStatusFrame));
			varMap.put("ImgItemFrame", doubleValueOfInt(ImgItemFrame));
			varMap.put("ImgMainFrame", doubleValueOfInt(ImgMainFrame));
			varMap.put("ImgClickItem", doubleValueOfInt(ImgClickItem));
			if(Mode == 2) {
				varMap.put("Key", doubleValueOfInt(Key));
				varMap.put("KeyName", keyName);
				varMap.put("MouseX", doubleValueOfInt(MouseX));
				varMap.put("MouseY", doubleValueOfInt(MouseY));
			}
			
			//varMap.put("bNoExec", doubleValueOfInt(bNoExec ? 1 : 0));
			varMap.put("PartsNo", doubleValueOfInt(partsNo));
			varMap.put("Time", doubleValueOfInt(WWAeval_timer));
			varMap.put("MapLocationX", doubleValueOfInt(mapLocationX));
			varMap.put("MapLocationY", doubleValueOfInt(mapLocationY));
			
			//さらにごり押しで配列をdoubleに変換
			for(int i = 0; i < ItemBox.length; i++)
				ItemBoxDouble[i] = ItemBox[i];
			for(int i = 0; i < Valiable.length; i++)
				ValiableDouble[i] = Valiable[i];
			
		}
		
		//大きな配列の変換は高速化のため要求に応じてやる　判定は手抜き
		//WWA関数の処理めんどくせ…
		boolean hasIsEmpty = line.indexOf("IsEmpty") != -1;
		boolean hasSearchParts = line.indexOf("PartsFind") != -1 || line.indexOf("SearchParts") != -1;
		
		if(hasIsEmpty || hasSearchParts || line.indexOf("Map[") != -1) {
			MapModded = true;
			for(int i = 0; i < Map.length; i++)
				for(int j = 0; j < Map[0].length; j++)
					MapDouble[i][j] = Map[i][j];
		}
		if(hasIsEmpty || hasSearchParts || line.indexOf("Object[") != -1) {
			ObjectModded = true;
			for(int i = 0; i < Object.length; i++)
				for(int j = 0; j < Object[0].length; j++)
					ObjectDouble[i][j] = Object[i][j];
		}
		if(hasIsEmpty || line.indexOf("AtrMap[") != -1) {
			AtrMapModded = true;
			for(int i = 0; i < AtrMap.length; i++)
				for(int j = 0; j < AtrMap[0].length; j++)
					AtrMapDouble[i][j] = AtrMap[i][j];
		}
		if(line.indexOf("AtrObject[") != -1) {
			AtrObjectModded = true;
			for(int i = 0; i < AtrObject.length; i++)
				for(int j = 0; j < AtrObject[0].length; j++)
					AtrObjectDouble[i][j] = AtrObject[i][j];
		}
		
		Object evaledValue = null;
		try {
			Expression exp = ExpRuleFactory.getDefaultRule().parse(evalExpr);
			exp.setVariable(new MapVariable(varMap));
			exp.refactorName(new RefactorVarName(null, "hp", "Energy"));
			exp.refactorName(new RefactorVarName(null, "hpmax", "EnergyMax"));
			exp.refactorName(new RefactorVarName(null, "at", "Strength"));
			exp.refactorName(new RefactorVarName(null, "df", "Defence"));
			exp.refactorName(new RefactorVarName(null, "gd", "Gold"));
			exp.refactorName(new RefactorVarName(null, "var", "Valiable"));
			exp.refactorName(new RefactorVarName(null, "item", "ItemBox"));
			exp.setFunction(WWAevalFunc);
			evaledValue = exp.eval();
		}
		catch(Exception e) {
			evaledValue = "\n※eval文エラー※\n" + e.toString() + "\n";
			e.printStackTrace();
		}
		
		String replacement = "";
		if(evaledValue == null) {
		}
		else if(evaledValue instanceof Number) {
			double evaledValueD = ((Number) evaledValue).doubleValue();
			int evaledValueI = (int)evaledValueD;
			
			if(evaledValueD == evaledValueI) //結果が整数なら小数点以下を消す
				replacement = String.valueOf(evaledValueI);
			else
				replacement = String.valueOf(evaledValueD);
		}
		else if(evaledValue instanceof String) {
			replacement = ((String) evaledValue)
				//多分高確率で必要ない「.0」を消す　誤爆する
				.replaceAll("(?<=\\d)\\.0(?!\\d)", "");
		}
		else if(evaledValue instanceof Boolean) {
			replacement = ((Boolean) evaledValue).booleanValue() ? "1" : "0";
		}
		else if(evaledValue.getClass().isArray()) {
			replacement = ArrayToString(evaledValue);
			//$mapや$faceで使える形式にする
			replacement = replacement.substring(1, replacement.length() - 1);
		}
		else {
			replacement = evaledValue.toString();
		}
		m.appendReplacement(evaledLine, quoteReplacement(replacement));
		if(WWAeval_debug)
			System.out.println("eval処理で \""+m.group()+"\" を \""+replacement+"\" に変換");
	}
	
	if(findFlag) {
		//値をハッシュから元変数に戻す
		//参照したが戻してないもの：PartsObject, PartsMap, PartsX, PartsY, Key, MouseX, MouseY, 
		//MapWidth, MapPartsMax, ObjectPartsMax, MesNumberMax, Time
		//ForCountは直接Mapを扱い、参照渡しは参照渡しなので戻す必要なし
		PlayerX = ((Double)varMap.get("PlayerX")).intValue();
		PlayerY = ((Double)varMap.get("PlayerY")).intValue();
		PlayerDirect = (char)((Double)varMap.get("PlayerDirect")).intValue();
		Energy = ((Double)varMap.get("Energy")).intValue();
		EnergyMax = ((Double)varMap.get("EnergyMax")).intValue();
		Strength = ((Double)varMap.get("Strength")).intValue();
		Defence = ((Double)varMap.get("Defence")).intValue();
		Gold = ((Double)varMap.get("Gold")).intValue();
		Step = ((Double)varMap.get("Step")).intValue();
		TimerCount = ((Double)varMap.get("TimerCount")).intValue();
		GameoverX = ((Double)varMap.get("GameoverX")).intValue();
		GameoverY = ((Double)varMap.get("GameoverY")).intValue();
		ImgChara = ((Double)varMap.get("ImgChara")).intValue();
		ImgButton = ((Double)varMap.get("ImgButton")).intValue();
		bSaveStop = ((Double)varMap.get("bSaveStop")).intValue();
		bDefault = ((Double)varMap.get("bDefault")).intValue();
		bPaintAll = (((Double)varMap.get("bPaintAll")).intValue() != 0);
		bStopInput = (((Double)varMap.get("bStopInput")).intValue() != 0);
		bAnmItem = ((Double)varMap.get("bAnmItem")).intValue();
		MusicNumber = ((Double)varMap.get("MusicNumber")).intValue();
		ImgEnergy = ((Double)varMap.get("ImgEnergy")).intValue();
		ImgStrength = ((Double)varMap.get("ImgStrength")).intValue();
		ImgDefence = ((Double)varMap.get("ImgDefence")).intValue();
		ImgGold = ((Double)varMap.get("ImgGold")).intValue();
		ImgBom = ((Double)varMap.get("ImgBom")).intValue();
		ImgStatusFrame = ((Double)varMap.get("ImgStatusFrame")).intValue();
		ImgItemFrame = ((Double)varMap.get("ImgItemFrame")).intValue();
		ImgMainFrame = ((Double)varMap.get("ImgMainFrame")).intValue();
		ImgClickItem = ((Double)varMap.get("ImgClickItem")).intValue();
		
		//bNoExec = (((Double)varMap.get("bNoExec")).intValue() != 0);
		
		//配列を戻す
		for(int i = 0; i < ItemBox.length; i++)
			ItemBox[i] = (int)ItemBoxDouble[i];
		
		for(int i = 0; i < Valiable.length; i++)
			Valiable[i] = (int)ValiableDouble[i];
		
		if(MapModded)
			for(int i = 0; i < Map.length; i++)
				for(int j = 0; j < Map[0].length; j++)
					Map[i][j] = (short)MapDouble[i][j];
		
		if(ObjectModded)
			for(int i = 0; i < Object.length; i++)
				for(int j = 0; j < Object[0].length; j++)
					Object[i][j] = (short)ObjectDouble[i][j];
		
		if(AtrMapModded)
			for(int i = 0; i < AtrMap.length; i++)
				for(int j = 0; j < AtrMap[0].length; j++)
					AtrMap[i][j] = (int)AtrMapDouble[i][j];
		
		if(AtrObjectModded)
			for(int i = 0; i < AtrObject.length; i++)
				for(int j = 0; j < AtrObject[0].length; j++)
					AtrObject[i][j] = (int)AtrObjectDouble[i][j];
		
		
		m.appendTail(evaledLine);
		line = evaledLine.toString();
	}
	return line;
}

//ここから独自関数の定義
//何か変数を使うときはvarMapの方を使う
public int ItemCount(double partsNo) {
	double[] ItemBoxD = (double[])varMap.get("ItemBox");
	int count = 0;
	for(int i = 0; i < ItemBox.length; i++)
		if(ItemBoxD[i] == partsNo)
			count++;
	return count;
}
public int ItemCount() { return ItemCount(0); }

public int ItemIndexOf(double partsNo) {
	double[] ItemBoxD = (double[])varMap.get("ItemBox");
	for(int i = 0; i < ItemBox.length; i++)
		if(ItemBoxD[i] == partsNo)
			return i;
	return -1;
}
public int ItemIndexOf() { return ItemIndexOf(0); }

public int LastItemIndexOf(double partsNo) {
	double[] ItemBoxD = (double[])varMap.get("ItemBox");
	for(int i = 0; i < ItemBox.length; i++)
		if(ItemBoxD[11 - i] == partsNo)
			return 11 - i;
	return -1;
}
public int LastItemIndexOf() { return LastItemIndexOf(0); }

public int ReplaceItem(double oldNo, double newNo, double count) {
	double[] ItemBoxD = (double[])varMap.get("ItemBox");
	int repCount = 0;
	for(int i = 0; i < ItemBox.length; i++) {
		if(count <= 0) break;
		
		if(ItemBoxD[i] == oldNo) {
			count--;
			repCount++;
			ItemBoxD[i] = newNo;
		}
	}
	return repCount;
}
public int ReplaceItem(double oldNo, double newNo) { return ReplaceItem(oldNo, newNo, 1); }
public int ReplaceItem(double oldNo) { return ReplaceItem(oldNo, 0); }

public String DirSwitch(String up, String right, String down, String left, double dir) {
	return dir == 8 ? up :
		dir == 6 ? right :
		dir == 2 ? down : left;
}
public String DirSwitch(String up, String right, String down, String left) {
	return DirSwitch(up, right, down, left, ((Double)varMap.get("PlayerDirect")).doubleValue());
}

public double DirSwitch(double up, double right, double down, double left, double dir) {
	return dir == 8 ? up :
		dir == 6 ? right :
		dir == 2 ? down : left;
}
public double DirSwitch(double up, double right, double down, double left) {
	return DirSwitch(up, right, down, left, ((Double)varMap.get("PlayerDirect")).doubleValue());
}

public String DirJapanese(String typeStr, double dir) {
	int type = typeStr.equals("方角") ? 1 : typeStr.equals("矢印") ? 2 : 0;
	
	return type == 0 ? DirSwitch("上", "右", "下", "左", dir) :
		type == 1 ? DirSwitch("北", "東", "南", "西", dir) :
		DirSwitch("↑", "→", "↓", "←", dir);
}
public String DirJapanese(String typeStr) {
	return DirJapanese(typeStr, ((Double)varMap.get("PlayerDirect")).intValue());
}
public String DirJapanese() { return DirJapanese(""); }

public double[] DirXY(double forward, double right, String type, double dir) {

	int x = (int)DirSwitch(right, forward, -right, -forward, dir);
	int y = (int)DirSwitch(-forward, right, forward, -right, dir);
	if(type.equalsIgnoreCase("P")) {
		x += PlayerX; y += PlayerY;
	}
	else if(type.equals("+")) {
		x += PartsX; y += PartsY;
	}
	double[] XY = {x, y};
	return XY;
}
public double[] DirXY(double forward, double right, String type) {
	return DirXY(forward, right, type, ((Double)varMap.get("PlayerDirect")).intValue());
}
public double[] DirXY(double forward, double right) {
	return DirXY(forward, right, "P");
}

public int DirX(double forward, double right, String type, double dir) {
	double[] XY = DirXY(forward, right, type, dir);
	return (int)XY[0];
}
public int DirX(double forward, double right, String type) {
	return DirX(forward, right, type, ((Double)varMap.get("PlayerDirect")).intValue());
}
public int DirX(double forward, double right) {
	return DirX(forward, right, "P");
}

public int DirY(double forward, double right, String type, double dir) {
	double[] XY = DirXY(forward, right, type, dir);
	return (int)XY[1];
}
public int DirY(double forward, double right, String type) {
	return DirY(forward, right, type, ((Double)varMap.get("PlayerDirect")).intValue());
}
public int DirY(double forward, double right) {
	return DirY(forward, right, "P");
}

public int DirNoOf(String word) {
	return word.equals("上") ? 8 :
		word.equals("右") ? 6 :
		word.equals("下") ? 2 :
		word.equals("左") ? 4 : 0;
}

public double RotatePlayer(double times, double dir) {
	times = Math.floor(times % 4);
	if(times < 0) times = 4 - times;
	
	for(int i = 0; i < times; i++)
		dir = DirSwitch(6, 2, 4, 8, dir);
	return dir;
}
public double RotatePlayer(double times) {
	return RotatePlayer(times, ((Double)varMap.get("PlayerDirect")).doubleValue());
}
public double RotatePlayer() { return RotatePlayer(1); }

public int NoOf(String name, double type) {
	if(name.length() == 0) return 0;
	String[] names = type == 1 ? WWAeval_mapName : WWAeval_objName;
	for (int i = 0; i < names.length; i++) {
		if(names[i].equals(name))
			return i;
	}
	return 0;
}
public int NoOf(String name) { return NoOf(name, 0); }

public int IsEmpty(double xD, double yD, String type) {
	double[][] ObjectD = (double[][])varMap.get("Object");
	double[][] MapD = (double[][])varMap.get("Map");
	double[][] AtrMapD = (double[][])varMap.get("AtrMap");
	int x = (int)xD;
	int y = (int)yD;
	
	try {
		if((type.indexOf("物") != -1 && ObjectD[y][x] != 0) ||
			(type.indexOf("壁") != -1 && MapD[y][x] != 0 && AtrMapD[(int)MapD[y][x]][ATR_TYPE] == MAP_WALL) ||
			(type.indexOf("背") != -1 && MapD[y][x] != 0))
		return 0;
	}
	catch(ArrayIndexOutOfBoundsException e) {
		return 0;
	}
	
	return 1;
}
public int IsEmpty(double x, double y) { return IsEmpty(x, y, "物"); }
public int IsEmpty(double[] xy, String type) { return IsEmpty(xy[0], xy[1], type); }
public int IsEmpty(double[] xy) { return IsEmpty(xy[0], xy[1]); }

public int InMap(double x, double y) {
	if(0 <= x && x <= MapWidth && 0 <= y && y <= MapWidth)
		return 1;
	else
		return 0;
}
public int InMap(double[] xy) { return InMap(xy[0], xy[1]); }

public double[] SearchPartsXY(double partsNo, double type) {
	double[][] target = (double[][])varMap.get(type == 1 ? "Map" : "Object");
	double[] XY = {-1, -1};
	search: for (int x = 0; x < MapWidth; x++) {
		for (int y = 0; y < MapWidth; y++) {
			if(target[y][x] == partsNo){
				XY[0] = x; XY[1] = y;
				break search;
			}
		}
	}
	return XY;
}
public double[] SearchPartsXY(double partsNo) { return SearchPartsXY(partsNo, 0); }

public int PartsFind(double partsNo, double type) {
	return (SearchPartsXY(partsNo, type))[0] == -1 ? 0 : 1;
}
public int PartsFind(double partsNo) { return PartsFind(partsNo, 0); }

public int SearchPartsX(double partsNo, double type) {
	double[] XY = SearchPartsXY(partsNo, type);
	return (int)XY[0];
}
public int SearchPartsX(double partsNo) { return SearchPartsX(partsNo, 0); }

public int SearchPartsY(double partsNo, double type) {
	double[] XY = SearchPartsXY(partsNo, type);
	return (int)XY[1];
}
public int SearchPartsY(double partsNo) { return SearchPartsY(partsNo, 0); }

public double[] ToPixelXY(double x, double y) {
	double[] pixXY = new double[2];
	pixXY[0] = (int)x % 10 * 40;
	pixXY[1] = (int)y % 10 * 40;
	if(x == (mapLocationX + 1) * 10) pixXY[0] += 400;
	if(y == (mapLocationY + 1) * 10) pixXY[1] += 400;
	return pixXY;
}
public double[] ToPixelXY(double[] xy) { return ToPixelXY(xy[0], xy[1]);}

public int ToPixelX(double x) {
	double[] pixXY = ToPixelXY(x, 0);
	return (int)pixXY[0];
}
public int ToPixelY(double y) {
	double[] pixXY = ToPixelXY(0, y);
	return (int)pixXY[1];
}

public double[] ToMapCoordXY(double x, double y, double inDisplay) {
	double[] mapXY = new double[2];
	mapXY[0] = (int)(x + 20) / 40;
	mapXY[1] = (int)(y + 20) / 40;
	if(inDisplay != 0) {
		mapXY[0] += mapLocationX * 10;
		mapXY[1] += mapLocationY * 10;
	}
	return mapXY;
}
public double[] ToMapCoordXY(double x, double y) { return ToMapCoordXY(x, y, 1); }
public double[] ToMapCoordXY(double[] xy, double inDisplay) { return ToMapCoordXY(xy[0], xy[1], inDisplay); }
public double[] ToMapCoordXY(double[] xy) { return ToMapCoordXY(xy[0], xy[1]); }

public int ToMapCoordX(double x, double inDisplay) {
	double[] mapXY = ToMapCoordXY(x, 0, inDisplay);
	return (int)mapXY[0];
}
public int ToMapCoordX(double x) { return ToMapCoordX(x, 1); }

public int ToMapCoordY(double y, double inDisplay) {
	double[] mapXY = ToMapCoordXY(0, y, inDisplay);
	return (int)mapXY[1];
}
public int ToMapCoordY(double y) { return ToMapCoordY(y, 1); }

public double UniformRandom(double seed) {
	//剰余を使って乱数生成　かなり規則性あるけど別にいいやね…
	double gameSeed = (double)WWAeval_randomSeed / 10;
	double kekka = (gameSeed * ++seed * seed + Math.pow(gameSeed, (seed * 29) % 29.3586)) % 100000;
	return Math.abs(kekka) / 100000;
}
public double UniformRandom() {
	double seed = PartsObject == 0 ? PartsMap + 0.0236 : PartsObject + 0.601; //適当
	return UniformRandom(seed);
}

public String toString(double no) {
	return no == (int)no ? Integer.toString((int)no) : Double.toString(no);
}

public double parseDouble(String str) {
	try {
		return Double.parseDouble(str);
	}
	catch(NumberFormatException e) {
		StringBuffer sb = new StringBuffer();
		int length = str.length();
		int i = 0;
		if(str.charAt(0) == '-') {
			sb.append('-'); i++;
		}
		for(; i < length; i++) {
			char c = str.charAt(i);
			if(c == '-' || c == '.' || ('0' <= c && c <= '9'))
				sb.append(c);
			else if(c == '_')
				continue;
			else
				break;
		}
		
		return sb.length() == 0 ? 0 : Double.parseDouble(sb.toString());
	}
}
//独自関数の定義ここまで


//小物

//音を鳴らす
void soundPlay(String name) {
	try {
		WWAevalApplet.getInstance().soundPlay(name);
	}
	catch(NullPointerException e) {
		System.out.println("ブラウザからWWAevalAppletが呼び出されていません。");
	}
}
void soundPlay(double name) { soundPlay(Integer.toString((int)name)); }


ArrayList splitConsClip(String str, String tokenizer, String clipPattern) {
	ArrayList split = new ArrayList();
	int prevEnd = 0;
	Matcher cr = Pattern.compile(tokenizer + "|" + clipPattern, Pattern.DOTALL).matcher(str);
	
	while(cr.find()) {
		if(cr.group().equals(tokenizer)) {
			split.add(str.substring(prevEnd, cr.start()));
			prevEnd = cr.end();
		}
	}
	if(prevEnd != str.length()) split.add(str.substring(prevEnd));
	return split;
}

String[] splitAndTrim(String str, char tokenizer) {
	ArrayList sp = new ArrayList();
	StringBuffer sb = new StringBuffer();
	StringBuffer spaceTemp = new StringBuffer();
	int length = str.length();
	for (int i = 0; i < length; i++) {
		char c = str.charAt(i);
		if(c == ' ') {
			if(sb.length() != 0) {
				spaceTemp.append(c);
			}
		}
		else if(c == tokenizer) {
			sp.add(sb.toString());
			sb.setLength(0);
			spaceTemp.setLength(0);
		}
		else if(spaceTemp.length() != 0) {
			sb.append(spaceTemp).append(c);
			spaceTemp.setLength(0);
		}
		else {
			sb.append(c);
		}
	}
	sp.add(sb.toString());
	return (String[])sp.toArray(new String[sp.size()]);
}


boolean hasAtLineHead(String str, String prefix) {
	return str.startsWith(prefix) || str.indexOf("\n" + prefix) != -1;
}

//0:表示メッセージの行数　1:<p>の数
int[] countMes(String str) {
	if(str == null) return new int[]{0, 0};
	str = str.toLowerCase();
	int length = str.indexOf("<c>");
	if(length != -1)
		str = str.substring(0, length);
	else
		length = str.length();
	
	if(str.length() == 0) return new int[]{0, 0};
	
	int dispLineCount = 0;
	boolean isFirst = true;
	for(int d = -1; d != -1 || isFirst; d = str.indexOf("\n", d + 1)) {
		isFirst = false;
		if(d + 1 < length && str.charAt(d + 1) != '$')
			dispLineCount++;
	}
	
	int pCount = 0;
	for(int p = str.indexOf("<p>"); p != -1; p = str.indexOf("<p>", p + 1))
		pCount++;
	
	return new int[]{dispLineCount, pCount};
}

int getNoByNameIfHas(String name, int type) {
	try {
		return Integer.parseInt(name);
	}
	catch(NumberFormatException e) {
		return (int)NoOf(name, type);
	}
}

double d(String str) {
	try {
		return Double.parseDouble(str.trim());
	}
	catch(NumberFormatException e) {}
	return 0;
}
int i(String str) {
	return (int)d(str);
}

double relDouble(String str, double orig) {
	str = str.trim();
	char c = str.charAt(0);
	return d(str) + (c == '+' || c == '-' ? orig : 0);
}
int relInt(String str, int orig) {
	return (int)relDouble(str, orig);
}

//一部の数値のラッパーをキャッシュする
Integer intValueOf(int n) {
	return (0 <= n && n < intCache.length) ? intCache[n] : new Integer(n);
}

Double doubleValueOfInt(int n) {
	return (0 <= n && n < doubleCache.length) ? doubleCache[n] : new Double(n);
}
Double doubleValueOfInt(double n) { return doubleValueOfInt((int)n); }

//Matcher#quoteReplacement()をコピペしただけのもの
String quoteReplacement(String s) {
	if ((s.indexOf('\\') == -1) && (s.indexOf('$') == -1))
		return s;
	StringBuffer sb = new StringBuffer();
	for (int i=0; i<s.length(); i++) {
		char c = s.charAt(i);
		if (c == '\\') {
			sb.append("\\\\");
		} else if (c == '$') {
			sb.append('\\').append('$');
		} else {
			sb.append(c);
		}
	}
	return sb.toString();
}

String ArrayToString(Object obj) {
	StringBuffer sb = new StringBuffer();
	if(obj.getClass().isArray()) {
		sb.append('[');
		int length = Array.getLength(obj);
		for (int i = 0; i < length; i++) {
			sb.append(ArrayToString(Array.get(obj, i)));
			if(i < length - 1)
				sb.append(", ");
		}
		sb.append(']');
		return sb.toString();
	}
	else {
		String str = String.valueOf(obj);
		if(obj instanceof Number && str.endsWith(".0"))
			str = str.substring(0, str.length() - 2);
		return str;
	}
}

//キーを文字に変換　これでいいのか？
//http://java.sun.com/javase/ja/6/docs/ja/api/java/awt/Event.html
String getKeyName(int key) {
	return key == Event.F1 ? "F1"
		: key == Event.F2 ? "F2"
		: key == Event.F3 ? "F3"
		: key == Event.F4 ? "F4"
		: key == Event.F5 ? "F5"
		: key == Event.F6 ? "F6"
		: key == Event.F7 ? "F7"
		: key == Event.F8 ? "F8"
		: key == Event.F9 ? "F9"
		: key == Event.F10 ? "F10"
		: key == Event.F11 ? "F11"
		: key == Event.F12 ? "F12"
		: key == Event.HOME ? "Home"
		: key == Event.END ? "End"
		: key == Event.PGUP ? "PGUP"
		: key == Event.PGDN ? "Page Down"
		: key == Event.UP ? "Up"
		: key == Event.DOWN ? "Down"
		: key == Event.LEFT ? "Left"
		: key == Event.RIGHT ? "Right"
		: key == Event.PRINT_SCREEN ? "Print Screen"
		: key == Event.SCROLL_LOCK ? "Scroll Lock"
		: key == Event.CAPS_LOCK ? "Caps Lock"
		: key == Event.NUM_LOCK ? "Num Lock"
		: key == Event.PAUSE ? "Pause"
		: key == Event.INSERT ? "Insert"
		: key == Event.ENTER ? "Enter"
		: key == Event.BACK_SPACE ? "Back Space"
		: key == Event.TAB ? "Tab"
		: key == Event.ESCAPE ? "Escape"
		: key == Event.DELETE ? "Delete"
		: Character.isDefined((char)key) ? String.valueOf((char)key)
		: "Unknown";
}

//ここまで小物

//画像を描画する
void drawPict() {

	if(WWAeval_forcePaintAll) bPaintAll = true;
	
	//パーツごとのエフェクト
	for(int i = 0; i < 2; i++) {
		int[] effList = i == 0 ? WWAeval_mapEffect : WWAeval_objEffect;
		Map partsMap = i == 0 ? WWAeval_mapMap : WWAeval_objectMap;
		
		for(int j = 0; j < effList.length; j++) {
			int cropNo = effList[j];
			if(cropNo == 0) continue;
			
			LinkedList partsList = (LinkedList)partsMap.get(intValueOf(j));
			if(partsList == null) continue;
			
			for (Iterator it = partsList.iterator(); it.hasNext();) {
				int partsX = ((Integer)it.next()).intValue();
				if(!it.hasNext()) break;
				int partsY = ((Integer)it.next()).intValue();
				
				
				GrMap.drawImage(ImgCrop[cropNo], partsX * 40 - ImgCrop[cropNo].getWidth(null) / 2 + 20,
					partsY * 40 - ImgCrop[cropNo].getHeight(null) / 2 + 20, null);
			}
		}
	}
	
	//$pictureによる画像（物体限定）
	boolean waitflag = false;
	boolean releaseflag = WWAeval_releaseNext;
	WWAeval_releaseNext = false;
	
	Font[] defFont = { GrMap.getFont(), GrStatus.getFont() };
	Color[] defColor = { GrMap.getColor(), GrStatus.getColor() };
	AffineTransform[] defTransform = { GrMap.getTransform(), GrStatus.getTransform() };
	
	if(WWAeval_tempWaited) bStopInput = false;
	
	for(int i = WWAeval_pictIndex; i < WWAeval_pictIndex + WWAeval_pictLength; i++) {
		int pictObjNo = Valiable[i] % PARTS_MAX;
		int pictMesNo = AtrObject[pictObjNo][ATR_STRING];
		
		if(pictObjNo == 0 || pictMesNo == 0) continue;
		
		//Variableの添え字をそのまま使う
		double[] pict = WWAeval_atrPict[i - WWAeval_pictIndex];
		ArrayList[] pictList = WWAeval_atrPictList[i - WWAeval_pictIndex];
		if(pict[P_DEF] == 0) setPict(i - WWAeval_pictIndex, i);
		
		if(pict[P_COUNT] == 0 && pict[P_INITSOUND] != 0)
			soundPlay(pict[P_INITSOUND]);
			//MusicNumber = (int)pict[P_INITSOUND];
		
		Graphics2D target = pict[P_ONSTATUS] != 0 ? GrStatus : GrMap;
		boolean canDraw = (pict[P_BEGIN] <= pict[P_COUNT] && (pict[P_HIDE] == 0 || pict[P_COUNT] <= pict[P_HIDE]));
		boolean canMove = pict[P_MOVE_START] <= pict[P_COUNT] && 
							(pict[P_MOVE_STOP] == 0 || pict[P_COUNT] <= pict[P_MOVE_STOP]);
		
		if(pict[P_HASPARTS] != 0) {
			if((pict[P_HASPARTS] >= PARTS_MAX ? WWAeval_mapMap : WWAeval_objectMap).get(
					intValueOf((int)pict[P_HASPARTS] % PARTS_MAX)) == null)
				//canDraw = false;
				continue;
		}
		
		
		if(canDraw) {
			int imgNo = ((Integer)pictList[PA_CROPS].get(
							(int)(pict[P_COUNT] / pict[P_ANIME_DELAY])
								% pictList[PA_CROPS].size())).intValue();
			
			int startX = 0;
			int startY = 0;
			double widthSum = pict[P_WIDTH] + pict[P_INTERVALX];
			double heightSum = pict[P_HEIGHT] + pict[P_INTERVALY];
			
			if(pict[P_FILL] != 0 && widthSum != 0 && heightSum != 0) { //どうなってるのこれ…
				
				int shiftDiffX = 0;
				int shiftDiffY = 0;
				
				if(pict[P_POLAR] != 0) {
					int spaceWidth = (int)Math.max(GRWIDTH / 2 + Math.abs(GRWIDTH / 2 - pict[P_ORIGINX]), 
							GRHEIGHT / 2 + Math.abs(GRHEIGHT / 2 - pict[P_ORIGINY]));
					pict[P_REPEATX] = Math.ceil(spaceWidth * 1.414 / widthSum);
					pict[P_REPEATY] = Math.ceil(360 / heightSum);
					
				}
				else {
				
					pict[P_REPEATX] = Math.ceil(GRWIDTH / widthSum + 1);
					pict[P_REPEATY] = Math.ceil(GRHEIGHT / heightSum + 1);
					
					pict[P_SHIFTX] %= widthSum;
					pict[P_SHIFTY] %= heightSum;
					
					shiftDiffX = (int)Math.ceil(Math.abs(pict[P_SHIFTX] * pict[P_REPEATY])
									/ widthSum) + 1;
					shiftDiffY = (int)Math.ceil(Math.abs(pict[P_SHIFTY] * pict[P_REPEATX])
									/ heightSum) + 1;
				}
				
				int diffSumX = (int)(double)(pict[P_DISPX] / widthSum - (pict[P_DISPX] < 0 ? 1 : 0)
								- pict[P_DISPY] / heightSum * pict[P_SHIFTX] / widthSum);
				int diffSumY = (int)(double)(pict[P_DISPY] / heightSum - (pict[P_DISPY] < 0 ? 1 : 0)
								- pict[P_DISPX] / widthSum * pict[P_SHIFTY] / heightSum);
			
				//shiftが2方向共に設定されてるとバグる　以下の設定は不完全な応急処置
				diffSumX += (int)(double)(diffSumX * Math.abs(pict[P_SHIFTY]) / heightSum);
				diffSumY += (int)(double)(diffSumY * Math.abs(pict[P_SHIFTX]) / widthSum);
				
				startX += -shiftDiffX - diffSumX;
				startY += -shiftDiffY - diffSumY;
				pict[P_REPEATX] += shiftDiffX - diffSumX;
				pict[P_REPEATY] += shiftDiffY - diffSumY;
			}
			
			if(pict[P_ALPHA] != 1) {
				//doubleからfloatへの正しい変換方法がいまいちわかりません！
				float alpha = (float)(double)Math.max(0, Math.min(1, pict[P_ALPHA]));
				target.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
			}
			
			String drawMes = null;
			int drawMesX = 0;
			int drawMesY = 0;
			int fontSize = 0;
			if(!pictList[PA_STRING].isEmpty()) {	//0:X　1:Y　2:文字列　...
				int span = (int)(pict[P_COUNT] / pict[P_ANIME_DELAY]) % (pictList[PA_STRING].size() - 2);
				
				drawMes = (String)pictList[PA_STRING].get(2 + span);
				drawMesX = ((Integer)pictList[PA_STRING].get(0)).intValue();
				drawMesY = ((Integer)pictList[PA_STRING].get(1)).intValue();
				
				target.setColor((pictList[PA_COLORS].size() >= 2)
					? (Color)pictList[PA_COLORS].get(1) : Color.BLACK);
				
				if(!pictList[PA_FONT].isEmpty()) {
					target.setFont((Font)pictList[PA_FONT].get(0));
					
					if(((Boolean)pictList[PA_FONT].get(1)).booleanValue())
						target.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
							RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				}
				fontSize = target.getFont().getSize();
			}
			
			for(int y = startY; y < pict[P_REPEATY]; y++) {
				for(int x = startX; x < pict[P_REPEATX]; x++) {
					int dispX = (int)(pict[P_DISPX] + x * widthSum + y * pict[P_SHIFTX]);
					int dispY = (int)(pict[P_DISPY] + y * heightSum + x * pict[P_SHIFTY]);
					
					//マジックナンバーは気にしてはいけない
					if(pict[P_RANDOMX] != 0)
						dispX += (int)((UniformRandom(pictObjNo / 101 + (x + y * 10 + 27) * pict[P_RANDOM_SEED])
								- 0.5) * pict[P_RANDOMX] * 2);
					if(pict[P_RANDOMY] != 0)
						dispY += (int)((UniformRandom(pictObjNo / 103 + (x + y * 10 + 41) * pict[P_RANDOM_SEED])
								- 0.5) * pict[P_RANDOMY] * 2);
					
					if(!pictList[PA_CIRCLES].isEmpty()) { //0:X振幅　1:Y振幅　2:角速度　3:初期角度　...
						for(int j = 0; j < pictList[PA_CIRCLES].size() - 3; j += 4) {
							double cAngle = Math.toRadians(((Double)pictList[PA_CIRCLES].get(j + 3)).doubleValue());
							dispX += Math.round(((Double)pictList[PA_CIRCLES].get(j)).doubleValue()
										* Math.cos(cAngle));
							dispY += Math.round(((Double)pictList[PA_CIRCLES].get(j + 1)).doubleValue()
										* Math.sin(cAngle));
						}
					}
					
					if(pict[P_POLAR] != 0) { //X座標を動径に、Y座標を偏角（度数法）にそのままいれかえる
						int r = dispX;
						double angle = Math.toRadians(dispY);
						if(pict[P_POLAR_ROT] != 0) pict[P_ROTATE] = dispY;
						
						dispX = (int)(double)(r * Math.cos(angle) + pict[P_ORIGINX]);
						dispY = (int)(double)(r * Math.sin(angle) + pict[P_ORIGINY]);
					}
					
					if(pict[P_CENTER] != 0) { //基準位置を真ん中に
						dispX -= (int)pict[P_WIDTH] / 2;
						dispY -= (int)pict[P_HEIGHT] / 2;
					}
					
					int widthPCrop = (int)(pict[P_WIDTH] / pict[P_COMBINEX]);
					int heightPCrop = (int)(pict[P_HEIGHT] / pict[P_COMBINEY]);
					
					if(pictList[PA_ONPARTS].isEmpty()) {
						drawSub(target, defTransform[(int)pict[P_ONSTATUS]], pict, pictList, imgNo, x, y,
							dispX, dispY, widthPCrop, heightPCrop, drawMes, drawMesX, drawMesY, fontSize);
					}
					else {
						for(int j = 0; j < pictList[PA_ONPARTS].size(); j++) {
							int partsNo = ((Integer)pictList[PA_ONPARTS].get(j)).intValue();
							boolean isMap = partsNo >= PARTS_MAX;
							partsNo %= PARTS_MAX;
							LinkedList partsList = (LinkedList)(isMap ? WWAeval_mapMap : WWAeval_objectMap)
													.get(intValueOf(partsNo));
							if(partsList == null) continue;
							for (Iterator it = partsList.iterator(); it.hasNext();) {
								int partsX = ((Integer)it.next()).intValue();
								if(!it.hasNext()) break;
								int partsY = ((Integer)it.next()).intValue();
								
								//dispの値を相対座標とみなす
								drawSub(target, defTransform[(int)pict[P_ONSTATUS]], pict, pictList, imgNo,
									x, y, dispX + partsX * 40, dispY + partsY * 40,
									widthPCrop, heightPCrop, drawMes, drawMesX, drawMesY, fontSize);
							}
						}
					}
				}
			}
			
			if(pict[P_ALPHA] != 1)
				target.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			
			if(!pictList[PA_STRING].isEmpty()) {
				target.setFont(defFont[(int)pict[P_ONSTATUS]]);
				target.setColor(defColor[(int)pict[P_ONSTATUS]]);
				target.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
			}
		}
		
		if(canMove) {
			pict[P_DISPX] += pict[P_SPEEDX];
			pict[P_DISPY] += pict[P_SPEEDY];
			pict[P_SPEEDX] += pict[P_ACCELX];
			pict[P_SPEEDY] += pict[P_ACCELY];
			
			pict[P_WIDTH] += pict[P_SPEEDW];
			pict[P_HEIGHT] += pict[P_SPEEDH];
			pict[P_SPEEDW] += pict[P_ACCELW];
			pict[P_SPEEDH] += pict[P_ACCELH];
			
			pict[P_ALPHA] += pict[P_SPEEDA];
			pict[P_ROTATE] += pict[P_SPEEDR];
			
			for(int j = 0; j < pictList[PA_CIRCLES].size() - 3; j += 4) {
				double origAngle = ((Double)pictList[PA_CIRCLES].get(j + 3)).doubleValue();
				double newAngle = origAngle + ((Double)pictList[PA_CIRCLES].get(j + 2)).doubleValue();
				pictList[PA_CIRCLES].set(j + 3, new Double(newAngle % 360));
			}
		}
		
		if(pict[P_COUNT] != Integer.MAX_VALUE)
			pict[P_COUNT]++;
		else
			pict[P_COUNT] = Integer.MAX_VALUE / 2 + 1;
		
		
		if(pict[P_COUNT] > 10000 && pict[P_COUNT] % 50 == 0) {
			if(Math.abs(pict[P_DISPX]) + Math.abs(pict[P_DISPY]) > 1000000) { //定期的にどうでもいいものは消す
				if(pict[P_FILL] == 0) {
					pict[P_END] = 1;
					pict[P_CONNECT] = 0;
				}
				else { //オーバーフロー対策　見栄えは死ぬ
					pict[P_DISPX] = pict[P_DISPY] = 0;
				}
			}
		}
		
		
		if(pict[P_END] != 0 && pict[P_END] <= pict[P_COUNT]) { //画像表示の終わり
			if(pict[P_WAIT] != 0)
				//releaseflag = true;
				WWAeval_releaseNext = true;
			
			if(pict[P_PUT] != -1) {
				short[][] putTarget = pict[P_PUT] >= PARTS_MAX ? Map : Object;
				if(pict[P_PUTX] == -1) pict[P_PUTX] = PlayerX;
				if(pict[P_PUTY] == -1) pict[P_PUTY] = PlayerY;
				putTarget[(int)pict[P_PUTY]][(int)pict[P_PUTX]] = (short)(int)(pict[P_PUT] % PARTS_MAX);
			}
			
			if(pict[P_ENDSOUND] != 0)
				soundPlay(pict[P_ENDSOUND]);
			
			Valiable[i] = pict[P_CON_LIMIT] != 0 && pict[P_CON_LIMIT] <= pict[P_CON_COUNT]
				? 0
				: (int)pict[P_CONNECT];
			
			if(Valiable[i] % PARTS_MAX != 0) {
				pict[P_DEF] = 0;
				pict[P_CONNECTING] = 1;
				//setPict(i - WWAeval_pictIndex, i);
			}
			else {
				Valiable[i + WWAeval_pictLength] = 0;
				deletePict(i - WWAeval_pictIndex);
			}
				
			if(!pictList[PA_CREATE].isEmpty()) {
				for(int j = 0; j < pictList[PA_CREATE].size() - 1; j += 3) {
					int cIndex = ((Integer)pictList[PA_CREATE].get(j)).intValue();
					int cNo = ((Integer)pictList[PA_CREATE].get(j+1)).intValue();
					int cParam = j+2 < pictList[PA_CREATE].size()
							? ((Integer)pictList[PA_CREATE].get(j+2)).intValue()
							: (int)pict[P_PARAM];
					
					Valiable[cIndex + WWAeval_pictIndex] = cNo;
					Valiable[cIndex + WWAeval_pictIndex + WWAeval_pictLength] = cParam;
					
					deletePict(cIndex);
					//setPict((int)pict[P_CREATE_INDEX], cIndex + WWAeval_pictIndex);
				}
			}
			
		}
		else { //まだ画像表示が終わってないとき
			if(pict[P_WAIT] != 0 && pict[P_COUNT] <= pict[P_WAIT])
				waitflag = true;
			else if(pict[P_COUNT] == pict[P_WAIT] + 1)
				releaseflag = true;
		}
	}
	if(releaseflag) bStopInput = false;
	if(waitflag) bStopInput = true;
	
}

//画像描画のさらに下請け
void drawSub(Graphics2D target, AffineTransform defAf, double[] pict, ArrayList[] pictList,
		int imgNo, int x, int y, int dispX, int dispY, int widthPCrop, int heightPCrop,
		String drawMes, int drawMesX, int drawMesY, int fontSize) {
	
	Color bgColor = (Color)pictList[PA_COLORS].get(0);
	
	if(pict[P_ROTATE] != 0) {
		AffineTransform rot = (AffineTransform)defAf.clone();
		rot.rotate(Math.toRadians(pict[P_ROTATE]),
			dispX + pict[P_WIDTH] / 2, dispY + pict[P_HEIGHT] / 2);
		target.setTransform(rot);
	}
	
	if(pict[P_OUTLINE] != 0 && pict[P_FILL] == 0) {
		if(x == 0 || x + 1 == pict[P_REPEATX]) {
		
			if(y == 0 || y + 1 == pict[P_REPEATY]) {
				target.drawImage(ImgCrop[(int)pict[P_OUTLINE] + (x == 0 ? 0 : 2) + (y == 0 ? 0 : 20)],
					dispX + widthPCrop * (x == 0 ? -1 : 1), dispY + heightPCrop * (y == 0 ? -1 : 1),
					widthPCrop, heightPCrop, bgColor, null);
			}
			for(int cropY = 0; cropY < pict[P_COMBINEY]; cropY++) {
				target.drawImage(ImgCrop[(int)pict[P_OUTLINE] + (x == 0 ? 10 : 12)],
					dispX + widthPCrop * (x == 0 ? -1 : 1), dispY + heightPCrop * cropY,
					widthPCrop, heightPCrop, bgColor, null);
			}
		}
		if(y == 0 || y + 1 == pict[P_REPEATY]) {
			for(int cropX = 0; cropX < pict[P_COMBINEX]; cropX++) {
				target.drawImage(ImgCrop[(int)pict[P_OUTLINE] + (y == 0 ? 1 : 21)],
					dispX + widthPCrop * cropX, dispY + heightPCrop * (y == 0 ? -1 : 1),
					widthPCrop, heightPCrop, bgColor, null);
			}
		}
	}
	for(int cropY = 0; cropY < pict[P_COMBINEY]; cropY++) {
		for(int cropX = 0; cropX < pict[P_COMBINEX]; cropX++) {
			
			target.drawImage(ImgCrop[imgNo + cropX + cropY * 10],
				dispX + widthPCrop * cropX, dispY + heightPCrop * cropY,
				widthPCrop, heightPCrop, bgColor, null);
		}
	}
	if(drawMes != null)
		drawStringMulti(target, drawMes,
			dispX + drawMesX, dispY + drawMesY + fontSize, fontSize);
	
	if(pict[P_ROTATE] != 0) {
		target.setTransform(defAf);
	}
}

//画像をセットする
void setPict(int index, int varIndex) {
	int pictObjNo = Valiable[varIndex] % PARTS_MAX;
	int variableParam = Valiable[varIndex + WWAeval_pictLength];
	int pictMesNo = AtrObject[pictObjNo][ATR_STRING];
	double[] pict = WWAeval_atrPict[index];
	ArrayList[] pictList = WWAeval_atrPictList[index];
	double[] oldPict = (double[])pict.clone();
	
	boolean connecting = oldPict[P_CONNECTING] == 1;
	
	if(!connecting)
		Arrays.fill(oldPict, 0); //必要なさげだが
	
	deletePict(index);
	
	Arrays.fill(pict, 0);
	for(int i = 0; i < pictList.length; i++)
		pictList[i].clear();
	
	varMap.put("P_Param", doubleValueOfInt(variableParam));
	varMap.put("P_No", doubleValueOfInt(index));
	varMap.put("P_ConCount", doubleValueOfInt(connecting ? oldPict[P_CON_COUNT] + 1 : 0));
	String mes = GlobalMessage[pictMesNo];
	mes = parseWWAeval(mes, pictObjNo, false); //ここでの変数の変更はダメ
	if(mes == null) return;
	String[] lines = splitAndTrim(mes, '\n');
	
	//初期値
	pict[P_DEF] = 1;
	pictList[PA_CROPS].add(intValueOf(AtrObject[pictObjNo][ATR_CROP1]));
	if(AtrObject[pictObjNo][ATR_CROP2] != 0)
		pictList[PA_CROPS].add(intValueOf(AtrObject[pictObjNo][ATR_CROP2]));
	pictList[PA_COLORS].add(null);
	pict[P_WIDTH] = ImgCrop[AtrObject[pictObjNo][ATR_CROP1]].getWidth(null);
	pict[P_HEIGHT] = ImgCrop[AtrObject[pictObjNo][ATR_CROP1]].getHeight(null);
	pict[P_ANIME_DELAY] = 25;
	pict[P_INITSOUND] = AtrObject[pictObjNo][ATR_SOUND];
	pict[P_REPEATX] = pict[P_REPEATY] = 1;
	pict[P_COMBINEX] = pict[P_COMBINEY] = 1;
	pict[P_ALPHA] = 1;
	pict[P_PUT] = -1;
	
	pict[P_PARAM] = variableParam;
	pict[P_CON_COUNT] = connecting ? oldPict[P_CON_COUNT] + 1 : 0;
	
	boolean parseStarted = false;
	
	for(int i = 0; i < lines.length; i++) {
	
		if(!parseStarted) {
			if(lines[i].startsWith("$picture") || lines[i].startsWith("$pict_define")) {
				parseStarted = true;
			}
			else {
				continue;
			}
		}
		
		int equalPos = lines[i].indexOf('=');
		if(equalPos == -1) continue;
		
		String varName = lines[i].substring(0, equalPos).toLowerCase();
		
		String varValue = lines[i].substring(equalPos+1);
		String[] param = splitAndTrim(varValue, ',');
		
		if(varName.equals("disp")) {
			pict[P_DISPX] = d(param[0]);
			pict[P_DISPY] = (param.length >= 2) ? d(param[1]) : pict[P_DISPX];
		}
		else if(varName.equals("img")) {
			pictList[PA_CROPS].clear();
			for(int j = 0; j < param.length - 1; j += 2) {
				pictList[PA_CROPS].add(intValueOf(i(param[j]) + i(param[j + 1]) * 10));
			}
		}
		else if(varName.equals("combine")) {
			pict[P_COMBINEX] = i(param[0]);
			pict[P_COMBINEY] = (param.length >= 2) ? i(param[1]) : pict[P_COMBINEX];
		}
		else if(varName.equals("animedelay")) {
			pict[P_ANIME_DELAY] = d(param[0]);
		}
		else if(varName.equals("center")) {
			pict[P_CENTER] = i(param[0]);
		}
		else if(varName.equals("alpha")) {
			pict[P_ALPHA] = d(param[0]);
		}
		else if(varName.equals("alphaspeed")) {
			pict[P_SPEEDA] = d(param[0]);
		}
		else if(varName.equals("rotate")) {
			pict[P_ROTATE] = d(param[0]);
		}
		else if(varName.equals("rotatespeed")) {
			pict[P_SPEEDR] = d(param[0]);
		}
		else if(varName.equals("speed")) {
			pict[P_SPEEDX] = d(param[0]);
			pict[P_SPEEDY] = (param.length >= 2) ? d(param[1]) : pict[P_SPEEDX];
		}
		else if(varName.equals("accel")) {
			pict[P_ACCELX] = d(param[0]);
			pict[P_ACCELY] = (param.length >= 2) ? d(param[1]) : pict[P_ACCELX];
		}
		else if(varName.equals("size")) {
			pict[P_WIDTH] = d(param[0]);
			pict[P_HEIGHT] = (param.length >= 2) ? d(param[1]) : pict[P_WIDTH];
		}
		else if(varName.equals("sizespeed")) {
			pict[P_SPEEDW] = d(param[0]);
			pict[P_SPEEDH] = (param.length >= 2) ? d(param[1]) : pict[P_SPEEDW];
		}
		else if(varName.equals("sizeaccel")) {
			pict[P_ACCELW] = d(param[0]);
			pict[P_ACCELH] = (param.length >= 2) ? d(param[1]) : pict[P_ACCELW];
		}
		else if(varName.equals("disptime")) {
			pict[P_BEGIN] = i(param[0]);
			pict[P_HIDE] = (param.length >= 2) ? i(param[1]) : 0;
		}
		else if(varName.equals("movetime")) {
			pict[P_MOVE_START] = i(param[0]);
			pict[P_MOVE_STOP] = (param.length >= 2) ? i(param[1]) : 0;
		}
		else if(varName.equals("end")) {
			pict[P_END] = i(param[0]);
		}
		else if(varName.equals("repeat")) {
			pict[P_REPEATX] = i(param[0]);
			pict[P_REPEATY] = (param.length >= 2) ? i(param[1]) : pict[P_REPEATX];
		}
		else if(varName.equals("interval")) {
			pict[P_INTERVALX] = d(param[0]);
			pict[P_INTERVALY] = (param.length >= 2) ? d(param[1]) : pict[P_INTERVALX];
		}
		else if(varName.equals("shift")) {
			pict[P_SHIFTX] = d(param[0]);
			pict[P_SHIFTY] = (param.length >= 2) ? d(param[1]) : pict[P_SHIFTX];
		}
		else if(varName.equals("fill")) {
			pict[P_FILL] = d(param[0]);
		}
		else if(varName.equals("outline")) {
			pict[P_OUTLINE] = i(param[0]) + i(param[1]) * 10;
		}
		else if(varName.equals("random")) {
			pict[P_RANDOMX] = d(param[0]);
			pict[P_RANDOMY] = (param.length >= 2) ? d(param[1]) : pict[P_RANDOMX];
			if(oldPict[P_CON_TYPE] == 1)
				pict[P_RANDOM_SEED] = oldPict[P_RANDOM_SEED];
			else
				pict[P_RANDOM_SEED] = Math.random();
		}
		else if(varName.equals("circle")) {
			for(int j = 0; j < param.length;) {
				//0:X振幅　1:Y振幅　2:角速度　3:初期角度　...
				pictList[PA_CIRCLES].add(new Double(d(param[j++])));
				pictList[PA_CIRCLES].add(new Double(d(j < param.length ? param[j++] : param[j - 1])));
				pictList[PA_CIRCLES].add(new Double(j < param.length ? d(param[j++]) : 1));
				pictList[PA_CIRCLES].add(new Double(j < param.length ? d(param[j++]) : 0));
			}
		}
		else if(varName.equals("polar")) {
			pict[P_POLAR] = d(param[0]);
			if(param.length >= 2) {
				pict[P_ORIGINX] = d(param[1]);
				pict[P_ORIGINY] = (param.length >= 3) ? d(param[2]) : pict[P_ORIGINX];
				pict[P_POLAR_ROT] = (param.length >= 4 && d(param[3]) != 0) ? 1 : 0;
			}
		}
		else if(varName.equals("string")) {
			//0:X　1:Y　2:文字列1　3:文字列2　...
			//スピード重視で作ったparamを作り直す
			ArrayList paramA = splitConsClip(varValue, ",", "(?<!\\\\)\"(?:.*[^\\\\]+|)\"");
			
			pictList[PA_STRING].add(intValueOf(i((String)paramA.get(0))));
			pictList[PA_STRING].add(intValueOf(i((String)paramA.get(1))));
			
			for(int j = 2; j < paramA.size(); j++) {
				String str = ((String)paramA.get(j)).trim();
				if(str.startsWith("\"") && str.endsWith("\""))
					str = str.substring(1, str.length() - 1);
				
				int length = str.length();
				StringBuffer sb = new StringBuffer(length);
				for(int k = 0; k < length; k++) {
					char c = str.charAt(k);
					if(c != '\\' || k + 1 >= length) {
						sb.append(c);
					}
					else {
						c = str.charAt(++k);
						if(c == 'n')
							sb.append('\n');
						else
							sb.append(c);
					}
				}
				pictList[PA_STRING].add(sb.toString());
			}
		}
		else if(varName.equals("font")) {
			//フォント,スタイル,大きさ,アンチエイリアス
			//0:フォント　1:アンチエイリアス
			pictList[PA_FONT].clear();
			String styleStr = 1 < param.length ? param[1] : "";
			int style = (styleStr.indexOf("太字") != -1 ? Font.BOLD : Font.PLAIN)
						| (styleStr.indexOf("斜体") != -1 ? Font.ITALIC : Font.PLAIN);
			
			pictList[PA_FONT].add(new Font(param[0].trim().length() > 0 ? param[0].trim() : "Monospaced",
								style, 2 < param.length ? i(param[2]) : GrMap.getFont().getSize()));
			pictList[PA_FONT].add(Boolean.valueOf(3 < param.length && d(param[3]) != 0));
		}
		else if(varName.equals("color")) {//赤,緑,青,透明度
			int red = Math.max(0, Math.min(255, i(param[0])));
			int green = 1 < param.length ? Math.max(0, Math.min(255, i(param[1]))) : red;
			int blue = 2 < param.length ? Math.max(0, Math.min(255, i(param[2]))) : green;
			int alpha = 3 < param.length ? Math.max(0, Math.min(255, i(param[3]))) : 255;
			Color color = new Color(red, green, blue, alpha);
			if(pictList[PA_COLORS].size() <= 1)
				pictList[PA_COLORS].add(color);
			else
				pictList[PA_COLORS].set(1, color);
		}
		else if(varName.equals("bgcolor")) {//赤,緑,青,透明度
			int red = Math.max(0, Math.min(255, i(param[0])));
			int green = 1 < param.length ? Math.max(0, Math.min(255, i(param[1]))) : red;
			int blue = 2 < param.length ? Math.max(0, Math.min(255, i(param[2]))) : green;
			int alpha = 3 < param.length ? Math.max(0, Math.min(255, i(param[3]))) : 255;
			pictList[PA_COLORS].set(0, new Color(red, green, blue, alpha));
		}
		else if(varName.equals("onstatus")) {
			pict[P_ONSTATUS] = d(param[0]) != 0 ? 1 : 0;
		}
		else if(varName.equals("hasparts")) {
			pict[P_HASPARTS] = relInt(param[0], pictObjNo);
			pict[P_HASPARTS] += (param.length >= 2 && i(param[0]) == 1) ? PARTS_MAX : 0;
		}
		else if(varName.equals("onparts")) {
			for(int j = 0; j < param.length; j += 2) {
				int partsNo = relInt(param[j], pictObjNo);
				partsNo += (param.length >= j + 2 && i(param[j + 1]) == 1) ? PARTS_MAX : 0;
				pictList[PA_ONPARTS].add(intValueOf(partsNo));
			}
		}
		else if(varName.equals("sound")) {
			pict[P_INITSOUND] = i(param[0]);
			pict[P_ENDSOUND] = (param.length >= 2) ? i(param[1]) : 0;
		}
		else if(varName.equals("wait")) {
			pict[P_WAIT] = i(param[0]);
		}
		else if(varName.equals("put")) {
			pict[P_PUT] = i(param[0]);
			pict[P_PUT] += (param.length >= 2 && i(param[1]) == 1) ? PARTS_MAX : 0;
			pict[P_PUTX] = param.length >= 3 ? i(param[2]) : -1;
			pict[P_PUTY] = param.length >= 4 ? i(param[3]) : -1;
		}
		else if(varName.equals("connect")) {
			pict[P_CONNECT] = relInt(param[0], pictObjNo);
			
			if(oldPict[P_CON_LIMIT] > 0)
				pict[P_CON_LIMIT] = Math.floor(oldPict[P_CON_LIMIT]);
			else if(param.length >= 2)
				pict[P_CON_LIMIT] = i(param[1]);
			
			pict[P_CON_TYPE] = (param.length >= 3) ? i(param[2]) : 0;
		}
		else if(varName.equals("create")) {
			for(int j = 0; j < param.length - 1;) {
				pictList[PA_CREATE].add(intValueOf(i(param[j++])));
				pictList[PA_CREATE].add(intValueOf(relInt(param[j++], pictObjNo)));
				if(j < param.length) pictList[PA_CREATE].add(intValueOf(Math.abs(i(param[j++]))));
			}
		}
	}
	
	//調整
	pict[P_WIDTH] *= pict[P_COMBINEX];
	pict[P_HEIGHT] *= pict[P_COMBINEY];
	if(pict[P_WAIT] != 0) bStopInput = true;
	
	//for (int j = 0; j < pict.length; j++) System.out.println(j+":"+pict[j]);
}


//画像データを消す
void deletePict(int index) {
	Arrays.fill(WWAeval_atrPict[index], 0);
	for(int i = 0; i < WWAeval_atrPictList[index].length; i++)
		WWAeval_atrPictList[index][i].clear();
}

//複数行の文字列を書く
void drawStringMulti(Graphics g, String str, int x, int y, int lineSize) {
	int length = str.length();
	StringBuffer sb = new StringBuffer();
	for(int i = 0, l = 0; l < length; i++, l++) {
		for(; l < length; l++) {
			char c = str.charAt(l);
			if(c == '\n') break;
			sb.append(c);
		}
		g.drawString(sb.toString(), x, y + lineSize * i);
		sb.setLength(0);
	}
}


//WWA開始時（具体的には初めて画面をクリックしたとき）だけに呼ばれるメソッド
//画像読み込みの状態によってはここで不自然に固まる
void WWAeval_init() {

	if(Valiable == null || Object == null || AtrObject == null || ImgCrop == null || GlobalMessage == null)
		return;
	
	System.out.println("<WWAeval_init begin>");
	
	//初期値の定義
	ItemBoxDouble = new double[ItemBox.length];
	ValiableDouble = new double[Valiable.length];
	MapDouble = new double[Map.length][Map[0].length];
	ObjectDouble = new double[Object.length][Object[0].length];
	AtrMapDouble = new double[AtrMap.length][AtrMap[0].length];
	AtrObjectDouble = new double[AtrObject.length][AtrObject[0].length];
	
	intCache = new Integer[128];
	for(int i = 0; i < intCache.length; i++)
		intCache[i] = new Integer(i);
	doubleCache = new Double[128];
	for(int i = 0; i < doubleCache.length; i++)
		doubleCache[i] = new Double(i);
	
	varMap = new HashMap();
	constMap = new HashMap();
	WWAeval_objectMap = new HashMap();
	WWAeval_mapMap = new HashMap();
	WWAeval_atrPict = new double[WWAeval_pictLength][P_renban];
	WWAeval_atrPictList = new ArrayList[WWAeval_pictLength][PA_renban];
	
	for(int i = 0; i < WWAeval_atrPictList.length; i++)
		for(int j = 0; j < WWAeval_atrPictList[0].length; j++)
			WWAeval_atrPictList[i][j] = new ArrayList();
	
	//$nameでの名前と、$partseffectの画像を設定する
	WWAeval_objName = new String[AtrObject.length];
	WWAeval_mapName = new String[AtrMap.length];
	WWAeval_objEffect = new int[AtrObject.length];
	WWAeval_mapEffect = new int[AtrMap.length];
	
	for (int i = 0; i < 2; i++) {
		String[] nameList = i == 0 ? WWAeval_objName : WWAeval_mapName;
		int[] effList = i == 0 ? WWAeval_objEffect : WWAeval_mapEffect;
		int[][] atr = i == 0 ? AtrObject : AtrMap;
		for (int j = 0; j < atr.length; j++) {
			nameList[j] = "";
			int mesNo = atr[j][ATR_STRING];
			if(mesNo == 0) continue;
			Matcher name = Pattern.compile("^\\$name=(.+)$", Pattern.MULTILINE).matcher(GlobalMessage[mesNo]);
			if(name.find()) {
				nameList[j] = name.group(1).trim();
			}
			name = Pattern.compile("^\\$partseffect=(\\d+),(\\d+)$", Pattern.MULTILINE).matcher(GlobalMessage[mesNo]);
			if(name.find()) {
				effList[j] = i(name.group(2).concat(name.group(1)));
			}
		}
	}
	
	//WWAevalAppletとグローバルに通信し、画像を置換する
	String errMes = null;
	try {
		WWAevalApplet WeA = WWAevalApplet.getInstance();
		Map extraImgs = WeA.getImage();
		for(int i = 0; extraImgs == null; i++) {
			if(i > 200) //約1分経過したら
				throw new RuntimeException("タイムアウト");
			extraImgs = WeA.getImage();
			try{
				Thread.sleep(300);
			}catch (InterruptedException e){
			}
		}
		for (Iterator i = extraImgs.entrySet().iterator(); i.hasNext();) {
			Entry entry = (Entry)i.next();
			int index = ((Integer)entry.getKey()).intValue();
			if(index < 0 || ImgCrop.length <= index)
				System.out.println("画像のインデックスが範囲外："+index);
			else
				ImgCrop[index] = (Image)entry.getValue();
		}
		
		errMes = WeA.getErrMes();
	}
	catch(NullPointerException e) {
		errMes = "ブラウザからWWAevalAppletが呼び出されていません。";
	}
	catch(Exception e) {
		e.printStackTrace();
		errMes = "WWAevalApplet操作において次のエラーが発生しました：\n\n" + e.toString();
	}
	if(errMes != null) {
		WWAeval_imgLoadHasErr = true;
		
		WWAeval_initErrMes = "WWAevalの画像置換処理で\nエラーが発生しました。\n\n" + 
			"画面クリックでエラー内容を表示します。\n<p>\n" +
			errMes + "<p>\n" +
			"このままゲームを続けることもできますが、一部の画像が正しく表示されません。\n\n" +
			"特に考えられる原因がないのにこのエラーが出続けるという方は製作者まで報告してください。";
	}
	
	//定数or参照渡し
	constMap.put("ATR_CROP1", doubleValueOfInt(ATR_CROP1));
	constMap.put("ATR_CROP2", doubleValueOfInt(ATR_CROP2));
	constMap.put("ATR_TYPE", doubleValueOfInt(ATR_TYPE));
	constMap.put("ATR_MODE", doubleValueOfInt(ATR_MODE));
	constMap.put("ATR_STRING", doubleValueOfInt(ATR_STRING));
	constMap.put("ATR_ENERGY", doubleValueOfInt(ATR_ENERGY));
	constMap.put("ATR_STRENGTH", doubleValueOfInt(ATR_STRENGTH));
	constMap.put("ATR_DEFENCE", doubleValueOfInt(ATR_DEFENCE));
	constMap.put("ATR_GOLD", doubleValueOfInt(ATR_GOLD));
	constMap.put("ATR_ITEM", doubleValueOfInt(ATR_ITEM));
	constMap.put("ATR_NUMBER", doubleValueOfInt(ATR_NUMBER));
	constMap.put("ATR_JUMP_X", doubleValueOfInt(ATR_JUMP_X));
	constMap.put("ATR_JUMP_Y", doubleValueOfInt(ATR_JUMP_Y));
	constMap.put("ATR_SOUND", doubleValueOfInt(ATR_SOUND));
	constMap.put("ATR_MOVE", doubleValueOfInt(ATR_MOVE));
	
	constMap.put("MAP_STREET", doubleValueOfInt(MAP_STREET));
	constMap.put("MAP_WALL", doubleValueOfInt(MAP_WALL));
	constMap.put("MAP_LOCALGATE", doubleValueOfInt(MAP_LOCALGATE));
	constMap.put("MAP_URLGATE", doubleValueOfInt(MAP_URLGATE));
	constMap.put("OBJECT_NORMAL", doubleValueOfInt(OBJECT_NORMAL));
	constMap.put("OBJECT_MESSAGE", doubleValueOfInt(OBJECT_MESSAGE));
	constMap.put("OBJECT_URLGATE", doubleValueOfInt(OBJECT_URLGATE));
	constMap.put("OBJECT_STATUS", doubleValueOfInt(OBJECT_STATUS));
	constMap.put("OBJECT_ITEM", doubleValueOfInt(OBJECT_ITEM));
	constMap.put("OBJECT_DOOR", doubleValueOfInt(OBJECT_DOOR));
	constMap.put("OBJECT_MONSTER", doubleValueOfInt(OBJECT_MONSTER));
	constMap.put("OBJECT_SCORE", doubleValueOfInt(OBJECT_SCORE));
	constMap.put("OBJECT_SELL", doubleValueOfInt(OBJECT_SELL));
	constMap.put("OBJECT_BUY", doubleValueOfInt(OBJECT_BUY));
	constMap.put("OBJECT_RANDOM", doubleValueOfInt(OBJECT_RANDOM));
	constMap.put("OBJECT_SELECT", doubleValueOfInt(OBJECT_SELECT));
	constMap.put("OBJECT_LOCALGATE", doubleValueOfInt(OBJECT_LOCALGATE));
	
	constMap.put("F_X", doubleValueOfInt(0));
	constMap.put("F_Y", doubleValueOfInt(1));
	
	//定数扱いしていいのか微妙だけどまあいいか
	constMap.put("MapWidth", doubleValueOfInt(MapWidth));
	constMap.put("MapPartsMax", doubleValueOfInt(MapPartsMax));
	constMap.put("ObjectPartsMax", doubleValueOfInt(ObjectPartsMax));
	constMap.put("MesNumberMax", doubleValueOfInt(MesNumberMax));
	
	constMap.put("ItemBox", ItemBoxDouble);
	constMap.put("Valiable", ValiableDouble);
	constMap.put("Map", MapDouble);
	constMap.put("Object", ObjectDouble);
	constMap.put("AtrMap", AtrMapDouble);
	constMap.put("AtrObject", AtrObjectDouble);
	
	constMap.put("Gr", Gr);
	constMap.put("GrMap", GrMap);
	constMap.put("GrStatus", GrStatus);
	constMap.put("ImgCrop", ImgCrop);
	
	constMap.put("GlobalMessage", GlobalMessage);
	constMap.put("SystemMessage", SystemMessage);
			
	constMap.put("ObjectName", WWAeval_objName);
	constMap.put("MapName", WWAeval_mapName);
	constMap.put("ImgLoadError", doubleValueOfInt(WWAeval_imgLoadHasErr ? 1 : 0));
	
	//Temp定義
	constMap.put("Temp", new double[100]);
	constMap.put("TempStr", new String[100]);
	constMap.put("TempList", new ArrayList());
	constMap.put("TempMap", new HashMap());
	
	WWAeval_initialized = true;
	System.out.println("<WWAeval_init end>");
}


//WWAextendSubの呼び出し毎に呼ばれるメソッド
void WWAeval_start() {

	//今いる画面の左上の座標を習得するため禁じ手をつかいます！！！
	int mapLocationXTemp = mapLocationX;
	int mapLocationYTemp = mapLocationY;
	try {
		mapLocationX = WWAextend.dW;
		mapLocationY = WWAextend.dX;
	}
	catch(NoSuchFieldError e) {
		mapLocationX = (int)(Math.floor(PlayerX/10));
		mapLocationY = (int)(Math.floor(PlayerY/10));
	}
	
	//今のマップにあるパーツ番号をマップの相対座標で習得
	if(Mode == 1 || mapScrollCount != -1 || mapLocationX != mapLocationXTemp || mapLocationY != mapLocationYTemp) {
		if(Mode != 1 && mapScrollCount == -1) {
			mapScrollCount = 10;
			//PlayerDirectと同じ感じで
			if(mapLocationYTemp - 1 == mapLocationY && PlayerY % 10 == 0)
				mapScrollDir = 8;
			else if(mapLocationXTemp + 1 == mapLocationX && PlayerX % 10 == 0)
				mapScrollDir = 6;
			else if(mapLocationYTemp + 1 == mapLocationY && PlayerY % 10 == 0)
				mapScrollDir = 2;
			else if(mapLocationXTemp - 1 == mapLocationX && PlayerX % 10 == 0)
				mapScrollDir = 4;
			else
				mapScrollCount = -1;
		}
		
		
		WWAeval_objectMap.clear();
		WWAeval_mapMap.clear();
		
		int top = mapLocationY * 10;
		int left = mapLocationX * 10;
		if(mapScrollCount != -1) {
			top += (int)DirSwitch(1, 0, -1, 0, mapScrollDir) * mapScrollCount;
			left += (int)DirSwitch(0, -1, 0, 1, mapScrollDir) * mapScrollCount;
			mapScrollCount--;
		}
		for (int y = 0; y < 11; y++) {
			for (int x = 0; x < 11; x++) {
				for (int i = 0; i < 2; i++) {
					Integer no = intValueOf(i == 0 ? Object[y + top][x + left]
					                                    : Map[y + top][x + left]);
					LinkedList list;
					if(no.intValue() != 0) {
						Map map = i == 0 ? WWAeval_objectMap : WWAeval_mapMap;
						list = (LinkedList)map.get(no);
						if(list == null) {
							list = new LinkedList();
							map.put(no, list);
						}
						list.add(intValueOf(x));
						list.add(intValueOf(y));
					}
				}
			}
		}
	}
	
	if(Mode == 3) {
		//GlobalMessageが別インスタンスになってたらゲーム開始時かリスタート時…だと思いたい
		if(GlobalMessage != GlobalMessageTemp) {
			GlobalMessageTemp = GlobalMessage;
			
			Arrays.fill(Valiable, 0);
			ImgMainFrame = 10;
			ImgItemFrame = 21;
			ImgEnergy = 23;
			ImgStrength=24;
			ImgDefence = 25;
			ImgGold = 26;
			ImgBom = 33;
			ImgStatusFrame = 34;
	
			//UniformRandomで使うシード値を時間から生成
			int seed = (int)(long)((System.currentTimeMillis() / 10) % 1000000) + 5000;
			Valiable[WWAeval_seedIndex] = seed % 65000;
			Valiable[WWAeval_seedIndex + 1] = seed / 65000;
		}
		
		//シード値か経過時間が違えば、データロードが行われたことになる
		//奇跡が起こると検出を免れることもできるようだが…
		WWAeval_quickLoaded = 
			WWAeval_randomSeed != Valiable[WWAeval_seedIndex] + Valiable[WWAeval_seedIndex + 1] * 65000 ||
			WWAeval_timer != Valiable[WWAeval_timerIndex] + Valiable[WWAeval_timerIndex + 1] * 65000;
		
		if(WWAeval_quickLoaded) {
			WWAeval_randomSeed = Valiable[WWAeval_seedIndex] + Valiable[WWAeval_seedIndex + 1] * 65000;
			WWAeval_timer = Valiable[WWAeval_timerIndex] + Valiable[WWAeval_timerIndex + 1] * 65000;
			
			//pictデータを全消しし、リメイクの準備をする
			for (int i = 0; i < WWAeval_pictLength; i++)
				deletePict(i);
			
			//復帰用パスの仕様に合わせて負になりえる数値を復元
			int d = 1 << 16;
			if(EnergyMax > 65000) EnergyMax -= d;
			if(Strength > 65000) Strength -= d;
			if(Defence > 65000) Defence -= d;
			if(Gold > 65000) Gold -= d;
			for(int i = 0; i < Valiable.length; i++)
				if(Valiable[i] > 65000) Valiable[i] -= d;
		}
		
		//タイマーカウント
		WWAeval_timer++;
		Valiable[WWAeval_timerIndex]++;
		if(Valiable[WWAeval_timerIndex] >= 65000) {
			Valiable[WWAeval_timerIndex] -= 65000;
			Valiable[WWAeval_timerIndex + 1]++;
		}
	}
}

//WWAeval処理終了時に毎回呼ばれるもの
void WWAeval_final() {
	WWAeval_prevProcessTime = System.currentTimeMillis();
}

//WWAが終了するときにWWAevalApplet経由で呼ばれるもの
public void stop() {
	instance = null;
}

public static WWAextendSub getInstance() {
	//if(authedThread == Thread.currentThread())
		return instance;
	/*else {
		System.out.println("干渉を検出");
		return null;
	}*/
}


} //WWAextendSubクラスの終わり


////////////////////////////////////////////////////////////////////////////////////////////////
// WWAevalFunc
// WWAevalで使用する関数定義クラスです。
////////////////////////////////////////////////////////////////////////////////////////////////
class WWAevalFunc extends InvokeFunction {
	
	public Object evalObject(Object object, String name, Object[] args)
			throws Throwable {
		Class[] types = new Class[args.length];
		for (int i = 0; i < types.length; i++) {
			//数値はプリミティブなdoubleのみを想定
			types[i] = args[i] instanceof Number ? Double.TYPE : args[i].getClass();
		}

		//objectがなければWWA関数だとして処理する
		Method m;
		if(object == null) {
			try {
				object = WWAextendSub.getInstance();
				m = object.getClass().getMethod(name, types);
			}
			//WWAextendSubになければMathにあたる
			catch (NoSuchMethodException e) {
				object = null;
				m = Math.class.getMethod(name, types);
			}
		}
		else {
			Class c = object.getClass();
			//面倒なのでString#equals()だけ特別視
			if(c.getName().equals("java.lang.String") && name.equals("equals"))
				types[0] = Object.class;
			try {
				m = c.getMethod(name, types);
			}
			catch (NoSuchMethodException e) {
				//doubleがダメならintにすればいい…！
				for (int i = 0; i < types.length; i++) {
					if(types[i] == Double.TYPE) {
						types[i] = Integer.TYPE;
						args[i] = new Integer(((Number)args[i]).intValue());
					}
				}
				m = c.getMethod(name, types);
			}
		}
		Object ret = m.invoke(object, args);
		if(ret instanceof Number) {
			return new Double(((Number) ret).doubleValue());
		}
		else {
			return ret;
		}
	}
};



////////////////////////////////////////////////////////////////////////////////////////////////
// プログラム返信用
// そのままコンパイルするとＷＷＡ本体の「WWAextend.class」が
//同時に上書きされて中身のないファイルになります（ファイルサイズを確認）ので、
// 「WWAextend.class」はファイルのプロパティで上書き禁止にしてしまってください。
//class WWA {
class WWAextend {
static int dW;
static int dX;
public static void ReturnExtend(
		int Mode, int PlayerX, int PlayerY, char PlayerDirect,
		int Energy, int EnergyMax, int Strength, int Defence, int Gold, int Step, int TimerCount,
		int GameoverX, int GameoverY, int ImgChara, int ImgButton, int bSaveStop, int bDefault, boolean bPaintAll, boolean bStopInput, int bAnmItem, int MusicNumber,
		int ImgEnergy, int ImgStrength, int ImgDefence, int ImgGold, int ImgBom, int ImgStatusFrame, int ImgItemFrame, int ImgMainFrame, int ImgClickItem,
		int ItemBox[], int Valiable[],
		short Map[][], short Object[][],
		String ReturnMessage, String CopyRight, boolean bReset, boolean bNoExec,
		int AtrMap[][], int AtrObject[][],
		String GlobalMessage[], String SystemMessage[]
){}
}


