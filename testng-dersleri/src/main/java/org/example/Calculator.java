import java.util.Scanner;

public class Calculator {

    // Toplama işlemi
    public static double add(double x, double y) {
        return x + y;
    }

    // Çıkarma işlemi
    public static double subtract(double x, double y) {
        return x - y;
    }

    // Çarpma işlemi
    public static double multiply(double x, double y) {
        return x * y;
    }

    // Bölme işlemi
    public static double divide(double x, double y) {
        if (y == 0) {
            throw new IllegalArgumentException("Bölen sıfır olamaz!");
        }
        return x / y;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Yapmak istediğiniz işlemi seçin:");
        System.out.println("1. Toplama");
        System.out.println("2. Çıkarma");
        System.out.println("3. Çarpma");
        System.out.println("4. Bölme");

        while (true) {
            System.out.print("Seçiminizi girin (1/2/3/4): ");
            String choice = scanner.next();

            if (choice.equals("1") || choice.equals("2") || choice.equals("3") || choice.equals("4")) {
                System.out.print("İlk sayıyı girin: ");
                double num1 = scanner.nextDouble();
                System.out.print("İkinci sayıyı girin: ");
                double num2 = scanner.nextDouble();

                switch (choice) {
                    case "1":
                        System.out.println(num1 + " + " + num2 + " = " + add(num1, num2));
                        break;
                    case "2":
                        System.out.println(num1 + " - " + num2 + " = " + subtract(num1, num2));
                        break;
                    case "3":
                        System.out.println(num1 + " * " + num2 + " = " + multiply(num1, num2));
                        break;
                    case "4":
                        try {
                            System.out.println(num1 + " / " + num2 + " = " + divide(num1, num2));
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                }

                System.out.print("Başka bir işlem yapmak istiyor musunuz? (evet/hayır): ");
                String nextCalculation = scanner.next();
                if (!nextCalculation.equalsIgnoreCase("evet")) {
                    break;
                }
            } else {
                System.out.println("Geçersiz giriş, lütfen tekrar deneyin.");
            }
        }

        scanner.close();
    }
}
