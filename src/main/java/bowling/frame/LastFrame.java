package bowling.frame;

import bowling.Pins;
import bowling.exception.CannotScoreCalculateException;
import bowling.state.Throwing;
import bowling.state.running.Ready;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

public class LastFrame implements Frame {

    private static final String DELIMITER_OF_SCORE = "|";
    static final int FRAME_MIN_BOWL = 2;
    static final int FRAME_MAX_BOWL = 3;

    private final LinkedList<Throwing> states = new LinkedList<>();
    private final GameOver gameOver;

    private LastFrame() {
        this.states.add(new Ready());
        this.gameOver = new GameOver();
    }

    public static Frame first() {
        return new LastFrame();
    }

    @Override
    public Frame bowl(Pins pins) {
        validateEndGame();
        Throwing recent = recentState();

        if (isStrikeOrSpare(recent)) {
            states.add(new Ready().bowl(pins));
            gameOver.increaseBowlCount();
            return this;
        }

        // 1구 투구 시 첫 Ready 상태를 지운 후 상태를 추가한다.
        // 2구까지 투구 완료 시에도 1구에서 추가한 상태를 지운 후 최신 상태를 추가한다.
        states.removeLast();
        states.add(recent.bowl(pins));
        gameOver.increaseBowlCount();
        return this;
    }

    @Override
    public String symbol() {
        return states.stream()
                .map(Throwing::symbol)
                .collect(Collectors.joining(DELIMITER_OF_SCORE));
    }

    @Override
    public int getFrameNo() {
        return Frame.MAX_FRAME_NO;
    }

    @Override
    public boolean isEnd() {
        return gameOver.isEndGame(recentState());
    }

    @Override
    public int calculateScore() {
        try {
            return getScore().getScore();
        } catch (CannotScoreCalculateException e) {
            return Score.UN_SCORE_STATE;
        }
    }

    private Score getScore() {
        Score score = firstScore();
        for (int i = 1; i < states.size(); i++) {
            Throwing state = states.get(i);
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    private Score firstScore() {
        return states.get(0).getScore();
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        Score score = beforeScore;
        for (Throwing state : states) {
            score = state.calculateAdditionalScore(score);
        }
        return score;
    }

    private Throwing recentState() {
        return states.getLast();
    }

    private void validateEndGame() {
        if (isEnd()) {
            throw new UnsupportedOperationException("게임이 끝났습니다.");
        }
    }

    private boolean isStrikeOrSpare(Throwing recent) {
        return !recent.isMiss() && recent.isEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastFrame lastFrame = (LastFrame) o;
        return Objects.equals(states, lastFrame.states) && Objects.equals(gameOver, lastFrame.gameOver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, gameOver);
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "states=" + states +
                ", gameOver=" + gameOver +
                '}';
    }
}
