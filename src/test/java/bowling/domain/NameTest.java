package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @ParameterizedTest
    @NullAndEmptySource
    public void check_null_or_empty(final String param) {
        Assertions.assertThatThrownBy(() -> {
            Name name = new Name(param);
        }).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456", "tetetetet", "testName"})
    public void check_name_length(final String param){
        Assertions.assertThatThrownBy(() -> {
            Name name = new Name(param);
        }).isInstanceOf(RuntimeException.class);
    }
}
