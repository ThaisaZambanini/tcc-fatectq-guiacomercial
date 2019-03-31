package tcc.fatec.com.br.guiacomercialtcc.dto;

public class MensagemDTO {

    private String text;
    private String title;

    public void setText(String msg) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
