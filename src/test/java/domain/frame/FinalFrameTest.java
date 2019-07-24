package domain.frame;

import domain.Pins;
import domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static domain.frame.FrameResult.UNFINISHED_SCORE;
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

    @Test
    void 거터_처리를_할_경우_UNFINISHED_SCORE를_반환한다() {
        //given
        finalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));
        finalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(UNFINISHED_SCORE, 0));
    }

    @ParameterizedTest
    @CsvSource({"0, 1", "5, 4", "9, 0"})
    void 미쓰_처리를_할_경우_UNFINISHED_SCORE를_반환한다(int first, int second) {
        //given
        finalFrame.fillFrame(Pins.from(first));
        finalFrame.fillFrame(Pins.from(second));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(UNFINISHED_SCORE, 0));
    }

    @ParameterizedTest
    @CsvSource({"0, 10", "5, 5"})
    void 스페어와_거터_처리를_할_경우의_점수를_반환한다(int first, int second) {
        //given
        finalFrame.fillFrame(Pins.from(first));
        finalFrame.fillFrame(Pins.from(second));
        finalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(first + second, 0));
    }

    @ParameterizedTest
    @CsvSource({"0, 10, 5", "5, 5, 5"})
    void 스페어와_미쓰_처리를_할_경우의_점수를_반환한다(int first, int second, int third) {
        //given
        finalFrame.fillFrame(Pins.from(first));
        finalFrame.fillFrame(Pins.from(second));
        finalFrame.fillFrame(Pins.from(third));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(first + second + third, 0));
    }

    @ParameterizedTest
    @CsvSource({"0, 10, 10", "5, 5, 10"})
    void 스페어와_스트라이크_처리를_할_경우의_점수를_반환한다(int first, int second, int third) {
        //given
        finalFrame.fillFrame(Pins.from(first));
        finalFrame.fillFrame(Pins.from(second));
        finalFrame.fillFrame(Pins.from(third));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(first + second + third, 0));
    }

    @ParameterizedTest
    @CsvSource({"10, 10, 10"})
    void 스트라이크_세_번_처리를_할_경우의_점수를_반환한다(int first, int second, int third) {
        //given
        finalFrame.fillFrame(Pins.from(first));
        finalFrame.fillFrame(Pins.from(second));
        finalFrame.fillFrame(Pins.from(third));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(first + second + third, 0));
    }

    @ParameterizedTest
    @CsvSource({"10, 0, 10", "10, 5, 5"})
    void 스트라이크와_스페어를_할_경우의_점수를_반환한다(int first, int second, int third) {
        //given
        finalFrame.fillFrame(Pins.from(first));
        finalFrame.fillFrame(Pins.from(second));
        finalFrame.fillFrame(Pins.from(third));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(first + second + third, 0));
    }

    @ParameterizedTest
    @CsvSource({"10, 0, 0"})
    void 스트라이크와_거터_할_경우의_점수를_반환한다(int first, int second, int third) {
        //given
        finalFrame.fillFrame(Pins.from(first));
        finalFrame.fillFrame(Pins.from(second));
        finalFrame.fillFrame(Pins.from(third));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(first + second + third, 0));
    }

    @ParameterizedTest
    @CsvSource({"10, 0, 1", "10, 1, 0", "10, 1, 1"})
    void 스트라이크와_미쓰_할_경우의_점수를_반환한다(int first, int second, int third) {
        //given
        finalFrame.fillFrame(Pins.from(first));
        finalFrame.fillFrame(Pins.from(second));
        finalFrame.fillFrame(Pins.from(third));

        //when
        Score score = finalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(first + second + third, 0));
    }
}