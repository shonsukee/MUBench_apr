    public static Cid cid(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        try {
            long parsed = Long.parseLong(value.trim(), 10);
            return new Cid(parsed);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Cid value: " + value, e);
        }
    }
