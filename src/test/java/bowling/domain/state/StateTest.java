package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.state.end.End;
import bowling.domain.state.end.Miss;
import bowling.domain.state.end.Spare;
import bowling.domain.state.end.Strike;
import bowling.domain.state.progress.FirstHit;
import bowling.domain.state.progress.Start;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StateTest {

    @Test
    void 시작_상태를_생성한다() {
        //given
        State state = new Start();
        //when
        //then
        assertThat(state).isNotNull();
    }

    @Test
    void 첫_번째_투구_상태를_생성한다() {
        //given
        State state = FirstHit.from(Pins.create(5));
        //when
        //then
        assertThat(state).isNotNull();
    }

    @Test
    void 이전_투구가_스트라이크면_첫_번째_투구_상태를_생성할_수_없다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> FirstHit.from(Pins.create(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 종료_상태_Strike_를_생성한다() {
        //given
        State state = Strike.from();
        //when
        //then
        assertThat(state).isInstanceOf(End.class);
        assertThat(state).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,10", "1,9", "5,5", "9,1"})
    void 종료_상태_Spare_를_생성한다(int firstPins, int secondPins) {
        //given
        State state = Spare.of(Pins.create(firstPins), Pins.create(secondPins));
        //when
        //then
        assertThat(state).isInstanceOf(End.class);
        assertThat(state).isNotNull();
    }

    @Test
    void 종료_상태_Spare_를_생성_실패() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Spare.of(Pins.create(5), Pins.create(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 종료_상태_Miss_를_생성한다() {
        //given
        State state = Miss.from(Pins.create(4), Pins.create(5));
        //when
        //then
        assertThat(state).isInstanceOf(End.class);
        assertThat(state).isNotNull();
    }

    @ParameterizedTest
    @CsvSource(value = {"0,10", "1,9", "5,5", "9,1"})
    void 종료_상태_Miss_를_생성_실패(int firstPins, int secondPins) {
        //given
        //when
        //then
        assertThatThrownBy(() -> Miss.from(Pins.create(firstPins), Pins.create(secondPins)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
