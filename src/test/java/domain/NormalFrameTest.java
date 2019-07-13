package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class NormalFrameTest {

    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        int frameNumber = 1;
        List<Pins> roundResult = new ArrayList<>();
        normalFrame = NormalFrame.of(frameNumber, roundResult);
    }

    @Test
    void 프레임번호와_라운드결과와_다음프레임을_필드로_갖는_프레임을_생성한다() {

        //then
        assertThat(normalFrame.isMatch(normalFrame)).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"0, 7", "1, 2"})
    void 투구결과를_입력받아_라운드결과를_생성한다(int index, int fallenPins) {

        //given
        Pins firstFallenPins = Pins.from(7);
        Pins secondFallenPins = Pins.from(2);

        //when
        normalFrame.fillFrame(firstFallenPins);
        normalFrame.fillFrame(secondFallenPins);
        Pins expectedResult = Pins.from(fallenPins);

        //then
        assertThat(normalFrame.getRoundResult().get(index)).isEqualTo(expectedResult);
    }

    @Test
    void 현재_프레임에_투구결과가_2개있다면_다음_프레임에_투구결과를_저장한다() {
        //given
        Pins firstFallenPins = Pins.from(7);
        Pins secondFallenPins = Pins.from(2);
        Pins thirdFallenPins = Pins.from(2);

        //when
        normalFrame.fillFrame(firstFallenPins);
        normalFrame.fillFrame(secondFallenPins);
        NormalFrame nextNormalFrame = normalFrame.fillFrame(thirdFallenPins);

        //then
        assertAll(
                () -> assertThat(normalFrame.getRoundResult().size()).isEqualTo(2),
                () -> assertThat(nextNormalFrame.getRoundResult().get(0)).isEqualTo(thirdFallenPins)
        );
    }

    @Test
    void 현재_프레임의_투구결과가_스트라이크면_다음_프레임에_투구결과를_저장한다() {
        //given
        Pins firstFallenPins = Pins.from(10);
        Pins secondFallenPins = Pins.from(2);

        //when
        normalFrame.fillFrame(firstFallenPins);
        NormalFrame nextNormalFrame = normalFrame.fillFrame(secondFallenPins);

        //then
        assertAll(
                () -> assertThat(normalFrame.getRoundResult().size()).isEqualTo(1),
                () -> assertThat(nextNormalFrame.getRoundResult().get(0)).isEqualTo(secondFallenPins)
        );
    }

    @Test
    void 다음_프레임을_생성하면_프레임번호가_1만큼_증가한다() {
        //given
        Pins firstFallenPins = Pins.from(10);
        Pins secondFallenPins = Pins.from(2);

        //when
        normalFrame.fillFrame(firstFallenPins);
        NormalFrame nextNormalFrame = normalFrame.fillFrame(secondFallenPins);

        //then
        assertThat(nextNormalFrame.getFrameNumber()).isEqualTo(normalFrame.getFrameNumber() + 1);
    }
}
