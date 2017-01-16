import exception.RTLogUtilException;
import gui.RTLogDeveloperGUI;
import model.RTLogRecord;
import util.RTLogUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RTLogDeveloperApplication {

    public RTLogDeveloperApplication(String[] args) {
        final RTLogUtil util = new RTLogUtil();
        final Stack<String> arguments = getArgumentStack(args);
        final String file = arguments.pop();
        final List<RTLogRecord> records = util.parseFile(file);

        while (!arguments.empty()) {
            try {
                final String argument = arguments.pop();
                switch (argument) {
                    case "--findField":
                        System.out.println(util.toJson(util.findField(arguments.pop(), records)));
                        break;
                    case "--diff":
                        System.out.println(util.toJson(util.calculateDiff(file, arguments.pop())));
                        break;
                    case "--fields":
                        System.out.println(util.getFields(arguments.pop()));
                        break;
                    case "--findContent":
                        System.out.println(util.toJson(util.findContent(arguments.pop(), records)));
                        break;
                    case "--print":
                        System.out.println(util.toJson(records));
                        break;
                    case "--modify":
                        util.modify(records, arguments.pop(), arguments.pop(), arguments.pop());
                        break;
                    default:
                        System.out.println("Flag [" + argument + "] is not supported and therefore ignored.");
                }
            } catch(RTLogUtilException e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        }
    }

    private Stack<String> getArgumentStack(String[] args) {
        List<String> strings = Arrays.asList(args);
        Collections.reverse(strings);
        return new Stack<String>(){{addAll(strings);}};
    }

    public static void main(String[] args) {
        if(args[0].toLowerCase().equals("gui")) {
            new RTLogDeveloperGUI(args);
        } else {
            new RTLogDeveloperApplication(args);
        }

    }
}
