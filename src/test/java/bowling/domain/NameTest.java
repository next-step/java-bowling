package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {
    @Test
    void 플레이어는이름을지닌다() {
        assertThat(Name.of("이름1")).isEqualTo(Name.of("이름1"));
    }

    @Test
    void 플레이어의이름은_1에서3자사이() {
        assertThatThrownBy(() -> {
            Name.of("1");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toString테스트() {
        System.out.println(Name.of("이름은"));
    }
}
