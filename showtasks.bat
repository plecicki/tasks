call ./runcrud
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo RUNCRUD has errors - breaking work
goto fail

:openbrowser
set url="http://localhost:8080/crud/v1/tasks"
start chrome %url%
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Opening browser has errors - breaking work
goto fail

:fail
echo.
echo There (showtasks.bat) were errors

:end
echo.
echo Work is finished.