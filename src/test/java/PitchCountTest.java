import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PitchCountTest {

    @Test
    void 생성() {
        PitchCount pitchCount = new PitchCount(1);

        assertThat(pitchCount).isEqualTo(new PitchCount(1));
    }

    @ParameterizedTest
    @CsvSource(value = {"0,true", "1,true", "2,false"})
    void 일반_프레임_최대횟수만큼_던졌는지_확인(int count, boolean pitchable) {
        PitchCount pitchCount = new PitchCount(count);

        assertThat(pitchCount.isPitchable(false)).isEqualTo(pitchable);
    }

    @ParameterizedTest
    @CsvSource(value = { "2,true,true", "2,false,false"})
    void 마지막_프레임에서_조건만족후_3번째투구_가능한지_확인(int count,boolean isFinalAndTwoStrike, boolean pitchable) {
        PitchCount pitchCount = new PitchCount(count);

        assertThat(pitchCount.isPitchable(isFinalAndTwoStrike)).isEqualTo(pitchable);
    }
}