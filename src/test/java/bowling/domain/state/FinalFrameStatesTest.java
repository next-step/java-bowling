package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.state.finish.Miss;
import bowling.domain.state.finish.Spare;
import bowling.domain.state.finish.Strike;
import bowling.domain.state.running.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameStatesTest {

    FinalFrameStates states;

    @BeforeEach
    public void setUp() {
        states = FinalFrameStates.newInstance();

        states.push(Miss.of(Pins.of(1), Pins.of(3)));
        states.push(Strike.getInstance());
    }

    @DisplayName("처음 생성 시, Ready 상태를 추가")
    @Test
    public void create() {
        assertThat(FinalFrameStates.newInstance().getLastState())
                .isEqualTo(Ready.getInstance());
    }

    @DisplayName("가장 마지막 상태값을 반환")
    @Test
    public void getLastState() {
        assertThat(states.getLastState())
                .isEqualTo(Strike.getInstance());
    }

    @DisplayName("가장 처음 상태값을 반환")
    @Test
    public void getFirstState() {
        assertThat(states.getFirstState())
                .isEqualTo(Ready.getInstance());
    }

    @DisplayName("마지막 상태값을 다른 상태값으로 변경")
    @Test
    public void updateLastState() {
        states.updateLastState(Spare.of(Pins.of(2), Pins.of(8)));

        assertThat(states.getLastState())
                .isInstanceOf(Spare.class);
    }

    @DisplayName("새로운 상태값을 추가")
    @Test
    public void push() {
        states.push(Spare.of(Pins.of(2), Pins.of(8)));

        assertThat(states.getLastState())
                .isInstanceOf(Spare.class);
    }

    @DisplayName("상태값의 개수를 반환")
    @Test
    public void size() {
        assertThat(states.size())
                .isEqualTo(3);
    }

    @DisplayName("index 에 해당하는 상태값을 반환")
    @ParameterizedTest
    @MethodSource("indexOfCase")
    public void indexOf(final int index, final Class<? extends State> expected) {
        assertThat(states.indexOf(index))
                .isInstanceOf(expected);
    }

    private static Stream<Arguments> indexOfCase() {
        return Stream.of(
                Arguments.of(0, Ready.class),
                Arguments.of(1, Miss.class)
        );
    }

    @DisplayName("상태값 리스트를 반환")
    @ParameterizedTest
    @MethodSource("indexOfCase")
    public void getStates(final int index, final Class<? extends State> expected) {
        Stack<State> stack = states.getStates();

        assertThat(stack.get(index))
                .isInstanceOf(expected);
    }

    @DisplayName("Stack 을 List 로 변환하여 반환")
    @ParameterizedTest
    @MethodSource("indexOfCase")
    public void toList(final int index, final Class<? extends State> expected) {
        List<State> list = states.toList();

        assertThat(list.get(index))
                .isInstanceOf(expected);
    }
}
