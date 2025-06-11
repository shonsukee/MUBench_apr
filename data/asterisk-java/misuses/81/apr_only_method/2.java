import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.net.URLDecoder;

private List<String> decode(String s)
{
    final List<String> result = new ArrayList<>();
    if (s == null)
    {
        return result;
    }
    final String decodedString = URLDecoder.decode(s, StandardCharsets.ISO_8859_1);
    result.addAll(Arrays.asList(decodedString.split("\n")));
    return result;
}
