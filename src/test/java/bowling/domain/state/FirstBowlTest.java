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

class FirstBowlTest {
    @DisplayName("FirstBowl 생성한다.")
    @Test
    void FirstBowl_생성() {
        assertThat(new FirstBowl(new Pins(3))).isNotNull().isInstanceOf(FirstBowl.class);
    }

    @DisplayName("FirstBowl 생성 시 첫번째 투구에서 10개의 핀을 쓰러트릴 경우 예외가 발생한다.")
    @Test
    void FirstBowl_생성_예외() {
        Pins pins = new Pins(10);
        assertThatThrownBy(() -> new FirstBowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("첫번째 투구와 두번째 투구에서 쓰러트린 핀이 10을 넘어서는 경우 예외가 발생한다.")
    @Test
    void bowl_투구_예외() {
        FirstBowl firstBowl = new FirstBowl(new Pins(8));
        Pins pins = new Pins(3);
        assertThatThrownBy(() -> firstBowl.bowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두번째 투구에서 스페어 처리 한 경우 다음 상태는 'Spare' 이다.")
    @Test
    void bowl_투구_다음상태_Spare() {
        FirstBowl firstBowl = new FirstBowl(new Pins(8));
        FrameState bowl = firstBowl.bowl(new Pins(2));
        assertThat(bowl).isInstanceOf(Spare.class);
    }

    @DisplayName("두번째 투구에서 스페어 처리 못 한 경우 다음 상태는 'Miss' 이다.")
    @Test
    void bowl_투구_다음상태_Miss() {
        FirstBowl firstBowl = new FirstBowl(new Pins(8));
        FrameState bowl = firstBowl.bowl(new Pins(1));
        assertThat(bowl).isInstanceOf(Miss.class);
    }

    @DisplayName("FirstBowl 상태는 프레임 종료가 아니므로 false 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        FirstBowl firstBowl = new FirstBowl(new Pins(8));
        assertThat(firstBowl.isEnd()).isFalse();
    }

    @DisplayName("FirstBowl 상태는 쓰러트린 핀이 0일 경우 '-' 기호를 반환하고, 1 ~ 9개를 쓰러트릴 경우 쓰러트린 숫자를 기호로 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "0, -",
            "1, 1",
            "8, 8",
            "9, 9"
    })
    void symbol_기호_체크(int hitPins, String symbol) {
        assertThat(new FirstBowl(new Pins(hitPins)).symbol()).isEqualTo(symbol);
    }

    @DisplayName("FirstBowl 상태의 점수는 첫번째 투구로 쓰러트린 핀이 점수이고, 남은 기회는 0 이다.")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2,8,9})
    void score_점수(int firstHitPins) {
        FrameState frameState = new FirstBowl(new Pins(firstHitPins));
        assertThat(frameState.score()).isEqualTo(new Score(firstHitPins, 0));
    }

    @DisplayName("직전 점수가 보너스 점수라면, FirstBowl 상태의 점수는 첫번째 쓰러트린 핀 갯수를 점수를 더하고, 남은 기회를 1 감소 시킨다.")
    @ParameterizedTest
    @MethodSource("previousScore_provider")
    void calculateAdditionalScore_직전점수_보너스점수일_경우(Score previousScore, Pins hitPins, Score resultScore) {
        FrameState frameState = new FirstBowl(hitPins);
        assertThat(frameState.calculateAdditionalScore(previousScore)).isEqualTo(resultScore);
    }

    static Stream<Arguments> previousScore_provider() {
        return Stream.of(
                arguments(new Score(10, 2), new Pins(0), new Score(10, 1)),
                arguments(new Score(10, 2), new Pins(1), new Score(11, 1)),
                arguments(new Score(10, 2), new Pins(2), new Score(12, 1)),
                arguments(new Score(10, 1), new Pins(8), new Score(18, 0)),
                arguments(new Score(10, 1), new Pins(9), new Score(19, 0))
        );
    }

    @DisplayName("직전 점수가 보너스 점수가 아니라면, 직전 점수를 그대로 반환한다.")
    @Test
    void calculateAdditionalScore_직전점수_보너스점수가_아닐_경우() {
        FrameState frameState = new FirstBowl(new Pins(1));
        Score previousScore = new Score(10, 0);
        assertThat(frameState.calculateAdditionalScore(previousScore)).isEqualTo(previousScore);
    }
}