package bowling;

import bowling.domain.FrameLinkedList;
import bowling.domain.Frames;
import bowling.domain.Frame;
import bowling.domain.Name;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingMain {
    private static final int NUMBERS_OF_NORMAL_FRAMES = 9;
    private static final int INITIAL_INDEX = 0;

    public static void main(String[] args) {
        Name name = new Name(InputView.inputPlayerName());

        Frames frames = new Frames();
        int frameIndex = INITIAL_INDEX;

        while (frameIndex <= NUMBERS_OF_NORMAL_FRAMES) {
            FrameLinkedList frameLinkedList = frames.getFrames();
            Frame frame = frameLinkedList.get(frameIndex);
            frame.delivery(InputView.inputScore(frameIndex + 1));
            ResultView.printBowlingGame(name.getName(), frames, frameIndex);

            if (frame.additionallyDeliverable()) {
                continue;
            }
            frameIndex++;
        }
    }
}
