import board.Name;
import frame.Frames;
import frame.LastFrame;
import view.InputView;

import java.util.ArrayList;

public class BowlingController {

    public static void main(String[] args) {

        Name name = new Name(InputView.inputName());
        Frames frames = new Frames(new ArrayList<>());

        while (!frames.reachLast()) {
            BowlingService.rollUntilLast(name, frames);
        }

        //last frame
        LastFrame lastFrame = LastFrame.init();
        frames.addLastFrame(lastFrame);

        while (!lastFrame.isFull()) {
            BowlingService.rollLast(name, frames);
        }

    }

}
