#!/bin/bash

export MNT_HOME=/home/ngboss/aimnt_server/aimnt_svr
CLASSPATH=$CLASSPATH:$MNT_HOME/lib/*

nohup java com.ai.mnt.exec.StartTask >> /data/logs/server/mnt_svr.log 2>&1 &

echo "Start AIMNT Server Success!"
