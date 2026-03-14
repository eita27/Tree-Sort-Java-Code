import java.util.*;

class Node {
    int key;
    Node left, right;

    Node(int item) {
        key = item;
        left = right = null;
    }
}

public class Main {

    static Node root = null;

// INSERT (no duplicates)
static Node insert(Node root, int key) {
    if (root == null) {
        root = new Node(key);
        return root;
    }

    if (key < root.key)
        root.left = insert(root.left, key);
    else if (key > root.key)
        root.right = insert(root.right, key);
    else
        ; // key already exists, do nothing (ignore duplicates)

    return root;
}

    // FIND MAX (LEFT SUBTREE)
    static int findMax(Node node) {
        while (node.right != null)
            node = node.right;
        return node.key;
    }

    // FIND MIN (RIGHT SUBTREE)
    static int findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node.key;
    }

    // DELETE
    static Node delete(Node root, int key) {

        if (root == null)
            return root;

        if (key < root.key)
            root.left = delete(root.left, key);

        else if (key > root.key)
            root.right = delete(root.right, key);

        else {

            // NO LEFT SUBTREE
            if (root.left == null && root.right != null) {
                int min = findMin(root.right);
                root.key = min;
                root.right = delete(root.right, min);
            }

            // NO RIGHT SUBTREE
            else if (root.right == null && root.left != null) {
                int max = findMax(root.left);
                root.key = max;
                root.left = delete(root.left, max);
            }

            // TWO CHILDREN
            else if (root.left != null && root.right != null) {
                int max = findMax(root.left);
                root.key = max;
                root.left = delete(root.left, max);
            }

            // LEAF NODE
            else {
                root = null;
            }
        }

        return root;
    }

    // INORDER (TREE SORT)
    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }
    
    // PREORDER
static void preorder(Node root) {
    if (root != null) {
        System.out.print(root.key + " ");
        preorder(root.left);
        preorder(root.right);
    }
}

// POSTORDER
static void postorder(Node root) {
    if (root != null) {
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.key + " ");
    }
}

    // HEIGHT
    static int height(Node node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // STORE TREE INTO ARRAY (1D representation)
    static void store(Node node, int[] arr, int index) {
        if (node == null || index >= arr.length)
            return;

        arr[index] = node.key;
        store(node.left, arr, 2 * index + 1);
        store(node.right, arr, 2 * index + 2);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\nMENU");
            System.out.println("1. Insert nodes");
            System.out.println("2. Delete nodes");
            System.out.println("3. Print BST (1D Array)");
            System.out.println("4. Tree Sort (Traversals)");
            System.out.println("5. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                System.out.println("Enter integers to insert (space separated):");
                String line = sc.nextLine();
                String[] nums = line.split(" ");

                for (String num : nums) {
                    int val = Integer.parseInt(num);
                    root = insert(root, val);
                }

            }

            else if (choice == 2) {

                System.out.println("Enter integers to delete (space separated):");
                String line = sc.nextLine();
                String[] nums = line.split(" ");

                for (String num : nums) {
                    int val = Integer.parseInt(num);
                    root = delete(root, val);
                }

            }

            else if (choice == 3) {

                int treeHeight = height(root);
                int size = (int) Math.pow(2, treeHeight) - 1;
                int[] arr = new int[size];
                Arrays.fill(arr, 0); // fill with 0 for empty nodes
                store(root, arr, 0);

                System.out.println("BST 1D Array Representation:");
                System.out.println(Arrays.toString(arr));

            }

else if (choice == 4) {

    System.out.println("Inorder (Sorted order):");
    inorder(root);
    System.out.println();

    System.out.println("Preorder:");
    preorder(root);
    System.out.println();

    System.out.println("Postorder:");
    postorder(root);
    System.out.println();
}

            else if (choice == 5) {
                break;
            }

            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
