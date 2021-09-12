package bowling;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserNameTest {

    @Test
    public void create_test() {
        UserName userName = new UserName("bos");
        assertThat(userName).isEqualTo(new UserName("bos"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"1", "^^", "한글", "abcd"})
    public void validate_test(String name) {
        assertThatThrownBy(() -> new UserName(name)).isInstanceOf(IllegalArgumentException.class);
    }
}
