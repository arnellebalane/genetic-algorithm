
package genetic.algorithm;

public class SudokuAllele implements Allele {
  private int data;
  private boolean changeable;

  public SudokuAllele(int data, boolean changeable) {
    this.data = data;
    this.changeable = changeable;
  }

  @Override
  public void setData(int data) {
    this.data = (changeable) ? data : this.data;
  }

  @Override
  public int getData() {
    return data;
  }

  @Override
  public String toString() {
    return "" + data;
  }
}
