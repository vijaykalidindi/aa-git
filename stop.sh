#!/bin/bash
HOME=`pwd`
echo '---- Stopping components ---'
echo ''
cd ${HOME}/file-reader && ./stop.sh
cd ${HOME}/transaction-summary && ./stop.sh
cd ${HOME}/kafka && ./stop.sh
echo '--- Stopped ---'
