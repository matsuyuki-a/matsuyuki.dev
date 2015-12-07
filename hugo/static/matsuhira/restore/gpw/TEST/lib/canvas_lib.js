var jx = 300;
var jy = 50;
var mx = 40;
var my = 120;
var randed;
var canvas;

window.onload=init;
function init(){
	var canvas = document.getElementById("movie");
	canvas = canvas.getContext("2d");//.getContext(x)：canvas に描画するための API にアクセスできるオブジェクトを返す。 未サポートはnull
		
	canvas.scale(2,2);
	
  	canvas.fillText("・ω・",jx,jy);
  	canvas.fillText("・×・",mx,my);
  	setInterval(function(){
		canvas.clearRect(0,0,960,320);
		canvas.beginPath();
		canvas.fillText("・ω・",jx,jy);
		jx+=randomMove();
		jy+=randomMove();
		
		canvas.fillText("・×・",mx,my);
		mx+=randomMove();
		my+=randomMove();

  	},50);
}

function randomMove(){
	randed = Math.floor((Math.random()*2));
	if(randed == 0){return -3;}
	else{return 3;}
}