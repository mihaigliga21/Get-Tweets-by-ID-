package GET;

import java.io.*;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetTweets {
	
	//Application Settings
	private static String consumerKey = "R4dICN9z1B9wAl9JiVne2yMtD";
	private static String consumerSecret = "HwbZCGrAonAyEkNbMyeQPqsNoqweuj4F7I5goXu79CDl9CKmqV";
	private static String accessToken = "519873273-3iustnJC2PDGDig2s5mbOkqXFleovAu39DavPyai";
	private static String accessTokenSecret = "KlNLofYvRuiOCbJUvXPRX95feSqGehfF61CMmcYwfkQlE";

	public static void main(String[] args) throws IOException {
	    
		// connect to Twitter
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true);
	    cb.setOAuthConsumerKey(consumerKey);
	    cb.setOAuthConsumerSecret(consumerSecret);
	    cb.setOAuthAccessToken(accessToken);
	    cb.setOAuthAccessTokenSecret(accessTokenSecret);
	    
	    //Instantiate 
	    Twitter twitter = new TwitterFactory(cb.build()).getInstance();
	    
	    	// Open the files
			try{
			    FileInputStream fstream = new FileInputStream("input.txt");
			    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			    
			    FileWriter fwriter = new FileWriter("output.txt", false);
				BufferedWriter bw = new BufferedWriter(fwriter);
				
			    String strLine;

				  //Read File Line By Line
				  while ((strLine = br.readLine()) != null)   {
					  try {
				            Status status = twitter.showStatus(Long.parseLong(strLine));
				            					 
				            System.out.println("Tweet has been written into output file!!!");
				            bw.write("");
				            bw.write(status.getText() + "\n");
				            /*
				             * System.out.println(status.getText());
				             */
				     
				        } catch (NumberFormatException e) {
				            e.printStackTrace();
				        } catch (TwitterException e) {				            
				        	System.out.println("Error retrieving this tweet :(");
				        }					   					  
					  }
				  //close files
				  br.close();
				  bw.close();
				  
				}
				catch(FileNotFoundException e)
				{
					System.out.println(e);
				}	  	   	  
	}
}
