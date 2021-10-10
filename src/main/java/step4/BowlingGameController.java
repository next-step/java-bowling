package step4;

import java.util.Map;
import step4.domain.Frame;
import step4.domain.Frames;
import step4.domain.NormalFrame;
import step4.exception.NeedAdditionalFrameException;
import step4.view.InputView;
import step4.view.ResultView;

public class BowlingGameController {
    private Map<String, Frames> playersFrame;

    public BowlingGameController(Map<String, Frames> playersFrame) {
        this.playersFrame = playersFrame;
    }

    public void run() {
        printZeroFrameResult();
        playBowlingByFrame();
    }

    private void printZeroFrameResult() {
        ResultView.printMainColumn();
        Frame initInfoFrame = new NormalFrame(0);
        for (String nameOfPerson : this.playersFrame.keySet()) {
            printEachFrameResult(initInfoFrame, nameOfPerson);
        }
    }

    private void playBowlingByFrame() {
        for (int i = 1; i <= 10; i++) {
            playBowlingByPlayer();
        }
    }

    private void playBowlingByPlayer() {
        for (String player : this.playersFrame.keySet()) {
            Frames frames = playersFrame.get(player);
            Frame frame = frames.ofLast();
            playBowlingUntilFinish(player, frame);
            createNewFrame(frames, frame);
        }
    }

    private void createNewFrame(Frames frames, Frame frame) {
        if (frame.round() != 10 && frame.isFinish()) {
            frame = frame.createFrame(frame.round() + 1);
            frames.add(frame);
        }
    }

    private void playBowlingUntilFinish(String nameOfPerson, Frame frame) {
        while (!frame.isFinish()) {
            int falledPins = InputView.throwBowl(nameOfPerson);
            frame.throwBowl(falledPins);
            printPlayerEachFrameResult(playersFrame);
        }
    }

    private static void printPlayerEachFrameResult(Map<String, Frames> playersFrame) {
        ResultView.printMainColumn();
        for (String player : playersFrame.keySet()) {
            printEachFrameResult(playersFrame.get(player).ofFirst(), player);
        }
    }

    private static void printEachFrameResult(Frame currentFrame, String nameOfPerson) {
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
                totalScore += currentFrame.getScore().getScore();
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
