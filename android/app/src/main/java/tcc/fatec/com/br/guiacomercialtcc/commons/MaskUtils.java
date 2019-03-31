package tcc.fatec.com.br.guiacomercialtcc.commons;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import tcc.fatec.com.br.guiacomercialtcc.enums.TipoMascara;

public abstract class MaskUtils {
    static boolean isUpdating;
    static String old = "";
    private static final String maskCNPJ = "##.###.###/####-##";
    private static final String maskCPF = "###.###.###-##";
    private static final String maskData = "##/##/####";
    private static final String maskTelefone = "(##) ####-####";
    private static final String maskCelular = "(##) #####-####";
    private static final String maskCEP = "#####-###";

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public static void setMaksInput(final EditText editText, final TipoMascara tipo) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = unmask(s.toString());
                String mask;
                String defaultMask = getDefaultMask(str, tipo);

                mask = mascara(str, defaultMask, tipo);

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private static String getDefaultMask(String str, TipoMascara tipo) {
        if (tipo == TipoMascara.CPF_CNPJ) {
            String defaultMask = maskCPF;
            if (str.length() > 11) {
                defaultMask = maskCNPJ;
            }
            return defaultMask;
        } else if (tipo == TipoMascara.TELEFONE_CELULAR) {
            String defaultMask = maskTelefone;
            if (str.length() > 10) {
                defaultMask = maskCelular;
            }
            return defaultMask;
        } else if (tipo == TipoMascara.DATA_NASCIMENTO) {
            return maskData;
        } else if (tipo == TipoMascara.CNPJ) {
            return maskCNPJ;
        } else if (tipo == TipoMascara.CEP) {
            return maskCEP;
        }
        return "";
    }

    private static String mascara(String str, String defaultMask, TipoMascara tipo) {
        String mask = "";

        if (tipo == TipoMascara.CPF_CNPJ) {
            switch (str.length()) {
                case 11:
                    mask = maskCPF;
                    break;
                case 14:
                    mask = maskCNPJ;
                    break;
                default:
                    mask = defaultMask;
                    break;
            }
        } else if (tipo == TipoMascara.DATA_NASCIMENTO) {
            mask = maskData;
        } else if (tipo == TipoMascara.TELEFONE_CELULAR) {
            switch (str.length()) {
                case 10:
                    mask = maskTelefone;
                    break;
                case 11:
                    mask = maskCelular;
                    break;
                default:
                    mask = defaultMask;
                    break;
            }
        } else if (tipo == TipoMascara.CNPJ) {
            mask = maskCNPJ;
        } else if (tipo == TipoMascara.CEP) {
            return maskCEP;
        }
        return mask;
    }
}
