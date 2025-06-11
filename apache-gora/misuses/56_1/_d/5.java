static long encl(long l) {
    byte[] bytes;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos)) {
        dos.writeLong(l);
        dos.flush();
        bytes = baos.toByteArray();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return encoder.decodeLong(bytes);
}
