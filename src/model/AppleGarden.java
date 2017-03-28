package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Iurii Miedviediev
 *
 * Represents Apple Garden as matrix of zones (Apple Trees)
 * and all possible steps which can be made between zones
 *
 * @author DruidKuma
 * @version 1.0.0
 * @since 3/28/17
 */
public class AppleGarden {

    private final AppleTree[][] appleTreeMatrix;
    private List<Step> steps = new ArrayList<>();

    public AppleGarden(AppleTree[][] appleTrees) {
        this.appleTreeMatrix = appleTrees;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void init() {
        int rows = appleTreeMatrix.length;
        int columns = appleTreeMatrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                // if we can step right
                if(i < rows - 1) steps.add(new Step(appleTreeMatrix[i][j], appleTreeMatrix[i+1][j]));

                // if we can step down
                if(j < columns - 1) steps.add(new Step(appleTreeMatrix[i][j], appleTreeMatrix[i][j+1]));
            }
        }
    }
}