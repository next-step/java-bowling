package bowling;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    void 프레임_생성() {
        assertThat(FinalFrame.of(5).getScore()).isEqualTo(5);
    }

    @Test
    void 현재_프레임에_턴이_끝났는지_확인() {
        FinalFrame finalFrame = FinalFrame.of(5);
        finalFrame.bowl(3);
        assertThat(finalFrame.isFinished()).isTrue();
    }

    @Test
    void 보너스볼30점() {
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.bowl(10);
        finalFrame.bonusBowl(10);
        assertThat(finalFrame.getScore()).isEqualTo(30);
    }
}
