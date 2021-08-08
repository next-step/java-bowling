package bowling.domain.state.result;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndTest {

    @DisplayName("End는 isFinish를 true로 반환해야한다")
    @Test
    void should_return_true_is_finish() {
        //arrange
        End end = new End() {
            @Override
            public List<Integer> getHitPins() {
                return null;
            }
        };

        //act, assert
        assertTrue(end.isFinish());
    }

    @DisplayName("End는 isClean을 false로 반환해야한다")
    @Test
    void should_return_false_is_clean() {
        //arrange
        End end = new End() {
            @Override
            public List<Integer> getHitPins() {
                return null;
            }
        };

        //act, assert
        assertFalse(end.isClean());
    }

    @DisplayName("End는 isMiss을 false로 반환해야한다")
    @Test
    void should_return_false_is_miss() {
        //arrange
        End end = new End() {
            @Override
            public List<Integer> getHitPins() {
                return null;
            }
        };

        //act, assert
        assertFalse(end.isMiss());
    }

    @DisplayName("End일때 hitPins는 IllegalStateException을 반환한다")
    @Test
    void should_throw_exception_when_end_hit_pins() {
        //arrange
        End end = new End() {
            @Override
            public List<Integer> getHitPins() {
                return null;
            }
        };

        //act, assert
        assertThatIllegalStateException().isThrownBy(() -> end.hitPins(Pins.of(10)));
    }

}