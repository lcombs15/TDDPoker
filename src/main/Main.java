package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        if (args.length != 2) {
            System.out.println("Invalid # args: " + args.length + ".");
            System.out.println("Format: {path/to/input.txt} {path/to/output.txt}");
            return;
        }
        rankHandsInFile(args[0], args[1]);
    }

    /*
        Input file format:
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10
        Output file format:
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10 --> Left: Straight
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10 --> Left: High card - Ace of Hearts
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10 --> Left: Flush
     */
    public static void rankHandsInFile(String inputLocation, String outputLocation) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(new File(inputLocation));
        FileWriter output = new FileWriter(outputLocation);

        while (input.hasNext()) {
            String line = input.nextLine();

            output.write(line);

            Hand left, right;
            int split = line.indexOf("|");
            left = new Hand(line.substring(0, split - 1).trim());
            right = new Hand(line.substring(split + 1).trim());

            Hand winner = left.winningHandAgainst(right);
            output.write(" --> ");

            if (winner == null) {
                output.write("Tie");
            } else {
                output.write(winner == left ? "Left" : "Right");
                output.write(" wins: " + winner.getScore());

                if (winner.getScore().equals(Hand.Score.HighCard)) {
                    output.write(" - " + winner.getHighCard());
                }
            }
            output.write(System.lineSeparator());
        }
        output.close();
    }
}
