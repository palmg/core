@echo off 

echo begin maven deploy

set BASE_DIR=%cd%
set WORK_DIR=%BASE_DIR%\..\
cd %WORK_DIR%
echo folder£º%cd%

call mvn clean deploy -U -Dmaven.test.skip=true


pause