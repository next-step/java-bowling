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

    @Test
    void 종료_2회투구() {
        Bowlings bowlings = Bowlings.initNormal();

        bowlings.bowling(5);
        assertThat(bowlings.isEnd()).isFalse();

        bowlings.bowling(5);
        assertThat(bowlings.isEnd()).isTrue();
    }

    @Test
    void 종료_노말_스트라이크() {
        Bowlings bowlings = Bowlings.initNormal();
        bowlings.bowling(10);
        assertThat(bowlings.isEnd()).isTrue();
    }

    @Test
    void 종료_마지막_() {
        Bowlings bowlings = Bowlings.initFianl();
        bowlings.bowling(10);
        bowlings.bowling(10);
        bowlings.bowling(10);
        assertThat(bowlings.isEnd()).isTrue();
    }
}
