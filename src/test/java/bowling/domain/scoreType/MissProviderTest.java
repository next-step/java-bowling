package bowling.domain.scoreType;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class MissProviderTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void isFinished(boolean isFirst) {
        assertThat(new MissProvider(isFirst).isFinished())
                .isEqualTo(!isFirst);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void isClear(boolean isFirst) {
        assertThat(new MissProvider(isFirst).isClear())
                .isFalse();
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void getBonusCount(boolean isFirst) {
        assertThat(new MissProvider(isFirst).getBonusCount())
                .isEqualTo(0);
    }
}
