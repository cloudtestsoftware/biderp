#!/bin/sh

#To change into unix format open the file in vi
# Then type :set ff=unix and save the file

export JAVA_HOME=/home/srimanta/Documents/jdk1.8.0_121
export DATA_PATH=/home/srimanta/erp/biderp/WebContent/data
export JAR_PATH=/home/srimanta/erp/biderp/WebContent/WEB-INF/lib
export PATH=$JAVA_HOME/bin:$PATH

export SPRING_JARS=$JAR_PATH/org.springframework.beans-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.core-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.jdbc-3.0.5.RELEASE.jar:$JAR_PATH/org.springframework.transaction-3.0.5.RELEASE.jar

export JAVA_CLASSPATH=.$JAVA_CLASSPATH:$JAR_PATH/servlet.jar:$JAR_PATH/biderpinfra.jar:$SPRING_JARS:$JAR_PATH/ojdbc6.jar:$JAR_PATH/dom4j-1.6.1.jar:$JAVA_HOME/bin:$JAVA_HOME/lib:$JAVA_HOME/lib/tools.jar:$JAR_PATH/xerces.jar:$JAR_PATH/tomcat-dbcp.jar:$JAR_PATH/commons-logging-1.1.jar:

### Import Full schema and compile full
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat  -compile -log cms.log -inifile $DATA_PATH/context.xml

### Import Single table and No compile
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table RFQEmailDocs -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table Bids -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table RFQ -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table RfqParts -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table BidTasks -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table QRTasks -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table POTasks -compile  -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table TRTasks -compile  -log cms.log -inifile $DATA_PATH/context.xml



#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table Bids -compile -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table QuoteResource -compile -log cms.log -inifile $DATA_PATH/context.xml
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table BidRequest -compile -log cms.log -inifile $DATA_PATH/context.xml


#java -classpath $JAVA_CLASSPATH% cms.service.gen.datagen -import jobcode  -file $DATA_PATH/cmsjobcodenew.dat -dbtype oracle  -log cms.log -inifile $DATA_PATH/context.xml

java -classpath $JAVA_CLASSPATH% cms.service.gen.datagen -import rule -file $DATA_PATH/cmsruleraw.dat  -log rule.log -inifile $DATA_PATH/context.xml


#### import full schema without compile
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat  -log cms.log -inifile $DATA_PATH/context.xml

#### import single table without compile
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen -import schema -file $DATA_PATH/cms_service.dat -table QuizReply  -log cms.log -inifile $DATA_PATH/context.xml

#### compile all views
#java -classpath $JAVA_CLASSPATH cms.service.gen.datagen  -compileview -log cms.log -inifile $DATA_PATH/context.xml
