package domain;

import domain.frame.FrameIndex;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingMatch {

    private List<BowlingGame> bowlingMatch;

    private BowlingMatch(List<BowlingGame> bowlingMatch) {
        this.bowlingMatch = bowlingMatch;
    }

    public static BowlingMatch start(List<Player> players) {
        return new BowlingMatch(setUpMatch(players));
    }

    private static List<BowlingGame> setUpMatch(List<Player> players) {
        return players.stream()
                .map(BowlingGame::from)
                .collect(Collectors.toList());
    }

    public BowlingGame getOngoingGame() {
        int index = getOngoingGameIndex();
        return bowlingMatch.stream()
                .filter(game -> !game.isGameOver())
                .filter(game -> game.currentFrameIndex().isOngoingGameIndex(index))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    private int getOngoingGameIndex() {
        return bowlingMatch.stream()
                .map(BowlingGame::currentFrameIndex)
                .mapToInt(FrameIndex::getFrameIndex)
                .min()
                .orElseThrow(RuntimeException::new);
    }

    public boolean isOver() {
        return bowlingMatch.stream()
                .allMatch(BowlingGame::isGameOver);
    }
}
