
SET SERVICE_NAME=DongBoHoSo
SET NSSM_DISK=E:\setup\HaiDuong\nssm-2.24\win64\nssm.exe
SET PATH_JAR=E:\setup\HaiDuong\DongBoHS\DongBoHS-0.0.1-SNAPSHOT.jar

%NSSM_DISK% install %SERVICE_NAME% java -jar %PATH_JAR%
echo "Service is running "