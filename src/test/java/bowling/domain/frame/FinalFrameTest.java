package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
    }

    @DisplayName("프레임의 쓰러트린 핀 갯수를 저장할 수 있다.")
    @Test
    void init() {
        finalFrame.addPinCount(1);
        assertThat(finalFrame.getScore()).isEqualTo(1);
    }

    @DisplayName("한 프레임에서 세번 이상 투구할 수 없다.")
    @Test
    void overThreePitch() {
        assertThat(finalFrame.addPinCount(10)).isTrue();
        assertThat(finalFrame.addPinCount(1)).isTrue();
        assertThat(finalFrame.addPinCount(1)).isTrue();
        assertThat(finalFrame.addPinCount(1)).isFalse();
    }

    @DisplayName("첫번째 시도가 스트라이크가 아닐경우, 두번째 시도의 핀 갯수와 첫번째 시도의 핀 갯수가 10을 넘을 수 없다")
    @Test
    void error() {
        assertThat(finalFrame.addPinCount(8)).isTrue();
        assertThat(finalFrame.addPinCount(3)).isFalse();
    }

    @DisplayName("첫번째 시도가 스트라크일 경우, 두번째 핀의 갯수는 아무거나 관계 없다")
    @Test
    void secondPinAfterStrike() {
        finalFrame.addPinCount(10);
        finalFrame.addPinCount(8);
        assertThat(finalFrame.getScore()).isEqualTo(18);
    }

    @DisplayName("두번째 시도에서 스페어 처리를 못하면 세번째 기회는 없다.")
    @Test
    void donAtSecond() {
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(1);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("두번째 시도에서 스페어 처리 되면 한번의 기회를 얻는다")
    @Test
    void getThirdOpportunityWhenSpare() {
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(2);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("첫번째 시도에서 스트라이크 되면 두번의 기회를 얻는다")
    @Test
    void getTwoOpportunityWhenStrike() {
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(2);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(3);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("모든 시도가 스트라이크여도 세번의 기회가 주어진다.")
    @Test
    void onlyThreeOpportunity() {
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isTrue();
    }
}
