package qna.domain;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 삭제_권한_테스트() throws CannotDeleteException {
        A1.hasDeleteAuthority(UserTest.JAVAJIGI);

        assertThatThrownBy(() -> {
            A1.hasDeleteAuthority(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 삭제_테스트() {
        assertThat(A1.delete()).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
        assertThat(A1.isDeleted()).isTrue();
    }
}
