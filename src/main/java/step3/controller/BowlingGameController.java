package step3.controller;

import step3.NormalFrame;
import step3.domain.Frames;
import step3.view.InputView;

public class BowlingGameController {
    public void runGame() {
        Frames frames =  new Frames();
        int frameNum = 1;
        NormalFrame frame = new NormalFrame(frameNum);
        while (!frame.isGameEnd()) {


            if (frame.isFinish()) {
                NormalFrame nextFrame = frame.bowl(InputView.requireThrowBallNum(frameNum));
            };
        }
    }
}
