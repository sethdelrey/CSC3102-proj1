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
                int val;
                switch (choice) {
                    case "IN":
                        A.insert(sin.nextInt());
                        break;
                    case "MI":
                        str = String.format("%-8d//MI%n",A.getKey(A.miniumum(A.getRoot())));
                        break;
                    case "MA":
                        str = String.format("%-8d//MA%n",A.getKey(A.maximum(A.getRoot())));
                        break;
                    case "PR":
                        val = sin.nextInt();
                        str = String.format("%-8d//PR %d%n", A.predecessor(A.search(A.getRoot(), val)), val);
                        break;
                    case "SR":
                        val = sin.nextInt();
                        str = String.format("%-8d//SR%n", A.search(val), val);
                        break;
                    case "SC":
                        //str = String.format();
                        A.successor(A.search(A.getRoot(), sin.nextInt()));
                        break;
                    case "RA":
                        A.rank(sin.nextInt());
                        break;
                    case "TR":
                        A.inOrder(A.getRoot());
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
