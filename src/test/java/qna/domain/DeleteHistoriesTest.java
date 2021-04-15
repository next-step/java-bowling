package qna.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static qna.domain.AnswerTest.A1;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {

  @Test
  @DisplayName("질문으로 답변과 질문이 모두 포함된, 삭제기록을 만든다.")
  void from() {
    //given
    Q1.addAnswer(A1);
    //when
    DeleteHistories deleteHistories = DeleteHistories.from(Q1);
    //then
    List<DeleteHistory> historyList = deleteHistories.getList();
    assertAll(
        () -> assertEquals(historyList.size(), 2),
        () -> assertEquals(historyList.get(0).getContentType(), ContentType.QUESTION),
        () -> assertEquals(historyList.get(0).getDeletedBy(), JAVAJIGI),
        () -> assertEquals(historyList.get(1).getContentType(), ContentType.ANSWER),
        () -> assertEquals(historyList.get(1).getDeletedBy(), JAVAJIGI)
    );
  }
}