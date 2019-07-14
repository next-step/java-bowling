package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FinalFrameTest {

    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        List<Pins> roundResult = new ArrayList<>();
        finalFrame = FinalFrame.from(roundResult);
    }

    @Test
    void 라운드결과를_갖는_마지막_프레임을_생성한다() {

        //then
        assertThat(finalFrame.isMatch(finalFrame)).isTrue();
    }

    @Test
    void 라운드결과가_비어있으면_입력받은_투구결과를_라운드결과에_추가한다() {
        //given
        Pins firstFallenPins = Pins.from(7);

        //when
        finalFrame.fillFrame(firstFallenPins);

        //then
        assertThat(finalFrame.getRoundResult().get(FinalFrame.FIRST_TRIAL))
                .isEqualTo(firstFallenPins);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9, 10})
    void 초구에_스트라이크를_치면_세번째_투구를_할_수_있다(int fallenPins) {
        //given
        Pins strikePins = Pins.from(FinalFrame.STRIKE_PINS);
        Pins secondFallenPins = Pins.from(fallenPins);

        //when
        finalFrame.fillFrame(strikePins);
        finalFrame.fillFrame(secondFallenPins);

        //then
        assertThat(finalFrame.bonusBowlable()).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"0, 10", "9, 1"})
    void 두번째_투구에_스페어_처리를_하면_세번째_투구를_할_수_있다(int firstFallenPins, int secondFallenPins) {
        //given
        Pins firstPins = Pins.from(firstFallenPins);
        Pins sparePins = Pins.from(secondFallenPins);

        //when
        finalFrame.fillFrame(firstPins);
        finalFrame.fillFrame(sparePins);

        //then
        assertThat(finalFrame.bonusBowlable()).isTrue();
    }

    @Test
    void 두번쨰_투구에서_게임이_종료되었는데_추가로_투구결과를_입력하면_예외가_발생한다() {
        //given
        Pins firstPins = Pins.from(1);
        Pins secondPins = Pins.from(2);
        Pins illegalPins = Pins.from(3);
        finalFrame.fillFrame(firstPins);
        finalFrame.fillFrame(secondPins);

        //when
        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    finalFrame.fillFrame(illegalPins);
                }).withMessage(FinalFrame.ALERT_END_OF_GAME);
    }

    @ParameterizedTest
    @CsvSource({"10, 0, 0", "10, 10, 10", "0, 10, 10", "0, 10, 0"})
    void 세번째_투구에서_게임이_종료되었는데_추가로_투구결과를_입력하면_예외가_발생한다(int first, int second, int third) {
        //given
        Pins firstPins = Pins.from(first);
        Pins secondPins = Pins.from(second);
        Pins thirdPins = Pins.from(third);
        Pins illegalPins = Pins.from(4);
        finalFrame.fillFrame(firstPins);
        finalFrame.fillFrame(secondPins);
        finalFrame.fillFrame(thirdPins);

        //when
        //then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    finalFrame.fillFrame(illegalPins);
                }).withMessage(FinalFrame.ALERT_END_OF_GAME);
    }
}
