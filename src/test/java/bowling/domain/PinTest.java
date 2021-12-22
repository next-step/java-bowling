package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.fail;

public class PinTest {

    @Test
    @DisplayName("최소 핀 개수 에러발생")
    public void checkMinPin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Pin(-1);

            fail("핀 개수 에러가 발생해야 한다.");
        });
    }

    @Test
    @DisplayName("최대 핀 개수 에러발생")
    public void checkMaxPin() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Pin(11);

            fail("핀 개수 에러가 발생해야 한다.");
        });
    }

}
