package bowling;

import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {

    public static void main(String[] args) {
        User user = User.of(InputView.inputUsername());
        ResultView.printScoreBoard();
    }

}
