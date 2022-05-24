package qna.domain.question;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.answer.AnswerTest;
import qna.domain.deleteHistory.DeleteHistoriesTest;
import qna.domain.question.Question;
import qna.domain.user.User;
import qna.domain.user.UserTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        @Nested
        class 다른사람의_삭제요청이_주어질경우 {

            @Test
            void CannotDeleteException을_던진다() {
                Question question = createQuestion(UserTest.JAVAJIGI);

                assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                        .isInstanceOf(CannotDeleteException.class)
                        .hasMessage("질문을 삭제할 권한이 없습니다.");
            }

        }

        @Nested
        class 질문만_존재할경우 {

            @Test
            void 삭제처리후_삭제기록을_리턴한다() throws CannotDeleteException {
                Question question = createQuestion(UserTest.JAVAJIGI);

                DeleteHistories deleteHistories = question.delete(UserTest.JAVAJIGI);

                assertThat(question.isDeleted()).isTrue();
                assertThat(deleteHistories).isEqualTo(DeleteHistoriesTest.ONLY_QUESTION);
            }

        }

        @Nested
        class 다른사람이_쓴_답변이_존재할경우 {

            @Test
            void CannotDeleteException을_던진다() {
                Question question = createQuestion(UserTest.JAVAJIGI);
                question.addAnswer(AnswerTest.createAnswer(UserTest.SANJIGI, question));

                assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                        .isInstanceOf(CannotDeleteException.class)
                        .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }

        }

        @Nested
        class 내가쓴_답변만_존재할경우 {

            @Test
            void 삭제처리후_삭제기록을_리턴한다() throws CannotDeleteException {
                Question question = createQuestion(UserTest.JAVAJIGI);
                question.addAnswer(AnswerTest.createAnswer(UserTest.JAVAJIGI, Q1));

                DeleteHistories deleteHistories = question.delete(UserTest.JAVAJIGI);

                assertThat(question.isDeleted()).isTrue();
                assertThat(deleteHistories).isEqualTo(DeleteHistoriesTest.SAME_USER);
            }

        }

    }

}
