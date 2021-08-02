package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static bowling.domain.Fixture.DOWNED_PINS_10;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프레임 그룹에 대한 테스트")
class FramesTest {

    @DisplayName("프레임 그룹은 초기화에 아무런 정보가 필요 없다")
    @Test
    void init() {
        assertThat(Frames.init()).isInstanceOf(Frames.class);
    }

    @DisplayName("프레임 그룹을 통해 핀을 쓰러뜨리면, 자동으로 프레임 생성까지 한다")
    @Test
    void downPins() {
        Frames frames = Frames.init();

        assertThat(frames.getTotalStates().size()).isEqualTo(1);
        frames.downPins(DOWNED_PINS_10);
        assertThat(frames.getTotalStates().size()).isEqualTo(2);
    }

    @DisplayName("볼링게임의 종료 테스트")
    @Test
    void isBowlingEnd() {
        Frames frames = Frames.init();

        IntStream.range(0, 11)
                .forEach(i -> {
                    frames.downPins(DOWNED_PINS_10);
                    assertThat(frames.isBowlingEnd()).isFalse();
                });

        frames.downPins(DOWNED_PINS_10);
        assertThat(frames.isBowlingEnd()).isTrue();
    }

}
