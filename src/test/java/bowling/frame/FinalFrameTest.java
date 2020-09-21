package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private Frame frame;

    @Test
    @DisplayName("FinalFrame 생성")
    void create() {
        frame = FinalFrame.create(10);
        assertThat(frame.getFrameNumber()).isEqualTo(10);
    }

    @Test
    @DisplayName("FinalFrame 여부 확인 11이면 종료")
    void isFinal() {
        frame = FinalFrame.create(11);
        assertThat(frame.isFinal()).isTrue();
    }

    @Test
    @DisplayName("FinalFrame 종료. Next로 이동")
    void next() {
        frame = FinalFrame.create(10);
        assertThat(frame.next().getFrameNumber()).isEqualTo(11);
    }

}
