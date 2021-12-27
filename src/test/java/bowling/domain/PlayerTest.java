package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {
    @Test
    public void create() {
        final String name = "n1";
        assertThat(Player.of(name)).isInstanceOf(Player.class);
        assertThat(Player.of(name)).isEqualTo(Player.of(name));
        assertThat(Player.of(name).toString()).isEqualTo(name);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = "name1")
    public void createFailed(String name) {
        assertThatIllegalArgumentException().isThrownBy(() -> Player.of(name));
    }
}
