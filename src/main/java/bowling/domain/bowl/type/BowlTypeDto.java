package bowling.domain.bowl.type;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlTypeDto {
    private final List<Integer> scores;

    public BowlTypeDto(List<Integer> scores) {
        this.scores = scores;
    }

    public List<Integer> getScores() {
        return scores;
    }

    @Override
    public String toString(){
        return scores.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
    }
}
