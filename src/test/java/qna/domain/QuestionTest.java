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

    @BeforeEach
    void setUp() {
        Q1.addAnswer(A1);
        Q2.addAnswer(A1);
        Q2.addAnswer(A2);
    }

    @Test
    void createTest() {
        Question question = new Question("createTitle", "createContents");

        assertThat(question).isNotNull();
    }

    @DisplayName("질문을 질문 작성자가 삭제하면 성공한다.")
    @Test
    void deleteWithUserTest1() throws CannotDeleteException {
        Q1.doNotDelete();
        assertThat(Q1.isDeleted()).isFalse();

        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문을 질문 작성자가 아닌 사람이 삭제하면 실패한다.")
    @Test
    void deleteWithUserTest2() throws CannotDeleteException {
        Q1.doNotDelete();
        assertThat(Q1.isDeleted()).isFalse();

        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 작성자와 답변 작성자가 같은 경우에 삭제 성공한다.")
    @Test
    void deleteSuccessTest1() throws CannotDeleteException {
        Q1.doNotDelete();
        assertThat(Q1.isDeleted()).isFalse();

        Q1.delete();

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문만 있는 경우에 삭제 성공한다.")
    @Test
    void deleteSuccessTest2() throws CannotDeleteException {
        // given
        Question question = new Question("createTitle", "createContents");
        assertThat(question.isDeleted()).isFalse();

        // when
        question.delete();

        // then
        assertThat(question.isDeleted()).isTrue();
    }

}
