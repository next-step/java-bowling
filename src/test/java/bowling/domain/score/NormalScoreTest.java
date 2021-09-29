package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalScoreTest {

    @Test
    @DisplayName("빈 socre를 생성할 수 있다.")
    void createEmptyScoreTest() {

        // given
        Score expected = NormalScore.emptyScore();

        // when
        Score result = NormalScore.emptyScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫번째 pin이 비어있다면 저장할 수 있다.")
    void saveFirstPinTest() {

        // given
        Pin first = Pin.of(3);
        Score input = NormalScore.emptyScore();

        Score expected = NormalScore.of(first, null);

        // when
        Score result = input.saveNextPin(first);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("두번째 pin이 비어있다면 저장할 수 있다.")
    void saveSecondPinTest() {

        // given
        Pin first = Pin.of(3);
        Pin second = Pin.of(4);
        Score input = NormalScore.of(first, null);

        Score expected = NormalScore.of(first, second);

        // when
        Score result = input.saveNextPin(second);

        // then
        assertThat(result).isEqualTo(expected);
    }

}