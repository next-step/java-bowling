package qna.domain;

import org.junit.jupiter.api.Test;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnswersTest {

  @Test
  void hasAnotherUserTest() {
    Answers answers = new Answers();
    answers.add(AnswerTest.A1);
    answers.add(AnswerTest.A2);

    assertEquals(TRUE, answers.hasAnotherUser(User.GUEST_USER));
  }

}