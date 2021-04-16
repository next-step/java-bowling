package bowling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingServiceTest {


    @DisplayName("점수 생성 테스트")
    @Test
    void pitch_점수_생성테스트() {
        BowlingService bowlingService = new BowlingService();

        int actual = bowlingService.pitch(0);

        assertThat(actual).isEqualTo(0);
    }
}
