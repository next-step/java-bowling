package bowling.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingGames {

    private final List<BowlingGame> bowlingGames;
    private int playFrameNo;
    private int gameIndex;

    private BowlingGames(List<BowlingGame> bowlingGames, int playFrameNo, int gameIndex) {
        this.bowlingGames = bowlingGames;
        this.playFrameNo = playFrameNo;
        this.gameIndex = gameIndex;
    }

    public static BowlingGames of(List<String> userNames) {
        List<BowlingGame> bowlingGames = userNames.stream()
                                                  .map(User::valueOf)
                                                  .map(BowlingGame::of)
                                                  .collect(Collectors.toList());

        return new BowlingGames(bowlingGames, 1, 0);
    }

    public void bowling(int countOfPins) {
        BowlingGame bowlingGame = bowlingGames.get(gameIndex);
        bowlingGame.bowling(countOfPins);

        if (isEndFrames(playFrameNo)) {
            changePlayFrameNo();
            return;
        }

        moveGameIndex();
        processMoveGameIndex();
    }

    public String getTurnToUser() {
        BowlingGame bowlingGame = bowlingGames.get(gameIndex);
        return bowlingGame.getUserName();
    }

    private void changePlayFrameNo() {
        playFrameNo += 1;
        gameIndex = 0;
    }

    private void processMoveGameIndex() {
        while(bowlingGames.get(gameIndex).isEndOf(playFrameNo)) {
            moveGameIndex();
        }
    }

    private void moveGameIndex() {
        gameIndex = gameIndex >= bowlingGames.size() - 1 ? 0 : gameIndex + 1;
    }

    private boolean isEndFrames(int playFrameNo) {
        return bowlingGames.stream()
                            .allMatch(bowlingGame -> bowlingGame.isEndOf(playFrameNo));
    }

    public boolean isEnd() {
        return bowlingGames.stream()
                            .allMatch(BowlingGame::isEnd);
    }

    public Stream<BowlingGame> getBowlingGames() {
        return bowlingGames.stream();
    }

}
