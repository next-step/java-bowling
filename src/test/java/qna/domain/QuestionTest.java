package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;
import qna.exception.NotQuestionWriterException;
import qna.exception.OtherUserAnswerFoundException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("로그인 유저가 다른 사람이 작성한 질문글을 삭제할려고 시도할때, 삭제할수 없고 예외를 던진다.")
    void tryRemoveQuestionByOtherUser() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .isInstanceOf(NotQuestionWriterException.class);
    }

    @Test
    @DisplayName("답변에 작성자 이외의 유저가 답변을 달았을 경우, 삭제할 수 없고 예외를 던진다.")
    void bbb() {
        Q1.addAnswer2(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
            .isInstanceOf(OtherUserAnswerFoundException.class);
    }
}
