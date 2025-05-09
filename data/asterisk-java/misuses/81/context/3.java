import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class AsyncAgiEvent extends ResponseEvent
{
    private List<String> decode(String s)
    {
        final List<String> result = new ArrayList<>();

        if (s == null)
        {
            return result;
        }

        String decodedString = URLDecoder.decode(s, StandardCharsets.UTF_8);
        result.addAll(Arrays.asList(decodedString.split("\n")));

        return result;
    }
}
