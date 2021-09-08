package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.ARRAY;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_another_user_test() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("로그인 유저와 질문자가 같고 답변이 없으면 삭제 성공")
    public void delete_question_success() throws CannotDeleteException {

        assertThat(Q1.delete(UserTest.JAVAJIGI).isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("로그인 유저와 질문자가 같고 답변이 있고, 답변자가 다르면 에러")
    public void delete_question_answer_fail() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }


    @Test
    @DisplayName("로그인 유저와 질문자가 같고 답변이 있고, 답변자 모두 같으면 성공")
    public void delete_answer_fail() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        assertThat(Q1.delete(UserTest.JAVAJIGI).isDeleted()).isEqualTo(true);
    }
}
