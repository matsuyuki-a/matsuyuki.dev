<?php
$title = "";
switch($_GET["editor"]){
	case "matsuyuki":
		$title .="M"
		break;
	case "hirarirar":
		$title .="H"
		break;
	case "matsuhira":
		$title .="G"
		break;
	case "tai":
		$title .="T"
		break;
	default:
		die("����ȍ�҂��܂���...");
}
$title .= get_wwa_num_for_title($_GET["wwanum"]);

function get_wwa_num_for_title($num){
if($num >= 1000){
	die("WWA�I�I�X�M");
	return -1;
}elseif($num >= 100){
	return $num;
}elseif($num >= 10){
	return "0".$num;
}else{
	return "00".$num;
}


?>
			<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<html>
			<head>
			<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
			<title><?php echo $title; ?></title>
			</head>
			<body>
			<div id="main">
			<h1><?php echo $title; ?></h1>
			<p><a href="http://tsucreating.jp/matsuyuki/wwa/">WWA�̃y�[�W�ɖ߂�</a></p>
			<applet code="WWA.class" width="560" height="440">
			<?php echo "<param name=\"paramMapName\" value=\"$title\">" ?>
			���̃u���E�U�ɂ́AJava���s�����C���X�g�[������Ă��܂���B<BR>
			�ڍׂ�<a href="http://www.wwajp.com/wwafaq.html">�v�v�`�̂悭���鎿��</a>���ǂ����B
			</applet>
			<p>Internet RPG "<a href="http://www.wwajp.com/">World Wide Adventure</a>" (C)1996-2009 NAO</p>
			<p><a href="http://tsucreating.jp/matsuyuki/wwa/">WWA�̃y�[�W�ɖ߂�</a></p>
			</div>
			</body>
			</html>