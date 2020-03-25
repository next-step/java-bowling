package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    @DisplayName("객체 생성 비교")
    void compareToFinalFrame() {
        // give
        Frame actualFinalFrame = new FinalFrame(10);
        Frame expectedFinalFrame = new FinalFrame(10);
        // when
        boolean same = actualFinalFrame.equals(expectedFinalFrame);
        // then
        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("점수 확인")
    void getScore() {
        Frame frameWithBonus = new FinalFrame(10);
        frameWithBonus.bowl(10);
        frameWithBonus.bowl(8);
        frameWithBonus.bowl(7);

        Frame frame = new FinalFrame(10);
        frame.bowl(7);
        frame.bowl(2);

        assertThat(frameWithBonus.getScore()).isEqualTo(25);
        assertThat(frame.getScore()).isEqualTo(9);
    }
}
