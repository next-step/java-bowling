package bowling.domain.state;

import bowling.domain.pitch.Pitch;
import bowling.domain.score.Score;
import bowling.domain.state.exception.BonusBowlingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MissTest {

    State miss;

    @BeforeEach
    void beforeEach() {
        this.miss = Ready.of(4).bowling(4);
    }

    @Test
    @DisplayName("Miss 클래스 객체 생성")
    void create() {
        assertAll(() -> assertThat(Ready.of(4).bowling(4)).isInstanceOf(Miss.class),
                () -> assertThat(new Miss(Pitch.of(4), 4)).isInstanceOf(Miss.class));
    }

    @Test
    @DisplayName("보너스 투구 시, 예외 처리")
    void bonusBowlingException() {
        assertThatThrownBy(() -> this.miss.bonusBowling(5)).isExactlyInstanceOf(BonusBowlingException.class);
    }

    @ParameterizedTest(name = "이전 프레임 점수 계산 - {0}")
    @MethodSource("calculateScoreCondition")
    void calculateScore(State beforeState, int expectedScore) {
        Score after = this.miss.calculateScore(beforeState.createScore());

        assertThat(after.getScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> calculateScoreCondition() {
        return Stream.of(
                Arguments.of(Ready.of(10), 18),
                Arguments.of(Ready.of(5).bowling(5), 14)
        );
    }
}
