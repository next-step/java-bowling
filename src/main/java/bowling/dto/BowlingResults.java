package bowling.dto;

import bowling.domain.BowlingGames;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingResults {

    private List<BowlingResult> bowlingResults;

    public BowlingResults(List<BowlingResult> bowlingResults) {
        this.bowlingResults = bowlingResults;
    }

    public List<BowlingResult> getBowlingResults() {
        return bowlingResults;
    }

    public static BowlingResults from(BowlingGames bowlingGames) {
        return bowlingGames.values()
                .stream()
                .map(BowlingResult::from)
                .collect(Collectors.collectingAndThen(Collectors.toUnmodifiableList(), BowlingResults::new));
    }
}
