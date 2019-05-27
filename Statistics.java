import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Random;
import java.util.List;
import java.util.Arrays;

/**
* The Statistics class contains the methods to retrieve the AirbnbListing class
*
* @author Yeshvanth Prabakar, Arnav Ratan, Hardik Agrawal, Shreyas Solanki
* @version 30-03-18
*/
public class Statistics

{
    // instance variables - replace the example below with your own
   AirbnbDataLoader dataLoader;
   private ArrayList<AirbnbListing> listings;
   public static ArrayList<AirbnbListing> currentListings;
   public static ArrayList<String> neighbourhoods = new ArrayList();
   HashMap<String, String> mapDetails = new HashMap<>();
   ArrayList<String> arrayListOfKeys;
   private int index;
   static String[] elements = new String[33];
   /**
    * Constructor for objects of class Statistics
    */

   public Statistics()
   {
       dataLoader = new AirbnbDataLoader();
       listings = dataLoader.load();
       //This for loop adds all the properties to the arrayList neighbourhoods
       for (AirbnbListing property : listings){
           if(!neighbourhoods.contains(property.getNeighbourhood())){
            neighbourhoods.add(property.getNeighbourhood());
          }
       }
       addHashMapValues();
       Set<String> mapKeys = mapDetails.keySet();
       arrayListOfKeys = new ArrayList<String>(mapKeys); //Adds the key of the hashmap to an arraylist.
       currentListings = new ArrayList<AirbnbListing>();
   }
   /**
    * @param Takes borough as a String
    * @return List of all the properties in a particular borough.
    */
   public ArrayList specificListing(String borough)
   {
       
       for (AirbnbListing property : listings){
          if((property.getNeighbourhood()).equals(borough)){
            currentListings.add(property);
          }     
            }
       return currentListings;     
   }
   
   /**
    * Puts the Values into the HashMap mapDetails
    * Used to add statistics name as string and value of the the statistics 
    */
   public void addHashMapValues()
   {
       mapDetails.put("Total number of properties", Integer.toString(numberOfAllListings())); 
       mapDetails.put("Average review score of properties", Double.toString(averageReview()));  
       mapDetails.put("Entire number of homes and apartments",Integer.toString(numberOfEntireHomes()));  
       mapDetails.put("Most expensive neighbourhood", expensiveNeighbourhood()); 
       mapDetails.put("Most vacant neighbourhood", availableNeighbourhood());
       mapDetails.put("Neighbourhood with the best review", bestReiviewNeighbourhood());
       mapDetails.put("Neighbourhood with worst reviews", worstReiviewNeighbourhood());
       mapDetails.put("Neighbourhood with least no of homes",neighbourhoodWithLeastHomes());
   }
   
   /**
    * @return The next Statistics title.
    */
   public String nextStatisticTitle()
   {
       index++;
       if(index >= arrayListOfKeys.size())
       {
           index = 0;
       }
       int x = index;
       return arrayListOfKeys.get(x);
   }
   
   /**
    * @return The previous Statistics title.
    */
   public String previousStatisticTitle()
   {
       index--;
       if(index <= 0)
       {
           index = arrayListOfKeys.size()-1;
       }
       return arrayListOfKeys.get(index);
   }
   
   /**
    * @return The value of the statistics.
    * @param This takes the key of the hash Map 
    */
   public String statisticData(String key)
   {
       return mapDetails.get(key);
   }
    
   /**
    * @return total number of properties in the area
    */
 
   public int numberOfAllListings()
   {
       return listings.size();
   }

   /**
    * @return average review score of all listings
    */
   public double averageReview()
   {
      double sum = 0;
      double num = 0;
      for (String neighbourhood : neighbourhoods){
          num += numberOfListingsWithScores(neighbourhood);
      }
      for (AirbnbListing property : listings) {
          if (property.getReviewsPerMonth()>0){
            sum += property.getReviewsPerMonth();
         }
      }
      mapDetails.put("Average Review", Double.toString(sum/num));
      return sum/num;
   }
   
   /**
    * @return name of the neighbourhood with most highest average availability
    */
   public String availableNeighbourhood()
   {
      int max=0;
      String availableNeighbourhood="";
      for (String neighbourhood : neighbourhoods) {
         if(availabilityPerNeighbourhood(neighbourhood)>max){
            max = availabilityPerNeighbourhood(neighbourhood);
            availableNeighbourhood = neighbourhood;
        }
      }
      return availableNeighbourhood;
   }
   
    /**
    * @return name of the neighbourhood with most highest average availability
    */
   public String stringNeighbourhood()
   {
      String availableNeighbourhood="";
      for (String neighbourhood : neighbourhoods) {
            //max = availabilityPerNeighbourhood(neighbourhood);
            availableNeighbourhood = neighbourhood;
      }
      return availableNeighbourhood;
   }
   
   /**
    * @return number of entire homes
    */
   public int numberOfEntireHomes() 
   {
      int number = 0;
      for (AirbnbListing property : listings){
          if (property.getRoom_type().equals("Entire home/apt")){
              number = number + 1;
         }
      } 
      return number;
   }
   
   /**
    * This returns the neighbourhood with the best score.
    */
   public String bestReiviewNeighbourhood()
   {
      double max=0;
      String bestReviewNeighbourhood = "";
      for (String neighbourhood : neighbourhoods) {
          if(averageReviewPerNieghbourhood(neighbourhood)>max){
             max = averageReviewPerNieghbourhood(neighbourhood);
             bestReviewNeighbourhood = neighbourhood;
         }
      }
      return bestReviewNeighbourhood;
   }
   
   /**
    * @return name of the neighbourhood with most expensive average price
    */
   public String expensiveNeighbourhood()
   {
      int max=0;
      String expensiveNeighbourhood="";
      for (String neighbourhood : neighbourhoods) {
          if(pricesPerNieghbourhood(neighbourhood)>max){
            max=pricesPerNieghbourhood(neighbourhood);
            expensiveNeighbourhood = neighbourhood;
         }
      }
      return expensiveNeighbourhood;
   }
   
    /**
    * This returns the neighbourhood with the best score.
    */
   public String worstReiviewNeighbourhood()
   {
      double max = 500;
      String worstReviewNeighbourhood = "";
      for (String neighbourhood : neighbourhoods) {
          if(max>averageReviewPerNieghbourhood(neighbourhood)){
             max = averageReviewPerNieghbourhood(neighbourhood);
             worstReviewNeighbourhood = neighbourhood;
         }
      }
      return worstReviewNeighbourhood;
   }
   
   /**
   * @param name of neighbourhood
   *@return number of properties with reviews
   */
   public int numberOfListingsWithScores(String neighbourhood)
   {
       int num = 0;
       for (AirbnbListing property : listings){
           if(property.getNeighbourhood().equals(neighbourhood)){
               if (property.getReviewsPerMonth()>0){
                        num += 1;
               }
           }
        }
       return num;
   }
   
    /**
    * @return name of the neighbourhood which is the 
    */
   public String neighbourhoodWithLeastHomes()
   {
      int max=500;
      String availableNeighbourhood="";
      for (String neighbourhood : neighbourhoods) {
         if(max>availabilityPerNeighbourhood(neighbourhood)){
            max = availabilityPerNeighbourhood(neighbourhood);
            availableNeighbourhood = neighbourhood;
        }
      }
      return availableNeighbourhood;
   }
   
   /**
    * @param name of neighbourhood
    * @return average review score per neighbourhood
    */

   public double averageReviewPerNieghbourhood(String neighbourhood)
   {
      double review=0;
      for (AirbnbListing property : listings) {
          if(property.getNeighbourhood().equals(neighbourhood)){
              if (property.getReviewsPerMonth()>0){
                review += property.getReviewsPerMonth();
             }
         }
      }
      return review/numberOfListingsWithScores(neighbourhood);
   }
   
   /**
   * @param name of neighbourhood for which average price is calculated
   * @return average price per neighbourhood as
   * price per night*minimum number of nights
   */
   public int pricesPerNieghbourhood(String neighbourhood)
   {
      int price=0;
      for (AirbnbListing property : listings) {
          if(property.getNeighbourhood().equals(neighbourhood)){
            price += property.getPrice()*property.getMinimumNights();
         }
      }
      int average = price/countProperties(neighbourhood);
      return average;
   }
   
    /**
    * Counts number of properties per neighbourhood
    * @param neighbourhood(name of neighbourhood)
    * @return count(number of properties)
    */

   public int countProperties(String neighbourhood)
   {
       int count=0;
       for (AirbnbListing property : listings){
          if(property.getNeighbourhood().equals(neighbourhood)){
            count = count + 1;
         }
       }
       return count;
   }
   
   /**
    * @param name of neighbourhood
    * @return average review score per neighbourhood
    */

   public double averageReviewPerNeighbourhood(String neighbourhood)
   {
      double review=0;
      for (AirbnbListing property : listings) {
          if(property.getNeighbourhood().equals(neighbourhood)){
              if (property.getReviewsPerMonth()>0){
                review += property.getReviewsPerMonth();
             }
         }
      }
      return review/numberOfListingsWithScores(neighbourhood);
   }
   
    /**
    * @param name of neighbourhood for which average availability is calculated
    * @return average availability per neighbourhood as
    */
   public int availabilityPerNeighbourhood(String neighbourhood)
   {
      int availability=0;
      for (AirbnbListing property : listings) {
          if(property.getNeighbourhood().equals(neighbourhood)){
            availability += property.getAvailability365();
         }
      }
      int average = availability/countProperties(neighbourhood);
      return average;
   } 
   
}