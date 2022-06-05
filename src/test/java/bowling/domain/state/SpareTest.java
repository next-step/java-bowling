package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class SpareTest {
    @DisplayName("Spare 생성한다.")
    @Test
    void Spare_생성() {
        assertThat(new Spare(new Pins(3))).isNotNull().isInstanceOf(Spare.class);
    }

    @DisplayName("Spare 생성 시 첫번째 투구에서 10개의 핀을 쓰러트릴 경우 예외가 발생한다.")
    @Test
    void Spare_생성_예외() {
        Pins firstPins = new Pins(10);
        assertThatThrownBy(() -> new Spare(firstPins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Spare 상태는 종료 상태이므로 투구시 예외가 발생한다.")
    @Test
    void bowl_투구_예외() {
        Spare spare = new Spare(new Pins(3));
        Pins pins = new Pins(3);
        assertThatThrownBy(() -> spare.bowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Spare 상태는 프레임 종료이므로 true 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        Spare spare = new Spare(new Pins(3));
        assertThat(spare.isEnd()).isTrue();
    }

    @DisplayName("Spare 상태는 쓰러트린 핀이 0일 경우 '-' 기호를 반환하고, 1 ~ 9개를 쓰러트릴 경우 쓰러트린 숫자를 기호로 반환한다. 2번째 투구 기호는 '/' 기호를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, -|/",
            "1, 1|/",
            "8, 8|/"
    })
    void symbol_기호_체크(int firstPins, String symbol) {
        assertThat(new Spare(new Pins(firstPins)).symbol()).isEqualTo(symbol);
    }

    @DisplayName("Spare 상태의 점수는 10점 이고, 남은 기회는 1 이다.")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,8,9})
    void score_점수(int firstPins) {
        FrameState frameState = new Spare(new Pins(firstPins));
        assertThat(frameState.score()).isEqualTo(new Score(10, 1));
    }

    @DisplayName("직전 점수가 보너스 점수일때, 보너스 기회가 1이면 첫번째 투구로 쓰러트린 핀을 점수로 더하고, 보너스 기회가 2이면 첫번째와 두번째 투구로 쓰러트린 핀을 점수로 더한다. 남은 기회는 1 감소 시킨다.")
    @ParameterizedTest
    @MethodSource("previousScore_provider")
    void calculateAdditionalScore_직전점수_보너스점수일_경우(Score previousScore, Pins firstPins, Score resultScore) {
        FrameState frameState = new Spare(firstPins);
        assertThat(frameState.calculateAdditionalScore(previousScore)).isEqualTo(resultScore);
    }

    static Stream<Arguments> previousScore_provider() {
        return Stream.of(
                arguments(new Score(10, 2), new Pins(0), new Score(20, 0)),
                arguments(new Score(10, 2), new Pins(1), new Score(20, 0)),
                arguments(new Score(10, 2), new Pins(2), new Score(20, 0)),
                arguments(new Score(10, 1), new Pins(8), new Score(18, 0)),
                arguments(new Score(10, 1), new Pins(4), new Score(14, 0))
        );
    }

    @DisplayName("직전 점수가 보너스 점수가 아니라면, 직전 점수를 그대로 반환한다.")
    @Test
    void calculateAdditionalScore_직전점수_보너스점수가_아닐_경우() {
        FrameState frameState = new Spare(new Pins(4));
        Score previousScore = new Score(10, 0);
        assertThat(frameState.calculateAdditionalScore(previousScore)).isEqualTo(previousScore);
    }
}