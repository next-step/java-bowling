package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingGames {
    private final List<BowlingGame> value;
    private int currentFrameNo = 1;

    private BowlingGames(List<PlayerName> playerNames) {
        value = new ArrayList<>();
        for (PlayerName playerName : playerNames) {
            BowlingGame bowlingGame = BowlingGame.init(playerName);
            value.add(bowlingGame);
        }
    }

    public static BowlingGames init(List<PlayerName> playerNames) {
        return new BowlingGames(playerNames);
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        BowlingGame bowlingGame = getCurrentBowlingGame();
        bowlingGame.setKnockDownPins(knockDownPins);
        if (value.stream()
                .allMatch(bg -> bg.getCurrentFrameNo() != currentFrameNo)) {
            currentFrameNo++;
        }
    }

    private BowlingGame getCurrentBowlingGame() {
        return value.stream()
                .filter(bowlingGame -> !bowlingGame.isEnd() && bowlingGame.getCurrentFrameNo() == currentFrameNo)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("만족하는 볼링게임이 없음"));
    }

    public PlayerName getCurrentPlayer() {
        return getCurrentBowlingGame().getPlayerName();
    }

    public boolean isEnd() {
        return value.stream()
                .allMatch(BowlingGame::isEnd);
    }

    public List<BowlingGame> getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BowlingGames{" +
                "value=" + value +
                '}';
    }
}
