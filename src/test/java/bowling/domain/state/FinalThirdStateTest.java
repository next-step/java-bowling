package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalThirdStateTest {
    private FinalThirdState finalThirdState;

    @BeforeEach
    void setUp() {
        FinalFirstState finalFirstState = new FinalFirstState(new Strike());
        FinalSecondState finalSecondState = new FinalSecondState(finalFirstState, new Strike());
        finalThirdState = new FinalThirdState(finalSecondState, new Strike());
    }

    @Test
    void getScore() {
        assertThat(finalThirdState.getScore().getFrameScore()).isEqualTo(10);
    }

    @Test
    void getSymbol() {
        assertThat(finalThirdState.getSymbol()).isEqualTo("X|X|X");
    }
}
