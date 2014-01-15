
.PHONY: clean
.PHONY: all
.PHONY: run
.PHONY: e

all:
	javac -d bin -cp `find lib -name "*.jar" | tr "\n" ":"` -sourcepath src src/main/SeprDSA.java

clean:
	rm -rf bin
	mkdir bin

run:
	java -cp `find lib -name "*.jar" | tr "\n" ":"`:bin -Dsun.awt.disableMixing=true -Djava.library.path=lib/native main/SeprDSA
	
e:
	rm -rf bin
	mkdir bin
	javac -d bin -cp `find lib -name "*.jar" | tr "\n" ":"` -sourcepath src src/main/SeprDSA.java
	java -cp `find lib -name "*.jar" | tr "\n" ":"`:bin -Dsun.awt.disableMixing=true -Djava.library.path=lib/native main/SeprDSA
