/* Name: Leith Rabah
 * Dr. Andrew Steinberg
 * COP3503 Summer 2022
 * Programming Assignment 2
 */
public class AddressBookTree <T extends Comparable<T>, U> {
    private Node<T, U> root;
    private Node<T, U> TNULL;

    //set color values
    final int red = 1;
    final int black = 0;

    //Getter for root
    public Node<T, U> getRoot() {
        return root;
    }

    //constructor
    public void AddressBookTree() {
        this.TNULL = new Node<T, U>();
        TNULL.color = black;
        TNULL.left = null;
        TNULL.right = null;
        this.root = TNULL;
    } //complete

    //insert node into tree
    public void insert(T name, U office) {
        Node<T, U> x = root;
        Node<T, U> y = null;
        Node<T, U> z = new Node<T, U>(name, office);


        while (x != TNULL) {
            y = x;
            if (z.name.toString().compareTo(x.name.toString()) < 0)
                x = x.left;
            else
                x = x.right;
        }
        z.color = red;

        if (y == TNULL)
            root = z;
        else if (z.name.toString().compareTo(y.name.toString()) < 0)
            y.left = z;
        else
            y.right = z;

        z.parent = y;
        z.left = TNULL;
        z.right = TNULL;
        z.color = red;
        InsertFix(z);
    } //complete

    //fix tree after insertion
    public void InsertFix(Node<T, U> z){
        Node<T, U> y = z.parent;

        if (y == null) {
            z.color = black;
            return;
        }
        if (y.color == black) {
            return;
        }
        Node<T, U> grandparent = y.parent;

        if (grandparent == null) {
            y.color = black;
            return;
        }
        Node<T, U> uncle = getUncle(y);

        if (uncle != null && uncle.color == red) {
            y.color = black;
            grandparent.color = red;
            uncle.color = black;
            InsertFix(grandparent);
        }
        else if (y == grandparent.left) {
            if (z == y.right) {
                LeftRotation(y);
                y = z;
            }
            RightRotation(grandparent);
            y.color = black;
            grandparent.color = red;
        }
        else {
            if (z == y.left) {
                RightRotation(y);
                y = z;
            }
            LeftRotation(grandparent);
            y.color = black;
            grandparent.color = red;
        }
    } //complete

    //get uncle node for post-insertion fix
    public Node<T, U> getUncle(Node<T, U> x) {
        Node<T, U> grandparent = x.parent;

        if (grandparent.left == x)
            return grandparent.right;

        else if (grandparent.right == x)
            return grandparent.left;

        return null;
    } //complete

    //find node for deletion
    public Node<T, U> searchNode(Node<T, U> x, T name) {
        if (x.name.toString().compareTo((String) name) == 0)
            return x;

            //descend to the right subtree
        else if (x.name.toString().compareTo((String) name) < 0)
            return searchNode(x.right, name);

            //descend to the left subtree
        else if (x.name.toString().compareTo((String) name) > 0)
            return searchNode(x.left, name);

        else
            return null;
    } //complete

    //find predecessor
    public Node<T, U> TreeMinimum(Node<T, U> x) {
        Node<T, U> y = x;

        while (y.left != TNULL) {
            y = y.left;
        }
        return y;
    } //complete

    //delete node from the tree
    public void deleteNode(T name){
        Node<T, U> x;
        Node<T, U> w = this.root;
        Node<T, U> z = searchNode(w, name);
        Node<T, U> y = z;
        int yColor = y.color;

        if(z.left == this.TNULL){
            x = z.right;
            RBTransplant(z, z.right);
        }
        else if(z.right == this.TNULL){
            x = z.left;
            RBTransplant(z, z.left);
        }
        else{
            y = TreeMinimum(z.right);
            yColor = y.color;
            x = y.right;

            if (y.parent == z)
                x.parent = y;

            else {
                RBTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            RBTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yColor == black)
            DeleteFix(x);
    }

    //fix tree after deletion
    public void DeleteFix(Node<T, U> x){
        Node<T, U> w;

        while(x != this.root && x.color == black) {
            if(x == x.parent.left) {
                w = x.parent.right;

                if(w.color == red) {
                    w.color = black;
                    x.parent.color = red;
                    LeftRotation(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == black && w.right.color == black) {
                    w.color = red;
                    x = x.parent;
                }
                else {
                    if(w.right.color == black) {
                        w.left.color = black;
                        w.color = red;
                        RightRotation(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.right.color = black;
                    LeftRotation(x.parent);
                    x = this.root;
                }
            }
            else {
                w = x.parent.left;

                if(w.color == red) {
                    w.color = black;
                    x.parent.color = red;
                    RightRotation(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == black && w.left.color == black) {
                    w.color = red;
                    x = x.parent;
                }
                else {
                    if(w.left.color == black) {
                        w.right.color = black;
                        w.color = red;
                        LeftRotation(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = black;
                    w.left.color = black;
                    RightRotation(x.parent);
                    x = this.root;
                }
            }
        }
        x.color = black;
    }

    //find replacement node for tree balancing
    public void Replacement(Node<T, U> parent, Node<T, U> oldNode, Node<T, U> newNode) {
        if (parent == TNULL)
            root = newNode;
        else if (parent.left == oldNode)
            parent.left = newNode;
        else
            parent.right = newNode;

        if (newNode != TNULL)
            newNode.parent = parent;
    } //complete

    //rotate subtree left
    public void LeftRotation(Node<T, U> nodeName) {
        Node<T, U> parent = nodeName.parent;
        Node<T, U> rightChild = nodeName.right;

        nodeName.right = rightChild.left;

        if (rightChild.left != TNULL)
            rightChild.left.parent = nodeName;

        rightChild.left = nodeName;
        nodeName.parent = rightChild;
        Replacement(parent, nodeName, rightChild);
    } //complete

    //rotate subtree right
    public void RightRotation(Node<T, U> nodeName) {
        Node<T, U> parent = nodeName.parent;
        Node<T, U> leftChild = nodeName.left;

        nodeName.left = leftChild.right;

        if (leftChild.right != TNULL)
            leftChild.right.parent = nodeName;

        leftChild.right = nodeName;
        nodeName.parent = leftChild;
        Replacement(parent, nodeName, leftChild);
    } //complete

    //transplant nodes for balancing
    public void RBTransplant(Node<T, U> x, Node<T, U> y) {
        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.parent = x.parent;
    } //complete

    //in-order traversal to display tree
    public void traverse(Node<T, U> x) {
        if (x == TNULL)
            return;

        traverse(x.left);
        System.out.println(x.name + " " + x.office);
        traverse(x.right);
    } //complete

    //display the tree
    public void display() {
        traverse(root);
    } //complete

    //count the number of black nodes in the tree
    public int countBlack(Node<T, U> x) {
        int blackCount = 0;

        if (x == TNULL)
            return 0;
        if (x.color == black)
            blackCount++;

        return (blackCount + countBlack(x.left) + countBlack(x.right));
    } //complete

    //count the number of red nodes in the tree
    public int countRed(Node<T, U> x) {
        int redCount = 0;

        if (x == TNULL)
            return 0;
        if (x.color == red)
            redCount++;

        return (redCount + countRed(x.left) + countRed(x.right));
    } //complete
}

//create the node class
class Node <T, U>{
    public T name;
    public U office;
    public Node<T,U> parent;
    public Node<T,U> left;
    public Node<T,U> right;
    public int color;

    //overloaded constructor
    public Node(T name, U office){
        this.name = name;
        this.office = office;
    }

    //default constructor
    public Node(){

    }
} //complete