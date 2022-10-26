package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 글쓴이_정보_없음_오류_검증() {
        assertThatThrownBy(() -> new Answer(null, null, null))
                .isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void 질문_정보_없음_오류_검증() {
        assertThatThrownBy(() -> new Answer(JAVAJIGI, null, null))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void 댓글_삭제_타인_답변_여부_검증() {
        assertThatThrownBy(() -> A1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 댓글_삭제_검증() throws CannotDeleteException {
        Answer answer = getSampleAnswer();

        DeleteHistory result = answer.delete(UserTest.JAVAJIGI);

        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, answer.getId(), JAVAJIGI, null);

        assertAll(
                () -> assertThat(result).isEqualTo(expected),
                () -> assertThat(answer.isDeleted()).isTrue()
        );
    }

    private Answer getSampleAnswer() {
        return new Answer(JAVAJIGI, QuestionTest.Q1, "content sample");
    }
}
