package unpackingString;

import unpackingString.exception.UnpackingException;

import java.util.Scanner;

public class UnpackingStringService {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Введите строку для распаковки (для завершения введите пустую строку)");
            String inputString = scanner.nextLine();
            if (inputString.isEmpty())
                break;

            try {
                System.out.println(UnpackingString.checkValidAndUnpackString(inputString));
            } catch (UnpackingException exception) {
                System.out.println(exception.getErrorCode() + " " + exception.getMessage());
            }
        } while (true);
    }

}
