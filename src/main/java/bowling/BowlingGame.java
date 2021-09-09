package bowling;

import bowling.domain.Person;
import bowling.domain.ScoreBoard;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGame {

    public static void main(String[] args) {
        String name = InputView.userName();
        Person person = Person.from(name);

        ScoreBoard sb = ScoreBoard.create();

        NormalFrame firstFrame = NormalFrame.create();
        sb.write(person, firstFrame);
        Frames frames = sb.framesOfPerson(person);

        for (Frame frame : frames) {
            ResultView.showFrameInfo(frame.frameInfo());
            int downPinsCount = InputView.downPinsCount();
            ResultView.showHead();
            Frame roll = frame.roll(downPinsCount);
            ResultView.showPersonNameOnBoard(person);
            ResultView.showScoreBoard(frames);

            frames.add(roll);

            if (frames.isLast()) {
                break;
            }
        }
    }


}
