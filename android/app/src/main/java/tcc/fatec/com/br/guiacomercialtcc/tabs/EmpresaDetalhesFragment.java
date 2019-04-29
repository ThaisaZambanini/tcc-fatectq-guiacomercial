package tcc.fatec.com.br.guiacomercialtcc.tabs;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import tcc.fatec.com.br.guiacomercialtcc.R;
import tcc.fatec.com.br.guiacomercialtcc.adapter.AcaoArrayAdapter;
import tcc.fatec.com.br.guiacomercialtcc.commons.AppUtils;
import tcc.fatec.com.br.guiacomercialtcc.dto.CidadeDTO;
import tcc.fatec.com.br.guiacomercialtcc.model.Empresa;
import tcc.fatec.com.br.guiacomercialtcc.model.Endereco;
import tcc.fatec.com.br.guiacomercialtcc.model.TelefoneDTO;
import tcc.fatec.com.br.guiacomercialtcc.model.Usuario;
import tcc.fatec.com.br.guiacomercialtcc.to.BotaoAcaoTO;
import tcc.fatec.com.br.guiacomercialtcc.util.SessaoUtil;

public class EmpresaDetalhesFragment extends Fragment {
    private static Empresa dto;
    private Button btn_atualizar_dados;
    final private static int PERMISSAO_CHAMADA = 0;
    private String linkPermissao;
    private long id;

    public EmpresaDetalhesFragment() {
    }

    public static EmpresaDetalhesFragment newInstance(Empresa param1) {
        EmpresaDetalhesFragment fragment = new EmpresaDetalhesFragment();
        dto = param1;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empresa_detalhes, container, false);

        this.id = dto.getId();

        final ImageView logo = view.findViewById(R.id.imagem_logo);
        final TextView txt_nome = view.findViewById(R.id.text_nome_empresa_categoria);

        final TextView txt_endereco_linha1 = view.findViewById(R.id.text_endereco1_categoria);

        final TextView txt_endereco_linha2 = view.findViewById(R.id.text_endereco2_categoria);

        final TextView txt_endereco_linha3 = view.findViewById(R.id.text_endereco3_categoria);

        final TextView txt_categoria = view.findViewById(R.id.text_categoria);
        btn_atualizar_dados = view.findViewById(R.id.btn_atualizar_dados);

        final TextView estabelecimento_statusAberto = view.findViewById(R.id.estabelecimento_statusAberto);
        final TextView estabelecimento_statusFechado = view.findViewById(R.id.estabelecimento_statusFechado);

        final Uri uri = Uri.parse(dto.getLogo());
        logo.setImageURI(uri);

        txt_nome.setText(dto.getNome());
        txt_categoria.setText(dto.getCategoria().getNome());

        Endereco endereco = dto.getEndereco();
        if (endereco.getLinha1() != null) {
            txt_endereco_linha1.setText(endereco.getLinha1());
        }

        if (endereco.getLinha2() != null) {
            txt_endereco_linha2.setText(endereco.getLinha2());
        }

        if (StringUtils.isNotBlank(endereco.getLinha3())) {
            txt_endereco_linha3.setText(endereco.getLinha3());
        } else {
            txt_endereco_linha3.setVisibility(View.GONE);
        }

        if (dto.getStatus() != null && dto.getStatus().getTexto() == null || StringUtils.isEmpty(dto.getStatus().getTexto())) {
            estabelecimento_statusAberto.setVisibility(View.GONE);
            estabelecimento_statusFechado.setVisibility(View.GONE);
        } else {
            if (dto.getStatus() != null && dto.getStatus().isAberto()) {
                estabelecimento_statusAberto.setVisibility(View.VISIBLE);
                estabelecimento_statusFechado.setVisibility(View.GONE);
                estabelecimento_statusAberto.setText(dto.getStatus().getTexto());
            } else {
                estabelecimento_statusFechado.setVisibility(View.VISIBLE);
                estabelecimento_statusAberto.setVisibility(View.GONE);
                estabelecimento_statusFechado.setText(dto.getStatus().getTexto());
            }
        }

        final List<BotaoAcaoTO> acoes = new ArrayList<>(0);
        final CidadeDTO cidadeSessao = SessaoUtil.getCidade(getActivity().getApplicationContext());
        final ListView lv_contatos = view.findViewById(R.id.lista_contatos);

        if (!dto.getTelefones().isEmpty()) {
            for (final TelefoneDTO telefone : dto.getTelefones()) {
                if (telefone.getTipo().equals("W")) {
                    acoes.add(new BotaoAcaoTO(R.drawable.ic_whatsapp, telefone.getExibicao()) {
                        @Override
                        public void onClick() {
                            try {
                                Intent sendIntent = new Intent("android.intent.action.MAIN");
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra("jid", telefone.getDiscagem() + "@s.whatsapp.net");
                                sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                                sendIntent.setType("text/plain");
                                sendIntent.setPackage("com.whatsapp");
                                startActivity(sendIntent);
                            } catch (ActivityNotFoundException e) {
                                ligar(telefone.getDiscagem());
                            }
                        }
                    });
                } else if (telefone.getTipo().equals("C")) {
                    acoes.add(new BotaoAcaoTO(R.drawable.ic_phone_android, telefone.getExibicao()) {
                        @Override
                        public void onClick() {
                            ligar(telefone.getDiscagem());
                        }
                    });
                } else {
                    acoes.add(new BotaoAcaoTO(R.drawable.ic_call, telefone.getExibicao()) {
                        @Override
                        public void onClick() {
                            ligar(telefone.getDiscagem());
                        }
                    });
                }
            }
        }

        if (StringUtils.isNotBlank(dto.getLinkSite())) {
            acoes.add(new BotaoAcaoTO(R.drawable.ic_world, getString(R.string.site)) {
                @Override
                public void onClick() {
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(dto.getLinkSite())), ""));
                }
            });
        }

        if (StringUtils.isNotBlank(dto.getLinkFacebook())) {
            acoes.add(new BotaoAcaoTO(R.drawable.ic_facebook, getString(R.string.facebook)) {
                @Override
                public void onClick() {
                    try {
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        String facebookUrl = AppUtils.getFacebookPageURL(getActivity(), dto.getLinkFacebook());
                        facebookIntent.setData(Uri.parse(facebookUrl));
                        startActivity(facebookIntent);
                    } catch (ActivityNotFoundException e) {
                        startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(dto.getLinkFacebook())), ""));
                    }
                }
            });
        }

        if (StringUtils.isNotBlank(dto.getLinkInstagram())) {
            acoes.add(new BotaoAcaoTO(R.drawable.ic_instagram, getString(R.string.instagram)) {
                @Override
                public void onClick() {
                    try {
                        Uri uri2 = Uri.parse(dto.getLinkInstagram());
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                        intent2.setPackage("com.instagram.android");
                        startActivity(intent2);
                    } catch (ActivityNotFoundException e) {
                        startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(dto.getLinkInstagram())), ""));
                    }
                }
            });
        }

        if (StringUtils.isNotBlank(dto.getLinkTwitter())) {
            acoes.add(new BotaoAcaoTO(R.drawable.ic_twitter, getString(R.string.twitter)) {
                @Override
                public void onClick() {
                    startActivity(Intent.createChooser(new Intent(Intent.ACTION_VIEW, Uri.parse(dto.getLinkTwitter())), ""));
                }
            });
        }

        if (StringUtils.isNotBlank(dto.getEmail())) {
            acoes.add(new BotaoAcaoTO(R.drawable.ic_mail, getString(R.string.email)) {
                @Override
                public void onClick() {
                    Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
                    emailintent.setType("plain/text");
                    emailintent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{dto.getEmail()});
                    startActivity(Intent.createChooser(emailintent, "Enviar e-mail..."));
                }
            });
        }

        acoes.add(new BotaoAcaoTO(R.drawable.ic_share, getString(R.string.compartilhar)) {
            @Override
            public void onClick() {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");

                StringBuilder sb = new StringBuilder(EmpresaDetalhesFragment.dto.getNome());
                sb.append("\n");
                sb.append("\n");
                sb.append(EmpresaDetalhesFragment.dto.getEndereco().getLinha1());
                sb.append("\n");
                sb.append(EmpresaDetalhesFragment.dto.getEndereco().getLinha2());
                sb.append("\n");
                sb.append(cidadeSessao.getNome() + " - " + cidadeSessao.getEstadoDTO().getNome());

                for (TelefoneDTO telefone : EmpresaDetalhesFragment.dto.getTelefones()) {
                    sb.append("\n");
                    sb.append(telefone.getExibicao());
                }

                sb.append("\n");
                sb.append("\n");
                sb.append("\n");
                sb.append(EmpresaDetalhesFragment.dto.getLinkFoneja());

                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, EmpresaDetalhesFragment.dto.getLinkFoneja());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, sb.toString());
                startActivity(Intent.createChooser(sharingIntent, "Compartilhar..."));
            }
        });

        acoes.add(new BotaoAcaoTO(R.drawable.ic_map, getString(R.string.como_chegar)) {
            @Override
            public void onClick() {
                String enderecoLocalizacao = dto.getEndereco().getLinha1() + ", " + cidadeSessao.getNome() + " - " + cidadeSessao.getEstadoDTO().getNome() + " - " + dto.getEndereco().getCep();
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + enderecoLocalizacao + "_system");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        lv_contatos.setAdapter(new AcaoArrayAdapter(getActivity().getApplicationContext(), R.layout.item_adapter_acao, acoes));
        lv_contatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                acoes.get(position).onClick();
            }
        });
        AppUtils.setListViewHeightBasedOnChildren(lv_contatos);
        listenerButton();
        return view;
    }

    public void ligar(String numero) {
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + numero));
                startActivity(callIntent);
            } else {
                linkPermissao = numero;
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE},
                        PERMISSAO_CHAMADA);
            }
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + numero));
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSAO_CHAMADA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + linkPermissao));
                        startActivity(callIntent);
                    } catch (SecurityException e) {
                    }
                }
                return;
            }
        }
    }

    private void listenerButton() {
        btn_atualizar_dados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent i = new Intent(getActivity().getApplicationContext(), SolicitacaoAtualizacaoActivity.class);
                //startActivity(i);
            }
        });
    }
}
