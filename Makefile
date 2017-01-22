# Makefile for cryptography project
# James Edwards
# December 2016

make:
	javac ciphers/*.java
	javac drivers/*.java
	javac *.java
	touch input.txt
	nano input.txt

run:
	java Tester

clean:
	rm -f *.class
	rm -f ciphers/*.class
	rm -f drivers/*.class
	rm -f input.txt

caesar:
	java drivers/CaesarDriver

custom:
	java drivers/CustomDriver

grid:
	java drivers/GridDriver

shifting:
	java drivers/ShiftingDriver
