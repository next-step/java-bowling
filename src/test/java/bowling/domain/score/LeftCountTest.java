package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LeftCountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("leftCount를 생성한다.")
    void create(int leftCount) {
        assertThat(LeftCount.from(leftCount)).isEqualTo(LeftCount.from(leftCount));
    }
}
