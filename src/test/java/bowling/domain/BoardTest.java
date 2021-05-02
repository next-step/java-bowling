package bowling.domain;

import bowling.domain.frame.Round;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

  @Test
  @DisplayName("생성, 1라운드 테스트")
  void createTest(){
    Board board = new Board();
    board.addRound(new Player("STS"));

    Assertions.assertThat(board.size()).isEqualTo(1);
  }


}