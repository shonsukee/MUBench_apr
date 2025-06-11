@Test
public void testWritesReads() throws Exception {
    Properties props = new Properties();
    props.setProperty("keyBlah","valueBlah");
    props.setProperty("keyBlah2","valueBlah2");
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(bos);
    WritableUtils.writeProperties(dos, props);
    dos.flush();
    DataInput in = new DataInputStream(new ByteArrayInputStream(bos.toByteArray()));
    Properties propsRead = WritableUtils.readProperties(in);
    assertEquals(propsRead.getProperty("keyBlah"), props.getProperty("keyBlah"));
    assertEquals(propsRead.getProperty("keyBlah2"), props.getProperty("keyBlah2"));
}
