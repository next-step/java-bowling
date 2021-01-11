package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

public class PrintTest {

    @Test
    public void check_Null() {
        Assertions.assertThatThrownBy(() -> {
            Print print = new Print(null, null);
        }).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    public void check_null_frames(Frames frames) {
        Assertions.assertThatThrownBy(() -> {
            Print print = new Print(frames, new Name("111"));
        }).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    public void check_null_name(Name name) {
        Assertions.assertThatThrownBy(() -> {
            Print print = new Print(new Frames(), name);
        }).isInstanceOf(NullPointerException.class);
    }
}
