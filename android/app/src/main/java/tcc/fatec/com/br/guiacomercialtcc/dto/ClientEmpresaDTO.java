package tcc.fatec.com.br.guiacomercialtcc.dto;

import java.util.List;

public class ClientEmpresaDTO {

    private List<EmpresaBuscaDTO> empresas;
    private boolean mais;
    private int total;

    public List<EmpresaBuscaDTO> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<EmpresaBuscaDTO> empresas) {
        this.empresas = empresas;
    }

    public boolean isMais() {
        return mais;
    }

    public void setMais(boolean mais) {
        this.mais = mais;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
