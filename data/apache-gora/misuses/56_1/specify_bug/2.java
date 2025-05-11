import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;

static long encl(long l) {
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos)) {
        dos.writeLong(l);
        dos.flush();
        return encoder.decodeLong(baos.toByteArray());
    } catch (IOException e) {
        throw new UncheckedIOException(e);
    }
}
