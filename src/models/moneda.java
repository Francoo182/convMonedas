package models;

public class moneda {
    private Double valor;
    public moneda(consultaDTO DTOcon) {
        this.valor = DTOcon.conversion_rate();
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
