//C343 Summer 19 Daniel, Ruiz djruiz

public class Student {

    private String name;
    private String major;

    public Student(String name){
        this.name = name;
    }

    private void setMajor(String major){
        this.major = major;
    }

    public String toString(){
        String builder = "";
        builder += "Student: " + getName() + "\nMajor: " + getMajor() + "\n";
        return builder;
    }

    public String getMajor(){
        return this.major;
    }

    public String getName(){
        return this.name;
    }

    public static void main(String[] args){
        System.out.println("Students Testing\n---------------------------");

        //first student object created and tested
        Student Daniel = new Student("Daniel");
        Daniel.setMajor("Informatics/Com Sci");
        //System.out.println(Daniel);

        //second student object created and tested
        Student Bob = new Student("Bob");
        Bob.setMajor("BuSiNeSS");
        //System.out.println(Bob);

        Student Kelsey = new Student("Kelsey");
        Kelsey.setMajor("Communications");
        //System.out.println(Bob);

        //create and test students array
        Student[] studentsArray = new Student[3];
        studentsArray[0] = Daniel;
        studentsArray[1] = Bob;
        studentsArray[2] = Kelsey;
        for(int i = 0; i < studentsArray.length; i++){
            System.out.println(studentsArray[i]);
        }


    }
}
