package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

class AnswerTest {

    Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(JAVAJIGI, QuestionTest.Q1, "Answers Contents1");;
    }

    @Test
    void 사용자와_답변자가_같은경우_삭제가능() throws CannotDeleteException {
        assertThat(answer.isDeleted()).isFalse();
        answer.delete(JAVAJIGI);
        Assertions.assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void 사용자와_답변자가_다른경우_삭제불가능() {
        Assertions.assertThatThrownBy(() -> answer.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 삭제시_삭제이력을_남김() throws CannotDeleteException {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), JAVAJIGI, LocalDateTime.now());
        Assertions.assertThat(answer.delete(JAVAJIGI)).isEqualTo(deleteHistory);
    }
}
