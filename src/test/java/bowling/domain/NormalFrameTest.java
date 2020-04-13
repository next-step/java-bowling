package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame();
    }

    @DisplayName("프레임의 쓰러트린 핀 갯수를 저장할 수 있다.")
    @Test
    void init() {
        assertThat(normalFrame.addPinCount(8)).isTrue();
        assertThat(normalFrame.addPinCount(1)).isTrue();

        assertThat(normalFrame.getScore()).isEqualTo(9);
    }

    @DisplayName("한 프레임의 핀 갯수는 10개를 넘을 수 없다")
    @Test
    void overMax() {
        assertThat(normalFrame.addPinCount(8)).isTrue();
        assertThat(normalFrame.addPinCount(3)).isFalse();
    }

    @DisplayName("한 프레임에서 두번 이상 투구할 수 없다.")
    @Test
    void error() {
        assertThat(normalFrame.addPinCount(1)).isTrue();
        assertThat(normalFrame.addPinCount(1)).isTrue();
        assertThat(normalFrame.addPinCount(1)).isFalse();
    }

    @DisplayName("2회 시도하면 한 프레임은 끝이 난다.")
    @Test
    void done() {
        normalFrame.addPinCount(8);
        assertThat(normalFrame.isDone()).isFalse();
        normalFrame.addPinCount(1);
        assertThat(normalFrame.isDone()).isTrue();
    }

    @DisplayName("스트라이크 치면 프레임은 끝난다.")
    @Test
    void strikeDone() {
        normalFrame.addPinCount(10);
        assertThat(normalFrame.isDone()).isTrue();
    }
}
