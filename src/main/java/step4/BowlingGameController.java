package step4;

import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.NormalFrame;
import step4.exception.NeedAdditionalFrameException;
import step4.view.InputView;
import step4.view.ResultView;

public class BowlingGameController {
    public static void run() {
        int numOfPeople = InputView.numOfPeople();
        String nameOfPerson = InputView.nameOfPerson();

        ResultView.printMainColumn();
        ResultView.printFirstColumn(nameOfPerson);
        ResultView.printEmptyColumn(10);

        Frames frames = new Frames();
        Frame frame = new NormalFrame(1);
        frames.add(frame);

        while (!frame.isGameEnd()) {
            int falledPins = InputView.throwBowl(nameOfPerson);
            frame.throwBowl(falledPins);
            ResultView.printMainColumn();
            ResultView.printFirstColumn(nameOfPerson);

            printSymbol(frames);
            ResultView.printEmptyColumn(10 - frame.round());
            ResultView.printFirstColumn("");
            int round = printTotalScore(frames);
            ResultView.printEmptyColumn(10 - round);

            if (frame.round() != 10 && frame.isFinish()) {
                frame = frame.createFrame(frame.round() + 1);
                frames.add(frame);
            }
        }

    }

    private static void printSymbol(Frames frames) {
        Frame nextFrame = frames.ofFirst();
        while (nextFrame != null) {
            try {
                ResultView.printSymbol(nextFrame);
                nextFrame = nextFrame.next();
            } catch (NeedAdditionalFrameException e) {
                return;
            }
        }


    }

    private static int printTotalScore(Frames frames) {
        Frame testFrame = frames.ofFirst();
        int totalScore = 0;
        int round = 0;
        while (testFrame != null) {
            try {
                round ++;
                totalScore += Integer.parseInt(testFrame.getScore().getScore());
                ResultView.printResult(totalScore);
                testFrame = testFrame.next();
            } catch (NeedAdditionalFrameException e) {
                return round;
            }
        }

        return round  - 1;
    }
}
