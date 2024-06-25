package socketjava;

/**
 *
 * @author danielsantos
 */
import java.util.Random;

public class GeradorNomeAnimal {
    private static final String[] NOMES_ANIMAIS = {
        "Leão", "Tigre", "Urso", "Lobo", "Raposa",
        "Águia", "Tubarão", "Golfinho", "Elefante", "Leopardo",
        "Pantera", "Guepardo", "Jaguar", "Cavalo", "Falcão"
    };

    private static final Random RANDOM = new Random();

    public static String getNomeAleatorioAnimal() {
        int index = RANDOM.nextInt(NOMES_ANIMAIS.length);
        return NOMES_ANIMAIS[index];
    }
}

