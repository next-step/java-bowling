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
                    totalScoresOfPlayers.add(TotalScores.from(totalFrames.of(player), calculatedScoresOfPlayers.get(player)));
                }
        );
    }

    public static ScoreBoard from(Players players, TotalFrames totalFrames) {
        return new ScoreBoard(players, totalFrames);
    }

    public TotalScores totalScoreOf(int playerIndex) {
        return totalScoresOfPlayers.get(playerIndex);
    }

    public List<List<Integer>> calculatedScoresOfPlayers() {
        return calculatedScoresOfPlayers;
    }

    public void updateCalculatedScoresOf(int playerIndex, List<Integer> calculatedScores) {
        calculatedScoresOfPlayers.set(playerIndex, calculatedScores);
    }

    public List<Integer> calculatedScoresOf(int playerIndex) {
        return calculatedScoresOfPlayers.get(playerIndex);
    }
}
