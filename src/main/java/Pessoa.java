import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Pessoa {

    @Getter @Setter private String nome;
    @Getter @Setter private String cpf;
    @Getter @Setter private String endereco;
    @Getter @Setter private String telefone;

}
