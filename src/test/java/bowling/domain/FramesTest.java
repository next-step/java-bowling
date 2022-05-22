package bowling.domain;

import static bowling.domain.Frames.BOWLING_NORMAL_FRAMES;
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
    }

    @DisplayName("프레임들 객체에서 볼링게임 기본프레임 10개를 생성한다.")
    @Test
    void createTest2() {
        Frames frames = new Frames();

        assertThat(frames.size()).isEqualTo(BOWLING_NORMAL_FRAMES);
    }

    @DisplayName("프레임들 생성 후 현재프레임은 가장 앞 프레임이다.")
    @Test
    void createTest3() {
        Frames frames = new Frames();

        assertThat(frames.current()).isSameAs(frames.head());
    }

    @Test
    void throwBallTest1() {
        frames.throwBall(4);
        frames.throwBall(3);

        assertThat(frames.head().getFirstScore().get()).isEqualTo(4);
        assertThat(frames.head().getSecondScore().get()).isEqualTo(3);
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

        assertThat(frames.head().getFirstScore().get()).isEqualTo(9);
        assertThat(frames.head().getSecondScore().get()).isEqualTo(1);
        assertThat(frames.head().next().getFirstScore().get()).isEqualTo(10);
        assertThat(frames.head().next().getSecondScore()).isNull();
        assertThat(frames.current().before().getFirstScore().get()).isEqualTo(8);
        assertThat(frames.current().before().getSecondScore().get()).isEqualTo(1);
    }

    @DisplayName("10프레임에 스트라이크를 치면 2프레임 추가 진행 가능하다.")
    @Test
    void throwBallTest4() {
        throwBallNineTimes();

        frames.throwBall(10);   // 10
        frames.throwBall(10);   // 11
        frames.throwBall(10);   // 12

        assertThat(frames.isEndGame()).isTrue();
    }

    @DisplayName("10프레임에 스패어를 치면 1프레임 추가 진행 가능하다.")
    @Test
    void throwBallTest5() {
        throwBallNineTimes();

        frames.throwBall(4);    // 10
        frames.throwBall(6);    // 10
        frames.throwBall(10);   // 11

        assertThat(frames.isEndGame()).isTrue();
    }

    @DisplayName("최대 12프레임을 넘는 게임 진행은 불가하다.")
    @Test
    void throwBallTest6() {
        throwBallNineTimes();

        frames.throwBall(10);   // 10
        frames.throwBall(10);   // 11
        frames.throwBall(10);   // 12

        assertThatThrownBy(() -> {
            frames.throwBall(10);
        }).isInstanceOf(NullPointerException.class);
    }

    private void throwBallNineTimes() {
        frames.throwBall(10);
        frames.throwBall(10);
        frames.throwBall(10);
        frames.throwBall(10);
        frames.throwBall(10);
        frames.throwBall(10);
        frames.throwBall(10);
        frames.throwBall(10);
        frames.throwBall(10);
    }

}
