package enigma.core;

public class Enigma {

    public String[] rotors;
    public String reflector;
    public String initialPermutation;
    public int[] arrowPositions;


    public int inputIndex;
    public int rotor2index;
    public int rotor4index;
    public int rotor6index;
    public int reflectorInIndex;
    public int reflectorOutIndex;
    public int rotor5index;
    public int rotor3index;
    public int rotor1index;
    public int outputIndex;

    static Enigma enigma=null;

    private Enigma(){
        System.out.println("Lazy enigma\nMust be set for operation");
    }

    private Enigma(String[] rotors, String reflector, String initialPermutation,int[] arrowPositions)
            throws EnigmaConfigurationException {
        if(rotors.length!=6
                || reflector==null
                || initialPermutation.length()!=3)
            throw new EnigmaConfigurationException();
        this.rotors = rotors;
        this.reflector = reflector;
        this.initialPermutation = initialPermutation;
        this.arrowPositions = arrowPositions;
    }

    public static Enigma getEnigma(String[] rotors,
                            String reflector,
                            String initialPermutation,
                            int[] arrowPositions) {
        if(enigma==null)
            enigma = new Enigma(rotors, reflector, initialPermutation, arrowPositions);
        return enigma;
    }

    public static Enigma getEnigma() throws EnigmaInstanceException{
        enigma = new Enigma();
        //if(enigma==null) throw new EnigmaInstanceException();
        return enigma;
    }

    public static Enigma setEnigma(String[] rotors, String reflector, String initialPermutation,int[] arrowPositions){
        enigma.rotors = rotors;
        enigma.reflector = reflector;
        enigma.initialPermutation = initialPermutation;
        enigma.arrowPositions = arrowPositions;
        return enigma;
    }

}
