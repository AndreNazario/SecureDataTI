# SecureDataTI

SecureDataTI é um projeto desenvolvido em Java com o objetivo de fornecer soluções para armazenamento, manipulação e proteção de dados sensíveis. Este repositório contém todo o código-fonte, documentação e exemplos necessários para utilizar e contribuir com o projeto.

## Índice

- [Sobre](#sobre)
- [Funcionalidades](#funcionalidades)
- [Requisitos](#requisitos)
- [Instalação](#instalação)
- [Uso](#uso)
- [Contribuição](#contribuição)
- [Licença](#licença)

## Sobre

O SecureDataTI visa atender demandas de segurança de dados em aplicações Java, garantindo confidencialidade, integridade e disponibilidade das informações, seguindo as melhores práticas de desenvolvimento seguro.

## Funcionalidades

- Armazenamento seguro de dados sensíveis
- Criptografia e descriptografia de informações
- Autenticação e autorização de usuários
- Logs de auditoria para ações realizadas
- Integração simples com aplicações Java existentes

## Requisitos

- Java 8 ou superior
- Maven ou Gradle para gerenciamento de dependências

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/AndreNazario/SecureDataTI.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd SecureDataTI
   ```
3. Instale as dependências usando Maven:
   ```bash
   mvn install
   ```
   Ou usando Gradle:
   ```bash
   gradle build
   ```

## Uso

Exemplo de utilização no seu projeto Java:

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthSystem auth = new AuthSystem();

        System.out.print("Usuário: ");
        String username = sc.nextLine();
        System.out.print("Senha: ");
        String password = sc.nextLine();

        User loggedUser = auth.login(username, password);

        if (loggedUser != null) {
            System.out.println("Login realizado com sucesso!");
            auth.showMenu(loggedUser, sc);
        } else {
            System.out.println("Login inválido!");
        }

        sc.close();
    }
}
```

Consulte a documentação dos métodos disponíveis para mais detalhes.

## Contribuição

Contribuições são bem-vindas! Para contribuir com o projeto:

1. Fork este repositório
2. Crie uma branch (`git checkout -b feature/nova-funcionalidade`)
3. Faça suas alterações e commits
4. Envie um Pull Request

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

Qualquer dúvida ou sugestão, entre em contato via [Issues](https://github.com/AndreNazario/SecureDataTI/issues).
