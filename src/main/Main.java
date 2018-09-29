package main;

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
}
