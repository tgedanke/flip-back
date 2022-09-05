@echo off
chcp 65001
set base.path=%cd%
set config.path=%base.path%\config

set spring.profiles.active=debug
set spring.config.location=%config.path%\application.yml
set server.port = 8080

"%JAVA_HOME%\bin\java.exe" -jar SamsungAPIService.jar

pause