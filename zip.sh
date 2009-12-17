#!/bin/sh

git checkout-index -a -f --prefix=zip_temp/
find ./zip_temp -name '.gitignore' | xargs rm
zip -r test_harness.zip ./zip_temp 
rm -rf ./zip_temp
