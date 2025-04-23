public class CoreParameters {
    public static Cid cid(String value) {
        try {
            return new Cid(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("無効な値です，" + value, e);
        }
    }
}
