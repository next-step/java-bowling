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
    @DisplayName("로그인 유저가 작성자이고 답변이 없다면 질문을 삭제할 수 있다")
    void deleteQuestionSoftly() throws CannotDeleteException {
        DeleteHistory deleteHistory = Q2.deleteQuestionSoftly(UserTest.SANJIGI);
        assertThat(deleteHistory).isInstanceOf(DeleteHistory.class);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 유저가 작성자이지만 답변이 있다면 질문을 삭제할 수 없다")
    void throwExceptionWhenAnswerIsNotEmpty() {
        //given, when
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));

        //then
        assertThatThrownBy(() -> Q1.deleteQuestionSoftly(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("로그인 유저가 작성자가 아니면 예외를 반환한다")
    void throwExceptionWhenLoginUserIsNotWriter() {
        assertThatThrownBy(() -> Q1.deleteQuestionSoftly(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

}
