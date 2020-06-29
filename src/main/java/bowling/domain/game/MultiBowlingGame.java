package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.score.PitchScore;

import java.util.List;
import java.util.stream.Collectors;

public class MultiBowlingGame {
    private static final int ZERO_INDEX = 0;

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

    private SingleBowlingGame getCurrentGame() {
        return singleBowlingGames.stream()
                .filter(singleBowlingGame -> !singleBowlingGame.isCurrentFrameFinished())
                .findFirst()
                .orElseGet(() -> singleBowlingGames.get(ZERO_INDEX));
    }

    public void bowl(PitchScore pitchScore) {
        getCurrentGame().bowl(pitchScore);
    }

    public void moveToNextFrame() {
        boolean isCurrentFrameFinished = singleBowlingGames.stream()
                .allMatch(SingleBowlingGame::isCurrentFrameFinished);
        if (isCurrentFrameFinished) {
            singleBowlingGames.forEach(SingleBowlingGame::moveToNextFrame);
        }
    }

    public boolean hasNextTurn() {
        return singleBowlingGames.stream()
                .anyMatch(SingleBowlingGame::hasNextTurn);
    }

    public String getCurrentPlayerName() {
        return getCurrentGame().getPlayerName();
    }

    public int getPlayerCounts() {
        return singleBowlingGames.size();
    } //삭제 예정
}
