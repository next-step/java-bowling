package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FinalFrameTest {
    @DisplayName("파이널 프레임을 생성한다.")
    @Test
    void FinalFrame_생성() {
        FinalFrame finalFrame = new FinalFrame();
        assertThat(finalFrame).isNotNull().isInstanceOf(FinalFrame.class);
    }

    @DisplayName("파이널 프레임의 상태 리스트가 null 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullSource
    void FinalFrame_생성_프레임상태_예외(LinkedList<FrameState> frameStates) {
        assertThatThrownBy(() -> new FinalFrame(frameStates, 0)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파이널 프레임의 투구 횟수가 0 ~ 3 을 벗어날 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 4, 5})
    void FinalFrame_생성_프레임상태_예외(int bowlCount) {
        assertThatThrownBy(() -> new FinalFrame(new LinkedList<>(List.of(new BeforeProgress())), bowlCount)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("파이널 프레임을 초기화 한다.")
    @Test
    void initialize_파이널프레임_초기화() {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new BeforeProgress()));
        assertThat(initialFinalFrame.isEqualFrameStates(frameStates)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크 하지 못한 경우 프레임 상태는 ['FirstBowl'] 이 되고, 투구 횟수는 1이 된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 8, 9})
    void bowl_첫번째_투구_NON_STRIKE(int hitPins) {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        FinalFrame resultFrame = (FinalFrame) initialFinalFrame.bowl(new Pins(hitPins));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new FirstBowl(new Pins(hitPins))));
        assertThat(resultFrame.isEqualFrameStates(frameStates)).isTrue();
        assertThat(resultFrame.isMatchBowlCount(1)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크한 경우 프레임 상태는 ['Strike'] 이 되고, 투구 횟수는 1이 된다.")
    @Test
    void bowl_첫번째_투구_STRIKE() {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        FinalFrame resultFrame = (FinalFrame)initialFinalFrame.bowl(new Pins(10));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new Strike()));
        assertThat(resultFrame.isEqualFrameStates(frameStates)).isTrue();
        assertThat(resultFrame.isMatchBowlCount(1)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크 후, 두번째 투구에서 스트라이크 하지 못한 경우 프레임 상태는 ['Strike', 'FirstBowl'] 이 되고, 투구 횟수는 2가 된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 8, 9})
    void bowl_첫번째_투구_STRIKE_두번째_투구_NON_STRIKE(int hitPins) {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        initialFinalFrame.bowl(new Pins(10));
        FinalFrame resultFrame = (FinalFrame) initialFinalFrame.bowl(new Pins(hitPins));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new Strike(), new FirstBowl(new Pins(hitPins))));
        assertThat(resultFrame.isEqualFrameStates(frameStates)).isTrue();
        assertThat(resultFrame.isMatchBowlCount(2)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크 후, 두번째 투구에서 스트라이크 한 경우 프레임 상태는 ['Strike', 'Strike'] 이 되고, 투구 횟수는 2가 된다.")
    @Test
    void bowl_첫번째_투구_STRIKE_두번째_투구_STRIKE() {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        initialFinalFrame.bowl(new Pins(10));
        FinalFrame resultFrame = (FinalFrame) initialFinalFrame.bowl(new Pins(10));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new Strike(), new Strike()));
        assertThat(resultFrame.isEqualFrameStates(frameStates)).isTrue();
        assertThat(resultFrame.isMatchBowlCount(2)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크 후, 두번째 투구에서 10개 미만의 공을 쓰러뜨린 후, 세번째 투구에서 스페어 처리하지 못한 경우 프레임 상태는 ['Strike', 'Miss'] 이 되고, 투구 횟수는 3이 된다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1, 8",
            "2, 7",
            "0, 9",
            "9, 0"
    })
    void bowl_첫번째_투구_STRIKE_두번째_투구_NON_STRIKE_세번째_투구_MISS(int secondPins, int thirdPins) {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        initialFinalFrame.bowl(new Pins(10));
        initialFinalFrame.bowl(new Pins(secondPins));
        FinalFrame resultFrame = (FinalFrame) initialFinalFrame.bowl(new Pins(thirdPins));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new Strike(), new Miss(new Pins(secondPins), new Pins(thirdPins))));
        assertThat(resultFrame.isEqualFrameStates(frameStates)).isTrue();
        assertThat(resultFrame.isMatchBowlCount(3)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크 후, 두번째 투구에서 10개 미만의 공을 쓰러뜨린 후, 세번째 투구에서 스페어 처리한 경우 프레임 상태는 ['Strike', 'Spare'] 이 되고, 투구 횟수는 3이 된다.")
    @ParameterizedTest
    @CsvSource(value = {
            "1, 9",
            "2, 8",
            "0, 10",
            "5, 5"
    })
    void bowl_첫번째_투구_STRIKE_두번째_투구_NON_STRIKE_세번째_투구_SPARE(int secondPins, int thirdPins) {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        initialFinalFrame.bowl(new Pins(10));
        initialFinalFrame.bowl(new Pins(secondPins));
        FinalFrame resultFrame = (FinalFrame) initialFinalFrame.bowl(new Pins(thirdPins));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new Strike(), new Spare(new Pins(secondPins))));
        assertThat(resultFrame.isEqualFrameStates(frameStates)).isTrue();
        assertThat(resultFrame.isMatchBowlCount(3)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크 후, 두번째 투구에서 스트라이크 후, 세번째 투구에서 10개 미만의 핀을 쓰러뜨린 경우 프레임 상태는 ['Strike', 'Strike', 'FirstBowl'] 이 되고, 투구 횟수는 3이 된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 8, 9})
    void bowl_첫번째_투구_STRIKE_두번째_투구_STRIKE_세번째_투구_FIRSTBOWL(int thirdPins) {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        initialFinalFrame.bowl(new Pins(10));
        initialFinalFrame.bowl(new Pins(10));
        FinalFrame resultFrame = (FinalFrame) initialFinalFrame.bowl(new Pins(thirdPins));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new Strike(), new Strike(), new FirstBowl(new Pins(thirdPins))));
        assertThat(resultFrame.isEqualFrameStates(frameStates)).isTrue();
        assertThat(resultFrame.isMatchBowlCount(3)).isTrue();
    }

    @DisplayName("파이널 프레임, 첫번째 투구에서 스트라이크 후, 두번째 투구에서 스트라이크 후, 세번째 투구에서 스트라이크 한 경우 프레임 상태는 ['Strike', 'Strike', 'Strike'] 이 되고, 투구 횟수는 3이 된다.")
    @Test
    void bowl_첫번째_투구_STRIKE_두번째_투구_STRIKE_세번째_투구_Strike() {
        FinalFrame initialFinalFrame = FinalFrame.initialize();
        initialFinalFrame.bowl(new Pins(10));
        initialFinalFrame.bowl(new Pins(10));
        FinalFrame resultFrame = (FinalFrame) initialFinalFrame.bowl(new Pins(10));
        LinkedList<FrameState> frameStates = new LinkedList<>(List.of(new Strike(), new Strike(), new Strike()));
        assertThat(resultFrame.isEqualFrameStates(frameStates)).isTrue();
        assertThat(resultFrame.isMatchBowlCount(3)).isTrue();
    }

    @DisplayName("마지막 프레임이 종료됐는지 확인한다.")
    @ParameterizedTest
    @MethodSource("isFrameEnd_frameStatesProvider")
    void isFrameEnd_종료_체크(FinalFrame finalFrame, boolean trueOrFalse) {
        assertThat(finalFrame.isFrameEnd()).isEqualTo(trueOrFalse);
    }

    static Stream<Arguments> isFrameEnd_frameStatesProvider() {
        return Stream.of(
                arguments(new FinalFrame(new LinkedList<>(List.of(new BeforeProgress())), 0), false),
                arguments(new FinalFrame(new LinkedList<>(List.of(new FirstBowl(new Pins(5)))), 1), false),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new FirstBowl(new Pins(5)))), 2), false),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike())), 2), false),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Miss(new Pins(3), new Pins(5)))), 3), true),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Spare(new Pins(4)))), 3), true),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), new FirstBowl(new Pins(5)))), 3), true),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), new Strike())), 3), true)
        );
    }

    @DisplayName("마지막 프레임 상태에 따른 기호를 확인한다.")
    @ParameterizedTest
    @MethodSource("symbol_frameStatesProvider")
    void symbol_프레임상태_기호_체크(FinalFrame finalFrame, String symbol) {
        assertThat(finalFrame.symbol()).isEqualTo(symbol);
    }

    static Stream<Arguments> symbol_frameStatesProvider() {
        return Stream.of(
                arguments(new FinalFrame(new LinkedList<>(List.of(new BeforeProgress())), 0), ""),
                arguments(new FinalFrame(new LinkedList<>(List.of(new FirstBowl(new Pins(5)))), 1), "5"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new FirstBowl(new Pins(0)))), 1), "-"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Miss(new Pins(0), new Pins(8)))), 2), "-|8"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Miss(new Pins(3), new Pins(2)))), 2), "3|2"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Miss(new Pins(3), new Pins(0)))), 2), "3|-"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Spare(new Pins(4)))), 2), "4|/"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Spare(new Pins(0)))), 2), "-|/"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Spare(new Pins(0)), new FirstBowl(new Pins(4)))), 3), "-|/|4"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Spare(new Pins(0)), new FirstBowl(new Pins(0)))), 3), "-|/|-"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Spare(new Pins(1)), new FirstBowl(new Pins(4)))), 3), "1|/|4"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Spare(new Pins(1)), new FirstBowl(new Pins(0)))), 3), "1|/|-"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Spare(new Pins(1)), new Strike())), 3), "1|/|X"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new FirstBowl(new Pins(5)))), 2), "X|5"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike())), 2), "X|X"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Miss(new Pins(3), new Pins(5)))), 3), "X|3|5"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Miss(new Pins(0), new Pins(5)))), 3), "X|-|5"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Miss(new Pins(5), new Pins(0)))), 3), "X|5|-"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Spare(new Pins(0)))), 3), "X|-|/"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Spare(new Pins(4)))), 3), "X|4|/"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), new FirstBowl(new Pins(5)))), 3), "X|X|5"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), new FirstBowl(new Pins(0)))), 3), "X|X|-"),
                arguments(new FinalFrame(new LinkedList<>(List.of(new Strike(), new Strike(), new Strike())), 3), "X|X|X")
        );
    }

    @DisplayName("해당 프레임이 파이널 프레임인 것을 확인")
    @Test
    void isFinalFrame_false() {
        assertThat(FinalFrame.initialize().isFinalFrame()).isTrue();
    }
}