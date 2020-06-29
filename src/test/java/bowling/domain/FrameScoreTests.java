package bowling.domain;

import bowling.domain.exceptions.InvalidScoreValueException;
import bowling.domain.exceptions.InvalidTryOfApplyBonusException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameScoreTests {
    @DisplayName("완료 여부, 점수값을 전달받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        FrameScoreStatus frameScoreStatus = FrameScoreStatus.NOT_READY;
        int scoreValue = 12;

        assertThat(new FrameScore(frameScoreStatus, scoreValue))
                .isEqualTo(new FrameScore(frameScoreStatus, scoreValue));
    }

    @DisplayName("음수 점수로 객체를 생성할 수 없다.")
    @Test
    void createValidationTest() {
        FrameScoreStatus frameScoreStatus = FrameScoreStatus.NOT_READY;
        int scoreValue = -1;

        assertThatThrownBy(() -> new FrameScore(frameScoreStatus, scoreValue))
                .isInstanceOf(InvalidScoreValueException.class);
    }

    @DisplayName("준비 상태를 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("isReadyResource")
    void isReadyTest(FrameScoreStatus frameScoreStatus, boolean expectedResult) {
        int scoreValue = 10;
        FrameScore frameScore = new FrameScore(frameScoreStatus, scoreValue);

        assertThat(frameScore.isComplete()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> isReadyResource() {
        return Stream.of(
                Arguments.of(FrameScoreStatus.NOT_READY, false),
                Arguments.of(FrameScoreStatus.COMPLETE, true)
        );
    }

    @DisplayName("Spare 보너스를 적용해서 계산할 수 있다.")
    @Test
    void applySpareBonusTest() {
        FrameScore previousScore = new FrameScore(FrameScoreStatus.NOT_READY, 10);

        assertThat(previousScore.applySpareBonus(5)).isEqualTo(new FrameScore(FrameScoreStatus.COMPLETE, 15));
    }

    @DisplayName("이미 완료된 점수에 Spare 보너스를 적용할 수 없다.")
    @Test
    void applySpareBonusValidationTest() {
        FrameScore previousScore = new FrameScore(FrameScoreStatus.COMPLETE, 9);

        assertThatThrownBy(() -> previousScore.applySpareBonus(5))
                .isInstanceOf(InvalidTryOfApplyBonusException.class);
    }
}
