package bowling.domain.bowl;

public class BowlFactory {

    private BowlFactory() {
    }

    public static Bowl firstBall() {
        return new FirstBowl();
    }

    public static boolean isStrikeOrSpare(Bowl bowl) {
        return StrikeBowl.class.isAssignableFrom(bowl.getClass())
                || SpareBowl.class.isAssignableFrom(bowl.getClass());
    }

}
