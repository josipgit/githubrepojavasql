package J27LabelaContinuePrimjer;

class LabelContinuePrimjer {
    public static void main(String[] args) {
        vanjskaPetlja:  // labela "vanjskaPetlja"
        for (int i = 1; i <= 3; i++) {
            unutarnjaPetlja:
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    continue vanjskaPetlja;  // da je samo continue skocilo bi na unutranju petlju
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }
    }
}