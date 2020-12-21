package bowling.domain;

import bowling.dto.BowlingGameDto;
import bowling.dto.BowlingGamesDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BowlingGames {
    private final List<BowlingGame> value;
    private int currentFrameNo = 1;

    private BowlingGames(PlayerNames playerNames) {
        value = new ArrayList<>();
        for (PlayerName playerName : playerNames) {
            BowlingGame bowlingGame = BowlingGame.init(playerName);
            value.add(bowlingGame);
        }
    }

    public static BowlingGames init(PlayerNames playerNames) {
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

    public BowlingGamesDto convertToDto() {
        List<BowlingGameDto> bowlingGameDtos = value.stream()
                .map(BowlingGame::convertToDto)
                .collect(Collectors.toList());
        return BowlingGamesDto.of(bowlingGameDtos);
    }

    @Override
    public String toString() {
        return "BowlingGames{" +
                "value=" + value +
                '}';
    }
}
