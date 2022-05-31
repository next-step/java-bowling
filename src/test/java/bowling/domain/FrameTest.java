package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.state.FirstBowl;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
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
    void 프레임의_최초_준비상태에서_한번던진상태로_간다() {
        frame.shot(1);
        assertThat(frame.state()).isOfAnyClassIn(FirstBowl.class);
    }

    @Test
    void 프레임의_최초_준비상태에서_스트라이크상태로_간다() {
        frame.shot(10);
        assertThat(frame.state()).isOfAnyClassIn(Strike.class);
    }

    @Test
    void 프레임의_한번던진상태에서_스페어상태로_간다() {
        frame.shot(1);
        frame.shot(9);
        assertThat(frame.state()).isOfAnyClassIn(Spare.class);
    }

    @Test
    void 프레임의_한번던진상태에서_미스상태로_간다() {
        frame.shot(1);
        frame.shot(1);
        assertThat(frame.state()).isOfAnyClassIn(Miss.class);
    }

    @Test
    void 프레임에서_MISS_이후_던지는_기회가_끝났는지_확인한다() {
        assertThat(frame.isDone()).isFalse();
        frame.shot(1);
        frame.shot(1);
        assertThat(frame.isDone()).isTrue();
    }

    @Test
    void 프레임에서_SPARE_이후_던지는_기회가_끝났는지_확인한다() {
        assertThat(frame.isDone()).isFalse();
        frame.shot(5);
        frame.shot(5);
        assertThat(frame.isDone()).isTrue();
    }

    @Test
    void 프레임에서_STRIKE_이후_던지는_기회가_끝났는지_확인한다() {
        assertThat(frame.isDone()).isFalse();
        frame.shot(10);
        assertThat(frame.isDone()).isTrue();
    }
    
    @Test
    void createTest() {
        Frame frame = new Frame();
        assertThat(frame).isNotNull();
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

    @DisplayName("프레임에서 첫번째샷, 두번째샷 넘어뜨린 점수합산 기록을 성공한다.")
    @Test
    void scoreTest1() {
        frame.shot(4);
        frame.shot(4);

        assertThat(frame.scoreCalculated().getAsInt()).isEqualTo(8);
    }

    @DisplayName("프레임에서 첫번째샷 후 남은 핀보다 큰 수를 두번째샷 넘어뜨린 수로 넣으면 예외 발생한다.")
    @Test
    void scoreTest2() {
        frame.shot(4);
        assertThatThrownBy(() -> {
            frame.shot(7);
        }).isInstanceOf(IllegalArgumentException.class);
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

    @DisplayName("스페어 추가 점수는 해당 프레임이 끝나고 다음 투구 1개가 더해지기 전에는 계산되지 않는다.")
    @Test
    void calculateScoreTest3() {
        frame.shot(9);
        frame.shot(1);
        frame.createNext();

        assertThat(frame.scoreCalculated().isEmpty()).isTrue();
    }

    @DisplayName("스트라이크 추가 점수는 해당 프레임이 끝나고 다음 투구 2개의 점수가 계산된 후 계산된다.")
    @Test
    void calculateScoreTest4() {
        frame.shot(10);
        Frame second = frame.createNext();
        second.shot(2);
        second.shot(4);

        assertThat(frame.scoreCalculated().getAsInt()).isEqualTo(16);
    }

    @DisplayName("스트라이크 추가 점수는 해당 프레임이 끝나고 다음 투구 2개가 더해지기 전에는 계산되지 않는다")
    @Test
    void calculateScoreTest5() {
        frame.shot(10);
        Frame second = frame.createNext();
        second.shot(2);

        assertThat(frame.scoreCalculated().isEmpty()).isTrue();
    }

}
