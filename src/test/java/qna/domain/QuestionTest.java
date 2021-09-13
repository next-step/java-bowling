package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.WrongUserDeleteTryException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("로그인 유저가 다른 사람이 작성한 질문글을 삭제할려고 시도할때, 예외를 던진다.")
    void tryRemoveQuestionByOtherUser() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .isInstanceOf(WrongUserDeleteTryException.class);
    }
}
