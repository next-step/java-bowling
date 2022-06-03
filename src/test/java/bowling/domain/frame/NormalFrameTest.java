package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NormalFrameTest {
    @DisplayName("노멀 프레임을 생성한다.")
    @Test
    void NormalFrame_생성() {
        NormalFrame normalFrame = new NormalFrame(new BeforeProgress(), new FrameNumber(0));
        assertThat(normalFrame).isNotNull().isInstanceOf(NormalFrame.class);
    }

    @DisplayName("노멀 프레임을 생성 시, 프레임 상태가 null 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void NormalFrame_프레임_상태_null_인_경우(FrameState frameState) {
        assertThatThrownBy(() -> new NormalFrame(frameState, new FrameNumber(0))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("노멀 프레임을 생성 시, 프레임 넘버가 null 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void NormalFrame_프레임_넘버_null_인_경우(FrameNumber frameNumber) {
        assertThatThrownBy(() -> new NormalFrame(new BeforeProgress(), frameNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("노멀 프레임을 초기화 한다.")
    @Test
    void initialize_노멀프레임_초기화() {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        NormalFrame expectedNormalFrame = new NormalFrame(new BeforeProgress(), new FrameNumber(0));
        assertThat(initialNormalFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("노멀 프레임, 첫번째 투구에서 스트라이크 하지 못한 경우 현재 프레임 상태는 'FirstBowl' 이 되고, 프레임 넘버는 현재 값을 유지한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 8, 9})
    void bowl_첫번째_투구_NON_STRIKE(int hitPins) {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(hitPins));
        NormalFrame expectedNormalFrame = new NormalFrame(new FirstBowl(new Pins(hitPins)), new FrameNumber(0));
        assertThat(initialNormalFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("노멀 프레임, 첫번째 투구에서 스트라이크한 경우 현재 프레임 상태는 'STRIKE' 가 되고, 현재 프레임 넘버는 현재 값을 유지한다.")
    @Test
    void bowl_첫번째_투구_STRIKE() {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(10));
        NormalFrame expectedNormalFrame = new NormalFrame(new Strike(), new FrameNumber(0));
        assertThat(initialNormalFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("노멀 프레임, 첫번째 투구에서 스트라이크한 경우 다음 프레임 상태 'BeforeProgress' 프레임을 새롭게 반환하고, 프레임 넘버는 1 증가한다.")
    @Test
    void bowl_첫번째_투구_STRIKE_다음프레임_반환() {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        NormalFrame resultFrame = (NormalFrame) initialNormalFrame.bowl(new Pins(10));
        NormalFrame expectedNormalFrame = new NormalFrame(new BeforeProgress(), new FrameNumber(1));
        assertThat(resultFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("노멀 프레임, 두번째 투구에서 스페어 처리한 경우 현재 프레임 상태는 'SPARE' 가 되고, 현재 프레임 넘버는 현재 값을 유지한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1, 9",
            "3, 7",
            "5, 5",
            "0, 10"
    })
    void bowl_두번째_투구_SPARE(int firstPins, int secondPins) {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(firstPins));
        initialNormalFrame.bowl(new Pins(secondPins));
        NormalFrame expectedNormalFrame = new NormalFrame(new Spare(new Pins(firstPins)), new FrameNumber(0));
        assertThat(initialNormalFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("노멀 프레임, 두번째 투구에서 스페어 처리한 경우 다음 프레임 상태는 'BeforeProgress' 프레임을 새롭게 반환하고, 프레임 넘버는 1 증가한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1, 9",
            "3, 7",
            "5, 5",
            "0, 10"
    })
    void bowl_두번째_투구_SPARE_다음프레임_반환(int firstPins, int secondPins) {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(firstPins));
        NormalFrame resultFrame = (NormalFrame) initialNormalFrame.bowl(new Pins(secondPins));
        NormalFrame expectedNormalFrame = new NormalFrame(new BeforeProgress(), new FrameNumber(1));
        assertThat(resultFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("9번 프레임이 종료되고 다음 프레임으로 넘어갈 때 파이널 프레임을 새롭게 반환한다.")
    @Test
    void bowl_두번째_투구_SPARE_마지막프레임_반환() {
        Frame normalFrame = NormalFrame.create(new FrameNumber(8));
        Frame finalFrame = normalFrame.bowl(new Pins(10));
        assertThat(finalFrame).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("해당 프레임이 종료됬는지 확인")
    @ParameterizedTest
    @MethodSource("isFrameEnd_frameStateProvider")
    void isFrameEnd_프레임_종료_체크(NormalFrame normalFrame, boolean trueOrFalse) {
        assertThat(normalFrame.isFrameEnd()).isEqualTo(trueOrFalse);
    }

    @DisplayName("해당 프레임 상태에 따른 기호를 확인한다.")
    @ParameterizedTest
    @MethodSource("symbol_frameStateProvider")
    void symbol_프레임상태_기호_체크(NormalFrame normalFrame, String symbol) {
        assertThat(normalFrame.symbol()).isEqualTo(symbol);
    }

    static Stream<Arguments> isFrameEnd_frameStateProvider() {
        return Stream.of(
                arguments(new NormalFrame(new BeforeProgress(), new FrameNumber(0)), false),
                arguments(new NormalFrame(new FirstBowl(new Pins(5)), new FrameNumber(0)), false),
                arguments(new NormalFrame(new Miss(new Pins(4), new Pins(3)), new FrameNumber(0)), true),
                arguments(new NormalFrame(new Spare(new Pins(8)), new FrameNumber(0)), true),
                arguments(new NormalFrame(new Strike(), new FrameNumber(0)), true)
        );
    }

    static Stream<Arguments> symbol_frameStateProvider() {
        return Stream.of(
                arguments(new NormalFrame(new BeforeProgress(), new FrameNumber(0)), ""),
                arguments(new NormalFrame(new FirstBowl(new Pins(5)), new FrameNumber(0)), "5"),
                arguments(new NormalFrame(new FirstBowl(new Pins(0)), new FrameNumber(0)), "-"),
                arguments(new NormalFrame(new Miss(new Pins(4), new Pins(3)), new FrameNumber(0)), "4|3"),
                arguments(new NormalFrame(new Miss(new Pins(4), new Pins(0)), new FrameNumber(0)), "4|-"),
                arguments(new NormalFrame(new Miss(new Pins(0), new Pins(3)), new FrameNumber(0)), "-|3"),
                arguments(new NormalFrame(new Miss(new Pins(0), new Pins(0)), new FrameNumber(0)), "-|-"),
                arguments(new NormalFrame(new Spare(new Pins(0)), new FrameNumber(0)), "-|/"),
                arguments(new NormalFrame(new Spare(new Pins(2)), new FrameNumber(0)), "2|/"),
                arguments(new NormalFrame(new Strike(), new FrameNumber(0)), "X")
        );
    }

    @DisplayName("해당 프레임이 파이널 프레임이 아닌 것을 확인")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 7, 8})
    void isFinalFrame_false(int number) {
        FrameNumber frameNumber = new FrameNumber(number);
        assertThat(NormalFrame.create(frameNumber).isFinalFrame()).isFalse();
    }

    @DisplayName("해당 프레임 상태의 점수를 확인한다.")
    @ParameterizedTest
    @MethodSource("score_frameStateProvider")
    void score_프레임상태_점수_체크(NormalFrame normalFrame, Score resultScore) {
        assertThat(normalFrame.score()).isEqualTo(resultScore);
    }

    static Stream<Arguments> score_frameStateProvider() {
        return Stream.of(
                arguments(new NormalFrame(new BeforeProgress(), new FrameNumber(0)), Score.init()),
                arguments(new NormalFrame(new FirstBowl(new Pins(5)), new FrameNumber(0)), new Score(5, 0)),
                arguments(new NormalFrame(new Miss(new Pins(4), new Pins(3)), new FrameNumber(0)), new Score(7, 0)),
                arguments(new NormalFrame(new Spare(new Pins(8)), new FrameNumber(0)), new Score(10, 1)),
                arguments(new NormalFrame(new Strike(), new FrameNumber(0)), new Score(10, 2))
        );
    }

    @DisplayName("이전 프레임의 점수를 현재 점수와 합하여 확인한다.")
    @ParameterizedTest
    @MethodSource("calculateAdditionalScore_frameStateProvider")
    void calculateAdditionalScore_프레임상태_이전점수합_체크(Score previousScore, NormalFrame normalFrame, Score resultScore) {
        assertThat(normalFrame.calculateAdditionalScore(previousScore));
    }

    static Stream<Arguments> calculateAdditionalScore_frameStateProvider() {
        return Stream.of(
                arguments(new Score(10, 2) , new NormalFrame(new BeforeProgress(), new FrameNumber(0)), Score.init()),
                arguments(new Score(10, 2) , new NormalFrame(new FirstBowl(new Pins(5)), new FrameNumber(0)), new Score(15, 1)),
                arguments(new Score(10, 1) , new NormalFrame(new Miss(new Pins(4), new Pins(3)), new FrameNumber(0)), new Score(14, 0)),
                arguments(new Score(10, 1) , new NormalFrame(new Spare(new Pins(8)), new FrameNumber(0)), new Score(18, 0)),
                arguments(new Score(10, 1) , new NormalFrame(new Strike(), new FrameNumber(0)), new Score(20, 0))
        );
    }
}