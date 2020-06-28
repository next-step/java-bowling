package bowling.domain.game;

import bowling.domain.frame.Frames;

import java.util.List;
import java.util.stream.Collectors;

public class MultiBowlingGame {

    private final List<SingleBowlingGame> singleBowlingGames;

    private MultiBowlingGame(List<SingleBowlingGame> singleBowlingGames) {
        this.singleBowlingGames = singleBowlingGames;
    }

    public static MultiBowlingGame of(List<String> playerNames) {
        List<SingleBowlingGame> singleBowlingGames = playerNames.stream()
                .map(playerName -> SingleBowlingGame.of(playerName, Frames.initiate()))
                .collect(Collectors.toList());
        return new MultiBowlingGame(singleBowlingGames);
    }

    public int getPlayerCounts() {
        return singleBowlingGames.size();
    }
}
