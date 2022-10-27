package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchResultEnumTest {

    @Test
    void ofTest_STRIKE() {
        PitchResultEnum result = PitchResultEnum.of(0, RuleConfig.NUMBER_OF_PIN, RuleConfig.PITCH_COUNT - 1);
        assertThat(result).isEqualTo(PitchResultEnum.STRIKE);
    }

    @Test
    void ofTest_SPARE() {
        PitchResultEnum result = PitchResultEnum.of(0, RuleConfig.NUMBER_OF_PIN, 0);
        assertThat(result).isEqualTo(PitchResultEnum.SPARE);
    }

    @Test
    void ofTest_MISS() {
        PitchResultEnum result = PitchResultEnum.of(1, RuleConfig.NUMBER_OF_PIN, 0);
        assertThat(result).isEqualTo(PitchResultEnum.MISS);
    }

    @Test
    void ofTest_GUTTER() {
        PitchResultEnum result = PitchResultEnum.of(RuleConfig.NUMBER_OF_PIN, 0, RuleConfig.PITCH_COUNT);
        assertThat(result).isEqualTo(PitchResultEnum.GUTTER);
    }

    @Test
    void ofTest_NONE() {
        PitchResultEnum result = PitchResultEnum.of(RuleConfig.NUMBER_OF_PIN - 1, 1, RuleConfig.PITCH_COUNT - 1);
        assertThat(result).isEqualTo(PitchResultEnum.NONE);
    }

}