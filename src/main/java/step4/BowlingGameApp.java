package step4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.NormalFrame;
import step4.view.InputView;

public class BowlingGameApp {

    public static void main(String[] args) {
        Map<String, Frames> playersFrame = new HashMap<>();

        for (int i = 0; i < InputView.numOfPeople(); i++) {
            String nameOfPerson = InputView.nameOfPerson();
            Frame frame = new NormalFrame(1);
            Frames frames = new Frames();
            frames.add(frame);
            playersFrame.put(nameOfPerson, frames);
        }

        BowlingGameController bowlingGameController = new BowlingGameController(playersFrame);
        bowlingGameController.run();
    }
}
