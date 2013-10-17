
package genetic.algorithm;

public class GeneticAlgorithmTester {
  public static void main(String[] args) {
    GeneticAlgorithm sudoku = new GeneticAlgorithm();
    sudoku.setPuzzleParser(new SudokuPuzzleParser());
    sudoku.setSurvivorSelector(new ElitismSurvivorSelector());
    sudoku.setParentSelector(new TournamentParentSelector(3));
    sudoku.setRecombinator(new nPointCrossoverRecombinator(2));
  }
}
