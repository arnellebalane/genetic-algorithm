
package genetic.algorithm;

public class GeneticAlgorithmTester {
  public static void main(String[] args) {
    GeneticAlgorithm sudoku = new GeneticAlgorithm();
    sudoku.setPuzzleParser(new SudokuPuzzleParser());
    sudoku.setSurvivorSelector(new ElitismSurvivorSelector());
    sudoku.setParentSelector(new TournamentParentSelector(3));
    sudoku.setRecombinator(new nPointCrossoverRecombinator(3));
    sudoku.setMutator(new RandomResettingMutator());

    Individual solution = sudoku.solve("inputs/test.in");
    System.out.println(solution);
  }
}
