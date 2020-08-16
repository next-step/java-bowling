package bowling.domian.frame;

public class Score {
    private Score() {

    }

    public static Score strike() {
        return new Score();
    }

    public static Score spare() {
        return new Score();
    }

    public static Score miss(int falledPinsCount) {
        return new Score();
    }

    public boolean isCalculateDone() {
        return false;
    }

    public int getScore() {
        return -1;
    }

    public void additionalBowl(int falledPinsCount) {
    }
}
