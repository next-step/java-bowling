package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

@DisplayName("Question 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    Question question;

    @BeforeEach
    void setUp() {
        question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    void 삭제시_로그인_유저가_null이면_예외를_던진다() throws CannotDeleteException {
        assertThatNullPointerException()
            .isThrownBy(
                () -> question.delete(null)
            );
    }

    @Test
    void 삭제시_로그인_유저가_본인이면_성공한다() {
        assertThatNoException()
            .isThrownBy(
                () -> question.delete(UserTest.JAVAJIGI)
            );
    }

    @Test
    void 삭제시_로그인_유저가_본인이_아니면_예외를_던진다() {
        assertThatThrownBy(
            () -> question.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 본인이_작성한_답변이_있으면_삭제를_성공한다() {
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents"));

        assertThatNoException().isThrownBy(
            () -> question.delete(UserTest.JAVAJIGI)
        );
    }

    @Test
    void 타인이_작성한_답변이_있으면_예외를_던진다() {
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "Answers Contents"));

        assertThatThrownBy(
            () -> question.delete(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
