import { Deserializable } from "../model/deserializable.model";
import { Estado } from "../model/uf.model";

export class Cidade implements Deserializable {
  id: number;
  nome: string;
  estado: Estado;

  constructor() {
    this.estado = new Estado();
  }

  deserialize(input: any) {
    Object.assign(this, input);
    this.estado = new Estado().deserialize(input.estado);
    return this;
  }

}
