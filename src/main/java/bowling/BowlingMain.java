package bowling;

import bowling.view.BowlingInputView;
import bowling.view.BowlingOutputView;

public class BowlingMain {
    public static void main(String[] args) {
        String name = BowlingInputView.getPlayerNameWithPrompt("플레이어 이름은(3 english letters)?: ");

        BowlingOutputView.printFramesStatus(new Player(name), null);
    }
}
