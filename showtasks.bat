call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startbrowser
echo.
echo SHOW TASKS has errors
goto fail

@rem it will start default browser
:startbrowser
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end

:fail
echo.
echo Something is wrong, check runcrud.bat

:end
echo.
echo Check your web browser