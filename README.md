# Remitly Poland - Home exercise 
Author: Mikołaj Florczak

## About
This project is a solution to home exercise for first stage of recruitment process
for summer internship as Java Developer for Remitly Poland. <br/>

Method `resourceDoesNotContainsSingleAsterisk` checks value of `Resource` field
and returns logical true if the value is different from `*`.
If there are more than one element in `Statement` array method will return logical
false if at least one `Resource` field contains `*`.

In `main` function I presented example use of `resourceDoesNotContainsSingleAsterisk` method:

- The program reads an array of json objects from file `data.json`. 

- Validates if json format is `AWS::IAM:Role Policy` and throws `RuntimeException` 
with a suitable message, if it's not.

- Prints message describing every json object in `data.json`. 
  - `Single Asterisk` - if at least one `Resource` field is `*`,
  - `Not Single Asterisk` - if none of the `Resource` fields is `*` ,
  - `Wrong json format: ? not found` - if there are any of fields missing 
  (`Resource`, `Statement`, `PolicyDocument`, `PolicyName`),

## How to run
- Download or clone the repository.<br/>

- Open the project in IntelliJ<br/>

![open.png](..%2F..%2F..%2FPictures%2Fopen.png) <br/>

- Open Main class and run the program<br/><br/>
![main.png](..%2F..%2F..%2FPictures%2Fmain.png)<br/><br/>