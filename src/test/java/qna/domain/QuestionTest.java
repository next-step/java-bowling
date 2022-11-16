package qna.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

    @AfterEach
    void tearDown() {
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    void validation_작성자_본인_여부_정상() {
        assertThatCode(() -> Q1.validateOwner(UserTest.JAVAJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    void validation_작성자_본인_여부_오류() {
        assertThatThrownBy(() -> Q1.validateOwner(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 게시물_삭제() {
        DeleteHistories deleteHistories = new DeleteHistories();
        Q1.delete(deleteHistories);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories.histories()).hasSize(1);
    }

    @Test
    void 게시물_및_댓글_삭졔_정상() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);

        DeleteHistories deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories.histories()).hasSize(2);
    }

    @Test
    void 게시물_및_댓글_삭졔_오류(){
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
