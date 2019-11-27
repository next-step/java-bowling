package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    public void 생성() {
        Frames frames = new Frames();
        assertThat(frames).isEqualTo(new Frames());
    }

    @Test
    public void hasNext_Test() {
        Frames frames = new Frames();
        for (int i = 0; i < 9; i++) {
            frames.bowl(10);
        }
        assertThat(frames.hasNext()).isTrue();

        frames.bowl(0);
        frames.bowl(0);
        assertThat(frames.hasNext()).isFalse();
    }

    @Test
    public void getThisTurn_Test() {
        Frames frames = new Frames();
        assertThat(frames.getThisTurn()).isEqualTo(1);

        for (int i = 0; i < 9; i++) {
            frames.bowl(10);
        }
        assertThat(frames.getThisTurn()).isEqualTo(10);
    }

}