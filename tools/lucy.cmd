@SET lucybin=%~dp0
@java -jar -Xmx512m %lucybin:~0,-1%\..\lib\lucy.jar %*
