# Makefile for cryptography project
# James Edwards
# December 2016

make:
	javac *.java
	touch input.txt
	nano input.txt

run:
	java Tester

clean:
	rm -f *.class
	rm -f input.txt

caesar:
	java CaesarDriver
