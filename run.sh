#!/bin/bash
HOME=`pwd`
echo '---- Starting components ---'
echo ''
cd ${HOME}/kafka && ./run.sh
cd ${HOME}/file-reader && ./run.sh
cd ${HOME}/transaction-summary && ./run.sh

echo '--- Started ---'
