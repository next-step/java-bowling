package step4;

import java.util.ArrayList;
import java.util.List;
import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.NormalFrame;
import step4.exception.NeedAdditionalFrameException;
import step4.view.InputView;
import step4.view.ResultView;

public class BowlingGameController {
    public static void run() {
        int numOfPeople = InputView.numOfPeople();
        List<String> players = new ArrayList<>();
        Frames frames = new Frames();
        Frame initInfoFrame = new NormalFrame(0);
        for (int i = 0; i < numOfPeople; i++) {
            players.add(InputView.nameOfPerson());
        }

        ResultView.printMainColumn();
        for (String nameOfPerson : players) {
            ResultView.printFirstColumn(nameOfPerson);
//            ResultView.printEmptyColumn(10);
            ResultView.printEmptyColumn(10 - initInfoFrame.round());
            ResultView.printFirstColumn("");
            int round = printTotalScore(initInfoFrame);
            ResultView.printEmptyColumn(10 - round);
        }



        Frame frame = new NormalFrame(1);
        frames.add(frame);

//        while (!frame.isGameEnd()) {
//            int falledPins = InputView.throwBowl(nameOfPerson);
//            frame.throwBowl(falledPins);
//            ResultView.printMainColumn();
//            ResultView.printFirstColumn(nameOfPerson);
//            printSymbol(frames);
//            ResultView.printEmptyColumn(10 - frame.round());
//            ResultView.printFirstColumn("");
//            int round = printTotalScore(frames);
//            ResultView.printEmptyColumn(10 - round);
//
//            if (frame.round() != 10 && frame.isFinish()) {
//                frame = frame.createFrame(frame.round() + 1);
//                frames.add(frame);
//            }
//        }

    }

    private static void printSymbol(Frame currentFrame) {
        while (currentFrame != null) {
            try {
                ResultView.printSymbol(currentFrame);
                currentFrame = currentFrame.next();
            } catch (NeedAdditionalFrameException e) {
                return;
            }
        }


    }

    private static int printTotalScore(Frame currentFrame) {
        int totalScore = 0;
        int round = 0;
        while (currentFrame != null) {
            try {
                totalScore += Integer.parseInt(currentFrame.getScore().getScore());
                ResultView.printResult(totalScore);
                currentFrame = currentFrame.next();
                round ++;
            } catch (NeedAdditionalFrameException e) {
                return round;
            }
        }

        return round  - 1;
    }
}
