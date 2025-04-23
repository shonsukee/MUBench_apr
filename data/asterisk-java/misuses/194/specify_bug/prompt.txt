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
- ### Java API documentation is official documentation and must follow.
- Be sure to fix any maintainability issues that exist.
- If Java API documentation include throws Exception, it must be handled.
- Bug exists in line 12 to 19 of the ## Input code.

## Input Code
```java
public class RtcpReceivedEvent extends AbstractRtcpEvent
{
    public void setPt(String ptString)
    {
        // Format is "PT: %d(%s)"
        if (ptString == null || ptString.length() == 0)
        {
            this.pt = null;
            return;
        }

        if (ptString.indexOf('(') > 0)
        {
            this.pt = Long.parseLong(ptString.substring(0, ptString.indexOf('(')));
        }
        else
        {
            this.pt = Long.parseLong(ptString);
        }
    }
}
```

## Context
### Java API documentation
```
parseLong
public static long parseLong(String s)
                      throws NumberFormatException
Parses the string argument as a signed decimal long. The characters in the string must all be decimal digits, except that the first character may be an ASCII minus sign '-' (\u002D') to indicate a negative value or an ASCII plus sign '+' ('\u002B') to indicate a positive value. The resulting long value is returned, exactly as if the argument and the radix 10 were given as arguments to the parseLong(java.lang.String, int) method.
Note that neither the character L ('\u004C') nor l ('\u006C') is permitted to appear at the end of the string as a type indicator, as would be permitted in Java programming language source code.

Parameters:
s - a String containing the long representation to be parsed
Returns:
the long represented by the argument in decimal.
Throws:
NumberFormatException - if the string does not contain a parsable long.
```

## Output Indicator
Update the ### Input  Code as per the latest API specification, making necessary modifications.
Ensure the structure and format remain as close as possible to the original, but deprecated code must be updated. Output the all revised code without additional explanations or comments.
