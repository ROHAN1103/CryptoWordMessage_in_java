import java.io.*;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.lang.*;

public class crypto {
    static int code;

    static int gettime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("mm");
        DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("ss");
        String a = dtf.format(now);
        String b = dtf1.format(now);
        String c = dtf2.format(now);
        String d = dtf3.format(now);
        int date = Integer.parseInt(a);
        int hour = Integer.parseInt(b);
        int min = Integer.parseInt(c);
        int sec = Integer.parseInt(d);
        int sum = (sec + (min * (sec % 5)) + hour) / date;
        return sum;

    }

    static int adder(int x, int otp) {
        int sum = x + otp;
        return sum;
    }

    public static String toString(char a[]) {
        String str = new String(a);
        return str;
    }

    static String encode(String msg) {
        int min1 = 33, max1 = 57;
        Random random = new Random();
        int ran1 = (random.nextInt(max1 - min1 + 1) + min1);
        int otp1 = gettime() + 33;
        char cry2[] = new char[msg.length() + 2];
        int len = msg.length() / 3;
        for (int i = 0; i < msg.length() + 1; i++) {
            if (i == len) {
                cry2[i] = (char) otp1;
                cry2[i + 1] = (char) ran1;
            } else if (i < len) {
                char temp = msg.charAt(i);
                int temp2 = temp;
                int temp3 = adder(temp2, otp1);
                int temp4 = temp3 - ran1;
                cry2[i] = (char) temp4;
            }

            else if (i > len) {
                char temp = msg.charAt(i - 1);
                int temp2 = temp;
                int temp3 = adder(temp2, otp1);
                int temp4 = temp3 - ran1;
                cry2[i + 1] = (char) temp4;
            }

        }
        String ret = toString(cry2);
        return ret;
    }

    static String decode(String msg) {
        int pos = (msg.length() - 3) / 3;
        char word[] = new char[msg.length()];
        char decode[] = new char[msg.length() - 2];
        for (int i = 0; i < msg.length(); i++) {
            word[i] = msg.charAt(i);
        }
        int code1 = word[pos];
        int code2 = word[pos + 1];
        for (int j = 0; j < msg.length(); j++) {
            if (j == pos) {
                j = j + 2;
            }
            if (j < pos) {
                int x=word[j]-code1+code2;
                decode[j] = (char)x;
            }
            if (j > pos) {
                int x=word[j]-code1+code2;
                decode[j - 2] = (char)x;
            }

        }
        
        String ret = toString(decode);

        return ret;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n##############################################");
        System.out.println("######                                 #######");
        System.out.println("######    1.encode        2.decode     #######");
        System.out.println("######                                 #######");
        System.out.println("##############################################");
        System.out.print("Enter your Option : ");
        int choice = sc.nextInt();
        if (choice == 1) {
            Scanner sc1=new Scanner(System.in);
            System.out.print("\nEnter your Message : ");
            String msg = sc1.nextLine();
            System.out.println("\nyour message is : " + msg);
            String encoded = encode(msg);
            System.out.println("Your encoded Message is : " + encoded);
        }
        if (choice == 2) {
             Scanner sc2=new Scanner(System.in);
            System.out.print("Enter your Encoded Message : ");
            String dec = sc2.nextLine();
            System.out.println("Your Message is : " + dec);
            String decoded = decode(dec);
            System.out.println("Your decoded Message is : " + decoded);
        }

    }
}
