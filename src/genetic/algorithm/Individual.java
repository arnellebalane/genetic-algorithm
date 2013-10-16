
package genetic.algorithm;

public interface Individual {
  public void addAllele(int index, int data);
  public void setAllele(int index, int data);
  public Allele getAllele(int index);
  public int getFitness();
  public Individual clone();
  public void randomize();
  public boolean solved();
}
