package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {

    private final List<Frame> frames;
    private int currentIndex = 1;

    public static ScoreBoard from(String userName) {
        List<Frame> result = new LinkedList<>();
        Frame head = HeadFrame.from(userName);
        makeFramesRecursive(result, head);

        return new ScoreBoard(result);
    }

    private static void makeFramesRecursive(List<Frame> frameList, Frame frame) {
        if (frame.isFinalFrame()) {
            frameList.add(frame);
            return;
        }
        frameList.add(frame);
        makeFramesRecursive(frameList, frame.makeNextFrame());
    }

    private ScoreBoard(List<Frame> frames) {
        this.frames = frames;
    }

    public boolean record(BowlingScore score) {
        Frame currentFrame = frames.get(currentIndex);

        boolean isDone = currentFrame.record(score);

        if(isDone) {
            currentIndex++;
        }

        if(currentFrame.isFinalFrame() && isDone) {
            return true;
        }

        return false;
    }

    public String printableStatus() {
        String titleLine = frames.stream()
                .map(Frame::printableTitle)
                .collect(Collectors.joining());

        String valueLine = frames.stream()
                .map(Frame::printableValue)
                .collect(Collectors.joining());

        return titleLine + "\n" + valueLine + "\n";
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
}
