package bowling.domain.frame;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DummyFrameTest {

    @DisplayName("DummyFrame은 state와 함께 생성된다.")
    @Test
    void should_make_last_frame() {
        //arrange, act, assert
        assertThat(DummyFrame.of()).isInstanceOf(DummyFrame.class);
    }

    @DisplayName("DummyFrame은 score return시 null object를 반환한다")
    @Test
    void should_return_null() {
        //arrange, act, assert
        assertThat(DummyFrame.of().getScore()).isEqualTo(Score.NULL);
    }
}