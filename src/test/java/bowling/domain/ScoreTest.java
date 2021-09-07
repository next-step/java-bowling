package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    void create(){
        assertThat(new Score(1)).isEqualTo(new Score(1));
    }

    @DisplayName("음수 점수는 생성할 수 없다.")
    @Test
    void create_error(){
        assertThatThrownBy(() -> new Score(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}