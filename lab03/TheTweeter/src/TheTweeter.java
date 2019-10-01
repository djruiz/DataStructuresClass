import java.util.ArrayList;

public class TheTweeter {

    private ArrayList<TweetClass> tweets = new ArrayList<TweetClass>();

    private void add(TweetClass tweet){
        tweets.add(tweet);
    }

    public void remove(String author){
        for(int i = 0; i < tweets.size(); i++){
            if(tweets.get(i).author.equals(author)){
                tweets.remove(i);
            }
        }
    }

    public TweetClass get(String author){
        for(int i = 0; i < tweets.size(); i++){
            if(tweets.get(i).author.equals(author)){
                return tweets.get(i);
            }
        }
    }

    public String toString(){
        String empty = "";
        for(int i = 0; i < tweets.size(); i++) {
            empty += "Tweet Content: " + tweets.get(i).tweetContent + "  Tweet Author " + tweets.get(i).author + "\n";
        }
    }

    public static void main(String[] args){
        TweetClass tweeter = new TweetClass("Hello world", "Daniel Ruiz");
        TweetClass tweeter1 = new TweetClass("Hello world", "Daniel Ruiz");
        TweetClass tweeter2 = new TweetClass("Hello world", "Daniel Ruiz");
        TweetClass tweeter3 = new TweetClass("Hello world", "Daniel Ruiz");
        TweetClass tweeter4 = new TweetClass("Hello world", "Daniel Ruiz");
        TweetClass tweeter5 = new TweetClass("Hello world", "Daniel Ruiz");

        TheTweeter test = new TheTweeter();
        test.add(tweeter);
        test.add(tweeter1);
        test.add(tweeter2);
        test.add(tweeter3);
        test.add(tweeter4);
        test.add(tweeter5);

        System.out.println(test);
    }


}
