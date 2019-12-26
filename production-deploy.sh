#!/usr/bin/env bash

set -e
set -v

java -jar domain/target/website.jar $AWS_ACCESS_KEY_ID $AWS_SECRET_KEY
