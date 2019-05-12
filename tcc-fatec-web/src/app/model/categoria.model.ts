import { Deserializable } from "../model/deserializable.model";

export class Categoria implements Deserializable {
  id: number;
  nome: string;
  icone: string;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }

}
