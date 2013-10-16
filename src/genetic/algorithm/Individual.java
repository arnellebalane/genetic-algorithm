
package genetic.algorithm;

public interface Individual {
  public void addChromosome(int index, int value);
  public void setChromosome(int index, int value);
  public Chromosome getChromosome(int index);
  public int getFitness();
  public Individual clone();
  public void randomize();
  public boolean solved();
}
