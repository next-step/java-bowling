package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    public Answer a1;
    private Answer a2;

    @BeforeEach
    void init() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        a1 = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        a2 = new Answer(UserTest.SANJIGI, question, "Answers Contents2");

    }

    @Test
    @DisplayName("Answers가 정상적으로 삭제되는지 확인한다.")
    void deleteTest() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.addAll(a1, a1);

        answers.delete(UserTest.JAVAJIGI);
        answers.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    @DisplayName("질문자와 삭제자가 다른경우, 예외가 발생한다.")
    void deleteExceptionTest() {
        Answers answers = new Answers();
        answers.add(a2);

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> answers.delete(UserTest.JAVAJIGI));
    }
}