package bowling2.domain;

import bowling2.domain.frame.Frame2;
import bowling2.domain.frame.NormalFrame2;
import bowling2.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    private final List<Frame2> frame2s;
    private Frame2 currentFrame;

    public static Board init() {
        Frame2 firstFrame = new NormalFrame2(1);
        Board board = new Board(new ArrayList<>(), firstFrame);
        board.addFrameIfMoveToNext(firstFrame);
        return board;
    }

    private void addFrameIfMoveToNext(Frame2 mayBeNewFrame) {
        boolean notExisted = frame2s.stream()
                .noneMatch(existingFrame -> existingFrame.getIndex() == mayBeNewFrame.getIndex());
        if (notExisted) {
            frame2s.add(mayBeNewFrame);
        }
    }

    public Board(List<Frame2> frame2s) {
        this(frame2s, new NormalFrame2(1));
    }

    public Board(List<Frame2> frame2s, Frame2 currentFrame) {
        this.frame2s = frame2s;
        this.currentFrame = currentFrame;
    }

    public Frame2 currentFrame() {
        return currentFrame;
    }

    public int indexOfCurrentFrame() {
        return currentFrame.getIndex();
    }

    public Frame2 askCurrentFrame() {
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
        return Objects.equals(frame2s, board.frame2s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frame2s);
    }
}
