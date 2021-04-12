package qna.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 확인")
    public void isDeleted() {
      A1.delete();
      assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변자인지 확인")
    public void isOwner() {
        assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

}
