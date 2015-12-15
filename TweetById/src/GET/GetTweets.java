package GET;

import java.io.*;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetTweets {
	
	//Application Settings
	private static String consumerKey = "your consumerKey";
	private static String consumerSecret = "your consumerSecret";
	private static String accessToken = "your accessToken";
	private static String accessTokenSecret = "your accessTokenSecret";

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
							  Status statusList = twitter.showStatus(Long.parseLong(strLine));
							  
							  //long id = statusList.getId();
         					  String tw = statusList.getText();
         					  
         						  System.out.println("Tweet has been written into output file!!!");
         						  for (int i=0; i<TwitterResponse.READ; i++) {								   								  
         							  bw.write("");								  
         							  bw.write(cleanText(tw)+"\n");	
         						  }							  							  								
  
					        } catch (NumberFormatException e) {
					            e.printStackTrace();
					        } catch (TwitterException e) {				            
					        	System.out.println("Error retrieving this tweet :(");
					        	bw.write("This tweet has not been found!!" +"\n");
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
