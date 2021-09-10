package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ShotTest {
    @DisplayName("유효하지 않는 샷 테스트")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void invalidShotTest(int input) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Shot(input));
    }

}
