package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.NotQuestionWriterException;
import qna.exception.OtherUserAnswerFoundException;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("로그인 유저가 다른 사람이 작성한 질문글을 삭제할려고 시도할때, 삭제할수 없고 예외를 던진다.")
    void tryDeleteQuestionByOtherUser() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .isInstanceOf(NotQuestionWriterException.class);
    }

    @Test
    @DisplayName("답변에 작성자 이외의 유저가 답변을 달았을 경우, 삭제할 수 없고 예외를 던진다.")
    void tryDeleteQuestionWithAnswerWrittenByOtherUser() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
            .isInstanceOf(OtherUserAnswerFoundException.class);
    }

    @Test
    @DisplayName("질문글에 아무런 답변이 없을때, 작성자는 질문글을 지울수 있다.")
    void canDeleteQuestionWithoutAnyAnswer() {
        assertThatCode(() -> Q1.delete(UserTest.JAVAJIGI))
            .doesNotThrowAnyException();
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("질문글과 질문글에 달린 모든 답변이 작성자본인일때는 질문글 삭제가 가능하다.")
    void canDeleteQuestion() {
        Q1.addAnswer(AnswerTest.A1);
        assertThatCode(() -> Q1.delete(UserTest.JAVAJIGI))
            .doesNotThrowAnyException();
        assertThat(Q1.isDeleted()).isEqualTo(true);
    }

}
