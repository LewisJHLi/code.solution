# About
This is the solution for merging company code challenge from https://github.com/tosumitagrawal/codingskills

## Naming Convention
For company A, files shoud be name as catalogA.csv/ suppliersA.csv/ barcodesA.csv

## Test Coverage
The test coverage is about 90%.

![Test Coverage](https://user-images.githubusercontent.com/18642349/135565341-194a009c-b0b1-47d5-933b-55e05146dce2.png)


## Step to run
1. cd into code.solution dir
2. Add input files into the input dir
3. ```bash gradle clean build ``` to run the unit tests and build jar file (test created files will be located in input and output dir)
4. ```bash java -jar build/libs/code.solution-1.0.jar ``` to execute the jar
5. "result_output.csv" should be located in the output dir
