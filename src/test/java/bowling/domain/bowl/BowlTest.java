package bowling.domain.bowl;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BowlTest {
    @Test
    void Bowl은_pinCount없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new Bowl(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
