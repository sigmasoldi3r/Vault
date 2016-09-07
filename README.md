[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3506b671a3074cd0a1093b466acd3169)](https://www.codacy.com/app/pablobc-1995/Vault?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sigmasoldi3r/Vault&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/sigmasoldi3r/Vault.svg?branch=master)](https://travis-ci.org/sigmasoldi3r/Vault)
# Vault
File encryption system.

*Vault* is an easy encryption system for your files, however does __not support folder name encrypton__ yet.

### Science behind

The mecanism of "Vault" is nothing pioneering in the world of encryption,
what it does basically is roll (Circullary) octets (8-bit bytes) up or down cyclically, using a given string as *key*.

### Example

Imagine we have a text file called `foo.txt` that contains the string `hello world!`.

We use the input string `HELLO` as encryption key, that is the same (at binary level)
to the array `(byte){ 72, 69, 76, 76, 79 }`. This key, will be used to roll up the bytes of the file.

The file contains the array `(byte){ 104, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33 }`, so the operation will be
adding the string `HELLO` over that:
```
HELLOHELLOHE
+
hello world!
=
°ª¸¸¾h¼»¾»¬f
```

What at binary level translates to something like:
`(byte){ 104 + 72, 101 + 69, 108 + 76, 108 + 76, 111 + 79, 32 + 72, 119 + 69, 111 + 76, 114 + 76, 108 + 79, 100 + 72, 33 + 69 }`

Note: If the byte value exceeds 255, it will roll forward from 0 (ie: 250 + 20 => 15).

## Usage

Usage is simple, the program needs a folder called `vault` in the same directory of `vault.jar`, and you invoke in a command
promt `java -jar vault.jar`, then the program asks for the encryption/decryption key, then asks which operation shall be
done *d* or *e*, what means __Decryption__ and __Encryption__.

Simple!
