import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainClass {

    //Method that reads a file and adds its contents to an AVL Tree
    //while maintaining the binary and balance properties.
    //Writes the completed AVL Tree in sorted order into a separate ouput file.
    public static void main(String[] args) {
        System.out.print("Enter the file you would like to process: ");
        Scanner cin = new Scanner(System.in);
        String filePath = cin.next();
        long startTime = System.nanoTime();
        AVLTree A = new AVLTree();
        try (Scanner fin = new Scanner(new File(filePath))) {
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
                        str = A.minimum() + "\n";
                        break;
                    case "MA":
                        str = A.maximum() + "\n";
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
            System.out.println("There was no file found with that name.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
