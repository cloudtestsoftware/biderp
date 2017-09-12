#!/bin/sh

#To change into unix format open the file in vi
# Then type :set ff=unix and save the file

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_25.jdk/Contents/Home
export DATA_PATH=/Users/srimanta.jana/Documents/softlean/SoftleanService/data
export APP_HOME=/Users/srimanta.jana/MyProjects/webapps/generator
export JAR_PATH=/Users/srimanta.jana/SoftleanErp/3rdparty/lib
export PATH=$JAVA_HOME/bin:$PATH

export SPRING_JARS=$JAR_PATH/org.springframework.beans-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.core-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.jdbc-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.transaction-3.0.5.RELEASE.jar

export JAVA_CLASSPATH=.$JAVA_CLASSPATH:$JAR_PATH/serviceinfra.jar:$SPRING_JARS:$JAR_PATH/ojdbc6.jar:$JAR_PATH/dom4j-1.6.1.jar:$JAVA_HOME/bin:$JAVA_HOME/lib:$JAVA_HOME/lib/tools.jar:$JAR_PATH/xerces.jar:$JAR_PATH/tomcat-dbcp.jar:$JAR_PATH/commons-logging-1.1.jar:

java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table Budget  -log cms.log -inifile=$DATA_PATH/context.xml

#java -classpath $JAVA_CLASSPATH% cms.service.gen.datagen -import jobcode  -file $DATA_PATH/cmsjobcodenew.dat -dbtype oracle  -log cms.log -inifile=$DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH% cms.service.gen.datagen -import rule -file $DATA_PATH/cmsrule.dat  -log rule.log -inifile=$DATA_PATH/context.xml


