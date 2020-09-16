package bowling.view;

import bowling.domain.Game;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultViewerTest {
//    @Test
//    void getSumScores() {
//        Game game = Game.start(USERNAME);
//        game.hit(9);
//        game.hit(1);
//        assertThat(game.getSumScores().get(0)).isEqualTo(10);
//    }
//
//    @Test
//    void getSumScores_all_strike() {
//        Game game = Game.start(USERNAME);
//        for (int index = 0; index < 12; index++) {
//            game.hit(10);
//        }
//
//        List<Integer> scores = game.getSumScores();
//
//        assertThat(scores.get(0)).isEqualTo(30);
//        assertThat(scores.get(1)).isEqualTo(60);
//        assertThat(scores.get(2)).isEqualTo(90);
//        assertThat(scores.get(3)).isEqualTo(120);
//        assertThat(scores.get(4)).isEqualTo(150);
//        assertThat(scores.get(5)).isEqualTo(180);
//        assertThat(scores.get(6)).isEqualTo(210);
//        assertThat(scores.get(7)).isEqualTo(240);
//        assertThat(scores.get(8)).isEqualTo(270);
//        assertThat(scores.get(9)).isEqualTo(300);
//    }
//
//    @Test
//    void getSumScores_lastSpare() {
//        Game game = Game.start(USERNAME);
//        for (int index = 0; index < 12; index++) {
//            if (index == 9) {
//                game.hit(9);
//            } else if (index == 10) {
//                game.hit(1);
//            } else {
//                game.hit(10);
//            }
//        }
//
//        List<Integer> scores = game.getSumScores();
//
//        assertThat(scores.get(0)).isEqualTo(30);
//        assertThat(scores.get(1)).isEqualTo(60);
//        assertThat(scores.get(2)).isEqualTo(90);
//        assertThat(scores.get(3)).isEqualTo(120);
//        assertThat(scores.get(4)).isEqualTo(150);
//        assertThat(scores.get(5)).isEqualTo(180);
//        assertThat(scores.get(6)).isEqualTo(210);
//        assertThat(scores.get(7)).isEqualTo(240);
//        assertThat(scores.get(8)).isEqualTo(270);
//        assertThat(scores.get(9)).isEqualTo(290);
//    }
//
//    @Test
//    void getSumScores_sample() {
//        Game game = Game.start(USERNAME);
//
//        game.hit(1); game.hit(2);
//        game.hit(9); game.hit(1);
//        game.hit(2); game.hit(2);
//        game.hit(10); game.hit(0);
//        game.hit(10); game.hit(0);
//        game.hit(10); game.hit(0);
//        game.hit(10); game.hit(9);
//        game.hit(10); game.hit(0);
//        game.hit(9); game.hit(1);
//        game.hit(10); game.hit(9);  game.hit(1);
//
//        List<Integer> scores = game.getSumScores();
//
//        assertThat(scores.get(0)).isEqualTo(3);
//        assertThat(scores.get(1)).isEqualTo(15);
//        assertThat(scores.get(2)).isEqualTo(20);
//        assertThat(scores.get(3)).isEqualTo(50);
//        assertThat(scores.get(4)).isEqualTo(80);
//        assertThat(scores.get(5)).isEqualTo(110);
//        assertThat(scores.get(6)).isEqualTo(139);
//        assertThat(scores.get(7)).isEqualTo(159);
//        assertThat(scores.get(8)).isEqualTo(179);
//        assertThat(scores.get(9)).isEqualTo(199);
//    }
}
