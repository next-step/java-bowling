package bowling.domain;

import java.util.List;
import java.util.stream.Stream;

import bowling.engine.Bonus;
import bowling.engine.BonusScores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static bowling.domain.ShotResult.GUTTER;
import static bowling.domain.ShotResultTest.FIVE;
import static bowling.domain.ShotResultTest.THREE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameBonusTest {
    @Test
    public void create() {
        assertThat(FrameBonus.of(List.of(), ClearBonusScores.byNone())).isInstanceOf(FrameBonus.class);
        assertThat(FrameBonus.of(ClearBonusScores.byNone())).isInstanceOf(FrameBonus.class);
        assertThat(FrameBonus.of(List.of())).isInstanceOf(FrameBonus.class);
    }

    public static Stream<Arguments> parseCreateFailed() {
        return Stream.of(
                Arguments.of(null, ClearBonusScores.byNone()),
                Arguments.of(List.of(ClearBonusScores.byNone()), null),
                Arguments.of(List.of(ClearBonusScores.byNone(), ClearBonusScores.byNone(), ClearBonusScores.byNone()), ClearBonusScores.byNone())
        );
    }

    @ParameterizedTest(name = "create failed: {arguments}")
    @MethodSource("parseCreateFailed")
    public void createFailed(List<BonusScores> previous, BonusScores current) {
        assertThatIllegalArgumentException().isThrownBy(() -> FrameBonus.of(previous, current));
    }

    @Test
    public void applyBonus() {
        List<BonusScores> previous = List.of(ClearBonusScores.bySpare());
        BonusScores current = ClearBonusScores.bySpare();

        FrameBonus.of(previous, current).applyBonus(GUTTER);

        assertThat(previous.stream().allMatch(BonusScores::completed)).isTrue();
        assertThat(previous.stream().allMatch(scores -> scores.stream().allMatch(BowlingScore.of(0)::equals))).isTrue();

        assertThat(current.completed()).isTrue();
        assertThat(current.collect()).containsExactly(BowlingScore.of(0));
    }

    @Test
    public void applyBonusOnlyRemains() {
        BonusScores previous = ClearBonusScores.bySpare();
        previous.appendBonus(FIVE);
        BonusScores current = ClearBonusScores.bySpare();

        FrameBonus.of(List.of(previous), current).applyBonus(GUTTER);

        assertThat(previous.completed()).isTrue();
        assertThat(previous.collect()).doesNotContain(BowlingScore.of(0));

        assertThat(current.completed()).isTrue();
        assertThat(current.collect()).containsExactly(BowlingScore.of(0));
    }

    @Test
    public void score() {
        BonusScores previous = ClearBonusScores.bySpare();
        previous.appendBonus(FIVE);
        BonusScores current = ClearBonusScores.bySpare();

        Bonus bonus = FrameBonus.of(List.of(previous), current).applyBonus(THREE);
        assertThat(bonus.score()).isEqualTo(BowlingScore.of(3));
        assertThat(bonus.score()).isEqualTo(current.sum());
    }

    @Test
    public void remainAndCompleted() {
        BonusScores previous = ClearBonusScores.bySpare();
        BonusScores current = ClearBonusScores.bySpare();

        Bonus bonus = FrameBonus.of(List.of(previous), current);
        assertThat(bonus.remainBonus().size()).isEqualTo(2);
        assertThat(bonus.remainBonus()).containsExactly(previous, current);
        assertThat(bonus.completed()).isFalse();
        bonus.applyBonus(THREE);
        assertThat(bonus.remainBonus().isEmpty()).isTrue();
        assertThat(bonus.completed()).isTrue();
    }

}
