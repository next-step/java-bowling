package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StrikeTest {
    @DisplayName("Strike 생성한다.")
    @Test
    void Strike_생성() {
        assertThat(new Strike()).isNotNull().isInstanceOf(Strike.class);
    }

    @DisplayName("Strike 상태는 종료 상태이므로 투구시 예외가 발생한다.")
    @Test
    void bowl_투구_예외() {
        Strike strike = new Strike();
        Pins pins = new Pins(3);
        assertThatThrownBy(() -> strike.bowl(pins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Strike 상태는 프레임 종료이므로 true 를 반환한다.")
    @Test
    void isFrameEnd_종료_상태_체크() {
        Strike strike = new Strike();
        assertThat(strike.isEnd()).isTrue();
    }

    @DisplayName("Strike 상태는 쓰러트린 핀이 무조건 10이고, 'X' 기호를 반환한다.")
    @Test
    void symbol_기호_체크() {
        assertThat(new Strike().symbol()).isEqualTo("X");
    }

    @DisplayName("Strike 상태의 점수는 10점 이고, 남은 기회는 2 이다.")
    @Test
    void score_점수() {
        FrameState frameState = new Strike();
        assertThat(frameState.score()).isEqualTo(new Score(10, 2));
    }

    @DisplayName("직전 점수가 보너스 점수일때, 보너스 기회가 1이면 첫번째 투구로 쓰러트린 핀을 점수로 더하고, 보너스 기회가 2이면 첫번째와 두번째 투구로 쓰러트린 핀을 점수로 더한다. 남은 기회는 1 감소 시킨다.")
    @ParameterizedTest
    @MethodSource("previousScore_provider")
    void calculateAdditionalScore_직전점수_보너스점수일_경우(Score previousScore, Score resultScore) {
        FrameState frameState = new Strike();
        assertThat(frameState.calculateAdditionalScore(previousScore)).isEqualTo(resultScore);
    }

    static Stream<Arguments> previousScore_provider() {
        return Stream.of(
                arguments(new Score(10, 2), new Score(20, 1)),
                arguments(new Score(10, 2), new Score(20, 1)),
                arguments(new Score(10, 2), new Score(20, 1)),
                arguments(new Score(10, 1), new Score(20, 0)),
                arguments(new Score(10, 1), new Score(20, 0))
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