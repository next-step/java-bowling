package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    Frames frames;


    @BeforeEach
    void setUp() {
        frames = Frames.init();
    }

    @Test
    @DisplayName("Spare처리 후 다음 보너스 점수 포함")
    void scoreTest() {
        frames.bowl(5);
        frames.bowl(5);
        assertThat(frames.getScore(1)).isEqualTo(-1);
        frames.bowl(8);
        assertThat(frames.getScore(1)).isEqualTo(18);
    }

    @Test
    @DisplayName("스트라이크 처리 후 : 최대 2핀까지 점수 포함")
    void scoreTest2() {
        frames.bowl(10);
        assertThat(frames.getScore(1)).isEqualTo(-1);
        frames.bowl(5);
        frames.bowl(4);
        assertThat(frames.getScore(1)).isEqualTo(19);
    }


    @Test
    @DisplayName("스페어처리 못한 점수 테스트")
    void scoreTest3() {
        frames.bowl(5);
        frames.bowl(4);
        assertThat(frames.getScore(1)).isEqualTo(9);
    }

    @Test
    @DisplayName("3연속 스트라이크")
    void scoreTest4() {
        frames.bowl(10);
        assertThat(frames.getScore(1)).isEqualTo(-1);
        frames.bowl(10);
        assertThat(frames.getScore(1)).isEqualTo(-1);
        frames.bowl(10);
        assertThat(frames.getScore(1)).isEqualTo(30);
    }

    @Test
    @DisplayName("9, 10 프레임 점수 테스트")
    void scoreTest5() {
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(10);
        frames.bowl(4);
        frames.bowl(5);
        assertThat(frames.getScore(9)).isEqualTo(24);
        assertThat(frames.getScore(10)).isEqualTo(19);
    }
}
