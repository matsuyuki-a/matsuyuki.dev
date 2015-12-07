#!/usr/local/bin/perl

###################################################
#
#	カウンター　Ver 1.02 2007/08/29
#	Edit:たっちゃん
#
###################################################

# カウント数ファイル
$count_f = "countfail0.txt";

# 記録範囲時間　(24*60*60)=1日
$kirokuhani = 60;

# メイン桁数
$meinketasu = 6;

# コンマを入れる$conma = 1;
$conma = 1;
# 画像でコンマを入れる$conma_g = 1;
$conma_g = 0;

&cuntup;

$count0 = sprintf("%0".$meinketasu."d",$count0);

if ($count0 =~ /^[-+]?\d\d\d\d+/g && $conma==1) {
	for ($i = pos($count0) - 3, $j = $count0 =~ /^[-+]/; $i > $j; $i -= 3) {
		substr($count0, $i, 0) = ',';
	}
}


#####　書き出し処理　#####

print "Content-type:text/html;\n\n"; #koko2006/12/22
print <<EOF ;
<html>
<head>
<META http-equiv="content-type" content="text/html; charset=Shift_JIS">
<META http-equiv="Content-Style-Type" content="text/css">
<title>カウンター</title>
<style type="text/css">
<!--
body {margin-top:0}
-->
</style>
</head>
<body>
EOF

print "<TABLE border=\"0\">\n";
print "<TR><TD rowspan=\"2\">\n";
print "<font size=\"5\"><b>$count0</b></font></TD>\n";
print "<td><font size=\"2\">今日</font></td><td><font size=\"2\">$count3</font></td></TR>\n";
print "<TR><td><font size=\"2\">昨日</font></td><td><font size=\"2\">$count4</font></td></TR>\n";
print "</TABLE>\n";
print "</body>\n";
print "</html>\n";


exit;


##### カウントアップ本体　######
sub cuntup {
	if (not(-e $count_f)) { &failon ($count_f) ; }

	open (DAT, "< $count_f") or &error("エラー・ファイルが開けません");
	eval{ flock (DAT, 2); };
	@count = <DAT>;
	close (DAT);
	$loop_count = 0;

	while ($loop_count <= 10){
		for(0..50){$i=0;}
		if (-z $count_f){$size_f1 = 0;}else{last;}

		if ($size_f1 == 0 && $loop_count >= 10){
			&error("ファイル内容が壊れました。");
			last;
		}
		if ($size_f1 == 0){
			open (DAT, "< $count_f") or &error("エラー・ファイルが開けません0");
			eval{ flock (DAT, 2); };
			@count = <DAT>;
			close (DAT);
		}
		$loop_count++;
	}
	######
	($count0,$count1,$count2,$count3,$count4,$count5) = split(/<>/, $count[0]);
	##トータルカウント数#記録日#経過日数#今日のカウント数#昨日のカウント数
	$count0++;
	$local_time = time + (9*60*60);#GMT+9:00補正
	if (!$count1){
		$count1 = $local_time - ($local_time % (24*60*60));
	}
	if ($local_time - $count1 > 24*60*60){
		$count2 += int(($local_time - $count1)/(24*60*60));
		if ($local_time - $count1 > 2*24*60*60){
			$count4 = 0;
		}else{$count4 = $count3;}
		$count1 = $local_time - ($local_time % (24*60*60));
		$count3 = 0;
	}
	$count3++;
	if (!$count2){$count2 = 0; }
	if (!$count4){$count4 = 0; } 
	$count5 = time;
	$count_dat = "$count0<>$count1<>$count2<>$count3<>$count4<>$count5<>\n";
	######
	unshift (@count,$count_dat);
	$i =0;
	foreach (@count){
		($count0_d,$count1_d,$count2_d,$count3_d,$count4_d,$count5_d) = split(/<>/);
		if ($count5_d < time - $kirokuhani){
			$#count = $i - 1;
			last;
		}
		++$i;
	}
	#--------------書き込み
	open (DAT, "> $count_f ") or &error("エラー・ファイルが開けません1");
	eval{ flock (DAT, 2); };
	print DAT @count;
	close (DAT);

	$loop_count = 0;
	while ($loop_count <= 10){
		for(0..50){$i=0;}
		if (-z $count_f){$size_f1 = 0;}else{last;}
		if ($size_f1 == 0 && $loop_count <= 10){
			open (DAT, "> $count_f ") or &error("エラー・ファイルが開けません2");
			eval{ flock (DAT, 2); };
			print DAT @count;
			close (DAT);
		}
		$loop_count++;
	}
}

#####　カウントファイルの自動作成　######
sub failon {
	open (FIN, "> $_[0] ") or &error("エラー・ファイルが開けません");
	eval{ flock (FIN, 2); };
	$fdata = "0";
	print FIN $fdata ;
	close (FIN);
}
#### エラーの時 #####
sub error {
	print "Content-type:text/html;\n\n";
	print <<EOF ;
<html>
<head>
<META http-equiv="content-type" content="text/html; charset=Shift_JIS">
<META http-equiv="Content-Style-Type" content="text/css">
<title>カウンターエラー</title>
</head>
<body>
EOF
	print "<P><h4>$_[0]</h4>\n";
	print "</body>\n";
	print "</html>\n";
	exit;
}

