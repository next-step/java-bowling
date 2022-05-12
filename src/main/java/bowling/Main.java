package bowling;

import bowling.domain.frame.Frames;
import bowling.domain.game.Bowling;
import bowling.ui.InputView;
import bowling.ui.OutputView;

import java.util.List;

import static bowling.domain.frame.NormalFrameNo.MAX_NORMAL_FRAME_NO;
import static bowling.domain.frame.NormalFrameNo.MIN_NORMAL_FRAME_NO;

public class Main {

    public static void main(String[] args) {
        Bowling bowling = new Bowling(InputView.promptPlayer(), new Frames());
        addNormalFrames(bowling);
        addFinalFrame(bowling);
    }

    private static void addNormalFrames(Bowling bowling) {
        for (int frameNo = MIN_NORMAL_FRAME_NO; frameNo <= MAX_NORMAL_FRAME_NO; frameNo++) {
            List<Integer> normalPinNumbers = InputView.promptNormalPins(frameNo);
            bowling.addNormalFrame(normalPinNumbers.get(0), normalPinNumbers.get(1));
            OutputView.printBowling(bowling);
        }
    }

    private static void addFinalFrame(Bowling bowling) {
        List<Integer> finalPinNumbers = InputView.promptFinalPin();
        bowling.addFinalFrame(finalPinNumbers.get(0), finalPinNumbers.get(1));
        if (finalPinNumbers.size() == 3) {
            bowling.addExtra(finalPinNumbers.get(2));
        }
        OutputView.printBowling(bowling);
    }
}
