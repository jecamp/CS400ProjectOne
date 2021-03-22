run:
default: make run
	
compile:
FrontendInterface.class: FrontendInterface.java
	javac FrontendInterface.java

make run: FrontendInterface.class
	java FrontendInterface "movies.csv"
	
test: testData testBackend testFrontend

testFrontend: FrontendInterface.java
	java TestFrontend
TestFrontend.class: FrontendInterface.java
	javac TestFrontend.java

testBackend: FrontendInterface.java
	java TestBackend
TestBackend.class: Backend.java
	javac TestBackend.java

testData: FrontendInterface.java
	java TestMovieAndDataReader
TestMovieAndMovieDataReader.class: MovieDataReader.java
	javac MovieDataReader.java

clean:
	$(RM) *.class
