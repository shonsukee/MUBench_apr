static long encl(long l) {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos)) {
        dos.writeLong(l);
        dos.flush();
        return encoder.decodeLong(baos.toByteArray());
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
