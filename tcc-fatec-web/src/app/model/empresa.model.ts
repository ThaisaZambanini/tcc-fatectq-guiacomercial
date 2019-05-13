import { Deserializable } from "../model/deserializable.model";
import { Categoria } from "../model/categoria.model";
import { Endereco } from "../model/endereco.model";
import { Telefone } from "../model/telefone.model";

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
  telefone: Telefone[];

  deserialize(input: any) {
    Object.assign(this, input);
    this.categoria = new Categoria().deserialize(input.categoria);
    this.endereco = new Endereco().deserialize(input.endereco);
    return this;
  }

}
