import java.nio.ByteBuffer;

static long encl(long l) {
    byte[] bytes = ByteBuffer.allocate(Long.BYTES).putLong(l).array();
    return encoder.decodeLong(bytes);
}
