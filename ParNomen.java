public class ParNomen {
    private String nomenSalida;
    private String nomenEntrada;

    ParNomen(String salida, String entrada) {
        nomenSalida = salida;
        nomenEntrada = entrada;
    }

    public String getNomenSalida() {
        return nomenSalida;
    }

    public void setNomenSalida(String nomenSalida) {
        this.nomenSalida = nomenSalida;
    }

    public String getNomenEntrada() {
        return nomenEntrada;
    }

    public void setNomenEntrada(String nomenEntrada) {
        this.nomenEntrada = nomenEntrada;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nomenSalida == null) ? 0 : nomenSalida.hashCode());
        result = prime * result + ((nomenEntrada == null) ? 0 : nomenEntrada.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParNomen other = (ParNomen) obj;
        if (nomenSalida == null) {
            if (other.nomenSalida != null)
                return false;
        } else if (!nomenSalida.equals(other.nomenSalida))
            return false;
        if (nomenEntrada == null) {
            if (other.nomenEntrada != null)
                return false;
        } else if (!nomenEntrada.equals(other.nomenEntrada))
            return false;
        return true;
    }

}
