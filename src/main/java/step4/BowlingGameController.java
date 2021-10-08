package step4;

import java.util.ArrayList;
import java.util.List;
import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.NormalFrame;
import step4.view.InputView;
import step4.view.ResultView;

public class BowlingGameController {
    public static void run() {
        int numOfPeople = InputView.numOfPeople();

        String nameOfPerson = InputView.nameOfPerson();


        ResultView.printColumn();
        ResultView.printFirstColumn(nameOfPerson);
        ResultView.printEmptyColumn(10);

        Frames frames = new Frames();
        Frame frame = new NormalFrame(1);
        frames.add(frame);
        Frame firstFrame = frames.ofFirst();

        while (!frame.isGameEnd()) {
            int falledPins = InputView.throwBowl(nameOfPerson);
            frame.throwBowl(falledPins);
            ResultView.printColumn();
            ResultView.printFirstColumn(nameOfPerson);

            printSymbol(firstFrame);
            ResultView.printEmptyColumn(10 - frame.round());
            ResultView.printFirstColumn("");
            int round = printTotalScore(firstFrame);
            ResultView.printEmptyColumn(10 - round);

            if (frame.round() != 10 && frame.isFinish()) {
                frame = frame.createFrame(frame.round());
                frames.add(frame);
            }
        }



    }

    private static void printSymbol(Frame firstFrame) {
        Frame nextFrame = firstFrame;
        while (nextFrame != null) {
            ResultView.printSymbol(nextFrame);
            nextFrame = nextFrame.next();
        }
    }

    private static int printTotalScore(Frame firstFrame) {
        Frame currentFrame = firstFrame;
        Frame nextFrame = firstFrame;
        int totalScore = 0;
        int round = 0;
        while (nextFrame != null && !nextFrame.getScore().equals("")) {
            round ++;
            currentFrame = nextFrame;
            totalScore += Integer.parseInt(currentFrame.getScore());
            ResultView.printResult(totalScore);
            nextFrame = currentFrame.next();
        }
        return round;

    }
}
