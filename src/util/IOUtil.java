package util;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by Iurii Miedviediev
 *
 * @author DruidKuma
 * @version 1.0.0
 * @since 3/28/17
 */
public class IOUtil {

    public static final String INPUT_DATA_FILE = "src/input.txt";
    public static final String OUTPUT_DATA_FILE = "src/output.txt";

    public static int[][] readAppleTreeData() throws IOException {
        RandomAccessFile file = new RandomAccessFile(INPUT_DATA_FILE, "r");

        // read dimensions
        String line = file.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line);

        if(tokenizer.countTokens() < 2) throw new RuntimeException("Incorrect format of the dimension line");

        int rows = Integer.parseInt(tokenizer.nextToken());
        int columns = Integer.parseInt(tokenizer.nextToken());


        // read values
        int[][] values = new int[rows][columns];
        int currentRow = 0;
        while((line = file.readLine()) != null) {
            tokenizer = new StringTokenizer(line);

            // check correct number of values in the line (column values)
            if(tokenizer.countTokens() != columns) throw new RuntimeException("Invalid number of values: " + line);

            int currentColumn = 0;
            while (tokenizer.hasMoreTokens()) {
                values[currentRow][currentColumn++] = Integer.parseInt(tokenizer.nextToken());
            }
            currentRow++;
        }

        // check correct number of rows in result matrix
        if(currentRow != rows) throw new RuntimeException("Invalid number of rows in the input file: " + currentRow);

        return values;
    }

    public static void writeResultToFile(Integer result) throws UnsupportedEncodingException, FileNotFoundException {
        PrintWriter writer = new PrintWriter(OUTPUT_DATA_FILE, "UTF-8");
        writer.println(result);
        writer.close();
    }
}
