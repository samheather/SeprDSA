
.PHONY: clean
.PHONY: all
.PHONY: run


all:
	javac -d bin -cp `find lib -name "*.jar" | tr "\n" ":"` -sourcepath src src/SeprDSA.java

clean:
	rm -rf bin
	mkdir bin

run:
	java -cp `find lib -name "*.jar" | tr "\n" ":"`:bin -Djava.library.path=lib/native SeprDSA

