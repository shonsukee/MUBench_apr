public static Cid cid(String value) {
    if (value == null || value.trim().isEmpty()) {
        throw new IllegalArgumentException("value must not be null or empty");
    }
    try {
        return new Cid(value);
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid Cid value: " + value, e);
    }
}
