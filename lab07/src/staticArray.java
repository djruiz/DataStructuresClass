public class staticArray {

    Integer[] test;

    public staticArray(int size){
        this.test = new Integer[size];
    }

    public void fill(){
        for(int i = 0; i < test.length; i++){
            test[i] = i;
        }
    }

    public void removeAt(int index){
        test[index] = null;
    }

    @Override
    public String toString() {
        String build = "[";
        for(int i = 0; i < test.length; i++){
            build += test[i] + ", ";
        }
        build += "]";
        return build;
    }

    public static void main(String[] args){
        staticArray test1 = new staticArray(20);
        test1.fill();
        System.out.println(test1);

        //testing remove at
        test1.removeAt(0);
        test1.removeAt(5);
        System.out.println(test1);
    }

}
