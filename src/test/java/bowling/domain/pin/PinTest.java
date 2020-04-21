package bowling.domain.pin;

import bowling.domain.pin.Pin;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PinTest {

    @DisplayName("핀의 개수는 0~10 사이여야 한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 10})
    public void validate_success(int count) throws Exception {
        new Pin(count);
    }

    @DisplayName("핀의 개수가 0~10 사이가 아니면 exception")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11, 15})
    public void validate_fail(int count) throws Exception {
        assertThatThrownBy(
                () -> new Pin(count)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("주어진 수만큼 핀을 쓰러뜨린다")
    @Test
    public void bowl_success() throws Exception {
        //given
        Pin pin = Pin.from();
        Pin compare = new Pin(4);

        //when
        pin = pin.bowl(4);

        //then
        assertTrue(pin.equals(compare));
    }

    @DisplayName("0~10 개 사이의 핀만 쓰러뜨릴 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {-20, -1, 11, 20})
    public void getDownPin_fail(int count) throws Exception {
        //given
        Pin pin = Pin.from();

        //then
        assertThatThrownBy(
                () -> pin.bowl(count)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("쓰러뜨린 핀이 0개 이면 gutter 상태 이다")
    @Test
    public void isGutter_success() throws Exception {
        //given
        Pin pin1 = new Pin(10);
        Pin pin2 = Pin.from();

        //then
        assertFalse(pin1.isGutter());
        assertTrue(pin2.isGutter());
    }

    @DisplayName("쓰러뜨린 핀이 10개 이면 완료(true)로 응답")
    @Test
    public void isFinish_success() throws Exception {
        //given
        Pin max = new Pin(10);
        Pin min = new Pin(0);

        //then
        assertTrue(max.isFinish());
        assertFalse(min.isFinish());
    }

    @DisplayName("현재 쓰러진 핀의 개수와 전달 받은 핀의 쓰러진 개수를 더해 10개 이면 완료(true)로 응답")
    @Test
    public void isFinish_success_downPinCount() throws Exception {
        //given
        Pin first = new Pin(4);
        Pin second = new Pin(6);

        //then
        assertTrue(first.isFinish(second));
    }

    @DisplayName("넘어진 핀의 개수를 반환")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void getDownPin_success(int count) throws Exception {
        //given
        Pin pin = Pin.from();

        //when
        pin = pin.bowl(count);

        //then
        assertThat(pin.getDownPin()).isEqualTo(count);
    }

    @DisplayName("넘어진 핀의 개수를 반환")
    @Test
    public void getTotalDownPin_success() throws Exception {
        //given
        Pin pin1 = Pin.from();
        Pin pin2 = Pin.from();

        //when
        pin1 = pin1.bowl(1);
        pin2 = pin2.bowl(3);

        //then
        assertThat(pin1.getTotalDownPin(pin2)).isEqualTo(4);
    }
}
