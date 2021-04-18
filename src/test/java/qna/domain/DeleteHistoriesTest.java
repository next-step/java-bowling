package qna.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static qna.domain.UserTest.JAVAJIGI;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {

  private Question question;
  private Answer answer;

  @BeforeEach
  void setUp() {
    question = new Question("title1", "contents1").writeBy(JAVAJIGI);
    answer = new Answer(JAVAJIGI, question, "Answers Contents1");
  }

  @Test
  @DisplayName("질문으로 답변과 질문이 모두 포함된, 삭제기록을 만든다.")
  void from() {
    //given
    List<DeleteHistory> histories = new ArrayList<>();
    histories.add(DeleteHistory.ofQuestion(question.getId(), JAVAJIGI));
    histories.add(DeleteHistory.ofAnswer(answer.getId(), JAVAJIGI));
    //when
    DeleteHistories deleteHistories = DeleteHistories.from(histories);
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