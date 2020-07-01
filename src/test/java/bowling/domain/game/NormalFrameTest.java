package bowling.domain.game;

import bowling.exception.game.CanNotAccessMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {
    @ParameterizedTest
    @ValueSource(ints = 10)
    @DisplayName("첫 번째 프레임에서 STRIKE 인 경우 다음 프레임을 반환한다.")
    void strike(int countOfPins) {
        NormalFrame normalFrame = NormalFrame.init();

        assertThat(normalFrame.next(countOfPins))
                .isEqualTo(NormalFrame.initBy(FrameNumber.of(2)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("첫 번째 프레임에서 STRIKE 가 아닌 경우 현재 객체를 반환한다.")
    void not_strike(int countOfPins) {
        NormalFrame normalFrame = NormalFrame.init();

        assertThat(normalFrame.next(countOfPins))
                .isEqualTo(normalFrame);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("첫 번째 프레임에서 STRIKE 가 아닌 경우 next 메소드를 두번 호출하면 다음 객체를 반환한다.")
    void not_strike_next(int countOfPins) {
        NormalFrame normalFrame = NormalFrame.init();

        assertThat(normalFrame.next(countOfPins).next(countOfPins))
                .isEqualTo(NormalFrame.initBy(FrameNumber.of(2)));
    }

    @ParameterizedTest
    @ValueSource(ints = 10)
    @DisplayName("9 번째 프레임에서 STRIKE 인 경우 FinalFrame 을 반환한다.")
    void nine_frame(int countOfPins) {
        NormalFrame normalFrame = NormalFrame.initBy(FrameNumber.of(9));
        assertThat(normalFrame.next(countOfPins)).isEqualTo(FinalFrame.init());
    }

    @ParameterizedTest
    @CsvSource({"10, 1, 4"})
    @DisplayName("STRIKE 인 경우 다음 투구 시 bonus 볼에 저장된 후 next 메소드 호출 시 예외를 반환한다.")
    void strike_and_bonus_exception(int firstPins, int bonusPins, int errorPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThatThrownBy(() -> finalFrame.next(firstPins).next(bonusPins).next(errorPins))
                .isInstanceOf(CanNotAccessMethod.class);
    }
}