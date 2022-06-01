//package bowling.domain;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import bowling.domain.HitState.BonusShotCount;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class HitStateTest {
//
//    private BonusShotCount count;
//
//    @BeforeEach
//    void setUp() {
//        count = BonusShotCount.ofStrike();
//    }
//
//    @Test
//    void createStrikeBonusCountTest() {
//        assertThat(count).isNotNull();
//    }
//
//    @Test
//    void minusTest() {
//        count.minus();
//
//        assertThat(count.get()).isEqualTo(1);
//    }
//
//    @Test
//    void isRemainedTest() {
//        count.minus();
//        assertThat(count.isRemained()).isTrue();
//        count.minus();
//        assertThat(count.isRemained()).isFalse();
//    }
//
//}
