package qna.domain.question;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import qna.domain.ContentType;
import qna.domain.answer.AnswerTest;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.user.User;
import qna.domain.user.UserTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    public static Question createQuestion(User user) {
        return new Question(
                "title1", "contents1"
        ).writeBy(user);
    }

    @Nested
    class delete_메서드는 {

        @Test
        void 삭제처리후_삭제기록을_리턴한다() {
            DeleteHistories deleteHistories = Q1.delete();

            assertThat(Q1.isDeleted()).isTrue();
            assertThat(deleteHistories).isEqualTo(new DeleteHistories(
                    new DeleteHistory(ContentType.QUESTION, null, UserTest.JAVAJIGI, LocalDateTime.now())
            ));
        }

    }

    @Nested
    class answerHasWrittenByOthers_메서드는 {

        @Nested
        class 내가쓴_답변만_존재할경우 {

            @Test
            void false를_리턴한다() {
                Question question = createQuestion(UserTest.JAVAJIGI);
                question.addAnswer(AnswerTest.createAnswer(UserTest.JAVAJIGI, Q1));

                boolean actual = question.answerHasWrittenByOthers(UserTest.JAVAJIGI);

                assertThat(actual).isFalse();
            }

        }

        @Nested
        class 다른사람이_쓴_답변이_존재할경우 {

            @Test
            void true를_리턴한다() {
                Question question = createQuestion(UserTest.JAVAJIGI);
                question.addAnswer(AnswerTest.createAnswer(UserTest.SANJIGI, question));

                boolean actual = question.answerHasWrittenByOthers(UserTest.JAVAJIGI);

                assertThat(actual).isTrue();
            }

        }

    }
}
