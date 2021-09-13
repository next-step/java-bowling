package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

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
    @ParameterizedTest
    @CsvSource(value = {"10:10:10", "8:2:1"}, delimiter = ':')
    void isEnd_three(int first, int second, int bonus) {
        assertThat(new FinalFrame().pitch(first).pitch(second).pitch(bonus).isEnd()).isTrue();
    }

    @DisplayName("끝난 프레임에 투구를 던지면 에러가 난다.")
    @Test
    void validate_error() {
        assertThatThrownBy(() -> new FinalFrame().pitch(1).pitch(2).pitch(10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파이널 프레임에서 2번 투구한 결과: number + number = number ")
    @ParameterizedTest
    @CsvSource(value = {"8:1", "2:2", "1:1"}, delimiter = ':')
    void pitch_result_two_number(int first, int second) {
        assertThat(new FinalFrame().pitch(first).pitch(second).pitches().statuses())
                .isEqualTo(Arrays.asList(Status.NUMBER, Status.NUMBER));
    }

    @DisplayName("파이널 프레임에서 2번 투구한 결과: number + 0 = gutter ")
    @ParameterizedTest
    @CsvSource(value = {"8:0", "2:0", "1:0", "0:0"}, delimiter = ':')
    void pitch_result_two_gutter(int first, int second) {
        assertThat(new FinalFrame().pitch(first).pitch(second).pitches().statuses())
                .isEqualTo(Arrays.asList(Status.NUMBER, Status.GUTTER));
    }

    @DisplayName("파이널 프레임에서 2번 투구한 결과: number + number = spare ")
    @ParameterizedTest
    @CsvSource(value = {"8:2", "2:8", "1:9", "0:10"}, delimiter = ':')
    void pitch_result_two_spare(int first, int second) {
        assertThat(new FinalFrame().pitch(first).pitch(second).pitches().statuses())
                .isEqualTo(Arrays.asList(Status.NUMBER, Status.SPARE));
    }

    @DisplayName("파이널 프레임에서 2번 투구한 결과: 10 + 10 = strike ")
    @Test
    void pitch_result_two_strike() {
        assertThat(new FinalFrame().pitch(10).pitch(10).pitches().statuses())
                .isEqualTo(Arrays.asList(Status.STRIKE, Status.STRIKE));
    }

    @DisplayName("파이널 프레임에서 3번 투구한 결과: 스트라이크나 스페어의 경우를 포함한다.")
    @ParameterizedTest
    @CsvSource(value = {"8:2:10", "2:8:10", "10:1:2"}, delimiter = ':')
    void pitch_result_three(int first, int second, int bonus) {
        assertThat(new FinalFrame().pitch(first).pitch(second).pitch(bonus).pitches().statuses())
                .containsAnyOf(Status.STRIKE, Status.SPARE);
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