
package genetic.algorithm;

public class SudokuIndividual implements Individual {
  private int puzzleDimension;
  private int[] boardScheme;
  private Allele[][] alleles;
  private int fitness;

  public SudokuIndividual(int puzzleDimension) {
    this.puzzleDimension = puzzleDimension;
    fitness = 1;
    alleles = new Allele[puzzleDimension][puzzleDimension];
    boardScheme = new int[2];
    if (puzzleDimension == 4) {
      boardScheme[0] = 2;
      boardScheme[1] = 2;
    } else if (puzzleDimension == 6) {
      boardScheme[0] = 3;
      boardScheme[1] = 2;
    } else if (puzzleDimension == 8) {
      boardScheme[0] = 4;
      boardScheme[1] = 2;
    } else if (puzzleDimension == 9) {
      boardScheme[0] = 3;
      boardScheme[1] = 3;
    }
  }

  @Override
  public void addAllele(int index, int data) {
    alleles[(int) (index / puzzleDimension)][index % puzzleDimension] = new SudokuAllele(data, data == 0);
  }

  @Override
  public void setAllele(int index, int data) {
    alleles[(int) (index / puzzleDimension)][index % puzzleDimension].setData(data);
  }

  @Override
  public Allele getAllele(int index) {
    return alleles[(int) (index / puzzleDimension)][index % puzzleDimension];
  }

  @Override
  public int countAlleles() {
    return puzzleDimension * puzzleDimension;
  }

  @Override
  public int getFitness() {
    if (fitness == 1) {
      fitness = 0;
      for (int i = 0; i < puzzleDimension; i++) {
        fitness += evaluateFitness(getRow(i)) + evaluateFitness(getColumn(i)) + evaluateFitness(getBlock(i));
      }
    }
    return fitness;
  }

  @Override
  public Individual clone() {
    Individual clone = new SudokuIndividual(puzzleDimension);
    for (int i = 0; i < puzzleDimension * puzzleDimension; i++) {
      clone.addAllele(i, alleles[(int) (i / puzzleDimension)][i % puzzleDimension].getData());
    }
    return clone;
  }

  @Override
  public void randomize() {
    for (int i = 0; i < puzzleDimension * puzzleDimension; i++) {
      alleles[(int) (i / puzzleDimension)][i % puzzleDimension].setData((int) (Math.random() * puzzleDimension + 1));
    }
  }

  @Override
  public boolean solved() {
    return getFitness() == 0;
  }

  private Allele[] getRow(int index) {
    return alleles[index];
  }

  private Allele[] getColumn(int index) {
    Allele[] column = new Allele[puzzleDimension];
    for (int i = 0; i < column.length; i++) {
      column[i] = alleles[i][index];
    }
    return column;
  }

  private Allele[] getBlock(int index) {
    Allele[] block = new Allele[puzzleDimension];
    int blockIndex = 0;
    for (int i = index * boardScheme[0] % puzzleDimension, a = 0; a < boardScheme[0]; a++) {
      for (int j = ((int) (index / boardScheme[1])) * boardScheme[1], b = 0; b < boardScheme[1]; b++) {
        block[blockIndex++] = alleles[j + b][i + a];
      }
    }
    return block;
  }

  private int evaluateFitness(Allele[] data) {
    int missingAlleles = 0;
    for (int i = 1; i <= data.length; i++) {
      boolean present = false;
      for (int j = 0; j < data.length && !present; j++) {
        if (data[j].getData() == i) {
          present = true;
        }
      }
      if (!present) {
        missingAlleles++;
      }
    }
    return missingAlleles;
  }
}
