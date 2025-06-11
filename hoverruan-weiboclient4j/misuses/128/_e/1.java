    public static Cid cid(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Cid value must not be null or blank");
        }
        try {
            return new Cid(Long.parseLong(value, 10));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Cid value: " + value, e);
        }
    }
