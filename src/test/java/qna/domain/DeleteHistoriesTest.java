package qna.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteHistoriesTest {

	@Test
	void deleteHistories(){
		Assertions.assertThat(DeleteHsitory.of())
			.isEqualTo(new DelteHistories(new DeleteHistory()));
	}

}
