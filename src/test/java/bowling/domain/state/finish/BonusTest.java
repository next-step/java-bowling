package bowling.domain.state.finish;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.score.Pin;
import bowling.domain.state.State;
import bowling.exception.state.BonusStateCreateScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusTest {

    @Test
    @DisplayName("Bonus State는 Score를 생성할 수 없다.")
    void createScoreExceptionTest() {

        // given
        State state = new Bonus(Pin.of(4));

        // when & then
        assertThatExceptionOfType(BonusStateCreateScoreException.class)
            .isThrownBy(() -> state.createScore())
            .withMessageMatching("Bonus state는 Score를 생성할 수 없습니다.");
    }

}