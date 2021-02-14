+++
date = "2015-02-03T00:00:00+09:00"
draft = false
title = "大学で卒業論文書いた"
categories = ["時代背景", "研究", "Java", "サーブレット", "Spring", "MySQL", "Hibernate"]
slug = "thesis2015"
+++

大学に入って所属した研究室で卒業論文を書きました。

あるシステムを構築しました。技術的にはだいたいこんな具合。

- Java。サーブレット。
- [Spring Framework](http://projects.spring.io/spring-framework/) を用いた Model-View-Controller の構成。
- サーバサイドにある複雑な情報を予め組み立ておきます。
- クライアントからのリクエストに応じて、JSON形式で組み立てた情報を返します。(Web API)
- 必要な情報はMySQL上に保存。JavaのオブジェクトとSQLの対応(O/Rマッピング)には[Hibernate](http://hibernate.org/)を用いました。

