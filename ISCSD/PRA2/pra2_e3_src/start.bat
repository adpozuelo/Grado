cd bin
javac ..\src\*.java -d .
set classpath =.
start rmiregistry 1099
ping -n 1 127.0.0.1 > C:\temp\sleep.txt
start java -cp postgresql-9.2-1002.jdbc4.jar;. -Djava.rmi.codebase=file:./ RMIEAgendaServer
ping -n 2 127.0.0.1 > C:\temp\sleep.txt
start java RMIEAgendaClient
cd ..