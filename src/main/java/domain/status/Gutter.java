package domain.status;

public class Gutter extends Status {
    private static final String GUTTER_STR = "-";
    private static Gutter gutter = null;

    private Gutter() {
    }

    public static Status getInstance() {
        if (gutter == null) {
            gutter = new Gutter();
        }
        return gutter;
    }

    @Override
    public String toString() {
        return GUTTER_STR;
    }
}
