package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName(value = "질문 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(11L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    void 게시글_삭제_시_로그인_유저가_널이면_예외() {
        assertThatNullPointerException().isThrownBy(
            () -> question.delete(null)
        );
    }

    @Test
    void 답변이_없는_본인_게시글_삭제() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertAll(
            () -> assertThat(question.isDeleted()).isTrue(),
            () -> assertThat(deleteHistories).hasSize(1)
        );

    }

    @Test
    void 본인_답변만_있는_본인_게시글_삭제() throws CannotDeleteException {
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);
        assertAll(
            () -> assertThat(question.isDeleted()).isTrue(),
            () -> assertThat(deleteHistories).hasSize(2)
        );
    }

    @Test
    void 다른_사람의_답변이_있는_본인게시글_삭제할_때_예외() {
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "Answers Contents2"));
        assertThatThrownBy(
            () -> question.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
