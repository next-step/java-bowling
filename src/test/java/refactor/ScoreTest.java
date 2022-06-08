//package refactor;
//
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class ScoreTest {
//    @Test
//    void 각라운드는socre0remaining2에서시작() {
//        Score score = new Score();
//        assertThat(score).isEqualTo(new Score(0, 2));
//    }
//
//    @Test
//    void pitch한번하면remaning은1차감() {
//        Score score = new Score();
//        Score actual = score.pitchManual(2);
//        assertThat(actual).isEqualTo(new Score(2, 1));
//    }
//
//    @Test
//    void score는10을넘을수없음() {
//        assertThatThrownBy(() -> new Score(11, 1)).isInstanceOf(IllegalArgumentException.class);
//        assertThatThrownBy(() -> new Score(9, 1).pitchManual(2)).isInstanceOf(IllegalArgumentException.class);
//    }
//}
