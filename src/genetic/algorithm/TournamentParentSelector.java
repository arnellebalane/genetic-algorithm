
package genetic.algorithm;

public class TournamentParentSelector implements ParentSelector {
  private int k;

  public TournamentParentSelector(int k) {
    this.k = k;
  }

  @Override
  public Individual[] select(Individual[] population) {
    Individual[] parents = new Individual[population.length];
    for (int i = 0; i < parents.length; i++) {
      Individual[] kGroup = new Individual[k];
      for (int j = 0; j < kGroup.length; j++) {
        kGroup[j] = population[(int) (Math.random() * population.length)];
      }
      kGroup = rank(kGroup);
      parents[i] = kGroup[0];
    }
    return parents;
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
