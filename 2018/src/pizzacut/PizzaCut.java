package pizzacut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PizzaCut {

    private static Params params;

    private static int nCuts;

    private static int[][] result;

    public static void run(final File f) {
        parseParams(f);

        result = new int[params.C][params.R];

        cutGrid();

        //writeResult();


    }

    //recursively explore grid, evaluating cut that ends in current rightmost bottom cell,
    // greedily increasing cut size by moving down, right and by diagonal
    //resize prev seen cut to increase global gain
    private static void cutGrid() {

        int tCount = isTomato(0, 0) ? 1 : 0;
        Cell currCell = new Cell(0, 0, tCount, tCount ^= 1);

        if (isMaxPossibleSlice(currCell)){
            ++nCuts;
            //mark result grid
            //reset curr cell, always try to move down first, if not possible then right
        }
    }

    private static boolean isMaxPossibleSlice(final Cell cell) {
        return (cell.r + 1) * (cell.c + 1) == params.H;
    }

    private static boolean isValidSlice(final Cell cell) {
        return cell.nT >= params.L && cell.nM >= params.L && (cell.r + 1) * (cell.c + 1) <= params.H;
    }

    private static boolean isTomato(int r, int c) {
        return params.grid[r][c] == 'T';
    }

    private static void parseParams(final File f) {
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String line;

            int R = 0;
            int C = 0;
            int L = 0;
            int H = 0;

            char[][] grid = null;

            boolean firstLine = true;

            int rptr = 0;

            while ((line = br.readLine()) != null)  {
                if (firstLine){
                    String[] parts = line.split("\\s+");
                    if (parts.length != 4){
                        throw new RuntimeException("invalid file format");
                    }
                    R = Integer.parseInt(parts[0]);
                    C = Integer.parseInt(parts[1]);
                    L = Integer.parseInt(parts[2]);
                    H = Integer.parseInt(parts[3]);
                    grid = new char[R][C];

                    firstLine = false;
                } else {
                    for (int cptr = 0; cptr < line.length(); ++cptr) {
                        grid[rptr][cptr] = line.charAt(cptr);
                    }
                    ++rptr;
                }
            }
            params = new Params(R, C, L, H, grid);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class Cell{
        int r;
        int c;
        int nT;
        int nM;

        public Cell(final int r, final int c, final int nT, final int nM) {
            this.r = r;
            this.c = c;
            this.nT = nT;
            this.nM = nM;
        }
    }

    static class Params {
        final int R;
        final int C;
        final int L;
        final int H;

        final char[][] grid;

        public Params(final int R, final int C, final int L, final int H, final char[][] grid) {
            this.R = R;
            this.C = C;
            this.L = L;
            this.H = H;
            this.grid = grid;
        }
    }
}
