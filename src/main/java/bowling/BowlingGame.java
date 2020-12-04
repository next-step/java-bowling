package bowling;

import bowling.domain.*;
import bowling.domain.frame.Frames;
import bowling.view.InputView;

public class BowlingGame {

    public static void main(String[] args) {
        String name = InputView.scanPlayer();
        Player player = Player.from(name);

        Frames frames = Frames.init(player);
        Bowling bowling = new Bowling(frames);

        bowling.start();
    }

}
