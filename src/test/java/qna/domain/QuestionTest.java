package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.ForbiddenException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("로그인 유저가 아닌 사람이 삭제하면 에러")
    public void delete_another_user_test() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("로그인 유저와 질문자가 같고 답변이 없으면 삭제 성공")
    public void delete_question_success() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("로그인 유저와 질문자가 같고 답변이 있고, 답변자가 다르면 에러")
    public void delete_question_answer_fail() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI)).isInstanceOf(ForbiddenException.class);
    }


    @Test
    @DisplayName("로그인 유저와 질문자가 같고 답변이 있고, 답변자 모두 같으면 성공")
    public void delete_answer_fail() throws CannotDeleteException {
        Q2.addAnswer(AnswerTest.A2);
        Q2.delete(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isEqualTo(true);
    }
}
