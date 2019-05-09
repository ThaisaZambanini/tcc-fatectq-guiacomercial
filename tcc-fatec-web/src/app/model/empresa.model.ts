import { Deserializable } from "../model/deserializable.model";
import { Categoria } from "../model/categoria.model";

export class Empresa implements Deserializable {
  id: number;
  nome: string;
  categoria: Categoria;

  deserialize(input: any) {
    Object.assign(this, input);
    this.categoria = new Categoria().deserialize(input.estado);
    return this;
  }

}
