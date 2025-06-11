@Test
public void testWritesReads() throws Exception {
    Properties props = new Properties();
    props.setProperty("keyBlah", "valueBlah");
    props.setProperty("keyBlah2", "valueBlah2");

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(bos);
    WritableUtils.writeProperties(dos, props);
    dos.flush();

    DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bos.toByteArray()));

    Properties propsRead = WritableUtils.readProperties(dis);

    assertEquals(props.getProperty("keyBlah"), propsRead.getProperty("keyBlah"));
    assertEquals(props.getProperty("keyBlah2"), propsRead.getProperty("keyBlah2"));
}
