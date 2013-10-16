
package genetic.algorithm;

import java.io.BufferedReader;
import java.io.FileReader;

public class SudokuPuzzleParser implements PuzzleParser {

  @Override
  public Individual parse(String path) {
    Individual puzzle = null;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(path));
      String line = reader.readLine();
      int puzzleDimension = Integer.parseInt(line);
      puzzle = new SudokuIndividual(puzzleDimension);
      int index = 0;
      line = reader.readLine();
      while (line != null) {
        if (line.trim().length() > 0) {
          String[] row = line.split("");
          for (int i = 0; i < row.length; i++) {
            if (row[i].trim().length() > 0) {
              int data = Integer.parseInt(row[i]);
              puzzle.addAllele(index++, data);
            }
          }
        }
        line = reader.readLine();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return puzzle;
  }
}
