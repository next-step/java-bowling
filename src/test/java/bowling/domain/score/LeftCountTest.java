package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class LeftCountTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    @DisplayName("leftCount를 생성한다.")
    void createLeftCount(int leftCount) {
        assertThat(LeftCount.from(leftCount)).isEqualTo(LeftCount.from(leftCount));
    }

    @Test
    @DisplayName("일반적인 LeftCount를 생성한다.")
    void create() {
        // given
        // when
        final LeftCount leftCount = LeftCount.create();

        // then
        assertThat(leftCount).isEqualTo(LeftCount.from(0));
    }

    @Test
    @DisplayName("strike LeftCount를 생성한다.")
    void strikeLeftCount() {
        // given
        // when
        final LeftCount strike = LeftCount.strike();

        // then
        assertThat(strike).isEqualTo(LeftCount.from(2));
    }

    @Test
    @DisplayName("spare LeftCount를 생성한다.")
    void spareLeftCount() {
        // given
        // when
        final LeftCount spare = LeftCount.spare();

        // then
        assertThat(spare).isEqualTo(LeftCount.from(1));
    }
}
