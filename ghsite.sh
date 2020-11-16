#!/bin/bash
tmpdir=$(mktemp -d)
version=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
git clone git@github.com:Javastro/jsofa.git --branch gh-pages --single-branch $tmpdir/ghpages
mvn clean site site:stage -DstagingDirectory=$tmpdir/site
rsync -avz $tmpdir/site/ $tmpdir/ghpages/
git -C $tmpdir/ghpages add .
git -C $tmpdir/ghpages commit  -m "new jsofa site for $version"
git -C $tmpdir/ghpages push
rm -rf $tmpdir