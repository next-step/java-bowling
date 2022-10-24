package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsernameTest {

    @Test
    void shouldValidateUsername(){
        assertThatThrownBy(()->new Username(""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->new Username(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
