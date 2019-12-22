package bowling;

import bowling.domain.Frame2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Frame2Test {

    @Test
    @DisplayName("프레임 스코어 가져오기")
    void getScoreByFrame() {
        Frame2 frame2 = new Frame2(0);
        frame2.bowl(10);
        frame2.bowl(8);
        frame2.bowl(2);
        assertThat(frame2.getScore()).isEqualTo(20);
    }
}
