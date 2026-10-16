import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ArrayList<DocumentoBase> listaPlantillas = new ArrayList<>();
    private static final ArrayList<DocumentoBase> listaGenerados = new ArrayList<>();

    public static void main(String[] args) {
        inicializarBaseDatosMock();
        int opcion;

        do {
            desplegarMenu();
            opcion = solicitarEntero("Seleccione una opción del menú: ");

            switch (opcion) {
                case 1:
                    registrarNuevaPlantilla();
                    break;
                case 2:
                    consultarPlantillas();
                    break;
                case 3:
                    duplicarPlantillaPrototype();
                    break;
                case 4:
                    consultarGenerados();
                    break;
                case 5:
                    editarDocumentoGenerado();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción incorrecta. Intente de nuevo.");
            }
        } while (opcion != 6);

        SCANNER.close();
    }

    private static void inicializarBaseDatosMock() {
        listaPlantillas.add(new OfertaComercial("Plantilla Oferta Estándar", "OFT-00", "Cliente Genérico", "Consultoría TI", 3000000.0, 15));
        listaPlantillas.add(new ReporteTecnico("Plantilla Reporte Base", "Evaluación de Sistemas", "Ing. Asignado", "Soporte", "2026-10-15", "Sin observaciones"));
        listaPlantillas.add(new AcuerdoServicios("Plantilla Acuerdo Marco", "CTR-00", "Prestador por Definir", "Institución", 8000000.0, 12));
    }

    private static void desplegarMenu() {
        System.out.println("\nGESTOR INTELIGENTE DE DOCUMENTOS");
        System.out.println("1. Registrar nueva plantilla");
        System.out.println("2. Ver plantillas disponibles");
        System.out.println("3. Clonar plantilla (Patrón Prototype)");
        System.out.println("4. Ver documentos generados (Copias)");
        System.out.println("5. Modificar documento clonado");
        System.out.println("6. Salir");
    }

    private static void registrarNuevaPlantilla() {
        System.out.println("\n- Registrar Plantilla-");
        System.out.println("1. Oferta Comercial");
        System.out.println("2. Reporte Técnico");
        System.out.println("3. Acuerdo de Servicios");

        int tipo = solicitarEntero("Elija el tipo de documento: ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Opción no válida.");
            return;
        }

        String referencia = solicitarTextoNoVacio("Referencia o Nombre descriptivo: ");
        DocumentoBase nueva;

        switch (tipo) {
            case 1:
                String cod = solicitarTextoNoVacio("Código de oferta: ");
                String cli = solicitarTextoNoVacio("Cliente: ");
                String desc = solicitarTextoNoVacio("Descripción: ");
                double monto = solicitarDouble("Monto: ");
                int validez = solicitarEntero("Días de validez: ");
                nueva = new OfertaComercial(referencia, cod, cli, desc, monto, validez);
                break;
            case 2:
                String tit = solicitarTextoNoVacio("Título: ");
                String resp = solicitarTextoNoVacio("Responsable: ");
                String area = solicitarTextoNoVacio("Área: ");
                String fecha = solicitarTextoNoVacio("Fecha: ");
                String obs = solicitarTextoNoVacio("Observaciones: ");
                nueva = new ReporteTecnico(referencia, tit, resp, area, fecha, obs);
                break;
            case 3:
                String numC = solicitarTextoNoVacio("Código de contrato: ");
                String cont = solicitarTextoNoVacio("Contratista: ");
                String ent = solicitarTextoNoVacio("Entidad: ");
                double valC = solicitarDouble("Valor total: ");
                int plazo = solicitarEntero("Plazo en meses: ");
                nueva = new AcuerdoServicios(referencia, numC, cont, ent, valC, plazo);
                break;
            default:
                return;
        }

        listaPlantillas.add(nueva);
        System.out.println("¡Plantilla registrada con éxito!");
    }

    private static void consultarPlantillas() {
        System.out.println("\n--- Plantillas Registradas ---");
        if (listaPlantillas.isEmpty()) {
            System.out.println("No hay plantillas disponibles.");
            return;
        }
        for (int i = 0; i < listaPlantillas.size(); i++) {
            System.out.println("\n[Índice: " + i + "] Tipo: " + listaPlantillas.get(i).getClass().getSimpleName());
            listaPlantillas.get(i).mostrarInfo();
        }
    }

    private static void duplicarPlantillaPrototype() {
        System.out.println("\n--- Clonar Plantilla (Prototype) ---");
        if (listaPlantillas.isEmpty()) {
            System.out.println("No hay plantillas para clonar.");
            return;
        }
        consultarPlantillas();
        int idx = solicitarEntero("Ingrese el índice de la plantilla a clonar: ");

        if (idx < 0 || idx >= listaPlantillas.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        DocumentoBase modelo = listaPlantillas.get(idx);
        DocumentoBase clon = modelo.clonar();

        String nuevaRef = solicitarTexto("Nueva referencia para la copia (Dejar en blanco para conservar): ");
        if (!nuevaRef.isEmpty()) {
            clon.setReferencia(nuevaRef);
        }

        listaGenerados.add(clon);
        System.out.println("¡Clonación exitosa!");
        clon.mostrarInfo();
    }

    private static void consultarGenerados() {
        System.out.println("\n--- Documentos Generados ---");
        if (listaGenerados.isEmpty()) {
            System.out.println("No hay copias generadas.");
            return;
        }
        for (int i = 0; i < listaGenerados.size(); i++) {
            System.out.println("\n[Índice: " + i + "] Tipo: " + listaGenerados.get(i).getClass().getSimpleName());
            listaGenerados.get(i).mostrarInfo();
        }
    }

    private static void editarDocumentoGenerado() {
        System.out.println("\n--- Modificar Copia ---");
        if (listaGenerados.isEmpty()) {
            System.out.println("No hay documentos generados para modificar.");
            return;
        }
        consultarGenerados();
        int idx = solicitarEntero("Ingrese el índice del documento a modificar: ");

        if (idx < 0 || idx >= listaGenerados.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        DocumentoBase doc = listaGenerados.get(idx);
        int subOpcion;

        do {
            System.out.println("\nEditando documento: " + doc.getReferencia());
            System.out.println("1. Cambiar referencia descriptiva");

            if (doc instanceof OfertaComercial) {
                System.out.println("2. Cambiar código oferta");
                System.out.println("3. Cambiar cliente");
                System.out.println("4. Cambiar descripción");
                System.out.println("5. Cambiar monto");
                System.out.println("6. Cambiar validez (días)");
            } else if (doc instanceof ReporteTecnico) {
                System.out.println("2. Cambiar título");
                System.out.println("3. Cambiar responsable");
                System.out.println("4. Cambiar área");
                System.out.println("5. Cambiar fecha");
                System.out.println("6. Cambiar observaciones");
            } else if (doc instanceof AcuerdoServicios) {
                System.out.println("2. Cambiar código contrato");
                System.out.println("3. Cambiar contratista");
                System.out.println("4. Cambiar entidad");
                System.out.println("5. Cambiar valor total");
                System.out.println("6. Cambiar plazo (meses)");
            }

            System.out.println("0. Guardar y Volver");
            subOpcion = solicitarEntero("Seleccione una opción de edición: ");

            if (subOpcion == 0) break;

            if (subOpcion == 1) {
                doc.setReferencia(solicitarTextoNoVacio("Nueva referencia: "));
            } else if (doc instanceof OfertaComercial) {
                editarOferta((OfertaComercial) doc, subOpcion);
            } else if (doc instanceof ReporteTecnico) {
                editarReporte((ReporteTecnico) doc, subOpcion);
            } else if (doc instanceof AcuerdoServicios) {
                editarAcuerdo((AcuerdoServicios) doc, subOpcion);
            }
        } while (true);

        System.out.println("Documento actualizado correctamente:");
        doc.mostrarInfo();
    }

    private static void editarOferta(OfertaComercial o, int opt) {
        switch (opt) {
            case 2: o.setCodigoOferta(solicitarTextoNoVacio("Nuevo código: ")); break;
            case 3: o.setCliente(solicitarTextoNoVacio("Nuevo cliente: ")); break;
            case 4: o.setDescripcionServicio(solicitarTextoNoVacio("Nueva descripción: ")); break;
            case 5: o.setMonto(solicitarDouble("Nuevo monto: ")); break;
            case 6: o.setDiasValidez(solicitarEntero("Nuevos días: ")); break;
            default: System.out.println("Opción inválida.");
        }
    }

    private static void editarReporte(ReporteTecnico r, int opt) {
        switch (opt) {
            case 2: r.setTitulo(solicitarTextoNoVacio("Nuevo título: ")); break;
            case 3: r.setResponsable(solicitarTextoNoVacio("Nuevo responsable: ")); break;
            case 4: r.setArea(solicitarTextoNoVacio("Nueva área: ")); break;
            case 5: r.setFecha(solicitarTextoNoVacio("Nueva fecha: ")); break;
            case 6: r.setObservaciones(solicitarTextoNoVacio("Nuevas observaciones: ")); break;
            default: System.out.println("Opción inválida.");
        }
    }

    private static void editarAcuerdo(AcuerdoServicios a, int opt) {
        switch (opt) {
            case 2: a.setCodigoContrato(solicitarTextoNoVacio("Nuevo código: ")); break;
            case 3: a.setContratista(solicitarTextoNoVacio("Nuevo contratista: ")); break;
            case 4: a.setEntidad(solicitarTextoNoVacio("Nueva entidad: ")); break;
            case 5: a.setValorTotal(solicitarDouble("Nuevo valor: ")); break;
            case 6: a.setPlazoMeses(solicitarEntero("Nuevo plazo: ")); break;
            default: System.out.println("Opción inválida.");
        }
    }

    private static String solicitarTextoNoVacio(String msj) {
        while (true) {
            String txt = solicitarTexto(msj);
            if (!txt.isEmpty()) return txt;
            System.out.println("Este campo no puede estar vacío.");
        }
    }

    private static String solicitarTexto(String msj) {
        System.out.print(msj);
        return SCANNER.nextLine().trim();
    }

    private static int solicitarEntero(String msj) {
        while (true) {
            System.out.print(msj);
            try {
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        }
    }

    private static double solicitarDouble(String msj) {
        while (true) {
            System.out.print(msj);
            String val = SCANNER.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(val);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numérico válido.");
            }
        }
    }
}