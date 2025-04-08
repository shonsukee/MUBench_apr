private List<String> decode(String s)
{
    final List<String> result = new ArrayList<String>();
    if (s == null)
    {
        return result;
    }
    try
    {
        final String decodedString = URLDecoder.decode(s, "ISO-8859-1");
        result.addAll(Arrays.asList(decodedString.split("\n")));
    }
    catch (UnsupportedEncodingException e)
    {
        throw new RuntimeException("This JDK does not support ISO-8859-1 encoding", e);
    }
    return result;
}
