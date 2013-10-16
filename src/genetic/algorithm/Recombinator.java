
package genetic.algorithm;

public interface Recombinator {
  public Individual[] recombine(Individual[] parents, double crossoverProbability, int offspringCount);
}
