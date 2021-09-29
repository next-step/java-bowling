package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreTest {

    @Test
    @DisplayName("빈 socre를 생성할 수 있다.")
    void createEmptyScoreTest() {

        // given
        Score expected = NormalScore.of(new ArrayList<>());

        // when
        Score result = NormalScore.emptyScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫 Pin을 저장할 수 있다.")
    void saveFirstPinTest() {

        // given
        Pin first = Pin.of(5);
        Score input = NormalScore.emptyScore();

        Score expected = NormalScore.of(Arrays.asList(first));

        // when
        Score result = input.saveNextPin(first);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("NormalScore equals, hashCode 재정의 테스트")
    void normalScoreEqualsHashCodeTest() {

        // given
        List<Pin> pins = Arrays.asList(Pin.of(2), Pin.of(5));

        // when
        Score result = NormalScore.of(pins);

        // then
        assertThat(result)
            .isEqualTo(NormalScore.of(pins))
            .hasSameHashCodeAs(NormalScore.of(pins));
    }

}