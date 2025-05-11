    public static Cid cid(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        try {
            return new Cid(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Cid value: " + value, e);
        }
    }
