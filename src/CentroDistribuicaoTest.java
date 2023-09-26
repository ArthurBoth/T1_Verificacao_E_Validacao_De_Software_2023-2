import org.example.CentroDistribuicao;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class CentroDistribuicaoTest {
    // Aditivo 500; Alcool 2500; Gasolina 10000;
    @ParameterizedTest
    @CsvSource ({
                    "405, 9131, 1877, COMUM, 6881, 0, 385, 2740, 1408",
                    "304, 9466, 859, COMUM, 6856, 0, 289, 2840, 645",
                    "487, 6226, 448, COMUM, 4494, -14, 463, 1868, 336",
                    "442, 4985, 1577, COMUM, 3905, 0, 420, 1496, 1183",
                    "348, 4238, 1228, COMUM, 3291, 0, 331, 1272, 921",
                    "381, 3345, 43, COMUM, 2371, -14, 362, 1004, 33",
                    "396, 2299, 1732, COMUM, 2062, -14, 377, 690, 1299",
                    "468, 560, 1025, COMUM, 671, -14, 445, 168, 769",
                    "476, 2403, 432, COMUM, 1813, -14, 453, 721, 324",
                    "213, 7589, 1281, COMUM, 5643, 0, 203, 2277, 961",
                    "192, 5849, 886, COMUM, 4325, 0, 183, 1755, 665",
                    "220, 9496, 91, COMUM, 6680, -14, 209, 2849, 69",
                    "201, 4251, 2335, COMUM, 3569, 0, 191, 1276, 1752",
                    "168, 4810, 1008, COMUM, 3627, 0, 160, 1443, 756",
                    "208, 3417, 187, COMUM, 2449, -14, 198, 1026, 141",
                    "233, 542, 2030, COMUM, 898, -14, 222, 163, 1523",
                    "227, 589, 1061, COMUM, 688, -14, 216, 177, 796",
                    "214, 1820, 156, COMUM, 1323, -14, 204, 546, 117",
                    "16, 6015, 1448, COMUM, 4573, -14, 16, 1805, 1086",
                    "90, 6988, 704, COMUM, 5072, -14, 86, 2097, 528",
                    "48, 5551, 471, COMUM, 4005, -14, 46, 1666, 354",
                    "68, 4916, 1883, COMUM, 3915, -14, 65, 1475, 1413",
                    "73, 3833, 760, COMUM, 2876, -14, 70, 1150, 570",
                    "11, 3441, 483, COMUM, 2530, -14, 11, 1033, 363",
                    "51, 588, 1296, COMUM, 738, -14, 49, 177, 972",
                    "5, 918, 736, COMUM, 826, -14, 5, 276, 552",
                    "65, 1223, 560, COMUM, 999, -14, 62, 367, 420",
                    "251, 8214, 1392, ESTRATEGICO, 6110, 0, 239, 2465, 1044",
                    "346, 8709, 939, ESTRATEGICO, 6348, 0, 329, 2613, 705",
                    "292, 5177, 325, ESTRATEGICO, 3719, 0, 278, 1554, 244",
                    "364, 4946, 1478, ESTRATEGICO, 3849, 0, 346, 1484, 1109",
                    "436, 4129, 879, ESTRATEGICO, 3131, 0, 415, 1239, 660",
                    "420, 4557, 209, ESTRATEGICO, 3263, 0, 399, 1368, 157",
                    "327, 1339, 1357, ESTRATEGICO, 1292, 0, 311, 402, 1018",
                    "334, 2432, 898, ESTRATEGICO, 1943, 0, 318, 730, 674",
                    "266, 2301, 439, ESTRATEGICO, 1733, 0, 253, 691, 330",
                    "132, 7863, 2218, ESTRATEGICO, 6065, 0, 126, 2359, 1664",
                    "232, 6908, 1041, ESTRATEGICO, 5107, 0, 221, 2073, 781",
                    "233, 5357, 297, ESTRATEGICO, 3835, 0, 222, 1608, 223",
                    "236, 4272, 2324, ESTRATEGICO, 3583, 0, 225, 1282, 1743",
                    "143, 4727, 859, ESTRATEGICO, 3530, 0, 136, 1419, 645",
                    "150, 4319, 346, ESTRATEGICO, 3117, 0, 143, 1296, 260",
                    "211, 1730, 2412, ESTRATEGICO, 1824, 0, 201, 519, 1809",
                    "196, 1630, 899, ESTRATEGICO, 1375, 0, 187, 489, 675",
                    "231, 2241, 40, ESTRATEGICO, 1590, 0, 220, 673, 30",
                    "122, 5880, 1617, ESTRATEGICO, 4526, 0, 116, 1764, 1213",
                    "44, 6998, 701, ESTRATEGICO, 5076, 0, 42, 2100, 526",
                    "53, 7160, 333, ESTRATEGICO, 5097, 0, 51, 2148, 250",
                    "14, 4833, 1938, ESTRATEGICO, 3868, 0, 14, 1450, 1454",
                    "26, 4772, 810, ESTRATEGICO, 3544, 0, 25, 1432, 608",
                    "79, 3145, 293, ESTRATEGICO, 2278, 0, 76, 944, 220",
                    "124, 118, 1802, ESTRATEGICO, 539, 0, 118, 36, 1352",
                    "7, 880, 762, ESTRATEGICO, 806, 0, 7, 264, 572",
                    "68, 1907, 309, ESTRATEGICO, 1415, 0, 65, 573, 232",
                    "38, 600, 207, COMUM, 5000, -21, 37, 180, 156",
                    "4, 4, 4, COMUM, -1, -7, 4, 2, 3"
    })
    void encomendaCombustivelTest(
            int adt, int gas, int alc, CentroDistribuicao.TIPOPOSTO tipo,
            int pedido,
            int esperadoERR, int esperadoADT, int esperadoALC, int esperadoGAS
                                  ){
        CentroDistribuicao cd = new CentroDistribuicao(adt,gas,alc);

        int[] arr = cd.encomendaCombustivel(pedido, tipo);
        assertEquals(esperadoERR, arr[0]);
        assertEquals(esperadoADT, arr[1]);
        assertEquals(esperadoGAS, arr[2]);
        assertEquals(esperadoALC, arr[3]);
    }
}
