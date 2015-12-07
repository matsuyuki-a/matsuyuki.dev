#######################################
#クッキーネームの変更
$COOKIE_NAME = 'town_maker2'; #koko2006/11/13 

# 多重登録許可の名前　2006/11/16 # ('許可名前１','許可名前２','3');と続きます。
@tazyu_kyoka = ('ヒラリラー','ヒラリラー２','まつぷら','ピーター','蟋蟀','岳','tom','MO','サマンサ','あり','どら','ちはこ◎','シマ','ふぐ','パール','愛','恋次','未玲*','カメ','剣心','千歳','キノ','栞奈','コシン','ゼロ','アイスマン','mizu','ウィアード');

# 行動時間制限個別設定
#	%work_seigen = ('テスト',1,'たっちゃん2',10);#必ずセットで設定。分
# 行動待機時間
	%seigen = ('ヒラリラー',1,'たんちゃん',15,'マー坊',20);#名前と時間が組になります。秒

# 2007/02/11 town_lib.pl game.cgi $idou_jikan($k_yobi5) 割り当て　移動中の更新不許可。

#特定の街を隠す 'yes'
$machikakushi = 'yes';
#隠す街の番号　0 から始まる
$kakushimachi_no = '7';
$kakushimachi_no1 = '';
$kakushimachi_no2 = '';
$kakushimachi_no3 = '';
$kakushimachi_no4 = '';
#隠す街の名前
$kakushimachi_name = '秘密都市ロンヴァエ';
$kakushimachi_name1 = '';
$kakushimachi_name2 = '';
$kakushimachi_name3 = '';
$kakushimachi_name4 = '';

# 音を出すか(yes,no)
$otdashi = 'yes';

# 掲示板へのタグ書き込みを無効にする。(yes,no)
$kigiban_tag = 'no';

# eval{ flock (OUT, 2); }を使う。koko2007/09/21
$eval_flock = 'yes';

#eval{ flock (OUT, 2); };town_lib.cgi全体に付ける。koko2007/09/02 for (0..50){$i=0;} sleep 2007/06/19
########################################
#sub message　の
# &header();#koko 2005/05/31 変更 class=@_[0]
########################################
sub kozin_sprit{
($k_id,$name,$pass,$money,$bank,$job,$kokugo,$suugaku,$rika,$syakai,$eigo,$ongaku,$bijutu,$looks,$tairyoku,$kenkou,$speed,$power,$wanryoku,$kyakuryoku,$love,$unique,$etti,$first_access,$kounyuu,$sex,$access_byou,$access_time,$host,$house_name,$house_type,$byoumei,$mise_type,$last_mailcheck,$super_teiki,$energy,$last_ene_time,$next_tra,$last_syokuzi_split,$sintyou,$taijuu,$nou_energy,$last_nouene_time,$job_keiken,$job_kaisuu,$last_school,$byouki_sisuu,$loan_nitigaku,$loan_kaisuu,$k_sousisan,$jobsyu,$k_yobi3,$k_yobi4,$idou_jikan,$brauza,$shiharai,$syoukai_id,$aki,$oto,$hatugen)= split(/<>/);
#k_yobi3＝多重登録者　jobsyu＝マスターした職業　　kounyuu＝アイコンURL　house_name＝最後に仕事した時間　house_type＝配偶者のID番号　mise_type＝ログイン非表示　last_mailcheck＝未使用 #2006/11/29 $brauza 追加 $shiharai=銀行の支払い形式 idou_jikan $syoukai_id,$money2 2007/04/22
#koko2006/11/14
($last_syokuzi,$syo_sake,$syo_dezato,$syo_dorinku)=split(/=/, $last_syokuzi_split);
chomp $brauza; #koko2007/02/11
chomp $shiharai; #koko2007/02/11
chomp $syoukai_id;
chomp $aki;
chomp $oto; #2007/10/26
chomp $hatugen; #2007/10/26
#kokoend
}
 #2006/11/29 $brauza 追加
sub kozin_sprit2 {
($k_id,$name,$pass,$money,$bank,$job,$kokugo,$suugaku,$rika,$syakai,$eigo,$ongaku,$bijutu,$looks,$tairyoku,$kenkou,$speed,$power,$wanryoku,$kyakuryoku,$love,$unique,$etti,$first_access,$kounyuu,$sex,$access_byou,$access_time,$host,$house_name,$house_type,$byoumei,$mise_type,$last_mailcheck,$super_teiki,$energy,$last_ene_time,$next_tra,$last_syokuzi_split,$sintyou,$taijuu,$nou_energy,$last_nouene_time,$job_keiken,$job_kaisuu,$last_school,$byouki_sisuu,$loan_nitigaku,$loan_kaisuu,$k_sousisan,$jobsyu,$k_yobi3,$k_yobi4,$idou_jikan,$brauza,$shiharai,$syoukai_id,$aki,$oto,$hatugen)= split(/<>/,@_[0]);
@nouryoku_suuzi_hairetu =  split(/<>/,@_[0]);
chomp $nouryoku_suuzi_hairetu[54]; #koko2007/04/22
chomp $nouryoku_suuzi_hairetu[55]; #koko2007/04/22
chomp $nouryoku_suuzi_hairetu[56]; #koko2007/04/22
chomp $nouryoku_suuzi_hairetu[57]; #koko2007/04/22
chomp $nouryoku_suuzi_hairetu[58]; #koko2007/10/26
chomp $nouryoku_suuzi_hairetu[59]; #koko2007/10/26
#koko2006/11/14　#koko2007/02/10$shiharai idou_jikan
($last_syokuzi,$syo_sake,$syo_dezato,$syo_dorinku)=split(/=/, $last_syokuzi_split);
chomp $brauza; #koko2007/02/11
chomp $shiharai; #koko2007/02/11
chomp $syoukai_id;
chomp $aki;
chomp $oto; #2007/10/26
chomp $hatugen; #2007/10/26
#kokoend
}

sub temp_routin {
#koko2006/11/14
	$last_syokuzi_split = "$last_syokuzi=$syo_sake=$syo_dezato=$syo_dorinku";
#kokoend #2006/11/29 $brauza 追加 #koko2007/02/10 $shiharai 2007/02/11=$idou_jikan($k_yobi5)
	$k_temp="$k_id<>$name<>$pass<>$money<>$bank<>$job<>$kokugo<>$suugaku<>$rika<>$syakai<>$eigo<>$ongaku<>$bijutu<>$looks<>$tairyoku<>$kenkou<>$speed<>$power<>$wanryoku<>$kyakuryoku<>$love<>$unique<>$etti<>$first_access<>$kounyuu<>$sex<>$access_byou<>$access_time<>$host<>$house_name<>$house_type<>$byoumei<>$mise_type<>$last_mailcheck<>$super_teiki<>$energy<>$last_ene_time<>$next_tra<>$last_syokuzi_split<>$sintyou<>$taijuu<>$nou_energy<>$last_nouene_time<>$job_keiken<>$job_kaisuu<>$last_school<>$byouki_sisuu<>$loan_nitigaku<>$loan_kaisuu<>$k_sousisan<>$jobsyu<>$k_yobi3<>$k_yobi4<>$idou_jikan<>$brauza<>$shiharai<>$syoukai_id<>$aki<>$oto<>$hatugen<>\n";
}

sub aite_sprit {
($aite_k_id,$aite_name,$aite_pass,$aite_money,$aite_bank,$aite_job,$aite_kokugo,$aite_suugaku,$aite_rika,$aite_syakai,$aite_eigo,$aite_ongaku,$aite_bijutu,$aite_looks,$aite_tairyoku,$aite_kenkou,$aite_speed,$aite_power,$aite_wanryoku,$aite_kyakuryoku,$aite_love,$aite_unique,$aite_etti,$aite_first_access,$aite_kounyuu,$aite_sex,$aite_access_byou,$aite_access_time,$aite_host,$aite_house_name,$aite_house_type,$aite_byoumei,$aite_mise_type,$aite_last_mailcheck,$aite_super_teiki,$aite_energy,$aite_last_ene_time,$aite_next_tra,$aite_last_syokuzi,$aite_sintyou,$aite_taijuu,$aite_nou_energy,$aite_last_nouene_time,$aite_job_keiken,$aite_job_kaisuu,$aite_last_school,$aite_byouki_sisuu,$aite_loan_nitigaku,$aite_loan_kaisuu,$aite_sousisan,$aite_jobsyu,$aite_yobi3,$aite_yobi4,$aite_idou_jikan,$aite_brauza,$aite_shiharai,$aite_syoukai_id,$aite_aki,$aite_oto,$aite_hatugen)= split(/<>/,@_[0]);#$aite_brauza,$aite_shiharai koko2007/02/10 idou_jikan
@aite_nouryoku_suuzi_hairetu =  split(/<>/,@_[0]);
#aite_yobi3＝多重登録
chomp $aite_brauza; #koko2007/02/11   koko2007/04/22
chomp $aite_shiharai; #koko2007/02/11 koko2007/04/22
chomp $aite_syoukai_id;
chomp $aite_aki;
chomp $aite_oto;
chomp $aite_hatugen
}

sub aite_temp_routin {
	$aite_k_temp="$aite_k_id<>$aite_name<>$aite_pass<>$aite_money<>$aite_bank<>$aite_job<>$aite_kokugo<>$aite_suugaku<>$aite_rika<>$aite_syakai<>$aite_eigo<>$aite_ongaku<>$aite_bijutu<>$aite_looks<>$aite_tairyoku<>$aite_kenkou<>$aite_speed<>$aite_power<>$aite_wanryoku<>$aite_kyakuryoku<>$aite_love<>$aite_unique<>$aite_etti<>$aite_first_access<>$aite_kounyuu<>$aite_sex<>$aite_access_byou<>$aite_access_time<>$aite_host<>$aite_house_name<>$aite_house_type<>$aite_byoumei<>$aite_mise_type<>$aite_last_mailcheck<>$aite_super_teiki<>$aite_energy<>$aite_last_ene_time<>$aite_next_tra<>$aite_last_syokuzi<>$aite_sintyou<>$aite_taijuu<>$aite_nou_energy<>$aite_last_nouene_time<>$aite_job_keiken<>$aite_job_kaisuu<>$aite_last_school<>$aite_byouki_sisuu<>$aite_loan_nitigaku<>$aite_loan_kaisuu<>$aite_sousisan<>$aite_jobsyu<>$aite_yobi3<>$aite_yobi4<>$aite_idou_jikan<>$aite_brauza<>$aite_shiharai<>$aite_syoukai_id<>$aite_aki<>$aite_oto<>$aite_hatugen<>\n";#$aite_brauza<>$aite_shiharai<>$aite_oto<>$aite_hatugen koko2007/02/10 idou_jikan
	@aite_k_temp = ();
	push (@aite_k_temp,$aite_k_temp);
}

sub list_sprit{
($list_k_id,$list_name,$list_pass,$list_money,$list_bank,$list_job,$list_kokugo,$list_suugaku,$list_rika,$list_syakai,$list_eigo,$list_ongaku,$list_bijutu,$list_looks,$list_tairyoku,$list_kenkou,$list_speed,$list_power,$list_wanryoku,$list_kyakuryoku,$list_love,$list_unique,$list_etti,$list_first_access,$list_kounyuu,$list_sex,$list_access_byou,$list_access_time,$list_host,$list_house_name,$list_house_type,$list_byoumei,$list_mise_type,$list_last_mailcheck,$list_super_teiki,$list_energy,$list_last_ene_time,$list_next_tra,$list_last_syokuzi,$list_sintyou,$list_taijuu,$list_nou_energy,$list_last_nouene_time,$list_job_keiken,$list_job_kaisuu,$list_last_school,$list_byouki_sisuu,$list_loan_nitigaku,$list_loan_kaisuu,$list_sousisan,$list_jobsyu,$list_k_yobi3,$list_k_yobi4,$list_idou_jikan,$list_k_brauza,$list_k_shiharai,$list_syoukai_id,$list_aki,$list_oto,$list_hatugen)= split(/<>/,@_[0]);#$list_k_brauza,$list_k_shiharai koko2007/02/10 idou_jikan
chomp $list_k_brauza; #koko2007/02/11
chomp $list_k_shiharai; #koko2007/02/11
chomp $list_syoukai_id;
chomp $list_aki;
chomp $list_oto; 
chomp $list_hatugen;
#kokoend
}

sub list_temp {
	$list_temp="$list_k_id<>$list_name<>$list_pass<>$list_money<>$list_bank<>$list_job<>$list_kokugo<>$list_suugaku<>$list_rika<>$list_syakai<>$list_eigo<>$list_ongaku<>$list_bijutu<>$list_looks<>$list_tairyoku<>$list_kenkou<>$list_speed<>$list_power<>$list_wanryoku<>$list_kyakuryoku<>$list_love<>$list_unique<>$list_etti<>$list_first_access<>$list_kounyuu<>$list_sex<>$list_access_byou<>$list_access_time<>$list_host<>$list_house_name<>$list_house_type<>$list_byoumei<>$list_mise_type<>$list_last_mailcheck<>$list_super_teiki<>$list_energy<>$list_last_ene_time<>$list_next_tra<>$list_last_syokuzi<>$list_sintyou<>$list_taijuu<>$list_nou_energy<>$list_last_nouene_time<>$list_job_keiken<>$list_job_kaisuu<>$list_last_school<>$list_byouki_sisuu<>$list_loan_nitigaku<>$list_loan_kaisuu<>$list_sousisan<>$list_jobsyu<>$list_k_yobi3<>$list_k_yobi4<>$list_idou_jikan<>$list_k_brauza<>$list_k_shiharai<>$list_syoukai_id<>$list_aki<>$list_oto<>$list_hatugen<>\n";#$list_k_brauza<>$list_k_shiharai<> koko2007/02/10 idou_jikan
}

sub main_town_sprit{
($mt_zinkou,$mt_keizai,$mt_hanei,$mt_today,$mt_orosiflag,$mt_t_time,$mt_y_time,$mt_syokudouflag,$mt_departflag,$total_ninzuu,$mt_yobi8,$mt_yobi9,$mt_yobi10,$mt_yobi11,$mt_yobi12,$mt_yobi13,$mt_yobi14)= split(/<>/,@_[0]);
#mt_yobi8 = 創設日（お店の売り上げまたは掲示板書き込みがあった日）mt_yobi9＝卸更新日時　total_ninzuu＝未使用
}

sub main_town_temp{
	$mt_temp="$mt_zinkou<>$mt_keizai<>$mt_hanei<>$mt_today<>$mt_orosiflag<>$mt_t_time<>$mt_y_time<>$mt_syokudouflag<>$mt_departflag<>$total_ninzuu<>$mt_yobi8<>$mt_yobi9<>$mt_yobi10<>$mt_yobi11<>$mt_yobi12<>$mt_yobi13<>$mt_yobi14<>\n";
	@mt_temp = ();
	push (@mt_temp,$mt_temp);
}

sub town_sprit{
($town_name,$zinkou,$keizai,$hanei,$t_x0,$t_x1,$t_x2,$t_x3,$t_x4,$t_x5,$t_x6,$t_x7,$t_x8,$t_x9,$t_x10,$t_x11,$t_x12,$t_x13,$t_x14,$t_x15,$t_x16,$t_a0,$t_a1,$t_a2,$t_a3,$t_a4,$t_a5,$t_a6,$t_a7,$t_a8,$t_a9,$t_a10,$t_a11,$t_a12,$t_a13,$t_a14,$t_a15,$t_a16,$t_b0,$t_b1,$t_b2,$t_b3,$t_b4,$t_b5,$t_b6,$t_b7,$t_b8,$t_b9,$t_b10,$t_b11,$t_b12,$t_b13,$t_b14,$t_b15,$t_b16,$t_c0,$t_c1,$t_c2,$t_c3,$t_c4,$t_c5,$t_c6,$t_c7,$t_c8,$t_c9,$t_c10,$t_c11,$t_c12,$t_c13,$t_c14,$t_c15,$t_c16,$t_d0,$t_d1,$t_d2,$t_d3,$t_d4,$t_d5,$t_d6,$t_d7,$t_d8,$t_d9,$t_d10,$t_d11,$t_d12,$t_d13,$t_d14,$t_d15,$t_d16,$t_e0,$t_e1,$t_e2,$t_e3,$t_e4,$t_e5,$t_e6,$t_e7,$t_e8,$t_e9,$t_e10,$t_e11,$t_e12,$t_e13,$t_e14,$t_e15,$t_e16,$t_f0,$t_f1,$t_f2,$t_f3,$t_f4,$t_f5,$t_f6,$t_f7,$t_f8,$t_f9,$t_f10,$t_f11,$t_f12,$t_f13,$t_f14,$t_f15,$t_f16,$t_g0,$t_g1,$t_g2,$t_g3,$t_g4,$t_g5,$t_g6,$t_g7,$t_g8,$t_g9,$t_g10,$t_g11,$t_g12,$t_g13,$t_g14,$t_g15,$t_g16,$t_h0,$t_h1,$t_h2,$t_h3,$t_h4,$t_h5,$t_h6,$t_h7,$t_h8,$t_h9,$t_h10,$t_h11,$t_h12,$t_h13,$t_h14,$t_h15,$t_h16,$t_i0,$t_i1,$t_i2,$t_i3,$t_i4,$t_i5,$t_i6,$t_i7,$t_i8,$t_i9,$t_i10,$t_i11,$t_i12,$t_i13,$t_i14,$t_i15,$t_i16,$t_j0,$t_j1,$t_j2,$t_j3,$t_j4,$t_j5,$t_j6,$t_j7,$t_j8,$t_j9,$t_j10,$t_j11,$t_j12,$t_j13,$t_j14,$t_j15,$t_j16,$t_k0,$t_k1,$t_k2,$t_k3,$t_k4,$t_k5,$t_k6,$t_k7,$t_k8,$t_k9,$t_k10,$t_k11,$t_k12,$t_k13,$t_k14,$t_k15,$t_k16,$t_l0,$t_l1,$t_l2,$t_l3,$t_l4,$t_l5,$t_l6,$t_l7,$t_l8,$t_l9,$t_l10,$t_l11,$t_l12,$t_l13,$t_l14,$t_l15,$t_l16,$t_m0,$t_m1,$t_m2,$t_m3,$t_m4,$t_m5,$t_m6,$t_m7,$t_m8,$t_m9,$t_m10,$t_m11,$t_m12,$t_m13,$t_m14,$t_m15,$t_m16,$t_n0,$t_n1,$t_n2,$t_n3,$t_n4,$t_n5,$t_n6,$t_n7,$t_n8,$t_n9,$t_n10,$t_n11,$t_n12,$t_n13,$t_n14,$t_n15,$t_n16,$tika,$t_yobi2,$t_yobi3,$t_yobi4,$t_yobi5,$t_yobi6,$t_yobi7)= split(/<>/,@_[0]);
@town_sprit_matrix =  split(/<>/,@_[0]);
#t_yobi2 = 街設立日（その街の売り上げがあった日）
}

sub job_sprit {
($job_no,$job_name,$job_kokugo,$job_suugaku,$job_rika,$job_syakai,$job_eigo,$job_ongaku,$job_bijutu,$job_BMI_min,$job_BMI_max,$job_looks,$job_tairyoku,$job_kenkou,$job_speed,$job_power,$job_wanryoku,$job_kyakuryoku,$job_kyuuyo,$job_siharai,$job_love,$job_unique,$job_etti,$job_sex,$job_sintyou,$job_energy,$job_nou_energy,$job_rank,$job_syurui,$job_bonus,$job_syoukyuuritu)= split(/<>/,@_[0]);
}

sub syouhin_sprit{
($syo_syubetu,$syo_hinmoku,$syo_kokugo,$syo_suugaku,$syo_rika,$syo_syakai,$syo_eigo,$syo_ongaku,$syo_bijutu,$syo_kouka,$syo_looks,$syo_tairyoku,$syo_kenkou,$syo_speed,$syo_power,$syo_wanryoku,$syo_kyakuryoku,$syo_nedan,$syo_love,$syo_unique,$syo_etti,$syo_taikyuu,$syo_taikyuu_tani,$syo_kankaku,$syo_zaiko,$syo_cal,$syo_siyou_date,$syo_sintai_syouhi,$syo_zunou_syouhi,$syo_comment,$syo_kounyuubi,$tanka,$tokubai)= split(/<>/,@_[0]);
}

sub syouhin_temp{
	$syo_temp="$syo_syubetu<>$syo_hinmoku<>$syo_kokugo<>$syo_suugaku<>$syo_rika<>$syo_syakai<>$syo_eigo<>$syo_ongaku<>$syo_bijutu<>$syo_kouka<>$syo_looks<>$syo_tairyoku<>$syo_kenkou<>$syo_speed<>$syo_power<>$syo_wanryoku<>$syo_kyakuryoku<>$syo_nedan<>$syo_love<>$syo_unique<>$syo_etti<>$syo_taikyuu<>$syo_taikyuu_tani<>$syo_kankaku<>$syo_zaiko<>$syo_cal<>$syo_siyou_date<>$syo_sintai_syouhi<>$syo_zunou_syouhi<>$syo_comment<>$syo_kounyuubi<>$tanka<>$tokubai<>\n";
}

sub gym_sprit{
($gym_name,$gym_looks,$gym_tairyoku,$gym_kenkou,$gym_speed,$gym_power,$gym_wanryoku,$gym_kyakuryoku,$gym_nedan,$gym_kouka,$gym_etti,$gym_kankaku,$gym_syouhi)= split(/<>/,@_[0]);
}


sub school_sprit{
($sc_name,$sc_kokugo,$sc_suugaku,$sc_rika,$sc_syakai,$sc_eigo,$sc_ongaku,$sc_bijutu,$sc_nedan,$sc_kouka,$sc_syouhi)= split(/<>/,@_[0]);
}

sub ginkou_meisai_sprit {
($meisai_date,$meisai_naiyou,$meisai_hikidasi,$meisai_azuke,$meisai_zandaka,$meisai_syubetu)= split(/<>/,@_[0]);
}

sub ori_ie_sprit {
($ori_k_id,$ori_ie_name,$ori_ie_setumei,$ori_ie_image,$ori_ie_syubetu,$ori_ie_kentikubi,$ori_ie_town,$ori_ie_tateziku,$ori_ie_yokoziku,$ori_ie_sentaku_point,$ori_ie_rank,$ori_ie_rank2,$ori_ie_yobi8,$ori_ie_yobi9,$ori_ie_jyouhou)= split(/<>/,@_[0]); # $ori_ie_jyouhou koko2007/05/04 $ori_ie_rank2 2007/08/02
}

sub ori_ie_temp {
$ori_ie_temp = "$ori_k_id<>$ori_ie_name<>$ori_ie_setumei<>$ori_ie_image<>$ori_ie_syubetu<>$ori_ie_kentikubi<>$ori_ie_town<>$ori_ie_tateziku<>$ori_ie_yokoziku<>$ori_ie_sentaku_point<>$ori_ie_rank<>$ori_ie_rank2<>$ori_ie_yobi8<>$ori_ie_yobi9<>$ori_ie_jyouhou<>\n";# $ori_ie_jyouhou koko2007/05/04 $ori_ie_rank2 2007/08/02
}

#自分の家の設定ファイル分割
sub oriie_settei_sprit {
($my_con1,$my_con2,$my_con3,$my_con4,$my_con1_title,$my_con2_title,$my_con3_title,$my_con4_title,$my_yobi5,$my_yobi6,$my_yobi7,$my_yobi8,$my_yobi9,$my_yobi10,$my_yobi11,$my_yobi12,$my_yobi13,$my_yobi14,$my_yobi15,$my_yobi16,$my_yobi17,$my_yobi18)= split(/<>/,@_[0]);
	@oriie_settei_sprit = split(/<>/,@_[0]);
}

sub oriie_settei_temp {
$ori_ie_settei_temp = "$my_con1<>$my_con2<>$my_con3<>$my_con4<>$my_con1_title<>$my_con2_title<>$my_con3_title<>$my_con4_title<>$my_yobi5<>$my_yobi6<>$my_yobi7<>$my_yobi8<>$my_yobi9<>$my_yobi10<>$my_yobi11<>$my_yobi12<>$my_yobi13<>$my_yobi14<>$my_yobi15<>$my_yobi16<>$my_yobi17<>$my_yobi18<>\n";
}

sub log_kousin {
		&lock;	
#koko2005/10/08
		if (@_[1] ne ""){
			open(OUT,">@_[0]") || &error("@_[0]が開けません1");
			eval{ flock (OUT, 2); }; #koko 2005/06/05 念のため
			print OUT @_[1];
			close(OUT);
##koko2005/10/09　2007/09/02
			if (-z @_[0]){
				$loop_count = 0;
				while ($loop_count <= 10){
					for (0..50){$i=0;} #koko2007/09/02
					if ($loop_count == 10){&error("ファイルが消えています管理者に連絡ください。");}
					@f_stat_b = stat(@_[0]);
					$size_f = $f_stat_b[7];
					if ($size_f == 0 && @_[1] ne ""){
						open (DAT, "> @_[0] ") or &error("@_[0]が開けません2");
						eval{ flock (DAT, 2); };
						print DAT @_[1] ;
						close (DAT);
					}else{
						last;
					}
				$loop_count++;
				}
			}
		}
#kokoend
		&unlock;
}

sub  mail_sprit {
	($m_syubetu,$m_name,$m_com,$m_date,$m_byou,$m_yobi1,$m_yobi2,$m_yobi3,$m_yobi4,$m_yobi5)= split(/<>/,@_[0]);
}

sub openMylog {
		$my_log_file = "./member/@_[0]/log.cgi";
		open(MYL,"< $my_log_file") || &error("ログファイル（$my_log_file）が開けません。<br>再度ログインしても同様ならば管理人（$master_ad）までメールをください。");
		eval{ flock (MYL, 1); };
		$my_prof = <MYL>;
		if($my_prof == ""){&error("ログファイルに問題が起こりました。<br>お手数ですが、管理人（$master_ad）までメールをください。");}
		&kozin_sprit2($my_prof);
		close(MYL);
}

sub openAitelog {
	$aite_log_file = "./member/@_[0]/log.cgi";
	if (-e $aite_log_file){ #koko2007/09/06
		open(AIT,"< $aite_log_file") || &error("お相手の方のログファイル（$aite_log_file）が開けません。");
		eval{ flock (AIT, 1); };
		$aite_prof = <AIT>;
		if($aite_prof == ""){&error("@_[0]/log.cgi お相手の方のログファイルに問題があります。");} #koko2006/12/22 @_[0]/log.cgi
		&aite_sprit($aite_prof);
		close(AIT);
	}
}


sub check_pass {
	if($in{'k_id'} eq ""){
		if($in{'name'} eq ""){&error("ログインしてください。");}
		&id_check($in{'name'});
		$k_id = $return_id;
	}else{$k_id = $in{'k_id'};}
		$my_log_file = "./member/$k_id/log.cgi";
	open(MYL,"< $my_log_file") || &error("ログファイル（$my_log_file）が開けません。<br>再度ログインしても同様ならば管理人（$master_ad）までメールをください。");
	eval{ flock (MYL, 1); };
	$my_prof = <MYL>;
	close(MYL); #koko2007/11/08

	if($my_prof == ""){&error("ログファイルに問題が起こりました。<br>お手数ですが、管理人（$master_ad）までメールをください。");}
	&kozin_sprit2($my_prof);
	if($in{'pass'} ne $pass){
		&error("名前とパスワードが一致しません！");
	}
	if ($tajuukinsi_deny==1){
		if($k_yobi3 ne ""){
			&error("多重登録者は入居できません。<br>$k_yobi3");
		}
	}

#koko2006/11/29
	if ($in{'burauza_in'} ne "" and $in{'burauza_in'} ne $brauza){
		$brauza = $in{'burauza_in'};
	}
#kokoend

#

}

####BMI算出サブルーチン
sub check_BMI {
	$BMI = int (@_[1] / (@_[0] /100) / (@_[0] /100) );
	if($BMI >= 26){$taikei = "肥満";}
	elsif($BMI >= 24){$taikei = "やや太り気味";}
	elsif($BMI >= 20){$taikei = "標準";}
	elsif($BMI >= 18){$taikei = "やせ気味";}
	elsif($BMI <= 17){$taikei = "やせすぎ";}
}

#######ヘッダー出力
sub header {
	$heder_flag=1;
#print "Content-type: text/html;charset=Shift_JIS\n";
print "Content-type: text/html;\n";#koko2006/12/22
#gzip対応
		if($ENV{'HTTP_ACCEPT_ENCODING'} =~ /gzip/ && $gzip ne ''){  
		  if($ENV{'HTTP_ACCEPT_ENCODING'} =~ /x-gzip/){
		    print "Content-encoding: x-gzip\n\n";
		  }else{
		    print "Content-encoding: gzip\n\n";
		  }
		  open(STDOUT,"| $gzip -1 -c");
		}else{
		  print "\n";
		}
	print "<html>\n<head>\n<META http-equiv=\"content-type\" content=\"text/html; charset=Shift_JIS\">\n"; #koko2007/01/18
	if ($in{'command'} eq "mati_idou" || $in{'command'} eq "mati_idou2"){ #koko2007/04/14
		if ($in{'maemati'} != 3){ #koko2007/01/21 #koko2007/01/24
			&motimono_kensa_ev('ローラースルーゴーゴー'); #koko2007/03/13 
			if ($kensa_flag == 1){
				$maigo = 'yes';
			}else{
				if (int(rand(5)+1) == 1){
					$maigo = 'yes';
				} #kokoend
			}#koko2007/01/18
		}
		while(($syudan_name,$sokudo) = each %idou_syudan){
			push @syudan_names,$syudan_name;
			push @sokudos,$sokudo;
		}
#移動手段選択
		$idousyudan = "徒歩で";
		$ziko_idousyudan = "徒歩";
		$matiidou_time2 = $matiidou_time;
		foreach (@syudan_names){
			&motimono_kensa("$_");
			if ($kensa_flag == 1){
				$idousyudan = "$_で";
				$ziko_idousyudan = "$_";
				$matiidou_time2 = $idou_syudan{$_};
				last;
			}
		}
 #koko2007/04/14
		if ($in{'command'} eq "mati_idou2"){
			$maigo = 'no';
			$idousyudan = "電車で500円で";
			$ziko_idousyudan = "バス";
			$matiidou_time2 = 5; #移動時間
			$money -= 500; #バス料金
			# 代金処理書き込み
			&temp_routin;
			&log_kousin($my_log_file,$k_temp);
		}
 #kokoend2007/04/14

#koko2006/12/24
		if ($ziko_idousyudan eq '徒歩' || $ziko_idousyudan eq '自転車'){
			if ($idou_jikan == 1){&error("移動中に更新しないでください。");} #koko2007/02/11
			$idou_jikan = 1; #koko2007/02/11
			$tairyku_up = int(rand(10)+1);
			$kenkou_up = int(rand(10)+1);
			$speed_up = int(rand(10)+1);
			$wanryoku_up = int(rand(10)+1);
			$kyakuryoku_up = int(rand(10)+1);
			if ($ziko_idousyudan eq '徒歩'){
				if ($tairyku_up <= 5){
					$tairyoku += $tairyku_up;
					$disp .= "体力 $tairyku_up ";
				}
				if ($kenkou_up <= 5){
					$kenkou += $kenkou_up;
					$disp .= "健康 $kenkou_up ";
				}
				if ($speed_up <= 5){
					$speed += $speed_up;
					$disp .= "スピード $speed_up ";
				}
				if ($wanryoku_up <= 5){
					$wanryoku += $wanryoku_up;
					$disp .= "腕力 $wanryoku_up ";
				}
				if ($kyakuryoku_up <= 5){
					$kyakuryoku += $kyakuryoku_up;
					$disp .= "脚力 $kyakuryoku_up ";
				}
			}elsif($ziko_idousyudan eq '自転車'){
				if ($tairyku_up <= 3){
					$tairyoku += $tairyku_up;
					$disp .= "体力 $tairyku_up ";
				}
				if ($kenkou_up <= 3){
					$kenkou += $kenkou_up;
					$disp .= "健康 $kenkou_up ";
				}
				if ($speed_up <= 7){
					$speed += $speed_up;
					$disp .= "スピード $speed_up ";
				}
				if ($wanryoku_up <= 7){
					$wanryoku += $wanryoku_up;
					$disp .= "腕力 $wanryoku_up ";
				}
				if ($kyakuryoku_up <= 7){
					$kyakuryoku += $kyakuryoku_up;
					$disp .= "脚力 $kyakuryoku_up ";
				}
			}
			&temp_routin;
			&log_kousin($my_log_file,$k_temp);
			$disp .= "きたえられた。";
		}
#kokoend
#事故発生
		$randed = int (rand($ziko_kakuritu)+1);
		if ($randed == 1 ){ $ziko_flag  = "on";}else{$ziko_flag  = "off";}
		if ($ziko_idousyudan eq 'バス'){$ziko_flag  = "off";} #koko2007/04/14 事故に遭わない

#名前とパスをエンコード#koko2006/12/24
		#	#koko2006/04/11
		#	$en_name =$in{'name'};
		#	$en_pass =$in{'pass'};
		#	$en_name=~ s/(\W)/sprintf("%%%02X",unpack("C" , $1))/eg;
		#	$en_pass=~ s/(\W)/sprintf("%%%02X",unpack("C" , $1))/eg;
		#	$ziko_idousyudan=~ s/(\W)/sprintf("%%%02X",unpack("C" , $1))/eg; #kokoend2006/12/24

# $idou_jikan;#($k_yobi5) #koko2007/02/11
		$idou_time = time + $matiidou_time2; #koko2006/04/25 #koko2007/02/11
	#	$idou_jikan = time();
	#	if ($in{'command'} ne "mati_idou"){ #koko2006/12/22 kesukoto
	#		print "<meta http-equiv=\"refresh\" content=\"$matiidou_time2;URL=$script?mode=login_view&town_no=$in{'town_no'}&idou=$idou_time&name=$en_name&pass=$en_pass&command=idousyuuryou&ziko_flag=$ziko_flag&ziko_idousyudan=$ziko_idousyudan\">"; #koko2006/04/11,#koko2006/04/25
	#	}#koko2006/12/22
	}else{$idou_jikan = 0;} #koko2007/02/11
	
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
#koko2006/11/29
	if ($brauza eq "Microsoft Internet Explorer" && $job ne "学生"){ #koko2006/01/07
		$disp_javas = qq|<input type=image src=$img_dir/go_work.gif width=32 height=32 onMouseOver='mese_on()' onMouseOut='mese_out()'>|;

		$disp_javas2 = qq|<input type=image src=$img_dir/no_work.gif width=32 height=32 onMouseOver='mese_on2()' onMouseOut='mese_out()'>|;
		$disp_j1 = "document.getElementById('comm').innerHTML=\"$disp_javas\";\n";

		$disp_j2 = "document.getElementById('comm').innerHTML=\"$disp_javas2\";\n";

	}
#kokoend ++
#koko2006/12/20
	if ($in{'command'} eq "mati_idou" || $in{'command'} eq "mati_idou2"){ #koko2007/04/14
		$disp_in = "
function idouInterval(){
	idou = setTimeout('idousuru()',$matiidou_time2*1000);	
}
function idousuru(){
	document.f_idou.submit();
}
\n";
#koko2006/12/29
	}elsif ($in{'mode'} eq "keiba" && ($in{'command'} eq "start" || $in{'command'} eq "go")){
		$disp_in = "
function umaInterval(){
	owari = document.keiba.count.value;
	if(owari == 0){
		idou = setTimeout('idousuru()',3*1000);
	}
}
function idousuru(){
	document.keiba.submit();
}
\n";
	}
#kokoend
	print "<title>$title</title>\n";

	if ($in{'mode'} eq '' || $in{'mode'} eq 'kentiku_do' || $in{'mode'} eq 'make_town'){ #koko2007/06/13
		print <<"EOM";
<Script Language="JavaScript">
<!--
// フォームにメッセージを書き込む部分です。
function onMes5(mes){
	document.foMes5.TeMes5.value=mes;
}
// End -->
</Script>
EOM
	}elsif($in{'mode'} eq 'keiba'){ #koko2007/06/14
		print <<"EOM";
<Script Language="JavaScript">
<!--
function imanotime(){ //#koko2007/04/25
	if(document.ctw==null){clearInterval(retval_i);return;}
	if(document.ctw.mysec==null){clearInterval(retval_i);return;}
	mydate = new Date();
	document.ctw.mysec.value=Math.floor(mydate.getTime()/1000);
}
function pfvsetInterval(){
	retval_i = setInterval('imanotime()',1000); //koko2007/04/25
}
$disp_in //koko2006/12/20
// End -->
</Script>
EOM

	}elsif($in{'mode'} eq 'login_view'){ #koko2007/06/14
		if ($otdashi eq 'yes' && $brauza eq 'Microsoft Internet Explorer' && $oto eq 'on'){ #koko2007/07/01#koko2007/10/26
			$oto1 = "		document.mymujic.Play();//koko2007/01/02";
			$oto2 = "			document.mymujic.Stop();//koko2007/01/02";
			$oto3 = "		document.mymujic.Stop();//koko2007/01/02";
			$oto4 = "		document.mymujic2.Play();//koko2007/01/02";
		}else{
			$oto1 = "//";
			$oto2 = "//";
			$oto3 = "//";
			$oto4 = "//";
		} #kokoend

		print <<"EOM";
<Script Language="JavaScript">
<!--
///////////////////////////////////////////////////
// メッセージ No.5.1 Produced by「CLUB とむやん君」
// URL http://www2s.biglobe.ne.jp/~club_tom/
// 上の2行は著作権表示ですので消さないで下さい
///////////////////////////////////////////////////
function imanotime(){ //#koko2007/04/25
	if(document.ctw==null){clearInterval(retval_i);return;}
	if(document.ctw.mysec==null){clearInterval(retval_i);return;}
	mydate = new Date();
	document.ctw.mysec.value=Math.floor(mydate.getTime()/1000);
}
// フォームにメッセージを書き込む部分です。
function onMes5(mes){
	document.foMes5.TeMes5.value=mes;
}

function pfvsetInterval(){
	retval = setInterval('countdown()',1000);
	retval_w = setInterval('daytime()',1000);
	retval_i = setInterval('imanotime()',1000); //koko2007/04/25
}
$disp_in //koko2006/12/20
function countdown(){
	if(document.form1==null){clearInterval(retval);return;}
	if(document.form1.cdown==null){clearInterval(retval);return;}
	if(document.form1.cdown.value!='O K'){
		min = document.form1.cdown.value;
		min--;
		document.form1.cdown.value = min;
$oto1		document.mymujic.Play();//koko2007/01/02
		if(min<=0){ //koko2007/07/01
			document.form1.cdown.value='O K'
$oto2			document.mymujic.Stop();//koko2007/01/02
			clearInterval(retval);
		}
	}else{
		clearInterval(retval);
$oto3		document.mymujic.Stop();//koko2007/01/02
	}
}
function daytime(){
	if(document.ct==null){clearInterval(retval_w);return;} //koko
	if(document.ct.std==null){clearInterval(retval_w);return;} //koko
	var d_t = document.ct.std.value; //#koko2006/12/01
	var space = " ";
	var dt = d_t.split(space);
	var dtime = new Date(dt[0],dt[1]-1,dt[2],dt[3],dt[4],dt[5]);
	var imat = new Date();
//	alert("koko");

	nokorimd = (dtime.getTime() - imat.getTime() +1000)/(60*1000);
	if (nokorimd >= 0){
		nokorimd = Math.floor(nokorimd);
	}else{
		nokorimd = Math.ceil(nokorimd);
	}

	nokorim = (dtime.getTime() - imat.getTime())/(60*1000);
	if (nokorim >= 0){
		nokorim = Math.floor(nokorim);
	}else{
		nokorim = Math.ceil(nokorim);
	}
	nokoris = (dtime.getTime() - imat.getTime() - nokorim*60*1000)/1000;
	nokorihntei = nokoris
	nokoris = Math.abs(nokoris);
	nokoris = Math.ceil(nokoris);
	if (nokorihntei >= 0){
		if(nokoris >= 60){nokoris = 0;}
		if(nokoris < 10){nokoris = "0" + nokoris;}
		document.foMes5.w_time.value = nokorimd +"分" + nokoris +"秒";
		$disp_j2
	}else{
		document.foMes5.w_time.value = '仕事　ＯＫ';
$oto4		document.mymujic2.Play();//koko2007/01/02
		clearInterval(retval_w);
		$disp_j1
	}
}
// End -->
</Script>
EOM
	} #kokoend
	print <<"EOM";
<style type="text/css">
<!--
$town_stylesheet
-->
</style>
EOM
	if ($in{'mode'} eq "login_view" || @_[1] eq "sonomati"){$sonomati_style_settei ="$page_back[$in{'town_no'}]";}

	if($in{'command'} eq "mati_idou" || $in{'command'} eq "mati_idou2"){ #koko2006/12/22 koko2007/04/14
		print "</head><body style=\"$sonomati_style_settei\" class=@_[0] leftmargin=5 topmargin=5 marginwidth=5 marginheight=5 onLoad=\"idouInterval()\">\n";
#koko2006/12/29
	}elsif ($in{'mode'} eq "keiba" && ($in{'command'} eq "start" ||$in{'command'} eq "go")){
		print "</head><body style=\"$sonomati_style_settei\" class=@_[0] leftmargin=5 topmargin=5 marginwidth=5 marginheight=5 onLoad=\"umaInterval()\">\n";
	}elsif($in{'mode'} eq "login_view"){ #koko2007/06/09
		print "</head><body style=\"$sonomati_style_settei\" class=@_[0] leftmargin=5 topmargin=5 marginwidth=5 marginheight=5 onLoad=\"pfvsetInterval()\">\n";#koko2006/06/12 marginheight=5r 変だよ。
#koko2007/01/06
		if ($otdashi eq 'yes'){
			if ($brauza ne "Microsoft Internet Explorer"){
				print "<EMBED src=\"./sd029.wav\" name=mymujic type=audio/wav autostart=\"false\" hidden=\"true\" loop=\"true\">\n";
				print "<EMBED src=\"./sd024.wav\" name=mymujic2 type=audio/wav autostart=\"false\" hidden=\"true\" loop=\"false\">\n";
			}else{
				print "<object name=\"mymujic\" classid=\"clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95\" width=\"0\" height=\"0\"><param name=\"SRC\" value=\"./sd029.wav\"><param name=\"AUTOSTART\" value=\"false\"><param name=\"loop\" value=\"true\">\n</object>\n";#koko2007/01/06
				print "<object name=mymujic2 classid=\"clsid:22D6F312-B0F6-11D0-94AB-0080C74C7E95\" width=\"0\" height=\"0\"><param name=\"SRC\" value=\"./sd024.wav\"><param name=\"AUTOSTART\" value=\"false\"><param name=\"loop\" value=\"false\"></object>\n";#koko2007/01/06
			}
		}
	}elsif ($in{'mode'} eq "make_town"){
		print "</head><body leftmargin=5 topmargin=5 marginwidth=5 marginheight=5>\n";

	}else { #koko2007/06/09
		print "</head><body style=\"$sonomati_style_settei\" class=@_[0] leftmargin=5 topmargin=5 marginwidth=5 marginheight=5>\n";
	}
#kokoend**
}

sub ori_header {
#print "Content-type: text/html;charset=Shift_JIS\n";
print "Content-type: text/html;\n"; #koko2006/12/22
#gzip対応
		if($ENV{'HTTP_ACCEPT_ENCODING'} =~ /gzip/ && $gzip ne ''){  
		  if($ENV{'HTTP_ACCEPT_ENCODING'} =~ /x-gzip/){
		    print "Content-encoding: x-gzip\n\n";
		  }else{
		    print "Content-encoding: gzip\n\n";
		  }
		  open(STDOUT,"| $gzip -1 -c");
		}else{
		  print "\n";
		}
	print "<html>\n<head>\n<META http-equiv=\"content-type\" content=\"text/html; charset=Shift_JIS\">\n"; #koko2006/12/22
	print "<title>$title</title>\n";

	if ($in{'mode'} eq 'onsen'){ #koko2007/06/09
		print <<"EOM";
<Script Language="JavaScript">
<!--
///////////////////////////////////////////////////
// メッセージ No.5.1 Produced by「CLUB とむやん君」
// URL http://www2s.biglobe.ne.jp/~club_tom/
// 上の2行は著作権表示ですので消さないで下さい
///////////////////////////////////////////////////

// フォームにメッセージを書き込む部分です。
function onMes5(mes){
	document.foMes5.TeMes5.value=mes;
}

// koko 2005/05/23
function powaInterval(){
	pwval = setInterval('powa_puro()',1000)
}
function powa_puro(){
	if(document.powa==null){clearInterval(pwval);return;}
	if(document.powa.ene==null || document.powa.nou==null){clearInterval(pwval);return;}

	var d_t_e = document.powa.ene.value; //#koko2006/12/01
	var d_t_n = document.powa.nou.value; //#koko2006/12/01
	var space = " ";
	var timup_e = 0;
	var timup_n = 0;
	var dt_e = d_t_e.split(space);
	var dt_n = d_t_n.split(space);
	var dtime_e = new Date(dt_e[0],dt_e[1]-1,dt_e[2],dt_e[3],dt_e[4],dt_e[5]);
	var dtime_n = new Date(dt_n[0],dt_n[1]-1,dt_n[2],dt_n[3],dt_n[4],dt_n[5]);
	var imat = new Date();
//	alert("koko");

	nokorimd_e = (dtime_e.getTime() - imat.getTime() +1000)/(60*1000);
	if (nokorimd_e >= 0){
		nokorimd_e = Math.floor(nokorimd_e);
	}else{
		nokorimd_e = Math.ceil(nokorimd_e);
	}
	nokorim_e = (dtime_e.getTime() - imat.getTime())/(60*1000);
	if (nokorim_e >= 0){
		nokorim_e = Math.floor(nokorim_e);
	}else{
		nokorim_e = Math.ceil(nokorim_e);
	}
	nokoris_e = (dtime_e.getTime() - imat.getTime() - nokorim_e*60*1000)/1000;
	nokorihntei_e = nokoris_e
	nokoris_e = Math.abs(nokoris_e);
	nokoris_e = Math.ceil(nokoris_e);
	if (nokorihntei_e >= 0){
		if(nokoris_e >= 60){nokoris_e = 0;}
		if(nokoris_e < 10){nokoris_e = "0" + nokoris_e;}
		document.powa.tairyoku_pw.value = nokorimd_e +"分" + nokoris_e +"秒";
	}else{
		document.powa.tairyoku_pw.value = 'ＯＫ';
		timup_e = 1;
	}

	nokorimd_n = (dtime_n.getTime() - imat.getTime() +1000)/(60*1000);
	if (nokorimd_n >= 0){
		nokorimd_n = Math.floor(nokorimd_n);
	}else{
		nokorimd_n = Math.ceil(nokorimd_n);
	}

	nokorim_n = (dtime_n.getTime() - imat.getTime())/(60*1000);
	if (nokorim_n >= 0){
		nokorim_n = Math.floor(nokorim_n);
	}else{
		nokorim_n = Math.ceil(nokorim_n);
	}
	nokoris_n = (dtime_n.getTime() - imat.getTime() - nokorim_n*60*1000)/1000;
	nokorihntei_n = nokoris_n
	nokoris_n = Math.abs(nokoris_n);
	nokoris_n = Math.ceil(nokoris_n);
