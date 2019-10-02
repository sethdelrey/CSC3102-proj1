import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        AVLTree A = new AVLTree();
        try (Scanner fin = new Scanner(new File("AVLtree-input.txt"))) {
            FileWriter write = new FileWriter(new File("output.txt"));
            while (fin.hasNextLine()) {
                Scanner sin  = new Scanner(fin.nextLine());
                sin.useDelimiter(" ");
                String choice = sin.next();
                String str = "";
                int val = 0;
                if (sin.hasNextInt()) {
                    val = sin.nextInt();
                }

                switch (choice) {
                    case "IN":
                        A.insert(val);
                        break;
                    case "MI":
                        str = String.format("%-8d//MI%n", A.getKey(A.miniumum(A.getRoot())));
                        break;
                    case "MA":
                        str = String.format("%-8d//MA%n", A.getKey(A.maximum(A.getRoot())));
                        break;
                    case "PR":
                        str = String.format("%-8d//PR %d%n", A.predecessor(A.search(A.getRoot(), val)), val);
                        break;
                    case "SR":
                        str = String.format("%-8d//SR%n", A.search(val), val);
                        break;
                    case "SC":
                        str = String.format("%-8d//SC %d%n", A.successor(val), val);
                        break;
                    case "RA":
                        str = String.format("%-8d//RA %d%n", A.rank(val),val);
                        A.rank(sin.nextInt());
                        break;
                    case "TR":
                        str = A.inOrder() + "     //TR";
                        break;
                }
                write.write(str);
            }
            write.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("There was no file found with the name AVLtree-input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
