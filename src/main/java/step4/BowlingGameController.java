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
        for (int i = 1; i <= 9; i++) {
            while (!frame.isFinish()) {
                int falledPins = InputView.throwBowl(nameOfPerson);
                frame.throwBowl(falledPins);
                ResultView.printColumn();
                ResultView.printFirstColumn(nameOfPerson);

                printSymbol(firstFrame);
                printTotalScore(firstFrame);
                ResultView.printEmptyColumn(10 - i);
            }
            frame = frame.createFrame(i + 1);
            frames.add(frame);
        }
    }

    private static void printSymbol(Frame firstFrame) {
        Frame targetFrame = firstFrame;
        while (targetFrame != null) {
            ResultView.printSymbol(targetFrame);
            targetFrame = targetFrame.next();
        }
    }

    private static void printTotalScore(Frame firstFrame) {
        Frame targetFrame = firstFrame;
        while (targetFrame != null) {
            ResultView.printResult(targetFrame);
            targetFrame = targetFrame.next();
        }
    }
}
