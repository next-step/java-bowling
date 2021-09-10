package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    private NormalFrame frame;

    @BeforeEach
    void setUp() {
        frame = NormalFrame.of(1);
    }

    @DisplayName("마지막 프레임이 아닌 경우, 프레임의 번호는 1 ~ 9 사이여야 한다.")
    @ValueSource(ints = {0, 10, 11})
    @ParameterizedTest
    void invalidFrameNumber(int number) {
        assertThatThrownBy(() -> NormalFrame.of(number))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫 번째 투구로 10개의 핀을 모두 쓰러뜨리면 스트라이크이다.")
    @Test
    void strike() {
        frame.bowl(10);
        assertThat(frame.results()).containsExactly(PitchResult.of(10));
    }

    @DisplayName("두 번의 투구로 10개의 핀을 모두 쓰러뜨리면 스페어다.")
    @CsvSource(value = {"0:10", "1:9", "2:8", "3:7", "4:6", "5:5"}, delimiter = ':')
    @ParameterizedTest
    void spare(int firstPins, int secondPins) {
        frame.bowl(firstPins);
        frame.bowl(secondPins);
        assertThat(frame.results()).containsExactly(PitchResult.of(firstPins), PitchResult.spare(secondPins));
    }

    @DisplayName("두 번의 투구로 10개의 핀을 모두 쓰러뜨리지 못하면 미스다.")
    @Test
    void miss() {
        frame.bowl(4);
        frame.bowl(5);
        assertThat(frame.results()).containsExactly(PitchResult.of(4), PitchResult.of(5));
    }

    @DisplayName("하나의 핀도 쓰러뜨리지 못하면 거터다.")
    @Test
    void gutter() {
        frame.bowl(0);
        assertThat(frame.results()).containsExactly(PitchResult.of(0));
    }

    @DisplayName("두 번째 투구 결과, 쓰러뜨린 핀의 총 개수는 10개 이하여야 한다.")
    @Test
    void invalidTotalPins() {
        frame.bowl(3);
        assertThatThrownBy(() -> frame.bowl(8))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크를 친 경우, 해당 프레임은 종료된다.")
    @Test
    void frameIsEndAfterStrike() {
        assertThat(frame.isEnd()).isFalse();
        frame.bowl(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("스페어를 친 경우, 해당 프레임은 종료된다.")
    @Test
    void frameIsEndAfterSpare() {
        frame.bowl(3);
        assertThat(frame.isEnd()).isFalse();
        frame.bowl(7);
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("스트라이크를 친 경우, 해당 프레임에서 더 이상 투구를 할 수 없다.")
    @Test
    void cannotBowlAfterStrike() {
        frame.bowl(10);
        assertThatThrownBy(() -> frame.bowl(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스페어를 친 경우, 해당 프레임에서 더 이상 투구를 할 수 없다.")
    @CsvSource(value = {"0:10", "1:9", "2:8", "3:7", "4:6", "5:5"}, delimiter = ':')
    @ParameterizedTest
    void cannotBowlAfterSpare(int firstPins, int secondPins) {
        frame.bowl(firstPins);
        frame.bowl(secondPins);
        assertThatThrownBy(() -> frame.bowl(2))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
