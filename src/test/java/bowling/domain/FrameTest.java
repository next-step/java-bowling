package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract public class FrameTest {

    Frame frame;

    @DisplayName("1번 투구로 최대 10개의 볼링핀을 쓰러뜨릴 수 있다. 10개를 넘어가면 exception 을 던진다")
    @Test
    void maxCountOfFallDown(){
        assertThatThrownBy( () -> frame.mark(11) )
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("2번째 투구로 쓰러뜨릴 수 있는 핀의 수는 1번째 투구에서 남은 핀이다. 그 이상이면 exception 을 던진다")
    @Test
    void maxCountOfFallDown2(){
        frame.mark(9);
        assertThatThrownBy( () -> frame.mark(2) )
                .isInstanceOf(IllegalArgumentException.class);
    }
}
