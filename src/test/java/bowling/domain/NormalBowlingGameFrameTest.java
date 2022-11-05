package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NormalBowlingGameFrameTest {

    @DisplayName("투구 기록을 생성할 때, 2회 초과의 기록을 저장하면, 예외가 발생해야 한다.")
    @Test
    void create_givenOverMaxSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new NormalBowlingGameFrame(List.of(1, 2, 3)))
                .withMessage("투구 기록은 최대 2회 까지 저장할 수 있습니다.");
    }

    @DisplayName("투구 기록을 생성할 때, 0 보다 작은 정수를 포함하고 있다면, 예외가 발생해야 한다.")
    @Test
    void create_givenContainingNegative() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new NormalBowlingGameFrame(List.of(0, -1)))
                .withMessage("투구는 0 보다 작을 수 없습니다.");
    }

    @DisplayName("투구 기록을 생성할 때, 투구의 합이 10 보다 크면, 예외가 발생해야 한다.")
    @Test
    void create_givenSumOverMaxSum() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new NormalBowlingGameFrame(List.of(2, 9)))
                .withMessage("투구의 합은 최대 10 이어야 합니다.");
    }

    @DisplayName("투구 기록 추가하기")
    @Test
    void add() {
        BowlingGameFrame history = new NormalBowlingGameFrame();
        history.add(3);
        history.add(5);
        assertThat(history).isEqualTo(new NormalBowlingGameFrame(List.of(3, 5)));
    }

    @DisplayName("투구 기록을 추가할 때, 0 보다 작은 정수를 입력하면, 예외가 발생해야 한다.")
    @Test
    void add_givenNegative() {
        BowlingGameFrame history = new NormalBowlingGameFrame();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> history.add(-1))
                .withMessage("투구는 0 보다 작을 수 없습니다.");
    }

    @DisplayName("투구 기록을 추가할 때, 남은 핀의 수보다 큰 정수를 입력하면, 예외가 발생해야 한다.")
    @Test
    void add_givenOverRemainedPins() {
        BowlingGameFrame history = new NormalBowlingGameFrame();
        history.add(4);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> history.add(7))
                .withMessage("투구는 남은 핀의 개수(6) 보다 클 수 없습니다.");
    }

    @DisplayName("투구 기록을 2회보다 많이 추가할 때, 예외가 발생해야 한다.")
    @Test
    void add_whenOver2Times() {
        BowlingGameFrame bowlingGameFrame = new NormalBowlingGameFrame();
        bowlingGameFrame.add(3);
        bowlingGameFrame.add(4);
        assertThatIllegalStateException()
                .isThrownBy(() -> bowlingGameFrame.add(1))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

    @DisplayName("두번째 투구 기록을 추가할 때, 스트라이크라면, 예외가 발생해야 한다.")
    @Test
    void add_givenStrike() {
        BowlingGameFrame history = new NormalBowlingGameFrame();
        history.add(10);
        assertThatIllegalStateException()
                .isThrownBy(() -> history.add(0))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

}
