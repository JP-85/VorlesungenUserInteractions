//package exercise_1_2.me;
//
//import java.util.Arrays;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Param<Integer> alpha = new Param<>("alpha","-a", "--alpha");
//        Param<Float> beta = new Param<>("beta","-b", "--beta");
//        Param<String> gamma = new Param<>("gamma","-c", "--gamma");
//
//        alpha.setValue(5);
//        System.out.println(alpha.isType(Integer.class));
//        beta.setValue((float) 5.3);
//        System.out.println(beta.isType(Float.class));
//        gamma.setValue("bla");
//        System.out.println(gamma.isType(String.class));
//    }
//
//    public int argumentIndex(String[] args) {
//        int foundIndex;
//        int foundIndex1 = Arrays.asList(args).indexOf(shortName);
//        int foundIndex2 = Arrays.asList(args).indexOf(longName);
//
//        boolean noneFound = (foundIndex1 == -1) && (foundIndex2 == -1);
//        boolean bothFound = (foundIndex1 != -1) && (foundIndex2 != -1);
//
//        if (!noneFound && !bothFound) {
//            foundIndex = Math.max(foundIndex1, foundIndex2);
//        } else {
//            foundIndex = -1;
//            return foundIndex;
//        }
//
//    }
//}
