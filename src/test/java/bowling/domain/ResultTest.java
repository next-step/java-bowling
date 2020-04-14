package bowling.domain;

import bowling.domain.turn.TurnState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    private Result strike = Result.getResultState(TurnState.FIRST, 0);
    private Result spare = Result.getResultState(TurnState.SECOND, 0);
    private Result miss1 = Result.getResultState(TurnState.FIRST, 5);
    private Result miss2 = Result.getResultState(TurnState.SECOND, 5);
    private Result gutter = Result.getResultState(TurnState.SECOND, 10);

    @DisplayName("턴과 남은 핀의 개수를 이용해 상태를 구한다")
    @Test
    public void checkStatus_success() throws Exception {
        //then
        assertThat(strike).isEqualTo(Result.STRIKE);
        assertThat(spare).isEqualTo(Result.SPARE);
        assertThat(miss1).isEqualTo(Result.MISS);
        assertThat(miss2).isEqualTo(Result.MISS);
        assertThat(gutter).isEqualTo(Result.GUTTER);
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
