package bowling.domain;

import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("일반 프레임 테스트")
class NormalFrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {

        frame = NormalFrame.ready();
    }

    @DisplayName("스트라이크인 경우 한 번 더 투구할 수 없다.")
    @Test
    void strike_bowl() {

        frame.bowl(10);

        assertThat(frame.canBowl()).isFalse();
    }

    @DisplayName("스패어 상태에서 한 번 더 투구할 수 없다.")
    @Test
    void spare_bowl() {

        frame.bowl(7);
        frame.bowl(3);

        assertThat(frame.canBowl()).isFalse();
    }

    @DisplayName("투구가 끝난 상황에서 한 번 더 투구할 수 없다.")
    @Test
    void pitch_end() {

        frame.bowl(5);
        frame.bowl(3);

        assertThat(frame.canBowl()).isFalse();
    }

    @DisplayName("프레임의 번호는 0번 또는 10번이 될 수 없다.")
    @ParameterizedTest(name = "{displayName} 입력값={0}")
    @ValueSource(ints = {0, 10})
    void normalFrameException(int input) {
        assertThatThrownBy(() -> NormalFrame.next(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("10개를 쓰러뜨린 경우 프레임의 상태는 스트라이크다.")
    @Test
    void state() {

        frame.bowl(10);

        assertThat(frame.getState()).isInstanceOf(Strike.class);
    }

    @DisplayName("Strike 인 경우 다음 프레임이 생성된다.")
    @Test
    void strike_next_frame() {

        frame.bowl(10);

        final Frame nextFrame = frame.getNextFrame();

        assertThat(nextFrame).isNotNull();
    }

    @DisplayName("Spare 인 경우 다음 프레임이 생성된다.")
    @Test
    void spare_next_frame() {

        frame.bowl(0);
        frame.bowl(10);

        final Frame nextFrame = frame.getNextFrame();

        assertThat(nextFrame).isNotNull();
    }

    @DisplayName("Miss 인 경우 다음 프레임이 생성된다.")
    @Test
    void miss_next_frame() {

        frame.bowl(0);
        frame.bowl(10);

        final Frame nextFrame = frame.getNextFrame();

        assertThat(nextFrame).isNotNull();
    }

    @ParameterizedTest(name = "[MISS] 첫 번째 프레임에서 {0}|{1} 인 경우 점수는 {2}이다.")
    @CsvSource({"0,0,0", "1,2,3", "0,9,9"})
    void missScore(final int first,final int second, final int expected) {

        frame.bowl(first);
        frame.bowl(second);

        assertThat(frame.getIntScore()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "첫 번째 프레임에서 Strike 이고 다음 Spare {0}|{1} 인 경우 첫 번째 프레임의 점수는 {2}이다.")
    @CsvSource({"8,2,20"})
    void score_calc(final int first, final int second, final int expected) {

        frame.bowl(10);
        final Frame nextFrame = frame.getNextFrame();
        nextFrame.bowl(first);
        nextFrame.bowl(second);

        assertThat(frame.getIntScore()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "첫 번째 프레임에서 Strike 이고 다음 프레임이 Miss {0}|{1} 인 경우 첫 번째 프레임의 점수는 {2}이고 두 번째 프레임의 점수는 {3}이다.")
    @CsvSource({"0,5,15,5", "1,2,13,3", "0,9,19,9"})
    void score_calc2(final int first, final int second, final int firstExpected, final int secondExpected) {

        frame.bowl(10);
        final Frame nextFrame = frame.getNextFrame();
        nextFrame.bowl(first);
        nextFrame.bowl(second);

        assertAll(
                () -> assertEquals(firstExpected, frame.getIntScore()),
                () -> assertEquals(secondExpected, nextFrame.getIntScore())
        );
    }
}
