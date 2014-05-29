rem Jboss home
set jboss=D:\Development\Server\jboss-eap-6.2
rem Developers Home
set DFH=D:\Development\TSU\Medic

rem rd /Q /S %jboss%\standalone\data
rem rd /Q /S %jboss%\standalone\lib
rem rd /Q /S %jboss%\standalone\log
rem rd /Q /S %jboss%\standalone\tmp

if EXIST %jboss%\standalone\deployments\medic.war (
   del /Q %jboss%\standalone\deployments\medic.war
)
if EXIST %jboss%\standalone\deployments\medic.war.deployed (
   del /Q %jboss%\standalone\deployments\medic.war.deployed
)

call mvn clean
call mvn install -Dmaven.test.skip=true

if EXIST %DFH%\target\medic.war (
   copy %DFH%\target\medic.war %jboss%\standalone\deployments
)

cd %jboss%\bin

if EXIST %jboss%\standalone\deployments\medic.war goto START_SERVER

goto END

:START_SERVER
   call run.bat

:END
pause