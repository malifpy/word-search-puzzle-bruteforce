# Script file untuk build di Linux

# Kalau pakai VSCode bisa install
# Extension "Project Manager for Java"
# terus bisa export to jar dari situ

FILENAME=Tucil1_13520135

cd src/
javac *.java
mv *.class ../bin
cd ../bin
# Ganti isi MANIFEST.MF untuk ngubah file yang di-run
jar -cmvf MANIFEST.MF $FILENAME.jar *.class
mv $FILENAME.jar ..