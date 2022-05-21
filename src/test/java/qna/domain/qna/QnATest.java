package qna.domain.qna;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QnATest {

    @Nested
    class isDeleted_메서드는 {

        @Nested
        class 삭제된경우 {

            @Test
            void true를_리턴한다() {
                QnA qna = new QnA(true);

                boolean actual = qna.isDeleted();

                assertThat(actual).isTrue();
            }

        }

        @Nested
        class 삭제되지_않은경우 {

            @Test
            void false를_리턴한다() {
                QnA qna = new QnA(false);

                boolean actual = qna.isDeleted();

                assertThat(actual).isFalse();
            }

        }
    }
}
