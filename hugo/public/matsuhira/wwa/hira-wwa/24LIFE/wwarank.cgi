#!/usr/local/bin/perl
# ���������y�[�W��u���v���o�C�_��Perl���u����Ă���p�X���w��B

###################################
###                             ###
### WWA Ranking System Ver 1.15 ###
###                             ###
###################################

# ���̃X�N���v�g�̓t���[�ł��B�Ĕz�z�����R�ɍs���ĉ������B
# �A���A���̃X�N���v�g�ɂ���Q�Ȃǂ͈�ؕۏ؂��܂���B

#######################################################################################
# ���̃X�N���v�g��Jya!���񂪍쐬�������̂�NAO�����ς��ăZ�L�����e�B�������������̂ł��B
#######################################################################################

# �ݒ� ------------------------------------------------------------------------

$title    = '�_�N���̃����L���O�\�i���j';	# �^�C�g��
$c_name   = 'wwarank';			# �N�b�L�[��
$rankfile = 'ranking.txt';		# �����L���O��ۑ�����t�@�C����
$this_fn  = 'wwarank.cgi';		# ���̃t�@�C���̃t�@�C����
$max_rank = 50;				# ���ʂ܂Ń����N���邩�i0�ɂ���ΑS�ă����N�j

$text     = '#000000';		# �����F
$titlec   = '#0099ff';		# �^�C�g���F
$bgcolor  = '#9999cc';		# �w�i�F
$link     = '#3399ff';		# �����N�F
$vlink    = '#339900';		# �����N�ς݂̐F
$alink    = '#ff0000';		# �����N���N���b�N�������̐F
$tfcolor  = '#eeeeee';		# �e�[�u���̃t���[���F
$tbgcolor = '#ffffff';		# �e�[�u���̔w�i�F
$plcolor  = '#ff0000';		# �����L���O�ꗗ�̎��̃v���C���[�J���[

$hpx      = 10;			# �����͂̔{��
$atx      = 5;			# �U���͂̔{��
$dfx      = 0;			# �h��͂̔{��
$gdx      = 1;			# �S�[���h�̔{��
$st       = 10;			# �X�R�A�̍ő包��

$hpx      = 10;			# �����͂̔{��
$atx      = 5;			# �U���͂̔{��
$dfx      = 0;			# �h��͂̔{��
$gdx      = 1;			# �S�[���h�̔{��
$st       = 10;			# �X�R�A�̍ő包��

$home_url = "http://hirarira.hp.infoseek.co.jp/24LIFE/24LIFE.html";		# [HOME]���N���b�N���Ė߂�URL

###################################################################
# $idcode�ɂ̓}�b�v�쐬�c�[���œ��͂����Ïؔԍ�����͂��Ă��������B
# �i�Ïؔԍ�����͂��Ȃ������ꍇ�́A0�̂܂܁j
# CGI�𒼐ڌĂяo���ẴX�R�A�o�^��h���܂��B
# �Ïؔԍ��͂Ȃ�ׂ��ݒ肵�Ă����悤�ɂ��Ă��������B
###################################################################
$idcode   = 7470;			# ID�ԍ�

##################################################################
# $random�ɂ͓K���ȂR�����x�̐�������͂��Ă��������B
# ����ɂ��HTML��ύX���ẴX�R�A�����񂪂ł��Ȃ��悤�ɂȂ�܂��B
##################################################################
$random   = 731;		# �����쐬�p

##################################################################
# �e�X�e�[�^�X�ɏ���l���߂邱�Ƃ��ł��܂��B�i0�Ȃ疳�w��j
# �����c�[���Ȃǂ��g�p���ꂽ�ꍇ�́A
# �X�R�A999999�Ȃǖ����Ȓl�ɂȂ邱�Ƃ������̂ŁA
# �Ȃ�ׂ��ݒ肵�Ă������Ƃ��������߂��܂��B
##################################################################
$max_SCORE  = 0;		# �X�R�A����l
$max_HP     = 0;		# �����͏���l
$max_AT     = 0;		# �U���͏���l
$max_DF     = 0;		# �h��͏���l
$max_GD     = 0;		# �S�[���h����l



# �ݒ�͂����܂� --------------------------------------------------------------

&decode;
if ($FORM{'mode'} eq 'rank'){ &ranking; }
elsif ($FORM{'mode'} eq 'write'){ &regist; }
else { &post_form; }
&html_foot;
exit;

# ------- �����L���O�o�� -------
sub ranking{
	if (!open(NOTE,"$rankfile")) { &error('�����L���O�t�@�C����������܂���<br>code 52'); }
	@RANKING = <NOTE>;
	close(NOTE);
	&html_head;
	print "<table border>\n";
	print "<tr><th><NOBR>����</NOBR></th>\n";
	print "<th><NOBR>�X�R�A</NOBR></th>\n";
	print "<th>����</th>\n";
	print "<th>���O</th>\n";
	print "<th>�e����</th></tr>\n";
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
		print "<tr><td align=right><font color=$cl><NOBR>$rank��</NOBR></font></td>\n";
		print "<td align=right><font color=$cl><NOBR>$r_score</NOBR></font></td>\n";
		print "<td align=center><font color=$cl><NOBR>$r_date</NOBR></font></td>\n";
		print "<td align=left><font color=$cl><NOBR>$r_name</NOBR></font></td>\n";
		print "<td align=left><font color=$cl><NOBR>$r_extra</NOBR></font></td></tr>\n";
		$rank++;
	}
	print "</table>\n";
}

# ------- �X�R�A�������� -------
sub regist{
	if( $FORM{'name'} eq "" ){&error('���O�����͂���Ă��܂���<br>code 87');}
	if( (index $FORM{'name'},",") >= 0 ){&error('���O�ɃJ���}�����͎g���܂���<br>code 90');}
	if( (length $FORM{'name'}) > 20 ){&error('���O�̕��������������܂�<br>code 91');}
	if( $FORM{'score'} == 0 ){ &error('�X�R�A�O�ł͋L�^�ł��܂���<br>code 96');}
	if( (($FORM{'score'} >= $max_SCORE) && ($max_SCORE > 0))
			|| (($FORM{'HP'} >= $max_HP) && ($max_HP > 0))
				|| (($FORM{'AT'} >= $max_AT) && ($max_AT > 0))
					|| (($FORM{'DF'} >= $max_DF) && ($max_DF > 0))
						|| (($FORM{'GD'} >= $max_GD) && ($max_GD > 0)) ){
		&error('�X�e�[�^�X�Ɉُ킪����܂�<br>code 95');
	}
	$ident = $FORM{'score'} *($random) + $FORM{'HP'} *($random +7) + $FORM{'AT'} *($random +17) + $FORM{'DF'} *($random +41) + $FORM{'GD'} *($random +3);
	$ident = $ident %9999;
	if( $FORM{'ident'} != "$ident" ){ &error('�f�[�^�������񂳂�Ă��܂�<br>code 89'); }
	
	$mxsc = 9 x $st;
	$FORM{'score'} = $mxsc - $FORM{'score'};
	$FORM{'score'} = substr (0 x $st . $FORM{'score'},-$st);
	if (!open(NOTE,"$rankfile")){&error('�����L���O�t�@�C����������܂���<br>code 94');}
	@RANKING = <NOTE>;
	close(NOTE);
	$w_msg = '�X�R�A���m�F���܂���';
	$i = 0;
	$found = 0;
	$write = 1;
	foreach $p_data (@RANKING){
		($r_score,$r_date,$r_name,$r_pass,$r_extra) = split(/\,/,$p_data);
		if( ($r_pass eq $ENV{'REMOTE_ADDR'}) || ($r_name eq $FORM{'name'}) ){
			if( $r_score gt $FORM{'score'} ){
				splice(@RANKING, $i, 1);
				$w_msg = '�X�R�A���X�V���܂���';
			}else{
				$w_msg = '�O��̃X�R�A���X�V�ł��܂���ł���';
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
			$w_msg = "$max_rank�ʈȓ��ɓ���܂���ł���";
		}else{
			pop(@RANKING);
		}
	}
	if ($write == 1){
		if (!open(NOTE,">$rankfile")){&error('�����L���O�t�@�C����������܂���<br>code 128');}
		print NOTE @RANKING;
		close(NOTE);
	}
	&html_head;
	print "<font size=6>$w_msg</font><br>\n";
	if ($write == 1){print "�L�^���܂�<br>\n";}
	print "<br>\n<a href=\"$this_fn?mode=rank\">�����L���O������</a>\n";
}


# ------- FORM�쐬 -------
sub post_form{
	$idNumber = $FORM{'HP'} *7 +$FORM{'AT'} *11 +$FORM{'DF'} *19 +$FORM{'GD'} *5 +$FORM{'PX'} *17 +$FORM{'PY'} *21;
	for( $i = 1 ; $i <= 12 ; ++$i ){
		$elename = "ITEM$i";
		$idNumber += $FORM{$elename} *17;
	}
	$idNumber = $idNumber %9999;
	if( $idcode != 0 ){ $idNumber *= $idcode; }
	$idNumber = $idNumber %9999;
	if( $FORM{'ID'} != $idNumber ){ &error('�f�[�^�������񂳂�Ă��܂�<br>�܂��͈Ïؔԍ�������Ă��܂�<br>code 90'); }
	
	$score = $FORM{'HP'} * $hpx + $FORM{'AT'} * $atx + $FORM{'DF'} * $dfx + $FORM{'GD'} * $gdx;
	$ident = $score * $random + $FORM{'HP'} * ($random +7) + $FORM{'AT'} * ($random +17) + $FORM{'DF'} * ($random +41) + $FORM{'GD'} * ($random +3);
	$ident = $ident %9999;
	&get_date;
	&html_head;
	print<<"EOF";
<form action="$this_fn" method=POST>
���Ȃ��̃X�R�A��<font size=7> $score </font>�_�ł��B
<hr>
<input type="hidden" name="mode" value="write">
<input type="hidden" name="date" value="$date">
<input type="hidden" name="score" value="$score">
<input type="hidden" name="HP" value="$FORM{'HP'}">
<input type="hidden" name="AT" value="$FORM{'AT'}">
<input type="hidden" name="DF" value="$FORM{'DF'}">
<input type="hidden" name="GD" value="$FORM{'GD'}">
<input type="hidden" name="ident" value="$ident">

�����O�F<br>
<input type="text" size="20" name="name" maxlength="20"><br>
<hr>
<input type="submit" value="��������">
</form>
EOF
}

# ------- ���t�̎擾 -------
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

# ------- �f�R�[�h -------
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

# ------- �G���[���� -------
sub error{
	&html_head;
	$msg = $_[0];
	print "<font size=7 color=red>Error!</font>\n";
	print "<hr>$msg<hr>\n";
	&html_foot;
	exit;
}

# ------- HTML�i�w�b�_���j -------
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

# ------- HTML�i�t�b�^���j -------
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
