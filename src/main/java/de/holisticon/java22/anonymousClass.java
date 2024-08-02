import de.holisticon.java22.engine.*;

import java.util.List;
import java.util.stream.Gatherers;

void main() {

    /*
    Java 22 preview String Template Processor https://openjdk.org/jeps/430
     */
    stringFormatExample();

    /*
    Java 22 preview Unnamed Patterns and Variables: https://openjdk.org/jeps/443
     */
    unnamedVariableExample();
    unnamedPatternExample();

    /*
    Java 21 release Pattern Matching for switch: https://openjdk.org/jeps/441
     */
    coolerSwitchStatementsExample();

    /*
    Java 22 preview Stream Gatherers: https://openjdk.org/jeps/461
     */
    stringGathererExample();
}

void stringFormatExample() {
    System.out.println("####################");
    String name = "Holisticon";
    String welcomeText = STR."Welcome to \{name}";
    System.out.println(welcomeText);
}


void coolerSwitchStatementsExample() {
    System.out.println("####################");
    System.out.println(coolerSwitchStatements(null));
    System.out.println(coolerSwitchStatements("Yes"));
    System.out.println(coolerSwitchStatements("No"));
    System.out.println(coolerSwitchStatements("What now?"));
}

String coolerSwitchStatements(String input) {
    String output = null;
    switch (input) {
        case null -> output = "Oops, null";
        case String s when "Yes".equalsIgnoreCase(s) -> output = "It's Yes";
        case String s when "No".equalsIgnoreCase(s) -> output = "It's No";
        case String s -> output = "Try Again";
    }
    return output;
}


void unnamedVariableExample() {
    System.err.println("####################");
    try {
        final var string = "xyz";
        final int number = Integer.parseInt(string);
    } catch (final NumberFormatException _) {
        System.err.println("Not a number");
    }
}


void unnamedPatternExample() {
    System.out.println("####################");
    final Object gasCar = new Car<>("Dodge", "red", new GasEngine());
    final Object electricCar = new Car<>("BYD", "white", new ElectricEngine());
    final Object hybridCar = new Car<>("Cupe", "blue", new HybridEngine());

    final var cars = List.of(gasCar, electricCar, hybridCar);

    cars.forEach(carObject -> {
        System.out.println(STR."The \{getObjectsNameWithUnnamedPattern(carObject)} is \{getObjectsColorWithUnnamedPattern(carObject)}");
    });

}

String getObjectsColor(Object object) {
    if (object instanceof Car(String name, String color, Engine engine)) {
        return color;
    }
    return "No color!";
}

String getObjectsColorWithUnnamedPattern(Object object) {
    if (object instanceof Car(_, String color, _)) {
        return color;
    }
    return "No color!";
}

String getObjectsNameWithUnnamedPattern(Object object) {
    if (object instanceof Car(String name, _, _)) {
        return name;
    }
    return "No name!";
}


void stringGathererExample(){
    System.out.println("####################");
    final List<String> words = List.of("We", "did", "it", "because", "we", "felt", "like", "it");
    final List<List<String>> groups = groupsOfThree(words);
    System.out.println(groups);
}


public List<List<String>> groupsOfThree(List<String> words) {
    return words.stream()
            .gather(Gatherers.windowFixed(3))
            .toList();
}