package qna.domain.answer;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import qna.domain.ContentType;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.question.Question;
import qna.domain.user.User;
import qna.domain.user.UserTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    public static Answers createAnswers(User user) {
        return new Answers(List.of(
                AnswerTest.createAnswer(user, new Question())
        ));
    }

    @Nested
    class hasWrittenByOthers_메서드는 {

        @Nested
        class 다른사람이_쓴_답변이_주어질경우 {

            @Test
            void true를_리턴한다() {
                Answers answers = createAnswers(UserTest.SANJIGI);

                boolean actual = answers.hasWrittenByOthers(UserTest.JAVAJIGI);

                assertThat(actual).isTrue();
            }

        }

        @Nested
        class 내가쓴_답변이_주어질경우 {

            @Test
            void false를_리턴한다() {
                Answers answers = createAnswers(UserTest.JAVAJIGI);

                boolean actual = answers.hasWrittenByOthers(UserTest.JAVAJIGI);

                assertThat(actual).isFalse();
            }

        }
    }

    @Nested
    class delete_메서드는 {

        @Test
        void 삭제기록을_리턴한다() {
            Answers answers = createAnswers(UserTest.JAVAJIGI);

            List<DeleteHistory> delete = answers.delete();

            assertThat(delete).containsExactly(
                    new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now())
            );
        }
    }
}
