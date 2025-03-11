# PROCESSO DE CRIAÇÃO DO MICRO SERVIÇO DE USUÁRIO


## Criação do Projeto Java com Spring Boot:

## Configuração do projeto no Spring Initializr:

Project: Maven

Language: Java

Spring Boot: 3.4.3

Project Metadata:

Group: com.meusaas

Artifact: usuario-service

name: usuario-service

Description: SaaS em Java com Spring Boot e AWS

Package name: com.meusaas.usuario

## Dependências:

## Spring Web:
A dependência Spring Web é um dos pilares do ecossistema Spring e fornece todas as ferramentas necessárias para desenvolver aplicações web robustas – desde aplicações MVC tradicionais até APIs RESTful modernas. A seguir, descrevo em detalhes suas principais funcionalidades e utilidades, acompanhadas de exemplos.


---

### 1. Componentes Centrais e Arquitetura

DispatcherServlet:

No coração do Spring Web está o DispatcherServlet, que atua como um front controller. Ele intercepta todas as requisições HTTP e delega o processamento para os componentes adequados (controladores, interceptadores, resolvers de view etc.). Essa abordagem centralizada facilita o gerenciamento e a extensão do comportamento da aplicação.

HandlerMapping, HandlerAdapter e ViewResolver

HandlerMapping: Mapeia URLs para os respectivos controladores (controllers).

HandlerAdapter: Adapta a execução do método do controlador, permitindo que ele receba e processe a requisição de forma adequada.

ViewResolver: Define como as views (páginas HTML, por exemplo) serão localizadas e renderizadas, permitindo o desacoplamento entre lógica e apresentação.



---

### 2. Desenvolvimento de APIs RESTful e Aplicações MVC

Anotações e Controladores

O Spring Web utiliza anotações para facilitar o desenvolvimento de endpoints web:

@Controller e @RestController:

@Controller: Usada em aplicações MVC onde os métodos retornam nomes de views que serão renderizadas (por exemplo, com Thymeleaf ou JSP).

@RestController: Combina @Controller e @ResponseBody, facilitando a criação de APIs REST. Os métodos retornam diretamente os dados (geralmente em JSON ou XML).


@RequestMapping, @GetMapping, @PostMapping, etc.:
Permitem mapear métodos do controlador para URLs específicas e definir o método HTTP (GET, POST, PUT, DELETE).


### Exemplo 1 – API REST com @RestController:

    @RestController
    @RequestMapping("/api")
    public class HelloController {
    
        @GetMapping("/hello")
        public ResponseEntity<String> hello() {
            return ResponseEntity.ok("Olá, mundo!");
        }
    }

Nesse exemplo, a requisição GET para /api/hello retornará a mensagem "Olá, mundo!" em formato de resposta HTTP com status 200.

### Exemplo 2 – Aplicação MVC com @Controller:

    @Controller
    public class HomeController {
    
        @GetMapping("/")
        public String home(Model model) {
             model.addAttribute("mensagem", "Bem-vindo ao Spring Web!");
             return "home"; // Nome da view (ex.: home.html no Thymeleaf)
        }
    }

Aqui, a URL raiz (/) é mapeada para um método que adiciona um atributo ao modelo e retorna o nome da view a ser renderizada.


---

### 3. Conversores de Mensagens (Message Converters)

Uma das funcionalidades chave do Spring Web é a capacidade de converter automaticamente os dados entre formatos (como JSON, XML, etc.) e objetos Java. Essa conversão é feita através dos Message Converters. Por exemplo, ao utilizar a anotação @RequestBody, o Spring converte automaticamente o corpo da requisição (em JSON ou XML) em um objeto Java, e com @ResponseBody (ou @RestController) o objeto é serializado para o formato adequado na resposta.


---

### 4. Validação e Manipulação de Dados

Suporte à Validação:
A integração com o Bean Validation (por exemplo, Hibernate Validator) permite validar dados de entrada com anotações como @Valid e @NotNull, garantindo que as requisições recebam dados corretos.

Binding e Conversão de Dados:
O Spring Web gerencia a conversão de parâmetros de requisição (query parameters, path variables, etc.) para tipos Java, facilitando a manipulação dos dados enviados pelo cliente.



---

### 5. Tratamento de Exceções

O framework oferece mecanismos avançados para tratamento de erros:

@ExceptionHandler: Permite definir métodos específicos para tratar exceções lançadas em controladores.

@ControllerAdvice: Facilita a centralização do tratamento de exceções para toda a aplicação, permitindo um gerenciamento global dos erros.



---

### 6. Interceptadores e Filtros

Interceptors: Permitem interceptar e modificar requisições e respostas antes e depois da execução do controlador. São úteis para implementar funcionalidades como logging, autenticação, ou auditoria.

Filtros: São componentes do Java Servlet que podem ser aplicados antes do DispatcherServlet. Embora não sejam exclusivos do Spring, são integrados para complementar funcionalidades da aplicação web.



---

### 7. Suporte a Upload de Arquivos e Conteúdo Multipart

O Spring Web possui suporte nativo para processamento de requisições multipart, permitindo o upload de arquivos com facilidade. Basta configurar as propriedades de multipart e utilizar a anotação @RequestParam para capturar o arquivo enviado.

### Exemplo – Upload de Arquivo:

    @RestController
    public class FileUploadController {
    
        @PostMapping("/upload")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            // Processa o arquivo recebido
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Arquivo vazio!");
            }
            // Lógica para salvar o arquivo
            return ResponseEntity.ok("Arquivo " + file.getOriginalFilename() + " enviado com sucesso!");
        }
    }


---

### 8. Suporte a Requisições Assíncronas

Para melhorar a escalabilidade, o Spring Web permite o processamento assíncrono de requisições usando, por exemplo, Callable ou DeferredResult. Essa funcionalidade libera o thread de atendimento enquanto a tarefa é executada em background.

### Exemplo – Processamento Assíncrono com Callable:

    @RestController
    public class AsyncController {
    
        @GetMapping("/async")
        public Callable<String> async() {
             return () -> {
                  // Processamento demorado em background
                  Thread.sleep(2000); // Simula processamento
                  return "Processamento assíncrono completo!";
             };
        }
    }


---

### 9. Testes de Aplicações Web

O Spring Web também fornece ferramentas para testes, como o MockMvc, que permite testar os controladores e a lógica web sem a necessidade de subir um servidor real. Isso facilita a criação de testes unitários e de integração para as camadas web.


---

### 10. Configuração e Integração

Quando você inclui a dependência spring-boot-starter-web (usada em projetos Spring Boot), o framework:

Configura automaticamente o DispatcherServlet.

Integra um servidor embutido (como o Tomcat) para facilitar o desenvolvimento e o deploy.

Realiza a configuração dos Message Converters, validadores e outros componentes de forma automática.


### Exemplo de dependência no Maven:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

Essa dependência reúne todos os componentes mencionados e facilita o início do desenvolvimento de aplicações web com Spring.


---

### Conclusão

A dependência Spring Web é extremamente versátil e poderosa, proporcionando:

Desenvolvimento de APIs RESTful e aplicações web MVC: Por meio de anotações que simplificam o mapeamento de rotas e a serialização/deserialização de dados.

Conversão automática de mensagens e validação de dados: Reduzindo o boilerplate e aumentando a robustez da aplicação.

Tratamento centralizado de exceções, interceptadores e filtros: Permitindo personalizar o fluxo de requisições e respostas.

Suporte a uploads de arquivos e processamento assíncrono: Melhorando a experiência do usuário e a escalabilidade.


Essas funcionalidades fazem do Spring Web uma escolha ideal para projetos que vão desde pequenas APIs até grandes aplicações corporativas, onde a robustez, a flexibilidade e a escalabilidade são essenciais.

## Spring Data JPA:

O Spring Data JPA é uma parte do ecossistema Spring que simplifica a implementação da camada de persistência utilizando a API de Persistência Java (JPA). Ele abstrai grande parte da complexidade envolvida na manipulação de bancos de dados relacionais, permitindo que você foque na lógica de negócio e reduza a quantidade de código boilerplate. A seguir, veremos em detalhes suas funcionalidades, utilidades e exemplos práticos.

### 1. Abstração de Repositórios
Uma das principais funcionalidades do Spring Data JPA é a criação de repositórios (interfaces) que estendem interfaces pré-definidas, como:

CrudRepository: Fornece métodos básicos de CRUD (criar, ler, atualizar, deletar).
PagingAndSortingRepository: Além do CRUD, oferece suporte à paginação e ordenação.
JpaRepository: Estende as funcionalidades dos repositórios anteriores e adiciona métodos específicos da JPA.

### Exemplo Básico de Entidade e Repositório
---
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    
    @Entity
    public class Cliente {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        private String nome;
        
        // Construtores, getters e setters
        public Cliente() {}
    
        public Cliente(String nome) {
            this.nome = nome;
        }
    
        public Long getId() {
            return id;
        }
    
        public String getNome() {
            return nome;
        }
    
        public void setNome(String nome) {
            this.nome = nome;
        }
    }
---
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import java.util.List;
    
    public interface ClienteRepository extends JpaRepository<Cliente, Long> {
        
        // Query derivada do nome do método (Spring Data cria a implementação automaticamente)
        List<Cliente> findByNome(String nome);
        
        // Exemplo de consulta customizada com @Query
        @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
        List<Cliente> findByNomeLike(@Param("nome") String nome);
    }
---

Nesta abordagem, o Spring Data JPA gera automaticamente as implementações dos métodos com base na assinatura dos métodos da interface, eliminando a necessidade de escrever implementações manuais.

### 2. Derivação de Consultas
O framework permite criar métodos de consulta com base na nomenclatura do método. Por exemplo, o método findByNome indica que deve ser gerada uma consulta para buscar clientes com um determinado nome. Essa convenção torna a escrita de queries simples e intuitiva.

### Exemplo:
    List<Cliente> findByNome(String nome);
O Spring Data JPA converte isso em uma consulta SQL que busca por registros onde o campo nome seja igual ao parâmetro passado.

### 3. Consultas Customizadas
Além da derivação automática, você pode definir consultas mais complexas utilizando a anotação @Query para especificar a consulta JPQL (Java Persistence Query Language) ou SQL nativo.

### Exemplo com JPQL:

    @Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
    List<Cliente> findByNomeLike(@Param("nome") String nome);

### Exemplo com SQL Nativo:

    @Query(value = "SELECT * FROM cliente WHERE nome LIKE %:nome%", nativeQuery = true)
    List<Cliente> findByNomeNative(@Param("nome") String nome);

### 4. Paginação e Ordenação
Para trabalhar com grandes volumes de dados, o Spring Data JPA oferece suporte a paginação e ordenação de forma simples. Utilizando a interface PagingAndSortingRepository ou métodos do JpaRepository, você pode definir facilmente como os dados serão paginados e ordenados.

###Exemplo de Paginação:

    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    
    public interface ClienteRepository extends JpaRepository<Cliente, Long> {
        Page<Cliente> findByNome(String nome, Pageable pageable);
    }

Na camada de serviço ou controller, você pode criar um objeto PageRequest para definir a página e o tamanho:

    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    
    Pageable pageable = PageRequest.of(0, 10); // Primeira página com 10 registros
    Page<Cliente> clientes = clienteRepository.findByNome("João", pageable);
    
### 5. Suporte a Transações
O Spring Data JPA integra-se perfeitamente com a gestão de transações do Spring. Geralmente, as operações dos repositórios já estão envolvidas em uma transação, mas você pode utilizar a anotação @Transactional em métodos de serviço para controlar melhor o comportamento transacional.

### Exemplo:

    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    
    @Service
    public class ClienteService {
        
        private final ClienteRepository clienteRepository;
        
        public ClienteService(ClienteRepository clienteRepository) {
            this.clienteRepository = clienteRepository;
        }
        
        @Transactional
        public Cliente salvarCliente(Cliente cliente) {
            return clienteRepository.save(cliente);
        }
    }
    
### 6. Auditoria
O Spring Data JPA também oferece suporte à auditoria, permitindo rastrear automaticamente quando e por quem uma entidade foi criada ou modificada. Isso é feito utilizando anotações como @CreatedDate, @LastModifiedDate, entre outras, em conjunto com a configuração de auditoria.

### Exemplo de Auditoria:

    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.LastModifiedDate;
    import org.springframework.data.jpa.domain.support.AuditingEntityListener;
    import javax.persistence.EntityListeners;
    import javax.persistence.MappedSuperclass;
    import java.time.LocalDateTime;
    
    @MappedSuperclass
    @EntityListeners(AuditingEntityListener.class)
    public abstract class Auditable {
        
        @CreatedDate
        private LocalDateTime dataCriacao;
        
        @LastModifiedDate
        private LocalDateTime dataAtualizacao;
        
        // Getters e setters
        public LocalDateTime getDataCriacao() {
            return dataCriacao;
        }
        
        public LocalDateTime getDataAtualizacao() {
            return dataAtualizacao;
        }
    }

Depois, suas entidades podem estender essa classe para herdar automaticamente esses atributos.

### 7. Outras Funcionalidades e Utilidades

Consultas com Query By Example (QBE):
Permite a criação de consultas baseadas em um exemplo de objeto, o que pode ser útil em cenários dinâmicos.

Suporte a Projeções:
Permite retornar apenas partes de uma entidade (DTOs), evitando o carregamento desnecessário de dados.

Integração com o Spring Boot:
Quando usado com Spring Boot, a configuração do Spring Data JPA se torna ainda mais simples, com a auto-configuração do DataSource, do EntityManager e do gerenciamento de transações.

Customização do Repositório:
É possível definir implementações customizadas para métodos específicos do repositório, quando a derivação automática ou as consultas anotadas não atendem às necessidades.

### Conclusão
O Spring Data JPA é uma ferramenta poderosa que agiliza e simplifica o desenvolvimento de aplicações Java que necessitam interagir com bancos de dados relacionais. Suas funcionalidades – desde a derivação de queries até o suporte a paginação, ordenação e auditoria – reduzem a quantidade de código manual e promovem a manutenção e a escalabilidade da aplicação.

Com exemplos práticos como a criação de uma entidade, a definição de um repositório e a utilização de consultas customizadas, é possível perceber como o Spring Data JPA torna o desenvolvimento mais produtivo e menos sujeito a erros, permitindo que você foque na lógica de negócio e não nos detalhes da persistência de dados.

Esta abordagem modular e altamente integrada ao Spring torna o Spring Data JPA uma escolha popular para projetos que requerem uma camada de acesso a dados robusta e eficiente.

## MySQL Driver:

A dependência MySQL Driver – também conhecida como MySQL Connector/J – é o driver JDBC oficial fornecido pela Oracle para conectar aplicações Java a bancos de dados MySQL. Ela implementa as especificações da JDBC API, permitindo que os desenvolvedores realizem operações de CRUD, gerenciamento de transações e manipulação de dados de forma simples e integrada com o ecossistema Java.

### 1. Configuração e Instalação
Para utilizar o MySQL Connector/J, é necessário adicioná-lo ao projeto. Em projetos Maven, por exemplo, basta incluir a dependência no arquivo pom.xml:

---
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
---
Obs.: Verifique sempre a versão mais recente para aproveitar correções e novas funcionalidades.

### 2. Funcionalidades e Utilidades
   
### Conexão e Comunicação com o Banco de Dados
Driver JDBC Completo: Permite estabelecer conexões com o banco MySQL utilizando a classe DriverManager e o protocolo JDBC.
URL de Conexão Personalizável: A string de conexão pode incluir parâmetros para definir comportamento como uso de SSL, timezone, codificação de caracteres e opções de performance.
### Exemplo:
---
    String url = "jdbc:mysql://localhost:3306/meuBanco?useSSL=false&serverTimezone=UTC";
---   
### Execução de Consultas e Manipulação de Dados
Statements e PreparedStatements: Suporta execução de comandos SQL através de objetos Statement e PreparedStatement, permitindo consultas, atualizações e operações em lote.
ResultSet: Permite a manipulação dos resultados obtidos de uma consulta, possibilitando a leitura e iteração sobre os dados retornados.

### Gerenciamento de Transações
Controle de Auto-Commit: Possibilita ativar ou desativar o auto-commit para gerenciar transações manualmente, garantindo integridade dos dados em operações complexas.
Rollback e Commit: Após agrupar operações, o desenvolvedor pode confirmar (commit) ou reverter (rollback) a transação conforme a lógica do negócio.

### Recursos Avançados
Suporte a SSL e Conexões Seguras: Oferece suporte nativo para conexões criptografadas, garantindo segurança na transmissão dos dados.
Configurações de Performance: Diversos parâmetros de configuração (como caching de prepared statements, timeout, etc.) podem ser ajustados via URL ou propriedades do driver.
Compatibilidade com JDBC: Totalmente compatível com as versões mais recentes da especificação JDBC, facilitando a integração com frameworks e bibliotecas que dependem desse padrão.

### 3. Exemplos Práticos em Java
   
### Exemplo 1: Conexão Simples e Consulta de Dados
---
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.Statement;
    
    public class ExemploConexao {
        public static void main(String[] args) {
            // URL de conexão com parâmetros: sem SSL e timezone configurado
            String url = "jdbc:mysql://localhost:3306/meuBanco?useSSL=false&serverTimezone=UTC";
            String usuario = "root";
            String senha = "minhaSenha";
    
            try {
                // Registrar o driver (a partir do JDBC 4.0 não é obrigatório, mas pode ser incluído)
                Class.forName("com.mysql.cj.jdbc.Driver");
    
                // Estabelecendo a conexão
                Connection conexao = DriverManager.getConnection(url, usuario, senha);
                System.out.println("Conexão estabelecida com sucesso!");
    
                // Criação do Statement para executar uma consulta SQL
                Statement stmt = conexao.createStatement();
                String sql = "SELECT id, nome, email FROM usuarios";
                ResultSet rs = stmt.executeQuery(sql);
    
                // Iterando sobre os resultados
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    System.out.println("ID: " + id + ", Nome: " + nome + ", Email: " + email);
                }
    
                // Fechando recursos
                rs.close();
                stmt.close();
                conexao.close();
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
---
### Exemplo 2: Utilizando PreparedStatement e Gerenciamento de Transações
---
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    
    public class ExemploPreparedStatement {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/meuBanco?useSSL=false&serverTimezone=UTC";
            String usuario = "root";
            String senha = "minhaSenha";
    
            Connection conexao = null;
            PreparedStatement pstmt = null;
    
            try {
                conexao = DriverManager.getConnection(url, usuario, senha);
                // Desativando o auto-commit para controle manual de transações
                conexao.setAutoCommit(false);
    
                String sqlInsert = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
                pstmt = conexao.prepareStatement(sqlInsert);
    
                // Configurando parâmetros para o primeiro registro
                pstmt.setString(1, "João Silva");
                pstmt.setString(2, "joao.silva@example.com");
                pstmt.executeUpdate();
    
                // Configurando parâmetros para o segundo registro
                pstmt.setString(1, "Maria Souza");
                pstmt.setString(2, "maria.souza@example.com");
                pstmt.executeUpdate();
    
                // Comitando a transação se tudo ocorrer conforme o esperado
                conexao.commit();
                System.out.println("Inserção realizada com sucesso!");
    
            } catch (SQLException e) {
                // Revertendo as alterações em caso de erro
                if (conexao != null) {
                    try {
                        conexao.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                e.printStackTrace();
            } finally {
                // Fechando recursos
                try {
                    if (pstmt != null) pstmt.close();
                    if (conexao != null) conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
---
### 4. Conclusão
O MySQL Driver (Connector/J) é essencial para qualquer aplicação Java que necessite interagir com um banco de dados MySQL. Suas funcionalidades abrangem desde a simples conexão e execução de queries até o controle detalhado de transações e a implementação de recursos avançados como suporte a SSL e configurações de performance. Com exemplos claros em Java, é possível entender como aproveitar suas potencialidades e integrar de forma robusta e segura as operações com MySQL em aplicações Java.

Esta abordagem modular e orientada a boas práticas facilita a manutenção, o desempenho e a escalabilidade das aplicações, tornando o uso do MySQL Connector/J uma escolha consolidada no desenvolvimento Java moderno.
