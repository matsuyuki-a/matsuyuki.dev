#!/usr/local/bin/perl

##### 開発記録など ############
# 2007/05/17 
# Edit:ＢＢＳ(たっちゃん)
# 		Ver1.16
#
# bbs0.cgi	  700(パーミッション)
# kiroku.cgi	  600(パーミッション)
# host_kanri.cgi　600(パーミッション)
##### 設定 ####################
# このｃｇｉのファイルの名前
$this_cgi = 'bbs0.cgi';

# タイトルの名前
$this_name = 'ＢＢＳゼロ';

#タイトルの画像
$this_img = "./img/bbs0.gif";

# オーナーパスの設定(変更してください)
$ona_pas = '1234';

# 許可管理者名
$kanre_name = 'たっちゃん';

# 管理者が許可するまで公表しない時は1,公開の時 0,
$kanri = 1;

#内緒話の許可(許可=1,使わない="")
$naisyo_kyka = 1;

#英文字のみの投稿禁止(禁止=1,許可="")
$eiginomi_dame = 1;

#URLのマッチ許可(1～許可URL数)
$mach = 2;

#全角空白の数のセット　全角空白が指定文字以上続いたら等幅フォントになる。0は使わない
$zenkaku_kuuhaku = 3;

#オート改行処理 半角文字数
$tab_chengi = 100;

# 背景色
$this_bgcolor ="#add8e6";

#戻りアドレス"./homu.htm";
$modoru = "<br><input type=\"button\" value=\"終了\" onClick=\"window.close()\""; #新しいウインドーで開いた時
#$modoru = "<a href=\"./homu.htm\">終了</a>";  #リンクで戻る

#全体の設定
$bak_set = "background=\"./img/tatu.gif\"  style=\"background-attachment: fixed;\"";
# テーブルの色
$taitol_bgcolor = "#ffa500";
$name_bgcolor = "#7cfc00";
$comento_bgcolor = "#ffffff";
$table_bac = "#ffffff";

# この記録ファイル
$bbs_kiroku = "kiroku.cgi";

# レスが付くと上に上げる 1でアップ
$res_up = 1;

# 表示スレッド（何投稿表示するか）
$suredosu = 30;

# １スレッドの投稿限界 0の時は制限無し
$suredolimit =10;

# １ページに表示のスレッド数
$pegi_limit = 6;

# New の表示時間 24; は24時間 0は表示しない
$new_time = 24;

# 表の幅
$waido = "90%";

# 投稿の大きさ バイト
$max_size = 8000;

# 外部アクセス禁止ファイル名 koko2005/10/27 
$kinshiHostFaill = './host_kanri.cgi';

# GET の不許可　1
$get_no = 1;

# ミニカウンタの設置
#  → 0=no 1=テキスト 2=画像
$counter = 2;

# ミニカウンタの桁数
$mini_fig = 5;

# テキストのとき：ミニカウンタの色
$cntCol = "#DD0000";

# 画像のとき：画像ディレクトリを指定
#  → 最後は必ず / で閉じる
$gif_path = "./img/";
$mini_w = 27;		# 画像の横サイズ
$mini_h = 44;		# 画像の縦サイズ

# カウンタファイル
$cntfile = './count.dat';

# 登録するクッキーの名前
$COOKIE_NAME = 'bbs';
# クッキーの有効期間
$COOKIE_LIFE = 30;
##### 設定終了 ################
&acsesu;	#アクセス禁止
&loadformdata;	#フォーム入力
&load_cookie;	#クッキー書き込み
&yomikomi;	#データー読込
if ($FORM{'kanri'} eq 'on'){&a_kanri;}  #アクセス禁止の解除
if ($FORM{'acusesu'} ne ""){&acusesu;}  #アクセス禁止登録
if ($FORM{'edit'} ne ""){&edit;}	#管理者編集　#koko2006/06/21
if ($FORM{'kanri_ck'} ne ""){&kanri;}	#管理許可
if ($FORM{'res'} ne ""){&res_modo;}	#レス                #####
if ($FORM{'del'} ne ""){&dele;}		#削除表示
if ($FORM{'ouna_del'} ne ""){&ohua_del;}#オーナーの記事削除koko2006/03/28
&kakicomi;	#書込処理
&set_cookie;	#クッキーに値をセット
&print_http_header;	#クッキー付きヘッダー作り
&mein;		#表示
exit;
### アクセス管理 ##############
sub acsesu {
	$host = $ENV{'REMOTE_HOST'};
	if (!$host){$host = $ENV{'REMOTE_ADDR'};} #koko2007/05/16
	$addr = $ENV{'REMOTE_ADDR'};
	if ($host eq "" || $host eq $addr) {
		$host = gethostbyaddr(pack("C4", split(/\./, $addr)), 2) || $addr; #koko2007/05/18
	}
	if ($host eq "") { $host = $addr; }

	if ($kinshiHostFaill){
		open (REDDAT, "$kinshiHostFaill") or &err2("Open Error: $kinshiHostFaill");
		eval{ flock (REDDAT, 2); };
		@denyHost = <REDDAT>;
		close (REDDAT);
	}

	$denyHost = join(" ", @denyHost);

	local($flag)=0;
	foreach ( split(/\s+/, $denyHost) ) {
		s/(\W)/\\$1/g;
		s/\*/\.\*/g;
		if ($host =~ /$_/i || $addr =~ /$_/i) { $flag=1; last; }
	}
	if ($flag) { &err2("アクセスを許可されていません"); }

}
### データ読込 #################
sub yomikomi{
	open (COUNT, "<$bbs_kiroku") or &err2("エラー・ファイルが開けません1");
	eval{ flock (COUNT, 2); };
	$cout_ima0 = <COUNT>;
	@kiroku = <COUNT>;
	close (COUNT);

	($cout_ima,$koment_bak) = split(/<>/, $cout_ima0);
	chomp $cout_ima0;
}
##### メイン処理　#############
sub mein{
	&header;	# ヘッダーを呼び出し
	&form;		# ここに入力フォームを入れる
#	print "<hr width=\"$waido\" align=\"center\">\n";
	$i=0;
	
	$loop = 0;
	$end_loop =$#kiroku;
	$stato_point = 0;

	if ($FORM{'ato'} ne "" && $FORM{'pegi_a'} <= $end_loop -1){
		
		$loop = $FORM{'pegi_a'};
		++$loop;
		$stato_point = $loop;
	}else{
		$loop = $FORM{'pegi_m'};
		$stato_point = $FORM{'pegi_m'};
	}
	if ($FORM{'mae'} ne ""){
		$i = $FORM{'pegi_m'}-1;
		--$i;
		while ($i > 0){
#$loop_err++;if($loop_err > 200){err("loop_err 1");} #test
			(@kiroku2) = split(/<>/ ,$kiroku[$i]);
			if ($kiroku2[1] eq ""){
				++$loop;
			}
			--$i;
			if ($loop > $pegi_limit){last;}
		}
		if ($i < 0){$i = 0;}

		$stato_point = $i;
		$loop = $stato_point;
	}
	if ($loop eq ""){$loop = 0;}
	@kiroku2 = ();

	while ($loop <= $end_loop && $loop >= 0){
#$loop_err++;if($loop_err > 200){err("loop_err 2");} #test
		($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/ ,$kiroku[$loop]);
		(@kiroku2) = split(/<>/ ,$kiroku[$loop+1]);
		$disp_in = "";
#koko2007/05/13

		if ($naisyo_kyka == 1 && $naisyo ne '' && !$COOKIE{'pasbbs'}){ #$count2
			$naisyo = '';
		}elsif($naisyo){
			$disp_in = "内緒話";

			if(!($naisyo_kyka == 1 && $naisyo eq $COOKIE{'name'} || $name eq $COOKIE{'name'} || $COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'})){
				$name = '内緒話';
				$title = '内緒話';
				$comento = '内緒話';
			}
		}

		if ($kanri ==1 && $cheku ne 'cheku' && $COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'}){
			$disp_in .= " $host_m<br><input type=\"submit\" name=\"kanri_ck\" value=\"許可\">";
		}elsif($kanri ==1 && $cheku ne 'cheku'){
			$name = 'チェック待ち';
			$title = 'チェック待ち';
			$comento = 'チェック待ち';
		}
#kokoend
		if ($COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'}){

			$disp_in .= "<input type=\"submit\" name=\"edit\" value=\"編集\"><input type=\"submit\" name=\"acusesu\" value=\"アク禁\"><input type=\"submit\" name=\"ouna_del\" value=\"記事削除\">";#koko2006/06/21
		}
		#kokoend2006/03/28
		print "<form action=\"$this_cgi\" method=\"POST\">\n";
		print "<input type=\"hidden\" name=\"nomba0\" value=\"$loop\">\n";
		print "<input type=\"hidden\" name=\"acses_p\" value=\"$host_m\">\n";
		if ($count2 eq ""){
			print "<table border=\"0\" bgcolor=\"$table_bac\" align=\"center\" width=\"$waido\" class=\"t_class\" cellspacing=\"0\">\n";
		}else {
			print "<tr><td colspan=\"2\"><hr align=\"center\"></td></tr>\n";
		}
		print "<tr><td bgcolor=\"$taitol_bgcolor\">$count $title</td><td align=\"right\" bgcolor=\"$taitol_bgcolor\">$disp_in<input type=\"submit\" name=\"res\" value=\"Res\"></td></tr>\n";

		print "<tr><td bgcolor=\"$name_bgcolor\">$name</td>";
#koko2006/03/29
		if ($new_time > 0 && $count3+$new_time*60*60 > time){
			print "<td align=\"right\" bgcolor=\"$name_bgcolor\"><font color=\"#ff0000\">New $jikan</font></td></tr>\n";
		}else{
			print "<td align=\"right\" bgcolor=\"$name_bgcolor\">$jikan</td></tr>\n";
		}
#kokoend2006/03/29 koko2007/05/06 koko2007/05/16
		$font_family = "";
		if ($zenkaku_kuuhaku != 0){
			$zenkaku = "　" x $zenkaku_kuuhaku;
			if ($comento =~ m/$zenkaku/ ){
				$font_family = "font-family: monospace;";
			}
		}

		print "<tr><td colspan=\"2\" bgcolor=\"$comento_bgcolor\" style=\"word-break: break-all $font_family font-size: small;\">";
		if ($comento =~ m/\t/ ){
			$mijisu = 0;
			$moji_over = "";
			(@coment) = split(/<br>/,$comento);
			foreach (@coment){
				$mijisu = length $_;
				if ($tab_chengi < $mijisu){ #文字数指定
					$moji_over = "yes";
					last;
				}
			}
			$disp_pm = "<font color=\"$comento_bgcolor\">Tab </font>";
			if ($moji_over eq "yes"){
				$comento =~ s/\t/$disp_pm/g;
				$com_x = $comento;
			}else{
				$comento =~ s/<br>/\n/g;
				$com_x = "<pre>$comento</pre>";
			}
		}else{
			$com_x = $comento;
		}
		$comento = $com_x;

		print "$comento";
		print "</td></tr>\n";
#kokoend2007/05/06
		print "</form>\n";
		if ($kiroku2[1] eq ""){
			++$loop1;
			print "</table>\n";
			print "<hr width=\"$waido\" align=\"center\">\n";
		}

		$restato_point = $loop;
		++$loop;
		if ($loop1 >= $pegi_limit){last;}
	}

	print <<EOF ;
<table border="0" align="center">
<form action="$this_cgi" method="POST">
<input type="hidden" name="pegi_m" value="$stato_point">
<input type="hidden" name="pegi_a" value="$restato_point">
<tr><td><input type="submit" value="前へ" name="mae"></td>
</form>

<div align="center">
<form action="$this_cgi" method="POST">
<input type="hidden" name="pegi_m" value="$stato_point">
<input type="hidden" name="pegi_a" value="$restato_point">
<td><input type="submit" value="次へ" name="ato"></td>
</form>

<form action="$this_cgi" method="POST">
<td>　　削除 No:<input type="text" name="nob" size="6" maxlength="6">
 削除パス:<input type="text" name="pasbbs" size="20" maxlength="10">
<input type="submit" value="削除" name="del"></td></tr>
</form>
</table>
EOF

	&footer;	# フッターを呼び出し
}
### 管理者編集 #####koko2006/06/21
sub edit{
	if ($FORM{'nomba0'} ne "" && $COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'}){ 
		($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/ ,$kiroku[$FORM{'nomba0'}]);

		if ($FORM{'comento'} ne ""){
			$kiroku[$FORM{'nomba0'}] = "$count<>$count2<>$count3<>$name<>$pasbbs<>$FORM{'title'}<>$FORM{'comento'}<>$jikan<>$cheku<>$naisyo<>$host_m<>\n";
			open (COUNT, ">$bbs_kiroku") or &err2("エラー・ファイルが開けません2");
			eval{ flock (COUNT, 2); };
			print COUNT "$cout_ima0\n";
			print COUNT @kiroku ;
			close (COUNT);

			$title = $FORM{'title'};
			$comento = $FORM{'comento'};
		}

#$comento
		$comento0 = $comento;
		$comento0 =~ s/<br>/\n/g;
		$comento0 =~ s/&lt;/</g;
		$comento0 =~ s/&gt;/>/g;
		$comento0 =~ s/&quot;/"/g;
		$comento0 =~ s/&amp;/&/g;

		&header;	# ヘッダーを呼び出し

		print <<EOF ;
<table border="0" align="center" width="$waido" class="t_class" cellspacing="0">
<tr><td bgcolor="$taitol_bgcolor">$count $title</td><td align="right" bgcolor="$taitol_bgcolor"></td></tr>
<tr><td bgcolor="$name_bgcolor">$name</td><td align="right" bgcolor="$name_bgcolor">$jikan</td></tr>
<tr><td colspan=\"2\" bgcolor="$comento_bgcolor">$comento</td></tr>
</table>
<hr width="$waido" align="center">
<table border="2" align="center" width="$waido" class=\"t_class\">
<form action="$this_cgi" method="POST">
<input type="hidden" name="nomba0" value="$FORM{'nomba0'}">
<tr><td>名前：</td><td><input type="text" name="name" size="40" value="$name" maxlength="20">
<tr><td>題名：</td><td><input type="text" name="title" size="60" value="$title" maxlength="30"></td></tr>
<tr><td>内容：</td><td><textarea name="comento" cols="60" rows="6">$comento0</textarea></td></tr>
<tr><td></td>
<td><input type="submit" name="edit" value="編集"> <input type="reset" value="リセット">
</td></tr>
</table>
</form>
<hr width="$waido" align="center">
<form action="$this_cgi" method="POST">
<div align="center"><input type="submit" value="編集終了"></div>
</form>
EOF

		&footer;	# フッターを呼び出し
	} else {
		return;
	}
	exit;
}
### 書込処理 ###########
sub kakicomi{
	if ($FORM{'name'} eq "" || $FORM{'comento'} eq ""){return;}
	if ($FORM{'title'} eq ""){$FORM{'title'} = "(^_^;A";}

	($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/, $kiroku[$FORM{'nomba'}]);
#	if ($FORM{'nomba'} ne ""){
#		(@kiroku1) = split(/<>/, $kiroku[$FORM{'nomba'}+1]);
#	}

#koko2007/02/01
	if ($eiginomi_dame){
		if ($FORM{'comento'} !~ m/[\x80-\xff]/){
			&err2("I refuse contribution.");
		}
	}
#kokoend
	#-------------------------------------------------
	#  URLチェック　koko2007/04/10
	#-------------------------------------------------
	$i=0;
	while ($FORM{'comento'} =~ m/https?\:[\w\.\~\-\/\?\+\=\:\@\%\;\#\%]/g){ #　&が変になる
		$i++;
		if ($i > $mach){
			&err2("Over of URL.");
		}
	}
#kokoend

	if ($FORM{'comento'} eq $koment_bak){return;}
	++ $cout_ima ;
	$count = $cout_ima;
	if ($FORM{'nomba'} eq ""){
		$count2 = "";
	}else{
		++$count2;
		$FORM{'title'} ="Re$count2：".$FORM{'title'};
		
	}
	&get_time;
	$cheku = "";
	$coun3 = time;	#koko2006/03/29 new　などの新着用
	$kakikomi = "$count<>$count2<>$coun3<>$FORM{'name'}<>$FORM{'pasbbs'}<>$FORM{'title'}<>$FORM{'comento'}<>$jikan<>$cheku<>$FORM{'naisyobanashi'}<>$host<><br>\n";

	#koko2006/03/29
	if ($suredolimit != 0){
		if ($FORM{'nomba'} ne ""){
			$i= $FORM{'nomba'};
			while(1){
#$loop_err++;if($loop_err > 200){err("loop_err 1");} #test
				(@kiroku0) = split(/<>/, $kiroku[$i]);
				if ($kiroku0[1] eq ""){last;}
				--$i;
			}
			while(1){
#$loop_err++;if($loop_err > 200){err("loop_err 2");} #test
				(@kiroku0) = split(/<>/, $kiroku[$i]);
				if ($kiroku0[0] ne ""){
					push @up_sledo,$kiroku[$i];
		#		splice @kiroku,$i,1;
					$i++;
					(@kiroku0) = split(/<>/, $kiroku[$i]);
					if ($kiroku0[1] eq ""){last;}
				}
			}
$test ="$#up_sledo";
			if ($#up_sledo +1 >= $suredolimit){
				&err2("エラー・新しくスレッドを作ってください。<br><a href=\"$this_cgi\">スタート</a>");
			}
		}
	}
	if ($FORM{'res2'}){splice @kiroku,$FORM{'nomba'}+1,0,$kakikomi;}
	@up_sledo = ();

	if($FORM{'nomba'} ne ""){
		if ($res_up == 1){
			$i= $FORM{'nomba'};
			while(1){
$loop_err++;if($loop_err > 200){err("loop_err 2");} #test
				($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/, $kiroku[$i]);
				if ($count2 eq ""){last;}
				--$i;
			}
			while(1){
$loop_err++;if($loop_err > 200){err("loop_err 2");} #test
				($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/, $kiroku[$i]);
				push @up_sledo,$kiroku[$i];
				splice @kiroku,$i,1;
				($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/, $kiroku[$i]);
				if ($count2 eq ""){last;}
			}
			@kiroku =(@up_sledo,@kiroku);
		}
	}else{
		unshift (@kiroku ,$kakikomi);
	}

	$i = 0;
	$ii = 0;
	foreach (@kiroku){
		(@kiroku1) = split(/<>/);
		if ($kiroku1[1] eq ""){
			$ii++;
			if ($ii > $suredosu){last;}
		}
		++$i;
	}

	$#kiroku = --$i;

	open (COUNT, ">$bbs_kiroku") or &err2("エラー・ファイルが開けません2");
	eval{ flock (COUNT, 2); };
	print COUNT "$cout_ima<>$FORM{'comento'}<>\n";
	print COUNT @kiroku ;
	close (COUNT);
}
### オーナー削除 ######　koko2006/03/28
sub ohua_del{
	if ($FORM{'nomba0'} ne "" && $COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'}){ 
		(@kiroku0) = split(/<>/ ,$kiroku[$FORM{'nomba0'}]);
		(@kiroku1) = split(/<>/ ,$kiroku[$FORM{'nomba0'}+1]);

		if ($kiroku0[1] eq ""){
			if ($kiroku1[1] eq ""){
				splice @kiroku,$FORM{'nomba0'},1;
			}else{
				($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/ ,$kiroku[$FORM{'nomba0'}]);
				$kiroku[$FORM{'nomba0'}] = "$count<>$count2<>$count3<>削除<>削除<>削除<>削除<>$jikan<>$cheku<>$naisyo<>$host_m<><br>\n";
			}
		}else{
			splice @kiroku,$FORM{'nomba0'},1;
		}
		open (COUNT, ">$bbs_kiroku") or &err2("エラー・ファイルが開けません2");
		eval{ flock (COUNT, 2); };
		print COUNT "$cout_ima<>$FORM{'comento'}<>\n";
		print COUNT @kiroku ;
		close (COUNT);
	}
#	$FORM{'nomba0'} = "";
}
#kokoend 2006/03/28
### 管理チェック ######
sub kanri{
	$i = 0;
	if ($FORM{'nomba0'} ne "" && $COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'}){ 
		($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/ ,$kiroku[$FORM{'nomba0'}]);
		$cheku = 'cheku';
		$kiroku[$FORM{'nomba0'}] = "$count<>$count2<>$count3<>$name<>$pasbbs<>$title<>$comento<>$jikan<>$cheku<>$naisyo<>$host_m<><br>\n";
		open (COUNT, ">$bbs_kiroku") or &err2("エラー・ファイルが開けません3");
		eval{ flock (COUNT, 2); };
		print COUNT "$cout_ima0\n";
		print COUNT @kiroku ;
		close (COUNT);
	}
}

### 簡易削除 #####
sub dele{
	$i = 0;
	if ($FORM{'nob'} ne "" && $FORM{'pasbbs'} ne ""){ 
		foreach (@kiroku){
			($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/);
			if ($count eq $FORM{'nob'} && $ona_pas eq $FORM{'pasbbs'}){
				$del_kaki = $i;
				last;
			}
			if ($count eq $FORM{'nob'} && $pasbbs eq $FORM{'pasbbs'}){
				$del_kaki = $i;
				last;
			}
			++$i;
		}
		if ($del_kaki ne ""){
			($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/, $kiroku[$del_kaki]);
			$kiroku[$del_kaki] = "$count<>$count2<>$count3<>削除<>削除<>削除<>削除<>$jikan<>$cheku<>$naisyo<>$host_m<><br>\n";
			open (COUNT, ">$bbs_kiroku") or &err2("エラー・ファイルが開けません3");
			eval{ flock (COUNT, 2); };
			print COUNT "$cout_ima0\n";
			print COUNT @kiroku ;
			close (COUNT);
		}
	}
}

### 返信書込 #####
sub res_modo{
	&header;	# ヘッダーを呼び出し
	($count,$count2,$count3,$name,$pasbbs,$title,$comento,$jikan,$cheku,$naisyo,$host_m) = split(/<>/, $kiroku[$FORM{'nomba0'}]);
	$name0 = $name;
	$count2++;
	if ($cheku ne 'cheku'){	#koko2007/05/14
		$name = 'チェック待ち';
		$title = 'チェック待ち';
		$comento = 'チェック待ち';
	}
	if (!($naisyo eq "" || $naisyo eq $COOKIE{'name'}||$name eq $COOKIE{'name'}|| $COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'})){
		$name = '内緒話';
		$title = '内緒話';
		$comento = '内緒話';
	}

	print "<table border=\"0\" align=\"center\" width=\"$waido\" class=\"t_class\">\n";
	print "<tr><td>$count $title</td><td align=\"right\"></td></tr>\n";
	print "<tr><td>$name</td><td align=\"right\">$jikan</td></tr>\n";
	print "<tr><td colspan=\"2\">$comento</td></tr>\n";
#	print "</form>\n"; #koko2007/05/14
	print "</table>\n";


	$nomba = $FORM{'nomba0'};
	$toukou = 'res2';
	&form;
	print "<a href=\"$this_cgi\">もどる</a></div>\n";
	&footer;	# フッターを呼び出し
}

### フォーム作成 #######
sub form{
	if ($count2){
		if ($naisyo){
			if ($naisyo eq 'naisyo'){
				$naisyo = $name0;
			}
			$chec = " checked";
		}
	}else{
		$naisyo = 'naisyo';
	}
	if ($COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'}){
		$disp_kanre = "<input type=\"checkbox\" name=\"kanri\" value =\"on\">管理モード";
	}
 #koko2007/05/14
	print <<EOF ;
<table border="2" align="center" width="$waido" class=\"t_class\">
<form action="$this_cgi" method="POST">
<input type="hidden" name="nomba" value="$nomba">
<tr><td>名前：</td><td><input type="text" name="name" size="40" value="$COOKIE{'name'}" maxlength="20">
消去用パス：<input type="password" name="pasbbs" size="20" value="$COOKIE{'pasbbs'}" maxlength="10">
<input type="checkbox" name="naisyobanashi" value="$naisyo"$chec>内緒話(パス要す)
</td></tr>
<tr><td>題名：</td><td><input type="text" name="title" size="60" maxlength="30">
$disp_kanre
</td></tr>
<tr><td>内容：</td><td><textarea name="comento" cols="60" rows="6"></textarea></td></tr>
<tr><td></td>
<td><input type="submit" name="$toukou" value="送信する"> <input type="reset" value="リセット">
　　クッキークリア<input type="checkbox" name="coodel" value="clia">
</td></tr>
</table>
</form>
<hr width="$waido" align="center">
EOF
}

### フォーム受信 ##########
sub loadformdata {
	my ($query,$pair);
	if($ENV{'REQUEST_METHOD'} eq 'POST') {
		read(STDIN, $query, $ENV{'CONTENT_LENGTH'});
	} else {
		$query = $ENV{'QUERY_STRING'};
		if ($get_no ==1 && $query ne ""){&err2("エラー・GET 禁止");}
	}
	my ($saizu)=length $query;
	if ($saizu > $max_size){&err2("エラー・サイズオーバー");}
	
	foreach $pair (split(/&/, $query)) {
		my ($key, $value) = split(/=/, $pair);
	
	# 文字のデコード
		$value =~ tr/+/ /;
		$value =~ s/%([0-9a-fA-F][0-9a-fA-F])/chr(hex($1))/eg;
	
#		$value = jcode::euc($value);	# euc? sjis? jcode.plが必要
		$value =~ s/&/&amp;/g;
		$value =~ s/</&lt;/g;
		$value =~ s/>/&gt;/g;
		$value =~ s/"/&quot;/g;
		$value =~ s/\x0D\x0A/<br>/g;
	#	$value =~ tr/\t/ /; #2007/05/06
	
		$FORM{$key} = $value;
	}
}

### 現在の時間出し ###############
sub get_time{
	($sec,$min,$hour,$mday,$mon,$year,$wday) = localtime(time) ;	#一括取り入れ
	$year += 1900;	# $year = $year + 1900 と同じ
	++$mon ;
	@youbi=('日','月','火','水','木','金','土');

	$mond = sprintf("%02d",$mon);
	$mdayd = sprintf("%02d",$mday);
	$hourd = sprintf("%02d",$hour);
	$mind = sprintf("%02d",$min);
	$secd = sprintf("%02d",$sec);

	$jikan = "$year年$mond月$mdayd日$youbi[$wday]曜日$hourd時$mind分$secd秒";

#	$countup = 1;

}
### クッキーに値をセット ####
sub set_cookie{
	if ($FORM{'name'} && $FORM{'pasbbs'}){	#書込の時限定。
		if (!$FORM{'coodel'}){
			$COOKIE{'name'} = $FORM{'name'};
			$COOKIE{'pasbbs'}  = $FORM{'pasbbs'};
		}
	}
}
### クッキー読み出し ######
sub load_cookie{
	my	($pair, $cpair);
	
	foreach $pair (split(/;\s*/, $ENV{'HTTP_COOKIE'})) {
		my	($name, $value) = split(/=/, $pair);
		
		# 単一のクッキー値から%COOKIEにデコード
		if($name eq $COOKIE_NAME) {
			foreach $cpair (split(/&/, $value)) {
				my	($cname, $cvalue) = split(/#/, $cpair);
				
				$cvalue =~ s/%([0-9a-fA-F][0-9a-fA-F])/chr(hex($1))/eg;
				$COOKIE{$cname} = $cvalue;
			}
			last;
		}
	}
}

### クッキー発行 ####
sub print_http_header{
	my	(@cpairs, $cname, $cvalue, $value);
	if ($FORM{'coodel'}){$COOKIE_LIFE = -1;}
	# %COOKIEを単一のクッキー値にエンコード
	foreach $cname (keys %COOKIE) {
		$cvalue = $COOKIE{$cname};
		$cvalue =~ s/(\W)/sprintf("%%%02X", ord $1)/eg;
		push @cpairs, "$cname#$cvalue";
	}
	$value = join('&', @cpairs);
	
	# グリニッジ標準時の文字列
	my	@mon_str = qw(Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec);
	my	@wdy_str = qw(Sun Mon Tue Wed Thu Fri Sat);
	my	$life = $COOKIE_LIFE * 24 * 60 * 60;
	my	($sec, $min, $hour, $mday, $mon, $year, $wday) = gmtime(time + $life);
	my	$date = sprintf("%s, %02d-%s-%04d %02d:%02d:%02d GMT",
			$wdy_str[$wday], $mday, $mon_str[$mon], $year + 1900, $hour, $min, $sec);
	
#	print "Content-type: text/html; charset=Shift_JIS\n";
#	print "Set-Cookie: $COOKIE_NAME=$value; expires=$date\n\n";
	print "Set-Cookie: $COOKIE_NAME=$value; expires=$date\n";
}

### ヘッダー #######
sub header{
	print "Content-type:text/html; charset=Shift_JIS\n\n";
	print <<EOF ;
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=Shift_JIS">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>$this_name</title>
<style type="text/css">
<!--
h1{
  color: #ff6600 ;
}
.t_class{
  border: dashed 4px #339933 ;
}
td {
	word-break: break-all; /* koko2007/05/06 */
	}
-->
</style>
</head>
<BODY BGCOLOR="$this_bgcolor" $bak_set>
EOF

	print "<table border=\"0\" width=\"100%\"><tr><td width=\"250\">";
	&counter;
	print "</td><td align=\"center\">";
	print "<div align=\"center\"><img src=\"$this_img\" alt=\"$this_name\"</div>\n";
	print "</td><td width=\"250\"><br></td></tr></table>\n";
	print "<hr width=\"$waido\" align=\"center\">\n";

}

### フッター #########
sub footer{
#------------- 検査スタート ---------------------
# print "test=$kiroku[$FORM{'nomba0'}]<br>";
#-------　フォーム要素名　---------　入力確認用　後で消すこと
# print "<table border='1'>";
# print "<tr><th>フォーム要素名</th><th>データ</th></tr>";
#
# foreach $key (keys %FORM) {
#	print "<tr><th>$key</th><td>$FORM{$key}</td></tr>\n";
# }
# print "</table><br>";
#-------　クッキー要素名　---------
# print "<table border='1'>";
# print "<tr><th>クッキー要素名</th><th>データ</th></tr>";
#
# foreach $key (keys %COOKIE) {
#	print "<tr><th>$key</th><td>$COOKIE{$key}</td></tr>\n";
# }
# print "</table><br>";
#----------------------------------
# print @kiroku;
#------------ ここまで検査用 ----------------------
	print <<EOF ;
<div align="center">$modoru<div>
<div align="right">Edit:ＢＢＳ(たっちゃん)<br>
Ver1.16</div>
<!-- <hr width="$waido" align="center"> -->
</BODY>
</html>
EOF

	exit;
}
### エラーの時 #######
sub err2 {
#	print "Content-type:text/html; charset=Shift_JIS\n\n";
	print <<EOF ;
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=Shift_JIS">
<meta http-equiv="Content-Style-Type" content="text/css">
<title>$this_name</title>
</head>
<BODY BGCOLOR="$this_bgcolor" $bak_set>
EOF

	print "<h2 align=\"center\">";
	print "$_[0]\n";
	print "</h2>\n";
	print <<EOF ;
<div align="right">Edit:ＢＢＳ(たっちゃん)<br>
Ver1.16</div>
</BODY>
</html>
EOF

	exit;
}

### 禁止ホスト設定
sub acusesu {
	open (REDDAT, "$kinshiHostFaill") or &err2("Open Error: $kinshiHostFaill");
	eval{ flock (REDDAT, 2); };
	@denyHost = <REDDAT>;
	close (REDDAT);

	unshift @denyHost," $FORM{'acses_p'}";

	open (REDDAT, "> $kinshiHostFaill") or &err2("Open Error: $kinshiHostFaill");
	eval{ flock (REDDAT, 2); };
	print REDDAT "@denyHost";
	close (REDDAT);
}

### アクセス禁止リストからの解除
sub a_kanri {
	if (!($COOKIE{'name'} eq $kanre_name && $ona_pas eq $COOKIE{'pasbbs'})){return;}

	open (REDDAT, "$kinshiHostFaill") or &err2("Open Error: $kinshiHostFaill");
	eval{ flock (REDDAT, 2); };
	@denyHost = <REDDAT>;
	close (REDDAT);

	if ($FORM{'botan_no'} ne ""){
		splice @denyHost,$FORM{botan_no}, 1;
		open (DAT, "> $kinshiHostFaill") or &err2("エラー・ファイルが開けません$kinshiHostFaill");
		eval{ flock (DAT, 2); };
		print DAT @denyHost ;
		close (DAT);
	}

	&header;
	print "<div align=\"center\">\n";
	print "■自分のホストを入れると使えなくなります。$kinshiHostFaill　リストを開いて消してください。■<br>\n";
	print "<FORM ACTION=\"$this_cgi\" METHOD=POST>\n";
	print "<input type=\"hidden\" name=\"kanri\" value=\"on\">\n";
	print "<table border='1'>";
	print "<tr><th>選択</th><th>ホスト名</th></tr>\n";
	$loop_index = 0;
	foreach (@denyHost) {
		print "<tr><td><input type=radio name=botan_no value=$loop_index>$loop_index</td><td>$_</td></tr>\n";
		$loop_index++;
	}
	print "</table><br>";
	print "<INPUT TYPE=SUBMIT VALUE=\"削除\">\n";
	print "</form>\n";
	print "<a href=\"$this_cgi\">もどる</a></div>\n";

	&footer;

	exit;
}
#-------------------------------------------------
#  カウンタ処理
#-------------------------------------------------
sub counter {
	local($count,$cntup,@count);
  $loop_err++;if($loop_err > 200){die;} #test

	# 閲覧時のみカウントアップ
#	if ($countup eq '1') { $cntup=1; } else { $cntup=0; }
	$cntup=1;

	if(!-e "$cntfile"){&err2("Open Error: $cntfile");}
	# カウントファイルを読みこみ
	open(IN,"$cntfile") or &err2("Open Error: $cntfile");
	eval {flock(IN, 2);};
	$count = <IN>;
	close(IN);

	# IPチェックとログ破損チェック


#koko2005/10/29
	local($local_time);
	local($cnt, $ip,$kiroku_day,$keika_day,$today,$yestaday) = split(/:/, $count);
#	if ($host eq $ip || $cnt eq "") { $cntup=0; }

	# カウントアップ
	if ($cntup) {

		$local_time = time + (9*60*60);#GMT+9:00補正
		if (!$kiroku_day){
			$kiroku_day = $local_time - ($local_time % (24*60*60));
		}
		if ($local_time - $kiroku_day > 24*60*60){
			$keika_day += int(($local_time - $kiroku_day)/(24*60*60));
			if ($local_time - $kiroku_day > 2*24*60*60){
				$yestaday = 0;
			}else{$yestaday = $today;}
			$kiroku_day = $local_time - ($local_time % (24*60*60));
			$today = 0;
		}
		$today++;
		if (!$keika_day){$keika_day = 0; }
		if (!$yestaday){$yestaday = 0; } 

		$cnt++;
		open(OUT,"> $cntfile") || &err2("Write Error: $cntfile");
		eval {flock(OUT, 2);};
		print OUT "$cnt\:$host:$kiroku_day:$keika_day:$today:$yestaday";
		close(OUT);
		if (-z $cntfile){
			open(OUT,"> $cntfile") || &err2("Write Error: $cntfile");
			eval {flock(OUT, 2);};
			print OUT "$cnt\:$host:$kiroku_day:$keika_day:$today:$yestaday";
			close(OUT);
		}

	}
	# 桁数調整
	while(length($cnt) < $mini_fig) { 
		$cnt = '0' . $cnt;
$loop_err++;if($loop_err > 200){err("loop_err cunt");} #test
	}
	@count = split(//, $cnt);
	print "<TABLE border=\"0\"><TBODY>\n";
	print "<TR><TD rowspan=\"3\">\n";

	# GIFカウンタ表示
	if ($counter == 2) {
		foreach (0 .. $#count) {
			print "<img src=\"$gif_path$count[$_]\.gif\" alt=\"$count[$_]\" width=\"$mini_w\" height=\"$mini_h\">";
		}
	# テキストカウンタ表示
	} else {
		print "<font color=\"$cntCol\" face=\"Verdana,Helvetica,Arial\">$cnt</font><br>\n";
	}
	print "</TD><td><font size=\"2\">経過</font></td><td><font size=\"2\">$keika_day</font></td></TR>\n";
	print "<TR><td><font size=\"2\">今日</font></td><td><font size=\"2\">$today</font></td></TR>\n";
	print "<TR><td><font size=\"2\">昨日</font></td><td><font size=\"2\">$yestaday</font></td></TR>\n";
	print "</TBODY></TABLE><br>\n";
#kokoend
}

### 終わり。 #########
__END__
                                                                                                                                                                                                                                                                                                                                             