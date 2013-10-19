
package genetic.algorithm;

public interface Individual {
  public void addAllele(int index, int data);
  public void setAllele(int index, int data);
  public Allele getAllele(int index);
  public Allele randomAllele();
  public int countAlleles();
  public void evaluateFitness();
  public int getFitness();
  public Individual clone();
  public void randomize();
  public boolean solved();
}
