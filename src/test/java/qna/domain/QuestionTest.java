package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 질문을 삭제 할 수 없다.")
    void delete_실패() {
        assertThatThrownBy(()-> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
    void delete_성공() throws CannotDeleteException {
        Question question = new Question("제목","답변1").writeBy(UserTest.JAVAJIGI);
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }
}
