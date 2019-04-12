package domain.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    public static int START_FRAME = 1;
    public static int LAST_FRAME = 10;

    private final List<Frame> frames = new LinkedList<>();

    public boolean isEmpty() {
        return frames.isEmpty();
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public int getNextFrameNumber() {
        if (isEmpty()) {
            return START_FRAME;
        }

        if (getRecentFrame().isFinished()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public Frame getRecentFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean contains(Frame frame) {
        return frames.contains(frame);
    }

    public Frame get(int i) {
        return frames.get(i);
    }

    public int size() {
        return frames.size();
    }

    public List<String> getStatusStrings() {
        List<String> statusString = getFrameStatusStrings();
        statusString.addAll(getBlankStrings(statusString.size()));
        return statusString;
    }

    private List<String> getFrameStatusStrings() {
        return frames.stream()
                .map(Frame::toString)
                .collect(Collectors.toList());
    }

    public List<String> getScoreStrings() {
        List<String> scoreStrings = getFrameScoreStrings();
        scoreStrings.addAll(getBlankStrings(scoreStrings.size()));
        return scoreStrings;
    }

    private List<String> getFrameScoreStrings() {
        AtomicInteger sum = new AtomicInteger(0);

        return frames.stream()
                .filter(Frame::isScoreCalculationFinished)
                .map(frame -> sum.addAndGet(frame.getScore()))
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    private List<String> getBlankStrings(int previousSize) {
        return IntStream.range(0, LAST_FRAME - previousSize)
                .mapToObj($ -> "")
                .collect(Collectors.toList());
    }
}