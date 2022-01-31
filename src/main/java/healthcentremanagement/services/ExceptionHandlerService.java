package healthcentremanagement.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ExceptionHandlerService {
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LocalDate checkIfDateEnteredCorrect(){
        try{
            return LocalDate.parse(scanner.nextLine(), formatter);
        } catch (DateTimeParseException dateTimeParseException){
            System.out.println("Wrong date format entered");
            return null;
        }
    }

    public LocalTime checkIfTimeEnteredCorrect(){
        try {
            return LocalTime.parse(scanner.nextLine());
        } catch (DateTimeParseException dateTimeParseException){
            System.out.println("Wrong time format entered");
            return null;
        }
    }
}
