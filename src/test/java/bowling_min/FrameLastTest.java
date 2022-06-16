//package bowling_min;
//
//import bowling_step3.domain.*;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class FrameLastTest {
//    FramesTest frames = FramesTest.create();
//    Frame lastFrame = frames.get(9);
//
//    @Test
//    public void miss() {
//        lastFrame.playManual(8, frames);
//        lastFrame.playManual(1, frames);
//        assertThat(lastFrame.done()).isTrue();
//    }
//
//    @Test
//    public void spare_isnot_endgame() {
//        lastFrame.playManual(8, frames);
//        lastFrame.playManual(2, frames);
//        System.out.println(lastFrame);
//        assertThat(lastFrame.done()).isFalse();
//    }
//
//    @Test
//    public void spare_is_endgame() {
//        lastFrame.playManual(8, frames);
//        lastFrame.playManual(2, frames);
//        lastFrame.playManual(7, frames);
//        assertThat(lastFrame.done()).isTrue();
//    }
//
//    @Test
//    public void game_over() {
//        lastFrame.playManual(8, frames);
//        lastFrame.playManual(2, frames);
//        lastFrame.playManual(7, frames);
//        assertThatThrownBy(() -> {
//            lastFrame.playManual(3, frames);
//        }).isInstanceOf(UnsupportedOperationException.class);
//    }
//
//    @Test
//    public void getScore_miss() {
//        lastFrame.playManual(8, frames);
//        lastFrame.playManual(1, frames);
//        assertThat(lastFrame.getScore()).isEqualTo(9);
//    }
//
//    @Test
//    public void getScore_cannot_calculate() {
//        assertThatThrownBy(() -> {
//            lastFrame.playManual(8, frames);
//            lastFrame.getScore();
//        }).isInstanceOf(UnsupportedOperationException.class);
//    }
//
//    @Test
//    public void getScore_spare() {
//        lastFrame.playManual(8, frames);
//        lastFrame.playManual(2, frames);
//        lastFrame.playManual(9, frames);
//        assertThat(lastFrame.getScore()).isEqualTo(19);
//    }
//
//    @Test
//    public void getScore_twoStrike() {
//        lastFrame.playManual(10, frames);
//        lastFrame.playManual(10, frames);
//        lastFrame.playManual(8, frames);
//        assertThat(lastFrame.getScore()).isEqualTo(28);
//    }
//
//    @Test
//    public void getScore_threeStrike() {
//        lastFrame.playManual(10, frames);
//        lastFrame.playManual(10, frames);
//        lastFrame.playManual(10, frames);
//        assertThat(lastFrame.getScore()).isEqualTo(30);
//    }
//
//    @Test
//    public void getScore_9프레임_Strike() {
//        Subtotal subtotal = new Subtotal(State.WAIT_TWICE, 10);
//        lastFrame.playManual(10, frames);
//        lastFrame.playManual(10, frames);
//        assertThat(lastFrame.calculateAdditionalScore(subtotal).value()).isEqualTo(30);
//    }
//
//    @Test
//    public void getScore_9프레임_Spare() {
//        Subtotal subtotal = new Subtotal(State.WAIT_ONCE, 10);
//        lastFrame.playManual(9, frames);
//        lastFrame.playManual(1, frames);
//        assertThat(lastFrame.calculateAdditionalScore(subtotal).value()).isEqualTo(19);
//    }
//
//    @Test
//    public void getScore_9프레임_Strike_notReady() {
//        assertThatThrownBy(() -> {
//            Scores scores = new Scores(Arrays.asList(10), 0);
//            lastFrame.playManual(10, frames);
//            Subtotal subtotal = new Subtotal(State.WAIT_TWICE, 10);
//            lastFrame.calculateAdditionalScore(subtotal);
//        }).isInstanceOf(UnsupportedOperationException.class);
//    }
//
////    @Test
////    public void getDesc_3Strike() {
////        lastFrame.bowl(10).bowl(10).bowl(10);
////        assertThat(lastFrame.getDesc()).isEqualTo("X | X | X");
////    }
////
////    @Test
////    public void getDesc_Spare() {
////        lastFrame.bowl(8).bowl(2).bowl(10);
////        assertThat(lastFrame.getDesc()).isEqualTo("8 | / | X");
////    }
////
////    @Test
////    public void getDesc_Miss() {
////        lastFrame.bowl(8).bowl(1);
////        assertThat(lastFrame.getDesc()).isEqualTo("8 | 1");
////
////    }
//}