package bowling.domain.bowl;

public class BowlFactory {

    private BowlFactory() {
    }

    public static Bowl firstBall() {
        return new FirstBowl();
    }
}
