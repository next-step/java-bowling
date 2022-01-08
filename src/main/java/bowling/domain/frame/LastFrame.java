package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.running.Ready;

import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 마지막 프레임이다.
 * LinkedList<ThrowingState> states
 * -> 1구 + 2구의 상태 1개만 가질 수도 있고,(미스인 경우)
 * -> 1구 + 2구의 상태와 3구의 상태 2개를 가질 수도 있다(스페어, 스트라이크)
 */
public class LastFrame implements Frame {

    private static final String DELIMITER_OF_SCORE = "|";
    private static final int FRAME_MIN_BOWL = 2;
    private static final int FRAME_MAX_BOWL = 3;

    private final LinkedList<ThrowingState> states = new LinkedList<>();
    private int bowlCount;

    private LastFrame() {
        this.states.add(Ready.create());
        this.bowlCount = 0;
    }

    public static Frame first() {
        return new LastFrame();
    }

    @Override
    public Frame bowl(Pins pins) {
        validate();
        ThrowingState recent = states.getLast();

        // 스페어, 스트라이크일 경우 상태를 추가한다.
        // 1구 + 2구의 상태와 3구의 상태인 총 2가지 상태를 가진다.
        if (!recent.isMiss() && recent.isEnd()) {
            states.add(Ready.create().bowl(pins));
            bowlCount++;
            return this;
        }

        // 1구 투구 시 첫 Ready 상태를 지운 후 상태를 추가한다.
        // 2구까지 투구 완료 시에도 1구에서 추가한 상태를 지운 후 최신 상태를 추가한다.
        states.removeLast();
        states.add(recent.bowl(pins));
        bowlCount++;
        return this;
    }

    @Override
    public int getIndex() {
        return FrameIndex.MAX_INDEX;
    }

    @Override
    public boolean isEnd() {
        if (bowlCount == FRAME_MAX_BOWL) {
            return true;
        }
        ThrowingState recent = states.getLast();
        return bowlCount == FRAME_MIN_BOWL && recent.isMiss();
    }

    @Override
    public String symbol() {
        return states.stream()
                .map(ThrowingState::symbol)
                .collect(Collectors.joining(DELIMITER_OF_SCORE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastFrame lastFrame = (LastFrame) o;
        return bowlCount == lastFrame.bowlCount && Objects.equals(states, lastFrame.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(states, bowlCount);
    }

    @Override
    public String toString() {
        return "LastFrame{" +
                "states=" + states +
                ", bowlCount=" + bowlCount +
                '}';
    }

    private void validate() {
        ThrowingState recent = states.getLast();
        if (recent.isMiss()) {
            throw new UnsupportedOperationException("프레임이 끝난 상태는 투구할 수 없습니다.");
        }
    }
}
