import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException {

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        for(int i=0 ; i<restaurants.size() ; i++){
            if(restaurants.get(i).getName().equals(restaurantName)) {
                return restaurants.get(i);
            }
        }
        throw new restaurantNotFoundException("restaurantnotfound");

    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Long calculateOrder(List<Item> Orders){
        long totalCost= 0;
        for(int i=0; i<Orders.size();i++){
            totalCost=totalCost+Orders.get(i).getprice();
        }
        return  totalCost;

    }
}
