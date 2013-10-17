
package genetic.algorithm;

public interface Recombinator {
  public Individual[] recombine(Individual parent1, Individual parent2);
}
