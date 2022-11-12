package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

@DisplayName("Answers 테스트")
class AnswersTest {

    Answers answers;

    Answer sameWriterAnswer1;
    Answer sameWriterAnswer2;
    Answer otherWriterAnswer;

    @BeforeEach
    void setUp() {
        sameWriterAnswer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        sameWriterAnswer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        otherWriterAnswer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

        answers = new Answers();
        answers.add(sameWriterAnswer1);
        answers.add(sameWriterAnswer2);
    }

    @Test
    void 삭제시_로그인_유저가_널이면_예외를_던진다() {
        assertThatNullPointerException()
            .isThrownBy(
                () -> answers.delete(null)
            );
    }

    @Test
    void 로그인_유저가_본인이면_삭제를_성공한다() {
        assertThatNoException()
            .isThrownBy(
                () -> answers.delete(UserTest.JAVAJIGI)
            );
    }

    @Test
    void 삭제를_성공하면_모든_answer의_deleted_값은_True_이다() throws CannotDeleteException {
        answers.delete(UserTest.JAVAJIGI);

        assertThat(sameWriterAnswer1.isDeleted()).isTrue();
        assertThat(sameWriterAnswer2.isDeleted()).isTrue();
    }

    @Test
    void null인_answer를_추가하면_예외를_던진다() {
        assertThatNullPointerException()
            .isThrownBy(
                () -> answers.add(null)
            );
    }

    @Test
    void answer_추가를_성공한다() {
        assertThatNoException()
            .isThrownBy(
                () -> answers.add(otherWriterAnswer)
            );
    }

    @Test
    void 삭제시_로그인_유저와_답변자가_다를_경우_예외를_던진다() {
        assertThatThrownBy(
            () -> answers.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
