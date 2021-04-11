package bowling.contorller;

import bowling.domain.BowlingFrame;
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
        BowlingFrame bowlingFrame = bowlingGame.firstPitching(point);
        OutputView.firstResultView(BowlingBoardDto.of(player, bowlingFrame));
        boolean isStrike = bowlingFrame.isStrike();
        if (!isStrike) {
            return next();
        }
        return bowlingFrame;
    }

    public BowlingFrame next() {
        int point = InputView.inputPoint(bowlingGame.round());
        BowlingFrame bowlingFrame = bowlingGame.secondPitching(point);
        OutputView.secondResultView(BowlingBoardDto.of(player, bowlingFrame));
        return bowlingFrame;
    }

    public static void main(String[] args) {
        String playerName = InputView.inputPlayer();
        GameController gameController = new GameController(playerName);
        boolean isEnd = true;
        do {
            BowlingFrame play = gameController.play();
            isEnd = play.isEnd();
        } while (isEnd);
    }


}
