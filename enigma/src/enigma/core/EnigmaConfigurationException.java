package enigma.core;

public class EnigmaConfigurationException extends RuntimeException {
    EnigmaConfigurationException(){
        super("Please configure enigma correctly\n"
                +"Must pick 6 rotors(1~8)\n"
                +"One reflector(1~3)\n"
                +"An initial permutation of 3 characters");
    }
}
