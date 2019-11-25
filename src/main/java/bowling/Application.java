package bowling;

import bowling.controller.BowlingController;
import bowling.domain.Frames;
import bowling.view.InputView;
import bowling.view.ResultView;
import bowling.view.Template;

public class Application {

    public static void main(String[] args) {
        BowlingController bowlingController = new BowlingController();

        String inputName = InputView.inputName();
        Template.register(inputName);
        ResultView.printTemplate();

        int score = InputView.inputFrameShot(0);
        Frames frames = bowlingController.start(score);


        while (frames.size() < 10) {
            score = InputView.inputFrameShot(frames.getLastFramePosition());
            frames = bowlingController.execute(frames, score);
        }
    }


}
