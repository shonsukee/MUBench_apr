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
- Bug exists in line 8 to 12 of the ## Input code.

## Input Code
```java
public final class CalligraphyUtils {
    static final String pullFontPathFromTheme(Context context, int styleId, int attributeId) {
        final Resources.Theme theme = context.getTheme();
        final TypedValue value = new TypedValue();

        theme.resolveAttribute(styleId, value, true);
        final TypedArray typedArray = theme.obtainStyledAttributes(value.resourceId, new int[]{attributeId});
        try {
            return typedArray.getString(0);
        } finally {
            typedArray.recycle();
        }
    }
}
```

## Context
### Java API documentation
```
ObtainStyledAttributes(Int32, Int32[])
Retrieve styled attribute information in this Context's theme.
Parameters
resid
Int32
attrs
Int32[]
Returns
TypedArray
Attributes
RegisterAttribute
Exceptions
Resources.NotFoundException
```
```
getString
Added in API level 1

public String getString (int index)
Retrieves the string value for the attribute at index.

If the attribute is not a string, this method will attempt to coerce it to a string.

Parameters
index	int: Index of attribute to retrieve.
Returns
String	String holding string data. Any styling information is removed. Returns null if the attribute is not defined or could not be coerced to a string.
Throws
RuntimeException	if the TypedArray has already been recycled.
```

## Output Indicator
Update the ### Input  Code as per the latest API specification, making necessary modifications.
Ensure the structure and format remain as close as possible to the original, but deprecated code must be updated. Output the all revised code without additional explanations or comments.
