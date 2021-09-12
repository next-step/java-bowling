package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class FinalFrameTest {

    @DisplayName("두번 투구했을 때, 두 투구의 합이 10 이하면 프레임이 끝난다.")
    @Test
    void isEnd_two() {
        assertThat(new FinalFrame().pitch(2).pitch(3).isEnd()).isTrue();
    }

    @DisplayName("두번 투구했을 때, 두 투구의 합이 10 이면 프레임이 끝나지 않은 상태다.")
    @Test
    void isEnd_two_false() {
        assertThat(new FinalFrame().pitch(2).pitch(8).isEnd()).isFalse();
    }

    @DisplayName("투구를 최대 세번 (스트라이크 또는 스페어 일 때) 할 수 있다.")
    @Test
    void isEnd_three() {
        assertThat(new FinalFrame().pitch(10).pitch(10).pitch(10).isEnd()).isTrue();
        assertThat(new FinalFrame().pitch(8).pitch(2).pitch(1).isEnd()).isTrue();
    }

    @DisplayName("끝난 프레임에 투구를 던지면 에러가 난다.")
    @Test
    void validate_error() {
        assertThatThrownBy(() -> new FinalFrame().pitch(1).pitch(2).pitch(10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파이널 프레임에서 2번 투구한 결과는 다음과 같다.")
    @ParameterizedTest
    @CsvSource(value = {"10 2 X|2", "2 8 2|/", "2 0 2|-", "10 10 X|X"}, delimiter = ' ')
    void pitch_result_two(int first, int second, String expected) {
        assertThat(new FinalFrame().pitch(first).pitch(second).result()).isEqualTo(expected);
    }

    @DisplayName("파이널 프레임에서 3번 투구한 결과는 다음과 같다.")
    @ParameterizedTest
    @CsvSource(value = {"10 10 10 X|X|X", "10 2 8 X|2|/", "10 0 0 X|0|-"}, delimiter = ' ')
    void pitch_result_three(int first, int second, int bonus, String expected) {
        assertThat(new FinalFrame().pitch(first).pitch(second).pitch(bonus).result()).isEqualTo(expected);
    }

    @DisplayName("투구가 가능한 파이널 프레임에서 next 는 그대로 파이널 프레임이다.")
    @Test
    void next() {
        assertThat(new FinalFrame().pitch(1).next())
                .isEqualTo(new FinalFrame().pitch(1));
    }

    @DisplayName("끝난 파이널 프레임에서 next 는 없기 때문에 에러가 난다.")
    @Test
    void next_error() {
        assertThatThrownBy(() -> new FinalFrame().pitch(1).pitch(2).next())
                .isInstanceOf(IllegalArgumentException.class);
    }
}