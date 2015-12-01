#!/bin/sh

if [ -e "hugo/public" ]; then
  git rm -rf hugo/public
  git commit -m "temporal remove public"
  git subtree add --prefix=hugo/public https://$GH_TOKEN@github.com/rmn31415/rmn31415.github.io.git gh-pages --squash

  git push origin master
fi
