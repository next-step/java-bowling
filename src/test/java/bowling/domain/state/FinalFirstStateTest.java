package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-24 오후 4:39
 * Developer : Seo
 */
class FinalFirstStateTest {
    private FinalFirstState finalFirstState;

    @BeforeEach
    void setUp() {
        finalFirstState = new FinalFirstState(new Strike());
    }

    @Test
    void getScore() {
        assertThat(finalFirstState.getScore().getFrameScore()).isEqualTo(10);
    }

    @Test
    void getSymbol() {
        assertThat(finalFirstState.getSymbol()).isEqualTo("X");
    }
}
