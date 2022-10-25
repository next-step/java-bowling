package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PitchResultEnumTest {

    @Test
    void getPitchResultEnumTest_STRIKE() {
        PitchResultEnum result = PitchResultEnum.getPitchResultEnum(0, RuleConfig.NUMBER_OF_PIN, RuleConfig.PITCH_COUNT - 1);
        assertThat(result).isEqualTo(PitchResultEnum.STRIKE);
    }

    @Test
    void getPitchResultEnumTest_SPARE() {
        PitchResultEnum result = PitchResultEnum.getPitchResultEnum(0, RuleConfig.NUMBER_OF_PIN, 0);
        assertThat(result).isEqualTo(PitchResultEnum.SPARE);
    }

    @Test
    void getPitchResultEnumTest_MISS() {
        PitchResultEnum result = PitchResultEnum.getPitchResultEnum(1, RuleConfig.NUMBER_OF_PIN, 0);
        assertThat(result).isEqualTo(PitchResultEnum.MISS);
    }

    @Test
    void getPitchResultEnumTest_GUTTER() {
        PitchResultEnum result = PitchResultEnum.getPitchResultEnum(RuleConfig.NUMBER_OF_PIN, 0, RuleConfig.PITCH_COUNT);
        assertThat(result).isEqualTo(PitchResultEnum.GUTTER);
    }

    @Test
    void getPitchResultEnumTest_NONE() {
        PitchResultEnum result = PitchResultEnum.getPitchResultEnum(RuleConfig.NUMBER_OF_PIN - 1, 1, RuleConfig.PITCH_COUNT - 1);
        assertThat(result).isEqualTo(PitchResultEnum.NONE);
    }

}