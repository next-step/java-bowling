package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {

    private Question questionByJavajigi;
    private Answer answerByJavajigi1;
    private Answer answerByJavajigi2;

    @BeforeEach
    private void setup() {
        questionByJavajigi = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answerByJavajigi1 = new Answer(UserTest.JAVAJIGI, questionByJavajigi, "Answers Contents1");
        answerByJavajigi2 = new Answer(UserTest.JAVAJIGI, questionByJavajigi, "Answers Contents2");
        questionByJavajigi.addAnswer(answerByJavajigi1);
        questionByJavajigi.addAnswer(answerByJavajigi2);
    }

    @DisplayName("질문자가 아닌 경우 질문을 삭제 할 수 없다.")
    @Test
    public void deleteQuestionFailTest() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> questionByJavajigi.deleteBy(UserTest.SANJIGI));
    }

    @DisplayName("질문 삭제 시, 질문은 삭제 상태가 된다.")
    @Test
    public void deleteQuestionSuccessTest() {
        assertThat(questionByJavajigi.isDeleted()).isFalse();
        questionByJavajigi.deleteBy(UserTest.JAVAJIGI);
        assertThat(questionByJavajigi.isDeleted()).isTrue();
    }

    @DisplayName("질문 삭제 시, 질문의 모든 답변도 함께 삭제된다.")
    @Test
    public void deleteQuestionWithAllAnswers() {
        questionByJavajigi.deleteBy(UserTest.JAVAJIGI);
        assertThat(answerByJavajigi1.isDeleted()).isTrue();
        assertThat(answerByJavajigi2.isDeleted()).isTrue();
    }
}
