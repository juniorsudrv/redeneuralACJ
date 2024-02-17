package meupneuronio;

import Biblis.MemoryUtils;
import TrabalhaBits.NumeroBits;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NeuronioPC
        implements Serializable, Cloneable {

    public int indice = 0;
    public int maxtentativas = 3000;
    public int ncorrecoes = 0;
    public double[] aentradas = null;
    public double pesoInicial = 0.02D;
    public double aprendizado = 0.3D;
    public double bias = -0.1D;
    public ArrayList<MutableDouble> pesos = new ArrayList<>();
    public double S = 0.0D;
    public double[] udentritos;
    double MaxM = 0.0D;

    public NeuronioPC(int indice, boolean iP) {
        if (iP) {
            bias = 1;
        }
        this.MaxM = MemoryUtils.maxMemory();
        this.indice = indice;
        if (indice != 0) {
            this.maxtentativas += indice * 3;
        }
    }

    protected void iniciaPeso(int lt) {
        for (int cont = 0; cont < lt; cont++) {
            if (MemoryUtils.usedMemory() > this.MaxM - 200.0D) {
                System.out
                        .println(MemoryUtils.usedMemory() + "  " + MemoryUtils.maxMemory());
                System.gc();
            }

            this.pesos.add(new MutableDouble(pesoInicial));
        }
    }

    protected NeuronioPC dentritos(double... entradas) {
        this.aentradas = entradas;
        this.udentritos = entradas;

        if (this.pesos.size() == 0) {
            iniciaPeso(entradas.length);
        }

        if (this.ncorrecoes > this.maxtentativas) {
            this.aentradas[this.indice] = invertEntrada(this.aentradas[this.indice]);
        }

        double acum = 0.0D;
        try {
            for (int cont = 0; cont < entradas.length; cont++) {
                acum += this.pesos.get(cont).getValue() * entradas[cont];

            }
        } catch (Exception ex) {
            Logger.getLogger(NeuronioPC.class.getName()).log(Level.SEVERE, (String) null, ex);

            System.out.println(" Entrada p  " + this.pesos.size() + " " + entradas.length);
            System.exit(0);
        }
        acum += (1 * this.bias);
        this.S = acum;
        return this;
    }

    protected NeuronioPC dentritos(int nBitsG, double... entradas) {
        this.aentradas = entradas;
        this.udentritos = entradas;

        if (this.pesos.size() == 0) {
            iniciaPeso(nBitsG);
        }

        if (this.ncorrecoes > this.maxtentativas) {
             this.aentradas[this.indice] = invertEntrada(this.aentradas[this.indice]);
        }

        double acum = 0.0D;
        try {
            for (int cont = 0; cont < nBitsG; cont++) {
                acum += this.pesos.get(cont).getValue() * entradas[cont];

            }
        } catch (Exception ex) {
            Logger.getLogger(NeuronioPC.class.getName()).log(Level.SEVERE, (String) null, ex);

            System.out.println(" Entrada p  " + this.pesos.size() + " " + entradas.length);
            System.exit(0);
        }
        acum += (1 * this.bias);
        this.S = acum;
        return this;
    }

    public double saida() {
        //  System.out.println("SaidaAJ " + S);
        return S;
    }

    protected boolean saidaTreino(double valoresperado) {
        if (this.ncorrecoes > this.maxtentativas) {
            valoresperado *= this.aentradas[0];
        }

        return (saida() == valoresperado);
    }

    protected double saidaTreinoNumero(double valoresperado) {

     
         // System.out.println(saida()+ " VVVV "+valoresperado);        
        return saida();
    }

    protected void corrigePesos(double valoresTreino[], double[] saidadesejada) {

        for (int contS = 0; contS < saidadesejada.length; contS++) {
            double v = saidadesejada[contS];
            for (int cont = 0; cont < this.pesos.size(); cont++) {
                double valoresperado = v;
                valoresperado = valoresperado * valoresTreino[cont];
                double saida = valoresperado > 0 ? 1 : 0;

                this.pesos.get(cont).setValue(this.pesos.get(cont).getValue() + this.aprendizado * (valoresperado - saida) * valoresTreino[cont]);

            }
        }

        fazCorrecao();
    }

    protected void fazCorrecao() {
        this.ncorrecoes++;
    }

    protected boolean ativadoAJ() {
        return (this.ncorrecoes > this.maxtentativas);
    }

    protected double invertEntrada(double entrada) {
        if (entrada == 1.0D) {
            return 0.0D;
        }
        return 1.0D;
    }

    protected void exibePeso() {
        for (int cont = 0; cont < this.pesos.size(); cont++) {
            System.out.print("P" + cont + " " + ((MutableDouble) this.pesos.get(cont)).getValue());
        }
        System.out.println();
    }
}


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/meupneuronio/NeuronioAJ.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
