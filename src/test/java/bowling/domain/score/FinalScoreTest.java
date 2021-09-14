package bowling.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalScoreTest {

    @Test
    @DisplayName("빈 Score 생성 테스트")
    void createEmptyScoreTest() {

        // when
        FinalScore result = FinalScore.empty();

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }

    @Test
    @DisplayName("첫번째 핀을 생성할 수 있다.")
    void createFirstPinTest() {

        // given
        FinalScore start = FinalScore.empty();
        Pin first = Pin.of(5);

        // when
        FinalScore result = start.createFirstPin(first);

        // then
        assertThat(result).isInstanceOf(FinalScore.class);
    }
}