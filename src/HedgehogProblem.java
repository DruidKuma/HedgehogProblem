import model.AppleGarden;
import model.AppleTree;
import util.BestPathAlgorithm;
import util.IOUtil;

import java.io.IOException;

/**
 * Created by Iurii Miedviediev
 *
 * Imagine the rectangular garden sized M x N. Garden consists of square zones with one apple-tree in each zone.
 * There can be several apples under each tree.
 * There is an hedgehog in upper left square of the garden. It moves to lower right corner with some restrictions:
 * - the hedgehog  can  only  move to the next right or to the next lower square.
 *
 * Please, make an application to count the max amount of apples the hedgehog can collect on its way.
 *
 *
 * Tech Conditions:
 *
 * - The garden plan is given as a table "apples" witch consists of M rows and N columns.
 * - Table element apples[i,j] indicates a number of apples under the tree with coordinates i,j.
 * - Text file "input.txt" structured in this way:
 *      * first row consist of  numbers M and N separated with space
 *      * each of the next M rows consists of N values of apples[i,j] separated with space
 * - File "output.txt" must contain one natural number (result of calculations).
 *
 * @author DruidKuma
 * @version 1.0.0
 * @since 3/28/17
 */
public class HedgehogProblem {

    public static void main(String[] args) throws IOException {

        // read input data and init dimensions
        int[][] values = IOUtil.readAppleTreeData();
        int rows = values.length;
        int columns = values[0].length;


        // initialize Apple Tree matrix
        AppleTree[][] appleTrees = new AppleTree[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                appleTrees[i][j] = new AppleTree(String.format("%d-%d", i, j), values[i][j]);
            }
        }

        // initialize Apple Garden
        AppleGarden appleGarden = new AppleGarden(appleTrees);
        appleGarden.init();

        // perform calculations from the top left corner of the garden
        BestPathAlgorithm dijkstra = new BestPathAlgorithm(appleGarden);
        dijkstra.execute(appleTrees[0][0]);

        // get value for the bottom right corner of the garden
        Integer maxValue = dijkstra.getPath(appleTrees[rows - 1][columns - 1]);

        // write result to the file
        IOUtil.writeResultToFile(maxValue);
    }

}
