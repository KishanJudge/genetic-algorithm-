import java.util.Random;


public class base {
    
    public static void main(String[] args) {
        int bitstringSize = 8; // length 𝑛
        String[] population = createPopulation(bitstringSize);

        /*for (int x = 0; x < 100; x++) {
            System.out.println(population[x]);
        }*/

       
        
    }

    public static String[] createPopulation(int bitstringSize) {
        //create a list of a hundred randomly generated bitstrings of length 𝑛 called population
        Random random = new Random();
        int populationSize = 100;
        int populationCheck = 0;
        String[] population = new String[populationSize];
        do{
            for (int index = 0; index < population.length; index++ ) {
                String bitstring = new String();
                for (int x = 0; x < bitstringSize; x++) {
                    int bit = random.nextInt(2);
                    bitstring = bitstring + String.valueOf(bit);
                }
                if (bitstring.equals("11111111")) {
                    populationCheck++;
                }
                population[index] = bitstring;
            }
        } while (populationCheck == 100);

        return population;
    }
}

//fortnite