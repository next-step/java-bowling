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

//    @DisplayName("일반적인 점수는 해당 프레임이 끝나면 곧바로 계산된다.")
//    @Test
//    void calculateScoreTest1() {
//        frames.throwBall(9);    // 1
//        frames.throwBall(0);    // 1
//        frames.throwBall(2);    // 2
//        frames.throwBall(4);    // 2
//
//        frames.calculateScore();
//
//        Frame firstFrame = frames.head();
//        Frame secondFrame = firstFrame.next();
//        Score firstFrameScore = firstFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//        Score secondFrameScore = secondFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//
//        assertThat(firstFrameScore.get()).isEqualTo(9);
//        assertThat(secondFrameScore.get()).isEqualTo(6);
//    }
//
//    @DisplayName("스페어 추가 점수는 해당 프레임이 끝나고 다음 투구 1개의 점수가 계산된 후 계산된다.")
//    @Test
//    void calculateScoreTest2() {
//        frames.throwBall(9);    // 1
//        frames.throwBall(0);    // 1
//        frames.throwBall(2);    // 2
//        frames.throwBall(8);    // 2
//
//        frames.calculateScore();
//
//        Frame firstFrame = frames.head();
//        Frame secondFrame = frames.head().next();
//        Score firstFrameScore = firstFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//        Score secondFrameScore = secondFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//
//        assertThat(firstFrameScore.get()).isEqualTo(9);
//        assertThat(secondFrameScore.get()).isZero();
//
//        frames.throwBall(5);
//
//        frames.calculateScore();
//
//        secondFrameScore = secondFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//        assertThat(secondFrameScore.get()).isEqualTo(15);
//    }
//
//    @DisplayName("스트라이크 추가 점수는 해당 프레임이 끝나고 다음 투구 2개의 점수가 계산된 후 계산된다.")
//    @Test
//    void calculateScoreTest3() {
//        frames.throwBall(9);    // 1
//        frames.throwBall(0);    // 1    // score: 9
//        frames.throwBall(10);    // 2    // score: Strike - 10 + 5 + 4
//        frames.calculateScore();
//
//        Frame firstFrame = frames.head();
//        Frame secondFrame = frames.head().next();
//        Score firstFrameScore = firstFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//        Score secondFrameScore = secondFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//
//        assertThat(firstFrameScore.get()).isEqualTo(9);
//        assertThat(secondFrameScore.get()).isZero();
//
//        frames.throwBall(5);    // 3
//        frames.calculateScore();
//
//        secondFrameScore = secondFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//        assertThat(secondFrameScore.get()).isZero();
//
//        frames.throwBall(4);    // 3    // score: 9
//        frames.calculateScore();
//
//        secondFrameScore = secondFrame.getFrameScoreAsOptional()
//            .orElse(new Score());
//        assertThat(secondFrameScore.get()).isEqualTo(19);
//    }

}
