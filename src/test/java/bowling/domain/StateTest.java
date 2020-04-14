package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateTest {

    private State strike = State.checkStatue(Tern.FIRST, 0);
    private State spare = State.checkStatue(Tern.SECOND, 0);
    private State miss1 = State.checkStatue(Tern.FIRST, 5);
    private State miss2 = State.checkStatue(Tern.SECOND, 5);
    private State gutter = State.checkStatue(Tern.SECOND, 10);

    @DisplayName("턴과 남은 핀의 개수를 이용해 상태를 구한다")
    @Test
    public void checkStatus_success() throws Exception {
        //then
        assertThat(strike).isEqualTo(State.STRIKE);
        assertThat(spare).isEqualTo(State.SPARE);
        assertThat(miss1).isEqualTo(State.MISS);
        assertThat(miss2).isEqualTo(State.MISS);
        assertThat(gutter).isEqualTo(State.GUTTER);
    }

    @DisplayName("각 상태의 표기되는 점수 문자 구하기")
    @Test
    public void showStateDisplay_success() throws Exception {
        //then
        assertThat(strike.showStateDisplay(0)).isEqualTo("X");
        assertThat(spare.showStateDisplay(0)).isEqualTo("/");
        assertThat(miss1.showStateDisplay(0)).isEqualTo("0");
        assertThat(miss2.showStateDisplay(10)).isEqualTo("10");
        assertThat(gutter.showStateDisplay(0)).isEqualTo("-");
    }
}
