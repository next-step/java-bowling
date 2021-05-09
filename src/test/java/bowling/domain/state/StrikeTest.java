package bowling.domain.state;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StrikeTest {

    @DisplayName("Strike 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        State strike = Strike.initialize();

        assertAll(
                () -> assertThat(strike).isNotNull(),
                () -> assertThat(strike).isInstanceOf(Strike.class)
        );
    }

    @DisplayName("Strike 인스턴스의 Score 반환 여부 테스트")
    @Test
    void 반환_Score() {
        State strike = Strike.initialize();

        assertAll(
                () -> assertThat(strike.score()).isNotNull(),
                () -> assertThat(strike.score()).isInstanceOf(Score.class),
                () -> assertThat(strike.score().score()).isEqualTo(10)
        );
    }

}