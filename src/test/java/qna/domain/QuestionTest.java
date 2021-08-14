package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문글의 작성자가 아닐 경우 질문글을 삭제할 수 없다는 예외를 발생시킨다")
    void deleteException() throws Exception {
        Assertions.assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
