@Test
public void testWritesReads() throws Exception {
    Properties props = new Properties();
    props.put("keyBlah", "valueBlah");
    props.put("keyBlah2", "valueBlah2");

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(bos);
    WritableUtils.writeProperties(out, props);
    out.flush();

    DataInput in = new DataInputStream(new ByteArrayInputStream(bos.toByteArray()));

    Properties propsRead = WritableUtils.readProperties(in);

    assertEquals(props.get("keyBlah"), propsRead.get("keyBlah"));
    assertEquals(props.get("keyBlah2"), propsRead.get("keyBlah2"));
}
