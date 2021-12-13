package bowling.domain.bowl;

public class BowlFactory {

    private BowlFactory() {
    }

    public static Bowl firstBall() {
        return new FirstBowl();
    }

    public static boolean isStrikeOrSpare(Bowl bowl) {
        return isStrike(bowl) || isSpare(bowl);
    }

    private static boolean isStrike(Bowl bowl) {
        return StrikeBowl.class.isAssignableFrom(bowl.getClass());
    }

    private static boolean isSpare(Bowl bowl) {
        return SpareBowl.class.isAssignableFrom(bowl.getClass());
    }
}
