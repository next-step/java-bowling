package qna.domain.answer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import qna.domain.ContentType;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.question.Question;
import qna.domain.question.QuestionTest;
import qna.domain.user.User;
import qna.domain.user.UserTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    public static Answer createAnswer(User user, Question question) {
        return new Answer(
                user, question, "contents"
        );
    }

    @Nested
    class delete_메서드는 {

        @Test
        void 삭제처리후_삭제기록을_리턴한다() {
            Answer answer = createAnswer(UserTest.JAVAJIGI, QuestionTest.Q1);

            DeleteHistory deleteHistory = answer.delete();

            assertThat(answer.isDeleted()).isTrue();
            assertThat(deleteHistory).isEqualTo(
                    new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now())
            );
        }
    }
}
