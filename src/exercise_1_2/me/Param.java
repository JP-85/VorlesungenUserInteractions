//package exercise_1_2.me;
//
//public class Param<T> {
//    private String shortName;
//    private String longName;
//    private T value;
//
//    public void setArgumentName(String argumentName) {
//        this.argumentName = argumentName;
//    }
//
//    public String getShortName() {
//        return shortName;
//    }
//
//    public String getLongName() {
//        return longName;
//    }
//
//    public T getValue() {
//        return value;
//    }
//
//    public void setValue(T value) {
//        this.value = value;
//    }
//
//
//    public Param(String argumentName, String shortName, String longName) {
//        this.shortName = shortName;
//        this.longName = longName;
//    }
//
//    public boolean isType(Class<T> type) {
//        return this.value.getClass().equals(type);
//    }
//
//    public boolean argumentExists(String[] args) {
//        return this.argumentIndex(args) > -1;
//    }
//}
