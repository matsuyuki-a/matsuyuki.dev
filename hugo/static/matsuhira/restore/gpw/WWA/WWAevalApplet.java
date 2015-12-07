////////////////////////////////////////////////////////////////////////////////////////////////
// WWAevalApplet
// WWAevalでAppletの機能を使うために作成したプログラムです。
////////////////////////////////////////////////////////////////////////////////////////////////
import java.applet.Applet;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WWAevalApplet extends Applet implements Runnable {


//画像のあるフォルダの相対パス
String imgDir = "./img/";

//ロードする画像のファイル名
//ファイル名の番号と同じ添字のImgCropが置換されます
String[] imgNames = {
	"300.png",
	"301.png",
	"302.gif",
	"303.gif",
	"304.png",
	"310.gif",
	"311.png",
};

//画像フォルダ内の、読み込む画像ファイル名の一覧があるテキストファイル
//「""」以外を設定した場合上のimgNamesの設定よりも優先されます
//String imgListTxt = "";
String imgListTxt = "imglist.txt";






boolean imgLoaded = false;
String errMes = "";
HashMap loadedImgs = new HashMap();
private static WWAevalApplet instance;
private static volatile Integer countOfMe = new Integer(0);

public void paint(Graphics g) {
	g.drawString("WWA追加画像読み込み用のアプレットです", 16, 10);
}

public void start() {
	//念のため同期
	synchronized(countOfMe) {
		countOfMe = new Integer(countOfMe.intValue() + 1);
		if(countOfMe.intValue() > 1)
			System.out.println("干渉を検出 at WWAevalApplet");
	}
	
	instance = this;
	Thread thread = new Thread(this);
	thread.start();
}

public void stop() {

	synchronized(countOfMe) {
		countOfMe = new Integer(Math.max(0, countOfMe.intValue() - 1));
	}
	
	instance = null;
	try {
		WWAextendSub.getInstance().stop();
	}
	catch(NullPointerException e) {}
}

void LoadImage() {
	URL imgBase = getNormalizedURL(getDocumentBase(), imgDir);
	MediaTracker tracker = new MediaTracker(this);
	java.util.List imgList = new ArrayList();

	if(imgListTxt != null && imgListTxt.length() != 0) {
		URL u = null;
		BufferedReader br = null;
		try {
			u = new URL(imgBase, imgListTxt);
			br = new BufferedReader(new InputStreamReader(u.openStream(), "JISAutoDetect"));
			while (br.ready()) {
				String text = br.readLine().replaceAll("//.*$|\\s", "");
				if(text.length() == 0 || !text.matches(".*\\d.*"))
					continue;
				imgList.add(text);
			}
		} catch (Exception e) {
			addError(imgListTxt + "の読み込みに失敗しました。");
			e.printStackTrace();
		} finally {
			if (br != null) try { br.close(); } catch (IOException e) {}
		}
	}
	else {
		imgList = Arrays.asList(imgNames);
	}
	
	for(int i = 0; i < imgList.size(); i++) {
		
		String imgName = (String)imgList.get(i);
		Image img = null;
		Integer imgNo = null;
		
		Matcher m = Pattern.compile("\\d+").matcher(imgName);
		if(m.find()) {
			imgNo = Integer.valueOf(m.group());
		}
		else {
			addError("「" + imgName + "」に添字として使用する数字がありません。");
			continue;
		}
		if(loadedImgs.get(imgNo) != null) {
			addError("「" + imgName + "」と同じ番号の添字がすでに使用されています。");
			continue;
		}
		
		URL imgURL = getNormalizedURL(imgBase, imgName);
		if(imgURL == null) {
			addError("「" + imgName + "」は不正なファイル名です。");
			continue;
		}
		if(imgURL.toExternalForm().indexOf(imgBase.toExternalForm()) != 0) {
			addError("「" + imgName + "」は不正なフォルダ位置にアクセスしています。");
			continue;
		}
		img = getImage(imgURL);
		
		tracker.addImage(img, 0);
		
		try {
			tracker.waitForID(0);
		}
		catch (InterruptedException e) {}
		
		if((checkImage(img, this) & ERROR) != 0 ){
			img = null;
			addError("「" + imgName + "」が存在しないかユーザーがオフラインで読み込めませんでした。");
			continue;
		}
		
		if(img != null)
			loadedImgs.put(imgNo, img);
		else
			addError("「" + imgName + "」が読み込めませんでした。");
	}
}

URL getNormalizedURL(URL url, String relative) {
	try {
		return new URI(url.toString().replaceAll(" ", "%20")).resolve(relative.replaceAll(" ", "%20"))
						.normalize().toURL();
	}
	catch(Exception e) {
		addError("URLの解析に失敗しました。rel：" + relative);
		e.printStackTrace();
		return null;
	}
}

void addError(String err) {
	errMes = errMes.concat(err).concat("\n");
	System.out.println(err);
}

public void run() {
	LoadImage();
	imgLoaded = true;
}

public static WWAevalApplet getInstance() {
	//なかったらちょっと待つ
	for (int i = 0; i < 10; i++) {
		if(instance != null) return instance;
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e){
		}
	}
	return null;
}

public static boolean isInterfered() {
	//同期なしでいいや
	return countOfMe.intValue() > 1;
}

public HashMap getImage() {
	return imgLoaded ? loadedImgs : null;
}

public String getErrMes() {
	return errMes.length() == 0 ? null : errMes;
}

public void soundPlay(String name) {
	//音声ファイルをロードしていることが前提
	//…これってJava内で読み込まれてたらリロードしないんだよね？
	//JRE4ならリロードしないようだが・・・
	play(getDocumentBase(), name + ".au");
}

}