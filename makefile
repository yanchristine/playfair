run: Playfair.class
	java Playfair $(ARGS)

Playfair.class: Playfair.java
	javac Playfair.java

clean:
	rm *.class

help:
	@echo to decode: make run ARGS='decode ciphertext keyString'
	@echo to encode: make run ARGS='encode plaintext keyString'
