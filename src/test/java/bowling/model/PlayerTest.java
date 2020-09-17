package bowling.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {
    @ParameterizedTest
    @NullAndEmptySource
    public void constructor_ShouldThrow_WhenEmpty(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Player(name));
    }

    @ParameterizedTest
    @ValueSource(strings = { "A", "AB", "ABCD" })
    public void constructor_ShouldThrow_Invalid(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Player(name));
    }

    @Test
    public void equals() {
        assertThat(new Player("KDY")).isEqualTo(new Player("KDY"));
        assertThat(new Player("KDY")).isNotEqualTo(new Player("POB"));
    }
}
