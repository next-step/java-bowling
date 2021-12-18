package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    private Question question;

    public Answer a1;
    private Answer a2;

    @BeforeEach
    void init() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        a1 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        a2 = new Answer(UserTest.SANJIGI, question, "Answers Contents2");

    }

    @Test
    @DisplayName("Answers가 정상적으로 삭제되는지 확인한다.")
    void deleteTest() throws CannotDeleteException, NoSuchFieldException, IllegalAccessException {
        question.addAnswer(a1);
        question.addAnswer(a1);

        Answers answers = getAnswersByReflection(question);

        answers.delete(UserTest.JAVAJIGI);
        answers.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    @DisplayName("질문자와 삭제자가 다른경우, 예외가 발생한다.")
    void deleteExceptionTest() throws NoSuchFieldException, IllegalAccessException {
        question.addAnswer(a2);

        Answers answers = getAnswersByReflection(question);

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