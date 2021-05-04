package bowling.domain;

import bowling.domain.dto.FrameBoard;
import bowling.domain.dto.FrameInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Frames implements Iterable<Frame> {

    private final List<Frame> frames;

    public Frames() {
        this(new ArrayList<>());
    }

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public void pitch(int pinDownCount) {
        if (isEmpty()) {
            start();
        }
        if (lastFrame().isFinished()) {
            addNextFrame();
        }
        lastFrame().pitch(new Pitch(pinDownCount));
    }

    public int lastFrameNumber() {
        if (isEmpty()) {
            throw new IllegalStateException("프레임이 존재하지 않습니다. 투구를 실행해 주세요.");
        }
        return lastFrame().number();
    }

    public int nextTurnNumber() {
        if (isEmpty()) {
            return NormalFrame.FIRST_NUMBER;
        }
        if (isFinished()) {
            return lastFrameNumber();
        }
        if (lastFrame().isFinished()) {
            return lastFrameNumber() + 1;
        }
        return lastFrameNumber();
    }

    public boolean isFinished() {
        return !isEmpty() && lastFrame().isFinalFrame() && lastFrame().isFinished();
    }

    private boolean isEmpty() {
        return frames.isEmpty();
    }

    private void start() {
        frames.add(NormalFrame.first());
    }

    private void addNextFrame() {
        frames.add(lastFrame().next());
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - 1);
    }

    public int totalScore() {
        return frames.stream()
                .mapToInt(Frame::score)
                .sum();
    }

    public FrameBoard assembleFrameBoard(Participant participant) {
        return new FrameBoard(participant.getName(), nextTurnNumber(), assembleFrameInfos(), isFinished());
    }

    private List<FrameInfo> assembleFrameInfos() {
        List<FrameInfo> frameInfos = new ArrayList<>();
        for (Frame frame : frames) {
            FrameInfo frameInfo = frame.assembleFrameInfo();
            frameInfos.add(frameInfo);
        }
        return frameInfos;
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }

}
