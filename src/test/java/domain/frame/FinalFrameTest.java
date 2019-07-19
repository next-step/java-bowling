package domain.frame;

import domain.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FinalFrameTest {

    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = FinalFrame.of();
    }

    @ParameterizedTest
    @CsvSource({"10, 10", "10, 0", "10, 5"})
    void 첫번째_투구결과가_STRIKE인_경우_3구를_투구할_수_있다(int firstPins, int secondPins) {
        //given
        Pins first = Pins.from(firstPins);
        Pins second = Pins.from(secondPins);

        //when
        finalFrame.fillFrame(first).fillFrame(second);

        //then
        assertThat(finalFrame.isGameOver()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"0, 10", "5, 5"})
    void 두번째_투구결과가_SPARE인_경우_3구를_투구할_수_있다(int firstPins, int secondPins) {
        //given
        Pins first = Pins.from(firstPins);
        Pins second = Pins.from(secondPins);

        //when
        finalFrame.fillFrame(first).fillFrame(second);

        //then
        assertThat(finalFrame.isGameOver()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"0, 9", "5, 4", "9, 0"})
    void 두번째_투구결과가_MISS인_경우_3구를_투구할_수_없다(int firstPins, int secondPins) {
        //given
        Pins first = Pins.from(firstPins);
        Pins second = Pins.from(secondPins);

        //when
        finalFrame.fillFrame(first).fillFrame(second);

        //then
        assertThat(finalFrame.isGameOver()).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"0, 10, 10, 10", "10, 0, 0, 0",})
    void 게임종료_이후_투구하면_예외가_발생한다(int firstPins, int secondPins, int thirdPins, int fourthPins) {
        //given
        Pins first = Pins.from(firstPins);
        Pins second = Pins.from(secondPins);
        Pins third = Pins.from(thirdPins);
        Pins fourth = Pins.from(fourthPins);

        //when
        //then
        assertThatExceptionOfType(GameOverException.class)
                .isThrownBy(() -> {
                    finalFrame
                            .fillFrame(first)
                            .fillFrame(second)
                            .fillFrame(third)
                            .fillFrame(fourth);
                });
    }
}