package bowling;

import static bowling.view.InputView.inputUsername;
import static bowling.view.ResultView.printResult;

import bowling.domain.frame.Frames;
import bowling.domain.user.User;

public class Main {

    public static void main(String[] args) {
        User user = User.of(inputUsername());
        Frames frames = Frames.empty();
        printResult(user, frames);
    }

}
