package bowling.domain.bowl.type;

import bowling.domain.score.Scores;
import java.util.Objects;
import java.util.stream.Collectors;

public class BowlTypeDto {
    private final Scores scores;

    public BowlTypeDto(Scores scores) {
        this.scores = scores;
    }

    public Scores getScores() {
        return scores;
    }

    @Override
    public String toString(){
        return scores.getScores().stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));
    }
}
