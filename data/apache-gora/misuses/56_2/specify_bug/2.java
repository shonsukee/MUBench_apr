  public void testWritesReads() throws IOException {
    Properties props = new Properties();
    props.put("keyBlah", "valueBlah");
    props.put("keyBlah2", "valueBlah2");

    byte[] data;
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(bos)) {
      WritableUtils.writeProperties(dos, props);
      dos.flush();
      data = bos.toByteArray();
    }

    Properties propsRead;
    try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
         DataInputStream dis = new DataInputStream(bis)) {
      propsRead = WritableUtils.readProperties(dis);
    }

    assertEquals(propsRead.getProperty("keyBlah"), props.getProperty("keyBlah"));
    assertEquals(propsRead.getProperty("keyBlah2"), props.getProperty("keyBlah2"));
  }
