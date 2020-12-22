package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswersTest {

    private Answers answers;

    @BeforeEach
    void initAnswers() {
        answers = new Answers();
    }

    @Test
    public void delete_성공() {
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer2"));

        assertThatCode(() -> {
            answers.canDeleteBy(UserTest.JAVAJIGI);
            answers.delete();
        }).doesNotThrowAnyException();
    }

    @Test
    public void delete_다른_사람이_쓴_답변() {
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer2"));

        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            answers.canDeleteBy(UserTest.SANJIGI);
            answers.delete();
        }).withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    public void delete_일부만_다른_사람이_쓴_답변() {
        answers.add(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "answer1"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer2"));

        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            answers.canDeleteBy(UserTest.SANJIGI);
            answers.delete();
        }).withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
