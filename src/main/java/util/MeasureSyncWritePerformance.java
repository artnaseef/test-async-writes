package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MeasureSyncWritePerformance {
    public static void  main (String[] args) {
        MeasureSyncWritePerformance mainObj = new MeasureSyncWritePerformance();

        mainObj.instanceMain(args);
    }

    public void instanceMain (String[] args) {
        try {
            if ( args.length < 1 ) {
                System.err.println("Usage: MeasureSyncWritePerformance <temp-directory> [num-iteration]");
                System.exit(1);
            }

            int numIteration = 1000;
            if ( args.length > 1 ) {
                numIteration = Integer.parseInt(args[1]);
            }

            this.testWithDirectory(args[0], numIteration);
        } catch ( Exception exc ) {
            exc.printStackTrace();
        }
    }

    public void testWithDirectory (String path, int numIteration) throws IOException {
        File dir = new File(path);

        dir.mkdir();

        this.testWithFile(new File(dir, "testSync.dat"), numIteration);
    }

    public void testWithFile (File testFile, int numIteration) throws IOException {
        FileOutputStream output = new FileOutputStream(testFile);

        int iter;

        long startTime = System.nanoTime();

        iter = 0;
        while ( iter < numIteration ) {
            output.write("small write".getBytes());
            output.flush();
            output.getFD().sync();

            iter++;
        }

        long endTime = System.nanoTime();
        long elapsed = endTime - startTime;
        ElapsedTimeFormatter elapsedTimeFormatter = new ElapsedTimeFormatter(elapsed);

        System.out.println(Integer.toString(numIteration) + " synchronous iterations took " +
                elapsedTimeFormatter.formatSeconds(4) + "s");
    }
}
