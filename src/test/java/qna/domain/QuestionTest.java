package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 삭제 성공 테스트")
    @Test
    public void deleteSuccessTest() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("삭제할 질문이 다른 사람의 질문일때 Exception 테스트")
    @Test
    public void delete_others_question_throw_exception(){
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

}
