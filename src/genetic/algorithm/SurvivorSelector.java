
package genetic.algorithm;

public interface SurvivorSelector {
  public Individual[] select(Individual[] population, double survivalRate);
}
