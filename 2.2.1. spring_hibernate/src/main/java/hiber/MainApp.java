package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        Car car1 = new Car("Toyota", 111);
        Car car2 = new Car("BMW", 222);
        Car car3 = new Car("Audi", 333);
        Car car4 = new Car("Mercedes", 444);

        userService.addCar(car1);
        userService.addCar(car2);
        userService.addCar(car3);
        userService.addCar(car4);

        List<User> usersFromDb = userService.listUsers();
        List<Car> carsFromDb = userService.listCars();

        for (int i = 0; i < usersFromDb.size(); i++) {
            usersFromDb.get(i).setCar(carsFromDb.get(i));
            userService.update(usersFromDb.get(i));
        }


//        for (User user : usersFromDb) {
//            System.out.println("Id = " + user.getId());
//            System.out.println("First Name = " + user.getFirstName());
//            System.out.println("Last Name = " + user.getLastName());
//            System.out.println("Email = " + user.getEmail());
//            if (user.getCar() != null) {
//                System.out.println("Car = " + user.getCar().getModel() + ", series = " + user.getCar().getSeries());
//            }
//            System.out.println();
//        }
//
//        User foundUser = userService.getUserByCar("BMW", 222);
//        System.out.println("---- User who owns BMW 222 ----");
//        System.out.println("Name = " + foundUser.getFirstName() + " " + foundUser.getLastName());
//        System.out.println("Email = " + foundUser.getEmail());

        for (User user : usersFromDb) {
            System.out.println(user.getFirstName() + " " + user.getLastName() +
                    " owns " + (user.getCar() != null ? user.getCar().getModel() : "no car"));
        }
        context.close();
    }
}
