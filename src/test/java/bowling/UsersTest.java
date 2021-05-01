package bowling;

import bowling.domain.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class UsersTest {

    @Test
    @DisplayName("한 레인의 최대 인원 수는 6인, 그 이상의 경우 예외처리가 발생한다.")
    void cannotOver6() {
        List<String> userNames = Arrays.asList("AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG");

        Assertions.assertThatThrownBy(() -> {
            new Users(userNames);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
