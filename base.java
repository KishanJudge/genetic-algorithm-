import java.util.Random;


public class base {
    
    public static void main(String[] args) {
        String[] population = createPopulation();

        for (int x = 0; x < 100; x++) {
            System.out.println(population[x]);
        }

       
        
    }

    public static String[] createPopulation() {
        //create a list of a hundred randomly generated bitstrings of length 𝑛 called population
        Random random = new Random();
        int popSize = 100;
        String[] population = new String[popSize];
        for (int index = 0; index < population.length; index++ ) {
            String bitstring = new String();
            for (int x = 0; x < 8; x++) {
                int bit = random.nextInt(2);
                bitstring = bitstring + String.valueOf(bit);
            }
            population[index] = bitstring;
        }
        return population;
    }
}

//fortnite