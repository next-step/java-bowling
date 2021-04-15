package bowling.contorller;

import bowling.domain.BowlingBoard;
import bowling.domain.Player;
import bowling.domain.Point;
import bowling.dto.BowlingBoardDto;
import bowling.dto.PlayerDto;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {

    private final Player player;
    private final BowlingBoard bowlingBoard;

    public BowlingGame(String playerName) {
        this.player = Player.of(playerName);
        this.bowlingBoard = BowlingBoard.of();
    }

    public void play() {
        int point = InputView.inputPoint(bowlingBoard.round());
        OutputView.printResultView(PlayerDto.of(player));
        BowlingBoard nextBowling;
        boolean isEnd = false;
        do {
            nextBowling = bowlingBoard.pitching(Point.of(point));
            OutputView.printResultView(PlayerDto.of(player), BowlingBoardDto.of(nextBowling));
            isEnd = bowlingBoard.isEnd();
        } while (!isEnd);
    }

    public static void main(String[] args) {
        String playerName = InputView.inputPlayer();
        BowlingGame gameController = new BowlingGame(playerName);
        gameController.play();
    }
}
