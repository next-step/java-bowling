package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private final Question question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

    @Test
    void 삭제_전_테스트() {
        assertThat(question1.isDeleted()).isFalse();
    }

    @Test
    void 로그인_유저_질문자_다름_유효성_테스트() {
        assertThatThrownBy(() -> question1.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문자_답변자_다름_유효성_테스트() {
        question1.addAnswer(new Answer(UserTest.SANJIGI, question1, "content2"));
        assertThatThrownBy(() -> question1.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 삭제_테스트() throws CannotDeleteException {
        question1.delete(UserTest.JAVAJIGI);
        assertThat(question1.isDeleted()).isTrue();
    }

}
