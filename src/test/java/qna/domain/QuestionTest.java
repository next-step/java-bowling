package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("질문 작성자와 로그인 사용자 같지 않을 때, 질문 삭제 가능성 테스트 ")
    public void checkOwnerExceptionTest() throws Exception {
        assertThatThrownBy(() -> {
            Q1.checkDelete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }



}
