package bowling.model.frame;

import bowling.model.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {

    private FinalFrame frame;

    @BeforeEach
    void setUp() {
        frame = (FinalFrame) FinalFrame.of();
    }

    @DisplayName("게임 한번 진행 시 계속 진행한다")
    @Test
    void bowl_oneTime_thanContinueGame() {
        // given
        Pins first = Pins.valueOf(5);

        // when
        frame.bowl(first);

        assertThat(frame.isGameOver()).isFalse();
    }

    @DisplayName("첫번째 결과가 스페어 일 시 게임을 계속 진행한다")
    @Test
    void downPinTenAndTen_nextBonusStage() {
        // given
        Pins first = Pins.valueOf(5);
        Pins second = Pins.valueOf(5);

        // when
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isGameOver()).isFalse();
    }

    @DisplayName("첫번째 결과가 스트라이크 일 시 보너스 게임을 진행한다")
    @Test
    void downPinTenAndZero_nextBonusStage() {
        // given
        Pins first = Pins.DOWN_ALL;
        Pins second = Pins.DOWN_ZERO;

        // when
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isGameOver()).isFalse();
    }

    @DisplayName("두번째 결과가 스트라이크 일 시 보너스 게임을 진행한다")
    @Test
    void downPin0And10_nextBonusStage() {
        // given
        Pins first = Pins.DOWN_ZERO;
        Pins second = Pins.DOWN_ALL;

        // when
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isGameOver()).isFalse();
    }

    @DisplayName("게임 세번 진행 시 게임종료")
    @Test
    void bowl_threeTime_thanGameOver() {
        // given
        Pins downPins = Pins.DOWN_ALL;

        // when
        frame.bowl(downPins);
        frame.bowl(downPins);
        frame.bowl(downPins);

        assertThat(frame.isGameOver()).isTrue();
    }

    @DisplayName("첫번째 게임 결과가 미스일 경우 게임 종료한다")
    @Test
    void bowl_twoTime_thanContinueGame() {
        // given
        Pins first = Pins.valueOf(5);
        Pins second = Pins.DOWN_ZERO;

        // when
        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.isGameOver()).isTrue();
    }
}