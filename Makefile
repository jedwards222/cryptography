# Makefile for cryptography project
# James Edwards
# December 2016

make:
	javac *.java

run:
	java Tester

clean:
	rm -f *.class
