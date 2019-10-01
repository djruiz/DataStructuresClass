import java.util.*;
import java.net.*;
import java.io.*;

public class TweetCollection {

    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public TweetCollection(){
    }

    public void add(Tweet input){
        tweets.add(input);
    }

    public void remove(int input){
        tweets.remove(input);
    }

    public Tweet getTweet(int input){
        return tweets.get(input);
    }

    public TweetCollection byAuthor(String authorName){
        TweetCollection personsTweets = new TweetCollection();
        for(int i = 0; i < tweets.size(); i++){
            if(tweets.get(i).getAuthor().equals(authorName)){
                //System.out.println(tweets.get(i).getContent());
                personsTweets.add(tweets.get(i));
            }
        }
        return personsTweets;
    }

    public TweetCollection mentioned(String user){
        TweetCollection personsTweets = new TweetCollection();
        for(int i = 0; i < tweets.size(); i++){
            for(int j = 0; j < tweets.get(i).getContent().length(); j++){
                if(tweets.get(i).getContent().charAt(j) == '@' && tweets.get(i).getContent().length() > j + user.length()){
                    int beginCompare = j + 1;
                    int endCompare = beginCompare + user.length();
                    if(tweets.get(i).getContent().substring(beginCompare, endCompare).equals(user)){
                        personsTweets.add(tweets.get(i));
                    }
                }
            }
        }
        return personsTweets;
    }

    public String toString(){
        String build = "";
        for(int i = 0; i < tweets.size(); i++){
            build += "[";
            build += getTweet(i).toString();
            build += "]\n";
        }
        return build;
    }


    public static void main(String[] args){
        // C343/Summer 2019 - Mitja Hmeljak
//
// lab 06 starter code
        //create new instance of tweetcollection
        TweetCollection test1 = new TweetCollection();

        try {
//Step 1: create a scanner
            System.out.println("we're going to create a scanner");
//From a URL
            URL url = new URL("http://homes.soic.indiana.edu/classes/summer2019/csci/c343-mitja/test2019/tweet-data-May15.txt");
            System.out.println("obtained a URL");
            Scanner in = new Scanner(url.openStream());
            System.out.println("scanner created");
//From System.in (user's inputs)
//Scanner in = new Scanner(System.in);
//From a local file (e.g., tweet-data-May15.txt on your local machine)
//Scanner in = new Scanner(new FileReader("tweet-data-May15.txt"));
//Step 2: read data

            int i = 0;
            while (in.hasNext()) {
                //nextLine() reads a line;
                //Scanner class has other methods to allow the user to read values of various types, eg.., nextInt()
                String str = in.nextLine();
                System.out.print("at line ");
                System.out.print(i);
                System.out.print(" there is [");
                System.out.print(str);
                System.out.println("\n");
                System.out.println(str.substring(0, str.indexOf(" ")));
                Tweet test = new Tweet(str.substring(0, str.indexOf(" ")), str.substring(str.indexOf(" ")));
                test1.add(test);
                System.out.println("]");
                i++;
            }
            System.out.println("A");
//Step 3: close the scanner
            in.close();
        } catch (Throwable e) {
            System.out.println("exception is "+ e);
            e.printStackTrace();
        }


        //test to see if tweets were added correctly
        System.out.println(test1);
        System.out.println("\n\n\n");
        //System.out.println(test1.getTweet(0).getAuthor());
        System.out.println(test1.byAuthor("NoSQLDigest"));


        System.out.println(test1.mentioned("rtslabs"));
        System.out.println(test1.mentioned("kdnuggets"));


    }

}
