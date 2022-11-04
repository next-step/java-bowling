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
        frame = new NormalFrame();
    }

    @DisplayName("스트라이크인 경우 한 번 더 투구할 수 없다.")
    @Test
    void bowlException() {
        frame.bowl(10);

        assertThat(frame.canBowl()).isFalse();
    }

    @DisplayName("스패어 상태에서 한 번 더 투구할 수 없다.")
    @Test
    void bowlSpareException() {
        frame.bowl(7);
        frame.bowl(3);

        assertThat(frame.canBowl()).isFalse();
    }

    @DisplayName("투구가 끝난 상황에서 한 번 더 투구할 수 없다.")
    @Test
    void bowlMissException() {
        frame.bowl(5);
        frame.bowl(3);

        assertThat(frame.canBowl()).isFalse();
    }

    @DisplayName("프레임의 번호는 0번 또는 10번이 될 수 없다.")
    @ParameterizedTest(name = "{displayName} 입력값={0}")
    @ValueSource(ints = {0, 10})
    void normalFrameException(int input) {
        assertThatThrownBy(() -> new NormalFrame(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("프레임의 번호는 0번 또는 10번이 될 수 없다.")
    @ParameterizedTest(name = "{displayName} 입력값={0}")
    @ValueSource(ints = {0, 10})
    void finalFrameException(int input) {
        assertThatThrownBy(() -> new NormalFrame(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("10개를 쓰러뜨린 경우 프레임의 상태는 스트라이크다.")
    @Test
    void state() {
        frame.bowl(10);

        assertThat(frame.getState()).isInstanceOf(Strike.class);
    }

    @DisplayName("준비 상태인 경우 다음 프레임은 없다.")
    @Test
    void readyNextFrame() {
        Frame nextFrame = frame.getNextFrame();

        assertThat(nextFrame).isNull();
    }

    @DisplayName("스트라이크인 경우 다음 프레임이 생성된다.")
    @Test
    void strikeNextFrame() {
        frame.bowl(10);

        Frame nextFrame = frame.getNextFrame();

        assertThat(nextFrame).isNotNull();
    }

    @DisplayName("스패어인 경우 다음 프레임이 생성된다.")
    @Test
    void spareNextFrame() {
        frame.bowl(0);
        frame.bowl(10);

        Frame nextFrame = frame.getNextFrame();

        assertThat(nextFrame).isNotNull();
    }

    @DisplayName("미스인 경우 다음 프레임이 생성된다.")
    @Test
    void missNextFrame() {
        frame.bowl(0);
        frame.bowl(10);

        Frame nextFrame = frame.getNextFrame();

        assertThat(nextFrame).isNotNull();
    }

    @ParameterizedTest(name = "[MISS] 첫 번째 프레임에서 {0}|{1} 인 경우 점수는 {2}이다.")
    @CsvSource({
            "0,0,0",
            "1,2,3",
            "0,9,9"
    })
    void missScore(int firstPinCount, int secondPinCount, int expected) {
        frame.bowl(firstPinCount);
        frame.bowl(secondPinCount);

        assertThat(frame.getIntScore()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "첫 번째 프레임에서 스트라이크이고 다음 프레임이 MISS {0}|{1} 인 경우 첫 번째 프레임의 점수는 {2}이고 두 번째 프레임의 점수는 {3}이다.")
    @CsvSource({
            "0,5,15,5",
            "1,2,13,3",
            "0,9,19,9"
    })
    void score1(int firstPin, int secondPin, int firstExpected, int secondExpected) {
        frame.bowl(10);
        Frame nextFrame = frame.getNextFrame();

        nextFrame.bowl(firstPin);
        nextFrame.bowl(secondPin);

        assertAll(
                () -> assertEquals(firstExpected, frame.getIntScore()),
                () -> assertEquals(secondExpected, nextFrame.getIntScore())
        );
    }

    @ParameterizedTest(name = "첫 번째 프레임에서 스트라이크이고 다음 SPARE {0}|{1} 인 경우 첫 번째 프레임의 점수는 {2}이다.")
    @CsvSource({
            "8,2,20"
    })
    void score2(int firstPin, int secondPin, int expected) {
        frame.bowl(10);
        Frame nextFrame = frame.getNextFrame();

        nextFrame.bowl(firstPin);
        nextFrame.bowl(secondPin);

        assertThat(frame.getIntScore()).isEqualTo(expected);
    }

    @DisplayName("X, 8|/, 8 인 경우")
    @Test
    void score3() {
        frame.bowl(10);
        Frame second = frame.getNextFrame();

        second.bowl(8);
        second.bowl(2);

        Frame third = second.getNextFrame();
        third.bowl(8);

        assertThat(frame.getIntScore()).isEqualTo(20);
        assertThat(second.getIntScore()).isEqualTo(18);
    }

    @DisplayName("X, 8|/, 8|1 인 경우")
    @Test
    void score4() {
        frame.bowl(10);
        Frame second = frame.getNextFrame();

        second.bowl(8);
        second.bowl(2);

        Frame third = second.getNextFrame();
        third.bowl(8);
        third.bowl(1);

        assertThat(frame.getIntScore()).isEqualTo(20);
        assertThat(second.getIntScore()).isEqualTo(18);
        assertThat(third.getIntScore()).isEqualTo(9);
    }

    @DisplayName("X, X, 5| 인 경우")
    @Test
    void score5() {
        frame.bowl(10);
        Frame second = frame.getNextFrame();

        second.bowl(10);

        Frame third = second.getNextFrame();
        third.bowl(5);

        assertThat(frame.getIntScore()).isEqualTo(25);
    }

    @DisplayName("X, X, 5|4 인 경우")
    @Test
    void score6() {
        frame.bowl(10);
        Frame second = frame.getNextFrame();

        second.bowl(10);

        Frame third = second.getNextFrame();
        third.bowl(5);
        third.bowl(4);

        assertThat(frame.getIntScore()).isEqualTo(25);
        assertThat(second.getIntScore()).isEqualTo(19);
        assertThat(third.getIntScore()).isEqualTo(9);
    }
}
