package TestProfiStaff;

import java.util.Scanner;

public class Main {
	private static DBController db = new DBController();
    public static void main(String[] args) {

	    Scanner scanner = new Scanner(System.in);
	    boolean exit = false;
	    do {
	    	printMenu();
			switch (scanner.nextInt()) {
				case 1 -> addStudent();
				case 2 -> {
					System.out.println("Введите ID студента для удаления:");
					int id = scanner.nextInt();
					db.delete(id);
				}
				case 3 -> db.selectAll();
				case 4 -> exit = true;
			}
		} while (!exit);
    }

    public static void printMenu() {
    	System.out.println("\nВыберите действие:\n" +
							"1. Добавить студента\n" +
							"2. Удалить студента\n" +
							"3. Вывести список студентов\n" +
							"4. Закончить работу\n");
	}

	public static void addStudent() {
    	Scanner scanner = new Scanner(System.in);

		int id = db.getMaxId() + 1;
		System.out.println("Введите фамилию: ");
		String surname = scanner.next();
		System.out.println("Введите имя: ");
		String name = scanner.next();
		System.out.println("Введите отчество: ");
		String fname = scanner.next();
		System.out.println("Введите дату рождения: ");
		String birthday = scanner.next();
		System.out.println("Введите группу: ");
		String group = scanner.next();
		db.insert(id, surname, name, fname, birthday, group);
	}
}
