#!/bin/bash
echo '------------ Building file-reader component started ------------'
mvn clean install -DskipTests
docker build -t vijaykdotcom/file-reader .
echo '------------ Building file-reader component finished ------------'
