#!/bin/bash

git pull

cd hugo

echo -e "\033[0;32mDeploying updates to GitHub...\033[0m"

# Build the project.
hugo -t hugo-multi-bootswatch

# Go To Public folder
cd public

# Add changes to git.
git add -A

# Commit changes.
msg="rebuilding site `date`"
if [ $# -eq 1 ]; then
  msg="$1"
fi

git commit -m "$msg"

cd ../..

# Push source and build repos.
git push origin master
git subtree push --prefix=hugo/public git@github.com:rmn31415/news.rmn-web.net.git gh-pages
