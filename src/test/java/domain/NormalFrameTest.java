package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
}
