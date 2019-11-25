package bowling;

import bowling.controller.BowlingController;
import bowling.domain.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;
import bowling.view.Template;

public class Application {

    private static final int INIT_INDEX = 0;
    private static final int MAX_SIZE = 10;

    public static void main(String[] args) {
        BowlingController bowlingController = new BowlingController();

        String inputName = InputView.inputName();
        Template.register(inputName);
        ResultView.printTemplate();

        int score = InputView.inputFrameShot(INIT_INDEX);
        Frames frames = bowlingController.start(score);

        while (frames.size() < MAX_SIZE) {
            score = InputView.inputFrameShot(frames.getLastFramePosition());
            frames = bowlingController.execute(frames, score);
        }
    }
}
