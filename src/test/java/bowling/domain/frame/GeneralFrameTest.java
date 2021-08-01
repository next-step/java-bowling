package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.Fixture.DOWNED_PINS_10;
import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.*;

@DisplayName("일반 프레임 테스트")
class GeneralFrameTest {

    @DisplayName("일반 프레임은 초기화시 Preparation 상태값을 가진다")
    @Test
    void init() {
        assertThat(GeneralFrame.init()).isInstanceOf(GeneralFrame.class);
    }

    @DisplayName("일반 프레임에서 핀 쓰러뜨리기")
    @Test
    void downPins() {
        GeneralFrame generalFrame = GeneralFrame.init();

        assertThatCode(() -> generalFrame.downPins(DOWNED_PINS_5)).doesNotThrowAnyException();
    }

    @DisplayName("프레임이 끝난 상태에서 핀을 쓰러뜨리려 하면 예외가 발생한다")
    @Test
    void downPinsException() {
        GeneralFrame generalFrame = GeneralFrame.init();
        generalFrame.downPins(DOWNED_PINS_10);

        assertThatThrownBy(() -> generalFrame.downPins(DOWNED_PINS_5)).isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("프레임 추가 테스트")
    @Test
    void appendFrame() {
        List<Frame> frames = new ArrayList<>();
        frames.add(GeneralFrame.init());

        IntStream.range(0, 9)
                .forEach(i -> {
                    Frame frame = frames.get(frames.size() - 1);
                    frame.downPins(DOWNED_PINS_10);
                    assertThat(frame).isInstanceOf(GeneralFrame.class);
                    frame.appendFrame(frames);
                });

        Frame frame = frames.get(frames.size() - 1);
        frame.appendFrame(frames);
        assertThat(frames.get(frames.size() - 1)).isInstanceOf(LastFrame.class);
    }
}
