# Jobsity Code Challenge

Eduardo Zanela Java Code Challenge - Bowling App

## Prerequisites

Clone repo and install dependencies

```
$ git clone https://github.com/EduardoZanela/JavaBowlingChallenge.git
$ cd JavaBowlingChallenge
$ mvn clean package
```

## Run

This is a command-line app which reads a file that contains Bowling Rolls as follows:

``` 
Jeff	10
Jeff	10
Jeff	10
Jeff	10
Jeff	10
Jeff	10
Jeff	10
Jeff	10
Jeff	10
Jeff	10
Jeff	10
Jeff	10
```

Run it as a Java application, file path is a parameter. There is a folder with three scenarios that can be use as parameters `perfect.txt`, `rolls.txt` and `zero.txt`    

```
$ java -jar target\JavaBowlingChallenge-1.0-SNAPSHOT.jar --file input\rolls.txt
```