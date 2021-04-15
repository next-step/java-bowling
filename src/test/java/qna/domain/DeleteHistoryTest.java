package qna.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static qna.domain.AnswerTest.A1;
import static qna.domain.QuestionTest.Q1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoryTest {

  @Test
  @DisplayName("질문으로 삭제 기록을 만든다")
  void fromQuestion() {
    //given
    //when
    DeleteHistory history = DeleteHistory.from(Q1);
    //then
    assertAll(
        () -> assertEquals(history.getContentId(), Q1.getId()),
        () -> assertEquals(history.getContentType(), ContentType.QUESTION),
        () -> assertEquals(history.getDeletedBy(), Q1.getWriter())
    );
  }

  @Test
  @DisplayName("답변으로 삭제 기록을 만든다")
  void fromAnswer() {
    //given
    //when
    DeleteHistory history = DeleteHistory.from(A1);
    //then
    assertAll(
        () -> assertEquals(history.getContentId(), A1.getId()),
        () -> assertEquals(history.getContentType(), ContentType.ANSWER),
        () -> assertEquals(history.getDeletedBy(), A1.getWriter())
    );
  }
}