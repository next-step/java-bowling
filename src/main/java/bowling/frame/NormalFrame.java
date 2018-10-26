package bowling.frame;

import bowling.frame.state.State;
import bowling.frame.state.StateFactory;

public class NormalFrame implements Frame {
    public static final int UN_SCORE_STATE = -1;

    private Frame next;
    private State state;
    private int no;

    public NormalFrame(int no) {
        this.state = StateFactory.ready();
        this.no = no;
    }

    public Frame bowl(int countOfPin) {
        state = state.bowl(countOfPin);
        if (state.isFinish()) {
            next = createFrame();
            return next;
        }
        return this;
    }

    private Frame createFrame() {
        if (no + 1 == 10) {
            return  new LastFrame();
        }

        return new NormalFrame(no + 1);
    }

    public int getNo() {
        return no;
    }

    private Score getScore() {
        Score score = state.getScore();
        if (score.canCalculateScore()) {
            return score;
        }

        return next.calculateAdditionalScore(score);
    }

    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = state.calculateAdditionalScore(beforeScore);
        if (score.canCalculateScore()) {
            return score;
        }
        return next.calculateAdditionalScore(score);
    }

    FrameResult getFrameResult() {
        if (!state.isFinish()) {
            return new FrameResult(state.getDesc(), UN_SCORE_STATE);
        }

        try {
            return new FrameResult(state.getDesc(), getScore().getScore());
        } catch (CannotCalculateException e) {
            return new FrameResult(state.getDesc(), UN_SCORE_STATE);
        }
    }

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
}
