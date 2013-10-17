
package genetic.algorithm;

public class RandomResettingMutator implements Mutator {

  @Override
  public Individual mutate(Individual individual) {
    double mutationProbability = 0.01;
    for (int i = 0; i < individual.countAlleles(); i++) {
      double probabilityOfMutation = Math.random();
      if (probabilityOfMutation <= mutationProbability) {
        individual.setAllele(i, individual.randomAllele().getData());
      }
    }
    return individual;
  }
}
