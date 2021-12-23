package bowling;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class FinalKnockedPinCountsTest {
    private final FinalKnockedPinCounts finalKnockedPinCounts = new FinalKnockedPinCounts();

    @Test
    void 핀은_3번까지만_쓰러뜨릴_수_있다_생성자() {
        KnockedPinCount zero = new KnockedPinCount(0);
        assertThatThrownBy(() -> new NormalKnockedPinCounts(Arrays.asList(zero, zero, zero, zero)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AbstractKnockedPinCounts.WRONG_BOWL_COUNT_MESSAGE);
    }

    @Test
    void 핀은_스트라이크이면_3번까지_쓰러뜨릴_수_있다() {
        assertDoesNotThrow(() -> {
            finalKnockedPinCounts.knockOut(10);
            finalKnockedPinCounts.knockOut(0);
            finalKnockedPinCounts.knockOut(0);
        });
    }

    @Test
    void 핀은_스페어면_3번까지_쓰러뜨릴_수_있다() {
        assertDoesNotThrow(() -> {
            finalKnockedPinCounts.knockOut(5);
            finalKnockedPinCounts.knockOut(5);
            finalKnockedPinCounts.knockOut(0);
        });
    }

    @Test
    void 스페어나_스트라이크가_아닐경우_2번까지_쓰러뜨릴_수_있다() {
        finalKnockedPinCounts.knockOut(5);
        finalKnockedPinCounts.knockOut(4);
        assertThatThrownBy(() -> finalKnockedPinCounts.knockOut(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AbstractKnockedPinCounts.WRONG_BOWL_COUNT_MESSAGE);
    }

    @Test
    void 스페어나_스트라이크가_아닐경우_쓰러뜨린_핀의_개수의_합은_10개를_넘을_수_없다() {
        finalKnockedPinCounts.knockOut(5);
        assertThatThrownBy(() -> finalKnockedPinCounts.knockOut(6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(KnockedPinCount.INVALID_KNOCK_OUT_COUNT_MESSAGE);
    }

    @Test
    void 스트라이크_확인() {
        finalKnockedPinCounts.knockOut(10);

        assertThat(finalKnockedPinCounts.isStrike()).isTrue();
        assertThat(finalKnockedPinCounts.isSpare()).isFalse();
    }

    @Test
    void 스페어_확인() {
        finalKnockedPinCounts.knockOut(9);
        finalKnockedPinCounts.knockOut(1);

        assertThat(finalKnockedPinCounts.isSpare()).isTrue();
        assertThat(finalKnockedPinCounts.isStrike()).isFalse();
    }

    @Test
    void 첫투구종료_확인() {
        finalKnockedPinCounts.knockOut(9);
        assertThat(finalKnockedPinCounts.isFirstEnd()).isTrue();
        assertThat(finalKnockedPinCounts.isSecondEnd()).isFalse();
    }

    @Test
    void 두번째투구종료_확인() {
        finalKnockedPinCounts.knockOut(9);
        finalKnockedPinCounts.knockOut(1);
        assertThat(finalKnockedPinCounts.isSecondEnd()).isTrue();
        assertThat(finalKnockedPinCounts.isFirstEnd()).isFalse();
    }

    @Test
    void 마지막프레임의_투구인지_확인() {
        assertThat(finalKnockedPinCounts.isFinal()).isTrue();
    }

}
