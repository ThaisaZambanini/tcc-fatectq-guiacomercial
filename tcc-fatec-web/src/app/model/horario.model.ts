import { Deserializable } from "../model/deserializable.model";

export class Horario implements Deserializable {
  id: number;
  diaSemana: string;
  horarioInicial: string;
  horarioFinal: string;


  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }

}
