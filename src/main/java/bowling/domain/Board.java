package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.exception.BowlingException;
import bowling.exception.BowlingExceptionCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static bowling.exception.BowlingExceptionCode.NO_SUCH_FRAME;

public class Board {
    private final List<Frame> frames;
    private Frame currentFrame;
    private ScoreCalculator scoreCalculator;

    public static Board init() {
        Frame firstFrame = new NormalFrame(1);
        Board board = new Board(new ArrayList<>(), firstFrame);
        board.addFrameIfMoveToNext(firstFrame);
        return board;
    }

    private void addFrameIfMoveToNext(Frame mayBeNewFrame) {
        Optional.ofNullable(mayBeNewFrame)
                .ifPresent(newFrame -> {
                            boolean notExisted = frames.stream()
                                    .noneMatch(existingFrame -> existingFrame.getIndex() == newFrame.getIndex());
                            if (notExisted) {
                                frames.add(newFrame);
                            }
                        }
                );

    }

    public Board(List<Frame> frames) {
        this(frames, new NormalFrame(1), ScoreCalculator.init());
    }

    public Board(List<Frame> frames, Frame currentFrame) {
        this(frames, currentFrame, ScoreCalculator.init());
    }

    public Board(List<Frame> frames, Frame currentFrame, ScoreCalculator scoreCalculator) {
        this.frames = frames;
        this.currentFrame = currentFrame;
        this.scoreCalculator = scoreCalculator;
    }

    public int indexOfCurrentFrame() {
        return currentFrame.getIndex();
    }

    public void handleAfterTry(int fallenPins) {
        if (currentFrame.validatePins(fallenPins)) {
            throw new BowlingException(BowlingExceptionCode.INVALID_COUNT_OF_FALLEN_PINS, fallenPins);
        }
        currentFrame.handleAfterTry(fallenPins);
        // 투구 1번 끝날 때 마다 pending 건 처리 시도
        scoreCalculator.findPreparedPending()
                .ifPresent(pendingFrame -> {
                    Frame frame = search(pendingFrame.getIndex());
                    scoreCalculator.handlePending(frame);
                });

        // 다음 프레임으로 넘어가야할 때 pending에 들어가거나 점수 계산 바로 한다.
        if (currentFrame.isOver()) {
            scoreCalculator.pendingOrCalculate(currentFrame);
            askCurrentFrame();
        }
    }

    private void askCurrentFrame() {
        currentFrame = currentFrame.askCurrentFrame();
        addFrameIfMoveToNext(currentFrame);
    }

    public boolean inProgress() {
        return currentFrame != null;
    }

    public int frameSize() {
        return frames.size();
    }

    public List<Frame> frames() {
        return frames;
    }

    public Frame search(int index) {
        return frames.stream()
                .filter(frame -> frame.getIndex() == index)
                .findFirst()
                .orElseThrow(() -> new BowlingException(NO_SUCH_FRAME, index));
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
