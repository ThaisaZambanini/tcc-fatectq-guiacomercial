import { Deserializable } from "../model/deserializable.model";

export class FiltroEmpresa implements Deserializable {
  estado: string;
  cidade: string;
  logradouro: string;
  cep: string;
  numero: string;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }

}
