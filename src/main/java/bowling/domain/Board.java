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
    private ScorePendingQueue scorePendingQueue;

    public static Board init() {
        Frame firstFrame = new NormalFrame(1);
        Board board = new Board(new ArrayList<>(), firstFrame);
        board.addFrameIfMoveToNext(firstFrame);
        return board;
    }

    public Board(List<Frame> frames) {
        this(frames, new NormalFrame(1), new ScorePendingQueue());
    }

    public Board(List<Frame> frames, Frame currentFrame) {
        this(frames, currentFrame, new ScorePendingQueue());
    }

    public Board(List<Frame> frames, Frame currentFrame, ScorePendingQueue scorePendingQueue) {
        this.frames = frames;
        this.currentFrame = currentFrame;
        this.scorePendingQueue = scorePendingQueue;
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

    public int indexOfCurrentFrame() {
        return currentFrame.getIndex();
    }

    public void handleAfterTry(int fallenPins) {
        if(currentFrame.validatePins(fallenPins)) {
            throw new BowlingException(BowlingExceptionCode.INVALID_COUNT_OF_FALLEN_PINS, fallenPins);
        }
        currentFrame.handleAfterTry(fallenPins);
    }

    public void calculateScore() {
        // 투구 1번 끝날 때 마다 pending 건 점수 계산 시도
        handlePendingIfExisted();

        // 다음 프레임으로 넘어가야할 때 pendingQueue에 들어가거나 점수 계산 바로 한다.
        if (currentFrame.isOver()) {
            deferOrCalculate(currentFrame);
            askCurrentFrame(); // TODO(jack.comeback) : 프레임이 끝나고 항상 넘어오니까 moveToNextFrame() 이런 네이밍은 어떨지?
        }
    }

    // 스트라이크, 스페어면 점수 계산 지연한다.
    private void deferOrCalculate(Frame currentFrame) {
        if (currentFrame.isCommonScoreType()) {
            ScoreCalculator.calculate(currentFrame);
        }
        defer(currentFrame);
    }

    private void defer(Frame currentFrame) {
        if (currentFrame.isSpareScoreType()) {
            scorePendingQueue.add(PendingFrame.spare(currentFrame.getIndex()));
        }
        if (currentFrame.isStrikeScoreType()) {
            scorePendingQueue.add(PendingFrame.strike(currentFrame.getIndex()));
        }
    }

    private void handlePendingIfExisted() {
        scorePendingQueue.minusPopCount();
        scorePendingQueue.getPreparedPending()
                .ifPresent(pending -> {
                    Frame pendingFrame = search(pending.getIndex());
                    ScoreCalculator.handlePending(pendingFrame);
                });
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
