import java.util.ArrayList;

public class dynamicArray {

    ArrayList<Integer> test;
    int size;

    public dynamicArray(int size){
        test = new ArrayList<>(size);
        this.size = size;
        this.fill();
    }

    @Override
    public String toString() {
        String build = "[";
        for(int i = 0; i < test.size(); i++){
            build += test.get(i) + ", ";
        }
        build += "]";
        return build;
    }

    public void fill(){
        for(int i = 0; i < this.size; i++){
            test.add(i);
        }
    }

    public int getSize(){
        return this.size;
    }

    public void removeAt(int index){
        for (int i = index; i < this.size - 1; i++) {
            test.set(i, test.get(i + 1));
        }
        test.remove(this.size - 1);
        this.size -= 1;
    }

    public static void main(String[] args){
        dynamicArray test1 = new dynamicArray(20);
        //test1.fill();
        System.out.println(test1);
        System.out.println(test1.getSize());
        test1.removeAt(20);
        System.out.println(test1);
        test1.removeAt(17);
        System.out.println(test1);
        //System.out.println(test1.getSize());
    }

}
