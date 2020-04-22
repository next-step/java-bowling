package bowling.domain.score;

public class EmptyScore implements Score {
    private static final EmptyScore cache = new EmptyScore();

    @Override public boolean isCompleted() {
        return false;
    }

    @Override public int getScore() {
        return 0;
    }

    @Override public Score add(Score score) {
        return this;
    }

    public static EmptyScore valueOf() {
        return cache;
    }
}
