package bowling.dto;

import java.util.List;

public class StateDto {
    private List<Integer> scores;

    private StateDto(final List<Integer> scores) {
        this.scores = scores;
    }

    public static StateDto from(final List<Integer> scores) {
        return new StateDto(scores);
    }

    public List<Integer> getScores() {
        return scores;
    }
}
