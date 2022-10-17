package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void 로그인_사용자와_질문자_다른_경우_삭제_불가능() {

        final User 다른사용자 = new User(1L, "codeleesh", "password", "lsh", "codeleesh@gmail.com");

        assertThatThrownBy(() -> Q1.delete(다른사용자))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 로그인_사용자와_질문자_같은_경우_삭제_가능() throws CannotDeleteException {

        final User 질문자 = new User(1L, "codeleesh", "password", "lsh", "codeleesh@gmail.com");
        final Question 질문 = new Question("title1", "contents1").writeBy(질문자);

        질문.delete(질문자);

        assertThat(질문.isDeleted()).isTrue();
    }

    @Test
    void 답변이_없는_질문은_삭제_가능() throws CannotDeleteException {

        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 질문자와_답변자가_다르면_삭제_불가능() {

        final User 질문자 = new User(1L, "codeleesh", "password", "lsh", "codeleesh@gmail.com");
        final Question 질문 = new Question("title1", "contents1").writeBy(질문자);
        final User 답변자 = new User(2L, "pongdang", "password", "pd", "pongdang@gmail.com");
        final Answer 답변 = new Answer(답변자, 질문, "Answers Contents1");
        질문.addAnswer(답변);

        assertThatThrownBy(() -> 질문.delete(질문자))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 질문자와_답변자가_같으면_삭제_가능() throws CannotDeleteException {

        final User 질문자 = new User(1L, "codeleesh", "password", "lsh", "codeleesh@gmail.com");
        final Question 질문 = new Question("title1", "contents1").writeBy(질문자);
        final Answer 답변 = new Answer(질문자, 질문, "Answers Contents1");
        질문.addAnswer(답변);

        질문.delete(질문자);

        assertAll(
                () -> assertThat(질문.isDeleted()).isTrue(),
                () -> assertThat(답변.isDeleted()).isTrue()
        );
    }
}
