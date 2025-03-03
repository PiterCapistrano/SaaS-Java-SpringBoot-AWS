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
Package name: com.meusaas.usuario-service

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