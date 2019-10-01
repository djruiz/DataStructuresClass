// C343/Summer 2019 - Daniel Ruiz
// 
// lab 06 starter code


public class Tweet {

    private String content;
    private String user;

    public Tweet(String user, String content) {
        this.user = user;
        this.content = content;
    }

    public void display() {
        System.out.println(user + " tweeted about " + content);
    }

    public boolean contains(String query) {
        return content.contains(query);
    }

    public String toString(){
        String build = user + " tweeted about " + content;
        return build;
    }

    public String getAuthor(){
        return user;
    }

    public String getContent(){
        return this.content;
    }

    // test client code :
    public static void main(String[] argv) {
        Tweet t = new Tweet("TheDataUser", "Today we're going to talk about data structures!");
        t.display();
        String q = "data";
        System.out.println("it is " + t.contains(q) + " that the tweet contains " + q);
    }
}