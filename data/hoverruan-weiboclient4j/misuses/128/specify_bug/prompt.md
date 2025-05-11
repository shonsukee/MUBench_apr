## Instruction
You are a software engineer specializing in REST API.
Use the guidelines below to make any necessary modifications.

### Modification Procedure
0. First, familiarise yourself with the following steps and ### Notes.
1. Check the technical specifications of the Java API that you have studied and the official documentation of ## Context. If you don't know, output the ### Input Code as it is.
2. Based on the technical specifications of the Java API you have reviewed in step 1, identify the code according to the deprecated specifications contained in the ### Input Code. In this case, the deprecated specifications are the Java API calls that have been deprecated. If no code according to the deprecated specification is found, identify code with maintainability issues. If you are not sure, output the ### Input Code as it is.
3. If you find code according to the deprecated specification or maintainability issues in step 2, check the technical specifications in the Java API that you have studied and the official documentation of ## Context. If you are not sure, output the ### Input Code as it is.
4. With attention to the points listed in ### Notes below, modify the code identified in step 2 to follow the recommended specification analysed in step 3.
5. Verify again that the modified code works correctly.
6. If you determine that it works correctly, output the modified code.
7. If it is judged to fail, output the ### Input Code as it is.
8. If you are not sure, output the ### Input Code as it is.

### Notes.
- You must follow the ## Context.
- `### Java API documentation` is official documentation and must follow.
- Be sure to fix any maintainability issues that exist.
- If Java API documentation include throws Exception, it must be handled.
- Bug exists in line 5 of the `## Input code`.

## Input Code
```java
    public static Cid cid(String value) {
        return new Cid(value);
    }
```

## Context
### Custom class
```
public class Cid extends LongParam {
    public Cid(String value) {
        this(Long.parseLong(value));
    }
}
```
### Java API documentation
```
parseLong
public static long parseLong(String s,
             int radix)
                      throws NumberFormatException
Parses the string argument as a signed long in the radix specified by the second argument. The characters in the string must all be digits of the specified radix (as determined by whether Character.digit(char, int) returns a nonnegative value), except that the first character may be an ASCII minus sign '-' ('\u002D') to indicate a negative value or an ASCII plus sign '+' ('\u002B') to indicate a positive value. The resulting long value is returned.
Note that neither the character L ('\u004C') nor l ('\u006C') is permitted to appear at the end of the string as a type indicator, as would be permitted in Java programming language source code - except that either L or l may appear as a digit for a radix greater than 22.

An exception of type NumberFormatException is thrown if any of the following situations occurs:

The first argument is null or is a string of length zero.
The radix is either smaller than Character.MIN_RADIX or larger than Character.MAX_RADIX.
Any character of the string is not a digit of the specified radix, except that the first character may be a minus sign '-' ('\u002d') or plus sign '+' ('\u002B') provided that the string is longer than length 1.
The value represented by the string is not a value of type long.
Examples:

 parseLong("0", 10) returns 0L
 parseLong("473", 10) returns 473L
 parseLong("+42", 10) returns 42L
 parseLong("-0", 10) returns 0L
 parseLong("-FF", 16) returns -255L
 parseLong("1100110", 2) returns 102L
 parseLong("99", 8) throws a NumberFormatException
 parseLong("Hazelnut", 10) throws a NumberFormatException
 parseLong("Hazelnut", 36) returns 1356099454469L
 
Parameters:
s - the String containing the long representation to be parsed.
radix - the radix to be used while parsing s.
Returns:
the long represented by the string argument in the specified radix.
Throws:
NumberFormatException - if the string does not contain a parsable long.
```

## Output Indicator
Update the ### Input  Code as per the latest API specification, making necessary modifications.
Ensure the structure and format remain as close as possible to the original, but deprecated code must be updated. Output the all revised code without additional explanations or comments.
