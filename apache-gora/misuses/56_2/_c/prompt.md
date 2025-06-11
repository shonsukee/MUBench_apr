## Instruction
You are a software engineer specializing in REST API.
Use the guidelines below to make any necessary modifications.

### Modification Procedure
0. First, familiarise yourself with the following steps and ### Notes.
1. Check the technical specifications of the Java API that you have studied or in the official documentation. If you don't know, output the ### Input Code as it is.
2. Based on the technical specifications of the Java API you have reviewed in step 1, identify the code according to the deprecated specifications contained in the ### Input Code. In this case, the deprecated specifications are the Java API calls that have been deprecated. If no code according to the deprecated specification is found, identify code that is not based on best practice. If you are not sure, output the ### Input Code as it is.
3. If you find code according to the deprecated specification or not based on best practice in step 2, check the technical specifications in the Java API that you have studied or in the official documentation. If you are not sure, output the ### Input Code as it is.
4. With attention to the points listed in ### Notes below, modify the code identified in step 2 to follow the recommended specification analysed in step 3.
5. Verify again that the modified code works correctly.
6. If you determine that it works correctly, output the modified code.
7. If it is judged to fail, output the ### Input Code as it is.
8. If you are not sure, output the ### Input Code as it is.

### Notes.
- Official documentation must be retrieved from this domain. "https://www.oracle.com/jp/java/technologies/documentation.html".
- Make modifications according to the Oracle Java 8 API specification.
- "https://docs.oracle.com/javase/8/docs/api/java/io/DataOutputStream.html"


## Input Code
```java
  @Test
  public void testWritesReads() throws Exception {
    Properties props = new Properties();
    props.put("keyBlah", "valueBlah");
    props.put("keyBlah2", "valueBlah2");

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutput out = new DataOutputStream(bos);
    WritableUtils.writeProperties(out, props);


    DataInput in = new DataInputStream(new ByteArrayInputStream(bos.toByteArray()));

    Properties propsRead = WritableUtils.readProperties(in);

    assertEquals(propsRead.get("keyBlah"), props.get("keyBlah"));
    assertEquals(propsRead.get("keyBlah2"), props.get("keyBlah2"));
  }
```

## Output Indicator
Update the ### Input  Code as per the latest API specification, making necessary modifications.
Ensure the structure and format remain as close as possible to the original, but deprecated code must be updated. Output the all revised code without additional explanations or comments.