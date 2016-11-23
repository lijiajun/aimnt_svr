@echo off

SET LIB_PATH=..\lib
SET CLASSPATH=%CLASSPATH%;%LIB_PATH%\*

java -jar aimnt_svr.jar > d:\mnt_svr.log 2>&1
pause