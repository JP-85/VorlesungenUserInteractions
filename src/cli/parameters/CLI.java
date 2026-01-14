package cli.parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLI {
    public static void main(String[] args) throws IOException {
//        String filename = args[0];
//        System.out.println(filename);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter your name:");
        String name = br.readLine();
        System.out.println("Welcome " + name);


    }
}
