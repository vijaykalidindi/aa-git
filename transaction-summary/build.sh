#!/bin/bash
echo ''
echo '------------ Building transaction-summary component started ------------'
mvn clean install -DskipTests
docker build -t vijaykdotcom/transaction-summary .
echo '------------ Building transaction-summary component finished ------------'
