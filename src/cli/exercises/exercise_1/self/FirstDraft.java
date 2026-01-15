//package exercise_1_2.me;
//
//import java.util.Arrays;
//
//public class FirstDraft {
//    //    Write a program that accepts the three parameters:
////            → -a, --alpha (int)
////→             -b, --beta (float)
////→             -g, --gamma (String)
////      ▶ Output an error when:
////            → A value is not preceded by a known parameter name
////→ A parameter is not followed by a value (except when type String)
////→ The value cannot be converted into the expected type
////▶ Implement a function that:
////            → Returns whether a specific parameter was set
////→ Returns the value of a specific parameter
////▶ Finally output all parameters that were set
//
//    public static void main(String[] args) throws Exception {
//
//        if (args.length == 0) {
//            System.out.println("No Arguments given.");
//        } else if (checkParam(args)) {
//            if (indexAlpha(args) > -1) {
//                System.out.println(args[indexAlpha(args) - 1]);
//                System.out.println(args[indexAlpha(args)]);
//                System.out.println();
//            }
//            if (indexBeta(args) > -1) {
//                System.out.println(args[indexBeta(args) - 1]);
//                System.out.println(args[indexBeta(args)]);
//                System.out.println();
//            }
//            if (indexGamma(args) > -1) {
//                System.out.println(args[indexGamma(args) - 1]);
//                System.out.println(args[indexGamma(args)]);
//                System.out.println();
//            }
//        }
//    }
//
//    public static boolean checkParam(String[] args) {
//        return args.length % 2 == 0;
//    }
//
//    public static void illegalArguments(String e) throws Exception {
//        throw new Exception(e);
//    }
//
//    public static int argExists(String[] args, String arg1, String arg2) {
//        int foundIndex;
//        int foundIndex1 = Arrays.asList(args).indexOf(arg1);
//        int foundIndex2 = Arrays.asList(args).indexOf(arg2);
//
//        boolean noneFound = (foundIndex1 == -1) && (foundIndex2 == -1);
//        boolean bothFound = (foundIndex1 != -1) && (foundIndex2 != -1);
//
//        if (!noneFound && !bothFound) {
//            foundIndex = Math.max(foundIndex1, foundIndex2);
//        } else {
//            foundIndex = -1;
//        }
//        return foundIndex;
//    }
//
//    public static int indexAlpha(String[] args) throws Exception {
//        int argIndex = argExists(args, "-a", "--alpha");
//
//        if (argIndex > -1) {
//            try {
//                Integer.parseInt(args[argIndex + 1]);
//                argIndex = argIndex + 1;
//            } catch (Exception e) {
//                illegalArguments("Wrong alpha!");
//            }
//        }
//        return argIndex;
//    }
//
//    public static int indexBeta(String[] args) throws Exception {
//        int argIndex = argExists(args, "-b", "--beta");
//
//        if (argIndex > -1) {
//            try {
//                Float.parseFloat(args[argIndex + 1]);
//                argIndex = argIndex + 1;
//            } catch (Exception e) {
//                illegalArguments("Wrong beta!");
//            }
//        }
//        return argIndex;
//    }
//
//    public static int indexGamma(String[] args) throws Exception {
//        int argIndex = argExists(args, "-c", "--gamma");
//
//        if (argIndex > -1) {
//            if (args.length > argIndex + 1) {
//                argIndex = argIndex + 1;
//            } else {
//                illegalArguments("wrong Gamma");
//            }
//        }
//        return argIndex;
//    }
//
////    Based on the previous task:
////            → Add a parameter -h, --help (String)
////→ When that parameter is set:
////            ▶ Output a help text for all parameters if the value is empty
////▶ Output a help text for the requested parameter if the value is not empty
////▶ Output an error when the value is not matching the name of any parameter
////→ Don’t produce any further output
////-a, --alpha int Example parameter 1
////            -b, --beta float Example parameter 2
////            -g, --gamma String Example parameter 3
//
//
//
//
//}
