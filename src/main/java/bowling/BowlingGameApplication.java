package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.User;
import bowling.view.Input;
import bowling.view.Output;

public class BowlingGameApplication {
    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output(new BowlingGame(new User(input.userName())).start());
        output.show();
    }
}
