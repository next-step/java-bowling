package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SpareTest {

    @Test
    void 종료_여부_판단() {
        assertThat(new Spare(new Pin(0)).isFinished()).isTrue();
    }

    @Test
    void 게임진행_예외발생() {
        assertThatThrownBy(() -> new Spare(new Pin(0)).bowl(new Pin(0)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 메시지_출력() {
        assertThat(new Spare(new Pin(5)).toString()).isEqualTo("5" + Spare.SPARE_MESSAGE);
    }

    @Test
    void Score_생성() {
        assertThat(new Spare(new Pin(5)).score()).isEqualTo(Score.ofSpare());
    }

    @Test
    void Score_계산() {
        assertThat(new Spare(new Pin(5)).calculateScore(new Score(10, 2)))
                .isEqualTo(new Score(20, 0));
    }

}
