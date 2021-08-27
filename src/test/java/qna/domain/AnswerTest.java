package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 작성자와 로그인 사용자가 일치하면 답변을 삭제할 수 있다.")
    @Test
    void deletable() throws Exception {
        Answer answer = AnswerTest.A1;
        answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }
}
