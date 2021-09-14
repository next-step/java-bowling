package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @DisplayName("처음 던진 투구가 스트라이크면 한 프레임이 끝난다.")
    @Test
    void isEnd_strike() {
        Frame normal = new NormalFrame(1);
        normal.pitch(10);
        assertThat(normal.isEnd()).isTrue();
    }

    @DisplayName("두번째 투구를 마쳤을때 한 프레임이 끝난다.")
    @Test
    void isEnd_secondPitch() {
        Frame normal = new NormalFrame(1);
        normal.pitch(2).pitch(3);
        assertThat(normal.isEnd()).isTrue();
    }

    @DisplayName("노멀 프레임에서 세번 이상 던질때 에러가 발생한다.")
    @Test
    void pitch_validate_three() {
        assertThatThrownBy(() -> new NormalFrame(1).pitch(2).pitch(3).pitch(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("노멀 프레임에서 스트라이크 이후 또 투구하면 에러가 발생한다.")
    @Test
    void pitch_new_frame() {
        assertThatThrownBy(() -> new NormalFrame(1).pitch(10).pitch(3).pitch(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("노멀프레임에서 2번 투구한 결과: number + spare = 10")
    @ParameterizedTest
    @CsvSource(value = {"8:2", "0:10"}, delimiter = ':')
    void pitch_two_spare(int first, int second) {
        assertThat(new NormalFrame(1).pitch(first).pitch(second).pitches().statuses())
                .isEqualTo(Arrays.asList(Status.NUMBER, Status.SPARE));
    }

    @DisplayName("노멀프레임에서 2번 투구한 결과: number + 0 (gutter)")
    @ParameterizedTest
    @CsvSource(value = {"0:0", "5:0"}, delimiter = ':')
    void pitch_two_gutter(int first) {
        assertThat(new NormalFrame(1).pitch(first).pitch(0).pitches().statuses())
                .isEqualTo(Arrays.asList(Status.NUMBER, Status.GUTTER));
    }

    @DisplayName("투구가 끝난 프레임의 다음은 새 프레임이다.")
    @Test
    void next_normal() {
        Frame normal = new NormalFrame(1);
        assertThat(normal.pitch(2).pitch(8).next()).isEqualTo(new NormalFrame(2));
    }

    @DisplayName("투구가 끝난 마지막 노멀 프레임의 다음은 파이널 프레임이다.")
    @Test
    void next_final() {
        assertThat(new NormalFrame(9).pitch(10).next()).isEqualTo(new FinalFrame());
    }
}