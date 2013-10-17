
package genetic.algorithm;

public class nPointCrossoverRecombinator implements Recombinator {
  private int n;

  public nPointCrossoverRecombinator(int n) {
    this.n = n;
  }

  @Override
  public Individual[] recombine(Individual parent1, Individual parent2) {
    Individual[] offsprings = new Individual[2];
    Individual[] parents = {parent1.clone(), parent2.clone()};
    int[] breakPoints = initializeBreakPoints(parent1.countAlleles());
    int breakPointIndex = 0;
    int toggler = 0;
    for (int i = 0; i < parent1.countAlleles(); i++) {
      if (breakPointIndex != breakPoints.length && i == breakPoints[breakPointIndex]) {
        toggler = (toggler + 1) % 2;
        breakPointIndex++;
      }
      offsprings[0].setAllele(i, parents[toggler].getAllele(i).getData());
      offsprings[1].setAllele(i, parents[(toggler + 1) % 2].getAllele(i).getData());
    }

    return offsprings;
  }

  private int[] initializeBreakPoints(int size) {
    int[] breakPoints = new int[n];
    for (int i = 0; i < breakPoints.length; i++) {
      breakPoints[i] = (int) (Math.random() * size);
      boolean sorted = false;
      for (int j = i; j > 0 && !sorted; j--) {
        if (breakPoints[j] > breakPoints[j - 1]) {
          sorted = true;
          continue;
        }
        int temp = breakPoints[j];
        breakPoints[j] = breakPoints[j - 1];
        breakPoints[j - 1] = temp;
      }
    }
    return breakPoints;
  }
}
