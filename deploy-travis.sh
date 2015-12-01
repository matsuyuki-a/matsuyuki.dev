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
git push --quiet https://$GH_TOKEN@github.com/rmn31415/rmn31415.github.io.git origin master
git subtree push --prefix=hugo/public https://$GH_TOKEN@github.com/rmn31415/rmn31415.github.io.git origin gh-pages

