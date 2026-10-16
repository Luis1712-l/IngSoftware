public class AcuerdoServicios extends DocumentoBase {
    private String codigoContrato;
    private String contratista;
    private String entidad;
    private double valorTotal;
    private int plazoMeses;

    public AcuerdoServicios(String referencia, String codigoContrato, String contratista, String entidad, double valorTotal, int plazoMeses) {
        super(referencia);
        this.codigoContrato = codigoContrato;
        this.contratista = contratista;
        this.entidad = entidad;
        this.valorTotal = valorTotal;
        this.plazoMeses = plazoMeses;
    }

    // Constructor de copia privado (Patrón Prototype)
    private AcuerdoServicios(AcuerdoServicios original) {
        super(original.getReferencia());
        this.codigoContrato = original.codigoContrato;
        this.contratista = original.contratista;
        this.entidad = original.entidad;
        this.valorTotal = original.valorTotal;
        this.plazoMeses = original.plazoMeses;
    }

    @Override
    public AcuerdoServicios clonar() {
        return new AcuerdoServicios(this);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Referencia: " + getReferencia());
        System.out.println("Código Contrato: " + codigoContrato);
        System.out.println("Contratista: " + contratista);
        System.out.println("Entidad: " + entidad);
        System.out.println("Valor Total: $" + valorTotal);
        System.out.println("Plazo: " + plazoMeses + " meses");
    }

    public String getCodigoContrato() { return codigoContrato; }
    public void setCodigoContrato(String codigoContrato) { this.codigoContrato = codigoContrato; }
    public String getContratista() { return contratista; }
    public void setContratista(String contratista) { this.contratista = contratista; }
    public String getEntidad() { return entidad; }
    public void setEntidad(String entidad) { this.entidad = entidad; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public int getPlazoMeses() { return plazoMeses; }
    public void setPlazoMeses(int plazoMeses) { this.plazoMeses = plazoMeses; }
}