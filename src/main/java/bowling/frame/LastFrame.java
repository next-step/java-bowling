package bowling.frame;

import bowling.Pins;
import bowling.state.Throwing;
import bowling.state.running.Ready;

import java.util.LinkedList;
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
}
