package bowling.domain.frame;

import bowling.domain.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

class LastFrameTest {
    private Frame lastFrame;

    @BeforeEach
    void setUp() {
        lastFrame = LastFrame.create();
    }

    @Test
    @DisplayName("게임을 시작한 상태일 때, 종료 여부와 심볼을 확인한다")
    void ready() {
        assertAll(
                () -> assertThat(lastFrame).isInstanceOf(LastFrame.class),
                () -> assertThat(lastFrame.isFrameEnd()).isFalse(),
                () -> assertThat(lastFrame.getSymbol()).isEmpty()
        );
    }

    @ParameterizedTest
    @MethodSource("spareProvider")
    @DisplayName("스페어일 때, 추가로 투구할 수 있다")
    void spare(int firstPitch, int secondPitch, int thirdPitch, String symbol) {
        //given
        Pins firstPins = Pins.create(firstPitch);
        Pins secondPins = Pins.create(secondPitch);
        Pins thirdPins = Pins.create(thirdPitch);

        //when
        lastFrame.pitch(firstPins);
        lastFrame.pitch(secondPins);
        lastFrame.pitch(thirdPins);

        //then
        assertAll(
                () -> assertThat(lastFrame.isFrameEnd()).isTrue(),
                () -> assertThat(lastFrame.getSymbol()).isEqualTo(symbol)
        );
    }

    public static Stream<Arguments> spareProvider() {
        return Stream.of(
                Arguments.arguments(6, 4, 3, "6|/|3"),
                Arguments.arguments(9, 1, 10, "9|/|X")
        );
    }

    @ParameterizedTest
    @MethodSource("strikeProvider")
    @DisplayName("첫 투구에 스트라이크일 경우, 두번의 투구를 더 진행한다")
    void firstBowl(int firstPitch, int secondPitch, int thirdPitch, String symbol) {
        //given
        Pins firstPins = Pins.create(firstPitch);
        Pins secondPins = Pins.create(secondPitch);
        Pins thirdPins = Pins.create(thirdPitch);

        //when
        lastFrame.pitch(firstPins);
        lastFrame.pitch(secondPins);
        lastFrame.pitch(thirdPins);

        //then
        assertAll(
                () -> assertThat(lastFrame.isFrameEnd()).isTrue(),
                () -> assertThat(lastFrame.getSymbol()).isEqualTo(symbol)
        );
    }

    public static Stream<Arguments> strikeProvider() {
        return Stream.of(
                arguments(10, 5, 9, "X|5|9"),
                arguments(10, 10, 10, "X|X|X"),
                arguments(10, 0, 10, "X|-|/")
        );
    }

    @Test
    @DisplayName("미스일 때, 종료 여부와 상태를 확인한다")
    void miss() {
        //given
        Pins firstPins = Pins.create(6);
        Pins secondPins = Pins.create(3);

        //when
        lastFrame.pitch(firstPins);
        lastFrame.pitch(secondPins);

        //then
        assertAll(
                () -> assertThat(lastFrame.isFrameEnd()).isTrue(),
                () -> assertThat(lastFrame.getSymbol()).isEqualTo("6|3")
        );
    }

}