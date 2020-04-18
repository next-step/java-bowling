package bowling.domain.pitch;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstBowlTest {
    private FirstBowl firstBowl;
    @BeforeEach
    void setUp() {
        firstBowl = new FirstBowl(1);
    }
    @DisplayName("미스를 리턴한다")
    @Test
    void returnBowl() {
        assertThat(firstBowl.bowl(8)).isExactlyInstanceOf(Miss.class);
    }

    @DisplayName("스트라이크를 리턴한다")
    @Test
    void returnStrike() {
        assertThat(firstBowl.bowl(9)).isExactlyInstanceOf(Spare.class);
    }
}
