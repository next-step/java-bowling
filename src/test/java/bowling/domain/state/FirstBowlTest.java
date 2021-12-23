package bowling.domain.state;

import bowling.domain.value.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FirstBowlTest {

    @Test
    @DisplayName("첫 번째 핀 이후 두번째 핀을 쳤을 때 스페인 경우 검증")
    void strikeTest() {
        FirstBowl firstBowl = new FirstBowl(new Pins(5));

        assertThat(firstBowl.bowl(new Pins(5))).isEqualTo(new Spare(new Pins(5),new Pins(5)));
    }

    @Test
    @DisplayName("첫 번째 핀 이후 두번째 핀을 쳤을 때 미스인 경우 검증")
    void missTest() {
        FirstBowl firstBowl = new FirstBowl(new Pins(5));

        assertThat(firstBowl.bowl(new Pins(4))).isEqualTo(new Miss(new Pins(5), new Pins(4)));
    }

    @Test
    @DisplayName("첫 번째 핀 이후 두번째 핀을 쳤을 때 10을 넘었을 때 예외처리 검증")
    void gutterTest() {
        FirstBowl firstBowl = new FirstBowl(new Pins(5));

        assertThatThrownBy(() ->
                firstBowl.bowl(new Pins(7))).isInstanceOf(IllegalArgumentException.class);
    }

}
