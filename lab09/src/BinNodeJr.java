// C343 / Summer 2019
//
// a very simple, starting binary node class;
// it's so simple, it's named BinNodeJr.

public class BinNodeJr<E extends Comparable<? super E>> {
    private E value;
    private BinNodeJr<E> left;
    private BinNodeJr<E> right;

    public BinNodeJr(E e) {
        value = e;
        left = right = null;
    }

    public void setLeft(BinNodeJr<E> node) {
        left = node;
    }

    public void setRight(BinNodeJr<E> node) {
        right = node;
    }

    public boolean find(E q) {
        boolean kill = false;
        if (value.equals(q)) {
            return true;
        }
        else if (left != null && right == null) {
            kill = left.find(q);
        }
        if (right != null && left == null) {
            kill = right.find(q);
        }
        if (left == null && right == null) {
            kill = false;
        }
        else {
            kill = left.find(q) || right.find(q);
        }
        return kill;
    }


    public static void main(String[] argv) {
        BinNodeJr<Integer> root = new BinNodeJr<Integer>(9);
        BinNodeJr<Integer> node1 = new BinNodeJr<Integer>(29);
        BinNodeJr<Integer> node2 = new BinNodeJr<Integer>(39);
        root.setLeft(node1);
        root.setRight(node2);
        BinNodeJr<Integer> node3 = new BinNodeJr<Integer>(1);
        BinNodeJr<Integer> node4 = new BinNodeJr<Integer>(27);
        node1.setLeft(node3);
        node1.setRight(node4);
        BinNodeJr<Integer> node5 = new BinNodeJr<Integer>(420);
        BinNodeJr<Integer> node6 = new BinNodeJr<Integer>(30);
        node2.setLeft(node5);
        node2.setRight(node6);
        BinNodeJr<Integer> node7 = new BinNodeJr<Integer>(67);
        BinNodeJr<Integer> node8 = new BinNodeJr<Integer>(7);
        node3.setRight(node7);
        node3.setLeft(node8);
        BinNodeJr<Integer> node9 = new BinNodeJr<Integer>(2);
        BinNodeJr<Integer> node10 = new BinNodeJr<Integer>(8);
        node4.setRight(node9);
        node4.setLeft(node10);


        // find() needs to be implemented
        System.out.println("39 is found in the tree: " + root.find(39));
        // find(39) shall return true
        System.out.println("100 is found in the tree: " + root.find(100));
        // find(100) shall return false

        if (root.find(8)) {
            System.out.println("8 is found");
        } else {
            System.out.println("error");
        }
    }
}