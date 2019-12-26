#!/usr/bin/env bash

set -e
set -v

mvn clean
mvn verify
