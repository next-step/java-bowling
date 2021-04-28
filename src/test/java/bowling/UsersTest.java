package bowling;

import bowling.domain.User;
import bowling.domain.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class UsersTest {

    @Test
    @DisplayName("한 레인의 최대 인원 수는 6인, 그 이상의 경우 예외처리가 발생한다.")
    void cannotOver6() {
        Users users = new Users();

        int maxNumber = 6;
        IntStream.range(0, maxNumber)
                .mapToObj(i -> new User("JHL"))
                .forEach(users::add);

        Assertions.assertThatThrownBy(() -> {
            users.add(new User("SDA"));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
