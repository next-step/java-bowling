package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreTest {

    @Test
    @DisplayName("NormalScore equals, hashCode 재정의 테스트")
    void normalScoreEqualsHashCodeTest() {

        // given
        List<Pin> pins = Arrays.asList(Pin.of(2), Pin.of(5));

        // when
        NormalScore result = NormalScore.of(pins);

        // then
        assertThat(result)
            .isEqualTo(NormalScore.of(pins))
            .hasSameHashCodeAs(NormalScore.of(pins));
    }

}