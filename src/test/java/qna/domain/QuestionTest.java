package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("질문자와 로그인사용자 다르면 예외를 던진다.")
    void 질문자와_로그인사용자_다르다(){
        assertThatThrownBy(()->{
            Q2.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문을 삭제하면 question의 deleted 상태는 true가 된다.")
    void 질문자와_로그인사용자_같다(){
        DeleteHistories deleteHistory = Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문을 삭제하면 질문, 답변에 대한 deleteHistory를 반환한다.")
    void 질문자와_답변자_로그인사용자_같(){
        Question question = new Question(1L,"title3", "contents").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(1L, UserTest.JAVAJIGI, question, "answer1"));
        question.addAnswer(new Answer(2L, UserTest.JAVAJIGI, question, "answer2"));

        DeleteHistories deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories.getDeleteHistories()).containsExactly(
                new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI),
                new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI),
                new DeleteHistory(ContentType.ANSWER, 2L, UserTest.JAVAJIGI)
        );
        assertThat(question.isDeleted()).isTrue();
    }
}
