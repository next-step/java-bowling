package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BowlingsTest {

    @Test
    void 일반_다음_생성_성공() {
        Bowlings bowlings = Bowlings.initNormal();
        bowlings.bowling(4);

        assertThat(bowlings.get(0)).isEqualTo(Bowling.of(4));
    }

    @Test
    void 일반_다음_생성_실패() {
        Bowlings bowlings = Bowlings.initNormal();
        bowlings.bowling(10);

        assertThatIllegalArgumentException().isThrownBy(() -> bowlings.bowling(5));
    }

    @Test
    void 마지막_다음_생성_성공() {
        Bowlings bowlings = Bowlings.initFianl();
        bowlings.bowling(10);
        bowlings.bowling(10);
        bowlings.bowling(10);

        assertThat(bowlings.size()).isEqualTo(3);
    }
}
