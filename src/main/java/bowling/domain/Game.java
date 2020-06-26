package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;

public class Game {

    public void startGame() {
        NormalFrame normalFrame = NormalFrame.firstFrame();
        for (int i = 0; i < 9; i++) {
            normalFrame.nextFrame();
        }
        FinalFrame finalFrame = new FinalFrame();
    }

}
