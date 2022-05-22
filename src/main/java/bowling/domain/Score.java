package bowling.domain;
import java.util.Optional;

public class Score {
    private final ScoreType scoreType;
    private final int hit;

    public Score(int hit, Optional<Integer> prevHit) {
        this.hit = hit;
        this.scoreType = ScoreType.of(hit, prevHit);
    }

    public static Score play(Optional<Score> prevScore) {
        int hit = Player.pitch();
        if (prevScore.isPresent()) {
            return new Score(hit, Optional.of(prevScore.get().hit));
        }
        return new Score(hit, Optional.empty());
    }

    public static String payload(Optional<Score> score) {
        if (score.isEmpty()) {
            return String.format("%-4s", " ");
        }
        if (score.get().scoreType == ScoreType.STRIKE) {
            return String.format("%-4s", "X");
        }
        if (score.get().scoreType == ScoreType.SECOND) {
            return String.format("%-4s", score.get().hit);
        }
        if (score.get().scoreType == ScoreType.GUTTER) {
            return String.format("%-4s", score.get().hit);
        }
        if (score.get().scoreType == ScoreType.MISS) {
            return String.format("%-4s", score.get().hit);
        }
        if (score.get().scoreType == ScoreType.SPARE) {
            return String.format("%-4s", score.get().hit);
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
