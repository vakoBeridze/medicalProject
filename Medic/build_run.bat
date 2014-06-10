rem Jboss home
set jboss=E:\From Vako-PC\Server\jboss-eap-6.2
rem Developers Home
set DFH=E:\From Vako-PC\Development\medicalProject\Medic

rem rd /Q /S %jboss%\standalone\data
rem rd /Q /S %jboss%\standalone\lib
rem rd /Q /S %jboss%\standalone\log
rem rd /Q /S %jboss%\standalone\tmp

if EXIST %jboss%\standalone\deployments\Medic.war (
   del /Q %jboss%\standalone\deployments\Medic.war
)
if EXIST %jboss%\standalone\deployments\Medic.war.deployed (
   del /Q %jboss%\standalone\deployments\Medic.war.deployed
)

call mvn clean
call mvn install -Dmaven.test.skip=true

if EXIST %DFH%\target\Medic.war (
   copy %DFH%\target\Medic.war %jboss%\standalone\deployments
)

cd %jboss%\bin

if EXIST %jboss%\standalone\deployments\Medic.war goto START_SERVER

goto END

:START_SERVER
   call run.bat

:END
pause