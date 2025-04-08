private List<String> decode(String s)
{
    final List<String> result = new ArrayList<>();
    if (s == null)
    {
        return result;
    }
    try
    {
        final String decodedString = URLDecoder.decode(s, StandardCharsets.ISO_8859_1.name());
        result.addAll(Arrays.asList(decodedString.split("\n")));
    }
    catch (UnsupportedEncodingException e)
    {
        throw new RuntimeException("This JDK does not support ISO-8859-1 encoding", e);
    }
    return result;
}
