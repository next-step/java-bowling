package step4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<String, Frames> playersFrame = new HashMap<>();

        Frame initInfoFrame = new NormalFrame(0);
        for (int i = 0; i < numOfPeople; i++) {
            String nameOfPerson = InputView.nameOfPerson();
            players.add(nameOfPerson);
            Frame frame = new NormalFrame(1);
            Frames frames = new Frames();
            frames.add(frame);
            playersFrame.put(nameOfPerson, frames);
        }

        ResultView.printMainColumn();
        for (String nameOfPerson : players) {
            playBowlingByCycle(initInfoFrame, nameOfPerson);
        }

        for (int i = 1; i <= 10; i++) {
            for (String nameOfPerson : players) {
                Frames frames = playersFrame.get(nameOfPerson);
                Frame frame = frames.ofLast();
                while (!frame.isFinish()) {
                    int falledPins = InputView.throwBowl(nameOfPerson);
                    frame.throwBowl(falledPins);

                    ResultView.printMainColumn();
                    for (String player : players) {
                        playBowlingByCycle(playersFrame.get(player).ofFirst(), player);
                    }
                }

                if (frame.round() != 10 && frame.isFinish()) {
                    frame = frame.createFrame(frame.round() + 1);
                    frames.add(frame);
                }
            }
        }
    }

    private static void playBowlingByCycle(Frame currentFrame, String nameOfPerson) {
        ResultView.printFirstColumn(nameOfPerson);
        printSymbol(currentFrame);
        printTotalScore(currentFrame);
    }

    private static void printSymbol(Frame currentFrame) {
        int round = 0;
        while (currentFrame != null) {
            try {
                ResultView.printSymbol(currentFrame);
                round++;
                currentFrame = currentFrame.next();
            } catch (NeedAdditionalFrameException e) {
                ResultView.printEmptyColumn(10 - round);
                return;
            }
        }
        ResultView.printEmptyColumn(10 - round);
    }

    private static void printTotalScore(Frame currentFrame) {
        int totalScore = 0;
        int round = 0;
        ResultView.printFirstColumn("");
        while (currentFrame != null) {
            try {
                totalScore += Integer.parseInt(currentFrame.getScore().getScore());
                ResultView.printResult(totalScore);
                round ++;
                currentFrame = currentFrame.next();
            } catch (NeedAdditionalFrameException e) {
                ResultView.printEmptyColumn(10 - round);
                return;
            }
        }
        ResultView.printEmptyColumn(10 - round);
    }
}
