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
        Person person = InputView.user();

        ScoreBoard scoreBoard = ScoreBoard.create();

        NormalFrame firstFrame = NormalFrame.create();
        scoreBoard.write(person, firstFrame);
        Frames frames = scoreBoard.framesOfPerson(person);

        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            ResultView.showCurrentFrameInfo(frame.frameInfo());
            int downPinsCount = InputView.downPinsCount();

            ResultView.showHead();

            frame = frame.roll(downPinsCount);

            ResultView.showPersonNameOnBoard(person);
            ResultView.showScoreBoard(frames);

            frames.add(frame);
            if (frames.isGameEnd()) {
                break;
            }
        }
    }


}
