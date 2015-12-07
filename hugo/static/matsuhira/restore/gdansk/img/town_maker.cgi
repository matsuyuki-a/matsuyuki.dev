#!/usr/local/bin/perl

# ↑お使いのサーバーのパスに合わせてください。#2006/10/20 OUT 
#+++++++++++++++++++++++++++++++++++++
#  Copyright (c) 2003-2004 brassiere
$version = 'TOWN ver.1.40';
#  web：http://brassiere.jp/
#  mail：shohei@brassiere.jp
#このプログラムによって起きた事に責任を負いません。
#+++++++++++++++++++++++++++++++++++++

#　※このプログラムはフリーウエアですが、ゲーム内で時々「テキスト広告」が表示されます。また、予想以上に労力と時間がかかってしまったため、任意の使用料金のお支払いを歓迎しています。実際に設置し、運用した上で料金を支払うだけの価値があると思われた方は、適当と思われる額を支払っていただけるととても嬉しいです。今後の開発を維持していくために応援いただけると非常に助かります
#	  もちろん支払い義務はありませんし、機能制限もありません。また料金を支払っていただいた方とそうでない方で区別することはありません。支払わないのが普通だと思ってますので、恨むこともありません（笑。それでも料金を支払って下さるという方は、下記振込先まで送金をお願いいたします。

#　＜任意の料金支払先＞
#　みずほ銀行　集中第一支店　普通預金
#　支店番号：822
#　口座番号：9502477
#　受取人名：イーバンクギンコウ(カ

#　※料金の目安（あくまで参考で、これ以下でもこれ以上でも構いませんｗ）
#　○満足した＝500円
#　○ものすごく満足した＝1000円
#　○感動した！素晴らしい！＝1500円

#　＜重要＞　このゲームは画像面積が大きく、さらに頻繁に読み込みをするため、特にトラフィック容量において注意を要します（当サイトでの稼働実績ではまったく制約をしない場合、10日で10Ｇ強のトラフィック量となっています）。設置の際はそのサーバーで定められたトラフィック容量を確認した上で「街の移動時間」および「行動の制限時間」をなるべく長く取り、常にトラフィック量の推移をチェックすることを強くお勧めします。また、サーバーを複数お持ちであれば画像のみ別サーバーに置き（初期設定で画像フォルダーの指定をする）、トラフィック量を分散させることも有効です。
# ランダム紹介者処理 #'yes';'no';'';の三種類 #1/2 game.cgi にもう一個。
$syokai = 'no';#""; 

# アイテムの数を表示 koko2007/04/28
$kazu_disp = 'yes';
# タウンナンバーの保存 2007/09/18
$hozontown = 'yes';

# プルダウンタウンページ"no","yes","yes2","yes3":
$dairekutoin = 'yes3';
# eval{ flock (IN, 2); }; ロック強化 koko2007/06/18 sleep
###############################################################

require './jcode.pl';
require './cgi-lib.pl';
require './top.pl';
require './town_ini.cgi';
require './command.pl';
require './event.pl';
require './town_lib.pl';		#ver.1.4
require './unit.pl';		#ver.1.4
&decode;

# 指定ホストアクセス拒否
	# ホスト名を取得
	$get_host = $ENV{'REMOTE_HOST'}; #2007/05/16
#	if (!$get_host){$get_host = $ENV{'REMOTE_ADDR'};} #2007/05/16
	$get_addr = $ENV{'REMOTE_ADDR'};
	if ($get_host eq "" || $get_host eq $get_addr) {
		$get_host = gethostbyaddr(pack("C4", split(/\./, $get_addr)), 2) || $get_addr;
	}
if ($get_host eq "") { &error("恐れ入りますがホストが取得できない環境ではアクセスできません"); }
#koko2007/10/10
if ($host_kyuka_meker eq 'yes'){
	if($okhost){
		local($flag)=1;
		foreach ( split(/\s+/, $okhost) ) {
			s/(\W)/\\$1/g;
			s/\*/\.\*/g;
			if ($get_host =~ /$_/) { $flag=0; last; }
		}
		if ($flag) { 
			$admin_pass = ''; 
		}
	}else{
		($host1,$host2,$host3,$host4) = split(/\./, $get_host);
		if ($host1 !~ /\D/ && $host2 !~ /\D/ && $host3 !~ /\D/ && $host4 !~ /\D/){&error("host error $get_host");} #2007/10/10

		(@host5) = split(/\./, $get_host);
		$i = 0;
		foreach (@host5){
			if ($_ eq 'jp' || $_ eq 'JP' || $_ eq 'net'){
				$i++;
				if ($i >= 2){&error("host error $get_host");} #2007/10/10
			}
		}

#		$get_host = "'*'.$host5[1].$host5[2].$host5[3].$host5[4].$host5[5].$host5[6].$host5[7]"; # *.123,abc.ne.jp 先頭だけ「*」有効

		(@kanri1) = split(/\./, $my_host1);
		if ($kanri1[0] eq '*'){
			$oboegaki1 = $host5[0];
			$host5[0] ='*';
			$get_host1 = join(".",@host5);
		}else{$get_host1 = $get_host;}
		$itti = "1";
		if ($my_host1 && $my_host1 eq $get_host1){ #
			$itti = "yes";
		}elsif($my_host1){$itti = "no";}
		if($oboegaki1){$host5[0] = $oboegaki1;}
		$get_host = join(".",@host5);
		(@kanri2) = split(/\./, $my_host2);
		if ($kanri2[0] eq '*'){
			$oboegaki2 = $host5[0];
			$host5[0] ='*';
			$get_host2 = join(".",@host5);
		}else{$get_host2 = $get_host;}
		if ($my_host2 && $my_host2 eq $get_host2 && ($itti eq "no" || $itti eq "1")){
			$itti = "yes";
		}elsif($my_host2){$itti = "no";}
		if ($itti eq "no"){
			$admin_pass = '';
		}
		if($oboegaki2){$host5[0] = $oboegaki2;}
		$get_host = join(".",@host5);
	}
}
#kokoend #koko2007/04/17
	open(IN,"< dene2.cgi") || &error("Open Error : dene2.cgi");
	eval{ flock (IN, 1); };
	@dene2 = <IN>;
	close(IN);
	@deny = (@deny,@dene2);
#kokoend2007/04/17
	foreach (@deny) {
		if ($_ eq "") { next; }
		chomp $_;
		$_ =~ s/\*/\.\*/g;
		if ($get_host =~ /$_/i) { &error("恐れ入りますがご利用中のホストからはアクセスできません"); }
	}
#kokoend
	
#メンテチェック
	if($mente_flag == 1 && $in{'admin_pass'} eq "" && $in{'mode'} ne ""){&error("$mente_message")}	#ver.1.2

sub joukenbunki {
}

$seigenyou_now_time = time;
#制限時間チェック
		$ato_nanbyou=$koudou_seigen-($seigenyou_now_time - $access_byou);
		if($seigenyou_now_time - $access_byou < $koudou_seigen){&error("まだ行動できません。あと$ato_nanbyou秒お待ちください。")}

#ver.1.40ここから
#パスワードログ作成
	if ( !-e $pass_logfile){
		open(LOGF,"< $logfile") || &error("Open Error : $logfile");
		eval{ flock (LOGF, 1); };
		@pass_sakuse = <LOGF>;
		close(LOGF);
		@henkan_pass = (); #koko2007/07/21
		foreach (@pass_sakuse){
			&list_sprit($_);
			$henkan_temp = "$list_k_id<>$list_name<>$list_pass<>\n";
			push (@henkan_pass,$henkan_temp);
		}

#		sub by_r_number {$b <=> $a;}
#		@henkan_pass = sort by_r_number @henkan_pass;
#koko2005/10/08
	@henkan_pass = sort {$b <=> $a} @henkan_pass;
#kokoend


		open(PSS,">$pass_logfile") || &error("Write Error : $pass_logfile");
		eval{ flock (PSS, 2); };
		print PSS @henkan_pass;
		chmod 0666,"$pass_logfile";
		close(PSS);
	}
#koko2007/02/10
	if ($in{'mode'} eq "shiharaihouhou"){
		$shiharai = $in{'shiharai'};
		$in{'mode'} = "login_view";
	}
#kokoend
#条件分岐
	if($in{'mode'} eq "login_view"){&login_view;}
	if($in{'mode'} eq "orosi"){&orosi;}
	if($in{'mode'} eq "buy_orosi"){&buy_orosi;}
	if($in{'mode'} eq "gym"){&gym;}
	if($in{'mode'} eq "training"){&training;}
	if($in{'mode'} eq "syokudou"){&syokudou;}
	if($in{'mode'} eq "syokuzisuru"){&syokuzisuru;}
	if($in{'mode'} eq "syokudou2"){&syokudou2;}		#koko2006/07/16
	if($in{'mode'} eq "syokuzisuru2"){&syokuzisuru2;}	#koko2006/07/16
	if($in{'mode'} eq "school"){&school;}
	if($in{'mode'} eq "do_school"){&do_school;}
	if($in{'mode'} eq "depart_gamen"){&depart_gamen;}
	if($in{'mode'} eq "depart_gamen2"){&depart_gamen2;}	#koko2006/11/20
	if($in{'mode'} eq "buy_syouhin"){&buy_syouhin;}
	if($in{'mode'} eq "buy_syouhin2"){&buy_syouhin2;}	#koko2006/11/20
	if($in{'mode'} eq "hanbai"){&hanbai;}			#koko2006/11/28
	if($in{'mode'} eq "buy_syouhin_hanbai"){&buy_syouhin_hanbai;} #koko2006/11/28
	if($in{'mode'} eq "kentiku"){&kentiku;}
	if($in{'mode'} eq "kentiku_do"){&kentiku_do;}
	if($in{'mode'} eq "aisatu"){&aisatu;}
	if($in{'mode'} eq "mail_sousin"){&mail_sousin;}
	if($in{'mode'} eq "mail_do"){&mail_do;}
#	if($in{'mode'} eq "jamp_url"){&jamp_url;} #2007/09/26
#ver.1.40ここまで
	&main_view($in{'town_no'});
exit;


###################サブルーチン

#ログイン画面
sub login_view {
#日付けが変わっていた場合のイベント
	&time_get;
	my ($hutuu_risoku,$teiki_risoku);
	if ($access_time ne "$date"){
		$access_time = $date;
#預金の利息計算
		$hutuu_risoku = int ($bank*0.005);
		if ($hutuu_risoku > 0){
			$bank += $hutuu_risoku;
			&kityou_syori("普通預金利息","",$hutuu_risoku,$bank,"普");
		}
		$teiki_risoku = int ($super_teiki*0.01);
		if ($teiki_risoku > 0){
			$super_teiki += $teiki_risoku;
			&kityou_syori("スーパー定期利息","",$teiki_risoku,$super_teiki,"定");
		}
#住宅ローン引き落とし
		if ($loan_kaisuu > 0){
			$bank -= $loan_nitigaku;
			$loan_kaisuu --;
			&kityou_syori("住宅ローン支払い","$loan_nitigaku","",$bank,"普");
		}
#子供からの仕送り処理
		&kodomo_siokuri;
	#	&unei_siokuri; #koko2007/04/17
		&unei_siokuri2; #koko2007/04/29 &unei_siokuriとどちらかを使う。
		&unei_siokuri3; #koko2007/05/26
	}
#事故だった場合
	if ($in{'ziko_flag'} eq "on" && $in{'ziko_idousyudan'} ne "徒歩"){
		$monokiroku_file="./member/$k_id/mono.cgi";
		open(OUT,"< $monokiroku_file") || &error("Open Error : $monokiroku_file");
		eval{ flock (OUT, 1); };
		@mycar_hairetu = <OUT>;
		close(OUT);
		$ziko_sya_flg=0;
#ver.1.40ここから
		@new_mycar_hairetu =(); #koko2007/06/05
		foreach  (@mycar_hairetu) {
			&syouhin_sprit($_);
#koko2006/12/31
			if ($syo_syubetu ne  "ギフト" && $syo_taikyuu > 0 && $in{'ziko_idousyudan'} eq "$syo_hinmoku" && $ziko_sya_flg == 0){
				$syo_taikyuu -- ;
				$ziko_sya_flg=1;
				if ($syo_taikyuu <= 0){
					$taiha_comment = "$in{'ziko_idousyudan'}は大破しました。";
				}else{
					$taiha_comment = "残りの耐久（$syo_taikyuu）";
				}
#ver.1.40ここまで #koko2006/12/31
			}
			&syouhin_temp;
			push (@new_mycar_hairetu,$syo_temp);
		}
#自分の所有物ファイルを更新
		&lock;
		open(OUT,">$monokiroku_file") || &error("Write Error : $monokiroku_file");
		eval{ flock (OUT, 2); };
		print OUT @new_mycar_hairetu;
		close(OUT);
#koko2006/11/27
		$loop_count = 0;
		while ($loop_count <= 10){
			for (0..50){$i=0;}
			@f_stat_b = stat($monokiroku_file);
			$size_f = $f_stat_b[7];
			if ($size_f == 0 && @new_mycar_hairetu ne ""){
			#	sleep(1);#2006/11/27#koko2007/02/02
				open (OUT, "> $monokiroku_file") or &error("エラー・ファイルが開けません $monokiroku_file");
				eval{ flock (OUT, 2); };
				print OUT @new_mycar_hairetu;
				close (OUT);
			}else{
				last;
			}
			$loop_count++;
		}
#kokoend#koko2007/01/21
	if ($in{'maigo'} eq 'yes'){
		$in{'town_no'} = 3;
		$disp = "<br>おまけに迷子になってしまいました。<br>$town_hairetu[$in{'town_no'}]に行ってしまいました。";
	}
	&unlock;
	&message("<font color=#ff6600>交通事故を起こしてしまいました！<br>「$in{'ziko_idousyudan'}」の耐久度が１減ります。<br>$taiha_comment$disp</font>","login_view");
	}		#事故だった場合閉じ
	#迷子　
	if ($in{'maigo'} eq 'yes'){
		$in{'town_no'} = 3;
		&message("<font color=#ff6600>迷子になりました。<br>$town_hairetu[$in{'town_no'}]に行ってしまいました。</font>","login_view");
	} #kokoend

	&event_happen;
	$k_sousisan = $money + $bank + $super_teiki - ($loan_nitigaku * $loan_kaisuu);
	&main_view("$in{'town_no'}");
exit;
}

#トップ画面右部分
sub top_gamen {
	open(IN,"< $maintown_logfile") || &error("Open Error : $maintown_logfile");
	eval{ flock (IN, 1); };
			$maintown_para = <IN>;
			if ($maintown_para eq ""){&error("$maintown_logfileに問題があります。お手数ですが管理人（$master_ad）までご連絡ください。");}			#空ログチェック		ver.1.22
			&main_town_sprit($maintown_para);
	close(IN);
	&time_get;
#日付が変わっていたらメインタウンログを今日の日付に更新、卸時刻、明日の卸時刻を更新、卸フラグと食堂フラグを0にする
	if($date ne $mt_today){
			$mt_today=$date;
			$mt_t_time=$mt_y_time;
			$mt_y_time=int(rand(20))+1;		#ver.1.3
			$mt_orosiflag=0;
			$mt_syokudouflag=0;
			$mt_departflag=0;
			&main_town_temp;
			&lock;
			open(OUT,">$maintown_logfile") || &error("Write Error : $maintown_logfile");
			eval{ flock (OUT, 2); };
			print OUT $mt_temp;
			close(OUT);	
			&list_log_backup;
			&unlock;
	}

	open(IN,"< $logfile") || &error("Open Error : $logfile");
	eval{ flock (IN, 1); };
	@rankingMember = <IN>;
	$sankasyasuu = @rankingMember;
	close(IN);
	my $mt_keizai_hyouzi = int ($mt_keizai / (int(($date_sec - $mt_yobi8)/(60*60*24))+1));
	my $mt_henei_hyouzi = int ($mt_hanei / (int(($date_sec - $mt_yobi8)/(60*60*24))+1));
			if ($tajuukinsi_flag==1){$tajuucomment = "<br>※多重登録は禁止です。";}
			if ($tajuukinsi_deny==1){$tajuucomment .= "多重登録が発覚した時点でログインできなくなりますのでご注意ください。";}
 # %machi_gazou=('たっちゃんタウン','./img/keiba_tytle.gif','シーリゾート','./img/kentiku_tytle.gif');
	print <<"EOM";
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td>
           <div align="center"><font size="5"><b>$title</b></font></div>
	   <!-- <div align="center"><img src="$machi_gazou{"$title"}" alt="$title"></div>> -->
          </td>
        </tr>
        <tr><td  class="theue"><img src="$img_dir/dat.gif" alt="データ"></td></tr><tr>
          <td  class="thanaka" background="$img_dir/townnameback.gif">
<span  onMouseOver='onMes5(\"登録されているユーザ数（街全体）\")' onMouseOut='onMes5(\"\")'>人口：$sankasyasuu人</span>　<span  onMouseOver='onMes5(\"住民のお店の平均１日売り上げ額（街全体）\")' onMouseOut='onMes5(\"\")'>経済力：$mt_keizai_hyouzi円</span>　<span  onMouseOver='onMes5(\"掲示板の平均１日書き込み数（街全体）\")' onMouseOut='onMes5(\"\")'>繁栄度：$mt_henei_hyouzi</span></td></tr><tr>
<td  class="thanaka"><img src="$img_dir/tod.gif" alt="今日は・・・。"></td></tr><tr><td  class="theshita" background="$img_dir/townnameback.gif">
$top_nannichi</td>
        </tr>
        <tr>
          <td>	$osirase
<!-- ver.1.30ここから --> 
EOM
#koko2006/11/29
if ($genzaino_ninzuu >= $douzi_login_ninzuu){
	print <<"EOM";
		<div align=center><br><table  border=0  cellspacing="5" cellpadding="0" width=300 style="$message_win"><tr><td>現在、同時ログイン制限$douzi_login_ninzuu人を超えています。恐れ入りますが、しばらくしてからログインしてください。</td></tr></table>
EOM
	}else{
#koko2007/09/26
		if($hozontown eq 'yes'){$disp_tag = "<input type=\"hidden\" name=\"town_no\" value=\"$ck{'town_no'}\">\n";}else{$disp_tag="";}
		if ($dairekutoin eq 'yes' || $dairekutoin eq 'yes3'){
			$disp_tag = "街を選ぶ ： <select name=\"town_no\">\n";
			$disp_tag .= "<option value=\"\">普通</option>\n";
			if($hozontown eq 'yes'){$disp_tag .= "<option value=\"$ck{'town_no'}\">記憶</option>\n";} 
			$i=0;
			foreach (@town_hairetu){
				if($machikakushi eq 'yes'){ #koko2007/10/21
					if($_ eq $kakushimachi_name && $kakushimachi_name || $_ eq $kakushimachi_name1 && $kakushimachi_name1 || $_ eq $kakushimachi_name2 && $kakushimachi_name2 || $_ eq $kakushimachi_name3 && $kakushimachi_name3 || $_ eq $kakushimachi_name4 && $kakushimachi_name4){
						$i++;
						next;
					}
				}
				$disp_tag .= "<option value=\"$i\">$_</option>\n";
				$i++
			}
			$disp_tag .= "</select><br>\n";
		}
#koko2006/11/29
		print <<"EOM";
<Script Language="JavaScript">
<!--
function bzc(){
	document.getoin.burauza_in.value = navigator.appName;
}
//-->
</Script>
	<form method="POST" name="getoin" action="$script">
	<input type=hidden name="mode" value="login_view">
	<input type="hidden" name="burauza_in" value="">
        ●ログイン（登録済みの方）<br>
        お　名　前 ： 
        <input type="text" name="name" size="18" value="$ck{'name'}" maxlength="16"><br>
        パスワード ： 
        <input type="password" name="pass" size="18" value="$ck{'pass'}" maxlength="10"><br>
	$disp_tag<br>
EOM
	if ($sanka_hyouzi_kinou == 1){
		print <<"EOM";
		参加者一覧に名前を <input type="radio" name="sanka_hyouzi_on" value="off"> 表\示させない 
		<input type="radio" name="sanka_hyouzi_on" value="on" checked>  表\示させる<br>
EOM
	}
		print <<"EOM"; #koko2007/01/18
        <div align=center><input type="submit" value="OK" onMouseDown='bzc()'></div>
      </form>
	<hr size=1>
EOM
#kokoend
	if ($sankasyasuu >= $saidai_ninzuu){
		print "※現在、最大登録人数に達していますので新規登録はできません。";
	}elsif($new_touroku_per == 1) {
		print "※現在、新規登録を中止しています。";
	}else{
#koko2007/09/13
		if ($syokai ne 'no'){
			$disp_syukai = "紹介コード： <input type=\"text\" name=\"syokai\" size=\"18\" maxlength=\"16\"><br>\n";
		}else{$disp_syukai = "";}
#kokoend
		print <<"EOM";
     <form method="POST" action="game.cgi">
	<input type=hidden name=mode value="new">
		●新規登録（最大登録人数：$saidai_ninzuu人）$tajuucomment<br>
        お　名　前： 
        <input type="text" name="name" size="18" maxlength="16"><br>
<!-- #koko2007/09/13 -->
	$disp_syukai
<!-- #kokomade2007/09/13 -->
        パスワード： 
        <input type="password" name="pass" size="18" maxlength="10"><br>
		性　　　別： 
        <input type="radio" name="sex" value="m">男 
		<input type="radio" name="sex" value="f">女<br>
        <div align=center><input type="submit" value="OK"></div>
      </form>
<hr>
●「Customer Floor」にログイン（常連の方）
<FORM ACTION="password.cgi" METHOD="post">
<TABLE border="1">
  <TBODY>
    <TR>
      <TD align="right"><BR>
      ユーザーID</TD>
      <TD><INPUT type="text" name="id"></TD>
    </TR>
    <TR>
      <TD align="right">パスワード</TD>
      <TD><INPUT type="password" name="pass"></TD>
    </TR>
    <TR>
      <TD colspan="2" align="center"><INPUT TYPE="SUBMIT" VALUE="送信">
<INPUT TYPE="RESET" VALUE="クリア"></TD>
    </TR>
  </TBODY>
</TABLE>
</FORM>
<HR>
技術提供：<A href="http://cgi-garage.parallel.jp/">CGI-Garage</A>
<HR>
EOM
	}
}		#制限人数を超えていない場合の閉じ
# ver.1.30ここまで
	print <<"EOM";
<!-- koko2006/12/27-->
	<hr size=1>
	<form method=POST action="admin2.cgi">
	<input type=hidden name=mode value="admin">
	<input type=hidden name=name value="$ck{'name'}">
	<input type=hidden name=kanri value="fuku">
	パスワード <input type=password size=8 name="admin_pass"> <input type=submit value="副管理者メニュー">
	</form>
<!-- koko2006/12/27-->
	<hr size=1>
	<form method=POST action="admin.cgi">
	<input type=hidden name=mode value="admin">
	管理者ＩＤ <input type="text" size=10 name="kanrisya_id"><br><!-- koko2005/10/07-->
	パスワード <input type=password size=8 name="admin_pass"> <input type=submit value="管理者メニュー">
	</form>
		  </td>
        </tr>
      </table>
EOM
}

#街情報窓の出力　改造まつぷら 2008/03/25　さらに改造2009/02/07 int(rand(n))+1;のnには表示させるパターンの数を入れる。
sub town_jouhou {
 $news_rand = int(rand(4))+1;
 if ($news_rand == 1){
  $news_bar = $news_bar1;
 }elsif ($news_rand == 2){
  $news_bar = $news_bar2;
 }elsif ($news_rand == 3){
  $news_bar = $news_bar3;
  }else{
   $news_bar = $news_bar4;
  }
 $keizai_hyouzi = int ($keizai / (int(($date_sec - $t_yobi2)/(60*60*24))+1));
 $hanei_hyouzi = int ($hanei / (int(($date_sec - $t_yobi2)/(60*60*24))+1));
 print <<"EOM";
<table width="100%" border="0" cellspacing="0" cellpadding="0" align=center class="yosumi" background="$img_dir/townnameback.gif">
<tr bgcolor="white"><td><img src="$img_dir/ar.gif" alt="エリア名"></td><td><img src="$img_dir/dt.gif" alt="エリアデータ"></td></tr><tr><td><div align=center><span  class="tyuu">「<B>$title</B>」内</span><br><span  class="midasi">$town_name</span></div></td><td><span  onMouseOver='onMes5("この街の土地の価格")' onMouseOut='onMes5("")'>地　価：$town_tika_hairetu[@_[0]]万</span><br><span  onMouseOver='onMes5("この街の住民のお店の平均１日売り上げ額")' onMouseOut='onMes5("")'>経済力：$keizai_hyouzi円</span><br><span  onMouseOver='onMes5("この街にある掲示板の平均１日書き込み数")' onMouseOut='onMes5("")'>繁栄度：$hanei_hyouzi</td></tr><tr bgcolor="white"><td colspan="2"><img src="$img_dir/nt.gif" alt="最新トピックス"></td></tr><tr><td colspan="2">$news_bar</span></td></tr></table><br>
EOM
}
 
#コマンドボタンの出力
sub command_botan {			#ver.1.3	恋愛ボタン、配偶者家設定ボタン追加、子育てボタン追加、いとしき我が家ボタン削除、ボタンの並び数変更
    print "<img src=$img_dir/cm.gif alt=コマンド></td></tr><tr><td><tr><td background=$img_dir/cloud.gif>";
#購入物ファイルを開く
	$monokiroku_file="./member/$k_id/mono.cgi";
	if (! -e $monokiroku_file){
		open (OUT,">$monokiroku_file") || &error("自分の購入物ファイルを作成できません");
		eval{ flock (OUT, 2); };
		close(OUT);
	}
	open(OUT,"< $monokiroku_file") || &error("自分の購入物ファイルが開けません");
	eval{ flock (OUT, 1); };
	@my_kounyuu_list =<OUT>;
	@mono_name_keys = ();
	@mono_kouka_keys = ();
	foreach (@my_kounyuu_list){
		&syouhin_sprit($_);
		if ($syo_taikyuu <= 0){next;}
		push (@mono_name_keys ,$syo_hinmoku);
		push (@mono_kouka_keys ,$syo_kouka);
	}
	$botanyou_mono_check = join("<>",@mono_name_keys);
	$botanyou_kouka_check = join("<>",@mono_kouka_keys);
	close(OUT);
	
	if ($renai_system_on == 1){$botan_narabi_suu = 7;}else{$botan_narabi_suu = 6;}

#koko2006/11/25
	if ($in{'mysec'}){
		($sec_dis,$min_dis,$hour_dis,$mday_dis,$mon_dis,$year_dis,) = localtime($in{'mysec'} + 60*$work_seigen_time);
	}else{
		($sec_dis,$min_dis,$hour_dis,$mday_dis,$mon_dis,$year_dis,) = localtime($house_name + 60*$work_seigen_time);
	}
	$year_dis += 1900;
	$mon_dis++;
	if ($min_dis < 10){$min_dis = '0'.$min_dis}
	if ($sec_dis < 10){$sec_dis = '0'.$sec_dis}

	$kaigyou_flag=1;
	$top_botan  .= <<"EOM";
<form method=POST name=ct action="$script"><td><input type=hidden name=std value='$year_dis $mon_dis $mday_dis $hour_dis $min_dis $sec_dis'><!--koko2006/04/01 --><input type=hidden name=mode value="login_view"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/reload.gif' width=32 height=32  onMouseOver='onMes5("画面を更新します。「パワー」は休息後、このボタンを押すことで徐々に増えます。")' onMouseOut='onMes5("")'></td></form>
EOM
#kokoend
	if ($job ne "学生"){
	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .=  "</tr><tr>";}

	$top_botan  .= <<"EOM";
<!-- koko2006/04/01 -->
<script type="text/javascript">
<!--
//function my_sec(){ 取り消し2007/04/25
//	myDate = Math.round((new Date()).getTime()/1000);
//	document.ctw.mysec.value = myDate;
//}
// #koko2006/12/25
function mese_on() {
	onMes5("仕事に出かけます。現在、経験値：$job_keiken　勤務数：$job_kaisuu回");
}
function mese_out() {
	Disp = document.foMes5.w_time.value;
	onMes5(Disp);
}
function mese_on2() {
	onMes5("現在仕事、経験値：$job_keiken　勤務数：$job_kaisuu回");
}

//-->
</script>
<form method=POST name=ctw action="basic.cgi"><td><INPUT TYPE=hidden NAME="mysec"><input type=hidden name=mode value="do_work"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=hidden name=cond value="$condition"><div id="comm"><input type=image src='$img_dir/go_work.gif' width=32 height=32 onMouseOver='mese_on()' onMouseOut='mese_out()'></div></td></form><!--ver.1.40-->
EOM
#kokoend
	}elsif(($brauza eq 'FireFox' || $brauza eq "") && $job ne "学生"){ #koko2007/04/22
		print "<form method=POST name=ctw action=\"basic.cgi\"><td><INPUT TYPE=hidden NAME=\"mysec\"><input type=hidden name=mode value=\"do_work\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=hidden name=cond value=\"$condition\"><div id=\"comm\"><input type=image src='$img_dir/go_work.gif' width=32 height=32 onMouseOver='mese_on()' onMouseOut='mese_out()'></div></td></form><!--ver.1.40-->\n";
	}#kokoend

	
	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .=  "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="basic.cgi"><input type=hidden name=mode value="item"><td><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/item.gif' width=32 height=32  onMouseOver='onMes5("アイテムを使用します。")' onMouseOut='onMes5("")'></td></form><!--ver.1.40-->
EOM

	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .= "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="$script"><td><input type=hidden name=mode value="aisatu"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/aisatu.gif' width=32 height=32  onMouseOver='onMes5("今日の出来事や気分などあいさつをしましょう。")' onMouseOut='onMes5("")'></td></form>
EOM

	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .= "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="$script"><td><input type=hidden name=mode value="mail_sousin"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/mail.gif' width=32 height=32  onMouseOver='onMes5("街の住人あてにメッセージを送信することができます。")' onMouseOut='onMes5("")'></td></form>
EOM

	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .= "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="game.cgi"><td><input type=hidden name=mode value="doukyo"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/doukyo.gif' width=32 height=32  onMouseOver='onMes5("自分のキャラクターを作成し育てていきます。")' onMouseOut='onMes5("")'></td></form><!--ver.1.40-->
EOM

	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .= "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="game.cgi"><td><input type=hidden name=mode value="battle"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/battle.gif' width=32 height=32  onMouseOver='onMes5("ストリートファイトに出かけます。")' onMouseOut='onMes5("")'></td></form><!--ver.1.40-->
EOM

	if ($unit{$k_id} ne ""){
	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .=  "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="original_house.cgi"><td><input type=hidden name=mode value="my_house_settei">	<input type=hidden name=iesettei_id value="$k_id"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/my_housein.gif' width=32 height=32  onMouseOver='onMes5("自分の家に関する各種設定を行えます。")' onMouseOut='onMes5("")'></td></form><!--ver.1.40-->
EOM
	}

	if ($unit{$house_type} ne ""){
	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .=  "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="original_house.cgi"><td><input type=hidden name=mode value="my_house_settei"><input type=hidden name=iesettei_id value="$house_type"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/my_housein2.gif' width=32 height=32  onMouseOver='onMes5("配偶者の家に関する各種設定を行えます。")' onMouseOut='onMes5("")'></td></form><!--ver.1.40-->
EOM
	}
	
	if ($love >= $need_love && $renai_system_on == 1){
	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .=  "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="kekkon.cgi"><td><input type=hidden name=mode value="renai"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/renai.gif' width=32 height=32  onMouseOver='onMes5("恋人とデートをしたり、プロポーズしたりできます。")' onMouseOut='onMes5("")'></td></form><!--ver.1.40-->
EOM
	}
	
	if ($love >= $need_love && $renai_system_on == 1){
	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan  .=  "</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="kekkon.cgi"><td><input type=hidden name=mode value="kosodate"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/kosodate.gif' width=32 height=32  onMouseOver='onMes5("子育てをします。")' onMouseOut='onMes5("")'></td></form><!--ver.1.40-->
EOM
	}
	
	$kaigyou_flag ++;
	if($kaigyou_flag % $botan_narabi_suu == 0){$top_botan .="</tr><tr>";}
	$top_botan  .= <<"EOM";
<form method=POST action="game.cgi"><td><input type=hidden name=mode value="data_hozon"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=image src='$img_dir/off.gif' width=32 height=32  onMouseOver='onMes5("データ保存します。最後に保存した食事時間が$deleteUser日を超えるとユーザー削除されます。")' onMouseOut='onMes5("")'></td></form><!-- ver.1.40 -->
EOM

	print "<table boader=0 width=100%><tr>$top_botan</tr></table>";
#koko2007/10/26
	if($brauza eq 'Microsoft Internet Explorer'){
		if($in{'otoon'} && $in{'otodashi'} eq 'on'){
			$oto = 'on';
		}
		if($in{'otoon'} && $in{'otodashi'} ne 'on'){
			$oto = '';
		}
		if($oto eq 'on'){$checked_on = " checked";}

		&temp_routin;
		&log_kousin($my_log_file,$k_temp);

		print << "EOM";
<form method=POST action="$script" style="margin-top:0; margin-bottom:0;"><input type=hidden name=mode value="login_view"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=checkbox name=otodashi value="on"$checked_on>音を出す<input type=submit name=otoon value="OK"></form>
EOM
	}else{
		print "ブラウザ:$brauza"; #2006/11/30
	}
#end2007/10/26
}

#個人パラメータ出力
sub loged_gamen {
#パワーのMAX値計算
	$energy_max = int(($looks/12) + ($tairyoku/4) + ($kenkou/4) + ($speed/8) + ($power/8) + ($wanryoku/8) + ($kyakuryoku/8));
	$nou_energy_max = int(($kokugo/6) + ($suugaku/6) + ($rika/6) + ($syakai/6) + ($eigo/6)+ ($ongaku/6)+ ($bijutu/6));
	my ($date_sec) = time;
	
#身体パワー計算		#ver.1.3
	if ($in{'iiyudane'} eq "one"){
		$energy = $energy + int(($date_sec - $last_ene_time)/$sintai_kaihuku*$onsen_times);
	}elsif($in{'iiyudane'} eq "two"){
		$energy = $energy + int(($date_sec - $last_ene_time)/$sintai_kaihuku*$tokubetu_times);

#koko2006/03/31
	}elsif($in{'iiyudane'} eq "five"){ #matu
		$energy = $energy + int(($date_sec - $last_ene_time)/$sintai_kaihuku*$matu_times);
	}elsif($in{'iiyudane'} eq "fo"){ #take
		$energy = $energy + int(($date_sec - $last_ene_time)/$sintai_kaihuku*$take_times);
	}elsif($in{'iiyudane'} eq "three"){ #
		$energy = $energy + int(($date_sec - $last_ene_time)/$sintai_kaihuku*$ume_times);
#kokoend2006/03/31

	}else{
		$energy = $energy + int(($date_sec - $last_ene_time)/$sintai_kaihuku);#koko2007/05/30
	}
	$last_ene_time= $date_sec;

	if($energy > $energy_max){$energy = $energy_max;}
	if($energy < 0){$energy = 0;}
#頭脳パワー計算		#ver.1.3
	if ($in{'iiyudane'} eq "one"){
		$nou_energy = $nou_energy + int(($date_sec - $last_nouene_time)/$zunou_kaihuku*$onsen_times);
	}elsif($in{'iiyudane'} eq "two"){
		$nou_energy = $nou_energy + int(($date_sec - $last_nouene_time)/$zunou_kaihuku*$tokubetu_times);

#koko2006/03/31
	}elsif($in{'iiyudane'} eq "five"){
		$nou_energy = $nou_energy + int(($date_sec - $last_nouene_time)/$zunou_kaihuku*$matu_times);
	}elsif($in{'iiyudane'} eq "fo"){
		$nou_energy = $nou_energy + int(($date_sec - $last_nouene_time)/$zunou_kaihuku*$take_times);
	}elsif($in{'iiyudane'} eq "three"){
		$nou_energy = $nou_energy + int(($date_sec - $last_nouene_time)/$zunou_kaihuku*$ume_times);
#kokoend2006/03/31

	}else{
		$nou_energy = $nou_energy + int(($date_sec - $last_nouene_time)/$zunou_kaihuku);
	}
	$last_nouene_time= $date_sec;

	if($nou_energy > $nou_energy_max){$nou_energy = $nou_energy_max;}
	if($nou_energy < 0){$nou_energy = 0;}

#newメールのチェック
	$message_file="./member/$k_id/mail.cgi";
	open(MS,"< $message_file") || &error("自分のメールログファイルが開けません");
	eval{ flock (MS, 1); };
	$lastCheckTime=<MS>;		#最終メール時間の取得
	@lastMailTime=<MS>;		#メールデータ配列
	close(MS);
	foreach (@lastMailTime){
		&mail_sprit($_);
		if ($m_syubetu ne "送信"){$saigono_kita_mail = $m_byou;last;}
	}
#koko2007/09/28
	$i = 0;
	foreach (@lastMailTime){
		&mail_sprit($_);
		if ($m_syubetu eq "送信"){next;}
		if($lastCheckTime < $m_byou){++$i;}
	}
	@lastMailTime=split(/<>/,$lastMailTime);
	if($i){$i = "$i通の";}else{$i = "";}
	if($lastCheckTime < $saigono_kita_mail){$happend_ivent .= "<div style=color:#ff3300>★受信箱に$i新しいメッセージが届いています！</div>";}

#koko2006/11/23
	$bbs1_log_file = "./member/$k_id/bbs1_log.cgi";

	if (-e "$bbs1_log_file"){
		open(IN,"< $bbs1_log_file") || &error("Open Error : $bbs1_log_file");
		eval{ flock (IN, 1); };
		# 先頭行を取得
		$total_counter = <IN>;
		close(IN);
		($total_counter,$all_total_counter,$kakikomijikan,$yomidashijikan)= split(/<>/, $total_counter);		#ver.1.40
		if ($yomidashijikan < $kakikomijikan){
			$happend_ivent .= "<div style=color:#ff3300>★掲示板に新規書込があります！</div>";
		}
	}
#kokoend2007/05/29
	if (-e "./member/$k_id/kaishiya_bbs.cgi"){
		open(KAISYA,"< ./member/$k_id/kaishiya_bbs.cgi") || &error("kaishiya_bbs.cgiファイルを開くことが出来ませんでした。");
		eval{ flock (KAISYA, 1); };
		$kanri_bbs = <KAISYA>;
		@kiji_bbs = <KAISYA>;
		close(KAISYA);

		($opn_no,$men_no,$kai_id,$kai_name_kanre,$kaitime,$kakikomijikan,$yomidashijikan) = split(/<>/,$kanri_bbs);
		if ($yomidashijikan < $kakikomijikan){
			$happend_ivent .= "<div style=color:#ff3300>★会社掲示板に新規書込があります！</div>";
		}
	}
#kokoend

#身体パワーバー（％）算出	
	if(! $energy_max){$energy_max =1;}
	$ener_parcent = int($energy / $energy_max * 100);
	$ener_parcent_disp = $ener_parcent * 2;#koko2005/03/22
	$nokori_parcent = 200-$ener_parcent_disp;#koko2005/03/22
	if ($ener_parcent >59){$energy_bar = "energy_ao.gif";}
	elsif ($ener_parcent >19){$energy_bar = "energy_ki.gif";}
	else{$energy_bar = "energy_aka.gif";}
	
#頭脳パワーバー（％）算出	
	if(! $nou_energy_max){$nou_energy_max =1;}
	$nou_ener_parcent = int($nou_energy / $nou_energy_max * 100);
	$nou_ener_parcent_ds = $nou_ener_parcent * 2;#koko2005/03/22
	$nou_nokori_parcent = 200-$nou_ener_parcent_ds;#koko2005/03/22
	if ($nou_ener_parcent >59){$nou_energy_bar = "energy_ao.gif";}
	elsif ($nou_ener_parcent >19){$nou_energy_bar = "energy_ki.gif";}
	else{$nou_energy_bar = "energy_aka.gif";}

#最後に食事した時間を日付に変換
	&byou_hiduke($last_syokuzi);
	$last_syokuzi_henkan = $bh_full_date;
#空腹度を算出
	$tabetenaizikan = $date_sec - $last_syokuzi ;
	$manpuku_time = $syokuzi_kankaku*60;
	if ($tabetenaizikan < $manpuku_time){$kuuhukudo = "<font color=#ff3300>満腹（まだ食事できません）</font>";}
	elsif ($tabetenaizikan < $manpuku_time + 60*60*2 ){$kuuhukudo = "丁度いい";}
	elsif ($tabetenaizikan < $manpuku_time + 60*60*12 ){$kuuhukudo = "やや空腹";}
	elsif ($tabetenaizikan < $manpuku_time + 60*60*24 ){$kuuhukudo = "空腹";}
	elsif ($tabetenaizikan < $manpuku_time + 60*60*48 ){$kuuhukudo = "かなり空腹";}
	elsif ($tabetenaizikan < $manpuku_time + 60*60*24*4 ){$kuuhukudo = "すごい空腹";}		#ver.1.3
	elsif ($tabetenaizikan < $manpuku_time + 60*60*24*($deleteUser - 3)){$kuuhukudo = "死にそう・・";}		#ver.1.3
	else{$kuuhukudo = "餓　死　寸　前";}		#ver.1.3
	
#BMIをチェック
	$taijuu = sprintf ("%.1f",$taijuu);
	&check_BMI($sintyou,$taijuu);

##コンディション計算
#残パワーを加味
$condition_sisuu = ($nou_ener_parcent + $ener_parcent)/2;
#健康値を加味
$condition_sisuu += $kenkou / 100;
#空腹度を加味
if ($kuuhukudo eq "<font color=#ff3300>満腹（まだ食事できません）</font>"){$condition_sisuu *= 0.8;}elsif ($kuuhukudo eq "丁度いい"){$condition_sisuu *= 1;} elsif ($kuuhukudo eq "やや空腹"){$condition_sisuu *= 0.9;} elsif ($kuuhukudo eq "空腹"){$condition_sisuu *= 0.7;} elsif ($kuuhukudo eq "かなり空腹"){$condition_sisuu *= 0.6;} elsif ($kuuhukudo eq "すごい空腹"){$condition_sisuu *= 0.5;}else{$condition_sisuu *= 0.3;} 
#体系指数を加味
if ($taikei eq "肥満"){$condition_sisuu *= 0.8;}elsif ($taikei eq "やや太り気味"){$condition_sisuu *= 0.95;} elsif ($taikei eq "標準"){$condition_sisuu *= 1;} elsif ($taikei eq "やせ気味"){$condition_sisuu *= 0.95;} else{$condition_sisuu *= 0.8;} 

if($condition_sisuu > 10000) {$condition = "超ハツラツ！！"; $byouki_sisuu += 100}
elsif($condition_sisuu > 98) {$condition = "最高"; $byouki_sisuu += 2}
elsif($condition_sisuu > 75) {$condition = "良好"; $byouki_sisuu += 1}
elsif($condition_sisuu > 50) {$condition = "普通";}
elsif($condition_sisuu > 30) {$condition = "不良"; $byouki_sisuu -= -1}
elsif($condition_sisuu > 10) {$condition = "悪い"; $byouki_sisuu -= 3}
else{$condition = "最悪";}

if ($byouki_sisuu < -1000){$byoumei = "死亡";}
elsif  ($byouki_sisuu < -600){$byoumei = "植物状態";}
elsif  ($byouki_sisuu < -300){$byoumei = "末期ガン";}
elsif  ($byouki_sisuu < -150){$byoumei = "ガン";}
elsif  ($byouki_sisuu < -100){$byoumei = "脳腫瘍";}
elsif  ($byouki_sisuu < -70){$byoumei = "心臓病";}
elsif  ($byouki_sisuu < -40){$byoumei = "結核";}
elsif  ($byouki_sisuu < -20){$byoumei = "肺炎";}
elsif  ($byouki_sisuu < -10){$byoumei = "風邪";}
elsif  ($byouki_sisuu < 0){$byoumei = "風邪ぎみ";}
else {$byoumei = "";}

if ($byoumei){$condition = "<font color=#ff6600>$byoumei</font>";}

#ジョブレベル算出
	$job_level = int($job_keiken / 100) ;

#ホスト保存
	$host = $get_host;
	
#ログイン非表示on off		#ver.1.30
	if ($in{'sanka_hyouzi_on'} eq "on"){$mise_type = "on";}		#ver.1.30
	if ($in{'sanka_hyouzi_on'} eq "off"){$mise_type = "off";}		#ver.1.30

#最終アクセス保存
	$last_access_byou = $access_byou;
	if ($in{'mode'} ne "syokudou" && $in{'mode'} ne "school" && $in{'mode'} ne "gym"){
		$access_byou = $date_sec;
	}

#ログ更新
			&temp_routin;
			&log_kousin($my_log_file,$k_temp);

#個人パラメータ表示
	print <<"EOM";
<table width="100%" border="0" cellspacing="0" cellpadding="3" align=center>
<tr><td valign="top" width=65%>

<table  width="100%"  height="100% "border="0"  cellspacing="0" cellpadding="0" style=" border: $st_win_wak; border-style: solid; border-width: 1px;" bgcolor=$st_win_back><tr><td>
EOM

#ログインモードならコマンドボタンを表示
if ($in{'mode'} eq "login_view"){
	&command_botan;
#制限時間があれば制限表示
#	&error("$koudou_seigen");
		if ($koudou_seigen > 0){
					if ($koudou_seigen > 999){$seigen_width = 5;}elsif($koudou_seigen > 99){$seigen_width = 4;}else{$seigen_width = 3;}
					$ato_nanbyou=$koudou_seigen-($seigenyou_now_time - $last_access_byou);
					$saikoroprint="<span style=\"color:#666666; font-size: 11px; \">　行動できるまであと<input type=text name=cdown value=\"$koudou_seigen\" size=$seigen_width style='color:#ff3300; height: 10px; font-size: 11px; border: 0'>秒</span>";
		}
	
	print <<"EOM";
<table width="98%" border="0" cellspacing="0" cellpadding="5" align=center>
<tr><form  name=form1 method=POST action=\"$script\"><input type=hidden name=mode value="mail_sousin"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}">
<td  style="$message_window">
$happend_ivent$saikoroprint
</td></tr></form></table>
EOM
}
$next_levelup = 100 - ($job_keiken % 100);

#紹介キー
	if ($syokai eq 'yes'){
		$no = $k_id;#koko2007/04/22
		if(!$syoukai_id){
			@arufabet = ('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
			$moji1 = $arufabet[int(rand(26))];
			$moji2 = $arufabet[int(rand(26))];
			$suuji = sprintf("%02d",int(rand(100)));
			$moji3 = $arufabet[int(rand(26))];
			$moji4 = $arufabet[int(rand(26))];
			$syoukai_id = "$moji1$moji2$suuji$moji3$moji4";
#    #ログ更新
			&temp_routin;
			&log_kousin($my_log_file,$k_temp);
		}
		$disp_syokai = "<span class=honbun2>紹介コード：</span>$syoukai_id=$no<br>";# koko2007/04/23
	}
#koko2007/09/27
	if ($dairekutoin eq 'yes2'||$dairekutoin eq 'yes3'){&kingakusyori;} 

#ver.1.3ここから
	$money1 = $money;
	if ($money1 =~ /^[-+]?\d\d\d\d+/g) {
		  for ($i = pos($money1) - 3, $j = $money1 =~ /^[-+]/; $i > $j; $i -= 3) {
   			 substr($money1, $i, 0) = ',';
  		}
	}
	$k_sousisan1 = $k_sousisan;
		if ($k_sousisan1 =~ /^[-+]?\d\d\d\d+/g) {
  			for ($i = pos($k_sousisan1) - 3, $j = $k_sousisan1 =~ /^[-+]/; $i > $j; $i -= 3) {
    			substr($k_sousisan1, $i, 0) = ',';
  		}
	}
#ver.1.3ここまで
#koko2007/04/28
	if (-e $job_keiken_f){
		open (IN,"< $job_keiken_f") || &error("ファイルを開くことが出来ませんでした。");
		eval{ flock (IN, 1); };
		$job_keiken0 = <IN>;
		close(OUT);
	}
	chomp $job_keiken0;
	if ($job_keiken0){$disp_job = "<span class=honbun2>履歴書</span>：$job_keiken0";}
	unless($hatugen >= 1){$hatsugen_mes ="無し";}
	else{$hatsugen_mes ="$hatugen";}
#kokoend
	print <<"EOM";
</td></tr><tr><td>
<img src="$img_dir/st.gif" alt="ステータス"></td></tr><tr><td background="$img_dir/main.gif">
<span class=honbun2>名　前</span>：$name<br>
$disp_syokai
<span class=honbun2  onMouseOver='onMes5(\"総資産＝持ち金 + 普通預金 + スーパー定期 - ローン額\")' onMouseOut='onMes5(\"\")'>持ち金</span>：$money1円<span class=small>（総資産：$k_sousisan1円）</span><br>
<span class=honbun2  onMouseOver='onMes5(\"経験値：$job_keiken（次のレベルアップまであと$next_levelup）　勤務数：$job_kaisuu回\")' onMouseOut='onMes5(\"\")'>職　業</span>：$job（レベル $job_level）<br>

<!-- パワーバーの\表示 koko2005/03/22-->
<span class=honbun2  onMouseOver='onMes5(\"MAX値は身体パラメータを上げることで増加します。\")' onMouseOut='onMes5(\"\")'>身体パワー</span>：$energy （MAX値：$energy_max）<br><img src="$img_dir/$energy_bar" width="$ener_parcent_disp" height="8"><img src="$img_dir/nokori_bar.gif" width="$nokori_parcent" height="8"><br>
<span class=honbun2 onMouseOver='onMes5(\"MAX値は頭脳パラメータを上げることで増加します。\")' onMouseOut='onMes5(\"\")'>頭脳パワー</span>：$nou_energy（MAX値：$nou_energy_max）<br><img src="$img_dir/$nou_energy_bar" width="$nou_ener_parcent_ds" height="8"><img src="$img_dir/nokori_bar.gif" width="$nou_nokori_parcent" height="8"> <br>
<span class=honbun2  onMouseOver='onMes5(\"コンディションは「パワー」の回復度に最も影響を受けます（他にも様々な要因あり）\")' onMouseOut='onMes5(\"\")'>コンディション</span>：$condition<br>
<span class=honbun2>身　　長</span>：$sintyou cm<br>
<span class=honbun2>体　　重</span>：$taijuu kg<br>
<span class=honbun2 onMouseOver='onMes5(\"体格指数(BMI) = 体重(kg) ÷ 身長(m) ÷ 身長(m)\")' onMouseOut='onMes5(\"\")'>体格指数</span>：$BMI（$taikei）<br>
<span class=honbun2  onMouseOver='onMes5(\"あなたの登録してからの挨拶欄での発言回数です。宣伝も含まれます。\")' onMouseOut='onMes5(\"\")'>発言回数</span>：$hatsugen_mes <br>
<span class=honbun2  onMouseOver='onMes5(\"前回の食事：$last_syokuzi_henkan\")' onMouseOut='onMes5(\"\")'>空腹度</span>：$kuuhukudo<br>
<!--ver.1.3ここから-->
EOM
#koko2007/09/27
#配偶者、恋人の表示
					open(COA,"< $couple_file") || &error("$couple_fileに書き込めません");
					eval{ flock (COA, 1); };
						@all_couple = <COA>;
					close(COA);
					foreach (@all_couple){
						($cn_number,$cn_name1,$cn_name2,$cn_joutai,$cn_total_aijou,$cn_omoide1,$cn_omoide2,$cn_omoide3,$cn_omoide4,$cn_omoide5,$cn_kodomo,$cn_yobi1,$cn_yobi2,$cn_yobi3,$cn_yobi4,$cn_yobi5)= split(/<>/);
						if ($name eq "$cn_name1"){
							if ($cn_joutai eq "恋人"){$my_koibito .= "$cn_name2　";}
							elsif ($cn_joutai eq "配偶者"){$my_haiguusya = "$cn_name2";}
						}
						if ($name eq "$cn_name2"){
							if ($cn_joutai eq "恋人"){$my_koibito .= "$cn_name1　";}
							elsif ($cn_joutai eq "配偶者"){$my_haiguusya = "$cn_name1";}
						}
					}
					print <<"EOM";
					<span class=honbun2>配偶者</span>：$my_haiguusya<br>
					<span class=honbun2>恋　人</span>：$my_koibito<br>
					<span class=honbun2>所有物</span>：
EOM
#所有物の表示
#koko2007/10/19
	if(!@my_kounyuu_list){
		$monokiroku_file="./member/$k_id/mono.cgi";
		open(OUT,"< $monokiroku_file") || &error("自分の購入物ファイルが開けません");
		eval{ flock (OUT, 1); };
		@my_kounyuu_list =<OUT>;
		close(OUT);
	}
	foreach (@my_kounyuu_list){
		&syouhin_sprit ($_);
		if($syo_syubetu eq 'ギフト'){
			push @syubetu1,$_;
		}elsif($syo_syubetu eq 'ギフト商品'){
			push @syubetu2,$_;
			if($syo_taikyuu > 0){$morai_itemsuu++;} #koko2007/10/31
		}else{
			push @syubetu3,$_;
			if($syo_taikyuu > 0){$moti_itemsuu++;} #koko2007/10/31
		}
	}
#	$mochikazu = $#syubetu3 + 1; #koko2007/10/31
	print "<font color=#ff0099>購入商品 $moti_itemsuu</font><br>\n"; #koko2007/10/31
	foreach (@syubetu3){
		&syouhin_sprit ($_);

		if ($syo_taikyuu <=0){next;}
		if ($syo_hinmoku eq "いとしき我が家"){
			print <<"EOM";
<table boader=0 width=100%><tr><form method=POST action="original_house.cgi"><td><input type=hidden name=mode value="houmon"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=hidden name=ori_ie_id value="$k_id"><input type=image src='$img_dir/go_home2.gif' width=100 height=10  onMouseOver='onMes5("自分の家に行くことができます。")' onMouseOut='onMes5("")'></td></form></tr></table><!--ver.1.40-->
EOM
			next;
		}

#koko2007/04/28  持ってる数を出す。
		if ($kazu_disp eq 'yes'){
			if($syo_siyou_date + ($syo_kankaku*60) > time || (($syo_syubetu eq "食料品" || $syo_syubetu eq "ファーストフード") && time < $last_syokuzi + ($syokuzi_kankaku*60))){
				print "★$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}elsif($syo_syubetu eq "ギフト"){
				print "□$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}else{
				print "○$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}
		}else{
#koko2007/03/17
			if($syo_siyou_date + ($syo_kankaku*60) > time || (($syo_syubetu eq "食料品" || $syo_syubetu eq "ファーストフード") && time < $last_syokuzi + ($syokuzi_kankaku*60))){
				print "★$syo_hinmoku ";
			}elsif($syo_syubetu eq "ギフト"){
				print "□$syo_hinmoku ";
			}else{
				print "○$syo_hinmoku ";
			}
		}
#		print <<"EOM";
#		○$syo_hinmoku　
#EOM
#kokoend2007/03/17
	}
#	$mochikazu = $#syubetu2 + 1; #koko2007/10/31
	print "<br><font color=#ff0099>ギフトもらった商品 $morai_itemsuu</font><br>\n"; #koko2007/10/31
	foreach (@syubetu2){
		&syouhin_sprit ($_);

		if ($syo_taikyuu <=0){next;}
		if ($syo_hinmoku eq "いとしき我が家"){
			print <<"EOM";
<table boader=0 width=100%><tr><form method=POST action="original_house.cgi"><td><input type=hidden name=mode value="houmon"><input type=hidden name=name value="$in{'name'}"><input type=hidden name=pass value="$in{'pass'}"><input type=hidden name=k_id value="$k_id"><input type=hidden name=town_no value="$in{'town_no'}"><input type=hidden name=ori_ie_id value="$k_id"><input type=image src='$img_dir/go_home2.gif' width=100 height=10  onMouseOver='onMes5("自分の家に行くことができます。")' onMouseOut='onMes5("")'></td></form></tr></table><!--ver.1.40-->
EOM
			next;
		}
		if ($kazu_disp eq 'yes'){
			if($syo_siyou_date + ($syo_kankaku*60) > time || (($syo_syubetu eq "食料品" || $syo_syubetu eq "ファーストフード") && time < $last_syokuzi + ($syokuzi_kankaku*60))){
				print "★$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}elsif($syo_syubetu eq "ギフト"){
				print "□$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}else{
				print "○$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}
		}else{
			if($syo_siyou_date + ($syo_kankaku*60) > time || (($syo_syubetu eq "食料品" || $syo_syubetu eq "ファーストフード") && time < $last_syokuzi + ($syokuzi_kankaku*60))){
				print "★$syo_hinmoku ";
			}elsif($syo_syubetu eq "ギフト"){
				print "□$syo_hinmoku ";
			}else{
				print "○$syo_hinmoku ";
			}
		}
	}
	$mochikazu = $#syubetu1 + 1;
	print "<br><font color=#ff0099>ギフト送る商品 $mochikazu</font><br>\n";
	foreach (@syubetu1){
		&syouhin_sprit ($_);

		if ($syo_taikyuu <=0){next;}
		if ($kazu_disp eq 'yes'){
			if($syo_siyou_date + ($syo_kankaku*60) > time || (($syo_syubetu eq "食料品" || $syo_syubetu eq "ファーストフード") && time < $last_syokuzi + ($syokuzi_kankaku*60))){
				print "★$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}elsif($syo_syubetu eq "ギフト"){
				print "□$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}else{
				print "○$syo_hinmoku($syo_taikyuu$syo_taikyuu_tani) ";
			}
		}else{
			if($syo_siyou_date + ($syo_kankaku*60) > time || (($syo_syubetu eq "食料品" || $syo_syubetu eq "ファーストフード") && time < $last_syokuzi + ($syokuzi_kankaku*60))){
				print "★$syo_hinmoku ";
			}elsif($syo_syubetu eq "ギフト"){
				print "□$syo_hinmoku ";
			}else{
				print "○$syo_hinmoku ";
			}
		}
	}
#end2007/10/19
#ver.1.3ここまで
	$nouryoku_goukeiti = $kokugo + $suugaku + $rika + $syakai + $eigo + $ongaku + $bijutu + $looks + $tairyoku + $kenkou + $speed + $power + $wanryoku + $kyakuryoku;
	foreach (6..22){
		$nouryoku_bar[$_] = int (($nouryoku_suuzi_hairetu[$_] / $nouryoku_goukeiti)*100*$nouryoku_goukeiti*0.002);
	}

#koko2007/09/27
		if($hozontown eq 'yes'){$disp_tag = "<input type=\"hidden\" name=\"town_no\" value=\"$ck{'town_no'}\">\n";}else{$disp_tag="";}
		if ($dairekutoin eq 'yes2' || $dairekutoin eq 'yes3'){
			$disp_tag = "<select name=\"town_no\">\n";
			$i=0;
			foreach (@town_hairetu){
				if($machikakushi eq 'yes'){#koko2007/10/21
					if($_ eq $kakushimachi_name && $kakushimachi_name || $_ eq $kakushimachi_name1 && $kakushimachi_name1 || $_ eq $kakushimachi_name2 && $kakushimachi_name2 || $_ eq $kakushimachi_name3 && $kakushimachi_name3 || $_ eq $kakushimachi_name4 && $kakushimachi_name4){
						$i++;
						next;
					}
				}
				$disp_tag .= "<option value=\"$i\">$_</option>\n";
				$i++
			}
			$disp_tag .= "</select><br>\n";

		}

sub kingakusyori{ #内部におかれています・
	if(!$in{'fragu'}){
		$in{'mae_town'} = $in{'town_no'};
		$in{'fragu'} = 1;
	}elsif($in{'mae_town'} != $in{'town_no'}){
		$money =~ s/\,//g;
		$money -= 100000;
#ログ更新
		&temp_routin;
		&log_kousin($my_log_file,$k_temp);

		$in{'mae_town'} = $in{'town_no'};

	}
}

	print <<"EOM";
<br>$disp_job<br><!-- #koko2007/04/28 -->
<!-- ver.1.30 -->
EOM
if($dairekutoin eq 'yes2' || $dairekutoin eq 'yes3'){
	print <<"EOM";
	</td></tr><tr><td><img src="$img_dir/qj.gif" alt="クイックジャンプ"></td></tr><tr><td background="$img_dir/qjback.gif"><BR>
<div align=center>
<form method=POST action="$script">
<input type=hidden name=mode value="login_view">
<input type=hidden name=name value="$in{'name'}">
<input type=hidden name=pass value="$in{'pass'}">
<input type=hidden name=mae_town value="$in{'mae_town'}">
<input type=hidden name=fragu value="$in{'fragu'}">
$disp_tag<font color=#ff0000>※この移動には10万かかります。</font><br>
<input type="submit" value='GO!'></form></div></td></tr>
EOM
	} #kokoend2007/09/27
	print <<"EOM";
</td></tr><tr><td><img src="$img_dir/at.gif" alt="諸注意"></td></tr><tr><td bgcolor=#DDDDFF>
<div class=small>
※コンディションによって仕事で得られる経験値が変わってきます。<BR>
※ゲームを終了するときは必ず保存ボタンを押してください。
</div></td></tr><tr><td><img src="$img_dir/bc.gif" alt="掲示板・チャット"></td></tr><tr><td bgcolor=#FFDDEE><font color=#ff0000>
<a href="http://matsupla.chatx.whocares.jp/" target=_blank>新型新進グダニスク帝国公式チャット</a><br><small>
入室は
<img alt="" width="24" height="12" src="http://matsupla.chatx.whocares.jp/mcond/users.png" />人、
ROMは
<img alt="" width="24" height="12" src="http://matsupla.chatx.whocares.jp/mcond/roms.png" />人です。</small><br><br>
<a href="http://snow.advenbbs.net/bbs/hira.htm" target=_blank>ＴＯＷＮ総合外部掲示板</a><br>
<small>ＴＯＷＮの総合的なトラブルはこちらへ。</small><br>
<br>
<a href="http://bbs-r.com/m/g10works/" target=_blank>雑談掲示板</a><br>
<small>総合的な用件や雑談などはこちら！</small><br>
<br>
<a href="http://w1.oekakies.com/p/g10works/p.cgi" target=_blank>G10☆らくがきわーるど</a><br>
<small>絵をかきたくなったらどうぞ☆</small><br></font>
</td></tr><tr><td><img src="$img_dir/stf.gif" alt="スタッフ"></td></tr><tr><td>
<span class=honbun2>原作</span>：桑田湘平様<BR>
<span class=honbun2>ベーススクリプト</span>：たっちゃん様<BR>
<span class=honbun2>改造</span>：ヒラリラー・松雪<BR>
<span class=honbun2>制作</span>：G10－Project<BR>
<span class=honbun2>管理人</span>：$admin_name<BR>
<span class=honbun2>元老院議員</span>：$genrou_name[0]・$genrou_name[1]<BR>
<span class=honbun2>副管理人</span>：$fukukanrinin_name[0]・$fukukanrinin_name[1]<BR>
<span class=honbun2>臨時副管理人</span>：$rinjiadmin
<br>
</td><tr>
</table>
</td><td align=right>
<!-- ver.1.30ここから -->
<table border="0"  cellspacing="0" cellpadding="0" style=" border: $st_win_wak; border-style: solid; border-width: 1px; font-size: 11px; line-height: 11px; color: #006699" bgcolor=$st_win_back width=100% height=100%>
<tr><td colspan=2><img src="$img_dir/pm.gif" alt="パラメータ"></td></tr>
<tr bgcolor=#DDFFAA><td colspan=2 align=center><BR><span class=tyuu>頭　脳</span><BR></td></tr>
<tr bgcolor=#DDFFAA><td align=right>国語：</td><td><table class=small><tr><td width=$nouryoku_bar[6] bgcolor=#cc0000><font color=#ffffff>$kokugo</font></td></tr></table></td></tr>
<tr bgcolor=#DDFFAA><td align=right>数学：</td><td><table class=small><tr><td width=$nouryoku_bar[7] bgcolor=#0066cc><font color=#ffffff>$suugaku</font></td></tr></table></td></tr>
<tr bgcolor=#DDFFAA><td align=right>理科：</td><td><table class=small><tr><td width=$nouryoku_bar[8] bgcolor=#336633><font color=#ffffff>$rika</font></td></tr></table></td></tr>
<tr bgcolor=#DDFFAA><td align=right>社会：</td><td><table class=small><tr><td width=$nouryoku_bar[9] bgcolor=#ff6600><font color=#ffffff>$syakai</font></td></tr></table></td></tr>
<tr bgcolor=#DDFFAA><td align=right>英語：</td><td><table class=small><tr><td width=$nouryoku_bar[10] bgcolor=#ff0099><font color=#ffffff>$eigo</font></td></tr></table></td></tr>
<tr bgcolor=#DDFFAA><td align=right>音楽：</td><td><table class=small><tr><td width=$nouryoku_bar[11] bgcolor=#9900cc><font color=#ffffff>$ongaku</font></td></tr></table></td></tr>
<tr bgcolor=#DDFFAA><td align=right>美術：</td><td><table class=small><tr><td width=$nouryoku_bar[12] bgcolor=#6666ff><font color=#ffffff>$bijutu</font></td></tr></table></td></tr>
<tr style=" border: $st_win_wak; border-style: solid; border-top-width: 1px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px">
<td colspan=2 align=center bgcolor=#CCFFFF><BR><span class=tyuu>身　体</span><BR></td></tr>
<tr bgcolor=#CCFFFF><td  align=right nowrap>ルックス：</td><td><table class=small><tr><td width=$nouryoku_bar[13] bgcolor=#ccff66>$looks</td></tr></table></td></tr>
<tr bgcolor=#CCFFFF><td align=right>体力：</td><td><table class=small><tr><td width=$nouryoku_bar[14] bgcolor=#ffcc00>$tairyoku</td></tr></table></td></tr>
<tr bgcolor=#CCFFFF><td align=right>健康：</td><td><table class=small><tr><td width=$nouryoku_bar[15] bgcolor=#66ff66>$kenkou</td></tr></table></td></tr>
<tr bgcolor=#CCFFFF><td align=right nowrap>スピード：</td><td><table class=small><tr><td width=$nouryoku_bar[16] bgcolor=#99ffcc>$speed</td></tr></table></td></tr>
<tr bgcolor=#CCFFFF><td align=right>パワー：</td><td><table class=small><tr><td width=$nouryoku_bar[17] bgcolor=#ff9966>$power</td></tr></table></td></tr>
<tr bgcolor=#CCFFFF><td align=right>腕力：</td><td><table class=small><tr><td width=$nouryoku_bar[18] bgcolor=#ff9999>$wanryoku</td></tr></table></td></tr>
<tr bgcolor=#CCFFFF><td align=right>脚力：</td><td><table class=small><tr><td width=$nouryoku_bar[19] bgcolor=#ff99cc>$kyakuryoku</td></tr></table></td></tr>
<tr style=" border: $st_win_wak; border-style: solid; border-top-width: 1px; border-right-width: 0px; border-bottom-width: 0px; border-left-width: 0px">
<td colspan=2 align=center bgcolor=#FFBBEE><BR><span class=tyuu>その他</span><BR></td></tr>
<tr bgcolor=#FFBBEE><td align=right>LOVE：</td><td><table class=small><tr><td width=$nouryoku_bar[20] bgcolor=#ff9999>$love</td></tr></table></td></tr>
<tr bgcolor=#FFBBEE><td align=right>面白さ：</td><td><table class=small><tr><td width=$nouryoku_bar[21] bgcolor=#ffff66>$unique</td></tr></table></td></tr>
<tr bgcolor=#FFBBEE><td align=right>エッチ：</td><td><table class=small><tr><td width=$nouryoku_bar[22] bgcolor=#cc99cc>$etti</td></tr></table></td></tr>
</table>
<!-- ver.1.30ここまで -->
</td></tr></table>
EOM
}

###ログバックアップ処理
sub list_log_backup {
#	if ($in{'admin_pass'} ne $admin_pass){&error("パスワードが違います");}		#ver.1.22
#フォルダー内のファイル名を取得してバックアップログを作成
					use DirHandle;
					$dir = new DirHandle ("./log_dir");
					while($file_name = $dir->read){ #1つ読み込んで$folder_nameに代入
							if($file_name eq '.' || $file_name eq '..' || $file_name =~ /^backup_/ || $file_name eq '.DS_Store'){next;}
							my $backup_name = "backup_" ."$file_name";
							open (BK,"< ./log_dir/$file_name")  || &error("Open Error : ./log_dir/$file_name");
							eval{ flock (BK, 1); };
							my @back_data = <BK>;
							close (BK);
							if (@back_data != ""){
								open (BKO,">./log_dir/backup_dir/$backup_name") || &error("Write Error : ./log_dir/backup_dir/$backup_name");
								eval{ flock (BKO, 2); };
								print BKO @back_data;
								close (BKO);
							}
					}
					$dir->close;  #ディレクトリを閉じる
}