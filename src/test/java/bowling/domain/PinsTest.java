package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Pins pins = Pins.from();
        Pins compare = new Pins(4);

        //when
        pins = pins.bowl(4);

        //then
        assertTrue(pins.equals(compare));
    }

    @DisplayName("0~10 개 사이의 핀만 쓰러뜨릴 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {-20, -1, 11, 20})
    public void getDownPin_fail(int count) throws Exception {
        //given
        Pins pins = Pins.from();

        //then
        assertThatThrownBy(
                () -> pins.bowl(count)
        ).isInstanceOf(BowlingException.class);
    }


    @DisplayName("넘어뜨린 핀만큼 점수를 계산한다.")
    @Test
    public void getScore_success_current() throws Exception {
        //given
        Pins pins = Pins.from();

        //when
        pins = pins.bowl(5);
        Score score = pins.getScore();

        //then
        assertThat(score).isEqualTo(new Score(5));
    }

    @DisplayName("이전 점수와 더하여 넘어뜨린 핀만큼 점수를 계산한다.")
    @Test
    public void getScore_success_sum() throws Exception {
        //given
        Pins pins = Pins.from();

        //when
        pins = pins.bowl(5);
        Score score = pins.getScore(new Score(10));

        //then
        assertThat(score).isEqualTo(new Score(15));
    }

    @DisplayName("쓰러뜨린 핀이 0개 이면 gutter 상태 이다")
    @Test
    public void isGutter_success() throws Exception {
        //given
        Pins pin1 = new Pins(10);
        Pins pin2 = Pins.from();

        //then
        assertFalse(pin1.isGutter());
        assertTrue(pin2.isGutter());
    }

    @DisplayName("쓰러뜨린 핀이 10개 이면 완료(true)로 응답")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Pins max = new Pins(10);
        Pins min = new Pins(0);

        //then
        assertTrue(max.isFinish());
        assertFalse(min.isFinish());
    }

    @DisplayName("현재 쓰러진 핀의 개수와 전달 받은 핀의 쓰러진 개수를 더해 10개 이면 완료(true)로 응답")
    @Test
    public void isFinish_success_downPinCount() throws Exception {
        //given
        Pins first = new Pins(4);
        Pins second = new Pins(6);

        //then
        assertTrue(first.isFinish(second));
    }

    @DisplayName("넘어진 핀의 개수를 반환")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void getDownPin_success(int count) throws Exception {
        //given
        Pins pins = Pins.from();

        //when
        pins = pins.bowl(count);

        //then
        assertThat(pins.getDownPin()).isEqualTo(count);
    }

    @DisplayName("넘어진 핀의 개수를 반환")
    @Test
    public void getTotalDownPin_success() throws Exception {
        //given
        Pins pins1 = Pins.from();
        Pins pins2 = Pins.from();

        //when
        pins1 = pins1.bowl(1);
        pins2 = pins2.bowl(3);

        //then
        assertThat(pins1.getTotalDownPin(pins2)).isEqualTo(4);
    }
}
