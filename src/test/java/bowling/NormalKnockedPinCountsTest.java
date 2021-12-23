package bowling;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalKnockedPinCountsTest {
    @Test
    void 핀은_2번까지만_쓰러뜨릴_수_있다() {
        KnockedPinCount zero = new KnockedPinCount(0);

        assertThatThrownBy(() -> new NormalKnockedPinCounts(Arrays.asList(zero, zero, zero)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NormalKnockedPinCounts.WRONG_BOWL_COUNT_MESSAGE);
    }

    @Test
    void 쓰러뜨린_핀의_개수의_합은_10개를_넘을_수_없다() {
        NormalKnockedPinCounts normalKnockedPinCounts = new NormalKnockedPinCounts();
        normalKnockedPinCounts.knockOut(5);
        assertThatThrownBy(() -> normalKnockedPinCounts.knockOut(6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(KnockedPinCount.INVALID_KNOCK_OUT_COUNT_MESSAGE);
    }
}
