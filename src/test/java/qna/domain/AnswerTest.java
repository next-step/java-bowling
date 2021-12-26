package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete_성공")
    public void delete_success() throws Exception {
        Answer answer = A1;
        answer.canDelete(UserTest.JAVAJIGI);
        answer.setDeleted(true);
        Assertions.assertThat(answer.isDeleted()).isTrue();
    }
}
