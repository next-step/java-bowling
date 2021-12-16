package bowling;

import bowling.domain.frame.Pin;
import bowling.domain.game.BowlingGame;
import bowling.domain.user.User;
import bowling.service.BowlingService;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Bowling {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BowlingService bowlingService = createBowlingService(inputView);
        while (!bowlingService.isGameEnd()) {
            int falldPins = inputView.inputPitch(bowlingService.round());
            outputView.renderBoard(bowlingService.bowl(Pin.from(falldPins)));
        }
    }

    private static BowlingService createBowlingService(InputView inputView) {
        String name = inputView.inputName();
        User user = User.from(name);
        return new BowlingService(BowlingGame.readyGame(user));
    }
}
