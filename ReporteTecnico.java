public class ReporteTecnico extends DocumentoBase {
    private String titulo;
    private String responsable;
    private String area;
    private String fecha;
    private String observaciones;

    public ReporteTecnico(String referencia, String titulo, String responsable, String area, String fecha, String observaciones) {
        super(referencia);
        this.titulo = titulo;
        this.responsable = responsable;
        this.area = area;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    // Constructor de copia privado (Patrón Prototype)
    private ReporteTecnico(ReporteTecnico original) {
        super(original.getReferencia());
        this.titulo = original.titulo;
        this.responsable = original.responsable;
        this.area = original.area;
        this.fecha = original.fecha;
        this.observaciones = original.observaciones;
    }

    @Override
    public ReporteTecnico clonar() {
        return new ReporteTecnico(this);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Referencia: " + getReferencia());
        System.out.println("Título: " + titulo);
        System.out.println("Responsable: " + responsable);
        System.out.println("Área: " + area);
        System.out.println("Fecha: " + fecha);
        System.out.println("Observaciones: " + observaciones);
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}