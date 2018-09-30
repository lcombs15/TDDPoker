package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        if (args.length != 10) {
            System.out.println("Invalid # args: " + args.length + ". Must have 10 to make two hands.");
            System.out.println("Format: {Hand 1 - 5 cards} {Hand 2 - 5 cards}");
            return;
        }

        String HandString1 = "", HandString2 = "";
        for (int i = 0; i < 5; i++) {
            HandString1 += args[i] + " ";
            HandString2 += args[i * 2] + " ";
        }

        Hand a, b;
        a = new Hand(HandString1);
        b = new Hand(HandString2);

        int result = a.compareTo(b);
        String message;
        if (result == 1) {
            message = "First hand wins. - " + a.getScore();
        } else if (result == -1) {
            message = "Second hand wins. - " + b.getScore();
        } else {
            message = "The hands tie.";
        }

        System.out.println(message);
    }

    /*
        Input file format:
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10
        Output file format:
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10 --> Left: Straight
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10 --> Left: High card - Ace
            C1 C2 C3 C4 C5 | C6 C7 C8 C9 C10 --> Left: Flush
     */
    public void rankHandsInFile(String inputLocation, String outputLocation) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(new File(inputLocation));
        FileWriter output = new FileWriter(outputLocation);

        while(input.hasNext()){
            String line = input.nextLine();

            output.write(line);

            Hand left, right;
            left = new Hand(line.substring(0,line.indexOf("|") - 1).trim());
            right = new Hand(line.substring(line.indexOf("|") + 1).trim());

            Hand winner = left.winningHandAgainst(right);
            output.write(" --> ");

            if (winner == null){
                output.write("Tie");
            }else{
                output.write(winner == left ? "Left" : "Right");
                output.write(" wins: " + winner.getScore());

                if(winner.getScore().equals(Hand.Score.HighCard)){
                    output.write(" - " + winner.getHighCard());
                }
            }
            output.write(System.lineSeparator());
        }
        output.close();
    }
}
