
.PHONY: clean
.PHONY: all
.PHONY: run
.PHONY: car
# car does all of cLEAN, aLL, rUN

all:
	javac -d bin -cp `find lib -name "*.jar" | tr "\n" ":"` -sourcepath src src/SeprDSA.java

clean:
	rm -rf bin
	mkdir bin

run:
	java -cp `find lib -name "*.jar" | tr "\n" ":"`:bin -Djava.library.path=lib/native SeprDSA
car:
	javac -d bin -cp `find lib -name "*.jar" | tr "\n" ":"` -sourcepath src src/SeprDSA.java	
	rm -rf bin
	mkdir bin
	java -cp `find lib -name "*.jar" | tr "\n" ":"`:bin -Djava.library.path=lib/native SeprDSA

