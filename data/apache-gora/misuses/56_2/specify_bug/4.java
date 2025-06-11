public void testWritesReads() throws Exception {
  Properties props = new Properties();
  props.put("keyBlah", "valueBlah");
  props.put("keyBlah2", "valueBlah2");

  ByteArrayOutputStream bos = new ByteArrayOutputStream();
  DataOutputStream out = new DataOutputStream(bos);
  WritableUtils.writeProperties(out, props);
  out.flush();

  DataInputStream in = new DataInputStream(new ByteArrayInputStream(bos.toByteArray()));
  Properties propsRead = WritableUtils.readProperties(in);

  assertEquals(propsRead.get("keyBlah"), props.get("keyBlah"));
  assertEquals(propsRead.get("keyBlah2"), props.get("keyBlah2"));
}
