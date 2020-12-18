package bowling.dto;

import bowling.domain.pitchings.Pitchings2;

public class Frame2Dto {
    private final Pitchings2 pitchings;
    private final Integer totalScore;

    private Frame2Dto(Pitchings2 pitchings, Integer totalScore) {
        this.pitchings = pitchings;
        this.totalScore = totalScore;
    }

    public static Frame2Dto of(Pitchings2 pitchings, Integer totalScore) {
        return new Frame2Dto(pitchings, totalScore);
    }

    public Pitchings2 getPitchings() {
        return pitchings;
    }

    public Integer getTotalScore() {
        return totalScore;
    }
}
