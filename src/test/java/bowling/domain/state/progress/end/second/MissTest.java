package bowling.domain.state.progress.end.second;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.GeneralFrame;
import bowling.domain.state.end.second.Miss;
import bowling.domain.state.progress.FirstProgress;
import bowling.domain.state.progress.GeneralProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MissTest {

    private final Frame frame = new GeneralFrame(null);
    private Miss miss;

    @BeforeEach
    void init() {
        FirstProgress firstProgress = new FirstProgress(frame, Pin.of(3));
        GeneralProgress mockGeneralProgress = new GeneralProgress(frame, Pin.of(0), firstProgress);

        miss = new Miss(mockGeneralProgress);
    }

    @Test
    @DisplayName("Miss 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(miss.getPrintMark()).isEqualTo("3|-");
    }

}