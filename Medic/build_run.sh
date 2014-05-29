# Jboss home
jboss=/home/vako/Tsu/wildfly-8.0.0.Final
# Developers Home
DFH=/home/vako/Tsu/medicalProject/Medic

if [[ -e $jboss/standalone/deployments/medic.war ]]; then
   rm $jboss/standalone/deployments/medic.war
fi

if [[ -e $jboss/standalone/deployments/medic.war.deployed ]]; then
   rm $jboss/standalone/deployments/medic.war.deployed
fi

mvn clean
mvn install -Dmaven.test.skip=true

if [[ -e $DFH/target/medic.war ]]; then
	cp $DFH/target/medic.war $jboss$/standalone/deployments
fi

cd $jboss/bin

if [[ -e $jboss/standalone/deployments/medic.war ]]; then
	sh standalone.sh
fi

sleep