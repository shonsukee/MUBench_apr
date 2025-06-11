static long encl(long l) {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (DataOutputStream dos = new DataOutputStream(baos)) {
        dos.writeLong(l);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return encoder.decodeLong(baos.toByteArray());
}
