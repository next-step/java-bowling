package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalSecondStateTest {
    private FinalSecondState finalSecondState;

    @BeforeEach
    void setUp() {
        FinalFirstState finalFirstState = new FinalFirstState(new Strike());
        finalSecondState = new FinalSecondState(finalFirstState, new Strike());
    }

    @Test
    void getScore() {
        assertThat(finalSecondState.getScore().getFrameScore()).isEqualTo(10);
    }

    @Test
    void getSymbol() {
        assertThat(finalSecondState.getSymbol()).isEqualTo("X|X");
    }
}
