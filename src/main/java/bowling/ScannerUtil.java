package bowling;

import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String readLine(){
       return SCANNER.nextLine().trim();
    }
}
