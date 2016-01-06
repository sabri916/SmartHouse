#To Build Common Package
javac src/om/edu/mec/smartHouseCommon/*.java -d class/

#To Build Server
javac src/om/edu/mec/smartHouse/*.java -d class/ -classpath class/

#To Generate JAR for Server
jar cfm SmartHouseServer.jar serverManifest.txt -C class/ om/edu/mec/smartHouse/ -C class/ om/edu/mec/smartHouseCommon/SmartHouseModel.class

#To Run Server
java -jar SmartHouseServer.jar