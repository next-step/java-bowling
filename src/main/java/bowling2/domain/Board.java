package bowling2.domain;

import bowling2.domain.frame.Frame;
import bowling2.domain.frame.NormalFrame;
import bowling2.exception.BowlingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
            throw new BowlingException();
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

    // TODO(jack.comeback) : 에러처리 디테일하게
    public Frame search(int index) {
        return frames.stream()
                .filter(frame -> frame.getIndex() == index)
                .findFirst()
                .orElseThrow(() -> new BowlingException("index에 해당하는 프레임이 없습니다. index = " + index));
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
