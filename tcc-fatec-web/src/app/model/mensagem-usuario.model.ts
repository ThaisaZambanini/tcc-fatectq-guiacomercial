import { Deserializable } from "../model/deserializable.model";

export class MensagemUsuario {
  id: number;
  nome: string;
  email: string;
  telefone: string;
  assunto: string;
  mensagem: string;

  deserialize(input: any) {
    Object.assign(this, input);
    return this;
  }
}
