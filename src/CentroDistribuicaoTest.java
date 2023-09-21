import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CentroDistribuicaoTest {

    @org.junit.jupiter.api.Test
    void defineSituacao() {
    }

    // Aditivo 500; Alcool 2500; Gasolina 10000;
    @CsvSource({

    })
    void encomendaCombustivelTest(int adt, int alc, int gas, int recebido, int esperadoADT,
                                  int esperadoALC, int esperadoGAS, int esperadoERR,
                                  CentroDistribuicao.TIPOPOSTO tipo){
        CentroDistribuicao cd = new CentroDistribuicao(adt,gas,alc);

        int[] arr = cd.encomendaCombustivel(recebido, tipo);
        assertEquals(arr[0], esperadoERR);
        assertEquals(arr[1], esperadoADT);
        assertEquals(arr[2], esperadoGAS);
        assertEquals(arr[3], esperadoALC);
    }

}