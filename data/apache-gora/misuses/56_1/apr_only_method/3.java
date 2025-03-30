@Test
public void testWritesReads() throws Exception {
    Properties props = new Properties();
    props.setProperty("keyBlah", "valueBlah");
    props.setProperty("keyBlah2", "valueBlah2");

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutput out = new DataOutputStream(bos);
    WritableUtils.writeProperties(out, props);

    DataInput in = new DataInputStream(new ByteArrayInputStream(bos.toByteArray()));

    Properties propsRead = WritableUtils.readProperties(in);

    assertEquals(props.getProperty("keyBlah"), propsRead.getProperty("keyBlah"));
    assertEquals(props.getProperty("keyBlah2"), propsRead.getProperty("keyBlah2"));
}
