// C343 / Summer 2019
//
// a very simple, starting BST tree class;
// it's so simple, it's named BSTJr.

public class BSTJr <K extends Comparable<?super K>> {
    BinNode<K> root;
    BinNode<K> curr;

    // TODO for C343/Summer 2019 - Lab 09
    // "unbalanced" is used for balance checking:
    //    if a node is unbalanced, the tree will be unbalanced
    BinNode<K> unbalanced = null;

    public BSTJr() {
        root = null;
        curr = null;
    }
    public void build(K[] ks) {
        for (int i = 0; i < ks.length; i ++)
            insert(ks[i]);
    }
    public void insert(K k) {
        BinNode<K> t = new BinNode<K>(k);
        if (root == null) {
            root = curr = t;
        } else {
            curr = search(root, k);
            if (k.compareTo(curr.getKey()) < 0)
                curr.setLeft(t);
            else
                curr.setRight(t);
        }
    }
    public BinNode<K> search(BinNode<K> entry, K k) {
        if (entry == null)
            return null;
        else {
            entry.setSize(entry.getSize() + 1); //update the size of the subtree by one
            if (entry.isLeaf())
                return entry;
            if (k.compareTo(curr.getKey()) < 0) {
                if (entry.getLeft() != null)
                    return search(entry.getLeft(), k);
                else
                    return entry;
            } else {
                if (entry.getRight() != null)
                    return search(entry.getRight(), k);
                else
                    return entry;
            }
        }
    }
    public void display() {
        if(root == null) return;
        System.out.println("Preorder enumeration: key(size-of-the-subtree)");
        preorder(root);
        System.out.println();
    }
    public void preorder(BinNode<K> entry) {
        System.out.print(entry.getKey() + "(" + entry.getSize() + ") ");
        if(entry.getLeft() != null) preorder(entry.getLeft());
        if(entry.getRight() != null) preorder(entry.getRight());
    }

    // TODO for C343/Summer 2019 - Lab 09
    // implement checkBalance(), perhaps write treeHeight(node) as helper function

    public boolean checkBalance(){
        int diff = Math.abs(treeHeight(root.getLeft()) - treeHeight(root.getRight()));
        return diff < 1;
    }


    public int treeHeight(BinNode<K> node){
        if(node == null){
            return 0;
        }
        else {
            return Math.max(treeHeight(node.getLeft()), treeHeight(node.getRight())) + 1;
        }
    }


    public static void main(String[] argv) {
        BSTJr<Integer> tree = new BSTJr<Integer>();
        Integer[] ks = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10, 11};
        tree.build(ks);
        tree.display();

        if(tree.checkBalance()){
            System.out.println("balanced");
        }
        else{
            System.out.println("not balanced");
        }
    }
}