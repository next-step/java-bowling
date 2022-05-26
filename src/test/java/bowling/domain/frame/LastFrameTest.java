package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LastFrameTest {

    @DisplayName("해당 프레임의 Number를 반환한다.")
    @Test
    void numberTest() {
        assertThat(new LastFrame().number()).isEqualTo(10);
    }

    @DisplayName("처음 상태에서 10개 미만의 pin을 쓰러뜨리면 FirstBowl을 상태로 가지는 현재 프레임을 반환한다.")
    @Test
    void bowlTestFirstBowl() {
        assertThat(new LastFrame().bowl(5))
                .isEqualTo(new LastFrame(new FirstBowl(new Pins(5))));
    }

    @DisplayName("처음 상태에서 두 번 pin을 쓰러뜨렸을 때 합이 10개면 해당 프레임의 상태는 Spare")
    @Test
    void bowlTestSpare() {
        LastFrame lastFrame = new LastFrame(new Ready());
        lastFrame.bowl(5).bowl(5);
        assertThat(lastFrame).isEqualTo(new LastFrame(new Spare(new Pins(5), new Pins(5))));
    }

    @DisplayName("처음 상태에서 두 번 pin을 쓰러뜨렸을 때 합이 10개 미만이면 해당 프레임의 상태는 Miss")
    @Test
    void bowlTestMiss() {
        LastFrame lastFrame = new LastFrame(new Ready());
        lastFrame.bowl(5).bowl(4);
        assertThat(lastFrame).isEqualTo(new LastFrame(new Miss(new Pins(5), new Pins(4))));
    }

    @DisplayName("처음 상태에서 한 번에 10개를 쓰러뜨리면 해당 프레임의 상태는 Strike")
    @Test
    void bowlTestStrike() {
        LastFrame lastFrame = new LastFrame(new Ready());
        lastFrame.bowl(10);
        assertThat(lastFrame).isEqualTo(new LastFrame(new Strike()));
    }

    @DisplayName("현재 프레임의 상태가 Miss일 때 계산할 수 있다.")
    @Test
    void canCalculateCurrentScoreTestMiss() {
        assertThat(new LastFrame(new Miss(new Pins(5), new Pins(2))).canCalculateCurrentScore())
                .isTrue();
    }

    @DisplayName("현재 프레임의 상태가 Spare일 때 다음 상태에서 볼링 공을 한 번 이상 굴렸을 경우 계산할 수 있다.")
    @Test
    void canCalculateCurrentScoreTestSpare() {
        LastFrame frame = new LastFrame();
        frame.bowl(5);
        assertThat(frame.canCalculateCurrentScore()).isFalse();

        frame.bowl(5);
        assertThat(frame.canCalculateCurrentScore()).isFalse();

        frame.bowl(3);
        assertThat(frame.canCalculateCurrentScore()).isTrue();
    }

    @DisplayName("현재 프레임의 상태가 Strike일 때 이 후에 볼링 공을 두 번 이상 굴렸을 경우 계산할 수 있다.")
    @Test
    void canCalculateCurrentScoreTestStrike() {
        LastFrame frame = new LastFrame();
        frame.bowl(10);
        assertThat(frame.canCalculateCurrentScore()).isFalse();

        frame.bowl(3);
        assertThat(frame.canCalculateCurrentScore()).isFalse();

        frame.bowl(5);
        assertThat(frame.canCalculateCurrentScore()).isTrue();

    }

    @DisplayName("이전 점수를 입력 받아 점수를 계산할 수 있는지 여부를 반환한다.")
    @Test
    void canCalculatePrefixSumScoreTest() {
        NormalFrame frame = new NormalFrame(9, new Strike());
        LastFrame last = frame.last();

        last.bowl(10);
        assertThat(frame.canCalculateCurrentScore()).isFalse();

        last.bowl(3);
        assertThat(frame.canCalculateCurrentScore()).isTrue();
    }

    @DisplayName("현재 프레임의 점수를 반환한다.")
    @Test
    void scoreTest() {
        LastFrame frame = new LastFrame();
        frame.bowl(3).bowl(2);
        assertThat(frame.score()).isEqualTo(new Score(5, 0));
    }

    @DisplayName("이전 프레임으로부터 입력받은 점수와 현재 프레임의 점수를 합해 반환한다.")
    @Test
    void prefixSumScoreTest() {
        LastFrame frame = new LastFrame();
        frame.bowl(3).bowl(2);
        assertThat(frame.prefixSumScore(new Score(10, 1))).isEqualTo(new Score(13, 0));
    }
}