
all:
	javac Main.java Model/*.java View/*.java && mv Model/*.class View/*.class .

clean:
	rm *.class