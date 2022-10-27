package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchResultTest {

    @Test
    void ofTest_STRIKE() {
        PitchResult result = PitchResult.of(0, RuleConfig.NUMBER_OF_PIN, RuleConfig.PITCH_COUNT - 1);
        assertThat(result).isEqualTo(PitchResult.STRIKE);
    }

    @Test
    void ofTest_SPARE() {
        PitchResult result = PitchResult.of(0, RuleConfig.NUMBER_OF_PIN, 0);
        assertThat(result).isEqualTo(PitchResult.SPARE);
    }

    @Test
    void ofTest_MISS() {
        PitchResult result = PitchResult.of(1, RuleConfig.NUMBER_OF_PIN, 0);
        assertThat(result).isEqualTo(PitchResult.MISS);
    }

    @Test
    void ofTest_GUTTER() {
        PitchResult result = PitchResult.of(RuleConfig.NUMBER_OF_PIN, 0, RuleConfig.PITCH_COUNT);
        assertThat(result).isEqualTo(PitchResult.GUTTER);
    }

    @Test
    void ofTest_NONE() {
        PitchResult result = PitchResult.of(RuleConfig.NUMBER_OF_PIN - 1, 1, RuleConfig.PITCH_COUNT - 1);
        assertThat(result).isEqualTo(PitchResult.NONE);
    }

}