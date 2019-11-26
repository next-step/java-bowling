package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserTest {

    @Test
    public void 생성_성공(){
        User user = new User("aaa");
        assertThat(user).isEqualTo(new User("aaa"));
    }

    @ParameterizedTest
    @CsvSource(value = {"123", "ab"})
    public void 생성_실패(String name){
        assertThatThrownBy(() -> {
            new User(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}