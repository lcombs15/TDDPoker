package test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import main.Main;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private static String rootPath = Paths.get("").toAbsolutePath().toString() + "/src/test/files/";
    private static File outputFile = new File(rootPath + "temp/out.txt");

    @BeforeEach
    public void setup() throws IOException{
        removeOutputFile();
    }

    @Test
    public void givenFileWithSingleGame_whenStraightVSFlush_thenOutputFileIsAsExpected() throws FileNotFoundException, IOException {
        Main SUT = new Main();

        String inputLocation = rootPath + "input/single_record_straight_vs_flush.txt";

        SUT.rankHandsInFile(inputLocation, outputFile.getPath());

        String expected = "", result = "";

        Scanner expectedScanner = new Scanner(new File(rootPath + "output/single_record_straight_vs_flush.txt"));
        while(expectedScanner.hasNextLine()){
            expected += expectedScanner.nextLine();
        }

        Scanner resultScanner = new Scanner(outputFile);
        while(resultScanner.hasNextLine()){
            result += resultScanner.nextLine();
        }

        assertEquals(expected,result);
    }

    @AfterAll
    public static void cleanup(){
        removeOutputFile();
    }

    private static void removeOutputFile() {
        if(outputFile.exists()){
            outputFile.delete();
        }
    }
}