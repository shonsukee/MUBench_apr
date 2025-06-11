static long encl(long l) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    try {
      dos.writeLong(l);
      dos.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return encoder.decodeLong(baos.toByteArray());
}
