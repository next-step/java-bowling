package bowling.domain;

import static bowling.domain.Pins.START_PIN_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame();
    }

    @Test
    void createTest() {
        Frame frame = new Frame();
        assertThat(frame.remainedPins()).isEqualTo(START_PIN_COUNT);
    }

    @DisplayName("현재 프레임과 연결된 다음 프레임을 만들어서 연결한다.")
    @Test
    void createTest2() {
        Frame currentFrame = new Frame();
        Frame nextFrame = currentFrame.createNext();
        Frame beforeFrame = currentFrame.createBefore();

        assertThat(nextFrame.next()).isNull();
        assertThat(beforeFrame.before()).isNull();
        assertThat(currentFrame).isEqualTo(nextFrame.before());
        assertThat(currentFrame.before()).isEqualTo(beforeFrame);
        assertThat(currentFrame).isEqualTo(beforeFrame.next());
        assertThat(currentFrame.next()).isEqualTo(nextFrame);
    }

    @DisplayName("프레임에서 첫번째샷, 두번째샷 넘어뜨린 점수를 기록 성공한다.")
    @Test
    void scoreTest1() {
        frame.shot(4);
        assertThat(frame.firstScore()).isEqualTo(4);

        frame.shot(6);
        assertThat(frame.secondScore()).isEqualTo(6);
    }

    @DisplayName("프레임에서 첫번째샷 후 남은 핀보다 큰 수를 두번째샷 넘어뜨린 수로 넣으면 예외 발생한다.")
    @Test
    void scoreTest2() {
        frame.shot(4);
        assertThat(frame.firstScore()).isEqualTo(4);

        assertThatThrownBy(() -> {
            frame.shot(7);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("해당 프레임의 핀을 다 넘어뜨렸으면 턴 종료한다.")
    @Test
    void isDoneTest1() {
        assertThat(frame.isDone()).isFalse();

        frame.shot(10);

        assertThat(frame.isDone()).isTrue();
    }

    @DisplayName("2번 던졌으면 다 못넘어뜨렸어도 턴이 끝났다.")
    @Test
    void isDoneTest2() {
        assertThat(frame.isDone()).isFalse();

        frame.shot(3);
        frame.shot(5);

        assertThat(frame.isDone()).isTrue();
    }

    @DisplayName("일반적인 점수는 해당 프레임이 끝나면 곧바로 계산된다.")
    @Test
    void calculateScoreTest1() {
        frame.shot(9);    // 1
        frame.shot(0);    // 1

        assertThat(frame.scoreCalculated().getAsInt()).isEqualTo(9);
    }

    @DisplayName("스페어 추가 점수는 해당 프레임이 끝나고 다음 투구 1개의 점수가 계산된 후 계산된다.")
    @Test
    void calculateScoreTest2() {
        frame.shot(9);
        frame.shot(1);
        Frame next = frame.createNext();
        next.shot(2);

        assertThat(frame.scoreCalculated().getAsInt()).isEqualTo(12);
    }

    @DisplayName("스트라이크 추가 점수는 해당 프레임이 끝나고 다음 투구 2개의 점수가 계산된 후 계산된다.")
    @Test
    void calculateScoreTest3() {
        frame.shot(10);
        Frame second = frame.createNext();
        second.shot(2);
        second.shot(4);

        assertThat(frame.scoreCalculated().getAsInt()).isEqualTo(16);
    }

}
