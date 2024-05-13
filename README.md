## Command Line Interface Calculator

### based on Shunting yard algorithm

#### SDK: OpenJDK 21.0.2

To use program:

* Change Directory to \src
* Compile: ```javac Main.java```
* Run: ```java Main.java```

***
To use tests:

* Change Directory to \src
* Compile:
  ```javac -cp junit-platform-console-standalone-1.9.3.jar;. test\CalculateTest.java; test\ShuntingYardTest.java```
* Run: ```java -jar junit-platform-console-standalone-1.9.3.jar --class-path .;test --scan-class-path```
