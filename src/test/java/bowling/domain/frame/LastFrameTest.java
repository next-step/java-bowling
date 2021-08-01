package bowling.domain.frame;

import bowling.domain.pin.DownedPins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static bowling.domain.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링게임에서 마지막 프레임을 표현하는 클래스 테스트")
class LastFrameTest {

    @DisplayName("초기화에 필요한 정보는 없다")
    @Test
    void init() {
        assertThat(LastFrame.init()).isInstanceOf(LastFrame.class);
    }

    @DisplayName("프레임은 기본적으로 볼링 게임이 끝난 상태가 아니다")
    @Test
    void isBowlingEnd() {
        Frame lastFrame = LastFrame.init();

        assertThat(lastFrame.isBowlingEnd()).isFalse();

        lastFrame.downPins(DOWNED_PINS_5);
        lastFrame.downPins(DOWNED_PINS_2);

        assertThat(lastFrame.isBowlingEnd()).isTrue();
    }

    @DisplayName("마지막 프레임은 스페어나 스트라이크일 경우 총 세번까지 시도가 가능하다")
    @MethodSource
    @ParameterizedTest
    void downPins(List<DownedPins> prepareDownedPins) {
        LastFrame lastFrame = LastFrame.init();

        prepareDownedPins.forEach(downPins -> {
            assertThat(lastFrame.isBowlingEnd()).isFalse();
            lastFrame.downPins(downPins);
        });

        assertThat(lastFrame.isBowlingEnd()).isTrue();
    }

    private static Stream<Arguments> downPins() {
        return Stream.of(
                Arguments.of( //spare
                        Arrays.asList(
                                DOWNED_PINS_5,
                                DOWNED_PINS_5,
                                DOWNED_PINS_5
                        )
                ),
                Arguments.of( //strike
                        Arrays.asList(
                                DOWNED_PINS_10,
                                DOWNED_PINS_10,
                                DOWNED_PINS_10
                        )
                )
        );
    }
}
