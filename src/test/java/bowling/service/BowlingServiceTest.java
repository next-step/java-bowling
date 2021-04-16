package bowling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingServiceTest {


    @DisplayName("점수 생성 테스트")
    @Test
    void pitch_점수_생성테스트() {
        BowlingService bowlingService = new BowlingService();

        bowlingService.pitch(0, 0);

        assertThat(bowlingService.totalScore()).isZero();
    }

    private void pitches(int firstBall, int secondBall, int frames, BowlingService bowlingService) {
        for (int i = 0; i < frames; i++) {
            bowlingService.pitch(firstBall, secondBall);
        }
    }

    @DisplayName("점수 합산 테스트")
    @ParameterizedTest(name = "총 10 프레임 각 투구 시 {0} 개 넘어뜨려 전체 스코어 {1} 확인 테스트")
    @CsvSource(value = {"1,1,20", "2,2,40"})
    void pitch_10프레임_투구_테스트(int firstBall, int secondBall, int score) {
        BowlingService bowlingService = new BowlingService();
        pitches(firstBall, secondBall, 10, bowlingService);
        assertThat(bowlingService.totalScore()).isEqualTo(score);
    }

    @Test
    void spare_스페어_점수_처리테스트() {
        BowlingService bowlingService = new BowlingService();
        pitches(5, 5, 2, bowlingService);
        assertThat(bowlingService.totalScore()).isEqualTo(15);
    }
}
