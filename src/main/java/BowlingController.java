import board.Name;
import frame.Frames;
import frame.LastFrame;
import view.InputView;

import java.util.ArrayList;

import static view.OutputView.showBoard;

public class BowlingController {

    public static void main(String[] args) {

        Name name = new Name(InputView.inputName());
        Frames frames = new Frames(new ArrayList<>());

        //first rolling
        BowlingService.rollUntilLast(frames);
        showBoardUntilLast(name, frames);

        while (frames.isNotLast()) {
            BowlingService.rollUntilLast(frames);
            showBoardUntilLast(name, frames);
        }

        //last frame
        LastFrame lastFrame = LastFrame.init();
        frames.addLastFrame(lastFrame);

        while (lastFrame.isNotFull()) {
            BowlingService.rollLast(frames);
            showBoard(name, frames);
        }

    }

    private static void showBoardUntilLast(Name name, Frames frames) {
        if (frames.isNotLast()) {
            showBoard(name, frames);
        }
    }

}
