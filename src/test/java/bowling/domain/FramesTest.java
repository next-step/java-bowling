package bowling.domain;

import static bowling.domain.Frames.BOWLING_FINAL_FRAMES;
import static bowling.domain.Frames.BOWLING_NORMAL_FRAMES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;
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

    @DisplayName("프레임들 객체에서 볼링게임 기본프레임 9개와 최종프레임 1개를 생성한다.")
    @Test
    void createTest2() {
        Frames frames = new Frames();

        assertThat(frames.size()).isEqualTo(BOWLING_NORMAL_FRAMES + BOWLING_FINAL_FRAMES);
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

        assertThat(frames.head().firstScore()).isEqualTo(4);
        assertThat(frames.head().secondScore()).isEqualTo(3);
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

        assertThat(frames.head().firstScore()).isEqualTo(9);
        assertThat(frames.head().secondScore()).isEqualTo(1);
        assertThat(frames.head().next().firstScore()).isEqualTo(10);
        assertThat(frames.current().before().firstScore()).isEqualTo(8);
        assertThat(frames.current().before().secondScore()).isEqualTo(1);
    }

    @DisplayName("10프레임에 스트라이크를 치면 2번 볼 던지기 추가 진행 가능하다.")
    @Test
    void throwBallTest4() {
        throwBallNineFrames();

        frames.throwBall(10);   // 10
        frames.throwBall(10);   // 10 + 추가시기 1번째
        frames.throwBall(10);   // 10 + 추가시기 2번째

        assertThat(frames.isEndGame()).isTrue();
    }

    @DisplayName("10프레임에 스패어를 치면 1번 볼 던지기 추가 진행 가능하다.")
    @Test
    void throwBallTest5() {
        throwBallNineFrames();

        frames.throwBall(4);    // 10
        frames.throwBall(6);    // 10
        frames.throwBall(10);   // 10 + 추가시기 1번째

        assertThat(frames.isEndGame()).isTrue();
    }

    @DisplayName("10프레임에서 최대 3번 넘는 볼 던지기는 불가하다.")
    @Test
    void throwBallTest6() {
        throwBallNineFrames();

        frames.throwBall(10);   // 10
        frames.throwBall(10);   // 10 + 추가시기 1번째
        frames.throwBall(10);   // 10 + 추가시기 2번째

        assertThatThrownBy(() -> {
            frames.throwBall(10);
        }).isInstanceOf(NullPointerException.class);
    }

    private void throwBallNineFrames() {
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

    @DisplayName("일반적인 점수는 해당 프레임이 끝나면 곧바로 계산된다.")
    @Test
    void calculateScoreTest1() {
        frames.throwBall(9);    // 1
        frames.throwBall(0);    // 1
        frames.throwBall(2);    // 2
        frames.throwBall(4);    // 2

        Frame firstFrame = frames.head();
        Frame secondFrame = firstFrame.next();

        assertThat(firstFrame.scoreCalculated()).contains(9);
        assertThat(secondFrame.scoreCalculated()).contains(6);
    }

    @DisplayName("스페어 추가 점수는 해당 프레임이 끝나고 다음 투구 1개의 점수가 계산된 후 계산된다.")
    @Test
    void calculateScoreTest2() {
        frames.throwBall(9);    // 1
        frames.throwBall(0);    // 1
        frames.throwBall(2);    // 2
        frames.throwBall(8);    // 2

        Frame firstFrame = frames.head();
        Frame secondFrame = firstFrame.next();

        assertThat(firstFrame.scoreCalculated()).contains(9);
        assertThat(secondFrame.scoreCalculated()).isEmpty();

        frames.throwBall(10);
        assertThat(secondFrame.scoreCalculated()).contains(20);
    }

    @DisplayName("마지막 프레임의 점수는 그냥 있는 점수 다 더한다.")
    @Test
    void calculateScoreTest3() {
        throwBallNineFrames();
        Frame finalFrame = frames.current();

        frames.throwBall(9);

        assertThat(finalFrame.scoreCalculated()).contains(9);

        frames.throwBall(1);

        assertThat(finalFrame.scoreCalculated()).contains(10);

        frames.throwBall(10);

        assertThat(finalFrame.scoreCalculated()).contains(20);
    }

}
