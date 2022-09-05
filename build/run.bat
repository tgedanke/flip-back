@echo off
chcp 65001
set base.path=%cd%
set config.path=%base.path%\config

set spring.profile.active=prod
set spring.config.location=%config.path%\application.yml
set server.port = 9090

"%JAVA_HOME%\bin\java.exe" -jar SamsungAPIService.jar

pause