package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTests {
    private static final int HIT_PIN_FIVE = 5;
    private static final int HIT_PIN_STRIKE = 10;

    @DisplayName("첫 투구를 입력받아서 객체를 생성할 수 있다")
    @Test
    void createTest() {
        FinalFrame finalFrame = FinalFrame.firstBowl(HIT_PIN_FIVE);

        assertThat(finalFrame).isNotNull();
    }

    @DisplayName("스트라이크 두번이면 마지막 프레임이 마무리된다.")
    @Test
    void twoStrikeTest() {
        FinalFrame finalFrame = FinalFrame.firstBowl(HIT_PIN_STRIKE);
        finalFrame.bowl(HIT_PIN_STRIKE);

        assertThat(finalFrame.isCompleted()).isTrue();
    }
}
