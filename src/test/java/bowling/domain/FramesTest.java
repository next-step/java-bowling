package bowling.domain;

import static bowling.domain.Frames.BOWLING_FRAMES_DEFAULT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();
    }

    @Test
    void createTest() {
        Frames frames = new Frames();

        assertThat(frames).isNotNull();
        assertThat(frames.get()).hasSize(BOWLING_FRAMES_DEFAULT);
    }

    @DisplayName("프레임들 객체에서 볼링게임 프레임 12개를 생성한다.")
    @Test
    void createTest2() {
        Frames frames = new Frames();

        assertThat(frames.get()).hasSize(BOWLING_FRAMES_DEFAULT);
    }

    @DisplayName("프레임들 생성 후 현재프레임은 가장 앞 프레임이다.")
    @Test
    void createTest3() {
        Frames frames = new Frames();

        assertThat(frames.getCurrent()).isSameAs(frames.get().get(0));
        assertThat(frames.getCurrent()).isNotSameAs(frames.get().get(BOWLING_FRAMES_DEFAULT -1));
    }

    @Test
    void throwBallTest1() {
        frames.throwBall(4);
        frames.throwBall(3);

        assertThat(frames.get().get(0).getFirstScore().get()).isEqualTo(4);
        assertThat(frames.get().get(0).getSecondScore().get()).isEqualTo(3);
    }

    @DisplayName("유효하지 않은 맞춘개수 입력 시 예외 발생한다.")
    @Test
    void throwBallTest2() {
        assertThatThrownBy(() -> {
            frames.throwBall(-1);
        }).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> {
            frames.throwBall(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("한프레임 다 치고나면 다음 프레임으로 자동으로 넘어가서 쳐진다.")
    @Test
    void throwBallTest3() {
        frames.throwBall(9);    // 1
        frames.throwBall(1);    // 1
        frames.throwBall(10);   // 2
        frames.throwBall(8);    // 3
        frames.throwBall(1);    // 3

        assertThat(frames.get().get(0).getFirstScore().get()).isEqualTo(9);
        assertThat(frames.get().get(0).getSecondScore().get()).isEqualTo(1);
        assertThat(frames.get().get(1).getFirstScore().get()).isEqualTo(10);
        assertThat(frames.get().get(1).getSecondScore()).isNull();
        assertThat(frames.get().get(2).getFirstScore().get()).isEqualTo(8);
        assertThat(frames.get().get(2).getSecondScore().get()).isEqualTo(1);
    }

    @DisplayName("10프레임에 스트라이크를 치면 2프레임 추가한다.")
    @Test
    void throwBallTest4() {

    }

    @DisplayName("10프레임에 스패어를 치면 1프레임 추가한다.")
    @Test
    void throwBallTest5() {

    }

}
