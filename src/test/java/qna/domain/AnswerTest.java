package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete 테스트")
    void delete_성공() {
        Answer answer = copy(A1);
        answer.delete();

        assertThat(answer.isDeleted()).isTrue();
    }

    public static Answer copy(Answer answer) {
        return new Answer(answer.getWriter(), answer.getQuestion(), answer.getContents());
    }

}
