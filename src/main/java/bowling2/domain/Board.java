package bowling2.domain;

import bowling2.domain.frame.Frame;
import bowling2.domain.frame.NormalFrame;
import bowling2.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private final List<Frame> frames;
    private Frame currentFrame;

    public static Board init() {
        Frame firstFrame = new NormalFrame(1);
        Board board = new Board(new ArrayList<>(), firstFrame);
        board.addFrameIfMoveToNext(firstFrame);
        return board;
    }

    private void addFrameIfMoveToNext(Frame mayBeNewFrame) {
        boolean notExisted = frames.stream()
                .noneMatch(existingFrame -> existingFrame.getIndex() == mayBeNewFrame.getIndex());
        if (notExisted) {
            frames.add(mayBeNewFrame);
        }
    }

    public Board(List<Frame> frames) {
        this(frames, new NormalFrame(1));
    }

    public Board(List<Frame> frames, Frame currentFrame) {
        this.frames = frames;
        this.currentFrame = currentFrame;
    }

    public Frame currentFrame() {
        return currentFrame;
    }

    public int indexOfCurrentFrame() {
        return currentFrame.getIndex();
    }

    public Frame askCurrentFrame() {
        return currentFrame.askCurrentFrame();
    }

    // TODO(jack.comeback) : addFrameIfMoveToNext() 라는 역할 필요. 프레임이 끝나면 다음 프레임을 board에 추가한다.

    public void handleAfterTry(int fallenPins) {
        if (currentFrame.validatePins(fallenPins)) {
            throw new BowlingException();
        }
        currentFrame.handleAfterTry(fallenPins);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Objects.equals(frames, board.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
