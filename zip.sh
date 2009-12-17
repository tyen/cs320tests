#!/bin/sh

git checkout-index -a -f --prefix=checkout_test/
find ./checkout_test -name '.gitignore' | xargs rm
zip -r test_harness.zip ./checkout_test 
rm -rf ./checkout_test
