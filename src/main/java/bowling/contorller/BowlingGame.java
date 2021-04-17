package bowling.contorller;

import bowling.domain.BowlingBoard;
import bowling.domain.Player;
import bowling.domain.Point;
import bowling.domain.ThrowsState;
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
        OutputView.printResultView(PlayerDto.of(player));
        BowlingBoard nextBowling = bowlingBoard;
        while (!nextBowling.isEnd()) {
            int point = InputView.inputPoint(nextBowling.round());
            nextBowling = nextBowling.pitching(Point.of(point));
            int round = round(nextBowling);
            OutputView.printResultView(round, PlayerDto.of(player), BowlingBoardDto.of(nextBowling));
        }
    }

    private int round(BowlingBoard nextBowling) {
        if (nextBowling.state() == ThrowsState.FIRST_THROWS && nextBowling.round() != 1) {
            return nextBowling.round() - 1;
        }
        return nextBowling.round();
    }

    public static void main(String[] args) {
        String playerName = InputView.inputPlayer();
        BowlingGame gameController = new BowlingGame(playerName);
        gameController.play();
    }
}
