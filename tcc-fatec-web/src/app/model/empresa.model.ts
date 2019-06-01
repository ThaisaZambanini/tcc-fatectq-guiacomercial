import { Deserializable } from "../model/deserializable.model";
import { Categoria } from "../model/categoria.model";
import { Endereco } from "../model/endereco.model";
import { Telefone } from "../model/telefone.model";
import { Horario } from "../model/horario.model";
import { FormaPagamento } from "../model/forma-pagamento.model";

export class Empresa implements Deserializable {
  id: number;
  nome: string;
  logo: string;
  email: string;
  linkSite: string;
  linkFacebook: string;
  linkInstagram: string;
  linkTwitter: string;
  categoria: Categoria;
  endereco: Endereco;
  telefones: Telefone[];
  horarios: Horario[];
  listaFormaPagamento: FormaPagamento[];
  listaTelefonesRemovidos: Telefone[];
  listaHorariosRemovidos: Horario[];
  listaFormaPagamentoRemovido: FormaPagamento[];

  constructor() {
    this.categoria = new Categoria();
    this.endereco = new Endereco();
    this.telefones = new Array();
    this.horarios = new Array();
    this.listaFormaPagamento = new Array();
    this.listaTelefonesRemovidos = new Array();
    this.listaHorariosRemovidos = new Array();
    this.listaFormaPagamentoRemovido = new Array();
  }

  deserialize(input: any) {
    Object.assign(this, input);
    this.categoria = new Categoria().deserialize(input.categoria);
    this.endereco = new Endereco().deserialize(input.endereco);
    return this;
  }

}
