package qna.domain.answer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

  @DisplayName("생성 테스트")
  @Test
  void createTest(){
    List<Answer> answers = new ArrayList<>();
    Answer answer = new Answer();
    answers.add(answer);

    Assertions.assertThat(Answers.of(answers).head().get()).isEqualTo(answer);
  }
}