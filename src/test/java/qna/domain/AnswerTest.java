package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static qna.domain.ContentType.*;
import static qna.domain.QuestionTest.*;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, Q1, "Answers Contents2");

    @Test
    void delete_다른_사람답변_삭제() {
        Answer answer = new Answer(JAVAJIGI, Q1, "Answers Contents1");

        assertThatThrownBy(() -> answer.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_정상_동작() throws CannotDeleteException {
        Answer answer = new Answer(JAVAJIGI, Q1, "Answers Contents1");

        assertThat(answer.isDeleted()).isFalse();
        DeleteHistory deleteHistory = answer.delete(JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistory).isEqualTo(DeleteHistory.of(ANSWER, answer.getId(), JAVAJIGI));
    }
}
