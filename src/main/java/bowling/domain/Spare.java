package bowling.domain;

import java.util.List;

public class Spare {

    private static final String SYMBOL = "/";
    private static final int MAXIMUN_SCORE = 10;

    private final List<Pitch> pitches;

    private Spare(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static Spare from(List<Pitch> pitches) {
        return new Spare(pitches);
    }

    public boolean isSpare() {
        int frameScore = pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();
        return frameScore == MAXIMUN_SCORE;
    }

    @Override
    public String toString() {
        return SYMBOL;
    }
}
