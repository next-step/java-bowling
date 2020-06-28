package bowling.domain.frame;

import bowling.domain.bonus.BonusScore;
import bowling.domain.bonus.BonusScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    private Frame createFrame() {
        return new FinalFrame(new BonusScores(), 9);
    }

    private Frame createHasBonusScoreStrikeFrame() {
        BonusScores bonusScores = new BonusScores();
        bonusScores.addBonusScore(BonusScore.strikeBonusScore(9));
        return new FinalFrame(bonusScores, 9);
    }

    private Frame createHasBonusScoreSpareFrame() {
        BonusScores bonusScores = new BonusScores();
        bonusScores.addBonusScore(BonusScore.spareBonusScore(9));
        return new FinalFrame(bonusScores, 9);
    }

    @Test
    @DisplayName("strike frame에 값을 넣을 수 있는지 확인")
    void availableStrikePlayTest() {
        Frame frame = createHasBonusScoreStrikeFrame();
        frame.addPoint(10);
        assertThat(frame.availablePlay()).isTrue();
        frame.addPoint(5);
        assertThat(frame.availablePlay()).isFalse();
    }

    @Test
    @DisplayName("spare Frame에 값을 넣을 수 있는지 확인")
    void availableSparePlayTest() {
        Frame frame = createHasBonusScoreSpareFrame();
        assertThat(frame.availablePlay()).isTrue();
        frame.addPoint(5);
        frame.addPoint(5);
        assertThat(frame.availablePlay()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideOverflowScore")
    @DisplayName("총 점수 합산이 10이 넘는 경우 Exception")
    void validateScoreTest(int firstPoint, int secondPoint) {
        Frame frame = createFrame();
        frame.addPoint(firstPoint);
        assertThatThrownBy(() -> frame.addPoint(secondPoint))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideOverflowScore() {
        return Stream.of(
                Arguments.of(5, 6),
                Arguments.of(1, 10)
        );
    }

    @Test
    @DisplayName("strike일 경우 총합이 20이 넘어가는 경우 Exception")
    void validateAddStrike() {
        Frame frame = createHasBonusScoreStrikeFrame();
        frame.addPoint(10);

        assertThatThrownBy(() -> frame.addPoint(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("spare일 경우 총합이 20이 넘는 경우 Exception")
    void validateAddSpare() {
        Frame frame = createHasBonusScoreSpareFrame();
        frame.addPoint(5);
        frame.addPoint(5);

        assertThatThrownBy(() -> frame.addPoint(11))
                .isInstanceOf(IllegalArgumentException.class);
    }
}