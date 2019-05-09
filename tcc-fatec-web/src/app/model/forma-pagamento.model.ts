import { Deserializable } from "../model/deserializable.model";

export class FormaPagamento implements Deserializable {
  id: number;
  descricao: string;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }

}
