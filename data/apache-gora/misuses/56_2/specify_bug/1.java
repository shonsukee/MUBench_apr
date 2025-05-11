public void testWritesReads() throws IOException {
    Properties props = new Properties();
    props.setProperty("keyBlah", "valueBlah");
    props.setProperty("keyBlah2", "valueBlah2");

    byte[] data;
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         DataOutputStream out = new DataOutputStream(bos)) {
        WritableUtils.writeProperties(out, props);
        out.flush();
        data = bos.toByteArray();
    }

    Properties propsRead;
    try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
         DataInputStream in = new DataInputStream(bis)) {
        propsRead = WritableUtils.readProperties(in);
    }

    assertEquals(propsRead.getProperty("keyBlah"), props.getProperty("keyBlah"));
    assertEquals(propsRead.getProperty("keyBlah2"), props.getProperty("keyBlah2"));
}
