package bowling;

import bowling.domain.Board;
import bowling.domain.frame.Pin;
import bowling.domain.game.BowlingGame;
import bowling.domain.user.User;
import bowling.service.BowlingService;
import bowling.view.InputView;
import bowling.view.OutputView;

public class main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BowlingService bowlingService = createBowlingService(inputView);
        while (!bowlingService.isGameEnd()) {
            int falldPins = inputView.inputPitch(bowlingService.round());
            Board board = bowlingService.bowl(Pin.from(falldPins));
            outputView.renderBoard(board);
        }
    }

    private static BowlingService createBowlingService(InputView inputView) {
        String name = inputView.inputName();
        User user = User.from(name);
        BowlingService bowlingService = new BowlingService(BowlingGame.readyGame(user));
        return bowlingService;
    }
}
