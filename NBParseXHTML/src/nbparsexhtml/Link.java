package nbparsexhtml;

public class Link {

    private String href;
    private String tipoScuola;
    private String specializzazione;
    private String classe;

    public Link() {
        this.href = "";
        this.tipoScuola = "";
        this.specializzazione = "";
        this.classe = "";
    }

    public Link(String href, String tipoScuola, String specializzazione, String classe) {
        this.href = href.trim();
        this.tipoScuola = tipoScuola.trim();
        this.specializzazione = specializzazione.trim();
        this.classe = classe.trim();
    }


    public Link(Link libro) {
        this.href = libro.href;
        this.tipoScuola = libro.tipoScuola;
        this.specializzazione = libro.specializzazione;
        this.classe = libro.classe;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href.trim();
    }

    public String getTipoScuola() {
        return tipoScuola;
    }

    public void setTipoScuola(String tipoScuola) {
        this.tipoScuola = tipoScuola.trim();
    }

    public String getSpecializzazione() {
        return specializzazione;
    }

    public void setSpecializzazione(String specializzazione) {
        this.specializzazione = specializzazione.trim();
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe.trim();
    }

    public String toString() {
        return "[" + href + "] " + specializzazione + ", " + tipoScuola + " (" + classe + ")";
    }
}
