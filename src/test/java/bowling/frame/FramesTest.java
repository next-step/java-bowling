package bowling.frame;

import bowling.dto.StateDtos;
import bowling.pin.Pin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = Frames.init();
    }

    @Test
    @DisplayName("NormalFrame은 9회까지 진행되고, 10회는 LastFrame이 생성되어야 한다")
    void pitch() throws Exception {
        // given
        final Pin strike = Pin.from(Pin.MAX_COUNT_OF_PIN);

        // when: 모든 기회를 strike 치면 정확히 12번 투구한다
        for (int stage = 0; stage < 12; stage++) {
            frames.pitch(strike);
        }

        // then: 프레임 종료?
        Assertions.assertThat(frames.isEnd()).isTrue();
    }

    @Test
    void convert() {
        Assertions.assertThat(frames.convert()).filteredOn(StateDtos.class::isInstance);
    }
}
