rmn-web-sites
=============

このリポジトリは rmn-web.net およびそのサブドメインのサイトのページを生成するものです。

完全修飾ドメイン名(FQDN)ごとにディレクトリが切られており、それぞれがNetlifyでホストされています。

`master` ブランチの内容にどこか一つでも変更があるとすべてのサイトがデプロイされます。


サイト一覧
-----------------

| FQDN | サイト名 | ライセンス | ビルド方式 |
| ---- | -------- | ---------- | ---------- |
| rmn-web.net | rmn-web.net | [CC-BY 4.0](https://creativecommons.org/licenses/by/4.0/deed.ja) | hugo |
| pointp.rmn-web.net | 線分ABを点Pがノリノリで動くゲーム | [Apache 2.0](https://github.com/matsuyuki-a/rmn-web-sites/blob/master/pointp.rmn-web.net/LICENSE) | なし |
| portfolio.rmn-web.net | ポートフォリオ | [CC-BY 4.0](https://creativecommons.org/licenses/by/4.0/deed.ja) | hugo |
| teatime.rmn-web.net | Teatime Chat Wiki リダイレクト用 | [CC0 1.0](https://creativecommons.org/publicdomain/zero/1.0/deed.ja) | なし |


他のリポジトリからサイトを追加する
----------------

下記は portfolio.rmn-web.net をこのリポジトリに追加した時の例 (portfolio.rmn-web.net は昔 GitHubの `matsuyuki-a/portfolio.rmn-web.net` にありました。今は削除済です。)

``` sh
$ git remote add portfolio git@github.com:matsuyuki-a/portfolio.rmn-web.net.git
$ git subtree add -P portfolio.rmn-web.net/ portfolio master
```

終わったら Netlify の向き先をこのリポジトリに変更します。 base directoryを各FQDNのディレクトリにしておくとビルドが簡単になって便利です。


