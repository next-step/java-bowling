package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractBowlingGameFrameTest {

    private BowlingGameFrame history;

    @BeforeEach
    void setUp() {
        history = new AbstractBowlingGameFrame(new ArrayList<>()) {
            @Override
            protected void validateHits(List<Integer> hits) {}

            @Override
            protected void validateState() {}

            @Override
            public int getMaxSizeOfHits() {
                return 2;
            }
        };
    }

    @DisplayName("스트라이크라면, true를 반환해야 한다.")
    @Test
    void isStrike() {
        history.add(10);
        assertThat(history.isStrike()).isTrue();
    }

    @DisplayName("스페어라면, true를 반환해야 한다.")
    @Test
    void isSpare() {
        history.add(2);
        history.add(8);
        assertThat(history.isSpare()).isTrue();
    }

    @DisplayName("미스라면, true를 반환해야 한다.")
    @Test
    void isMiss() {
        history.add(0);
        history.add(2);
        assertThat(history.isMiss()).isTrue();
    }

}
