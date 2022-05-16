import java.io.IOException;
import java.util.Scanner;
public class main {
    public static void main(String[] args) throws ScannerException{
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(calc(input));
    }
    public static String calc(String input) throws ScannerException{
        String[] splitstr = input.split(" ");
        if(splitstr.length < 3){
            throw new ScannerException("// т.к. строка не является математической операцией");
        }
        if(splitstr.length > 3){
            throw new ScannerException("// т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+,-, *, *)");
        }
        try {
            int num1 = Integer.parseInt(splitstr[0]);
            int num2 = Integer.parseInt(splitstr[2]);
            if((num1 <1 || num1>10) || (num2 < 1 || num2 >10)){
                throw new ScannerException("// т.к. вы можете использовать числа от 1 до 10 включительно, не больше и не меньше.");
            }
            String operation = splitstr[1];
            int result = 0;
            switch (operation) {
                case ("+"):
                    result = num1 + num2;
                    break;
                case ("-"):
                    result = num1 - num2;
                    break;
                case ("*"):
                    result = num1 * num2;
                    break;
                case ("/"):
                    result = num1 / num2;
                    break;
                default:
                    throw new ScannerException("// т.к недопустимая арифметическая операция");
            }
            input = Integer.toString(result);
        } catch (NumberFormatException nft){
            try {
                RoumanNumber num1 = RoumanNumber.valueOf(splitstr[0]);
                RoumanNumber num2 = RoumanNumber.valueOf(splitstr[2]);
            } catch (IllegalArgumentException exception) {
                throw new ScannerException("т.к используются одновременно разные системы счисления или вы ввели дробное число.");
            }
            RoumanNumber num1 = RoumanNumber.valueOf(splitstr[0]);
            RoumanNumber num2 = RoumanNumber.valueOf(splitstr[2]);
            String operation = splitstr[1];
            if((num1.getConvertToArabian() <1 || num1.getConvertToArabian()>10) || (num2.getConvertToArabian() < 1 || num2.getConvertToArabian() >10)){
                throw new ScannerException("// т.к. вы можете использовать числа от 1 до 10 включительно, не больше и не меньше.");
            }
            int num = 0;
            switch (operation){
                case ("+"):
                    num = num1.getConvertToArabian() + num2.getConvertToArabian();
                    break;
                case ("-"):
                    num = num1.getConvertToArabian() - num2.getConvertToArabian();
                    break;
                case ("*"):
                    num = num1.getConvertToArabian() * num2.getConvertToArabian();
                    break;
                case ("/"):
                    num = num1.getConvertToArabian() / num2.getConvertToArabian();
                    break;
                default:
                    throw new ScannerException("// т.к недопустимая арифметическая операция");
            }
            if(num <= 0){
                throw new ScannerException("// т.к. в римской системе нет отрицательных чисел.");
            }
            for (RoumanNumber result : RoumanNumber.values()) {
                if ( (num -1) == result.ordinal()){
                    input = result.name();
                }
            }
        }
        return input;
    }
}