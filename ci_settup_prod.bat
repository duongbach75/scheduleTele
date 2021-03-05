
SET SERVICE_NAME=DongBoHoSo
SET NSSM_DISK=C:\nssm\win64\nssm.exe
SET PATH_JAR=C:\Setup\DongBoHoSo\DongBoHS-0.0.1-SNAPSHOT.jar

%NSSM_DISK% install %SERVICE_NAME% java -jar %PATH_JAR%
echo "Service is running"