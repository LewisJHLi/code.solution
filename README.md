# About
This is the solution for merging company code challenge from https://github.com/tosumitagrawal/codingskills

## Naming Convention
For company A, files shoud be name as catalogA.csv/ suppliersA.csv/ barcodesA.csv

## Test Coverage
The test coverage is about 90%.

<img width="537" alt="Test Coverage" src="https://user-images.githubusercontent.com/18642349/135735150-187a337f-e7f4-4f47-808d-b1c8ec8d07bf.png">


## Step to run
1. cd into code.solution dir
2. Add input files into the input dir if not there
3. ```gradle clean build ``` to run the unit tests and build jar file (test created files will be located in input and output dir)
4. ```java -jar build/libs/code.solution-1.0.jar ``` to execute the jar
5. "result_output.csv" should be located in the output dir
