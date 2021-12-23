package bowling.domain.state.progress.end.second;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.state.end.second.Spare;
import bowling.domain.state.progress.FirstProgress;
import bowling.domain.state.progress.SecondProgress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpareTest {

    private Spare spare;

    @BeforeEach
    void init() {
        FirstProgress firstProgress = new FirstProgress(Pin.of(3));
        SecondProgress mockSecondProgress = new SecondProgress(Pin.of(7), firstProgress);

        spare = new Spare(mockSecondProgress);
    }

    @Test
    @DisplayName("Spare 표기가 잘 되는지 확인한다.")
    void getPrintMarkTest() {
        assertThat(spare.getPrintMark()).isEqualTo("3|/");
    }
}
