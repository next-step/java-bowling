package bowling.domain.frame.status;

import bowling.bowlingexception.InvalidPinStatusException;
import bowling.domain.frame.DownedPin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    @Test
    @DisplayName("2회차 합이 10이 아닌 경우의 예외처리")
    void invalidCondition() {
        DownedPin firstPitch = DownedPin.fromNumber(4);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(5);

        assertThatThrownBy(
                () -> new Spare(firstPitch, secondPitch)
        ).isInstanceOf(InvalidPinStatusException.class);
    }

    @Test
    @DisplayName("스페어 출력 테스트(Gutter 미포함)")
    void spareDescriptionWithoutGutter() {
        DownedPin firstPitch = DownedPin.fromNumber(5);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(5);
        Spare status = new Spare(firstPitch, secondPitch);

        assertThat(status.getDescription())
                .isEqualTo("5 | /");
    }

    @Test
    @DisplayName("스페어 출력 테스트(Gutter 포함)")
    void spareDescriptionWithGutter() {
        DownedPin firstPitch = DownedPin.fromNumber(0);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(10);
        Spare status = new Spare(firstPitch, secondPitch);

        assertThat(status.getDescription())
                .isEqualTo("- | /");
    }

    @Test
    @DisplayName("Miss에서 기본 스코어를 반환하는 지에 대한 테스트")
    void calculateScore() {
        DownedPin firstPitch = DownedPin.fromNumber(5);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(5);

        Spare spare = new Spare(firstPitch, secondPitch);

        assertThat(spare.calculateBaseScoreOfFrame())
                .isEqualTo(new Score(10, 1));
    }
}
