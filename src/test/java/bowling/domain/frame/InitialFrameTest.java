package bowling.domain.frame;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InitialFrameTest {

    @DisplayName("InitialFrame은 state와 함께 생성된다.")
    @Test
    void should_make_last_frame() {
        //arrange, act, assert
        assertThat(InitialFrame.of()).isInstanceOf(InitialFrame.class);
    }

    @DisplayName("InitialFrame은 score return시 null object를 반환한다")
    @Test
    void should_return_null() {
        //arrange, act, assert
        assertThat(InitialFrame.of().getScore()).isEqualTo(Score.NULL);
    }
}