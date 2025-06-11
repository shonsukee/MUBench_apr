@Test
public void testWritesReads() throws Exception {
    Properties props = new Properties();
    props.setProperty("keyBlah", "valueBlah");
    props.setProperty("keyBlah2", "valueBlah2");

    byte[] data;
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         DataOutputStream out = new DataOutputStream(bos)) {
        WritableUtils.writeProperties(out, props);
        data = bos.toByteArray();
    }

    Properties propsRead;
    try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(data))) {
        propsRead = WritableUtils.readProperties(in);
    }

    assertEquals(props.getProperty("keyBlah"), propsRead.getProperty("keyBlah"));
    assertEquals(props.getProperty("keyBlah2"), propsRead.getProperty("keyBlah2"));
}
