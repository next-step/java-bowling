package bowling.domain.shot.type;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ClearProviderTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void isFinished(int bonusCount) {
        assertThat(new ClearProvider(bonusCount).isFinished())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void isClear(int bonusCount) {
        assertThat(new ClearProvider(bonusCount).isClear())
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getBonusCount(int bonusCount) {
        assertThat(new ClearProvider(bonusCount).getBonusCount())
                .isEqualTo(bonusCount);
    }
}
