package enigma.core;

public class Enigma {

    String[] rotors;
    String reflector;
    String initialPermutation;
    int[][] arrowCombination;

    static Enigma enigma=null;

    private Enigma(){
        System.out.println("Lazy enigma\nMust be set for operation");
    }

    private Enigma(String[] rotors, String reflector, String initialPermutation,int[][] arrowCombination)
            throws EnigmaConfigurationException {
        if(rotors.length!=6
                || reflector==null
                || initialPermutation.length()!=3)
            throw new EnigmaConfigurationException();
        this.rotors = rotors;
        this.reflector = reflector;
        this.initialPermutation = initialPermutation;
        this.arrowCombination = arrowCombination;
    }

    public static Enigma getEnigma(String[] rotors,
                            String reflector,
                            String initialPermutation,
                            int[][] arrowCombination) {
        if(enigma==null)
            enigma = new Enigma(rotors, reflector, initialPermutation,arrowCombination);
        return enigma;
    }

    public static Enigma getEnigma() throws EnigmaInstanceException{
        enigma = new Enigma();
        //if(enigma==null) throw new EnigmaInstanceException();
        return enigma;
    }

    public static Enigma setEnigma(String[] rotors, String reflector, String initialPermutation,int[][] arrowCombination){
        enigma.rotors = rotors;
        enigma.reflector = reflector;
        enigma.initialPermutation = initialPermutation;
        enigma.arrowCombination = arrowCombination;
        return enigma;
    }

}
