package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Component
class CarConsoleRunner implements CommandLineRunner {

	private final CarRepository carRepository;

	public CarConsoleRunner(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("\nВыберите действие:\n" +
					"1. Добавить автомобиль\n" +
					"2. Показать все автомобили\n" +
					"3. Редактировать автомобиль\n" +
					"4. Удалить автомобиль\n" +
					"5. Поиск по марке\n" +
					"0. Выход");
			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1:
					addCar(scanner);
					break;
				case 2:
					showAllCars();
					break;
				case 3:
					editCar(scanner);
					break;
				case 4:
					deleteCar(scanner);
					break;
				case 5:
					searchByBrand(scanner);
					break;
				case 0:
					running = false;
					System.out.println("Выход...");
					break;
				default:
					System.out.println("Неверный выбор, попробуйте снова.");
			}
		}
		scanner.close();
	}

	private void addCar(Scanner scanner) {
		System.out.println("Введите марку:");
		String brand = scanner.nextLine();
		System.out.println("Введите модель:");
		String model = scanner.nextLine();
		System.out.println("Введите цвет:");
		String color = scanner.nextLine();
		System.out.println("Введите год выпуска:");
		int year = Integer.parseInt(scanner.nextLine());
		System.out.println("Введите цену:");
		double price = Double.parseDouble(scanner.nextLine());

		Car car = new Car();
		car.setBrand(brand);
		car.setModel(model);
		car.setColor(color);
		car.setYear(year);
		car.setPrice(price);

		carRepository.save(car);
		System.out.println("Автомобиль добавлен.");
	}

	private void showAllCars() {
		List<Car> cars = carRepository.findAll();
		if (cars.isEmpty()) {
			System.out.println("Список автомобилей пуст.");
		} else {
			cars.forEach(car -> System.out.println(car));
		}
	}

	private void editCar(Scanner scanner) {
		System.out.println("Введите id автомобиля для редактирования:");
		Long id = Long.parseLong(scanner.nextLine());
		Optional<Car> optionalCar = carRepository.findById(id);
		if (optionalCar.isPresent()) {
			Car car = optionalCar.get();
			System.out.println("Введите новую марку (текущая: " + car.getBrand() + "):");
			car.setBrand(scanner.nextLine());
			System.out.println("Введите новую модель (текущая: " + car.getModel() + "):");
			car.setModel(scanner.nextLine());
			System.out.println("Введите новый цвет (текущий: " + car.getColor() + "):");
			car.setColor(scanner.nextLine());
			System.out.println("Введите новый год выпуска (текущий: " + car.getYear() + "):");
			car.setYear(Integer.parseInt(scanner.nextLine()));
			System.out.println("Введите новую цену (текущая: " + car.getPrice() + "):");
			car.setPrice(Double.parseDouble(scanner.nextLine()));

			carRepository.save(car);
			System.out.println("Автомобиль обновлен.");
		} else {
			System.out.println("Автомобиль с таким id не найден.");
		}
	}

	private void deleteCar(Scanner scanner) {
		System.out.println("Введите id автомобиля для удаления:");
		Long id = Long.parseLong(scanner.nextLine());
		if (carRepository.existsById(id)) {
			carRepository.deleteById(id);
			System.out.println("Автомобиль удален.");
		} else {
			System.out.println("Автомобиль с таким id не найден.");
		}
	}

	private void searchByBrand(Scanner scanner) {
		System.out.println("Введите марку для поиска:");
		String brand = scanner.nextLine();
		List<Car> cars = carRepository.findByBrand(brand);
		if (cars.isEmpty()) {
			System.out.println("Автомобили с такой маркой не найдены.");
		} else {
			cars.forEach(car -> System.out.println(car));
		}
	}
}
