package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DeleteHistoryTest {

  static Stream<Arguments> postEntities() {
    return Stream.of(
        Arguments.of(QuestionTest.Q1, ContentType.QUESTION, UserTest.JAVAJIGI),
        Arguments.of(AnswerTest.A1, ContentType.ANSWER, UserTest.JAVAJIGI)
    );
  }

  @DisplayName("정적 팩터리 메서드로 DeleteHistory를 생성할 수 있다.")
  @ParameterizedTest
  @MethodSource("postEntities")
  void create(PostEntity entity) {
    assertThat(DeleteHistory.of(entity)).isNotNull();
  }

  @DisplayName("정적 팩터리 메서드로 DeleteHistory를 생성한 객체의 값을 확인한다.")
  @ParameterizedTest
  @MethodSource("postEntities")
  void create_값(PostEntity entity, ContentType type, User writer) {
    DeleteHistory history = DeleteHistory.of(entity);

    assertAll(
        () -> assertThat(entity.getContentType()).isEqualTo(type),
        () -> assertThat(entity.isOwner(writer)).isTrue()
    );
  }
}
