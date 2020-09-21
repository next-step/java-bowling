package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private Frame frame;

    @Test
    @DisplayName("First NomalFrame 생성")
    void createFirst() {
        frame = NormalFrame.first();
        assertThat(frame).isNotNull();
        assertThat(frame.getFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("NormalFrame 생성")
    void create() {
        frame = NormalFrame.create(2);
        assertThat(frame.getFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("FinalFrame 여부 확인")
    void isFinal() {
        frame = NormalFrame.create(7);
        assertThat(frame.isFinal()).isFalse();
    }

    @Test
    @DisplayName("Frame 종료. Next Frame 추가")
    void next() {
        frame = NormalFrame.create(5);
        assertThat(frame.next().getFrameNumber()).isEqualTo(6);
    }

}
