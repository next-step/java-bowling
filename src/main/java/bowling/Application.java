package bowling;

import bowling.controller.BowlingController;
import bowling.domain.Frames;
import bowling.domain.Header;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;

public class Application {

    private static final int INIT_INDEX = 0;
    private static final int MAX_SIZE = 10;

    public static void main(String[] args) {
        BowlingController bowlingController = new BowlingController();

        String inputName = InputView.inputName();
        Header.register(inputName);
        ResultView.printFrames(new ArrayList<>());

        int score = InputView.inputFrameShot(INIT_INDEX);
        Frames frames = bowlingController.start(score);
        int position = frames.getLastFramePosition();
        ResultView.printFrames(frames.getFrames());

        while (position < MAX_SIZE) {
            score = InputView.inputFrameShot(frames.getLastFramePosition());
            frames = bowlingController.execute(frames, score);
            position = frames.getLastFramePosition();
            ResultView.printFrames(frames.getFrames());
        }
    }
}
