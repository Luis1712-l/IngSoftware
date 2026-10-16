public abstract class DocumentoBase {
    private String referencia;

    public DocumentoBase(String referencia) {
        this.referencia = referencia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public abstract DocumentoBase clonar();
    public abstract void mostrarInfo();
}