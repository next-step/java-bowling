package bowling.frame;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {

    @Test
    @DisplayName("노말 프레임은 10번 진행 시 에러")
    void ten_frameNumber() {
        assertThatThrownBy(() -> NormalFrame.of(10))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("다음 프레임 확인")
    void next() {
        NormalFrame normalFrame = NormalFrame.first();
        assertThat(normalFrame.next()).isEqualTo(NormalFrame.of(1));
    }

    @Test
    @DisplayName("스트라이크 확인")
    void strikeTest() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(10);
        assertThat(normalFrame.getFallenPins()).isEqualTo("X");
    }

    @ParameterizedTest
    @CsvSource(value = {"4,6", "5,5", "9,1"})
    @DisplayName("스페어 확인")
    void strikeTest(int first, int second) {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(first);
        normalFrame.bowl(second);

        assertThat(normalFrame.getFallenPins()).isEqualTo(first +" | /");
    }

    @Test
    @DisplayName("투구가 3번 에러")
    void three_pitch() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(1);
        normalFrame.bowl(2);

        assertThatThrownBy( () -> normalFrame.bowl(2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("스트라이크 시 한번 더 투구 할 시 에러")
    void strike_one_more_pitch() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(10);
        assertThatThrownBy( () -> normalFrame.bowl(2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,5,6", "2,8,10", "1,1,2"})
    @DisplayName("점수 계산")
    void score(int first, int second, int expect) {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(first);
        normalFrame.bowl(second);
        assertThat(normalFrame.getScore()).isEqualTo(expect);
    }
}
