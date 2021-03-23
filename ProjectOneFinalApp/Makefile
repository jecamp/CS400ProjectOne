run: compile

compile: FrontendInterface.class Backend.class MovieDataReader.class Movie.class
        java FrontendInterface movies.csv

FrontendInterface.class: FrontendInterface.java
        javac FrontendInterface.java

Backend.class: Backend.java
        javac Backend.java

MovieDataReader.class: MovieDataReader.java
        javac MovieDataReader.java

Movie.class: Movie.java
        javac Movie.java

test: testData testBackend testFrontend

testFrontend: TestFrontend.class
        java TestFrontend

TestFrontend.class: TestFrontend.java
        javac TestFrontend.java

testBackend: TestBackend.class
        java TestBackend

TestBackend.class: TestBackend.java
        javac TestBackend.java

testData: TestMovieAndMovieDataReader.class
        java TestMovieAndMovieDataReader

TestMovieAndMovieDataReader.class: TestMovieAndMovieDataReader.java
        javac TestMovieAndMovieDataReader.java

clean:
        $(RM) *.class
