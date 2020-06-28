package bowling;

public class NumberOfKnockedDown {

  private Integer value;

  public NumberOfKnockedDown() {
  }

  public NumberOfKnockedDown(Integer value) {
    this.value = value;
  }

  public int getValue() {
    return value == null ? 0 : value;
  }

  public boolean isNull() {
    return value == null;
  }

  @Override
  public String toString() {
    return "NumberOfKnockedDown{" +
        "value=" + value +
        '}';
  }
}
