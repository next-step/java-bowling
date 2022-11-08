package bowling;

import bowling.domain.User;

public class BowlingController {

    public void play() {
        InputView inputView = new InputView();
        String name = inputView.inputName();
        User user = new User(name);
    }
}
