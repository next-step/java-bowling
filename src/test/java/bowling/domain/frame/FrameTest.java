package bowling.domain.frame;

import bowling.domain.state.NextReady;
import bowling.domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    @DisplayName("프레임 객체 비교")
    void equalsToFrame() {
        Frame frame = new Frame(1);
        Frame expectedFrame = new Frame(1);

        boolean same = frame.equals(expectedFrame);

        assertThat(same).isTrue();
    }

    @Test
    @DisplayName("bowl 이후 생성되는 상태 비교")
    void compareToStateByAfterBowl() {
        Frame frame = new Frame(1);
        State expectedFrame = frame.bowl(2);

        boolean same = frame.getState().equals(expectedFrame);

        assertThat(same).isTrue();
        assertThat(expectedFrame instanceof NextReady).isTrue();
    }

    @Test
    @DisplayName("프레임 상태 체크 - 미스")
    void checkMissByFrameStatus() {
        Frame frame = new Frame(1);
        frame.bowl(2);
        frame.bowl(2);

        boolean miss = frame.isMiss();

        assertThat(miss).isTrue();
    }

    @Test
    @DisplayName("프레임 상태 체크 - 스페어")
    void checkSpareByFrameStatus() {
        Frame frame = new Frame(1);
        frame.bowl(2);
        frame.bowl(8);

        boolean spare = frame.isSpare();

        assertThat(spare).isTrue();
    }

    @Test
    @DisplayName("프레임 상태 체크 - 스트라이크")
    void checkStrikeByFrameStatus() {
        Frame frame = new Frame(1);
        frame.bowl(10);

        boolean strike = frame.isStrike();

        assertThat(strike).isTrue();
    }
}
