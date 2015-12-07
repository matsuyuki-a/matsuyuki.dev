#
# eval{ flock (OI, 2); }; 2007/06/16
#
sub get_unit {
##################パーツ情報の定義（ここで定義されたパーツは管理者メニューの「街のレイアウト作成」に現れます。
	%unit = (
#画像のみのパーツ（自分の好きなパーツ画像を増やしたい場合は、このフォーマットを参考に追加してください。画像データはimgフォルダに入れてください。画像は全て32px × 32pxに統一する必要があります。パーツ定義はこのままで画像データのみを同じファイル名の任意の画像に差し替えてもOKです）
#　"記号" => "<td><img src=$img_dir/画像ファイル名></td>",
#　※「記号」は識別しやすい任意の文字をつけて構いませんが、あまり長くならないようにしましょう（できるだけ２文字以内）。また他とダブらないように注意してください。

#ver.1.30	パーツのhtml記述をいくつか省略化
"交差" => "<td><img src=$img_dir/kousa_r.gif></td>",
"道横" => "<td><img src=$img_dir/yoko_r.gif></td>",
"道縦" => "<td><img src=$img_dir/tate_r.gif></td>",
"桜" => "<td><img src=$img_dir/chip1037.gif></td>",
"海線" => "<td><img src=$img_dir/rikyo.gif></td>",
"木1" => "<td><img src=$img_dir/tree1.gif></td>",
"木2" => "<td><img src=$img_dir/tree2.gif></td>",
"木3" => "<td><img src=$img_dir/tree3.gif></td>",
"木4" => "<td><img src=$img_dir/tree4.gif></td>",
"海" => "<td><img src=$img_dir/asase.gif></td>",
"木" => "<td><img src=$img_dir/chip10.gif></td>",
"宇宙" => "<td><img src=$img_dir/utyu.gif></td>",
"海底" => "<td><img src=$img_dir/ui.gif></td>",
"軍艦" => "<td><img src=$img_dir/chipt002.gif></td>",
"ミサ" => "<td><img src=$img_dir/land9.gif></td>",
"草" => "<td><img src=$img_dir/land2.gif></td>",
"森" => "<td><img src=$img_dir/land6.gif></td>",
"雪" => "<td><img src=$img_dir/land690.gif></td>",
"流氷" => "<td><img src=$img_dir/chip1028.gif></td>",
"薄氷" => "<td><img src=$img_dir/land1.gif></td>",
"雪木" => "<td><img src=$img_dir/land60.gif></td>",
"雪ダ" => "<td><img src=$img_dir/monument0.gif></td>",
"背1" => "<td><img src=$img_dir/house21.gif></td>",
"背2" => "<td><img src=$img_dir/land8.gif></td>",
"路1" => "<td><img src=$img_dir/Senro5.png></td>",
"路2" => "<td><img src=$img_dir/Senro3.png></td>",
"路3" => "<td><img src=$img_dir/Senro2.png></td>",
"路4" => "<td><img src=$img_dir/Senro4.png></td>",
"路5" => "<td><img src=$img_dir/Senro1.png></td>",
"路6" => "<td><img src=$img_dir/Senro6.png></td>",
"高1" => "<td><img src=$img_dir/chip10025.gif></td>",
"高2" => "<td><img src=$img_dir/chip10024.gif></td>",
"高3" => "<td><img src=$img_dir/house102.gif></td>",
"高4" => "<td><img src=$img_dir/house100.gif></td>",
"レ左" => "<td><img src=$img_dir/rail_l.png></td>",
"レ右" => "<td><img src=$img_dir/rail_r.png></td>",

#リンク用パーツ（姉妹都市など街から他のURLへリンクを貼る場合のフォーマットです。新たに追加する場合は、このフォーマットをコピーしてから、記号、URL、説明、必要ならば画像ファイル名を変更してください）
#　"記号" => "<td><a href=\"リンク先のURL\" target=_blank><img src='$img_dir/画像名'  width=32 height=32 border=0  onMouseOver='onMes5(\"マウスをのせたとき表示される説明\")' onMouseOut='onMes5(\"\")'></a></td>",
#　下は書き方のサンプルです。実際にこれを街に設置しないでください。

"リンク" => "<td><a href=\"http://brassiere.jp/03com/town2/town_maker.cgi\" target=_blank><img src='$img_dir/link.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"テスト参加用のサンプル設置プログラムです。\")' onMouseOut='onMes5(\"\")'></a></td>\n",

"説明" => "<td><a href=\"./setumei.htm\" target=_blank><img src='$img_dir/link.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"初心者への説明。\")' onMouseOut='onMes5(\"\")' alt='初心者への説明。'></a></td>\n",

"連絡" => "<td><a href=\"./bbs0/bbs0.cgi\" target=_blank><img src='$img_dir/renraku.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"入られなくなった時の連絡用です。\")' onMouseOut='onMes5(\"\")'></a></td>\n",

#機能をもったパーツ（以下は、それぞれ機能をもった建物などへのリンクになりますので、基本的に変更はしないでください。必要であれば、説明、画像ファイル名（src='$img_dir/○○.gifの部分）の変更は可）

"アイテム" => "<form method=POST action=\"item.cgi\"><input type=hidden name=mode value=\"item_make\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/img062.gif'  onMouseOver='onMes5(\"アイテムのアイデア紹介・登録。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#koko2005/12/03

"役場" => "<form method=POST action=\"yakuba.cgi\"><td height=32 width=32><input type=hidden name=mode value=yakuba><input type=hidden name=name value=$name><input type=hidden name=pass value=$pass><input type=hidden name=k_id value=$k_id><input type=hidden name=town_no value=$in{'town_no'}><input type=image src='$img_dir/chip1064.gif'  onMouseOver='onMes5(\"首都庁です。新規に住民登録された方やランキングを見ることができます。\")' onMouseOut='onMes5(\"\")'></td></form>",		#ver.1.40

"銀行" => "<form method=POST action=\"basic.cgi\"><input type=hidden name=mode value=\"ginkou\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=$pass><input type=hidden name=k_id value=$k_id><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/bank.gif'  onMouseOver='onMes5(\"銀行です。お金を預けたり引き出したりできます。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40

"病院" => "<form method=POST action=\"basic.cgi\"><input type=hidden name=mode value=byouin><input type=hidden name=name value=$name><input type=hidden name=pass value=$pass><input type=hidden name=k_id value=$k_id><input type=hidden name=town_no value=$in{'town_no'}><td height=32 width=32><input type=image src='$img_dir/hospi.gif'  onMouseOver='onMes5(\"病院です。費用は高めですが、ほとんどの病気を全快させます。\")' onMouseOut='onMes5(\"\")'></td></form>",		#ver.1.40

"カード" => "<form method=POST action=\"basic.cgi\"><input type=hidden name=mode value=\"donus\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"前の人が引いたカードと同じ数字を出さないようにするカードゲームです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40
"カード２" => "<form method=POST action=\"basic.cgi\"><input type=hidden name=mode value=\"donus2\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"前の人が引いたカードと同じ数字を出さないようにするカードゲーム２です。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40

"カード３" => "<form method=POST action=\"basic.cgi\"><input type=hidden name=mode value=\"donus3\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"前の人が引いたカードと同じ数字を出すようにするカードゲーム３です。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40

"カード４" => "<form method=POST action=\"basic.cgi\"><input type=hidden name=mode value=\"donus4\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"High＆Lowを当てるゲームです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40

"スロット" => "<form method=POST action=\"slot.cgi\"><input type=hidden name=mode value=\"l1_slot\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"スロットで遊ぼう。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"サイコロ" => "<form method=POST action=\"saikoro.cgi\"><input type=hidden name=mode value=\"saikoro\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"サイコロで遊ぼう。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"お宝" => "<form method=POST action=\"otakara.cgi\"><input type=hidden name=mode value=\"otakara\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/takara.gif'  onMouseOver='onMes5(\"お宝ゲーム\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"ギフト屋" => "<form method=POST action=\"gifutoya.cgi\"><input type=hidden name=mode value=gifutoya><input type=hidden name=mysec value=$in{'mysec'}><!-- koko2006/04/01 --><input type=hidden name=name value=$name><input type=hidden name=pass value=$pass><input type=hidden name=k_id value=$k_id><input type=hidden name=town_no value=$in{'town_no'}><td height=32 width=32><input type=image src='$img_dir/gft.gif'  onMouseOver='onMes5(\"ギフト作成所\")' onMouseOut='onMes5(\"\")'></td></form>",

"マルチ" => "<form method=POST action=\"cardw2.cgi\"><input type=hidden name=mode value=\"cardw\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"マルチカードだよ。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"くじ" => "<form method=POST action=\"kuzi.cgi\"><input type=hidden name=mode value=\"kuzi_game\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/kuji.gif'  onMouseOver='onMes5(\"くじだよ。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"ロト" => "<form method=POST action=\"loto.cgi\"><input type=hidden name=mode value=\"loto_game\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"ロトだよ。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"ロト6" => "<form method=POST action=\"loto6.cgi\"><input type=hidden name=mode value=\"loto_game\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/donuts_tate.gif'  onMouseOver='onMes5(\"ロト6だよ。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"株" => "<form method=POST action=\"kabu.cgi\"><input type=hidden name=mode value=\"kabu\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/kabu.gif'  onMouseOver='onMes5(\"株の取引場\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"食堂" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"syokudou\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/syokudou.gif'  onMouseOver='onMes5(\"セントラル食堂です。種類は豊富ですが、値段は高めで在庫も少なめです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",
#koko2006/07/16
"食堂２" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"syokudou2\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/syokudou.gif'  onMouseOver='onMes5(\"セントラル食堂２です。種類は豊富ですが、値段は高めで在庫も少なめです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"デパ" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"depart_gamen\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/depart.gif'  onMouseOver='onMes5(\"中央デパートです。種類は豊富ですが、値段は高めで在庫も少なめです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"デパ2" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"depart_gamen2\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/depart.gif'  onMouseOver='onMes5(\"ライバルデパートです。種類は豊富ですが、値段は高めで在庫も少なめです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"自販機" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"hanbai\"><input type=hidden name=mysec value=\"$in{'mysec'}\"><!-- koko2006/04/01 --><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/zidou2.gif'  onMouseOver='onMes5(\"自動販売機です。\")' onMouseOut='onMes5(\"\")'></td></form>",

"販売1" => "<form method=POST action=\"hanbai1.cgi\"><input type=hidden name=mode value=\"hanbai1\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/house1.gif'  onMouseOver='onMes5(\"お店1\")' onMouseOut='onMes5(\"\")'></td></form>",

"リサイ" => "<form method=POST action=\"recycle.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"resycle\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/resai.gif'  onMouseOver='onMes5(\"リサイクルショップです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"クーポン" => "<form method=POST action=\"coupon.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"coupon\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/reload.gif'  onMouseOver='onMes5(\"クーポン引換所です。\")' onMouseOut='onMes5(\"\")'></td></form>",

"福引" => "<form method=POST action=\"fukubiki.cgi\"><input type=hidden name=mode value=\"fukubiki\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/fukubiki.gif'  onMouseOver='onMes5(\"福引ゲーム\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"ジム" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"gym\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/gym.gif'  onMouseOver='onMes5(\"スポーツジムです。体を鍛えることができます。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"学校" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"school\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/school.gif'    onMouseOver='onMes5(\"スクールです。特定の能\力を伸ばすのに大きな効果がありますが、1日1レッスンです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",
#koko 2005/09/11
"教室" => "<form method=POST action=\"kyushitu.cgi\"><td height=32 width=32><input type=hidden name=mode value=kyushitu><input type=hidden name=name value=$name><input type=hidden name=pass value=$pass><input type=hidden name=k_id value=$k_id><input type=hidden name=town_no value=$in{'town_no'}><input type=image src='$img_dir/house21.gif'  onMouseOver='onMes5(\"マルチカルチャースクールです、なんでも鍛えられます\")' onMouseOut='onMes5(\"\")'></td></form>",
#kokoend
"職安" => "<form method=POST action=\"basic.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"job_change\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/work.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"職業安定所です。就職、転職などはこちらへ。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40

"空地" => "<td><img src=$img_dir/akiti.gif onMouseOver='onMes5(\"この場所に家を建てることができます。\")' onMouseOut='onMes5(\"\")'></td>\n",
"空地2" => "<td><img src=$img_dir/battle.gif onMouseOver='onMes5(\"この場所に家を建てることができます。\")' onMouseOut='onMes5(\"\")'></td>\n",
#koko2007/03/29
"空地3" => "<td><img src=$img_dir/akiti3.gif onMouseOver='onMes5(\"この場所に家を建てることができます。\")' onMouseOut='onMes5(\"\")'></td>\n",
"空地4" => "<td><img src=$img_dir/akiti3.gif onMouseOver='onMes5(\"この場所に家を建てることができます。\")' onMouseOut='onMes5(\"\")'></td>\n",
#koko2007/05/02
"問屋" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"orosi\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/tonya.gif\" onMouseOver='onMes5(\"商店や企業を持っている人は、ここで商品を仕入れることができます。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"建築" => "<form method=POST action=\"$script\"><td height=32 width=32><input type=hidden name=mode value=\"kentiku\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/kentiku.gif\"  onMouseOver='onMes5(\"建設会社です。家を建てたいときはここに依頼します。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"競馬" => "<form method=POST action=\"basic.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"keiba\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/keiba.gif\"  onMouseOver='onMes5(\"競馬場です。\")' onMouseOut='onMes5(\"\")'></td></form>\n",			#ver.1.40

"プロフ" => "<form method=POST action=\"basic.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"prof\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/prof.gif\"  onMouseOver='onMes5(\"住民の実際のプロフィールを登録するところです。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40

"キャラ" => "<form method=POST action=\"game.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"c_league\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/chara_battle.gif\"  onMouseOver='onMes5(\"最強のキャラクターを決める『Cリーグ』会場です。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40

"街コン" => "<form method=POST action=\"./mati_contest.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"matikon\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/matikon.gif\"  onMouseOver='onMes5(\"街の名誉をかけて競う『週間街コンテスト』です。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40

"地" => "<td><img src=$img_dir/kentiku_yotei.gif width=32 height=32  width=32 height=32 border=0 onMouseOver='onMes5(\"建築予\定地です。\")' onMouseOut='onMes5(\"\")'></td>\n",

#バージョンアップ等により新しい機能パーツを個別に追加する場合、「ここから」～「ここまで」の間に追加してください。
####「ここから」
"空" => "<td><img src=$img_dir/sora.gif></td>",
"壁" => "<td><img src=$img_dir/kabe.gif></td>",
"24" => "<td><a href=\"http://hirarira.hp.infoseek.co.jp/24LIFE/24LIFE.html\" target=_blank><img src='$img_dir/24LIFE.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"24LIFEに移動します\")' onMouseOut='onMes5(\"\")'></a></td>",
"ゲム" => "<td><a href=\"http://hirarira.hp.infoseek.co.jp/newwwa/newwwa.html\" target=_blank><img src='$img_dir/game.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"新ＷＷＡゲームセンターです\")' onMouseOut='onMes5(\"\")'></a></td>",
"吉" => "<td><a href=\"http://w1.oroti.com/~wgsv2/town/town_maker.cgi\" target=_blank><img src='$img_dir/yoko_r.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"電脳神都に飛びます\")' onMouseOut='onMes5(\"\")'></a></td>",
"ブ" => "<td><a href=\"http://aaaa2000.hp.infoseek.co.jp/cgi-bin/townof2nd/town_maker.cgi\" target=_blank><img src='$img_dir/yoko_r.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"ブレヴィブタウンに飛びます\")' onMouseOut='onMes5(\"\")'></a></td>",
"公園" => "<td><img src='$img_dir/chip1065.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"公園です\")' onMouseOut='onMes5(\"\")'></a></td>",
"港" => "<td><img src='$img_dir/chip1045.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"港です\")' onMouseOut='onMes5(\"\")'></a></td>",
"いのら" => "<td><img src='$img_dir/monster0.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"いのらです\")' onMouseOut='onMes5(\"\")'></a></td>",
"じ1" => "<td><img src='$img_dir/JJ1.png'  width=32 height=32 border=0  onMouseOver='onMes5(\"じうーん\")' onMouseOut='onMes5(\"\")'></a></td>",
"じ2" => "<td><img src='$img_dir/JJ2.png'  width=32 height=32 border=0  onMouseOver='onMes5(\"やあやあ。極細ポッキーはあるかい？\")' onMouseOut='onMes5(\"\")'></a></td>",
"じ3" => "<td><img src='$img_dir/JJ3.png'  width=32 height=32 border=0  onMouseOver='onMes5(\"うー？\")' onMouseOut='onMes5(\"\")'></a></td>",
"じ4" => "<td><img src='$img_dir/JJ4.png'  width=32 height=32 border=0  onMouseOver='onMes5(\"じうーん・・・。\")' onMouseOut='onMes5(\"\")'></a></td>",
"じ5" => "<td><img src='$img_dir/JJ5.png'  width=32 height=32 border=0  onMouseOver='onMes5(\"わぁい\")' onMouseOut='onMes5(\"\")'></a></td>",
"じ6" => "<td><img src='$img_dir/JJ6.png'  width=32 height=32 border=0  onMouseOver='onMes5(\"本体の雨宮慈雨もよろしくね\")' onMouseOut='onMes5(\"\")'></a></td>",
"じ7" => "<td><img src='$img_dir/JJ7.png'  width=32 height=32 border=0  onMouseOver='onMes5(\"じうーん？\")' onMouseOut='onMes5(\"\")'></a></td>",
"じ8" => "<td><img src='$img_dir/JJ8.png'  width=32 height=32 border=0  onMouseOver='onMes5(\"ふう・・・。\")' onMouseOut='onMes5(\"\")'></a></td>",
"駅1" => "<td><img src='$img_dir/station.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"駅です、現在電車には乗れません\")' onMouseOut='onMes5(\"\")'></a></td>",
"モ" => "<td><img src='$img_dir/monument1.gif'  width=32 height=32 border=0  onMouseOver='onMes5(\"旧ヒラリラー合衆国の記念碑です\")' onMouseOut='onMes5(\"\")'></a></td>",
#ver.1.1追加
"温泉" => "<!-- koko2006/04/10-->
<script type=\"text/javascript\">
<!--
function on_sec(){
	myonDate = Math.round((new Date()).getTime()/1000);
	document.onsen.onsec.value = myonDate;
}
//-->
</script>
<!-- kokoend -->
<form method=POST name=onsen action=\"basic.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"onsen\"><input type=hidden name=onsec value=\"\"><!-- koko 2007/06/11 --><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/onsen_a.gif\"  onMouseOver='onMes5(\"温泉です。疲れた体を癒しましょう。入浴料は$nyuuyokuryou円です。\")' onMouseOut='onMes5(\"\")' onClick=\"on_sec()\"></td></form>\n",	#ver.1.40

#ver.1.3追加
"斡旋" => "<form method=POST action=\"kekkon.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"assenjo\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/assenjo.gif\"  onMouseOver='onMes5(\"恋人斡旋所です。バーチャルな恋愛や結婚をしたい方はこちらへ登録してください。\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40
####
"街1_1" => "<form method=POST action=\"$script\"><input type=hidden name=mode value=\"login_view\"><input type=hidden name=command value=\"mati_idou\"><td height=32 width=32><input type=hidden name=maemati value=\"$in{'town_no'}\"><input type=hidden name=town_no value=\"0\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=image src=\"$img_dir/my_housein.gif\"    onMouseOver='onMes5(\"メインタウンへ移動\")' onMouseOut='onMes5(\"\")' alt=\"メインタウンへ移動\"></td></form>\n",
#koko2007/01/24

"人生" => "<form method=POST action=\"./sugoroku_for_town/zinsei.cgi\"><td height=32 width=32><input type=hidden name=name value=$in{'name'}><input type=hidden name=pass value=$in{'pass'}><input type=hidden name=sex value=$sex><input type=hidden name=mode value=cont><input type=hidden name=yobidasi value=login_view><input type=hidden name=command value=select_com><input type=image src=\"$img_dir/zinsei.gif\"  onMouseOver='onMes5(\"「人生のゲーム」です。稼いだお金をTOWNの銀行に振り込むことができます。\")' onMouseOut='onMes5(\"\")'></td></form>",

"合成" => "<form method=POST action=\"gouseiya.cgi\"><input type=hidden name=mode value=\"gousei\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/gouseiyai.gif'  onMouseOver='onMes5(\"合成して品物を作ります。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"競売" => "<form method=POST action=\"auction.cgi\"><input type=hidden name=mode value=\"auction\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/auction.gif'  onMouseOver='onMes5(\"競売所です。\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"特典" => "<form method=POST action=\"tokuten.cgi\"><input type=hidden name=mode value=\"tokuten\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/tokuten.gif'  onMouseOver='onMes5(\"特典\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"ゲムラ" => "<form method=POST action=\"gamerand.cgi\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><td height=32 width=32><input type=image src='$img_dir/game.gif'  onMouseOver='onMes5(\"ゲームランド\")' onMouseOut='onMes5(\"\")'></td></form>\n",

"住所" => "<form method=POST action=\"jyusyo.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"jyusyo\"><input type=hidden name=name value=\"$name\"><input type=hidden name=pass value=\"$pass\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src='$img_dir/bil.gif' onMouseOver='onMes5(\"お店や会社を見ることが出来ます\")' onMouseOut='onMes5(\"\")'></td></form>\n",		#ver.1.40 2007/11/13

####「ここまで」
);

################パーツ情報の定義ここまで
}

sub kozin_house {
#個人の家情報をunitハッシュに代入
	open(OI,"< $ori_ie_list") || &error("Open Error : $ori_ie_list");
	eval{ flock (OI, 1); }; #koko2007/06/16
	@ori_ie_hairetu = <OI>;
	foreach (@ori_ie_hairetu) {
			&ori_ie_sprit($_);
			$unit{"$ori_k_id"} = "<form method=POST action=\"original_house.cgi\"><td><input type=hidden name=mode value=\"houmon\"><input type=hidden name=ori_ie_id value=\"$ori_k_id\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$ori_ie_image\"   onMouseOver='onMes5(\"$ori_ie_setumei\")' onMouseOut='onMes5(\"\")' alt=\"$ori_ie_setumei\"></td></form>\n";		#ver.1.40
	}
	close(OI);
}

sub simaitosi {
#他の街へのリンク情報をunitハッシュに代入
	$i=0;
	$i2=1;
	foreach (@town_hairetu) {
			$unit{"街$i2"} = "<form method=POST action=\"$script\"><input type=hidden name=mode value=\"login_view\"><input type=hidden name=command value=\"mati_idou\"><td height=32 width=32><input type=hidden name=maemati value=\"$in{'town_no'}\"><input type=hidden name=town_no value=\"$i\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=image src=\"$img_dir/kousa_r.gif\"    onMouseOver='onMes5(\"$_へ移動\")' onMouseOut='onMes5(\"\")' alt=\"$_へ移動\"></td></form>\n"; #koko2007/01/24
			$i ++;
			$i2 ++;
	}
} #2006/04/24

# 電車で移動 #koko2007/04/14
sub simaitosi2 {
#他の街へのリンク情報をunitハッシュに代入
	$i=0;
	$i2=1;
	foreach (@town_hairetu) {
			$unit{"バス$i2"} = "<form method=POST action=\"$script\"><input type=hidden name=mode value=\"login_view\"><input type=hidden name=command value=\"mati_idou2\"><td height=32 width=32><input type=hidden name=maemati value=\"$in{'town_no'}\"><input type=hidden name=town_no value=\"$i\"><input type=hidden name=mysec value=\"$in{'mysec'}\"><!-- koko2006/04/01 --><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=image src=\"$img_dir/Station.png\"  onMouseOver='onMes5(\"$_へ電車で移動\")' onMouseOut='onMes5(\"\")' alt=\"$_へ電車で移動\"></td></form>"; #koko2007/01/24
			$i ++;
			$i2 ++;
	}
}


sub admin_parts {
#管理者作成BBSへのリンク情報をunitハッシュに代入
	$i=1;
	$i2=0;
	foreach (@admin_bbs_syurui) {
			$unit{"掲$i"} = "<form method=POST action=\"original_house.cgi\"><td height=32 width=32><input type=hidden name=mode value=\"normal_bbs\"><input type=hidden name=ori_ie_id value=\"admin\"><input type=hidden name=bbs_num value=\"$i2\"><input type=hidden name=k_id value=\"$k_id\"><input type=hidden name=name value=\"$in{'name'}\"><input type=hidden name=pass value=\"$in{'pass'}\"><input type=hidden name=town_no value=\"$in{'town_no'}\"><input type=image src=\"$img_dir/$admin_bbs_gazou[$i2]\"   onMouseOver='onMes5(\"$_\")' onMouseOut='onMes5(\"\")' alt=\"$_\"></td></form>\n";		#ver.1.40
			$i ++;
			$i2 ++;
	}
}

1;