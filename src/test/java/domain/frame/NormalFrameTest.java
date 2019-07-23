package domain.frame;

import domain.Pins;
import domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {

    private NormalFrame normalFrame;
    private int frameNumber;

    @BeforeEach
    void setUp() {
        frameNumber = 1;
        FrameIndex index = FrameIndex.from(frameNumber);
        normalFrame = NormalFrame.of(index);
    }

    @Test
    void 다음프레임과_현재_상태를_가지는_프레임을_생성한다() {

        assertThat(normalFrame).isNotNull();
    }

    @Test
    void 프레임번호가_9가_아니면_다음_번호를_가진_NormalFrame을_생성한다() {
        //when
        NormalFrame generatedFrame = (NormalFrame) normalFrame.generateNextFrame();

        //then
        assertThat(generatedFrame.getIndex())
                .isEqualTo(FrameIndex.from(frameNumber + 1));
    }

    @Test
    void 프레임번호가_9인_경우_다음_프레임은_FinalFrame으로_생성한다() {
        //given
        frameNumber = 9;
        NormalFrame normalFrame = NormalFrame.of(FrameIndex.from(frameNumber));

        //when
        Frame generatedFrame = normalFrame.generateNextFrame();

        //then
        assertThat(generatedFrame instanceof FinalFrame).isTrue();
    }

    @Test
    void 준비_상태에서는_기본_점수를_반환한다() {
        //given
        //when
        Score score = normalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(0, 0));
    }

    @Test
    void 거터_처리를_할_경우_거터_점수를_반환한다() {
        //given
        //when
        normalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));
        normalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));
        Score score = normalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(0, 0));
    }

    @Test
    void 미쓰_처리를_할_경우_미쓰_점수를_반환한다() {
        //given
        //when
        normalFrame.fillFrame(Pins.from(5));
        normalFrame.fillFrame(Pins.from(4));
        Score score = normalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(9, 0));
    }

    @Test
    void 스페어_처리를_할_경우_스페어_점수를_반환한다() {
        //given
        //when
        normalFrame.fillFrame(Pins.from(5));
        normalFrame.fillFrame(Pins.from(5));
        Score score = normalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(10, 1));
    }

    @Test
    void 스트라이크_처리를_할_경우_스트라이크_점수를_반환한다() {
        //given
        //when
        normalFrame.fillFrame(Pins.from(Pins.STRIKE_PINS));
        Score score = normalFrame.getScore();

        //then
        assertThat(score).isEqualTo(Score.of(10, 2));
    }

    @Test
    void 스페어_처리_후_거터일_경우의_점수를_계산한다() {
        //given
        normalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));
        normalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));

        //when
        Score score = normalFrame.updateScore(Score.of(10, 1));

        //then
        assertThat(score).isEqualTo(Score.of(10, 0));
    }

    @ParameterizedTest
    @CsvSource({"0, 9", "0, 10", "9, 0", "5, 4"})
    void 스페어_처리_후에는_초구의_점수만_합산한다(int first, int second) {
        //given
        normalFrame.fillFrame(Pins.from(first));
        normalFrame.fillFrame(Pins.from(second));

        //when
        Score score = normalFrame.updateScore(Score.of(10, 1));

        //then
        assertThat(score).isEqualTo(Score.of(10 + first, 0));
    }

    @Test
    void 스페어_처리_후_스트라이크일_경우의_점수를_계산한다() {
        //given
        normalFrame.fillFrame(Pins.from(Pins.STRIKE_PINS));

        //when
        Score score = normalFrame.updateScore(Score.of(10, 1));

        //then
        assertThat(score).isEqualTo(Score.of(10 + Pins.STRIKE_PINS, 0));
    }

    @Test
    void 스트라이크_처리_후_거터일_경우의_점수를_계산한다() {
        //given
        normalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));
        normalFrame.fillFrame(Pins.from(Pins.GUTTER_PINS));

        //when
        Score score = normalFrame.updateScore(Score.of(10, 2));

        //then
        assertThat(score).isEqualTo(Score.of(10, 0));
    }

    @ParameterizedTest
    @CsvSource({"0, 9", "9, 0", "5, 4"})
    void 스트라이크_처리_후_미쓰일_경우_초구와_2구의_점수를_모두_합산한다(int first, int second) {
        //given
        normalFrame.fillFrame(Pins.from(first));
        normalFrame.fillFrame(Pins.from(second));

        //when
        Score score = normalFrame.updateScore(Score.of(10, 2));

        //then
        assertThat(score).isEqualTo(Score.of(10 + first + second, 0));
    }

    @ParameterizedTest
    @CsvSource({"0, 10", "1, 9"})
    void 스트라이크_처리_후_스페어일_경우의_점수를_계산한다(int first, int second) {
        //given
        normalFrame.fillFrame(Pins.from(first));
        normalFrame.fillFrame(Pins.from(second));

        //when
        Score score = normalFrame.updateScore(Score.of(10, 2));

        //then
        assertThat(score).isEqualTo(Score.of(10 + first + second, 0));
    }

    @Test
    void 스트라이크_처리_후_스트라이크일_경우의_점수를_계산한다() {
        //given
        normalFrame.fillFrame(Pins.from(Pins.STRIKE_PINS));

        //when
        Score score = normalFrame.updateScore(Score.of(10, 2));

        //then
        assertThat(score).isEqualTo(Score.of(10 + Pins.STRIKE_PINS, 1));
    }

    @ParameterizedTest
    @CsvSource({"0, 9", "9, 0", "5, 4"})
    void 연속_스트라이크_처리_후에는_초구의_점수만_합산한다(int first, int second) {
        //given
        normalFrame.fillFrame(Pins.from(first));
        normalFrame.fillFrame(Pins.from(second));

        //when
        Score score = normalFrame.updateScore(Score.of(20, 1));

        //then
        assertThat(score).isEqualTo(Score.of(20 + first, 0));
    }
}
