package bowling.domain;

import bowling.domain.State.Ready;
import bowling.domain.State.State;
import bowling.exception.CannotCalculateException;

public class NormalFrame implements Frame {
    private static final int MAX_FRAME_NUM = 10;
    private boolean bonusFlag = false;
    private State state;
    private int frameNum;
    private Frame next;

    public NormalFrame(int frameNum) {
        state = new Ready();
        this.frameNum = frameNum;
    }

    @Override
    public Frame bowl(int felledPins) {
        state = state.bowl(felledPins);
        if (state.isFinish()) {
            next = create();
            return next;
        }
        return this;
    }

    private Frame create() {
        if (!state.isFinish()) {
            throw new IllegalArgumentException("끝나지 않은 상태에서는 새로운 프레임을 만들 수 없습니다.");
        }
        if (frameNum + 1 == MAX_FRAME_NUM) {
            return new FinalFrame(bonusFlag);
        }
        return new NormalFrame(frameNum + 1);
    }

    public void bonus() {
        bonusFlag = true;
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    @Override
    public int getFrameNum() {
        return frameNum;
    }

    @Override
    public Score getScore() {
        Score score = state.getScore();
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    @Override
    public void addFrameResult(Board board) {
        board.add(getFrameResult());

        if (next != null) {
            next.addFrameResult(board);
        }
    }

    public Board createBoard() {
        Board board = new Board();
        addFrameResult(board);
        return board;
    }

    public FrameResult getFrameResult() {
        if (!state.isFinish()) {
            return new FrameResult(state.getDesc());
        }

        try {
            return new FrameResult(state.getDesc(), getScore().getScore());
        } catch (CannotCalculateException e) {
            return new FrameResult(state.getDesc());
        }
    }
}
