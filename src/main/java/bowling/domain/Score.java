package bowling.domain;

import java.util.Optional;

public class Score {
    private final ScoreType scoreType;
    private final Hit hit;

    public Score(int first) {
        this.hit = new Hit(first);
        this.scoreType = ScoreType.of(first);
    }

    public Score(int first, int second) {
        this.hit = new Hit(first, second);
        this.scoreType = ScoreType.of(first, second);
    }

    public static Score play(Optional<Score> prevScore) {
        if (prevScore.isPresent()) {
            int hit = Player.pitch(prevScore.get().hit.remainingPin());
            return new Score(prevScore.get().hit.first(), hit);
        }
        int hit = Player.pitch(10);
        return new Score(hit);
    }


    public static String payload(Optional<Score> score) {
        if (score.isEmpty()) {
            return format(" ");
        }
        if (score.get().scoreType == ScoreType.STRIKE) {
            return format("X");
        }
        if (score.get().scoreType == ScoreType.SECOND) {
            return format(score.get().hit.firstStr());
        }
        if (score.get().scoreType == ScoreType.GUTTER) {
            return format("-|-");
        }
        if (score.get().scoreType == ScoreType.MISS) {
            return format(score.get().hit.firstStr() + "|" + score.get().hit.secondStr());
        }
        if (score.get().scoreType == ScoreType.SPARE) {
            return format(score.get().hit.first() + "|/");
        }
        throw new RuntimeException("unreachable " + score.get());
    }

    public static String format(String string) {
        return String.format("%-4s", string);
    }

    public boolean done() {
        return this.scoreType != ScoreType.SECOND;
    }
}
