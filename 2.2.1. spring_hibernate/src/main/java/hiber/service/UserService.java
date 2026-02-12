package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void addCar(Car car);
    List<User> listUsers();
    List<Car> listCars();
    void update(User user);
    User getUserByCar(String model, int series);
}
