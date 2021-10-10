package step4;

import java.util.HashMap;
import java.util.Map;
import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.NormalFrame;
import step4.domain.PlayersFrames;
import step4.view.InputView;

public class BowlingGameApp {

    public static void main(String[] args) {
        int numOfPeople = InputView.numOfPeople();
        PlayersFrames playersFrames = new PlayersFrames();

        for (int i = 0; i < numOfPeople; i++) {
            String nameOfPerson = InputView.nameOfPerson();
            Frame frame = new NormalFrame(1);
            Frames frames = new Frames();
            frames.add(frame);
            playersFrames.put(nameOfPerson, frames);
        }

        BowlingGameController bowlingGameController = new BowlingGameController(playersFrames);
        bowlingGameController.run();
    }
}
