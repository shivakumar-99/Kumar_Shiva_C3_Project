import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    static RestaurantService service = new RestaurantService();
    static Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeAll
    static void initAll() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        System.out.println("Initialisation Done");
    }

    @AfterAll
    static void tearDown() {
        service = null;
        restaurant = null;
        System.out.println("Tear Down Completed");
    }
    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant restaurant3 = service.addRestaurant("A","Chennai",openingTime,closingTime);

        String existingRestaurant = restaurant.getName();
        assertEquals(restaurant3, service.findRestaurantByName("A"));
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        String non_existing_restaurant = "not a valid restaurant";
        assertThrows(restaurantNotFoundException.class,()->{service.findRestaurantByName(non_existing_restaurant);});
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException  {
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }

    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void calculate_price_for_given_items(){
        Restaurant restaurant1=service.addRestaurant("Kritunga","hyderabad",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        Restaurant restaurant2=service.addRestaurant("Woq","malkajgrir",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        Restaurant restaurant3=service.addRestaurant("TSK","Secundrabad",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        restaurant1.addToMenu("Sweet corn soup",119);
        restaurant2.addToMenu("Mutton mandi", 799);
        restaurant3.addToMenu("Dum Biryani", 299);

        Item item1 = new Item("Sweet corn soup",119);
        Item item2 = new Item("Mutton mandi", 799);

        List<Item> Orders = new ArrayList<Item>();
        Orders.add(item1);
        Orders.add(item2);

        assertEquals( 918, service.calculateOrder(Orders));

    }
}