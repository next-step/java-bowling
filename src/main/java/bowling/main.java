package bowling;

import bowling.domain.Board;
import bowling.domain.frame.Pin;
import bowling.domain.game.BowlingGame;
import bowling.domain.user.User;
import bowling.service.BowlingService;
import bowling.view.InputView;

public class main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        BowlingService bowlingService = createBowlingService(inputView);
        while (!bowlingService.isGameEnd()) {
            int falldPins = inputView.inputPitch(bowlingService.round());
            Board bowl = bowlingService.bowl(Pin.from(falldPins));
            System.out.println(bowl);
        }
//        bowlingService.bowl(Pin.TEN);
    }

    private static BowlingService createBowlingService(InputView inputView) {
        String name = inputView.inputName();
        User user = User.from(name);
        BowlingService bowlingService = new BowlingService(BowlingGame.readyGame(user));
        return bowlingService;
    }
}
