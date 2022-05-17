package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    private Question question;

    @BeforeEach
    void setUp() {
        question = createDefaultTestQuestion();
    }

    @Test
    void createTest() {
        Question question = new Question("createTitle", "createContents");

        assertThat(question).isNotNull();
    }

    @DisplayName("질문을 질문 작성자가 삭제하면 성공한다.")
    @Test
    void deleteWithUserTest1() throws CannotDeleteException {
        question.doNotDelete();
        assertThat(question.isDeleted()).isFalse();

        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문을 질문 작성자가 아닌 사람이 삭제하면 실패한다.")
    @Test
    void deleteWithUserTest2() {
        question.doNotDelete();
        assertThat(question.isDeleted()).isFalse();

        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 작성자는 같지만 답변 중 작성자가 다른 경우에 예외 발생한다.")
    @Test
    void deleteWithUserTest3() {
        question.addAnswer(A1);
        question.addAnswer(A2);
        question.doNotDelete();
        assertThat(question.isDeleted()).isFalse();

        assertThatThrownBy(() -> {
            question.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 작성자와 답변 작성자가 같은 경우에 삭제 성공한다.")
    @Test
    void deleteSuccessTest1() throws CannotDeleteException {
        question.doNotDelete();
        assertThat(question.isDeleted()).isFalse();

        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문만 있는 경우에 삭제 성공한다.")
    @Test
    void deleteSuccessTest2() throws CannotDeleteException {
        // given
        assertThat(question.isDeleted()).isFalse();

        // when
        question.delete(UserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문 삭제 성공 시, 질문과 딸려있는 답변 전부 삭제되어있다.")
    @Test
    void deleteSuccessTest3() throws CannotDeleteException {
        question.doNotDelete();
        assertThat(question.isDeleted()).isFalse();

        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(question.getAnswers().stream()
            .allMatch(Answer::isDeleted)).isTrue();
    }

    private static Question createDefaultTestQuestion() {
        return new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

}
