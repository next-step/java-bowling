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
            return String.format("%-4s", " ");
        }
        if (score.get().scoreType == ScoreType.STRIKE) {
            return String.format("%-4s", "X");
        }
        if (score.get().scoreType == ScoreType.SECOND) {
            return String.format("%-4s", score.get().hit.firstStr());
        }
        if (score.get().scoreType == ScoreType.GUTTER) {
            return String.format("%-4s", "-|-");
        }
        if (score.get().scoreType == ScoreType.MISS) {
            return String.format("%-4s", score.get().hit.firstStr() + "|" + score.get().hit.secondStr());
        }
        if (score.get().scoreType == ScoreType.SPARE) {
            return String.format("%-4s", score.get().hit.first() + "|/");
        }
        throw new RuntimeException("unreachable " + score.get());
    }



    public boolean done() {
        return this.scoreType != ScoreType.SECOND;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreType=" + scoreType +
                ", hit=" + hit +
                '}';
    }
}
