package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    @DisplayName("삭제후 deleted 는 true다.")
    void 삭제성공_로그인사용자_답변한사람_같은경우_deleted상태(){
        A1.delete(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제후 deleteHistory를 반환한다.")
    void 삭제성공_로그인사용자_답변한사람_같은경우_deleteHistory반환(){
        assertThat(A1.delete(UserTest.JAVAJIGI)).isEqualTo(
                new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI)
        );
    }

    @Test
    void 삭제실패_로그인사용자_답변한사람_다른경우(){
        assertThatThrownBy(()->{
            A1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}