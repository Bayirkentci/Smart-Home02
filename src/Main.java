import device.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SmartHomeSystem system = new SmartHomeSystem();

        int q = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < q; i++) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            String command = parts[0];
            String result = "";

            switch (command) {
                case "add_device":
                    if (parts.length != 4) {
                        result = "invalid input";
                    } else {
                        result = system.addDevice(parts[1], parts[2], parts[3]);
                    }
                    break;

                case "set_device":
                    if (parts.length != 4) {
                        result = "invalid input";
                    } else {
                        result = system.setDevice(parts[1], parts[2], parts[3]);
                    }
                    break;

                case "remove_device":
                    if (parts.length != 2) {
                        result = "invalid input";
                    } else {
                        result = system.removeDevice(parts[1]);
                    }
                    break;

                case "list_devices":
                    result = system.listDevices();
                    break;

                case "add_rule":
                    if (parts.length != 4) {
                        result = "invalid input";
                    } else {
                        result = system.addRule(parts[1], parts[2], parts[3]);
                    }
                    break;

                case "check_rules":
                    if (parts.length != 2) {
                        result = "invalid input";
                    } else {
                        result = system.checkRules(parts[1]);
                    }
                    break;

                case "list_rules":
                    result = system.listRules();
                    break;

                default:
                    result = "invalid input";
            }

            System.out.println(result);
        }
    }
}