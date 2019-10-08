import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        AVLTree A = new AVLTree();
        try (Scanner fin = new Scanner(new File("AVLtree-input.txt"))) {
            FileWriter write = new FileWriter(new File("output.txt"));
            Scanner sin;
            while (fin.hasNextLine()) {
                sin  = new Scanner(fin.nextLine());
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
                        str = A.getKey(A.miniumum(A.getRoot())) + "\n";
                        break;
                    case "MA":
                        str = A.getKey(A.maximum(A.getRoot())) + "\n";
                        break;
                    case "PR":
                        str = A.predecessor(val) + "\n";
                        break;
                    case "SE":
                        str = A.select(val) + "\n";
                        break;
                    case "SR":
                        str = A.search(val) + "\n";
                        break;
                    case "SC":
                        str = A.successor(val) + "\n";
                        break;
                    case "RA":
                        str = A.rank(val) + "\n";
                        break;
                    case "TR":
                        str = A.inOrder() + "\n";
                        break;
                }
                write.write(str);
            }
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            write.write(time/1000 + " micro-sec");
            write.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("There was no file found with the name AVLtree-input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
