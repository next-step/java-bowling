package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.BowlingGameFrameTestFixture.createNormalBowlingGameFrame;
import static org.assertj.core.api.Assertions.*;

class NormalBowlingGameFrameTest {

    @DisplayName("투구 기록 추가하기")
    @Test
    void add() {
        BowlingGameFrame history = createNormalBowlingGameFrame();
        history.add(3);
        history.add(5);
        assertThat(history).isEqualTo(createNormalBowlingGameFrame(3, 5));
    }

    @DisplayName("투구 기록을 추가할 때, 0 보다 작은 정수를 입력하면, 예외가 발생해야 한다.")
    @Test
    void add_givenNegative() {
        BowlingGameFrame history = createNormalBowlingGameFrame();
        assertThatIllegalArgumentException()
                .isThrownBy(() -> history.add(-1))
                .withMessage("투구는 0 보다 작을 수 없습니다.");
    }

    @DisplayName("투구 기록을 추가할 때, 남은 핀의 수보다 큰 정수를 입력하면, 예외가 발생해야 한다.")
    @Test
    void add_givenOverRemainedPins() {
        BowlingGameFrame history = createNormalBowlingGameFrame();
        history.add(4);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> history.add(7))
                .withMessage("투구는 남은 핀의 개수(6) 보다 클 수 없습니다.");
    }

    @DisplayName("투구 기록을 2회보다 많이 추가할 때, 예외가 발생해야 한다.")
    @Test
    void add_whenOver2Times() {
        BowlingGameFrame bowlingGameFrame = createNormalBowlingGameFrame();
        bowlingGameFrame.add(3);
        bowlingGameFrame.add(4);
        assertThatIllegalStateException()
                .isThrownBy(() -> bowlingGameFrame.add(1))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

    @DisplayName("두번째 투구 기록을 추가할 때, 스트라이크라면, 예외가 발생해야 한다.")
    @Test
    void add_givenStrike() {
        BowlingGameFrame history = createNormalBowlingGameFrame();
        history.add(10);
        assertThatIllegalStateException()
                .isThrownBy(() -> history.add(0))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

    @DisplayName("스트라이크라면, 2번의 투구를 더 했을때, 점수를 가져야 한다.")
    @Test
    void hasScore_givenStrike() {
        BowlingGameFrame frame3 = new NormalBowlingGameFrame(null);
        BowlingGameFrame frame2 = new NormalBowlingGameFrame(frame3);
        BowlingGameFrame frame1 = new NormalBowlingGameFrame(frame2);
        frame1.add(10);
        assertThat(frame1.hasScore()).isFalse();

        frame2.add(10);
        assertThat(frame1.hasScore()).isFalse();

        frame3.add(9);
        assertThat(frame1.hasScore()).isTrue();
    }

    @DisplayName("스페어라면, 1번의 투구를 더 했을 때, 점수를 가져야 한다.")
    @Test
    void hasScore_givenSpare() {
        BowlingGameFrame frame2 = new NormalBowlingGameFrame(null);
        BowlingGameFrame frame1 = new NormalBowlingGameFrame(frame2);
        frame1.add(9);
        frame1.add(1);
        assertThat(frame1.hasScore()).isFalse();

        frame2.add(10);
        assertThat(frame1.hasScore()).isTrue();
    }

    @DisplayName("미스라면, 추가적인 투구 없이 점수를 가져야 한다.")
    @Test
    void hasScore_givenMiss() {
        BowlingGameFrame frame = new NormalBowlingGameFrame(null);
        frame.add(8);
        frame.add(1);
        assertThat(frame.hasScore()).isTrue();
    }

    @DisplayName("스트라이크라면, 2번의 투구를 더한 점수를 가져야 한다.")
    @Test
    void getScore_givenStrike() {
        BowlingGameFrame frame3 = new NormalBowlingGameFrame(null);
        BowlingGameFrame frame2 = new NormalBowlingGameFrame(frame3);
        BowlingGameFrame frame1 = new NormalBowlingGameFrame(frame2);
        frame1.add(10);
        frame2.add(10);
        frame3.add(9);

        assertThat(frame1.getScore()).isEqualTo(29);
    }

    @DisplayName("스페어라면, 1번의 투구를 더한 점수를 가져야 한다.")
    @Test
    void getScore_givenSpare() {
        BowlingGameFrame frame2 = new NormalBowlingGameFrame(null);
        BowlingGameFrame frame1 = new NormalBowlingGameFrame(frame2);
        frame1.add(9);
        frame1.add(1);
        frame2.add(10);

        assertThat(frame1.getScore()).isEqualTo(20);
    }

    @DisplayName("미스라면, 추가적인 투구 없이 점수를 가져야 한다.")
    @Test
    void getScore_givenMiss() {
        BowlingGameFrame frame = new NormalBowlingGameFrame(null);
        frame.add(8);
        frame.add(1);

        assertThat(frame.getScore()).isEqualTo(9);
    }

}
