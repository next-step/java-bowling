package bowling.contorller;

import bowling.domain.BowlingFrame;
import bowling.domain.Point;
import bowling.view.InputView;
import bowling.view.OutputView;

public class GameController {

    private final Player player;
    private final BowlingGame bowlingGame;


    public GameController(String playerName) {
        this.player = Player.of(playerName);
        this.bowlingGame = BowlingGame.of();
    }

    public BowlingFrame play() {
        int point = InputView.inputPoint(bowlingGame.round());
        BowlingFrame bowlingFrame = bowlingGame.firstPitching(Point.of(point));
        OutputView.firstResultView(BowlingBoardDto.of(player, bowlingFrame));
        boolean isStrike = bowlingFrame.isStrike();
        if (!isStrike) {
            return next();
        }
        return bowlingFrame;
    }

    public BowlingFrame next() {
        int point = InputView.inputPoint(bowlingGame.round());
        BowlingFrame bowlingFrame = bowlingGame.secondPitching(Point.of(point));
        OutputView.secondResultView(BowlingBoardDto.of(player, bowlingFrame));
        return bowlingFrame;
    }

    public static void main(String[] args) {
        String playerName = InputView.inputPlayer();
        GameController gameController = new GameController(playerName);
        boolean isEnd;
        do {
            BowlingFrame frame = gameController.play();
            isEnd = frame.isEnd();
        } while (!isEnd);
    }


}
