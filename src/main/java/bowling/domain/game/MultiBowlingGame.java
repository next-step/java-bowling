package bowling.domain.game;

import bowling.domain.dto.BowlingGameDto;
import bowling.domain.exception.BowlingBuildingException;
import bowling.domain.frame.Frames;
import bowling.domain.score.PitchScore;

import java.util.List;
import java.util.stream.Collectors;

public class MultiBowlingGame {

    private final List<SingleBowlingGame> singleBowlingGames;

    private MultiBowlingGame(List<SingleBowlingGame> singleBowlingGames) {
        this.singleBowlingGames = singleBowlingGames;
    }

    public static MultiBowlingGame of(List<String> playerNames) {
        validatePlayerCounts(playerNames);
        List<SingleBowlingGame> singleBowlingGames = playerNames.stream()
                .map(playerName -> SingleBowlingGame.of(playerName, Frames.initiate()))
                .collect(Collectors.toList());
        return new MultiBowlingGame(singleBowlingGames);
    }

    private static void validatePlayerCounts(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new BowlingBuildingException(BowlingBuildingException.INVALID_PLAYER_COUNTS);
        }
    }

    private SingleBowlingGame getCurrentGame() {
        return singleBowlingGames.stream()
                .filter(singleBowlingGame -> !singleBowlingGame.isCurrentFrameFinished())
                .findFirst()
                .orElseThrow(() -> new BowlingBuildingException(BowlingBuildingException.CANNOT_FIND_GAME_TURN));
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

    public boolean isEnd() {
        return singleBowlingGames.stream()
                .noneMatch(SingleBowlingGame::hasNextTurn);
    }

    public List<BowlingGameDto> getBowlingGameDtos() {
        return singleBowlingGames.stream()
                .map(SingleBowlingGame::getBowlingGameDto)
                .collect(Collectors.toList());
    }

    public String getCurrentPlayerName() {
        return getCurrentGame().getPlayerName();
    }
}
