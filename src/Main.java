// Main class for testing MyHashTable and BST implementations
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        // Test MyHashTable
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(11);
        Random rand = new Random();


        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(100000);
            table.put(new MyTestingClass(id), new Student("name: student_" + id));
        }

        System.out.println("Bucket sizes in MyHashTable:");
        System.out.println();
        int[] bucketSizes = table.getBucketSizes();
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements");
        }


        BST<Integer, String> bst = new BST<>();
        bst.put(5, "Five");
        bst.put(3, "Three");
        bst.put(7, "Seven");
        bst.put(1, "One");
        bst.put(4, "Four");

        System.out.println("\nBST size: " + bst.size());
        System.out.println("BST in-order traversal:");
        for (BST.Entry elem : bst.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}