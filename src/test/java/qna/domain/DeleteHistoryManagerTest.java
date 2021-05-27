package qna.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoryManagerTest {
  @Test
  @DisplayName("Question과 Answers를 통해서 생성한다")
  public void create() throws Exception {
    //given
    Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
    //when
    //then
    assertDoesNotThrow(
        () -> new DeleteHistoryManager(answers, QuestionTest.Q1)
    );
  }

  @Test
  @DisplayName("DeleteHistories와 Boolean 값을 받아서 삭제 처리를 진행한다")
  public void deleteProcessing() throws Exception {
    //given
    Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
    DeleteHistoryManager deleteHistoryManager = new DeleteHistoryManager(answers, QuestionTest.Q1);
    DeleteHistories deleteHistories = new DeleteHistories();
    //when
    deleteHistoryManager.deleteProcess(true, deleteHistories);
    //then
    assertEquals(deleteHistories.of().size(), 3);
  }

}