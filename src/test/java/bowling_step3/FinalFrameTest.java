package bowling_step3;

import bowling_step3.domain.Frame.FinalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FinalFrameTest {

    @ParameterizedTest
    @DisplayName("마지막 프레임 투구 & 점수")
    @CsvSource(value = {"4,4, 8", "1,2, 3", "4,2, 6"})
    public void 마지막_프레임_투구(int firstOfKnockDown, int secondOfKnockDown, int score) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(firstOfKnockDown);
        frame.pitch(secondOfKnockDown);

        assertThat(frame.getScore()).isEqualTo(score);
    }

    @ParameterizedTest
    @DisplayName("마지막 프레임 스트라이크_스페어인 경우 3번 투구에 대한 점수")
    @CsvSource(value = {"10,10,10, 30", "10,1,2, 13", "9,1,10, 20"})
    public void 마지막_프레임_스트라이크_스페어인경우_3번투구(int firstOfKnockDown, int secondOfKnockDown, int thirdOfKnockDown, int score) {
        FinalFrame frame = new FinalFrame();
        frame.pitch(firstOfKnockDown);
        frame.pitch(secondOfKnockDown);
        frame.pitch(thirdOfKnockDown);

        assertThat(frame.getScore()).isEqualTo(score);
    }

    @Test
    @DisplayName("마지막 프레임 쓰러뜨린 핀 10개 초과시 예외처리")
    public void 마지막프레임_핀_쓰러뜨린_핀_10개초과_예외처리() {
        FinalFrame frame = new FinalFrame();
        assertThatThrownBy(() -> frame.pitch(11))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
