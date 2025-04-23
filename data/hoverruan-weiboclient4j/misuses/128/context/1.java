public class CoreParameters {
    public static Cid cid(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("値がnullまたは空です．");
        }
        try {
            return new Cid(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CIDの形式が正しくありません：" + value, e);
        }
    }
}
