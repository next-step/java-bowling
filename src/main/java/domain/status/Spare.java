package domain.status;

public class Spare extends Status {
    private static final String SPARE_STR = "/";
    private static Spare spare = null;

    private Spare() {
    }

    public static Status getInstance() {
        if (spare == null) {
            spare = new Spare();
        }
        return spare;
    }

    @Override
    public String toString() {
        return SPARE_STR;
    }
}
