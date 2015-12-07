var form;

window.onload = function(){
	form = document.getElementById("regform");
}
function setURI(){
	if(document.reg.pass.value == ""){form.setAttribute("action","");}
	else{form.setAttribute("action","m005_3" + document.reg.pass.value + ".html");}
}