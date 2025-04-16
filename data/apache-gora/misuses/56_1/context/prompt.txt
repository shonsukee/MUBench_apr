## Instruction
You are a software engineer specializing in REST API.
Use the guidelines below to make any necessary modifications.

### Modification Procedure
0. First, familiarise yourself with the following steps and ### Notes.
1. Check the technical specifications of the Java API that you have studied and the official documentation of ## Context. If you don't know, output the ### Input Code as it is.
2. Based on the technical specifications of the Java API you have reviewed in step 1, identify the code according to the deprecated specifications contained in the ### Input Code. In this case, the deprecated specifications are the Java API calls that have been deprecated. If no code according to the deprecated specification is found, identify code that is not based on best practice. If you are not sure, output the ### Input Code as it is.
3. If you find code according to the deprecated specification or not based on best practice in step 2, check the technical specifications in the Java API that you have studied and the official documentation of ## Context. If you are not sure, output the ### Input Code as it is.
4. With attention to the points listed in ### Notes below, modify the code identified in step 2 to follow the recommended specification analysed in step 3.
5. Verify again that the modified code works correctly.
6. If you determine that it works correctly, output the modified code.
7. If it is judged to fail, output the ### Input Code as it is.
8. If you are not sure, output the ### Input Code as it is.

### Notes.
- You must follow the official documentation of ## Context.

## Input Code
```java
  static long encl(long l) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    try {
      dos.writeLong(l);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return encoder.decodeLong(baos.toByteArray());
  }
```

## Context
```
ByteArrayOutputStream baos = new ByteArrayOutputStream();
DataOutputStream w = new DataOutputStream(baos);

w.writeInt(100);
w.write(byteArray);

w.flush();

byte[] result = baos.toByteArray();
```
```
writeLong
public final void writeLong(long v)
                     throws IOException
Writes a long to the underlying output stream as eight bytes, high byte first. In no exception is thrown, the counter written is incremented by 8.
Specified by:
writeLong in interface DataOutput
Parameters:
v - a long to be written.
Throws:
IOException - if an I/O error occurs.
See Also:
FilterOutputStream.out

flush
public void flush()
           throws IOException
Flushes this data output stream. This forces any buffered output bytes to be written out to the stream.
The flush method of DataOutputStream calls the flush method of its underlying output stream.

Specified by:
flush in interface Flushable
Overrides:
flush in class FilterOutputStream
Throws:
IOException - if an I/O error occurs.
See Also:
FilterOutputStream.out, OutputStream.flush()
```

## Output Indicator
Update the ### Input  Code as per the latest API specification, making necessary modifications.
Ensure the structure and format remain as close as possible to the original, but deprecated code must be updated. Output the all revised code without additional explanations or comments.
