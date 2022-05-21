package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName(value = "답변 테스트")
class AnswerTest {

    public Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    void 동등성_테스트() {
        assertThat(answer).isEqualTo(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
    }

    @Test
    void 답변자와_로그인_유저가_같은_경우_삭제() throws CannotDeleteException {
        answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void 로그인_유저가_널이면_예외() {
        assertThatNullPointerException().isThrownBy(
            () -> answer.delete(null)
        );

    }

    @Test
    void 답변자와_로그인_유저가_다른_경우_예외() {
        assertThatThrownBy(
            () -> answer.delete(UserTest.SANJIGI)
        ).isExactlyInstanceOf(CannotDeleteException.class);
    }
}
