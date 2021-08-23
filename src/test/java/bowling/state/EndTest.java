package bowling.state;

import bowling.exception.EndStateException;
import bowling.pin.Pin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {
    private Pin dummyPins;
    private State end;

    @BeforeEach
    void setUp() {
        dummyPins = Pin.from(0);
        end = End.init();
    }

    @Test
    @DisplayName("End는 볼링 게임이 종료됨을 의미하므로 더 이상 진행할 수 없다")
    void nextPitch() {
        Assertions.assertThatThrownBy(() -> {
            end.nextPitch(dummyPins);
        }).isInstanceOf(EndStateException.class);
    }

    @Test
    @DisplayName("End는 볼링 게임이 종료됨을 의미하므로 아무런 점수를 갖지 않는다")
    void getScore() {
        Assertions.assertThat(end.getScore()).isEmpty();
    }

    @Test
    @DisplayName("End는 볼링 게임이 종료됨을 의미한다")
    void isEnd() {
        Assertions.assertThat(end.isEnd()).isTrue();
    }

    @Test
    @DisplayName("End는 볼링 게임이 종료됨을 의미하므로 아무런 상태를 갖지 않는다")
    void isClean() {
        Assertions.assertThat(end.isClean()).isFalse();
    }
}
