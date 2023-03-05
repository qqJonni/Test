import java.util.Scanner;                         // Импортируем дерективу для ввода данных с клавиатуры

public class Calculator{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);       // Создаём объект scanner для входного потока System.in
        Main result = new Main();                       // Создаём объект result класса Main
        System.out.println("Input:");                   // Запрос ввода выражения
        String expression = scanner.nextLine();         // Ввод выражения c методом nextLine (позволяет принимать в input выражение в виде целой строки)
        String answer = result.calc(expression);        // Объект answer выводит результат из класса Main через метод calc(expression), обрабатывая введённые данные

        System.out.println("Output:\n" + answer);        // Выводим ответ через \n для переноса строки
    }
}class Main{                                             // Создаём класс Main
    public static String calc(String input){             // Создаём метод calc
        boolean arabOrRoman = false;                      // Определяем какие числа введены (арабские или римские)
        String exception = "throws Exception";            // Создаём объект exception для вывода при неверном вводе данных
        Main romanCheck = new Main();                     // Создаём объект romanCheck для проверки и перевода из римских в арабские
        Main arabToRoman = new Main();                    // Для перевода ответа в римские
        int result = 0;                                   // Считает выражение
        String[] inputSplit = input.split(" ");     // Создаём массив inputSplit c разделителем split
        if (inputSplit.length != 3){                      // Если не 3 элемента, возвращаем "throws Exception"
            return exception;                             // Ловим, если не 3 элемента
        }
        Integer firstNumber = 0;                          // Создаём объект firstNumber
        Integer secondNumber = 0;                         // Создеём объект secondNumber
        try {                                             // Делаем исключения
            firstNumber = Integer.valueOf(inputSplit[0]);  // Преобразуем из строки в число методом valueOf первое число введенного значения
            secondNumber = Integer.valueOf(inputSplit[2]); // Преобразуем из строки в число методом valueOf второе число введенного значения
        } catch (NumberFormatException e) {                          // Обрабатываем исключения
            try {                                                    // Делаем исключение
                firstNumber = romanCheck.romanToArab(inputSplit[0]); // Проверяем арабское или римское первое число введенного значения
                secondNumber = romanCheck.romanToArab(inputSplit[2]);// Проверяем арабское или римское второе число введенного значения
                arabOrRoman = true;                                  // Если true, выполняем операцию
            } catch (NumberFormatException ex) {                     // Обрабатываем исключение
                return exception;                                    // Если false, выводим "throws Exception"
            }
        }
        if ((firstNumber < 1) || (firstNumber > 10) || (secondNumber < 1) || (secondNumber > 10)){ // Указываем диапазон значений
            return exception;                                                                      // Если false, выводим "throws Exception"
        }
        String sign = inputSplit[1];                            // Создаём объект sign, присваиваем значение 2 объекта в ведённом значении
        switch (sign) {                                         // Создаём оператор выбора switch для объекта sign
            case "+" -> result = firstNumber + secondNumber;    // Через лямбда выражение проверяем +
            case "-" -> result = firstNumber - secondNumber;    // Через лямбда выражение проверяем -
            case "*" -> result = firstNumber * secondNumber;    // Через лямбда выражение проверяем *
            case "/" -> result = firstNumber / secondNumber;    // Через лямбда выражение проверяем /
            default -> {
                return exception;                                // Если знак другой, выводим "throws Exception"
            }
        }

        String output;                                               // Наш вывод

        if (arabOrRoman){
            if(result < 1){                                        // Если результат римских чисел <1, выводим "throws Exception"
                return exception;
            } else {
                output = arabToRoman.arabToRome(result);           // Выводим результат вычисления в римских
            }
        } else {
            output = Integer.toString(result);                     // Выводим результат вычисления в арабских
        }
        return output;
    }
    Integer romanToArab(String romanInput){                            // Переводим римский ввод в арабский
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());  // Исключаем посчитанные числа
            }
        }
        return result;
    }
    String arabToRome(int arabInput){                                  // Перевод арабские в римские
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}
