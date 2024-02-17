package meupneuronio;

import TrabalhaBits.NumeroBits;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class NeuronioAJC
        implements Serializable {

    private ArrayList<NumeroBits> entradasTreinoBits = new ArrayList<>();

    class valorEsperado implements Serializable {

        ArrayList<Double> valorEsperado = new ArrayList<>();
    }

    ArrayList<valorEsperado> valorEsperado = new ArrayList<>();

    private String ultimaRodada = "";

    private int nentradas = 0;
    private NeuronioPC[] neuroniosC1 = null;
    private NeuronioPC[] neuroniosC2 = null;
    private NeuronioPC neuronioPC0 = new NeuronioPC(0, true);
    private NeuronioCF[] neuronioCF = null;

    public int nBitsG;

    public void setValorTreino(int valor, double... valoreEsperado) {
        this.entradasTreinoBits.add(new NumeroBits(this.nBitsG, 2, valor, valoreEsperado));

    }

    public NumeroBits getValorTeste(int valor) {
        return (new NumeroBits(this.nBitsG, 2, valor, 0));

    }

    public NumeroBits getValorTesteCEsperado(int valor, double... valorEsperado) {
        return (new NumeroBits(this.nBitsG, 2, valor, valorEsperado));

    }

    public static int lenghtImagem(BufferedImage img) {

        System.out.println("Lenght " + (((java.awt.image.DataBufferInt) img.getRaster().getDataBuffer()).getData()).length);
        return (((java.awt.image.DataBufferInt) img.getRaster().getDataBuffer()).getData()).length;

    }

    public static int lenghtImagem(File valor) {
        try {
            return (((DataBufferByte) MeuAJC.image2BlackWhiteTest(ImageIO.read(valor)).getRaster().getDataBuffer()).getData()).length;
        } catch (IOException ex) {
            Logger.getLogger(NeuronioAJC.class.getName()).log(Level.SEVERE, (String) null, ex);

            return -1;
        }
    }

    public void setValorTreino(File valor, double... valoreEsperado) {
        try {
            byte[] dados = ((DataBufferByte) MeuAJC.image2BlackWhiteTest(ImageIO.read(valor)).getRaster().getDataBuffer()).getData();

            this.entradasTreinoBits.add(new NumeroBits(dados.length, 1, (byte[]) dados.clone()));

            for (int cont = 0; cont < valoreEsperado.length; cont++) {
                if (this.valorEsperado.size() - 1 < cont) {
                    this.valorEsperado.add(new valorEsperado());
                }
                ((valorEsperado) this.valorEsperado.get(cont)).valorEsperado.add(Double.valueOf(valoreEsperado[cont]));
            }

        } catch (IOException ex) {
            Logger.getLogger(NeuronioAJC.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

    public void setValorTreinoNovo(BufferedImage img, double... valoreEsperado) {

        int[] dados = (((java.awt.image.DataBufferInt) img.getRaster().getDataBuffer()).getData());

        this.entradasTreinoBits.add(new NumeroBits(dados.length, 1, (int[]) dados.clone(), valoreEsperado));

    }

    public void setValorTreinoNovo(File valor, double... valoreEsperado) {
        try { 
            
            
            byte[] dados = ((DataBufferByte) (ImageIO.read(valor)).getRaster().getDataBuffer()).getData();

            this.entradasTreinoBits.add(new NumeroBits(dados.length, 1, (byte[]) dados.clone(), valoreEsperado));

        } catch (IOException ex) {
            Logger.getLogger(NeuronioAJC.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
    }

    public NumeroBits getValorTreinoNovo(File valor) {
        try {
            byte[] dados = ((DataBufferByte) (ImageIO.read(valor)).getRaster().getDataBuffer()).getData();

            return (new NumeroBits(dados.length, 1, (byte[]) dados.clone(), 0));

        } catch (IOException ex) {
            Logger.getLogger(NeuronioAJC.class.getName()).log(Level.SEVERE, (String) null, ex);
        }
        return null;
    }

    public NumeroBits getValorTreinoNovo(BufferedImage img) {

        int[] dados = (((java.awt.image.DataBufferInt) img.getRaster().getDataBuffer()).getData());

        return (new NumeroBits(dados.length, 1, (int[]) dados.clone(), 0));
    }

    public NeuronioAJC(int nNeuronios, int nSizeBits) {
        this.nentradas = nNeuronios;
        this.nBitsG = nSizeBits;
        this.neuroniosC1 = new NeuronioPC[nNeuronios];
        for (int cont = 0; cont < this.neuroniosC1.length; cont++) {

            this.neuroniosC1[cont] = new NeuronioPC(cont, cont % 2 == 0);
            System.out.println("NeuronioAJ " + cont + " de:" + this.neuroniosC1.length);
        }
    }

    public NeuronioAJC(int nSizeBits) {

        this.nBitsG = nSizeBits;

    }

    /*
    public boolean treino(double[] valores) {
        boolean erro = false;
        int treinou = 0;

        double[] saidas = new double[this.neuroniosC1.length];
        do {
            System.out.println();
            erro = false;
            double[] arrayOfDouble = new double[this.nentradas];
            int i;
            for (i = 0; i < valores.length; i += this.nentradas + 1) {
                for (int v = 0; v < arrayOfDouble.length; v++) {
                    System.out.print(valores[i + v] + "-");
                    arrayOfDouble[v] = valores[i + v];
                }
                for (int a = 0; a < this.neuroniosC1.length; a++) {
                    if (!this.neuroniosC1[a].dentritos(this.nBitsG,
                            arrayOfDouble.clone()).saidaTreino(valores[i + this.nentradas])) {
                        erro = true;
                        this.neuroniosC1[a].corrigePesos(this.neuroniosC1[a].saida(), valores[i + this.nentradas]);
                        treinou++;

                        break;
                    }
                    saidas[a] = this.neuroniosC1[a].saida();
                }
            }
        } while (erro);

        System.out.println("Passou");

        do {
            erro = false;
            double[] arrayOfDouble = new double[this.nentradas];
            int i;
            for (i = 0; i < valores.length; i += this.nentradas + 1) {
                for (int v = 0; v < arrayOfDouble.length;) {
                    arrayOfDouble[v] = valores[i + v];
                    v++;
                }
                for (int a = 0; a < this.neuroniosC1.length; a++) {

                    saidas[a] = this.neuroniosC1[a].dentritos(this.nBitsG, arrayOfDouble.clone()).saida();
                    System.out.print(arrayOfDouble[0] + " " + arrayOfDouble[1] + " " + saidas[a] + " \n");
                }
                System.out.print(" \n" + this.neuronioPC0
                        .dentritos(this.nBitsG, saidas).saida() + " Valores " + valores[i + this.nentradas] + "\n");

                if (!this.neuronioPC0.dentritos(this.nBitsG, saidas.clone()).saidaTreino(valores[i + this.nentradas])) {
                    this.neuronioPC0.corrigePesos(this.neuronioPC0.saida(), valores[i + this.nentradas]);
                    treinou++;
                    erro = true;
                }

            }

        } while (erro);

        System.out.println("treinou " + treinou);

        double[] ventrada = new double[this.nentradas];
        int t;
        for (t = 0; t < valores.length; t += this.nentradas + 1) {
            System.out.print(" Entrada  ");

            for (int v = 0; v < ventrada.length; v++) {

                ventrada[v] = valores[t + v];

                System.out.print(" - " + ventrada[v]);
            }
            for (int a = 0; a < this.neuroniosC1.length; a++) {
                saidas[a] = this.neuroniosC1[a].dentritos(this.nBitsG, ventrada.clone()).saida();
            }

            System.out.println(" -_" + this.neuronioPC0.dentritos(this.nBitsG, saidas.clone()).saida());
        }

        return true;
    }

    public boolean treinoEX2(ArrayList<NumeroBits> nbits, double[][] saidadesejada) {
        this.nentradas = nbits.size();
        int contI = ((NumeroBits) nbits.get(0)).vetbits.length / 2;
        int nNeuro;
        for (nNeuro = 0; nNeuro < this.neuroniosC1.length; nNeuro++) {

            this.neuroniosC1[nNeuro] = new NeuronioPC(nNeuro, nNeuro % 2 == 0);
            contI++;
            if (contI >= ((NumeroBits) nbits.get(0)).vetbits.length) {
                contI = 0;
            }

        }

        this.neuronioCF = null;
        this.neuronioCF = new NeuronioCF[saidadesejada.length];
        for (nNeuro = 0; nNeuro < this.neuronioCF.length; nNeuro++) {
            this.neuronioCF[nNeuro] = new NeuronioCF(nNeuro % 2 == 0);
        }

        boolean erro = false;

        do {
            erro = false;
            double[] saida1 = new double[this.neuroniosC1.length];
            for (int i = 0; i < nbits.size(); i++) {
                for (int j = 0; j < this.neuroniosC1.length; j++) {

                    if (!this.neuroniosC1[j].dentritos(this.nBitsG, nbits.get(i).vetbits).saidaTreino(saidadesejada[0][i])) {
                        this.neuroniosC1[j].corrigePesos(this.neuroniosC1[j].saida(), saidadesejada[0][i]);
                        erro = true;

                        break;
                    }
                }
            }
        } while (erro);

        JOptionPane.showMessageDialog(null, "SAIU");

        return true;
    }
     */
    public void treinarRede() {
        double[][] sd = new double[this.valorEsperado.size()][this.valorEsperado.get(0).valorEsperado.size()];

        for (int cont = 0; cont < this.valorEsperado.size(); cont++) {

            for (int i = 0; i < this.valorEsperado.get(cont).valorEsperado.size(); i++) {

                sd[cont][i] = this.valorEsperado.get(cont).valorEsperado.get(i).doubleValue();
            }
        }

        treinoEX(this.entradasTreinoBits, sd);
    }

    public int treinoEX(ArrayList<NumeroBits> nbits, double[][] saidadesejada) {
        this.nentradas = nbits.size();

        if (neuroniosC2 == null) {
            neuroniosC2 = new NeuronioPC[neuroniosC1.length];
        }
        int cont;
        for (cont = 0; cont < this.neuroniosC1.length; cont++) {
            this.neuroniosC1[cont] = new NeuronioPC(cont, cont % 2 == 0);
            this.neuroniosC2[cont] = new NeuronioPC(cont, cont % 2 == 0);
        }

        this.neuronioCF = null;
        this.neuronioCF = new NeuronioCF[saidadesejada.length];

        for (cont = 0; cont < this.neuronioCF.length; cont++) {
            this.neuronioCF[cont] = new NeuronioCF(cont % 2 == 0);
        }

        for (NumeroBits entradasBits : nbits) {

            this.neuroniosC1[0]
                    .dentritos(entradasBits.vetbits.clone()).saida();

        }

        boolean erro = false;
        int treinou = 0;
        double[] saidasAJ = new double[this.neuroniosC1.length];
        double[] saidasAJ2 = new double[this.neuroniosC1.length];
        int maxN = 0;
        boolean inicioD = true;
        do {

            int ate = 0;
            int qtErro = 0;
            erro = false;
            for (int nbitscont = 0; nbitscont < nbits.size(); nbitscont++) {

                for (int ajcont = 0; ajcont < this.neuroniosC1.length; ajcont++) {

                    saidasAJ[ajcont] = this.neuroniosC1[ajcont]
                            .dentritos(nBitsG, nbits.get(nbitscont).vetbits.clone()).saida();

                    saidasAJ2[ajcont] = this.neuroniosC2[ajcont].dentritos(saidasAJ.length,
                            saidasAJ.clone()).saida();
                    if (inicioD) {
                        System.out.println("Inicio dentrito " + ajcont + " de " + this.neuroniosC1.length);
                    }
                }

                inicioD = false;

                for (int j = 0; j < this.neuronioCF.length; j++) {

                    if (!this.neuronioCF[j].dentritos(saidasAJ2.length,
                            saidasAJ2.clone()).saidaTreino(saidadesejada[nbitscont][j])) {
                        erro = true;
                        treinou++;
                        qtErro++;

                        for (int k = 0; k < this.neuroniosC1.length; k++) {

                            ate = (ate < k) ? k : ate;

                            this.neuroniosC1[k]
                                    .corrigePesos(nbits.get(nbitscont).vetbits.clone(),
                                            saidadesejada[nbitscont]);

                            saidasAJ[k] = this.neuroniosC1[k].dentritos(this.nBitsG, nbits.get(nbitscont).vetbits.clone()).saida();
                            saidasAJ2[k] = this.neuroniosC2[k].dentritos(saidasAJ.length, saidasAJ.clone()).saida();

                            if (this.neuronioCF[j].dentritos(
                                    saidasAJ2.length,
                                    saidasAJ2.clone()).saidaTreino(saidadesejada[nbitscont][j])) {
                                break;
                            }

                            this.neuroniosC2[k].corrigePesos(saidasAJ,
                                    saidadesejada[nbitscont]);
                            saidasAJ[k] = this.neuroniosC1[k].dentritos(this.nBitsG, nbits.get(nbitscont).vetbits.clone()).saida();
                            saidasAJ2[k] = this.neuroniosC2[k].dentritos(saidasAJ.length, saidasAJ.clone()).saida();

                            if (this.neuronioCF[j].dentritos(
                                    saidasAJ2.length,
                                    saidasAJ2.clone()).saidaTreino(saidadesejada[nbitscont][j])) {
                                break;
                            }

                            this.neuronioCF[j].corrigePesos(this.neuronioCF[j].saida(), saidadesejada[nbitscont][j]);
                        }

                        if (this.neuronioCF[j].dentritos(
                                saidasAJ2.length,
                                saidasAJ2.clone()).saidaTreino(saidadesejada[nbitscont][j])) {
                            break;
                        }
                    }
                }
            }

            int aJ = 0;

            for (int i = 0; i < this.neuroniosC1.length; i++) {

                if (this.neuroniosC1[i].ativadoAJ()) {
                    aJ++;
                }
            }
            maxN = (maxN < ate) ? ate : maxN;

            this.ultimaRodada = "Neurônios Treinados ao total: " + (maxN + 1) + " -Neurônios treinados desta roda: " + ate + " -Neurônios Ativados: " + aJ + " -Neurônios com Erro: " + qtErro + " -Treinos totais: " + treinou;

            System.out.println(this.ultimaRodada);
        } while (erro);

        for (int scont = 0; scont < this.neuronioCF.length; scont++) {
            System.out.println(scont + " ");
            for (int nbitscont = 0; nbitscont < nbits.size(); nbitscont++) {
                for (int ajcont = 0; ajcont < this.neuroniosC1.length; ajcont++) {
                    saidasAJ[ajcont] = this.neuroniosC1[ajcont].dentritos(this.nBitsG, ((NumeroBits) nbits.get(nbitscont)).vetbits.clone()).saida();

                    saidasAJ2[ajcont] = this.neuroniosC2[ajcont].dentritos(saidasAJ.length, saidasAJ.clone()).saida();

                }

            }
        }

        System.out.println("Saiu " + treinou);

        return treinou;
    }

    public String AcaoAJS(NumeroBits nbits) {
        ArrayList<NumeroBits> n = new ArrayList<>();
        n.add(nbits);
        double[][] saidasF = AcaoAJ(n);
        String acum = "";
        for (int j = 0; j < saidasF.length; j++) {
            acum = acum + "\n";
            for (int i = 0; i < (saidasF[j]).length; i++) {
                acum = acum + " " + saidasF[j][i];
            }
        }
        System.out.println("Acum " + acum);
        return acum;
    }

    public String AcaoAJSS(ArrayList<NumeroBits> nbits) {
        double[][] saidasF = AcaoAJ(nbits);
        String acum = "";

        for (int j = 0; j < saidasF.length; j++) {
            acum = acum + "Camada de saída " + j + "------------------------------------------------------------------------------------------\n";
            for (int i = 0; i < (saidasF[j]).length; i++) {
                acum = acum + " Entrada de " + j + " " + i + "( " + ((NumeroBits) nbits.get(i)).toLong2() + " ) Saida de :" + saidasF[j][i] + "\n";
            }
        }
        return acum;
    }

    public Object acaoPS(long valor) {
        return saidaCompleta(new NumeroBits(this.nBitsG, 2, valor), 0);
    }

    public Object acaoPS(long valor, int index) {
        return saidaCompleta(new NumeroBits(this.nBitsG, 2, valor), index);
    }

    public String acaoAJCSimples(long valor) {
        return AcaoAJS(new NumeroBits(this.nBitsG, 2, valor));
    }

    public String acaoAJCSimples(File valor) {
        try {
            byte[] dados = ((DataBufferByte) MeuAJC.image2BlackWhiteTest(ImageIO.read(valor)).getRaster().getDataBuffer()).getData();
            return AcaoAJS(new NumeroBits(dados.length, 1, (byte[]) dados.clone()));
        } catch (IOException ex) {
            Logger.getLogger(NeuronioAJC.class.getName()).log(Level.SEVERE, (String) null, ex);

            return null;
        }
    }

    public String AcaoAJS(ArrayList<NumeroBits> nbits) {
        double[][] saidasF = AcaoAJ(nbits);
        String acum = "";

        for (int j = 0; j < saidasF.length; j++) {
            acum = acum + "\n--";
            for (int i = 0; i < (saidasF[j]).length; i++) {
                acum = acum + " " + saidasF[j][i];
            }
        }
        return acum;
    }

    public double[][] AcaoAJ(ArrayList<NumeroBits> nbits) {
        double[] saidasAJ = new double[this.neuroniosC1.length];
        double[] saidasAJ2 = new double[this.neuroniosC1.length];
        double[][] saidasF = new double[this.neuronioCF.length][nbits.size()];
        for (int scont = 0; scont < this.neuronioCF.length; scont++) {

            for (int nbitscont = 0; nbitscont < nbits.size(); nbitscont++) {
                for (int ajcont = 0; ajcont < this.neuroniosC1.length; ajcont++) {
                    saidasAJ[ajcont] = this.neuroniosC1[ajcont].dentritos(this.nBitsG,
                            nbits.get(nbitscont).vetbits.clone()).saida();
                    saidasAJ2[ajcont] = this.neuroniosC2[ajcont].dentritos(saidasAJ.length,
                            saidasAJ.clone()).saida();

                }
                saidasF[scont][nbitscont] = this.neuronioCF[scont].dentritos(saidasAJ2.length, saidasAJ2).saida();
            }
        }

        return saidasF;
    }

    public void treinarRedePS(int nNeuronios) {

        treinoPS(entradasTreinoBits, nNeuronios, entradasTreinoBits);
    }

    public void treinarRedePS() {

        treinoPS(entradasTreinoBits, 1, entradasTreinoBits);
    }

    NeuronioPC[] neEntrada = null;
    NeuronioPC[] neSaida1 = null;
    NeuronioPC[] neSaida2 = null;
    NeuronioPC[] neSaida3 = null;

    public boolean treinoPS(ArrayList<NumeroBits> nbits, int NS, ArrayList<NumeroBits> nSaida) {
        neEntrada = new NeuronioPC[NS];
        neSaida1 = new NeuronioPC[NS];
        neSaida2 = new NeuronioPC[NS];

        neSaida3 = new NeuronioPC[NS];
        int nNeuro;
        for (nNeuro = 0; nNeuro < neEntrada.length; nNeuro++) {
            neEntrada[nNeuro] = new NeuronioPC(nNeuro, nNeuro % 2 == 0);
            neSaida1[nNeuro] = new NeuronioPC(nNeuro, nNeuro % 2 == 0);
            neSaida2[nNeuro] = new NeuronioPC(nNeuro, nNeuro % 2 == 0);

        }

        for (nNeuro = 0; nNeuro < nbits.get(0).valorEsperado.length; nNeuro++) {

            neSaida3[nNeuro] = new NeuronioPC(nNeuro, nNeuro % 2 == 0);

        }

        boolean erro = false;

        int cont = 0;

        NumeroBits t = null;

        do {
            int nBitsCount = 0;
            erro = false;

            for (NumeroBits valoresEntrada : nbits) {
                nBitsCount++;

                if (t == null) {
                    t = valoresEntrada;
                }

                // System.out.println("Entrou para " + valoresEntrada.valor + " neEntrada " + neEntrada.length);
                for (int index = 0; index < valoresEntrada.valorEsperado.length; index++) {
                    double r = saidaCompleta(valoresEntrada, index);

                    if (valoresEntrada.valorEsperado[index] < 0 ? r >= 0 : valoresEntrada.valorEsperado[index] >= 0 ? r < 0 : true) {

                        treinaNeuroniosF1(0, valoresEntrada, index);
                        erro = true;
                    }

                    r = saidaCompleta(valoresEntrada, index);

                    if (valoresEntrada.valorEsperado[index] < 0 ? r >= 0 : valoresEntrada.valorEsperado[index] >= 0 ? r < 0 : true) {

                        treinaNeuroniosF1(1, valoresEntrada, index);
                        erro = true;
                    }

                    r = saidaCompleta(valoresEntrada, index);

                    if (valoresEntrada.valorEsperado[index] < 0 ? r >= 0 : valoresEntrada.valorEsperado[index] >= 0 ? r < 0 : true) {

                        treinaNeuroniosF1(2, valoresEntrada, index);
                        erro = true;
                    }

                    r = saidaCompleta(valoresEntrada, index);

                    if (valoresEntrada.valorEsperado[index] < 0 ? r >= 0 : valoresEntrada.valorEsperado[index] >= 0 ? r < 0 : true) {

                        treinaNeuroniosFF(valoresEntrada, index);

                        erro = true;
                    }

                    if (valoresEntrada.valorEsperado[index] < 0 ? r >= 0 : valoresEntrada.valorEsperado[index] >= 0 ? r < 0 : true) {

                        System.out.println("Erro" + valoresEntrada.valor + " esp " + valoresEntrada.valorEsperado[index] + " sd " + r);

                    }

                    if (!erro) {
                        r = saidaCompleta(valoresEntrada, index);
                        System.out.println("SemErro " + index + " " + nBitsCount + " " + valoresEntrada.valor + " esp " + valoresEntrada.valorEsperado[index] + " sd " + r);
                    
                        
                    }

                }

            }
            cont++;

        } while (erro);

        System.out.println("Terminou " + cont);

        return true;
    }

    public double saidaCompleta(NumeroBits valoresEntrada, int index) {

        Double rAC = 0.0;
        for (int nNeuro = 0; nNeuro < neEntrada.length; nNeuro++) {

            rAC += saidaNeuronioF1(nNeuro, valoresEntrada, index) * 0.2;

        }

        double r = saidaNeuronioFF(getValorTesteCEsperado(rAC.intValue(), valoresEntrada.valorEsperado), index);
        return r;
    }

    public double saidaNeuronioF1(int nNeuro, NumeroBits valoresEntrada, int index) {

        Double r = neEntrada[nNeuro]
                .dentritos(valoresEntrada.vetbits.length, valoresEntrada.vetbits.clone())
                .saida();

        NumeroBits nS1 = new NumeroBits(this.nBitsG, 2, r.intValue(), valoresEntrada.getValorEsperado());

        Double r2 = neSaida1[nNeuro]
                .dentritos(nS1.vetbits.length, nS1.vetbits.clone())
                .saida();

        NumeroBits nS2 = new NumeroBits(this.nBitsG, 2, r2.intValue(),
                valoresEntrada.getValorEsperado());

        Double r3 = neSaida2[nNeuro]
                .dentritos(nS2.vetbits.length, nS2.vetbits.clone())
                .saida();

        return r3.intValue();
    }

    public void treinaNeuroniosF1(int camada, NumeroBits valoresEntrada, int index) {

        for (int nNeuro = 0; nNeuro < neEntrada.length; nNeuro++) {
            Double r = neEntrada[nNeuro]
                    .dentritos(valoresEntrada.vetbits.length, valoresEntrada.vetbits.clone())
                    .saidaTreinoNumero(valoresEntrada.getValorEsperado()[index]);

            if (camada == 0) {
                neEntrada[nNeuro].corrigePesos(valoresEntrada.vetbits.clone(),
                        valoresEntrada.getValorEsperado());
            }
            NumeroBits nS1 = new NumeroBits(this.nBitsG, 2, r.intValue(), valoresEntrada.getValorEsperado());

            Double r2 = neSaida1[nNeuro]
                    .dentritos(nS1.vetbits.length, nS1.vetbits.clone())
                    .saidaTreinoNumero(nS1.getValorEsperado()[index]);
            if (camada == 1) {
                neSaida1[nNeuro].corrigePesos(nS1.vetbits.clone(),
                        nS1.getValorEsperado());
            }
            NumeroBits nS2 = new NumeroBits(this.nBitsG, 2, r2.intValue(),
                    valoresEntrada.getValorEsperado());

            Double r3 = neSaida2[nNeuro]
                    .dentritos(nS2.vetbits.length, nS2.vetbits.clone())
                    .saidaTreinoNumero(nS2.getValorEsperado()[index]);
            if (camada == 2) {
                neSaida2[nNeuro].corrigePesos(nS2.vetbits.clone(),
                        nS2.getValorEsperado());
            }
        }

    }

    public double saidaNeuronioFF(NumeroBits valoresEntrada, int index) {

        Double r3 = neSaida3[index]
                .dentritos(valoresEntrada.vetbits.length, valoresEntrada.vetbits.clone())
                .saida();

        return r3.intValue();
    }

    public void treinaNeuroniosFF(NumeroBits valoresEntrada, int index) {

        neSaida3[index].corrigePesos(valoresEntrada.vetbits.clone(),
                valoresEntrada.getValorEsperado());

    }

    public ArrayList<Double> saidaPS(ArrayList<NumeroBits> nbits, int index) {
        ArrayList<Double> result = new ArrayList<>();
        for (NumeroBits nb : nbits) {
            result.add(saidaCompleta(nb, index));
        }

        return result;
    }

    public boolean treinoEX(ArrayList<NumeroBits> nbits, double[] saidadesejada) {
        this.nentradas = nbits.size();

        for (int cont = 0; cont < this.neuroniosC1.length; cont++) {
            this.neuroniosC1[cont] = new NeuronioPC(cont, cont % 2 == 0);
        }

        boolean erro = false;
        int treinou = 0;
        double[] saidas = new double[this.neuroniosC1.length];

        while (true) {
            erro = false;
            int nbcont;
            for (nbcont = 0; nbcont < nbits.size(); nbcont++) {

                for (int ajcont = 0; ajcont < this.neuroniosC1.length; ajcont++) {

                    if (!this.neuroniosC1[ajcont].dentritos(
                            this.nBitsG,
                            (nbits.get(nbcont)).vetbits.clone()).saidaTreino(saidadesejada[nbcont])) {

                        this.neuroniosC1[ajcont].corrigePesos(nbits.get(ajcont).vetbits.clone(),
                                saidadesejada);
                        erro = true;
                        treinou++;
                    }
                }
            }
            if (!erro) {
                System.out.println("Passou");

                do {
                    erro = false;
                    System.out.println("Saidas ");
                    for (nbcont = 0; nbcont < nbits.size(); nbcont++) {
                        System.out.println();

                        for (int ajcont = 0; ajcont < this.neuroniosC1.length; ajcont++) {

                            saidas[ajcont] = this.neuroniosC1[ajcont].dentritos(this.nBitsG,
                                    ((NumeroBits) nbits.get(nbcont)).vetbits.clone()).saida();

                            System.out.print(ajcont + "- (" + ((NumeroBits) nbits.get(nbcont)).paraString() + ")-:-" + saidas[ajcont] + " saidaT" + saidadesejada[nbcont] + " //");
                        }
                        if (!this.neuronioPC0.dentritos(this.nBitsG, saidas).saidaTreino(
                                saidadesejada[nbcont])) {
                            //  this.neuronioPC0.corrigePesos(this.neuronioPC0.saida(), saidadesejada);
                            erro = true;
                            treinou++;
                        }

                    }

                } while (erro);

                System.out.println("\nTREINOS " + treinou);
                for (nbcont = 0; nbcont < nbits.size(); nbcont++) {

                    for (int ajcont = 0; ajcont < this.neuroniosC1.length; ajcont++) {
                        saidas[ajcont] = this.neuroniosC1[ajcont].dentritos(this.nBitsG,
                                ((NumeroBits) nbits.get(nbcont)).vetbits.clone()).saida();
                    }

                }

                return true;
            }
        }
    }

    public double saida(double[] valores) {
        double[] saidas = new double[this.neuroniosC1.length];

        double[] ventrada = new double[this.nentradas];
        int t;
        for (t = 0; t < valores.length; t += this.nentradas + 1) {
            System.out.print(" Entrada  ");

            for (int v = 0; v < ventrada.length; v++) {

                ventrada[v] = valores[t + v];

                System.out.print(" - " + ventrada[v]);
            }
            for (int a = 0; a < this.neuroniosC1.length; a++) {
                saidas[a] = this.neuroniosC1[a].dentritos(this.nBitsG,
                        ventrada.clone()).saida();
            }

            System.out.println(" -_" + this.neuronioPC0.dentritos(this.nBitsG,
                    saidas.clone()).saida());
        }
        return 0.0D;
    }

    public String stringDeDouble(double d[]) {
        String t = "";

        for (int cont = 0; cont < d.length; cont++) {

            t += d[cont] + "-";
        }
        return t;
    }

    static double PAR = -2, IMPAR = 2;

    public static void main(String[] args) {
//        NeuronioAJC nr = new NeuronioAJC(2, 100);
//
//        nr.setValorTreino(2, PAR);//Para par
//         nr.setValorTreino(2000, PAR);//Para par
//        nr.setValorTreino(2001, IMPAR);// Para impar
//        nr.setValorTreino(2003, IMPAR);// Para impar
//        nr.treinarRedePS();
//
//        ArrayList<NumeroBits> aN = new ArrayList<>();
//        aN.add(nr.getValorTeste(7));    
//        System.out.println(nr.saidaPS(aN).toString() + "-------");

        File fileN = new File("IMG0");
        File fileS = new File("IMG1");
        File[] nfile = fileN.listFiles();
        File[] sfile = fileS.listFiles();

        NeuronioAJC nr = new NeuronioAJC(NeuronioAJC.lenghtImagem(sfile[0]));
        for (int cont = 0; cont < nfile.length; cont++) {
            System.out.println(" " + nfile[cont].getPath());
            nr.setValorTreinoNovo(nfile[cont], IMPAR);
        }

        for (int cont = 0; cont < sfile.length; cont++) {
            System.out.println(" " + sfile[cont].getPath());

            nr.setValorTreinoNovo(sfile[cont], PAR);
        }

       // System.out.println(""+nr.getValorTreinoNovo(sfile[0]).paraStringCast()
      //  .contentEquals(nr.getValorTreinoNovo(sfile[0]).paraStringCast()));
        nr.treinarRedePS(3);

        ArrayList<NumeroBits> aN = new ArrayList<>();
        aN.add(nr.getValorTreinoNovo(sfile[0]));
        aN.add(nr.getValorTreinoNovo(nfile[0]));
        System.out.println(nr.saidaPS(aN, 0).toString() + "-------");
        System.out.println(nr.saidaCompleta(nr.entradasTreinoBits.get(0), 0));

    }
}


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/meupneuronio/NeuronioAJC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
