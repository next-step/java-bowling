package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.BDDAssertions.then;

public class PinCountTest {

    @Test
    @DisplayName("쓰러뜨린 핀의 수 객체를 생성한다.")
    public void create() throws Exception {
        PinCount pinCount = new PinCount(1);
        assertThat(pinCount).isEqualTo(new PinCount(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("0 ~ 10 이외의 값을 인자로 받을 경우 예외가 발생한다.")
    public void checkBound(int score) throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PinCount(score))
                .withMessage("쓰러뜨린 핀의 개수가 0 ~ 10의 범위인지 확인하여 주세요.");
    }

    @Test
    @DisplayName("쓰러진 개수가 10개일 경우 참을 반환한다.")
    public void isStrike() throws Exception {
        PinCount pinCount = new PinCount(10);
        boolean isStrike = pinCount.isStrike();
        assertThat(isStrike).isTrue();
    }

    @Test
    @DisplayName("쓰러뜨린 핀의 개수를 더하여 반환한다.")
    public void plus() throws Exception {
        //given
        PinCount pinCount = new PinCount(10);

        //when
        int firstTotalPinCount = pinCount.plus(new PinCount(10));
        int secondTotalPinCount = pinCount.plus(15);
        int thirdTotalPinCount = pinCount.plus(Arrays.asList(new PinCount(10), new PinCount(10)));

        then(firstTotalPinCount).isEqualTo(20);
        then(secondTotalPinCount).isEqualTo(25);
        then(thirdTotalPinCount).isEqualTo(30);
    }
}
