package bowling;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalKnockedPinCountsTest {
    private final NormalKnockedPinCounts normalKnockedPinCounts = new NormalKnockedPinCounts();

    @Test
    void 핀은_2번까지만_쓰러뜨릴_수_있다_생성자() {
        KnockedPinCount zero = new KnockedPinCount(0);

        assertThatThrownBy(() -> new NormalKnockedPinCounts(Arrays.asList(zero, zero, zero)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AbstractKnockedPinCounts.WRONG_BOWL_COUNT_MESSAGE);
    }

    @Test
    void 핀은_최대_2번까지만_쓰러뜨릴_수_있다() {
        normalKnockedPinCounts.knockOut(0);
        normalKnockedPinCounts.knockOut(0);
        assertThatThrownBy(() -> normalKnockedPinCounts.knockOut(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AbstractKnockedPinCounts.WRONG_BOWL_COUNT_MESSAGE);
    }

    @Test
    void 핀은_스트라이크면_1번만_쓰러뜨릴_수_있다() {
        normalKnockedPinCounts.knockOut(10);
        assertThatThrownBy(() -> normalKnockedPinCounts.knockOut(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AbstractKnockedPinCounts.WRONG_BOWL_COUNT_MESSAGE);
    }

    @Test
    void 쓰러뜨린_핀의_개수의_합은_10개를_넘을_수_없다() {
        normalKnockedPinCounts.knockOut(5);
        assertThatThrownBy(() -> normalKnockedPinCounts.knockOut(6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(KnockedPinCount.INVALID_KNOCK_OUT_COUNT_MESSAGE);
    }

    @Test
    void 스트라이크_확인() {
        normalKnockedPinCounts.knockOut(10);

        assertThat(normalKnockedPinCounts.isStrike()).isTrue();
        assertThat(normalKnockedPinCounts.isSpare()).isFalse();
    }

    @Test
    void 스페어_확인() {
        normalKnockedPinCounts.knockOut(9);
        normalKnockedPinCounts.knockOut(1);

        assertThat(normalKnockedPinCounts.isSpare()).isTrue();
        assertThat(normalKnockedPinCounts.isStrike()).isFalse();
    }

    @Test
    void 첫투구종료_확인() {
        normalKnockedPinCounts.knockOut(9);
        assertThat(normalKnockedPinCounts.isFirstEnd()).isTrue();
        assertThat(normalKnockedPinCounts.isSecondEnd()).isFalse();
    }

    @Test
    void 두번째투구종료_확인() {
        normalKnockedPinCounts.knockOut(9);
        normalKnockedPinCounts.knockOut(1);
        assertThat(normalKnockedPinCounts.isSecondEnd()).isTrue();
        assertThat(normalKnockedPinCounts.isFirstEnd()).isFalse();
    }

    @Test
    void 마지막프레임의_투구인지_확인() {
        assertThat(normalKnockedPinCounts.isFinal()).isFalse();
    }
}
