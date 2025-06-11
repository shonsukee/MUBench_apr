import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AsyncAgiEvent extends ResponseEvent {
    private List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        String decodedString = URLDecoder.decode(s, StandardCharsets.UTF_8);
        result.addAll(decodedString.lines().collect(Collectors.toList()));
        return result;
    }
}
