package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ScoreBoard {

    private final List<TotalScores> totalScoresOfPlayers;
    private final List<List<Integer>> calculatedScoresOfPlayers;

    private ScoreBoard(Players players, TotalFrames totalFrames) {
        totalScoresOfPlayers = new ArrayList<>();
        calculatedScoresOfPlayers = new ArrayList<>();
        IntStream.range(0, players.numberOfPlayers()).forEach(
                player -> {
                    calculatedScoresOfPlayers.add(new ArrayList<>());
                    totalScoresOfPlayers.add(
                            TotalScores.from(totalFrames.of(player), calculatedScoresOfPlayers.get(player)));
                }
        );
    }

    public static ScoreBoard from(Players players, TotalFrames totalFrames) {
        return new ScoreBoard(players, totalFrames);
    }

    public TotalScores totalScoreOf(int index) {
        return totalScoresOfPlayers.get(index);
    }

    public List<List<Integer>> calculatedScoresOfPlayers() {
        return calculatedScoresOfPlayers;
    }

    public void updateCalculatedScoresOf(int index, List<Integer> calculatedScores) {
        calculatedScoresOfPlayers.set(index, calculatedScores);
    }

    public List<Integer> calculatedScoresOf(int index) {
        return calculatedScoresOfPlayers.get(index);
    }
}
