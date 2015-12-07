#------------------以下、初期設定項目-------------------
#街の名前
$title='新進グダニスク帝国';

#管理者名（ここで設定した管理者名とパスワードで新規登録することで、家を無料で作成することができます）
$admin_name = 'ヒラリラー';

#管理者パスワード
$admin_pass = '821074';

#お知らせバー　byまつぷら 2008/03/25　さらに改造2009/02/07
$news_bar1 =<< "EOM";
<marquee bgcolor=black><font color=red size=4><B>【速報】</font><font color=white size=4>憲法改正の署名をただ今裁判所でやっております。お手数お掛けしますが最寄の裁判所にて署名をお願いします。</font></marquee>
EOM
$news_bar2 =<< "EOM";
<marquee bgcolor=black><font color=red size=4><B>【ＰＲ】</font><font color=white size=4>帝都A-10にて口ト４購入受付中！まったりと参加よろしくお願いします（´∀｀ ）　by＠ゆう</font></marquee>
EOM
$news_bar3 =<< "EOM";
<marquee bgcolor=black><font color=red size=4><B>【連絡】</font><font color=white size=4>諸事情により３月４日まで管理人があまり来られなくなります。何か問題がありましたら副管理人【レオ】【剣心】にお尋ねください。</font></marquee>
EOM
#プロキシー強化および自分以外が進入防止　town_maker.cgi　のホスト強化 koko2007/09/09
$host_kyuka = 'no';
#プロキシー強化　town_maker.cgi　
$host_kyuka_meker = 'no';
# アクセス制限許可型（半角スペースで区切る）#プロキシー強化および自分以外が進入防止　#管理者ホスト名1　#管理者ホスト名2も入れてください。
#  → 許可するホスト名又はIPアドレスを記述 優先度一番（アスタリスク可）これを使うと下記は無視されます。
#  → 記述例 abc.def.ne.jp 12.34.56.* 半角スペース区切り
$okhost = '';
#管理者ホスト名1　'abc.def.ne.jp'のような自分のアクセスポイントを入れる。自宅 '*.def.ne.jp'先頭のみ「*」数値変化を無視
$my_host1 = ''; #*.def.ne.jp
#管理者ホスト名2　　同上　会社などの出先 書かれていなければ普通通り。
$my_host2 = ''; #211.154.120.*
#メンテ中フラグ（通常は0。1でメンテ中となりゲームを中断させることができます）
$mente_flag = '0';

#メンテナンス時のメッセージ
$mente_message = 'ただいまメンテナンス中。。。5分くらいお待ちください';

#画像フォルダー（img）の指定。プログラムからの相対パス or http://～から始まる絶対パス　※最後の「/」は不要です。
$img_dir = './img';

#戻り先ホームページ
$homepage = 'http://w6.oroti.com/~hirarira/';

#管理者メールアドレス（問い合わせ用）
$master_ad = '';

#管理者リンク#koko2005/09/08
$master_kb = './bbs0/bbs0.cgi';

#トップ画面でのお知らせ内容（タグ可）
$osirase = <<"EOM";
<!-- ここから-->
<SCRIPT language="JavaScript">
<!--
time=new Date(2006,(7-1),9);
time=Math.ceil((time.getTime()-new Date().getTime())/86400000);
document.write('真・ニューヒラリラーシティ設置から、'+-(time)+'日経ちました。');
//-->
</SCRIPT><br>
<SCRIPT language="JavaScript">
<!--
time=new Date(2006,(9-1),19);
time=Math.ceil((time.getTime()-new Date().getTime())/86400000);
document.write('ヒラリラー合衆国になってから、'+-(time)+'日経ちました。');
//-->
</SCRIPT><br>
<SCRIPT language="JavaScript">
<!--
time=new Date(2007,(7-1),22);
time=Math.ceil((time.getTime()-new Date().getTime())/86400000);
document.write('新進ラリラー帝国になってから、'+-(time)+'日経ちました。');
//-->
</SCRIPT><br>
<SCRIPT language="JavaScript">
<!--
time=new Date(2007,(12-1),23);
time=Math.ceil((time.getTime()-new Date().getTime())/86400000);
document.write('新進グダニスク帝国設立から、'+-(time)+'日経ちました。');
//-->
</SCRIPT><br>
<br>
<INPUT type="button" value="ＴＯＷＮ総合外部掲示板、ＴＯＷＮに入れないバグ等はこちらで" onclick="parent.location='http://snow.advenbbs.net/bbs/hira.htm';" style="color:#FFFFFF;background:#0000CD;border:1px dotted #000000;"><BR>
<br>
<INPUT type="button" value="新進グダニスク帝国憲法、住民は必ず見たほうがいいです" onclick="parent.location='keppo.html';" style="color:#FFFFFF;background:#0000CD;border:1px dotted #000000;"><BR>
<br>
<INPUT type="button" value="新型新進グダニスク帝国公式チャット" onclick="parent.location='http://jasin.chatx.whocares.jp/';" style="color:#FFFFFF;background:#009900;border:1px dotted #000000;"><BR>
入室中ユーザー数:
<img alt="" width="24" height="12" src="http://jasin.chatx.whocares.jp/mcond/users.png" />  
ROMユーザー数:
<img alt="" width="24" height="12" src="http://jasin.chatx.whocares.jp/mcond/roms.png" /><br>
<STYLE TYPE="text/css"><!--BODY {background-image:url("m.gif");background-attachment:fixed;}--></STYLE>
<TEXTAREA cols="85" rows="5" style="background-color:テキストエリア内の背景色;color:テキストエリア内の文字色;">
このＴＯＷＮの更新履歴
3月19日　　最大登録人数200→250人へ
3月12日　　署名により憲法改正
3月11日　　壁紙を「Spring」に
3月9日　　 公式チャットを新型へ変更
2月6日　　 電光掲示板設置
2月3日　　 スタンプラリーのスタンプ撤去
1月下旬　　線路と駅をリアルなものに
12月22日　 壁紙を『Winter』に
12月21日　 年末大宴会の告知開始
12月18日　 スタンプラリーのスタンプ設置
11月4日　　壁紙を「Autumn」に
9月9日　　 仕事バグ解消＋α
9月9日　　 ヨゼック西専用掲示板追加
8月29日　　ガス村追加
7月29日　　同盟TOWN追加
7月29日　　ヨゼック東追加
7月22日　　最大登録人数175→200人へ
6月29日　　「Customer Floor」のテスト開始
5月23日　　クロム市追加
5月14日　　素材提供者追加
5月14日　　正式チャット設置
4月5日　　 ブレイブタウン消滅によりリンクより削除
2月29日　　ユーザー削除期間50日⇒120日へ
2月24日　　ＴＯＷＮ総合外部掲示板設置
2月24日　　バグ修正
2月19日　　新ポケモンTOWNとの同盟締結
2月2日　　 イベント100％終了
1月5日　　 ブレイブのバナーを下へ移動
1月5日　　 更新履歴をグダニスクに移動完了
11月30日　 恋愛システム復活
11月20日　 ブレイブタウンの広告バナー設置
11月10日　 憲法を大幅に改正
11月10日　 ヨゼックと帝都の背景を新しくしました。
11月3日　　銀行送金の限界を1兆円に設定
10月20日　 大寒村追加、ラヴァート復活
9月25日　　ラヴァートから難民キャンプへ
9月25日　　ゼオストの暴走によりラヴァートとヨゼックの半数が消滅
8月27日　　最大登録人数150→130人へ
8月27日　　パスワード強化
8月24日　　所有物の限度数を２５へ増加
8月24日　　アイテム100種類ほど追加
8月15日　　臨時選挙開幕
7月31日　　スロット第弐軍事島追加
7月24日　　カードゲーム２設置
7月23日　　自動販売機設置
7月23日　　新進ラリラー帝国憲法発布
7月22日　　新進ラリラー帝国誕生
7月22日　　ヒラリラー合衆国崩壊
7月21日　　リンク変更、初代副管理人にみなな氏に任命
5月27日　　アイテム４３種類いっきに追加！
4月21日　　最大登録人数150→175人へ
4月17日　　ＯＦＦ会のアレ追加
4月16日　　ａｓｌｎｄ作成、微調整
4月15日　　税金徴収
4月9日　　 アイテム投稿所作成
4月7日　　 ギフト作成所追加！
4月6日　　 温泉の回復時間を表示
4月3日　　 税金法改定
4月3日　　 最大登録人数130→150人へ
3月30日　　中央地区（南）に学校設立
3月30日　　地価下落
3月21日　　病気などのコンディション大幅追加、バグ修正
3月3日　　 クニナカ宇宙ステーションが宇宙の藻屑へ・・・・
1月24日　　やっぱ最大登録人数150→130人へ
1月24日　　中央地区（南）に首都庁追加
1月24日　　家の価格、内装費用下落
1月8日　　 最大登録人数120→150人へ
12月12日　 超インフレ発生
12月11日　 クニナカ宇宙ステーション「州へ昇格」
11月19日　 設定変更、画像追加
11月14日　 憲法発布、裁判所設置
11月11日　 挨拶ログ６→１５へ
11月7日　　ホワック州への航空便の運行開始
11月7日　　『宇宙州』準州に降格
10月28日   24LIFEへのリンク追加
10月28日　 k-i空間消滅
10月22日　 中央地区（南）に競馬場設立
10月22日　 所有物の限度数100→20へ
10月21日　 おさい銭の1000万盗むを100万盗むに変更
10月11日　 アイテム何個か追加
10月X日　　天空・・・・
9月27日　　さい銭欄変更
9月23日　　アイテム少し追加
9月22日　　最大最大登録人数100人→120人へ
9月19日　　窃盗法導入
9月19日　　真・ニューヒラリラーシティからヒラリラー合衆国
9月19日　　ＷＷＡネタ関連版追加
9月16日　　ユーザー削除期間を20日から40日へ変更
9月15日　　税金制度導入
8月24日　　海底消滅、森誕生
8月23日　　真・ニューヒラリラーシティ統括中央政府設立
8月21日　　ヒラリラー城崩壊
8月19日　　極寒村追加
8月18日　　板追加
8月18日　　新マップ「緑島」追加
8月18日　　アクセサリ追加
8月18日　　移動手段大変更
8月15日　　地下地区更新
8月14日　　病院、温泉画像変更
8月13日　　新アイテム、新職業募集板の注意事項追加
8月12日　　更新履歴追加</TEXTAREA>
</FORM>
<STYLE TYPE="text/css">
<!--
BODY {background-image:url("Autumn.gif");background-attachment:fixed;}
-->
</STYLE>
<font color=#666666>あなたもこの街の住民になってみませんか？　街では掲示板やゲームなどを通して色々な方と知り合う事ができます。さまざまな職業についたり、豪華な家を建てたり、どんな人生にするかはあなた次第！初めての方は下の「新規登録」より登録してください。<br></font>
<font color=#ff6600>※二重登録は一応許可してありますが、する場合は一言管理者にご報告ください。<br>
※登録後、名前の変更はできません。</font><br>
<hr>
画像、技術提供者<br>
<a href="http://www.propel.ne.jp/~yysky/gallery/" target="_blank"><img src="hakoniwa.gif" border="0"></a>  <a href="http://www.blue-moon.jp/" target="_blank"><img src="ban_s01.jpg" border="0"></a>  <a href="http://www2s.biglobe.ne.jp/~tatsuji/" target="_blank"><img src="tatujibr.gif" border="0"></a>
<hr>
<!-- ここまで-->
EOM

#作成する街の名前（一番左の街が最初に表示される街となります。例として４つを設定してありますが、１つだけでも、４つ以上でも構いません。※重要：ゲーム途中で街を追加する場合は、必ず最後に追加してください）
@town_hairetu = ("帝都ジューレスカ","臨海新都心ラヴァート","ヨゼック西","ゴベス湖","ミサイル軍事島","大寒村","眠らない都スベラガス","秘密都市ロンヴァエ","クロム市","ヨゼック東","ガス村");

#街の地価（上で指定した街の地価。左から順に上と対応させる。単位は万円）
@town_tika_hairetu = ("10000","1800","500","200","0","10","1600","25000","1000","1000","100");

#街の背景スタイル（上で指定した街の背景スタイル設定。左から順に上と対応させる。スタイルで画像指定可）
@page_back = ("background-image : url( img/Spring.png)","background-color:#ffffcc","background-image : url( img/yz.gif)","background-color:#00ffff","background-color:#336699","background-color:#00ccff","background-color:#000000","background-image : url( img/sora.gif)","background-color:#00ff00","background-color:#ffffcc","background-color:#669933");

#参加者が建てられる家の画像と価格（'画像名','価格'の形で設定してください。価格の単位は（万円）です。家の画像はimgフォルダに入れておく必要があります。）
%ie_hash=('house1.gif','100','house2.gif','100','house3.gif','100','house4.gif','300','house5.gif','300','house6.gif','300','house7.gif','500','house8.gif','500','house9.gif','500','house10.gif','500','house11.gif','800','house12.gif','800','house13.gif','800','house14.gif','800','house15.gif','8000','house16.gif','1000','house17.gif','1000','house18.gif','1000','house19.gif','1000','kamakura.gif','0','bil2.gif','150','bil3.gif','2000','bil4.gif','2000','bil5.gif','2000','sage.gif','2000','bbs6.gif','7777','newhose1.gif','2000','hose99.gif','0','onsen.gif','5000','house20.gif','120000','house21.gif','600000','chip1024.gif','3800000','house23.gif','150000','3r.gif','180000','land5.gif','2000');

#内装費用（左からA～Dランク。Aランクは４つのコンテンツを表示可能、Dランクは１つのみのコンテンツを表示可能。単位は万円）
@housu_nedan = ("2000","1000","500","0");

#役場でのランキング表示数
$rankMax='100';

#何日間食事しないと死んでしまうか（ユーザー削除期間）
$deleteUser = '120';

#食事は何分ごとにとることができるか
$syokuzi_kankaku = '30';

#身体パワーの回復率（何秒で１ポイント回復するか。数が少ないほど回復が早い）
$sintai_kaihuku = "5";

#頭脳パワーの回復率（何秒で１ポイント回復するか。数が少ないほど回復が早い）
$zunou_kaihuku = "5";

#卸問屋に並べる商品の数
$orosi_sinakazu = "400";

#卸問屋の在庫調整（「syouhin.dat」で指定の在庫の何倍の数を置くか。お店が増えてきて問屋の在庫数が足らないと思ったらこの数字を増やしてください。1.5などの指定も可）
$ton_zaiko_tyousei = '1';

#セントラル食堂に並べる食品の種類数
$syokudou_sinakazu = "30";

#デパートに並べる商品の種類数
$depart_sinakazu = "120";

#所有物の限度数
$syoyuu_gendosuu = '25';

#食堂やデパートでの在庫調節値（基準の在庫数をこの数字で割った数が店頭に並びますので、この数字を大きくするほど在庫が少なく、小さくするほど在庫が多くなります）
$zaiko_tyousetuti ="3";

#お店の種別（このゲーム内のお店に出回る商品のデータは、「dat_dir」内にある「shouhin.dat」に記録されています。「shouhin.dat」ファイルの一番左にあるのが商品種別で、ここの「お店の種別」と対応している必要があります。※ただし食堂を表す「食」は「shouhin.dat」のみにある種別となります）
@global_syouhin_syubetu = ("スーパー","書籍","食料品","薬","スポーツ用品","電化製品","美容","DVD","ファーストフード","日用品","お花","デザート","ギフト","アルコール","乗り物","ゲーム","ドリンク","ＷＷＡ","ＴＯＷＮ");

#挨拶の種類
@aisatu_keyword = ("あいさつ","雑談","みんなに知らせたいこと","オススメＵＲＬ");

#挨拶ログの保存件数
$aisatu_max = '50';

#最大登録者数
$saidai_ninzuu = '250';

#自動ファイル生成フォルダのパーミッション（777＝1、755＝2）※1でエラーが出るようなら2を試してみてください。
$zidouseisei = "2";

#メッセージ窓のスタイル設定
$message_window ="border: #ff9933; border-style: dotted; border-width: 1px; background-color:#ffffaa; color:336699";

#ステータス窓の枠色
$st_win_wak = "#ff9966";
#ステータス窓の背景色
$st_win_back = "#ffffff";

#設置する掲示板（'掲示板の名前'を「,」でつないだ形で設定してください。ここでの名前はマウスをのせた時にウインドウに表示させる名前です。ページ内のタイトルや各種デザイン・設定は管理メニューの「管理者作成BBSの設定」で行います。実際に街の任意の位置へ掲示板を配置するのは、管理者メニューの「街のレイアウト作成」で行ってください。）
@admin_bbs_syurui =('みんなの広場。総合掲示板です。','疑問解決BBS。普段疑問に思っている事を聞いてしまおう。','オススメ情報BBS。あなたのお薦め教えてください。','批判、要望掲示板　なにか言いたいことがあったらここに書き込んでください','新アイテム、新職業募集掲示板','帝都ジューレスカ専用掲示板','港町ラヴァート専用掲示板','ヨゼック町住民専用掲示板','選挙所『住民の皆様誰かに投票願います！』','KITOWN専用（ボンジン入場不可）','ＷＷＡネタ関連ＢＢＳ　ＷＷＡの出演者募集、ＷＷＡ制作アンケートはここで','裁判所　あなたの一言でなにかが変わる！','クロム市ICOIセンター','東ヨゼック住民談話所');

#上記掲示板の画像（左から上と対応させる）
@admin_bbs_gazou =('bbs1.gif','bbs2.gif','bbs3.gif','bbs4.gif','bbs6.gif','bbs5.gif','bbs5.gif','bbs5.gif','bbs8.gif','aisatu.gif','bbs7.gif','chip1061.gif','chip1064.gif','bbs5.gif');

#BBSの保存記事数（親記事、レス記事合わせた件数です）
$bbs_kizi_max = '100';

# アクセス拒否するホスト名
@deny = ("*.host.xx.jp","xxx.xxx.xx.");

#多重登録禁止（1で禁止、0で禁止しない）
$tajuukinsi_flag = '1';

#多重登録者をログインできなくする（1でする、0でしない）
$tajuukinsi_deny = '0';

#Ｃリーグ１大会の日数
$c_nissuu = '14';

#Ｃリーグの試合数
$c_siaisuu = "100";

#Ｃリーグの試合間隔（単位は分）30koko
$c_siai_kankaku = '1';

#キャラクターの画像サイズ指定（指定しない場合、自由サイズになります）
$chara_x_size = '32';	#横サイズ
$chara_y_size = '32';	#縦サイズ

#週間街コンテストの賞金額（単位は万円）
$mati_con_syoukin = '3000000';

#街コンテストの日数
$mati_con_nissuu = '7';

#プロフィールの１ページ表示数
$hyouzi_max_grobal = '10';

##以下、プロフィールでの選択肢
#性別のselect
		@sex_array=('','男','女','中間');

#年齢のselect
		@age_array=('','～14歳','15～18歳','19～22歳','23～26歳','27～30歳','31～34歳','35～38歳','39～42歳','43～46歳','47～50歳','51歳～');

#住所のselect
		@address_array=('','北海道','青森','岩手','宮城','秋田','山形','福島','群馬','栃木','茨城','埼玉','千葉','東京','神奈川','新潟','富山','石川','福井','山梨','長野','岐阜','静岡','愛知','三重','滋賀','京都','大阪','兵庫','奈良','和歌山','鳥取','島根','岡山','広島','山口','徳島','香川','愛媛','高知','福岡','佐賀','長崎','熊本','大分','宮崎','鹿児島','沖縄','海外');
		
#選択式プロフィール項目1
		$prof_name1='アピールポイント';
		@prof_array1=('','かっこいい','頭がいい','背が高い','ガッシリ体格','マッチョ','優しい','お金持ち','車が自慢','一人暮らし','心意気','素直','マメ','誠実','面白い','カワイイ','キレイ','胸が自慢','脚が自慢','ナイスバディ','家庭的','スポーツ得意','歌がうまい','料理が得意','エッチ','ダメ人間');

#選択式プロフィール項目2
		$prof_name2='ただいま';
		@prof_array2=('','恋人募集中','友だち募集中','メル友募集中','趣味友募集中','飲み友募集中','合コン仲間募集中','ＨＰ宣伝中','不倫相手募集中','愛人募集中','仕事一筋中','新婚中','別居中','不倫中','熱愛中','同棲中','勘当中','家出中','平凡生活中','片思い中');

#選択式プロフィール項目3
		$prof_name3='身長';
		@prof_array3=('','秘密','低い','やや低い','普通','やや高い','高い');

#選択式プロフィール項目4
		$prof_name4='体重';
		@prof_array4=('','秘密','やせてる','少しやせてる','普通','ややぽちゃ','ぽちゃ');

#選択式プロフィール項目5
		$prof_name5='職業';
		@prof_array5=('','フリーター','学生','無職','ＯＬ','サラリーマン','公務員','主婦','営業マン','技術職','商社マン','銀行マン','SE・プログラマー','パイロット','スチュワーデス','警察','消防士','僧侶','ファッション関係','プランナー','編集者','クリエイター','販売員','美容師','大工','マスコミ','社長','会社役員','事務職','コンピュータ','飲食業','教師','医師','アーティスト','デザイナー','タレント','看護婦','保母','コンサルタント','自営業','水商売','音楽関係','芸人','スポーツ選手','その他');
		
#記述式プロフィール項目
	@kijutu_prof = ('似ている有名人','趣味','ホームページ','弱点','好きな有名人','好きなスポーツ','好きな映画','好きなＴＶ番組','好きな音楽','好きな異性のタイプ','将来の夢','嫌いなもの','今一番行きたいところ','今一番したいこと','一言コメント');

##以下各種デザインのスタイル設定
#メッセージ窓のスタイル設定
$message_win ="border: #0000ff; border-style: dotted; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; background-color:#ffffff; color:000000";

$town_stylesheet =<< "EOM";
.dai {  font-size: 13px; font-weight: bold;color: #000000}	/*大きな文字*/
.tyuu {  font-size: 13px; color: #ff6600}	/*ステータス中見出し*/
.honbun2 {  font-size: 13px; line-height: 16px; color: #006699}	/*ステータス項目部分*/
.honbun3 {  font-size: 13px; line-height: 13px; color: #006699}	/*パラメータ項目部分*/
.honbun5 {  font-size: 13px; line-height: 13px; color: #339900}	/*キャラのパラメータ項目部分*/
.honbun4 {  font-size: 13px; line-height: 22px; color: #006699}	/*中くらいの文字*/
.job_messe {  font-size: 13px; line-height: 22px; color: #000000}	/*パラメータメッセージ*/
.small {  font-size: 13px; color: #444444}	/*小さい文字*/
.midasi {  font-size: 16px; font-weight: bold; text-align: center;color: #666666}	/*街の名前*/
.gym_style { background-color:#ffcc33; background-image:url($img_dir/shop_bak.gif)}	/*ジムの背景*/
.syokudou_style { background-color:#ccff66; background-image:url($img_dir/shop_bak.gif)}	/*食堂の背景*/
.orosi_style { background-color:#996633; background-image:url($img_dir/shop_bak.gif)}	/*卸問屋の背景*/
.job_style { background-color:#669966; background-image:url($img_dir/shop_bak.gif)}	/*ハローワークの背景*/
.school_style { background-color:#339999; background-image:url($img_dir/shop_bak.gif)}	/*学校の背景*/
.ginkou_style { background-color:#999999; background-image:url($img_dir/shop_bak.gif)}	/*銀行の背景*/
.yakuba_style { background-color:#336699; background-image:url($img_dir/shop_bak.gif)}	/*役場の背景*/
.item_style { background-color:#ffcc66; background-image:url($img_dir/command_bak.gif)}	/*アイテム使用画面の背景*/
.kentiku_style { background-color:#996666; background-image:url($img_dir/command_bak.gif)}	/*建築会社の背景*/
.omise_list_style { background-color:#b293ad; background-image:url($img_dir/shop_bak.gif)}	/*個人お店の商品リスト背景*/
.prof_style { background-color:#ccff99; background-image:url($img_dir/shop_bak.gif)}	/*プロフィール画面背景*/
.keiba_style { background-color:#99cc66; }	/*競馬背景*/

.yosumi {  border: #666666; border-style: solid; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; background-color:#ffffff}	/*街ステータス窓*/

.sumi {  border: #000000; border-style: solid; border-top-width: 0px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 0px}
.migi {  border: #000000; border-style: solid; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px}
.sita {  border: #000000; border-style: solid; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px}
.sita2 {  border: #666666; border-style: solid; border-top-width: 0px; border-right-width: 0px; border-bottom-width: 1px; border-left-width: 0px}
.jouge {  border: #000000; border-style: solid; border-top-width: 1px; border-right-width: 0px; border-bottom-width: 1px; border-left-width: 0px}
.message {  font-size: 13px; line-height: 16px; color: #000000;}


.purasu {color: #009900;}
.mainasu { color: #ff3300;}
.kuro {  font-size: 13px; text-align: left;color: #000000}
.honbun {  font-size: 13px; line-height: 16px; color: #000000}
.loto {  font-size: 28px; color: #000000;line-height:180%;}
.naka {  border: #000000; border-style: solid; border-top-width: 1px; border-right-width: 0px; border-bottom-width: 1px; border-left-width: 0px}
a {color:#333333;text-decoration: none}
a:hover {text-decoration: underline}
body {font-size:13px;color:#000000 }
table {font-size:13px;color:#000000;}
EOM

##########################ver.1.1追加
#ギフト所有限度数（贈られたギフト）
$gift_gendo = '10';

#ギフト購入限度数
$kounyu_gift_gendo = '10';

#自分のお店に置ける商品アイテム数
$mise_zaiko_gendo = '100';

#アイテム数が限度以上なら同じアイテムでも在庫を増やせなくする（増やせない＝1、増やせる＝0）
$douitem_ok = '0';

#温泉入浴時に通常の何倍の早さでパワーが回復するか
$onsen_times = '10';

#温泉で使っている画像の数（温泉の画像を追加する場合、img/onsenフォルダー内に○.jpg（○は1から始まる連番の数字）というファイル名で入れてください）
$on_gazou_suu = '10';

#温泉入浴料（単位は円）
$nyuuyokuryou = '100';

#gzipのパス（サーバーがgzip圧縮に対応していない場合空欄にしてください（※画面が真っ白になる場合などは対応してない可能性が高いです）。ただし、gzip圧縮が使えるとテキストの転送量をかなり小さくできます）
$gzip = '';

#徒歩で街の移動にかかる時間（秒指定）
$matiidou_time = '30';

#各乗り物ごとで移動にかかる時間（'乗り物','かかる秒数'のフォーマットで。乗り物はsyouhin.datにある商品でないと意味がありません。ただし商品種別が「乗り物」である必要はありません。コメントに「※移動手段です」と入れてあげた方が親切かと思います。また速いものから先に並べてください）
%idou_syudan =('自転車','15','ロケット','5','次元転送装置','0','早歩き','20','ランニングシューズ','17','ローラースルーゴーゴー','ベスパ','10','スーパーカブ','10','ドゥカティ','7','ナナハン','7','カローラ','7','ボルボ','6','キャデラック','6','ベンツ','5','ロールスロイス','5','スカイラインGTR','4','ロータスヨーロッパ','4','アルファロメオ','4','ジャガー','4','BMW','4','フェアレディZ','5','15','ポルシェ','3','フェラーリ','2','ミグ25','3');

#街移動時に事故を起こす確率（何分の一の確率かで指定。デフォルトでは10分の１という意味）
$ziko_kakuritu = '20';

#行動制限時間（秒指定。制限を付けない場合＝0）
$koudou_seigen = '5';

#カードゲームができる間隔（単位：分）
$crad_game_time = '1';

##########################ver.1.2追加

#同一アイテムの所有個数の限度
$item_kosuuseigen = '5';

#仕事ができる間隔（単位：分。制限無しの場合は0を指定）
$work_seigen_time = '1';

#競馬の購入限度枚数（単位：枚）
$keiba_gendomaisuu = '1000000';

##########################ver.1.21追加
#メンバーログのファイルロック方式（0でリネームロック、0でうまく稼働しない場合1にしてください）
$mem_lock_num = '0';


##########################ver.1.3追加
#恋人斡旋プロフィール記録ファイル
$as_profile_file='./log_dir/as_pfofilelog.cgi';
#カップル記録ファイル
$couple_file='./log_dir/couplelog.cgi';
#子供記録ファイル
$kodomo_file='./log_dir/kodomolog.cgi';
#街ニュース記録ファイル
$news_file='./log_dir/mati_news.cgi';

#役場のニュース表示件数
$news_kensuu = '100';

#お店に置いておける在庫の上限数
$mise_zaiko_limit = '100';

#メールの保存件数
$mail_hozon_gandosuu = '50';

#特別風呂は何倍の速度で回復するか
$tokubetu_times = '100000000';

#特別風呂でかかる費用（円）
$tokubetuburo_hiyou = '1000000';

#koko2006/03/31
#松風呂は何倍の速度で回復するか
$matu_times = '100';
#松風呂でかかる費用（円）
$matu_hiyou = '5000';
#竹風呂は何倍の速度で回復するか
$take_times = '200';
#竹風呂でかかる費用（円）
$take_hiyou = '20000';
#梅風呂でかかる費用（円）
$ume_times = '500';
#梅風呂でかかる費用（円）
$ume_hiyou = '100000';
#kokoend2006/03/31

#恋愛システムを使うか（1＝使う、0＝使わない　※0にすることでハート＆子供アイコンが出現されなくなります。また、0の場合「恋人斡旋所」も街に設置しないでください）
$renai_system_on = '1';

#恋愛に必要なLOVEパラメータの数値
$need_love = '200';

#同性の恋愛を許可する（許可しない＝0、許可する＝1）
$douseiai_per = '0';

#同時に何人とつきあえるか（配偶者＋恋人）
$koibito_seigen = '5';

#結婚に必要なラブラブ度（思い出数値の合計）
$aijou_kijun = '500';

#結婚に必要な思い出数値（それぞれの思い出の最低値）
$omoide_kijun = '80';

#恋人と何日間デートをしないと別れてしまうか
$wakare_limit_koibito = '7';

#配偶者と何日間デートをしないと別れてしまうか
$wakare_limit_haiguu = '9';

#相手が配偶者の場合の子供ができる確率（10なら10分の１の確率）koko
$kodomo_kakuritu1 = '7';

#相手が恋人の場合の子供ができる確率（80なら80分の１の確率）koko
$kodomo_kakuritu2 = '15';

#子育てできる間隔（単位：時間）
$kosodate_kankaku = '3';

#子供のパラメータを１あげるのにかかる費用（円）
$youikuhiyou = '20000';

#何日間子供に食事を与えないと死んでしまうか
$kodomo_sibou_time = '7';

#子供は何歳まで生きるか（仕送りが送られてくる期間）
$kodomo_sibou_time2 = '70';

#以下、結婚斡旋所での変更可能なプロフィール項目
#年齢のselect
		@as_age_array=('','～14歳','15～18歳','19～22歳','23～26歳','27～30歳','31～34歳','35～38歳','39～42歳','43～46歳','47～50歳','51歳～');

#住所のselect
		@as_address_array=('','北海道','青森','岩手','宮城','秋田','山形','福島','群馬','栃木','茨城','埼玉','千葉','東京','神奈川','新潟','富山','石川','福井','山梨','長野','岐阜','静岡','愛知','三重','滋賀','京都','大阪','兵庫','奈良','和歌山','鳥取','島根','岡山','広島','山口','徳島','香川','愛媛','高知','福岡','佐賀','長崎','熊本','大分','宮崎','鹿児島','沖縄','海外');

#選択式プロフィール項目1
		$as_prof_name2='アピールポイント';
		@as_prof_array2=('','かっこいい','頭がいい','背が高い','ガッシリ体格','マッチョ','優しい','お金持ち','車が自慢','一人暮らし','心意気','素直','マメ','誠実','面白い','カワイイ','キレイ','胸が自慢','脚が自慢','ナイスバディ','家庭的','スポーツ得意','歌がうまい','料理が得意','エッチ','ダメ人間');

#選択式プロフィール項目2
		$as_prof_name3='住んでいる街';
		@as_prof_array3=('','家を持っていない','メインストリート','シーリゾート','カントリータウン','ダウンタウン');

#選択式プロフィール項目3
		$as_prof_name4='欲しい子供の数';
		@as_prof_array4=('','子供はいらない','1人でいい','2人くらい','3人くらい','4、5人は欲しい','6人以上');

#選択式プロフィール項目4
		$as_prof_name5='相手の年齢は';
		@as_prof_array5=('','年齢は気にしない','同じくらいがいい','年上がいい','年下がいい','すごく年上がいい','すごく年下がいい');

#選択式プロフィール項目5
		$as_prof_name6='相手に望むこと';
		@as_prof_array6=('','かっこよさ','頭のよさ','背の高さ','ガッシリ体格','マッチョ','優しさ','裕福度','一人暮らし','心意気','素直さ','マメさ','誠実さ','面白さ','カワイさ','キレイさ','胸の大きさ','脚のキレイさ','ナイスバディ','家庭的','スポーツマン','歌のうまさ','料理の上手さ','エッチさ');

#街の下に挨拶を表示するか（表示する＝1、表示しない＝0）
$top_aisatu_hyouzi = '1'; #koko

#上で表示するにした場合の表示件数
$top_aisatu_hyouzikensuu = '20';
#上で表示するにした場合の名前の色
$top_aisatu_hyouzi_iro1 = '#333399';
#上で表示するにした場合の記事の色
$top_aisatu_hyouzi_iro2 = '#333333';

#トップページで街の下に自由表示するするhtml（EOM～EOMの間にhtmlで記述）
$top_information =<< "EOM";
<div align="center"><br><br>
<iframe src="./coun0/counter0.cgi" name="counter" width="170" height="40" scrolling="no">カウンター</iframe>
<br>
</div>
<Table Border>
<Tr>
<Td>
同盟ＴＯＷＮ<br>
<a href="http://w3.oroti.com/~wgs/cgi-bin/EBNC/town_maker.cgi"><img src="ebnctop.gif"></a><br>
<a href="http://oroti.com/~poke/town_hairu/"><img src="new_poketown.gif"></a><br>
</Td>
</Tr>
</Table> 
EOM

##########################ver.1.30追加
# 参加者を表示する（1＝表示、0＝表示しない）
$sanka_hyouzi_kinou = '1';

# 上で「表示」にした場合の表示位置（1＝上、0＝下）
$sanka_hyouzi_iti = '1';

#同時にログインできる人数
$douzi_login_ninzuu = 20;

##########################ver.1.40追加
# 参加者ファイル（ver.1.30で"./log_dir/guestlog.cgi"だった指定を変更）
$guestfile = "./guestlog_00.cgi";

#ゲームしないでログアウトするまでの時間（秒）
$logout_time = '1200';

#新規登録の受け付け（1＝うけつけない、0＝うけつける）koko
$new_touroku_per = '0';

#名前＆パスワード記録ファイル
$pass_logfile = './log_dir/passlog.cgi';

#自分や配偶者のお店で商品を買えなくする（1＝買えない、0＝買える）
$kaenai_seigen = '0';

############## 追加 ###################
# 買い物制限品名特定品目を持っていないと買えない物を作る
$kyokasuru = '';#'コーヒー';
$kyokahin2 = '';#'バイアグラ';
# 制限品目リスト上記商品を持っていないとこの商品は買えなくなる。
@kyokahitsuyou = ('ビッグマック','リポビタンD','リコリス','コーヒー','バイアグラ','フェロモン香水','Apple1','ブラタンメンバーズカード');

#######################################

#------------------設定変更ここまで（以下は必要があれば変更してください）
#スクリプトの名前
$script='./town_maker.cgi';
#個人ログデータファイル
$logfile='./log_dir/memberlog.cgi';
#Ｃリーグログデータファイル
$doukyo_logfile='./log_dir/doukyo_log.cgi';
#オリジナル家リストファイル
$ori_ie_list='./log_dir/ori_ie_log.cgi';
#メイン街パラメータ記録ファイル
$maintown_logfile='./log_dir/maintownlog.cgi';
#卸商品記録ファイル
$orosi_logfile='./log_dir/orosilog.cgi';
#今日の食堂メニュー記録ファイル
$syokudou_logfile='./log_dir/syokudoulog.cgi';
#今日のデパートの品揃え記録ファイル
$depart_logfile='./log_dir/departlog.cgi';
#挨拶ログ記録ファイル
$aisatu_logfile='./log_dir/aisatulog.cgi';
#街コンテストログファイル
$maticon_logfile='./log_dir/maticonlog.cgi';
#街コンテスト功労者ログファイル
$kourousya_logfile='./log_dir/kourousyalog.cgi';
#ドーナツゲームログファイル
$donuts_logfile='./log_dir/donutslog.cgi';
#ドーナツゲームログファイル2#koko
#$donuts2_logfile='./log_dir/donutslog2.cgi';
#競馬ログファイル
$keiba_logfile='./log_dir/keibalog.cgi';
#競馬ランキングログファイル
$keibarank_logfile='./log_dir/keibaranklog.cgi';
#ロックファイル名
$lockfile = './lock';
#競馬ロックファイル名
$keibalockfile = './lock2';
#プロフィール記録ファイル
$profile_file='./log_dir/pfofilelog.cgi';

1;
