
package genetic.algorithm;

public class ElitismSurvivorSelector implements SurvivorSelector {

  @Override
  public Individual[] select(Individual[] population, double survivalRate) {
    Individual[] survivors = new Individual[(int) (population.length * survivalRate)];
    population = rank(population);
    System.arraycopy(population, 0, survivors, 0, survivors.length);
    return survivors;
  }

  private Individual[] rank(Individual[] individuals) {
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      for (int i = 0; i < individuals.length - 1; i++) {
        if (individuals[i].getFitness() < individuals[i + 1].getFitness()) {
          sorted = false;
          Individual temp = individuals[i];
          individuals[i] = individuals[i + 1];
          individuals[i + 1] = temp;
        }
      }
    }
    return individuals;
  }
}
