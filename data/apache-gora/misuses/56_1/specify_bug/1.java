static long encl(long l) {
    byte[] data;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos)) {
        dos.writeLong(l);
        dos.flush();
        data = baos.toByteArray();
    } catch (IOException e) {
        throw new UncheckedIOException(e);
    }
    return encoder.decodeLong(data);
}
