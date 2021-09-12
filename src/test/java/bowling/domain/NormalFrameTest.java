package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("노멀프레임에서 2번 투구한 결과는 다음과 같다.")
    @ParameterizedTest
    @CsvSource(value = {"2 2|2", "8 2|/", "0 2|-"}, delimiter = ' ')
    void spare(int countOfPints, String expected) {
        assertThat(new NormalFrame(1).pitch(2).pitch(countOfPints).result()).isEqualTo(expected);
    }

    @DisplayName("투구가 끝난 프레임의 다음은 새 프레임이다.")
    @Test
    void next_normal(){
        Frame normal = new NormalFrame(1);
        assertThat(normal.pitch(2).pitch(8).next()).isEqualTo(new NormalFrame(2));
    }
    
}