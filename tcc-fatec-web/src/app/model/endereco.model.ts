import { Deserializable } from "../model/deserializable.model";
import { Cidade } from "../model/cidade.model";

export class Endereco implements Deserializable {

  id: number;
  logradouro: string;
  cep: string;
  bairro: string;
  numero: string;
  complemento: string;
  cidade: Cidade;

  deserialize(input: any) {
    Object.assign(this, input);
    this.cidade = new Cidade().deserialize(input.cidade);
    return this;
  }

}
