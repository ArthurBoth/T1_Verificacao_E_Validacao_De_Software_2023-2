public class CentroDistribuicao {
    public enum SITUACAO { NORMAL, SOBRAVISO, EMERGENCIA } 
    public enum TIPOPOSTO { COMUM, ESTRATEGICO }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    private static final int HALF_ADITIVO = (MAX_ADITIVO / 2); // 250
    private static final int HALF_ALCOOL = (MAX_ALCOOL / 2); // 1250
    private static final int HALF_GASOLINA = (MAX_GASOLINA / 2); // 5000
    
    private static final int QUARTER_ADITIVO = (MAX_ADITIVO / 4); // 125
    private static final int QUARTER_ALCOOL = (MAX_ALCOOL / 4); // 625
    private static final int QUARTER_GASOLINA = (MAX_GASOLINA / 4); // 2500

    private int currentAditivo;
    private int currentAlcool;
    private int currentGasolina;
    private SITUACAO situacao;

    public CentroDistribuicao (int tAditivo, int tGasolina, int tAlcool) throws IllegalArgumentException{
        this.currentAditivo = validValueAdt(tAditivo);
        this.currentAlcool = validValueAlc(tAlcool);
        this.currentGasolina = validValueGas(tGasolina);

        defineSituacao();
    } 
        
    public void defineSituacao(){
        if (currentAditivo >= HALF_ADITIVO && currentAlcool >= HALF_ALCOOL && currentGasolina >= HALF_GASOLINA) {
            this.situacao = SITUACAO.NORMAL;
            return;
        }

        if (currentAditivo < QUARTER_ADITIVO || currentAlcool < QUARTER_ALCOOL || currentGasolina < QUARTER_GASOLINA) {
            this.situacao = SITUACAO.EMERGENCIA;
            return;
        }

        this.situacao = SITUACAO.SOBRAVISO;
    } 

    public SITUACAO getSituacao(){
        return this.situacao;
    } 
    public int gettAditivo(){
        return this.currentAditivo;
    } 

    public int gettAlcool(){
        return this.currentAlcool;
    } 

    public int gettGasolina(){
        return this.currentGasolina;
    } 
    
    
    public int recebeAditivo(int qtdade) {
        int aux;
        int canReceive = (MAX_ADITIVO - currentAditivo);

        if (qtdade <= 0) {
            return -1;
        }

        if (canReceive < qtdade) {
            aux = MAX_ADITIVO - currentAditivo;
            currentAditivo = MAX_ADITIVO;
            return aux;
        }

        currentAditivo += qtdade;
        defineSituacao();
        return qtdade;
    } 

    public int recebeAlcool(int qtdade) {
        int aux;
        int canReceive = (MAX_ALCOOL - currentAlcool);

        if (qtdade <= 0) {
            return -1;
        }

        if (canReceive < qtdade) {
            aux = MAX_ALCOOL - currentAlcool;
            currentAlcool = MAX_ALCOOL;
            return aux;
        }

        currentAlcool += qtdade;
        defineSituacao();
        return qtdade;
    } 

    public int recebeGasolina(int qtdade) {
        int aux;
        int canReceive = (MAX_GASOLINA - currentGasolina);

        if (qtdade <= 0) {
            return -1;
        }

        if (canReceive < qtdade) {
            aux = MAX_GASOLINA - currentGasolina;
            currentGasolina = MAX_GASOLINA;
            return aux;
        }

        currentGasolina += qtdade;
        defineSituacao();
        return qtdade;
    } 

    // 5% de Aditivo, 25% de Álcool e 70% de Gasolina. Truncar para baixo o resultado final.
    // -7 se receber parametro inválido, -14 não atender pela situação e -21 se não tiver combustível suficiente
    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        int MAX_QUANTITY = 10000; // obtido através da fórmula abaixo
        /*
         * 10000 / 0,7 = 14285 (arredondado para baixo)
         * 2500 / 0,25 = 10000
         * 500 / 0,05 = 10000
         * 
         * Então o valor máximo de combustível que pode ser encomendado é 10000, pois é o menor valor obtido
         */

        double neededAtd = qtdade * 0.05;
        double neededAlc = qtdade * 0.25;
        double neededGas = qtdade * 0.70;
        
        int leftoverAtd;
        int leftoverAlc;
        int leftoverGas;

        int[] result = new int[4];

        if (qtdade < 0 || qtdade > MAX_QUANTITY){
            result[0] = -7;
            return result;
        }

        switch (this.situacao){
            case EMERGENCIA:
                if (tipoPosto == TIPOPOSTO.COMUM){
                    result[0] = -14;
                    return result;
                }

                neededAtd = neededAtd * 0.5;
                neededAlc = neededAlc * 0.5;
                neededGas = neededGas * 0.5;

                break;

            case SOBRAVISO:
                if (tipoPosto == TIPOPOSTO.COMUM){
                    neededAtd = neededAtd * 0.5;
                    neededAlc = neededAlc * 0.5;
                    neededGas = neededGas * 0.5;
                }

                break;

            default: // NORMAL
                break;
        }

        if (canDeliver(neededAtd, neededAlc, neededGas)){
            result[0] = -21;
            return result;
        }

        leftoverAtd = currentAditivo - (int) Math.floor(neededAtd);
        leftoverAlc = currentAlcool - (int) Math.floor(neededAlc);
        leftoverGas = currentGasolina - (int) Math.floor(neededGas);

        result[1] = leftoverAtd;
        result[2] = leftoverGas;
        result[3] = leftoverAlc;

        return result;
    }

    private boolean canDeliver(double neededAtd, double neededAlc, double neededGas){
        return !(currentAditivo < neededAtd || currentAlcool < neededAlc || currentGasolina < neededGas);
    }

    private int validValueAdt(int tAditivo) throws IllegalArgumentException{
        if (tAditivo < 0 || tAditivo > MAX_ADITIVO) {
            throw new IllegalArgumentException("Valor inválido para o tanque de aditivo");
        }
        
        return tAditivo;
    }

    private int validValueAlc(int tAlcool) throws IllegalArgumentException{
        if (tAlcool < 0 || tAlcool > MAX_ALCOOL) {
            throw new IllegalArgumentException("Valor inválido para o tanque de alcool");
        }

        return tAlcool;
    }

    private int validValueGas(int tGasolina) throws IllegalArgumentException{
        if (tGasolina < 0 || tGasolina > MAX_GASOLINA) {
            throw new IllegalArgumentException("Valor inválido para o tanque de gasolina");
        }

        return tGasolina;
    }
}