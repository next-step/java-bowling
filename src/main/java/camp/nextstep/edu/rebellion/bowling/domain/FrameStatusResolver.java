package camp.nextstep.edu.rebellion.bowling.domain;

public class FrameStatusResolver {
    private static final int SPARE = 10;
    private static final int GUTTER = 0;

    private FrameStatusResolver() {};

    public static FrameStatus resolve(Frame frame) {
        if (!frame.isStarted()) {
            return new NotStarted();
        }

        if (frame.isStrike()) {
            return new Strike();
        }

        int firstScore = frame.getFirstScore();
        int lastScore = frame.getLastScore();
        int frameScore = firstScore + lastScore;

        if (SPARE == frameScore) {
            return new Spare(firstScore, lastScore);
        }

        if (GUTTER == frameScore) {
            return new Gutter();
        }

        return new Miss(firstScore, lastScore);
    }
}
