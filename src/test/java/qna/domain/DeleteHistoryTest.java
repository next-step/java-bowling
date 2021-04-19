package qna.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static qna.domain.UserTest.JAVAJIGI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoryTest {

  private Question question;
  private Answer answer;

  @BeforeEach
  void setUp() {
    question = new Question("title1", "contents1").writeBy(JAVAJIGI);
    answer = new Answer(JAVAJIGI, question, "Answers Contents1");
  }

  @Test
  @DisplayName("질문으로 삭제 기록을 만든다")
  void fromQuestion() {
    //given
    //when
    DeleteHistory history = DeleteHistory.ofQuestion(question.getId(), JAVAJIGI);
    //then
    assertAll(
        () -> assertEquals(history.getContentId(), question.getId()),
        () -> assertEquals(history.getContentType(), ContentType.QUESTION),
        () -> assertEquals(history.getDeletedBy(), question.getWriter())
    );
  }

  @Test
  @DisplayName("답변으로 삭제 기록을 만든다")
  void fromAnswer() {
    //given
    //when
    DeleteHistory history = DeleteHistory.ofAnswer(answer.getId(), JAVAJIGI);
    //then
    assertAll(
        () -> assertEquals(history.getContentId(), answer.getId()),
        () -> assertEquals(history.getContentType(), ContentType.ANSWER),
        () -> assertEquals(history.getDeletedBy(), answer.getWriter())
    );
  }
}