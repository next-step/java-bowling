package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제실행 대상 객체 생성.")
    @Test
    void deleteTest() {
        Answer answer = ofUser(1L, UserTest.JAVAJIGI);
        Answer otherAnswer = ofUser(2L, UserTest.SANJIGI);

        DeleteHistory delete = answer.delete();

        assertThat(answer.delete()).isEqualTo(delete);
        assertThat(otherAnswer.delete()).isNotEqualTo(delete);
    }

    public static Answer ofUser(long id, User user) {
        return new Answer(id, user, QuestionTest.Q1, "Answers Contents");
    }
}
