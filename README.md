## Command Line Interface Calculator

### based on Shunting yard algorithm

#### SDK: OpenJDK 21.0.2

To use program:

* cd src
* Compile:```javac -d out Main.java```
* Run: ```java -cp ./out Main```

---

To build .jar file:

* cd out
* Pack: ```jar cfe calculator.jar Main *.class calculator/```
* Run: ```java -jar calculator.jar```

---

To use tests:

* cd src
* Compile (windows):```javac -d out -cp "junit-platform-console-standalone-1.9.3.jar;." -sourcepath . test/*.java```
* Compile (linux):```javac -d out -cp "junit-platform-console-standalone-1.9.3.jar:." -sourcepath . test/*.java```
* Run: ```java -jar junit-platform-console-standalone-1.9.3.jar --class-path out --scan-class-path```

---

To use checkstyle:

* Run: ```java -jar checkstyle-10.16.0.jar -c checkstyle.xml src```

