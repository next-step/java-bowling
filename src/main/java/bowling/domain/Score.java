package bowling.domain;

import java.util.Optional;

public class Score {
    private final ScoreType scoreType;
    private final int hit;

    public Score(int hit, Optional<Integer> prevHit) {
        this.scoreType = ScoreType.of(hit, prevHit);
        if (prevHit.isPresent()) {
            this.hit = prevHit.get();
            return;
        }
        this.hit = hit;
    }

    public static Score play(Optional<Score> prevScore) {
        if (prevScore.isPresent()) {
            int hit = Player.pitch(prevScore.get().remainingPin());
            return new Score(hit, Optional.of(prevScore.get().hit));
        }
        int hit = Player.pitch(10);
        return new Score(hit, Optional.empty());
    }

    int remainingPin() {
        return 10 - this.hit;
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
            return String.format("%-4s", "-");
        }
        if (score.get().scoreType == ScoreType.MISS) {
            return String.format("%-4s", score.get().hit + "|" + score.get().remainingPin());
        }
        if (score.get().scoreType == ScoreType.SPARE) {
            return String.format("%-4s", score.get().hit + "|/");
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
