package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class BowlingsTest {

    @Test
    void 일반_다음_생성_성공() {
        Bowlings bowlings = new NormalBowlings();
        bowlings.createNext(4);

        assertThat(bowlings.get(0)).isEqualTo(Bowling.of(4));
    }

    @Test
    void 일반_다음_생성_실패() {
        Bowlings bowlings = new NormalBowlings();
        bowlings.createNext(10);

        assertThatIllegalArgumentException().isThrownBy(() -> bowlings.createNext(5));
    }

    @Test
    void 마지막_다음_생성_성공() {
        Bowlings bowlings = new FianlBowlings();
        bowlings.createNext(10);
        bowlings.createNext(10);
        bowlings.createNext(10);

        assertThat(bowlings.size()).isEqualTo(3);
    }
}
