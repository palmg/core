@echo off

echo begin maven compile

set BASE_DIR=%cd%
set WORK_DIR=%BASE_DIR%\..\
cd %WORK_DIR%
echo folder£º%cd%

call mvn clean compile -U

pause