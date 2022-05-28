package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("해당 프레임의 Number를 반환한다.")
    @Test
    void numberTest() {
        assertThat(new NormalFrame(3).number()).isEqualTo(3);
    }

    @DisplayName("처음 상태에서 10개 미만의 pin을 쓰러뜨리면 FirstBowl을 상태로 가지는 현재 프레임을 반환한다.")
    @Test
    void bowlTestFirstBowl() {
        assertThat(NormalFrame.first().bowl(5))
                .isEqualTo(new NormalFrame(1, new FirstBowl(new Pins(5))));
    }

    @DisplayName("처음 상태에서 두 번 pin을 쓰러뜨렸을 때 합이 10개면 해당 프레임의 상태는 Spare")
    @Test
    void bowlTestSpare() {
        NormalFrame first = NormalFrame.first();
        first.bowl(5).bowl(5);
        assertThat(first).isEqualTo(new NormalFrame(1, new Spare(new Pins(5), new Pins(5))));
    }

    @DisplayName("처음 상태에서 두 번 pin을 쓰러뜨렸을 때 합이 10개 미만이면 해당 프레임의 상태는 Miss")
    @Test
    void bowlTestMiss() {
        NormalFrame first = NormalFrame.first();
        first.bowl(5).bowl(4);
        assertThat(first).isEqualTo(new NormalFrame(1, new Miss(new Pins(5), new Pins(4))));
    }

    @DisplayName("처음 상태에서 한 번에 10개를 쓰러뜨리면 해당 프레임의 상태는 Strike")
    @Test
    void bowlTestStrike() {
        NormalFrame first = NormalFrame.first();
        first.bowl(10);
        assertThat(first).isEqualTo(new NormalFrame(1, new Strike()));
    }

    @DisplayName("현재 프레임의 상태가 Miss일 때 계산할 수 있다.")
    @Test
    void canCalculateCurrentScoreTestMiss() {
        assertThat(new NormalFrame(3, new Miss(new Pins(5), new Pins(2))).canCalculateCurrentScore())
                .isTrue();
    }

    @DisplayName("현재 프레임의 상태가 Spare일 때 다음 상태에서 볼링 공을 한 번 이상 굴렸을 경우 계산할 수 있다.")
    @Test
    void canCalculateCurrentScoreTestSpare() {
        NormalFrame frame = new NormalFrame(3, new Spare(new Pins(5), new Pins(5)));
        NormalFrame next = frame.next();
        assertThat(frame.canCalculateCurrentScore()).isFalse();

        next.bowl(1);
        assertThat(frame.canCalculateCurrentScore()).isTrue();
    }

    @DisplayName("현재 프레임의 상태가 Strike일 때 이 후에 볼링 공을 두 번 이상 굴렸을 경우 계산할 수 있다.")
    @Test
    void canCalculateCurrentScoreTestStrike() {
        NormalFrame frame = new NormalFrame(3, new Strike());
        NormalFrame next = frame.next();
        assertThat(frame.canCalculateCurrentScore()).isFalse();

        next.bowl(2);
        assertThat(frame.canCalculateCurrentScore()).isFalse();

        next.bowl(3);
        assertThat(frame.canCalculateCurrentScore()).isTrue();
    }

    @DisplayName("이전 점수를 입력 받아 점수를 계산할 수 있는지 여부를 반환한다.")
    @Test
    void canCalculatePrefixSumScoreTest() {
        NormalFrame first = new NormalFrame(3, new Strike());
        NormalFrame second = first.next();
        NormalFrame third = second.next();

        second.bowl(10);
        third.bowl(5);

        assertThat(second.canCalculatePrefixSumScore(new Strike().score())).isTrue();
    }

    @DisplayName("현재 프레임의 점수를 반환한다.")
    @Test
    void scoreTest() {
        NormalFrame frame = new NormalFrame(3, new Spare(new Pins(5), new Pins(5)));
        NormalFrame next = frame.next();
        next.bowl(5);

        assertThat(frame.score()).isEqualTo(new Score(15, 0));
    }

    @DisplayName("이전 프레임으로부터 입력받은 점수와 현재 프레임의 점수를 합해 반환한다.")
    @Test
    void prefixSumScoreTest() {
        NormalFrame frame = new NormalFrame(3, new Spare(new Pins(5), new Pins(5)));
        NormalFrame next = frame.next();
        next.bowl(5);

        assertThat(next.prefixSumScore(new Score(10, 1)))
                .isEqualTo(new Score(15, 0));
    }
}