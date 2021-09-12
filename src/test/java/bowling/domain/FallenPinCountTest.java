package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FallenPinCountTest {

    @ParameterizedTest(name = "넘어진 핀의 수는 0에서 10개 사이의 값이다. - 성공 테스트")
    @ValueSource(ints = {0, 10})
    public void createFallenPinCountSuccessTest(int countInput) {
        assertThat(new FallenPinCount(countInput).count())
                .isEqualTo(countInput);
    }

    @ParameterizedTest(name = "넘어진 핀의 수는 0에서 10개 사이의 값이다. - 실패 테스트")
    @ValueSource(ints = {-1, 11})
    public void createFallenPinCountFailTest(int countInput) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FallenPinCount(countInput))
                .withMessageContaining(String.valueOf(countInput));
    }

}