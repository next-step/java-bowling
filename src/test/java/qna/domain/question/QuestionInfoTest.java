package qna.domain.question;

import org.junit.jupiter.api.Test;
import qna.domain.user.User;
import qna.domain.user.UserTest;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionInfoTest {

    @Test
    void equalsWriter() {
        QuestionInfo questionInfo = new QuestionInfo(UserTest.JAVAJIGI);

        boolean actual = questionInfo.equalsWriter(UserTest.JAVAJIGI);

        assertThat(actual).isTrue();
    }

    @Test
    void equalsWriterWithNotMatchUser() {
        QuestionInfo questionInfo = new QuestionInfo("title", "contents");
        questionInfo.writeBy(UserTest.JAVAJIGI);

        boolean actual = questionInfo.equalsWriter(UserTest.SANJIGI);

        assertThat(actual).isFalse();
    }

    @Test
    void writer_and_writeBy() {
        QuestionInfo questionInfo = new QuestionInfo("title", "contents");
        questionInfo.writeBy(UserTest.JAVAJIGI);

        User writer = questionInfo.writer();

        assertThat(writer).isEqualTo(UserTest.JAVAJIGI);
    }
}
