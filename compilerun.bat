echo off
mkdir bin
copy "%cd%\src\NizamLayout.fxml" "%cd%\bin\src"
echo on
javac -d bin src\*.java
cd bin && java src.Nizam
cd ..