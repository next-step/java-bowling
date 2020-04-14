package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @DisplayName("핀의 개수는 0~10 사이여야 한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 10})
    public void validate_success(int count) throws Exception {
        new Pins(count);
    }

    @DisplayName("핀의 개수가 0~10 사이가 아니면 exception")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11, 15})
    public void validate_fail(int count) throws Exception {
        assertThatThrownBy(
                () -> new Pins(count)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("주어진 수만큼 핀을 쓰러뜨린다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Pins pins = new Pins();

        pins = pins.bowl(5);
    }

    @DisplayName("남은 핀보다 많은 핀을 넘어뜨리려 하면 exception")
    @Test
    public void bowl_fail() throws Exception {
        //given
        Pins pins = new Pins(5);

        //then
        assertThatThrownBy(
                () -> pins.bowl(10)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("넘어뜨린 핀만큼 점수를 계산한다.")
    @Test
    public void getScore_success() throws Exception {
        //given
        Pins pins = new Pins();

        //when
        pins = pins.bowl(5);
        Score score = pins.getScore(new Score(10));

        //then
        assertThat(score).isEqualTo(new Score(15));
    }
}
