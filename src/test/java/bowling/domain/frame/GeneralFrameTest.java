package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import bowling.Pin;
import bowling.domain.progress.Closed;
import bowling.domain.progress.GeneralProgress;
import bowling.domain.state.end.Strike;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneralFrameTest {

    private static final Pin MISS = Pin.of(0);
    private static final Pin NORMAL = Pin.of(5);
    private static final Pin STRIKE = Pin.of(10);

    private GeneralFrame generalFrame;

    @BeforeEach
    void init() {
        generalFrame = new GeneralFrame(null);
    }
    @Test
    @DisplayName("Strike시 해당 프레임은 종료된다.")
    void bowlStrikeTest() {
        assertThat(generalFrame.bowl(STRIKE)).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("일반 투구시, 해당 프레임은 다음 프레임으로 넘어간다.")
    void bowlGeneralTest() {
        assertThat(generalFrame.bowl(NORMAL)).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("MISS 시, 해당 프레임은 다음 프레임으로 넘어간다.")
    void bowlMissTest() {
        assertThat(generalFrame.bowl(MISS)).isInstanceOf(GeneralProgress.class);
    }

    @Test
    @DisplayName("Strike가 아닌경우 최대 2번의 시도가 가능하다.")
    void bowlTryCountTest() {
        assertThat(generalFrame.bowl(NORMAL)).isInstanceOf(GeneralProgress.class);
        assertThat(generalFrame.bowl(NORMAL)).isInstanceOf(Closed.class);
    }

    @Test
    @DisplayName("투구시 해당 프레임내 결과가 저장된다.")
    void bowlStatueTest() {
        generalFrame.bowl(NORMAL);
        assertThat(generalFrame.getResults()).hasSize(1);

        generalFrame.bowl(NORMAL);
        assertThat(generalFrame.getResults()).hasSize(2);
    }
}