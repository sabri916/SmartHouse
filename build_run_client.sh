#To Build Common Package
javac src/om/edu/mec/smartHouseCommon/*.java -d class/

#To Build Client
javac src/om/edu/mec/smartHouseClient/*.java -d class/ -classpath class/

#To Generate JAR for Client
jar cfm SmartHouseClient.jar clientManifest.txt -C class/ om/edu/mec/smartHouseClient/ -C class/ om/edu/mec/smartHouseCommon/SmartHouseModel.class res/

#To Run Client
java -jar SmartHouseClient.jar