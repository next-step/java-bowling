package bowling.model.frame;

import bowling.model.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    private Frame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = NormalFrame.create();
    }

    @Test
    @DisplayName("게임을 시작한 상태일 때, 종료 여부와 심볼을 확인한다")
    void ready() {
        assertAll(
                () -> assertThat(normalFrame).isInstanceOf(NormalFrame.class),
                () -> assertThat(normalFrame.isFrameEnd()).isFalse(),
                () -> assertThat(normalFrame.getSymbol()).isEmpty()
        );
    }

    @Test
    @DisplayName("스트라이크일 때, 종료 여부와 심볼를 확인한다")
    void strike() {
        //given
        Pins firstPins = Pins.create(10);

        //when
        normalFrame.pitch(firstPins);

        //then
        assertAll(
                () -> assertThat(normalFrame.isFrameEnd()).isTrue(),
                () -> assertThat(normalFrame.getSymbol()).isEqualTo("X")
        );
    }

    @Test
    @DisplayName("스페어일 때, 종료 여부와 심볼를 확인한다")
    void spare() {
        //given
        Pins firstPins = Pins.create(6);
        Pins secondPins = Pins.create(4);

        //when
        normalFrame.pitch(firstPins);
        normalFrame.pitch(secondPins);

        //then
        assertAll(
                () -> assertThat(normalFrame.isFrameEnd()).isTrue(),
                () -> assertThat(normalFrame.getSymbol()).isEqualTo("6|/")
        );
    }

    @Test
    @DisplayName("첫 투구에 모든 핀을 쓰러트리지 못했을 때, 종료 여부와 심볼을 확인한다")
    void firstBowl() {
        //given
        Pins firstPins = Pins.create(6);

        //when
        normalFrame.pitch(firstPins);

        //then
        assertAll(
                () -> assertThat(normalFrame.isFrameEnd()).isFalse(),
                () -> assertThat(normalFrame.getSymbol()).isEqualTo("6")
        );
    }

    @Test
    @DisplayName("미스일 때, 종료 여부와 상태를 확인한다")
    void miss() {
        //given
        Pins firstPins = Pins.create(6);
        Pins secondPins = Pins.create(3);

        //when
        normalFrame.pitch(firstPins);
        normalFrame.pitch(secondPins);

        //then
        assertAll(
                () -> assertThat(normalFrame.isFrameEnd()).isTrue(),
                () -> assertThat(normalFrame.getSymbol()).isEqualTo("6|3")
        );
    }

}