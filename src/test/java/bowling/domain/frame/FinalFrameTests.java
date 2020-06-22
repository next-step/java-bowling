package bowling.domain.frame;

import bowling.domain.frameStatus.FinalFrameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;

    @DisplayName("초구로 맞춘 핀의 수와 아홉번째 프레임을 입력 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        NormalFrame ninthFrame = NormalFrame.start(FIVE).bowl(FIVE);
        FinalFrame finalFrame = FinalFrame.firstBowl(FIVE, ninthFrame);

        assertThat(finalFrame).isEqualTo(new FinalFrame(10, FinalFrameStatus.bowlFirst(FIVE), ninthFrame));
    }
}
