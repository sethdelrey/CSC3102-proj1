import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        AVLTree A = new AVLTree();
        try (Scanner fin = new Scanner(new File("AVLtree-input.txt"))) {
            while (fin.hasNextLine()) {
                Scanner sin  = new Scanner(fin.nextLine());
                sin.useDelimiter(" ");
                String choice = sin.next();
                switch (choice) {
                    case "IN":
                        A.insert(sin.nextInt());
                        break;
                    case "MI":
                        A.miniumum();
                        break;
                    case "MA":
                        A.maximum();
                        break;
                    case "PR":
                        A.predessor(sin.nextInt());
                        break;
                    case "SR":
                        A.search(sin.nextInt());
                        break;
                    case "SC":
                        A.successor(sin.nextInt());
                        break;
                    case "RA":
                        A.rank(sin.nextInt());
                        break;
                    case "TR":
                        A.inOrder();
                        break;
                }
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("There was no file found with the name AVLtree-input.txt");
        }
    }
}
