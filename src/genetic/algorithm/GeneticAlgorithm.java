
package genetic.algorithm;

public class GeneticAlgorithm {
  private int populationSize;
  private int maxGenerations;
  private double survivalRate;
  private double crossoverProbability;
  private double mutationProbability;
  private Individual[] population;

  private PuzzleParser puzzleParser;
  private SurvivorSelector survivorSelector;
  private ParentSelector parentSelector;
  private Recombinator recombinator;
  private Mutator mutator;

  public GeneticAlgorithm() {
    populationSize = 10;
    maxGenerations = 500000;
    survivalRate = 0.1;
    crossoverProbability = 0.2;
    mutationProbability = 0.2;
  }

  public GeneticAlgorithm(int populationSize, int maxGenerations, double survivalRate, double crossoverProbability, double mutationProbability) {
    this.populationSize = populationSize;
    this.maxGenerations = maxGenerations;
    this.survivalRate = survivalRate;
    this.crossoverProbability = crossoverProbability;
    this.mutationProbability = mutationProbability;
  }

  public void setPuzzleParser(PuzzleParser puzzleParser) {
    this.puzzleParser = puzzleParser;
  }

  public void setSurvivorSelector(SurvivorSelector survivorSelector) {
    this.survivorSelector = survivorSelector;
  }

  public void setParentSelector(ParentSelector parentSelector) {
    this.parentSelector = parentSelector;
  }

  public void setRecombinator(Recombinator recombinator) {
    this.recombinator = recombinator;
  }

  public void setMutator(Mutator mutator) {
    this.mutator = mutator;
  }

  public Individual solve(String puzzlePath) {
    Individual puzzle = puzzleParser.parse(puzzlePath);
    population = initializePopulation(puzzle);

    int generation = 0;
    while (generation++ < maxGenerations && !solutionFound(population)) {
      Individual[] survivors = survivorSelector.select(population, survivalRate);
      Individual[] parents = parentSelector.select(population);
      Individual[] offsprings = recombine(parents, population.length - survivors.length);
      System.arraycopy(survivors, 0, population, 0, survivors.length);
      System.arraycopy(offsprings, 0, population, survivors.length, offsprings.length);
      population = mutate(population);
    }
    population = rankPopulation(population);
    return population[0];
  }

  private Individual[] initializePopulation(Individual original) {
    Individual[] individuals = new Individual[populationSize];
    for (int i = 0; i < individuals.length; i++) {
      individuals[i] = original.clone();
      individuals[i].randomize();
    }
    return individuals;
  }

  private Individual[] rankPopulation(Individual[] individuals) {
    boolean sorted = false;
    while (!sorted) {
      sorted = true;
      for (int i = 0; i < individuals.length - 1; i++) {
        if (individuals[i].getFitness() < individuals[i].getFitness()) {
          sorted = false;
          Individual temp = individuals[i];
          individuals[i] = individuals[i + 1];
          individuals[i + 1] = temp;
        }
      }
    }
    return individuals;
  }

  private Individual[] recombine(Individual[] parents, int offspingCount) {
    Individual[] offsprings = new Individual[offspingCount];
    for (int i = 0; i < offsprings.length; i += 2) {
      double probabilityOfCrossover = Math.random();
      Individual parent1 = parents[(int) (Math.random() * parents.length)];
      Individual parent2 = parents[(int) (Math.random() * parents.length)];
      Individual[] children = null;
      if (probabilityOfCrossover <= crossoverProbability) {
        children = recombinator.recombine(parent1, parent2);
      } else {
        children[0] = parent1;
        children[1] = parent2;
      }
      offsprings[i] = children[0];
      if (i < offsprings.length - 1) {
        offsprings[i + 1] = children[1];
      }
    }
    return offsprings;
  }

  private Individual[] mutate(Individual[] population) {
    for (int i = 0; i < population.length; i++) {
      double probabilityOfMutation = Math.random();
      if (probabilityOfMutation <= mutationProbability) {
        population[i] = mutator.mutate(population[i]);
      }
    }
    return population;
  }

  private boolean solutionFound(Individual[] individuals) {
    for (int i = 0; i < individuals.length; i++) {
      if (individuals[i].solved()) {
        return true;
      }
    }
    return false;
  }
}
