package bowling.controller.dto;

import bowling.domain.pitch.Pitch;

import java.util.List;
import java.util.Optional;

public class FrameStatus {
    public static final FrameStatus EMPTY = new FrameStatus(null, null);

    private List<Pitch> pitches;
    private Integer score;

    public FrameStatus(List<Pitch> pitches, Integer score) {
        this.pitches = pitches;
        this.score = score;
    }

    public List<Pitch> getPitches() {
        return pitches;
    }

    public Optional<Integer> getScore() {
        return Optional.ofNullable(score);
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
