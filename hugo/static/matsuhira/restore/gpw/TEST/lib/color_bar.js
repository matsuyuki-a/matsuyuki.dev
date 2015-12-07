var canvas;
var nowTime;
var d;
var h;
var m;
var s;

window.onload=init;
function init(){
	var canvas = document.getElementById("movie");
	canvas = canvas.getContext("2d");//.getContext(x)：canvas に描画するための API にアクセスできるオブジェクトを返す。 未サポートはnull
		
	//ここからカラーバー
	
		//１段目
		canvas.fillStyle='rgb(102,102,102)';
		canvas.fillRect(0,0,107,180);//(始点x,始点y,⊿x,⊿y)
		
		canvas.fillStyle='rgb(170,170,170)';
		canvas.fillRect(107,0,107,180);
		
		canvas.fillStyle='rgb(255,255,0)';
		canvas.fillRect(214,0,107,180);
		
		canvas.fillStyle='rgb(0,255,255)';
		canvas.fillRect(321,0,107,180);
		
		canvas.fillStyle='rgb(0,255,0)';
		canvas.fillRect(428,0,107,180);
		
		canvas.fillStyle='rgb(255,0,255)';
		canvas.fillRect(535,0,107,180);
		
		canvas.fillStyle='rgb(255,0,0)';
		canvas.fillRect(642,0,107,180);
		
		canvas.fillStyle='rgb(0,0,255)';
		canvas.fillRect(749,0,107,180);
		
		canvas.fillStyle='rgb(102,102,102)';
		canvas.fillRect(856,0,107,180);
		
		//２段目
		canvas.fillStyle='rgb(0,255,255)';
		canvas.fillRect(0,180,107,26);
		
		canvas.fillStyle='rgb(204,204,204)';
		canvas.fillRect(107,180,107,26);
		
		canvas.fillStyle='rgb(170,170,170)';
		canvas.fillRect(214,180,642,26);
		
		canvas.fillStyle='rgb(0,0,255)';
		canvas.fillRect(856,180,107,26);
		
		//３段目
		canvas.fillStyle='rgb(255,255,0)';
		canvas.fillRect(0,206,107,26);

		var grad  = canvas.createLinearGradient(0,0,749,0);
		  grad.addColorStop(0,'rgb(0,0,0)');
		  grad.addColorStop(0.5,'rgb(131,131,131)');
  		　grad.addColorStop(1,'rgb(255,255,255)');
		canvas.fillStyle = grad;
		canvas.rect(107,206,749,26);
  		canvas.fill();
  		
		canvas.fillStyle='rgb(255,0,0)';
		canvas.fillRect(856,206,107,26);
		
		//４段目
		canvas.fillStyle='rgb(34,34,34)';
		canvas.fillRect(0,232,107,108);
		
		canvas.fillStyle='rgb(0,0,0)';
		canvas.fillRect(107,232,160,108);
		
		canvas.fillStyle='rgb(255,255,255)';
		canvas.fillRect(267,232,215,108);
		
		canvas.fillStyle='rgb(255,255,255)';
		canvas.fillRect(267,232,215,108);
		
		canvas.fillStyle='rgb(0,0,0)';
		canvas.fillRect(482,232,478,108);
		
	//カラーバーここまで
		
	//文字
	canvas.fillStyle='rgb(255,255,255)';
	canvas.font = "25px 'メイリオ'";
	canvas.shadowBlur = 10;
	canvas.shadowColor = "#990000";
	canvas.fillText("G10-Project☆Works!",500,270);
	canvas.fillText("Canvas-Vision　実用化試験",500,300); 
	
	setInterval(function(){
		canvas.clearRect(267,232,215,108);
		canvas.shadowBlur = 0;
		canvas.fillStyle='rgb(255,255,255)';
		canvas.fillRect(267,232,215,108);
		
		canvas.shadowBlur = 10;
		canvas.fillStyle='rgb(0,0,0)';
		canvas.shadowColor = "#000000";
		canvas.fillText(getNowTime(),400,300); 
	},1000)
}

function getNowTime(){
	d = new Date();
	h = d.getHours();
	m = d.getMinutes();
	s = d.getSeconds()
	if (h < 10) { h = "0" + h; }
	if (m < 10) { m = "0" + m; }
	return h+":"+m;
}