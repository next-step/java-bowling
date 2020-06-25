package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FinalFrame 클래스 테스트")
public class FinalFrameTest {

    @DisplayName("볼링 결과를 갖는다.")
    @Test
    void bowl() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(new Pin(10));

        assertThat(finalFrame.getStates().getStates().get(0)).isEqualTo(State.STRIKE);
        assertThat(finalFrame.getPin()).isEqualTo(new Pin(10));
    }

}
