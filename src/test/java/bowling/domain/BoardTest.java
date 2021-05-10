package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoardTest {

  @Test
  @DisplayName("생성, 1라운드 테스트")
  void createTest() {
    Board board = new Board();
    board.addRound(new Player("STS"));

    assertThat(board.size()).isEqualTo(1);
  }


}