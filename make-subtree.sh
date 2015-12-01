#!/bin/sh

if [ -e "hugo/public" ]; then
  git rm -rf hugo/public
  git commit -m "temporal remove public"
  git subtree add --prefix=hugo/public git@github.com:rmn31415/news.rmn-web.net.git gh-pages --squash
  git push origin master
fi
