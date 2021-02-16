package functionalinterface;

public class _Consumer {
    public static void main(String[] args) {

    }

    static void greetCostumer(Custumer custumer) {
        System.out.println("Hello" + custumer.name
                + "thank for registering the phone number "
                + custumer.phoneNumber);
    }

    static class Custumer {
        private final String name;
        private final String phoneNumber;

        Custumer(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }
    }
}
