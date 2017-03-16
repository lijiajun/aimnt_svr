#!/bin/bash

export MNT_HOME=/home/ngboss/aimnt_server/aimnt_svr
CLASSPATH=$CLASSPATH:$MNT_HOME/lib/*

taskname=$1
strdate=`date "+%Y%m%d"`

if [[ -z "$taskname" ]] 
then
    echo "ple set param!"
    echo "Example: sh startServer.sh task=onlineInfoBusiness"
    exit
fi
echo "taskname --- $taskname"
echo "java com.ai.mnt.exec.StartTask task=$taskname >> /data/logs/server/mnt_svr_${taskname}_${strdate}.log 2>&1 &"

nohup java com.ai.mnt.exec.StartTask task=$taskname >> /data/logs/server/mnt_svr_${taskname}_${strdate}.log 2>&1 &

echo "Start AIMNT Server Success!"
