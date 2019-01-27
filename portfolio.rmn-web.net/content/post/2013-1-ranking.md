+++
date = "2013-11-22T00:00:00+09:00"
draft = false
title = "大学のサークルでランキングシステム作成"
categories = ["時代背景", "C++", "DXライブラリ", "TCP/IP", "Windows開発"]
slug = "ranking2013"
+++

2013年の大学学園祭では、各部員が作成したゲームからスコアサーバにスコアを
送信して、ランキングを表示するシステムを作成しました。

展示では、スコアサーバは普通のWindowsマシンでした。常時起動していて、
画面には現在のスコアランキングが表示されていました。各端末からのリクエストを受け取ると
ゲームのIDとスコアを適切な順位の場所に挿入し、画面の更新を行っていました。

当時はなぜか生のTCP/IPを扱っていましたが、のちに普通にHTTPでやりとりして、
サーバは普通のWebサーバで、ランキングのデータ保持は関係データベースを使った方が
簡単にできると感じました。そして、2015年度の学園祭では後輩がそれを実装しました
（PHP+MySQLだったようです）。


### データ
<table>
<tbody><tr><th>タイトル</th><td>ERS( E. Ranking System )</td></tr>
<tr><th>作者</th><td>rmn.（現: まつゆき）</td></tr>
<tr><th>ソースコード</th><td>公開準備中</td></tr>
<tr><th>開発言語</th><td>C++ (Microsoft Visual C++ 2012 Express)</td></tr>
<tr><th>使用ライブラリ</th><td><a href="http://homepage2.nifty.com/natupaji/DxLib/">DXライブラリ</a></td></tr>
<tr><th>開発期間</th><td>2013年8月-2013年10月</td></tr>
</tbody></table>
