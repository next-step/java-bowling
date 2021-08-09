package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.exception.BowlFailureException;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Frames implements Iterable<Frame> {
    private final List<Frame> frames;
    private Frames(final List<Frame> frames) {
        validateFrameSize(frames);

        this.frames = frames;
    }

    private void validateFrameSize(final List<Frame> frames) {
        if (frames.isEmpty()) {
            throw new IllegalArgumentException("최소 1개의 프레임을 생성 해야 합니다.");
        }
    }

    public static Frames generate(final int size) {
        validateGenerateSize(size);

        List<Frame> frames = generateExcludeFinalFrame(size);
        addFinalFrame(frames);

        return new Frames(frames);
    }

    private static List<Frame> generateExcludeFinalFrame(final int size) {
        return Stream.generate(Frame::new)
                .limit(size - 1)
                .collect(Collectors.toList());
    }

    private static void addFinalFrame(List<Frame> frames) {
        frames.add(new FinalFrame());
    }

    private static void validateGenerateSize(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("최소 1개의 프레임을 생성 해야 합니다.");
        }
    }

    public void bowl(TurnScore score) {
        Frame waitingFrame = waitingFrame()
                .orElseThrow(BowlFailureException::new);

        replace(waitingFrame, waitingFrame.bowl(score));
    }

    private Optional<Frame> waitingFrame() {
        return frames.stream()
                .filter(Frame::isWaiting)
                .findFirst();
    }

    public boolean isCompleted() {
        return frames.stream()
                .noneMatch(Frame::isWaiting);
    }

    private void replace(Frame oldFrame, Frame newFrame) {
        int oldFrameIndex = frames.indexOf(oldFrame);

        frames.set(oldFrameIndex, newFrame);
    }

    public int size() {
        return frames.size();
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }
}
