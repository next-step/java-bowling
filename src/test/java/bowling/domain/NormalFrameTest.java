package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    @Test
    void 프레임_상태_테스트_스트라이크() {
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10);
        assertThat(normalFrame.frameExpression())
                .isEqualTo("X   ");
    }

    @Test
    void 프레임_상태_테스트_스페어() {
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(3).bowl(7);
        assertThat(normalFrame.frameExpression())
                .isEqualTo("3|/ ");
    }

    @Test
    void 프레임_상태_테스트_미스() {
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(3).bowl(3);
        assertThat(normalFrame.frameExpression())
                .isEqualTo("3|3 ");
    }

    @Test
    void 프레임_상태_테스트_거터() {
        Frame normalFrame = new NormalFrame(1);
        normalFrame.bowl(0).bowl(0);
        assertThat(normalFrame.frameExpression())
                .isEqualTo("-|- ");
    }

    @Test
    void 스트라이크_미스_상황_일때() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10).bowl(5).bowl(3).bowl(5).bowl(3);
        assertThat(normalFrame.score()).isEqualTo(18);
    }

    @Test
    void 스트라이크_상황_일때() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(10);
        assertThat(normalFrame.score()).isEqualTo(-1);
    }

    @Test
    void 미스_계산() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.bowl(5).bowl(4);
        assertThat(normalFrame.score()).isEqualTo(9);
    }

}