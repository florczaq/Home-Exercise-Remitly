# Remitly Poland - Home exercise 
Author: Mikołaj Florczak

## About
This project is a solution to home exercise for first stage of recruitment process
for summer internship as Java Developer for Remitly Poland. <br/>

Method `resourceDoesNotContainsSingleAsterisk` checks value of `Resource` field
and returns logical true if the value is different from `*`.
If there is more than one element in `Statement` array method will return logical
false as long as at least one `Resource` field contains `*`.

In `main` function, I presented an example of how to use `resourceDoesNotContainsSingleAsterisk` method:

- The program reads an array of json objects from file `data.json`. 

- Validates if json format is `AWS::IAM:Role Policy` and throws `JSONFormatException` 
with a suitable message, if it's not.

- Prints message describing every json object in `data.json`. 
  - `Single Asterisk` - if at least one `Resource` field is `*`,
  - `Not Single Asterisk` - if none of the `Resource` fields is `*` ,
  - `Wrong json format: ? not found` - if there are any of fields missing 
  (`Resource`, `Statement`, `PolicyDocument`, `PolicyName`),

## How to run
- Download or clone the repository.<br/><br/>
![download](https://github.com/florczaq/Home-Exercise-Remitly/assets/84631301/3e19a456-f322-4cbe-bf67-6662b086c186)<br/>
- Open the project in IntelliJ
  - Make sure you have configured Maven on your computer - [How to install maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)<br/><br/>
![open](https://github.com/florczaq/Home-Exercise-Remitly/assets/84631301/8dabec3a-d636-48af-ab03-8c810427394d) <br/>
- Open Main class and run the program<br/><br/>
![main](https://github.com/florczaq/Home-Exercise-Remitly/assets/84631301/df6a7da3-0342-4c70-8cb7-3710519ae344) <br/>
