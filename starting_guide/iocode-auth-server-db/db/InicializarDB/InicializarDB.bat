@echo off

SET BDNAME=%~1

echo Configuration:
echo BDNAME=%BDNAME%
echo.

set ERROR=0

echo Setting environment variables
SET PGPASSWORD=postgres

echo Removing Database.....
psql -U postgres -h localhost -f ../scripts/dropUserAndDatabase.sql 1> dropUserAndDatabase.log 2>&1
call :checkLogBBDD dropUserAndDatabase.log

echo Creating Database.....
psql -U postgres -h localhost -f ../scripts/createUserAndDatabase.sql 1> createUserAndDatabase.log 2>&1
call :checkLogBBDD createUserAndDatabase.log

echo Creating Database Structure.....
psql -U postgres -h localhost -d db_hospedajes_notifications -f ../scripts/createTables.sql 1> createTables.log 2>&1
call :checkLogBBDD createTables.log

echo Inserting Minimum Data.....
psql -U postgres -h localhost -d db_hospedajes_notifications -f ../scripts/insertConfigData.sql 1> insertConfigData.log 2>&1
call :checkLogBBDD insertConfigData.log


echo Finishing off the process for %BDNAME%.
exit /b %ERROR%

goto :eof

:checkLogBBDD
::Check there are no errors
FindStr /c:"no se reconoce" %1
IF %ERRORLEVEL% EQU 0 (
  echo ERROR: An error has been produced while executing scripts. Check %1 for details
  set ERROR=1
  exit /b 1
)
FindStr /c:"error" %1
IF %ERRORLEVEL% EQU 0 (
  echo ERROR: An error has been produced while executing scripts. Check %1 for details
  set ERROR=1
  exit /b 1
)
