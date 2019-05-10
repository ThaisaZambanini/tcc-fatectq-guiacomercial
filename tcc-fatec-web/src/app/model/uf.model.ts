import { Deserializable } from "../model/deserializable.model";

export class Estado implements Deserializable {
  id: number;
  nome: string;
  selected: boolean;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }

}
