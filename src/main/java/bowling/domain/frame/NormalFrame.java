package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final int playCount;
    private State state;
    private Score score;
    public static final int MAX_PLAY_COUNT = 8;
    public static final int MIN_PLAY_COUNT = 0;
    private static final String INVALID_PLAY_COUNT = "normal frame은 0번 이상 9번 미만 프레임만 가능합니다.";
    public static final String INVALID_END_PLAY = "더이상 진행 할 수 없습니다.";

    public NormalFrame(int playCount) {
        this(playCount, new Ready());
    }

    public NormalFrame(int playCount, State state) {
        validate(playCount);
        this.playCount = playCount;
        this.state = state;
    }

    private void validate(int playCount) {
        if (playCount < MIN_PLAY_COUNT || playCount > MAX_PLAY_COUNT) {
            throw new IllegalArgumentException(INVALID_PLAY_COUNT);
        }
    }

    public static NormalFrame createFirst() {
        return new NormalFrame(MIN_PLAY_COUNT);
    }

    public NormalFrame next() {
        return new NormalFrame(playCount + 1);
    }

    public void play(int count) {
        if (this.isEnd()) {
            throw new IllegalArgumentException(INVALID_END_PLAY);
        }

        this.state = state.play(count);
        createScore();
    }

    @Override
    public String getFallenPins() {
        return state.toString();
    }

    @Override
    public int getScore() {
        return this.score.getScore();
    }

    @Override
    public void calculateScore(int playCount, int count) {
        if (this.playCount == playCount || score.isEndCalculate()) {
            return;
        }

        score.addScore(count);
    }

    public boolean hasScore() {
        return Objects.nonNull(score) && score.isEndCalculate();
    }

    private void createScore() {
        if (!state.isFinish()) {
            return;
        }

        this.score = state.getScore();
    }

    public boolean isEnd() {
        return state.isFinish();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame frame = (NormalFrame) o;
        return playCount == frame.playCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playCount);
    }
}
