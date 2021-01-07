package bowling.domain.score;

import bowling.domain.frame.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ScoreTest {

    @Test
    @DisplayName("스코어의 생성")
    void testScore() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(4);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(5);
        pins.add(firstPitch);
        pins.add(secondPitch);

        Score sampleScore = new Score(pins);
    }

    @Test
    @DisplayName("일반 생성의 에러 처리")
    void testExceptions() {
        List<DownedPin> pins = new ArrayList<>();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> new Score(pins)
        );
    }

    @Test
    @DisplayName("Miss 상태의 스코어 확인(추가적인 입력은 제외 됨)")
    void testMiss() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(8);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(1);

        pins.add(firstPitch);
        pins.add(secondPitch);

        Score score = new Score(pins);

        assertThat(score.calculateScore()).isEqualTo(9);

        score.record(5);
        assertThat(score.calculateScore()).isEqualTo(9);
    }

    @Test
    @DisplayName("Spare 상태의 추가 점수 확인")
    void testSpare() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(5);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(5);

        pins.add(firstPitch);
        pins.add(secondPitch);

        Score score = new Score(pins);
        assertThat(score.hasFixedScore()).isFalse();

        score.record(10);
        assertThat(score.hasFixedScore()).isTrue();
        assertThat(score.calculateScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("Strike 상태의 추가 점수 확인")
    void testStrike() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(10);

        pins.add(firstPitch);

        Score score = new Score(pins);

        assertThat(score.hasFixedScore()).isFalse();

        score.record(10);
        score.record(4);

        assertThat(score.hasFixedScore()).isTrue();
        assertThat(score.calculateScore()).isEqualTo(24);
    }
}
