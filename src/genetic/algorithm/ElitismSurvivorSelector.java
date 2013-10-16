
package genetic.algorithm;

public class ElitismSurvivorSelector implements SurvivorSelector {

  @Override
  public Individual[] select(Individual[] population, double survivalRate) {
    Individual[] survivors = new Individual[(int) (population.length * survivalRate)];
    population = rank(population);
    System.arraycopy(population, 0, survivors, 0, survivors.length);
    return survivors;
  }

  private Individual[] rank(Individual[] population) {
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      for (int i = 0; i < population.length - 1; i++) {
        if (population[i].getFitness() < population[i].getFitness()) {
          sorted = false;
          Individual temp = population[i];
          population[i] = population[i + 1];
          population[i + 1] = temp;
        }
      }
    }
    return population;
  }
}
