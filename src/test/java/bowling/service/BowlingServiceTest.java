package bowling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingServiceTest {


    @DisplayName("점수 생성 테스트")
    @Test
    void pitch_점수_생성테스트() {
        BowlingService bowlingService = new BowlingService();

        bowlingService.pitch(0);

        assertThat(bowlingService.totalScore()).isZero();
    }

    @DisplayName("점수 합산 테스트")
    @ParameterizedTest(name = "총 10 프레임 각 투구 시 {0} 개 넘어뜨려 전체 스코어 {1} 확인 테스트")
    @CsvSource(value = {"1,20", "2,40"})
    void pitch_10프레임_투구_테스트(int pitch, int score) {
        BowlingService bowlingService = new BowlingService();
        for(int i = 0 ; i < 20 ; i++) {
            bowlingService.pitch(pitch);
        }
        assertThat(bowlingService.totalScore()).isEqualTo(score);
    }
}
