
package genetic.algorithm;

public interface Mutator {
  public Individual[] mutate(Individual[] population, double mutationProbability);
}
