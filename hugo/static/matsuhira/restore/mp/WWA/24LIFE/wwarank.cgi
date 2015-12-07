#!/usr/local/bin/perl
# ↑自分がページを置くプロバイダのPerlが置かれているパスを指定。

###################################
###                             ###
### WWA Ranking System Ver 1.15 ###
###                             ###
###################################

# このスクリプトはフリーです。再配布も自由に行って下さい。
# 但し、このスクリプトによる被害などは一切保証しません。

#######################################################################################
# このスクリプトはJya!さんが作成したものをNAOが改変してセキュリティを強化したものです。
#######################################################################################

# 設定 ------------------------------------------------------------------------

$title    = 'ダクラのランキング表（仮）';	# タイトル
$c_name   = 'wwarank';			# クッキー名
$rankfile = 'ranking.txt';		# ランキングを保存するファイル名
$this_fn  = 'wwarank.cgi';		# このファイルのファイル名
$max_rank = 50;				# 何位までランクするか（0にすれば全てランク）

$text     = '#000000';		# 文字色
$titlec   = '#0099ff';		# タイトル色
$bgcolor  = '#9999cc';		# 背景色
$link     = '#3399ff';		# リンク色
$vlink    = '#339900';		# リンク済みの色
$alink    = '#ff0000';		# リンクをクリックした時の色
$tfcolor  = '#eeeeee';		# テーブルのフレーム色
$tbgcolor = '#ffffff';		# テーブルの背景色
$plcolor  = '#ff0000';		# ランキング一覧の時のプレイヤーカラー

$hpx      = 10;			# 生命力の倍率
$atx      = 5;			# 攻撃力の倍率
$dfx      = 0;			# 防御力の倍率
$gdx      = 1;			# ゴールドの倍率
$st       = 10;			# スコアの最大桁数

$hpx      = 10;			# 生命力の倍率
$atx      = 5;			# 攻撃力の倍率
$dfx      = 0;			# 防御力の倍率
$gdx      = 1;			# ゴールドの倍率
$st       = 10;			# スコアの最大桁数

$home_url = "http://hirarira.hp.infoseek.co.jp/24LIFE/24LIFE.html";		# [HOME]をクリックして戻るURL

###################################################################
# $idcodeにはマップ作成ツールで入力した暗証番号を入力してください。
# （暗証番号を入力しなかった場合は、0のまま）
# CGIを直接呼び出してのスコア登録を防ぎます。
# 暗証番号はなるべく設定しておくようにしてください。
###################################################################
$idcode   = 7470;			# ID番号

##################################################################
# $randomには適当な３桁程度の数字を入力してください。
# これによりHTMLを変更してのスコア改ざんができないようになります。
##################################################################
$random   = 731;		# 乱数作成用

##################################################################
# 各ステータスに上限値を定めることができます。（0なら無指定）
# 改造ツールなどが使用された場合は、
# スコア999999など無茶な値になることが多いので、
# なるべく設定しておくことをおすすめします。
##################################################################
$max_SCORE  = 0;		# スコア上限値
$max_HP     = 0;		# 生命力上限値
$max_AT     = 0;		# 攻撃力上限値
$max_DF     = 0;		# 防御力上限値
$max_GD     = 0;		# ゴールド上限値



# 設定はここまで --------------------------------------------------------------

&decode;
if ($FORM{'mode'} eq 'rank'){ &ranking; }
elsif ($FORM{'mode'} eq 'write'){ &regist; }
else { &post_form; }
&html_foot;
exit;

# ------- ランキング出力 -------
sub ranking{
	if (!open(NOTE,"$rankfile")) { &error('ランキングファイルが見つかりません<br>code 52'); }
	@RANKING = <NOTE>;
	close(NOTE);
	&html_head;
	print "<table border>\n";
	print "<tr><th><NOBR>順位</NOBR></th>\n";
	print "<th><NOBR>スコア</NOBR></th>\n";
	print "<th>時刻</th>\n";
	print "<th>名前</th>\n";
	print "<th>各種情報</th></tr>\n";
	$rank = 1;
	$mxsc = 9 x $st;
	foreach $p_data (@RANKING){
		($r_score,$r_date,$r_name,$r_pass,$r_extra) = split(/\,/,$p_data);
		$r_score = $mxsc - $r_score;
		if( $r_pass eq $ENV{'REMOTE_ADDR'} ){
			$cl = $plcolor;
		}else{
			$cl = $text;
		}
		print "<tr><td align=right><font color=$cl><NOBR>$rank位</NOBR></font></td>\n";
		print "<td align=right><font color=$cl><NOBR>$r_score</NOBR></font></td>\n";
		print "<td align=center><font color=$cl><NOBR>$r_date</NOBR></font></td>\n";
		print "<td align=left><font color=$cl><NOBR>$r_name</NOBR></font></td>\n";
		print "<td align=left><font color=$cl><NOBR>$r_extra</NOBR></font></td></tr>\n";
		$rank++;
	}
	print "</table>\n";
}

# ------- スコア書き込み -------
sub regist{
	if( $FORM{'name'} eq "" ){&error('名前が入力されていません<br>code 87');}
	if( (index $FORM{'name'},",") >= 0 ){&error('名前にカンマ文字は使えません<br>code 90');}
	if( (length $FORM{'name'}) > 20 ){&error('名前の文字数が多すぎます<br>code 91');}
	if( $FORM{'score'} == 0 ){ &error('スコア０では記録できません<br>code 96');}
	if( (($FORM{'score'} >= $max_SCORE) && ($max_SCORE > 0))
			|| (($FORM{'HP'} >= $max_HP) && ($max_HP > 0))
				|| (($FORM{'AT'} >= $max_AT) && ($max_AT > 0))
					|| (($FORM{'DF'} >= $max_DF) && ($max_DF > 0))
						|| (($FORM{'GD'} >= $max_GD) && ($max_GD > 0)) ){
		&error('ステータスに異常があります<br>code 95');
	}
	$ident = $FORM{'score'} *($random) + $FORM{'HP'} *($random +7) + $FORM{'AT'} *($random +17) + $FORM{'DF'} *($random +41) + $FORM{'GD'} *($random +3);
	$ident = $ident %9999;
	if( $FORM{'ident'} != "$ident" ){ &error('データが改ざんされています<br>code 89'); }
	
	$mxsc = 9 x $st;
	$FORM{'score'} = $mxsc - $FORM{'score'};
	$FORM{'score'} = substr (0 x $st . $FORM{'score'},-$st);
	if (!open(NOTE,"$rankfile")){&error('ランキングファイルが見つかりません<br>code 94');}
	@RANKING = <NOTE>;
	close(NOTE);
	$w_msg = 'スコアを確認しました';
	$i = 0;
	$found = 0;
	$write = 1;
	foreach $p_data (@RANKING){
		($r_score,$r_date,$r_name,$r_pass,$r_extra) = split(/\,/,$p_data);
		if( ($r_pass eq $ENV{'REMOTE_ADDR'}) || ($r_name eq $FORM{'name'}) ){
			if( $r_score gt $FORM{'score'} ){
				splice(@RANKING, $i, 1);
				$w_msg = 'スコアを更新しました';
			}else{
				$w_msg = '前回のスコアを更新できませんでした';
				$write = 0;
			}
			next;
		}
		$i++;
	}
	$w_extra = "HP: $FORM{'HP'} AT: $FORM{'AT'} DF: $FORM{'DF'} Gold: $FORM{'GD'} Host: $ENV{'REMOTE_ADDR'}";
	$w_data = "$FORM{'score'}\,$FORM{'date'}\,$FORM{'name'}\,$ENV{'REMOTE_ADDR'}\,$w_extra\n";
	push(@RANKING,$w_data);
	@RANKING = sort(@RANKING);
	if (@RANKING >$max_rank && $max_rank != 0){
		if ($RANKING[$#RANKING] eq $w_data){
			$write = 0;
			$w_msg = "$max_rank位以内に入れませんでした";
		}else{
			pop(@RANKING);
		}
	}
	if ($write == 1){
		if (!open(NOTE,">$rankfile")){&error('ランキングファイルが見つかりません<br>code 128');}
		print NOTE @RANKING;
		close(NOTE);
	}
	&html_head;
	print "<font size=6>$w_msg</font><br>\n";
	if ($write == 1){print "記録します<br>\n";}
	print "<br>\n<a href=\"$this_fn?mode=rank\">ランキングを見る</a>\n";
}


# ------- FORM作成 -------
sub post_form{
	$idNumber = $FORM{'HP'} *7 +$FORM{'AT'} *11 +$FORM{'DF'} *19 +$FORM{'GD'} *5 +$FORM{'PX'} *17 +$FORM{'PY'} *21;
	for( $i = 1 ; $i <= 12 ; ++$i ){
		$elename = "ITEM$i";
		$idNumber += $FORM{$elename} *17;
	}
	$idNumber = $idNumber %9999;
	if( $idcode != 0 ){ $idNumber *= $idcode; }
	$idNumber = $idNumber %9999;
	if( $FORM{'ID'} != $idNumber ){ &error('データが改ざんされています<br>または暗証番号が違っています<br>code 90'); }
	
	$score = $FORM{'HP'} * $hpx + $FORM{'AT'} * $atx + $FORM{'DF'} * $dfx + $FORM{'GD'} * $gdx;
	$ident = $score * $random + $FORM{'HP'} * ($random +7) + $FORM{'AT'} * ($random +17) + $FORM{'DF'} * ($random +41) + $FORM{'GD'} * ($random +3);
	$ident = $ident %9999;
	&get_date;
	&html_head;
	print<<"EOF";
<form action="$this_fn" method=POST>
あなたのスコアは<font size=7> $score </font>点です。
<hr>
<input type="hidden" name="mode" value="write">
<input type="hidden" name="date" value="$date">
<input type="hidden" name="score" value="$score">
<input type="hidden" name="HP" value="$FORM{'HP'}">
<input type="hidden" name="AT" value="$FORM{'AT'}">
<input type="hidden" name="DF" value="$FORM{'DF'}">
<input type="hidden" name="GD" value="$FORM{'GD'}">
<input type="hidden" name="ident" value="$ident">

お名前：<br>
<input type="text" size="20" name="name" maxlength="20"><br>
<hr>
<input type="submit" value="書き込む">
</form>
EOF
}

# ------- 日付の取得 -------
sub get_date{
	$ENV{'TZ'} = "JST-9";
	local($sec,$min,$hour,$day,$mon,$year) = localtime();
	$year += 1900;
	$mon++;
	if ($sec < 10)  { $sec  = "0$sec";  }
	if ($min < 10)  { $min  = "0$min";  }
	if ($hour < 10) { $hour = "0$hour"; }
	if ($mon < 10)  { $mon  = "0$mon";  }
	if ($day < 10)  { $day  = "0$day";  }
	$date = "$year/$mon/$day $hour:$min:$sec";
}

# ------- デコード -------
sub decode{
	$ENV{'REQUEST_METHOD'} =~ tr/a-z/A-Z/;
	if ($ENV{'REQUEST_METHOD'} eq "POST") {
		read(STDIN,$buffer,$ENV{'CONTENT_LENGTH'});
	}else{
		$buffer = $ENV{'QUERY_STRING'};
	}
	@pairs = split(/&/,$buffer);
	foreach $pair(@pairs){
		($name,$value) = split(/=/,$pair);
		$value =~tr/+/ /;
		$value =~s/%(..)/pack("C",hex($1))/eg;
		&convert(*value,'sjis');
		$FORM{$name} = $value;
	}
}

# ------- エラー処理 -------
sub error{
	&html_head;
	$msg = $_[0];
	print "<font size=7 color=red>Error!</font>\n";
	print "<hr>$msg<hr>\n";
	&html_foot;
	exit;
}

# ------- HTML（ヘッダ部） -------
sub html_head{
	print<<"EOF";
Content-type: text/html

<html>
<head><title>$title</title></head>

<body text="$text" bgcolor="$bgcolor" link="$link" vlink="$vlink" alink="$alink">

<center>
<table border=2 cellspacing=5 bgcolor="$tfcolor">
<th bgcolor="$tbgcolor"><font size=6 color="$titlec">$title</font></th>
</table>
</center>

<table bgcolor="$tfcolor" border><td>
[<a href="$home_url">HOME</a>]
</td></table>

<hr>
<center>
<table border=2 cellpadding=5 cellspacing=3 bgcolor="$tfcolor">
<td bgcolor="$tbgcolor" align="center">
EOF
}

# ------- HTML（フッタ部） -------
sub html_foot{
	print<<"EOF";
</td></table>
</center>
<br><hr>
<table bgcolor="#eeeeee" border align=center><td><address>
WWA Ranking System ( Writtened by Jya! Customized by NAO )
</address></td></table>

</body></html>
EOF
}





;######################################################################
;#
;# jcode.pl: Japanese character code conversion library
;#
;# Copyright (c) 1991,1992 Software Research Associates, Inc.
;#	Original by srekcah@sra.co.jp, Feb 1992
;#
;#	Maintained by Kazumasa Utashiro <utashiro@sra.co.jp>
;#	Software Research Associates, Inc., Japan
;#
;; $rcsid = q$Id: jcode.pl,v 1.9 1994/02/14 06:16:29 utashiro Exp $;
;#
;######################################################################

sub init {
    ($version) = ($rcsid =~ /,v ([\d.]+)/);
    $re_sjis_c = '[\201-\237\340-\374][\100-\176\200-\374]';
    $re_sjis_s = "($re_sjis_c)+";
    $re_euc_c  = '[\241-\376][\241-\376]';
    $re_euc_s  = "($re_euc_c)+";
    $re_jin    = '\033\$[\@B]';
    $re_jout   = '\033\([BJ]';
    &jis_inout("\033\$B", "\033(B");

    for $from ('jis', 'sjis', 'euc') {
	for $to ('jis', 'sjis', 'euc') {
	    eval "\$convf{$from, $to} = *${from}2${to};";
	}
    }
}

sub jis_inout {
    $jin = shift || $jin;
    $jout = shift || $jout;
    $jin = "\033\$".$jin if length($jin) == 1;
    $jout = "\033\(".$jout if length($jout) == 1;
    ($jin, $jout);
}

sub get_inout {
    local($jin, $jout);
    $_[$[] =~ /$re_jin/o && ($jin = $&);
    $_[$[] =~ /$re_jout/o && ($jout = $&);
    ($jin, $jout);
}

sub getcode {
    local(*_) = @_;
    return undef unless /[\033\200-\377]/;
    return 'jis' if /$re_jin|$re_jout/o;

    local($sjis, $euc);
    $sjis += length($&) while /$re_sjis_s/go;
    $euc  += length($&) while /$re_euc_s/go;
    (&max($sjis, $euc), ('euc', undef, 'sjis')[($sjis<=>$euc) + $[ + 1]);
}
sub max { $_[ $[ + ($_[$[] < $_[$[+1]) ]; }

sub convert {
	&init;
    local(*_, $ocode, $icode) = @_;
    return (undef, undef) unless $icode = $icode || &getcode(*_);
    $ocode = 'jis' unless $ocode;
    local(*convf) = $convf{$icode, $ocode};
    do convf(*_);
    (*convf, $icode);
}

sub jis2jis {
    local(*_) = @_;
    s/$re_jin/$jin/go;
    s/$re_jout/$jout/go;
}

sub sjis2jis {
    local(*_) = @_;
    s/$re_sjis_s/&_sjis2jis($&)/geo;
}
sub _sjis2jis {
    local($_) = @_;
    s/../$s2e{$&}||&s2e($&)/geo;
    tr/\241-\376/\041-\176/;
    $jin . $_ . $jout;
}

sub euc2jis {
    local(*_) = @_;
    s/$re_euc_s/&_euc2jis($&)/geo;
}
sub _euc2jis {
    local($_) = @_;
    tr/\241-\376/\041-\176/;
    $jin . $_ . $jout;
}

sub jis2euc {
    local(*_) = @_;
    s/$re_jin([!-~]*)$re_jout/&_jis2euc($1)/geo;
}
sub _jis2euc {
    local($_) = @_;
    tr/\041-\176/\241-\376/;
    $_;
}

sub jis2sjis {
    local(*_) = @_;
    s/$re_jin([!-~]*)$re_jout/&_jis2sjis($1)/geo;
}
sub _jis2sjis {
    local($_) = @_;
    tr/\041-\176/\241-\376/;
    s/../$e2s{$&}||&e2s($&)/ge;
    $_;
}

sub sjis2euc {
    local(*_) = @_;
    s/$re_sjis_c/$s2e{$&}||&s2e($&)/geo;
}
sub s2e {
    ($c1, $c2) = unpack('CC', $code = shift);
    if ($c2 >= 0x9f) {
	$c1 = $c1 * 2 - ($c1 >= 0xe0 ? 0xe0 : 0x60);
	$c2 += 2;
    } else {
	$c1 = $c1 * 2 - ($c1 >= 0xe0 ? 0xe1 : 0x61);
	$c2 += 0x60 + ($c2 < 0x7f);
    }
    $s2e{$code} = pack('CC', $c1, $c2);
}

sub euc2sjis {
    local(*_) = @_;
    s/$re_euc_c/$e2s{$&}||&e2s($&)/geo;
}
sub e2s {
    ($c1, $c2) = unpack('CC', $code = shift);
    if ($c1 % 2) {
	$c1 = ($c1>>1) + ($c1 < 0xdf ? 0x31 : 0x71);
	$c2 -= 0x60 + ($c2 < 0xe0);
    } else {
	$c1 = ($c1>>1) + ($c1 < 0xdf ? 0x30 : 0x70);
	$c2 -= 2;
    }
    $e2s{$code} = pack('CC', $c1, $c2);
}

sub sjis2sjis { 0; }
sub euc2euc { 0; }

1;
