
# $arrService = Get-Service -Name $SERVICE_NAME
# if ($arrService.Status -ne 'Stopped'){ net STOP $SERVICE_NAME } else {  }

If (Get-Service $SERVICE_NAME -ErrorAction SilentlyContinue) {

    If ((Get-Service $SERVICE_NAME).Status -eq 'Running') {

        echo "Service is running "
        Stop-Service $SERVICE_NAME

    } Else {

        echo "Service is stop"

    }

} Else {

    .\ci_settup.bat

}
