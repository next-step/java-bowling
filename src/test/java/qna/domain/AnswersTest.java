package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    private Question q1;
    private Answer a2;

    @BeforeEach
    void init() {
        q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        a2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    }

    @Test
    @DisplayName("Answers가 정상적으로 삭제되는지 확인한다.")
    void deleteTest() throws CannotDeleteException, NoSuchFieldException, IllegalAccessException {
        q1.addAnswer(AnswerTest.A1);
        q1.addAnswer(AnswerTest.A1);

        Answers answers = getAnswersByReflection(q1);

        answers.delete(UserTest.JAVAJIGI);
        answers.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    @DisplayName("질문자와 삭제자가 다른경우, 예외가 발생한다.")
    void deleteExceptionTest() throws NoSuchFieldException, IllegalAccessException {
        q1.addAnswer(a2);

        Answers answers = getAnswersByReflection(q1);

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> answers.delete(UserTest.JAVAJIGI));
    }

    private Answers getAnswersByReflection(Question question)
        throws NoSuchFieldException, IllegalAccessException {
        Field answersField = Question.class.getDeclaredField("answers");
        answersField.setAccessible(true);
        return (Answers) answersField.get(question);
    }
}