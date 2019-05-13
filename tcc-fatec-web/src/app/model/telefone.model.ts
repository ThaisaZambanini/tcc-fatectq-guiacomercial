import { Deserializable } from "../model/deserializable.model";

export class Telefone implements Deserializable {

  id: number;
  tipo: string;
  ddd: number;
  numero: number;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }
}
