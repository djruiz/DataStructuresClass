public class TweetClass {

    public String tweetContent;
    public String author;

    public TweetClass(String tweetContent, String author){
        this.tweetContent = tweetContent;
        this.author = author;
    }

    public void printContent(){
        System.out.println(tweetContent);
    }

    private boolean contains(String s){
        return tweetContent.contains(s);
    }

    public static void main(String[] args){

        TweetClass tweeter = new TweetClass("Hello world", "Daniel Ruiz");
        //print method test
        tweeter.printContent();

        if(tweeter.contains("world")){
            System.out.println("Contains method works");
        }

    }

}
