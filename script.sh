#!/bin/bash
cd framework/src/ 
javac -classpath gson-2.8.6.jar:/home/mertina/Bureau/L2/variableClasspath/servlet-api.jar:. -d . *.java
jar cvf ../../test_framework/WEB-INF/lib/framework.jar etu002087
cd ../../test_framework/WEB-INF/classes/
javac -classpath /home/mertina/Bureau/L2/Web_Dynamique/RepertoireDetravailler/UtilisationframeWork/test_framework/WEB-INF/lib/framework.jar -d . *.java
cp -r /home/mertina/Bureau/L2/Web_Dynamique/RepertoireDetravailler/UtilisationframeWork/test_framework /home/mertina/Bureau/L2/Web_Dynamique/tomcat1/apache-tomcat-10.0.27/webapps/
cd /home/mertina/Bureau/L2/Web_Dynamique/tomcat1/apache-tomcat-10.0.27/bin

javac -classpath ./framework.jar -d . *.java

CATALINA_HOME=/home/mertina/Bureau/L2/Web_Dynamique/tomcat1/apache-tomcat-10.0.27/
${CATALINA_HOME}/bin/startup.sh

