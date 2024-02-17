package meupneuronio;

import java.io.Serializable;

public class NeuronioCF
        implements Serializable, Cloneable {

    public int ncorrecoes = 0;
    public double[] aentradas = null;
    public double aprendizado = 0.2D;
    public double bias = -1.0D;
    public double[] pesos = null;
    public double S = 0.0D;
    public double pesoInicial = 0.93D;

    public NeuronioCF(boolean iP) {
        if (iP) {
            bias = 1;
        }
    }

    public void iniciaPeso(int lt) {
        this.pesos = new double[lt];

        for (int cont = 0; cont < this.pesos.length; cont++) {
            this.pesos[cont] = pesoInicial;
        }
    }

    protected NeuronioCF dentritos(int nBitsSize,double... entradas) {
        this.aentradas = entradas;
        if (this.pesos == null) {
            iniciaPeso(nBitsSize);
        }
        double acum = 0.0D;
        for (int cont = 0; cont < nBitsSize; cont++) {
             acum += this.pesos[cont] * entradas[cont];
         }

        acum += (this.bias * 1);

        this.S = acum;
        return this;
    }

    protected double saida() {
        System.out.println("Saida S " + this.S);
        return (this.S >= 0.5D) ? 1.0D : 0.0D;
    }

    protected boolean saidaTreino(double esperado) {
        return (esperado == saida());
    }

    protected void corrigePesos(double saida, double esperado) {
        for (int cont = 0; cont < this.pesos.length - 1; cont++) {

            this.pesos[cont] = this.pesos[cont] + this.aprendizado * (esperado - saida) * this.aentradas[cont];
        }
        this.pesos[this.pesos.length - 1] = this.pesos[this.pesos.length - 1] + this.aprendizado * (esperado - saida) * this.bias;

        this.ncorrecoes++;
    }

    protected void exibePeso() {
        for (int cont = 0; cont < this.pesos.length; cont++) {
            System.out.print("P" + cont + " " + this.pesos[cont]);
        }
        System.out.println();
    }
}


/* Location:              /home/junior/Downloads/RedeACJLib_0.3.jar!/meupneuronio/Neuronio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
