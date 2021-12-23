package bowling;

import bowling.controller.BowlingGame;
import bowling.domain.value.User;
import bowling.view.InputView;

public class Client {
    public static void main(String[] args) {

        String name = InputView.inputUserName();
        User user = new User(name);

        BowlingGame bowlingGame = new BowlingGame(user);
        bowlingGame.start();
    }
}
