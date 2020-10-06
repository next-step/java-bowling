package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SpareTest {
    @DisplayName("Spare 상태 생성")
    @Test
    void create() {
        Spare spare = new Spare(5, 5);
        assertThat(spare).hasToString("5|/");
    }

    @DisplayName("Spare 상태 유효하지 않은 생성")
    @Test
    void create_invalid() {
        assertThatThrownBy(() -> new Spare(5, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Spare 상태 투구시 exception 발생")
    @Test
    void pitch() {
        Spare spare = new Spare(5, 5);

        assertThatThrownBy(() -> spare.pitch(2))
                .isInstanceOf(UnsupportedOperationException.class);
    }

}
