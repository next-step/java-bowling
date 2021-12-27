package bowling.domain.value;

import bowling.domain.factory.FrameFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FramesTest {

    private Frames frames;

    @BeforeEach
    void setup() {
        FrameFactory frameFactory = new FrameFactory();

        frames = Frames.from(frameFactory.create());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "   4|      5",
            "   4|      0"
    }, delimiter = '|')
    @DisplayName("마지막 프레임에서 미스/거터가 발생되는 경우 정상 종료 확인")
    void isGameOver(int firstPitch, int secondPitch) {
        testNormalFrame();

        assertThat(frames.isGameOver()).isFalse();

        frames.bowl(new Pins(firstPitch));
        frames.bowl(new Pins(secondPitch));

        assertThat(frames.isGameOver()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "   4|      6|      4",
            "   5|      5|     10"
    }, delimiter = '|')
    @DisplayName("마지막 프레임에서 투구수 3회 발생되는 경우(스페어, 스트라이크) 정상 종료 확인")
    void isGameOver2(int firstPitch, int secondPitch, int bonusPitch) {
        testNormalFrame();

        assertThat(frames.isGameOver()).isFalse();

        frames.bowl(new Pins(firstPitch));
        frames.bowl(new Pins(secondPitch));
        frames.bowl(new Pins(bonusPitch));

        assertThat(frames.isGameOver()).isTrue();
    }

    private void testNormalFrame() {
        for (int i = 1; i < 10; i++) {
            frames.bowl(new Pins(10));
        }
    }

    @Test
    @DisplayName("기본 프레임에서 투구의 합이 10핀이 넘어가는 경우 예외 발생")
    void knockedDown_exception() {
        frames.bowl(new Pins(4));
        assertThatIllegalArgumentException().isThrownBy(() -> frames.bowl(new Pins(7)));
    }

    @Test
    @DisplayName("마지막 프레임에서 두번째 투구의 합이 10핀이 넘어가는 경우 예외 발생")
    void knockedDown_exception2() {
        testNormalFrame();

        frames.bowl(new Pins(4));
        assertThatIllegalArgumentException().isThrownBy(() -> frames.bowl(new Pins(7)));
    }

}
