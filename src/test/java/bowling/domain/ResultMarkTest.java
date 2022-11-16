package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class ResultMarkTest {

    @Test
    void 첫번째_투구에_모든_핀_쓰러뜨리면_스트라이크() {
        FallenPins firstFallenPins = FallenPins.of(10);

        assertThat(ResultMark.getResultMark(firstFallenPins, null))
                .isEqualTo(ResultMark.STRIKE);
    }

    @Test
    void 첫번째_투구_후_핀이_남아있고_두번째_투구에_모든_핀_쓰러뜨리면_스페어() {
        FallenPins firstFallenPins = FallenPins.of(4);
        FallenPins secondFallenPins = FallenPins.of(6);

        assertThat(ResultMark.getResultMark(secondFallenPins, firstFallenPins))
                .isEqualTo(ResultMark.SPARE);
    }

    @Test
    void 두번째_투구_후에도_모든_핀을_쓰러뜨리지_못하면_미스() {
        FallenPins firstFallenPins = FallenPins.of(4);
        FallenPins secondFallenPins = FallenPins.of(3);

        assertThat(ResultMark.getResultMark(secondFallenPins, firstFallenPins))
                .isEqualTo(ResultMark.MISS);
    }

    @Test
    void 투구_시_하나도_핀을_못쓰러뜨리면_거터() {
        assertAll(
                () -> assertThat(ResultMark.getResultMark(FallenPins.of(0), null))
                        .isEqualTo(ResultMark.GUTTER),
                () -> assertThat(ResultMark.getResultMark(FallenPins.of(0), FallenPins.of(7)))
                        .isEqualTo(ResultMark.GUTTER),
                () -> assertThat(ResultMark.getResultMark(FallenPins.of(0), FallenPins.of(0)))
                        .isEqualTo(ResultMark.GUTTER)
        );
    }

}
