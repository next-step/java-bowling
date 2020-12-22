package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void initAnswer() {
         answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1");
    }

    @Test
    public void delete_성공() {
        assertThatCode(() -> {
            answer.canDeleteBy(UserTest.JAVAJIGI);
            answer.delete();
        }).doesNotThrowAnyException();

        assertThat(answer.isDeleted()).isEqualTo(true);
    }

    @Test
    public void delete_다른_사람이_쓴_답변() {
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            answer.canDeleteBy(UserTest.SANJIGI);
            answer.delete();
        }).withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

        assertThat(answer.isDeleted()).isEqualTo(false);
    }
}
