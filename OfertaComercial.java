public class OfertaComercial extends DocumentoBase {
    private String codigoOferta;
    private String cliente;
    private String descripcionServicio;
    private double monto;
    private int diasValidez;

    public OfertaComercial(String referencia, String codigoOferta, String cliente, String descripcionServicio, double monto, int diasValidez) {
        super(referencia);
        this.codigoOferta = codigoOferta;
        this.cliente = cliente;
        this.descripcionServicio = descripcionServicio;
        this.monto = monto;
        this.diasValidez = diasValidez;
    }

    // Constructor de copia privado (Patrón Prototype)
    private OfertaComercial(OfertaComercial original) {
        super(original.getReferencia());
        this.codigoOferta = original.codigoOferta;
        this.cliente = original.cliente;
        this.descripcionServicio = original.descripcionServicio;
        this.monto = original.monto;
        this.diasValidez = original.diasValidez;
    }

    @Override
    public OfertaComercial clonar() {
        return new OfertaComercial(this);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Referencia: " + getReferencia());
        System.out.println("Código Oferta: " + codigoOferta);
        System.out.println("Cliente: " + cliente);
        System.out.println("Descripción: " + descripcionServicio);
        System.out.println("Monto: $" + monto);
        System.out.println("Validez: " + diasValidez + " días");
    }

    public String getCodigoOferta() { return codigoOferta; }
    public void setCodigoOferta(String codigoOferta) { this.codigoOferta = codigoOferta; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getDescripcionServicio() { return descripcionServicio; }
    public void setDescripcionServicio(String descripcionServicio) { this.descripcionServicio = descripcionServicio; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public int getDiasValidez() { return diasValidez; }
    public void setDiasValidez(int diasValidez) { this.diasValidez = diasValidez; }
}