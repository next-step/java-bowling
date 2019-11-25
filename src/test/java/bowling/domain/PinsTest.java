package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinsTest {
    @ParameterizedTest
    @DisplayName("점수 반영 테스트")
    @MethodSource(value = "providePinsAndScore")
    void add(Pin[] Pins, int score) {
        Pins expectPins = new Pins(Arrays.asList(Pins));
        assertThat(expectPins.score()).isEqualTo(score);
    }

    static Stream<Arguments> providePinsAndScore() {
        return Stream.of(
                Arguments.of(new Pin[]{new Pin(5)}, 5),
                Arguments.of(new Pin[]{new Pin(10)}, 10),
                Arguments.of(new Pin[]{new Pin(6), new Pin(4)}, 10),
                Arguments.of(new Pin[]{new Pin(10), new Pin(10), new Pin(5)}, 25),
                Arguments.of(new Pin[]{new Pin(6), new Pin(4), new Pin(5)}, 15),
                Arguments.of(new Pin[]{new Pin(10), new Pin(10), new Pin(10)}, 30)
        );
    }

    @Test
    @DisplayName("점수 반영시 핀 수 초과 에러 테스트")
    void fallDownIsOverFlowPinCountException() {
        Pin[] tetPins = new Pin[]{new Pin(10), new Pin()};
        int nextPin = 10;
        Pins expectPins = new Pins(Arrays.asList(tetPins));

        assertThatThrownBy(() -> {
            expectPins.fallDown(nextPin);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Pins.IS_OVER_FLOW_PIN_COUNT);
    }

    @Test
    @DisplayName("점수 반영시 쓰러트릴 수 있는 횟 수 초과 에러 테스트")
    void fallDownIsOverFlowBallCountException() {
        Pin[] tetPins = new Pin[]{new Pin(6), new Pin(3)};
        int nextPin = 2;
        Pins expectPins = new Pins(Arrays.asList(tetPins));

        assertThatThrownBy(() -> {
            expectPins.fallDown(nextPin);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Pins.IS_OVER_FLOW_BALL_COUNT);
    }

    @Test
    @DisplayName("마지막 프레임에서 스트라이크 또는 스페어가 아닐때 에러 테스트")
    void fallDownIsNotAddAbleThirdBall() {
        Pin[] tetPins = new Pin[]{new Pin(6), new Pin(3), new Pin()};
        int nextPin = 2;
        Pins expectPins = new Pins(Arrays.asList(tetPins));

        assertThatThrownBy(() -> {
            expectPins.fallDown(nextPin);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Pins.IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE);
    }

    @Test
    @DisplayName("투구 가능한 수를 리턴한다.")
    void addAblePinCount() {
        Pins pins = new Pins(false);
        assertThat(pins.addAblePinCount(false)).isEqualTo(10);

        pins.fallDown(5);
        assertThat(pins.addAblePinCount(false)).isEqualTo(5);

        pins.fallDown(5);
        assertThat(pins.addAblePinCount(false)).isEqualTo(0);
    }

    @Test
    @DisplayName("size가 다른지 확인 한다.")
    void isNotSame() {
        List<Pin> pinList = Arrays.asList(new Pin(5), new Pin(5));
        Pins pins = new Pins(pinList);
        assertThat(pins.isNotSameSize(pinList.size() - 1)).isTrue();
    }
}
