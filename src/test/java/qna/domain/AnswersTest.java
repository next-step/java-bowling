package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnswersTest {

  @Test
  @DisplayName("질문자와 답변 글의 모든 답변자 같은 경우 삭제한다.")
  void deleteAnswers() throws CannotDeleteException {
    Answers answers = new Answers();
    answers.add(AnswerTest.A1);

    List<DeleteHistory> deleteHistories = answers.deleteAll(AnswerTest.A1.getWriter());
    assertEquals(1, deleteHistories.size());
  }

  @Test
  @DisplayName("질문의 답변 글에 작성자가 아닌 다른 유저의 글이 있을 경우 삭제하지 않고 exception을 던진다.")
  void deleteAnswers_hasAnswerByAnotherUser() {
    Answers answers = new Answers();
    answers.add(AnswerTest.A1);
    answers.add(AnswerTest.A2);

    assertThrows(CannotDeleteException.class, () -> answers.deleteAll(AnswerTest.A1.getWriter()));
  }

}