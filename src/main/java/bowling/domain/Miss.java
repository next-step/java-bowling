package bowling.domain;

import java.util.List;

public class Miss {

    private static final int MAXINUM_SCORE = 10;

    private final List<Pitch> pitches;


    private Miss(List<Pitch> pitches) {
        this.pitches = pitches;
    }

    public static Miss from(List<Pitch> pitches) {
        return new Miss(pitches);
    }

    public boolean isMiss() {
        int frameScore = pitches.stream()
                .mapToInt(Pitch::getScore)
                .sum();

        return frameScore < MAXINUM_SCORE;
    }

    @Override
    public String toString() {
        return "" + pitches.get(1).getScore();
    }
}
