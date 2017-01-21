# Cryptography
##### James Edwards, December 2016

#### Overview
This project contains several different implementations of ciphers, inspired by
Simon Singh's *[The Code Book](http://simonsingh.net/books/the-code-book/)*. Currently
it includes 4 ciphers:

1. a [Caesar cipher](https://en.wikipedia.org/wiki/Caesar_cipher),
which works by replacing each letter in the plaintext with a letter that is some
constant number of spaces away in the real alphabet.

2. a [substitution cipher](https://en.wikipedia.org/wiki/Substitution_cipher),
which works by replacing each letter in the plaintext according to a constant cipher
alphabet.

3. a custom shifting substitution cipher, which works in the same way as a normal
substitution cipher, except that it shifts the alphabet used by some constant number
of positions after each letter is substituted.

4. a ["grid" cipher](https://en.wikipedia.org/wiki/Transposition_cipher#Columnar_transposition)
, which works by placing the plaintext into a grid column by column, and then
reading from the grid row by row to generate the ciphertext. The current version
in the project is a simplified version of the description that is linked to.

#### Compiling

Compilation is simple: the user can either manually run `javac *.java` or use the customized `make` command from the root directory. Either option will compile all of the necessary java files. The `make` command will also create an empty `input.txt` file and open in it in the Nano text editor. The user will then type in the input message that they will want to encrypt or decrypt. The command `make clean` can be used to remove the `.class` and `input.txt` files that are generated by compilation.

#### Running

To run the test file, the user may use the command `make run` or `java Tester`. The user should manually fill in the file `input.txt` with whatever text they would like to be encoded. The test program will then output the original contents of the input file, followed by the contents of the file after the various ciphers have been applied to it.

<!-- TODO:
  ~ add in feature for user to create cipher alphabet file
  ~ try implementing FileChooser correctly
  ~ output results to an output file, allow for clearing of input file?
  ~ Allow user to choose only one cipher result
  ~ Add additional cipher options -->
