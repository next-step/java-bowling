package bowling.domain;

import bowling.domain.turn.Turn;
import bowling.domain.turn.TurnState;
import bowling.domain.turn.Turns;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @DisplayName("1~10번 프레임 까지만 등록 가능 하다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void validate_success(int number) throws Exception {
        //then
        new NormalFrame(new Turns(Turn.from(3)), number, null, TurnState.READY);
    }

    @DisplayName("1~10번 외의 프레임 까지만 등록시 exception")
    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 11, 15, 100})
    public void validate_fail(int number) throws Exception {
        //then
        assertThatThrownBy(
                () -> new NormalFrame(new Turns(Turn.from(3)), number, null,
                        TurnState.READY)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("1~9번 프레임은 2회만 투구 가능하다")
    @Test
    public void validate_fail_thirdThrow() throws Exception {
        //given
        Turns more = new Turns(Arrays.asList(Turn.from(3), Turn.from(3), Turn.from(3)));

        //then
        assertThatThrownBy(
                () -> new NormalFrame(more, 1, null, TurnState.READY)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("프레임의 첫 투구 시")
    @Test
    public void bowl_success() throws Exception {
        //given
        NormalFrame frame = NormalFrame.from();

        //when
        NormalFrame next = frame.bowl(3);

        //then
        assertThat(next.getTurns().size()).isEqualTo(1);
    }
}
