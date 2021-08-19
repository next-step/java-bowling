package bowling.state;

import bowling.exception.EndStateException;
import bowling.pin.Pin;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class MissTest {
    private Miss miss;

    @BeforeEach
    void setUp() {
        final Pin firstDownedPins = Pin.from(5);
        final Pin lastDownedPins = Pin.from(3);
        miss = Miss.from(firstDownedPins, lastDownedPins);
    }

    @Test
    @DisplayName("Miss는 더이상 상태가 변할 수 없다")
    void nextPitch() {
        Assertions.assertThatThrownBy(() -> {
            miss.nextPitch(null);
        }).isInstanceOf(EndStateException.class);
    }

    @Test
    @DisplayName("Miss상태는 이전 투구에 쓰러트린 Pin과 마지막 투구에 쓰러트린 Pin을 각각 반환한다")
    void getScore() {
        List<Integer> missScore = miss.getScore();
        Assertions.assertThat(missScore.size()).isEqualTo(2);
        Assertions.assertThat(missScore).containsExactly(5, 3);
    }

    @Test
    @DisplayName("Miss는 Frame을 더 진행 할 수 없다")
    void isEnd() {
        Assertions.assertThat(miss.isEnd()).isTrue();
    }

    @Test
    @DisplayName("Miss상태일 경우 레인에 Pin이 남아있음을 의미한다")
    void isClean() {
        Assertions.assertThat(miss.isClean()).isFalse();
    }
}
