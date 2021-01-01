package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.frame.FinalFrame;
import step2.domain.frame.Frame;
import step2.domain.frame.NormalFrame;
import step2.domain.state.Miss;
import step2.domain.state.Spare;
import step2.domain.state.Strike;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalFrameTest {

    @Test
    @DisplayName("NormalFrame 초기화")
    void init() {
        assertThat(NormalFrame.init()).isInstanceOf(Frame.class);
    }

    @Test
    @DisplayName("NormalFrame 투구시 현재 인스턴스 반환")
    void bowl() {
        Frame frame = NormalFrame.init();

        assertThat(frame.bowl(9)).isSameAs(frame);
    }

    @Test
    @DisplayName("NormalFrame 투구시 새로운 NormalFrame 반환")
    void bowlReturnNewNormalFrame() {
        Frame frame = NormalFrame.init();

        assertThat(frame.bowl(10)).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("NormalFrame 투구시 FinalFrame 반환")
    void bowlReturnFinalFrame() {
        Frame frame = new NormalFrame(9);

        assertThat(frame.bowl(10)).isInstanceOf(FinalFrame.class);
    }

}