package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PinMarkTest {

    @DisplayName("PinMark 는 10개 이상 할 수 없다. 10개를 넘어가면 exception 을 던진다")
    @Test
    void pin(){
        assertThatThrownBy( () -> PinMark.pin(11) )
                .isInstanceOf(IllegalArgumentException.class);
    }

}
