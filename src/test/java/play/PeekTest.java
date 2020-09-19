package play;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class PeekTest {

  @Test
  void peek() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> accs = new ArrayList<>();

    int[] acc = {0};
    numbers.stream()
        .peek(n -> acc[0] += n)
        .forEach(n -> accs.add(acc[0]));

    System.out.println(accs);
    assertThat(accs).isEqualTo(Arrays.asList(1, 3, 6, 10, 15));
  }
}
