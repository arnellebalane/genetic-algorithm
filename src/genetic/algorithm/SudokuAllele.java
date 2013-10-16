
package genetic.algorithm;

public class SudokuAllele implements Allele {
  private int value;
  private boolean changeable;

  public SudokuAllele(int value, boolean changeable) {
    this.value = value;
    this.changeable = changeable;
  }

  @Override
  public void setData(int value) {
    this.value = (changeable) ? value : this.value;
  }

  @Override
  public int getData() {
    return value;
  }
}
