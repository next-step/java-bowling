package bowling.domain.game;

import bowling.domain.pin.FirstFramePins;
import bowling.domain.pin.Pins;
import bowling.domain.pin.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    @ParameterizedTest
    @ValueSource(ints = 10)
    @DisplayName("첫 번째 프레임에서 STRIKE 인 경우 현재 프레임을 반환한다.")
    void strike(int countOfPins) {
        NormalFrame normalFrame = NormalFrame.init();

        assertThat(normalFrame.next(countOfPins))
                .isEqualTo(normalFrame);
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
    @DisplayName("첫 번째 프레임에서 STRIKE 가 아닌 경우 next 메소드를 3번 호출하면 다음 객체를 반환한다.")
    void not_strike_next(int countOfPins) {
        NormalFrame normalFrame = NormalFrame.init();

        assertThat(normalFrame.next(countOfPins).next(countOfPins).next(countOfPins))
                .isEqualTo(new NormalFrame(FrameNumber.of(2), FirstFramePins.of(Pins.of(countOfPins))));
    }

    @ParameterizedTest
    @ValueSource(ints = 10)
    @DisplayName("9 번째 프레임에서 STRIKE 인 경우 next 메소드 호출 시 FinalFrame 을 반환한다.")
    void nine_frame(int countOfPins) {
        NormalFrame normalFrame = NormalFrame.initBy(FrameNumber.of(9));
        assertThat(normalFrame.next(countOfPins).next(countOfPins))
                .isEqualTo(FinalFrame.init(Strike.of(Pins.of(countOfPins))));
    }

    @ParameterizedTest
    @CsvSource({"10"})
    @DisplayName("STRIKE 인 경우 canBowling 메소드 호출 시 false 를 반환한다.")
    void strike_can_bowling(int firstPins) {
        NormalFrame normalFrame = NormalFrame.init();
        assertThat(normalFrame.next(firstPins).canBowling()).isEqualTo(false);
    }
}