static long encl(long l) {
    byte[] bytes;
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos)) {
        dos.writeLong(l);
        bytes = baos.toByteArray();
    } catch (IOException e) {
        throw new UncheckedIOException(e);
    }
    return encoder.decodeLong(bytes);
}
