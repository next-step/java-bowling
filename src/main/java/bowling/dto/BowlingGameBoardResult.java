package bowling.dto;

import bowling.game.BowlingGameBoard;
import bowling.game.BowlingGames;

import java.util.List;
import java.util.stream.Collectors;

public class BowlingGameBoardResult {

    private final List<BowlingGameResult> result;

    private BowlingGameBoardResult(final List<BowlingGameResult> result) {
        this.result = result;
    }

    public static BowlingGameBoardResult newInstance(final BowlingGameBoard bowlingGameBoard) {
        BowlingGames bowlingGames = bowlingGameBoard.getBowlingGames();
        List<BowlingGameResult> games = bowlingGames.getAllGames()
                .stream()
                .map(BowlingGameResult::newInstance)
                .collect(Collectors.toList());

        return new BowlingGameBoardResult(games);
    }

    public List<BowlingGameResult> getResult() {
        return result;
    }
}
