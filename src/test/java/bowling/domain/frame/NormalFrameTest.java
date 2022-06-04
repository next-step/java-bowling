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
        NormalFrame normalFrame = new NormalFrame(new BeforeProgress());
        assertThat(normalFrame).isNotNull().isInstanceOf(NormalFrame.class);
    }

    @DisplayName("노멀 프레임을 생성 시, 프레임 상태가 null 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void NormalFrame_프레임_상태_null_인_경우(FrameState frameState) {
        assertThatThrownBy(() -> new NormalFrame(frameState)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("노멀 프레임을 초기화 한다.")
    @Test
    void initialize_노멀프레임_초기화() {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        NormalFrame expectedNormalFrame = new NormalFrame(new BeforeProgress());
        assertThat(initialNormalFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("노멀 프레임, 첫번째 투구에서 스트라이크 하지 못한 경우 현재 프레임 상태는 'FirstBowl' 이 된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 8, 9})
    void bowl_첫번째_투구_NON_STRIKE(int hitPins) {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(hitPins));
        NormalFrame expectedNormalFrame = new NormalFrame(new FirstBowl(new Pins(hitPins)));
        assertThat(initialNormalFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("노멀 프레임, 첫번째 투구에서 스트라이크한 경우 현재 프레임 상태는 'STRIKE' 가 된다.")
    @Test
    void bowl_첫번째_투구_STRIKE() {
        NormalFrame initialNormalFrame = NormalFrame.initialize();
        initialNormalFrame.bowl(new Pins(10));
        NormalFrame expectedNormalFrame = new NormalFrame(new Strike());
        assertThat(initialNormalFrame).isEqualTo(expectedNormalFrame);
    }

    @DisplayName("노멀 프레임, 두번째 투구에서 스페어 처리한 경우 현재 프레임 상태는 'SPARE' 가 된다.")
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
        NormalFrame expectedNormalFrame = new NormalFrame(new Spare(new Pins(firstPins)));
        assertThat(initialNormalFrame).isEqualTo(expectedNormalFrame);
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
                arguments(new NormalFrame(new BeforeProgress()), false),
                arguments(new NormalFrame(new FirstBowl(new Pins(5))), false),
                arguments(new NormalFrame(new Miss(new Pins(4), new Pins(3))), true),
                arguments(new NormalFrame(new Spare(new Pins(8))), true),
                arguments(new NormalFrame(new Strike()), true)
        );
    }

    static Stream<Arguments> symbol_frameStateProvider() {
        return Stream.of(
                arguments(new NormalFrame(new BeforeProgress()), ""),
                arguments(new NormalFrame(new FirstBowl(new Pins(5))), "5"),
                arguments(new NormalFrame(new FirstBowl(new Pins(0))), "-"),
                arguments(new NormalFrame(new Miss(new Pins(4), new Pins(3))), "4|3"),
                arguments(new NormalFrame(new Miss(new Pins(4), new Pins(0))), "4|-"),
                arguments(new NormalFrame(new Miss(new Pins(0), new Pins(3))), "-|3"),
                arguments(new NormalFrame(new Miss(new Pins(0), new Pins(0))), "-|-"),
                arguments(new NormalFrame(new Spare(new Pins(0))), "-|/"),
                arguments(new NormalFrame(new Spare(new Pins(2))), "2|/"),
                arguments(new NormalFrame(new Strike()), "X")
        );
    }

    @DisplayName("해당 프레임이 파이널 프레임이 아닌 것을 확인")
    @Test
    void isFinalFrame_false() {
        assertThat(NormalFrame.initialize().isFinalFrame()).isFalse();
    }

    @DisplayName("해당 프레임 상태의 점수를 확인한다.")
    @ParameterizedTest
    @MethodSource("score_frameStateProvider")
    void score_프레임상태_점수_체크(NormalFrame normalFrame, Score resultScore) {
        assertThat(normalFrame.score()).isEqualTo(resultScore);
    }

    static Stream<Arguments> score_frameStateProvider() {
        return Stream.of(
                arguments(new NormalFrame(new BeforeProgress()), Score.init()),
                arguments(new NormalFrame(new FirstBowl(new Pins(5))), new Score(5, 0)),
                arguments(new NormalFrame(new Miss(new Pins(4), new Pins(3))), new Score(7, 0)),
                arguments(new NormalFrame(new Spare(new Pins(8))), new Score(10, 1)),
                arguments(new NormalFrame(new Strike()), new Score(10, 2))
        );
    }

    @DisplayName("이전 프레임의 점수를 현재 점수와 합하여 확인한다.")
    @ParameterizedTest
    @MethodSource("calculateAdditionalScore_frameStateProvider")
    void calculateAdditionalScore_프레임상태_이전점수합_체크(Score previousScore, NormalFrame normalFrame, Score resultScore) {
        assertThat(normalFrame.calculateAdditionalScore(previousScore)).isEqualTo(resultScore);
    }

    static Stream<Arguments> calculateAdditionalScore_frameStateProvider() {
        return Stream.of(
                arguments(new Score(10, 2) , new NormalFrame(new BeforeProgress()), Score.init()),
                arguments(new Score(10, 2) , new NormalFrame(new FirstBowl(new Pins(5))), new Score(15, 1)),
                arguments(new Score(10, 1) , new NormalFrame(new Miss(new Pins(4), new Pins(3))), new Score(14, 0)),
                arguments(new Score(10, 1) , new NormalFrame(new Spare(new Pins(8))), new Score(18, 0)),
                arguments(new Score(10, 1) , new NormalFrame(new Strike()), new Score(20, 0))
        );
    }
}