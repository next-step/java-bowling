package step4.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NumberOfPlayerTest {

    @Test
    @DisplayName("참여할 플레이어의 인원 수를 저장하는 객체를 생성한다.")
    public void create() throws Exception {
        NumberOfPlayer numberOfPlayer = new NumberOfPlayer(2);
        assertThat(numberOfPlayer).isEqualTo(new NumberOfPlayer(2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0"})
    @DisplayName("참여할 플레이어의 인원 수가 양수가 아닐 경우 예외가 발생한다..")
    public void create_exception(int numberOfPlayer) throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new NumberOfPlayer(numberOfPlayer));
    }
}