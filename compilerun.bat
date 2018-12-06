echo off
mkdir bin
javac -d bin src\*.java
copy "%cd%\src\NizamLayout.fxml" "%cd%\bin\src\NizamLayout.fxml"
cd bin && java src.Nizam
cd ..
exit
