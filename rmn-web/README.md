rmn-web-sites
=============

このリポジトリは rmn-web.net およびそのサブドメインのサイトのページを生成するものです。

完全修飾ドメイン名(FQDN)ごとにディレクトリが切られており、それぞれが[Netlify](https://netlify.com/)でホストされています。

`master` ブランチの内容にどこか一つでも変更があるとすべてのサイトがデプロイされます。

※ rmn-web.net は既に新規コンテンツの更新を終了しています。

サイト一覧
-----------------

| FQDN | サイト名 | ライセンス | ビルド方式/ツール |
| ---- | -------- | ---------- | ---------- |
| [rmn-web.net](https://rmn-web.net/) | 旧ブログ | [CC-BY 4.0](https://creativecommons.org/licenses/by/4.0/deed.ja) | [hugo](https://gohugo.io/) |
| [pointp.rmn-web.net](https://pointp.rmn-web.net) | 線分ABを点Pがノリノリで動くゲーム | [Apache 2.0](https://github.com/matsuyuki-a/rmn-web-sites/blob/master/pointp.rmn-web.net/LICENSE) | なし |
| [portfolio.rmn-web.net](https://portfolio.rmn-web.net) | ポートフォリオ | [CC-BY 4.0](https://creativecommons.org/licenses/by/4.0/deed.ja) | [hugo](https://gohugo.io/) |
| [teatime.rmn-web.net](https://teatime.rmn-web.net) | Teatime Chat Wiki リダイレクト用 | [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/deed.ja) | なし |
| [trivial.rmn-web.net](https://trivial.rmn-web.net) | 小ネタ | doon.jpg * 以外: [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/deed.ja) | なし |

\* doon.jpg: (C) 2012 Jiu AMAMIYA, All Rights Reserved. 無断でのご利用はご遠慮ください。

他のリポジトリからサイトを追加する
----------------

下記は portfolio.rmn-web.net をこのリポジトリに追加した時の例 (portfolio.rmn-web.net は昔 GitHubの `matsuyuki-a/portfolio.rmn-web.net` にありました。今は削除済です。)

``` sh
$ git remote add portfolio git@github.com:matsuyuki-a/portfolio.rmn-web.net.git
$ git subtree add -P portfolio.rmn-web.net/ portfolio master
```

終わったら Netlify の向き先をこのリポジトリに変更します。 base directoryを各FQDNのディレクトリにしておくとビルドが簡単になって便利です。


