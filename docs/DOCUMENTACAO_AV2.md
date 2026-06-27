# Documentacao AV2 - Clinica VidaPlena

Este documento registra a estrutura final do sistema e aponta onde os principais conceitos de Programacao Orientada a Objetos foram aplicados.

## 1. Visao Geral

A Clinica VidaPlena e um sistema de console em Java para gerenciamento de pacientes, profissionais, consultas, atendimentos, pagamentos e relatorios.

O projeto usa armazenamento em memoria por meio de colecoes Java. Nao ha banco de dados, interface grafica ou frameworks externos.

## 2. Diagrama de Classes

```text
Pessoa (abstract)
|-- Paciente
`-- Profissional (abstract)
    |-- ClinicoGeral
    |-- Fisioterapeuta
    |-- Psicologo
    `-- Nutricionista

Pagamento (abstract, implements Exportavel)
|-- PagamentoDinheiro
|-- PagamentoCartao
`-- PagamentoConvenio

Consulta implements Agendavel, Exportavel
Atendimento implements Exportavel

Atendimento *-- Prontuario
Profissional o-- HorarioDisponivel
Paciente --> Convenio

ClinicaServico
|-- List<Paciente>
|-- List<Profissional>
|-- List<Consulta>
|-- List<Atendimento>
|-- List<Pagamento>
|-- List<Pessoa>
|-- Set<String> cpfsCadastrados
|-- Map<String, Paciente> pacientesPorCpf
`-- Map<String, Profissional> profissionaisPorNome
```

## 3. Mapa dos Conceitos Aplicados

| Conceito | Onde aparece |
|---|---|
| Classe abstrata | `Pessoa`, `Profissional`, `Pagamento` |
| Heranca | `Paciente extends Pessoa`, `Profissional extends Pessoa`, especialidades estendem `Profissional` |
| Encapsulamento | Atributos privados com getters e setters nas entidades principais |
| Sobrecarga | Construtores de `Paciente`, `Profissional`, `Consulta`, `Atendimento`, `Pagamento` |
| Sobrescrita | `exibirResumo()`, `calcularValorFinal()`, `exportarDados()` |
| Polimorfismo | `List<Pessoa>`, `List<Pagamento>`, chamadas de `exibirResumo()` e `calcularValorFinal()` |
| Ligacao dinamica | O metodo executado depende do tipo real em listas polimorficas |
| Interfaces | `Agendavel` e `Exportavel` |
| Casting seguro | Relatorio unificado usa `instanceof` para tratar `Paciente` e `Profissional` |
| Associacao | `Paciente` possui referencia para `Convenio` |
| Agregacao | `Profissional` possui lista de `HorarioDisponivel` |
| Composicao | `Atendimento` cria e controla seu `Prontuario` |
| Colecoes | `ArrayList`, `HashSet`, `HashMap` em `ClinicaServico` |
| Tratamento de erro | Entradas numericas usam `try/catch` em `Main` |

## 4. Responsabilidades das Classes

| Classe | Responsabilidade |
|---|---|
| `Main` | Menus, leitura de dados e exibicao de mensagens no console |
| `ClinicaServico` | Armazenamento central e buscas principais do sistema |
| `Pessoa` | Dados comuns de pessoas |
| `Paciente` | Dados do paciente, convenio e status ativo/inativo |
| `Profissional` | Dados comuns dos profissionais, especialidade e agenda |
| `ClinicoGeral` | Profissional com encaminhamento |
| `Fisioterapeuta` | Profissional com total de sessoes previstas |
| `Psicologo` | Profissional com abordagem terapeutica |
| `Nutricionista` | Profissional com plano alimentar |
| `Consulta` | Agendamento, cancelamento, remarcacao e status da consulta |
| `Atendimento` | Registro de atendimento e criacao do prontuario |
| `Prontuario` | Observacoes, diagnostico, procedimentos e data de registro |
| `Pagamento` | Base abstrata para pagamentos |
| `PagamentoDinheiro` | Pagamento a vista |
| `PagamentoCartao` | Pagamento parcelado |
| `PagamentoConvenio` | Pagamento com cobertura de convenio |
| `Relatorio` | Relatorios gerais, financeiros, por periodo, por profissional e unificado |

## 5. Jornadas de Usuario

### Jornada 1 - Cadastro de Paciente

1. Usuario acessa o menu `Pacientes`.
2. Escolhe `Cadastrar`.
3. Informa nome e CPF.
4. Escolhe o tipo de cadastro.
5. O sistema cria um objeto `Paciente`.
6. `ClinicaServico` cadastra o paciente e controla CPF unico com `HashSet`.

### Jornada 2 - Cadastro de Profissional

1. Usuario acessa o menu `Profissionais`.
2. Escolhe `Cadastrar`.
3. Informa nome e especialidade.
4. O sistema normaliza a especialidade informada.
5. Conforme a especialidade, cria uma subclasse de `Profissional`.
6. O profissional e armazenado em lista e em `HashMap` para busca por nome.

### Jornada 3 - Agendamento de Consulta

1. Usuario acessa o menu `Consultas`.
2. Escolhe agendar por profissional ou por especialidade.
3. Informa paciente, data e horario.
4. O sistema verifica paciente ativo, profissional existente, agenda e conflito de horario.
5. Se os dados estiverem validos, cria uma `Consulta` com status `agendada`.

### Jornada 4 - Registro de Atendimento

1. Usuario acessa o menu `Atendimentos`.
2. Informa o indice da consulta.
3. O sistema permite registrar atendimento apenas para consulta agendada.
4. Usuario informa observacoes, diagnostico e procedimentos.
5. O sistema cria um `Atendimento`.
6. O `Atendimento` cria seu proprio `Prontuario`.
7. A consulta passa para status `realizada`.

### Jornada 5 - Pagamento

1. Usuario acessa o menu `Pagamentos`.
2. Escolhe pagamento direto ou automatico.
3. Informa valor e tipo de pagamento.
4. O sistema cria uma subclasse de `Pagamento`.
5. O calculo final ocorre por polimorfismo via `calcularValorFinal()`.

### Jornada 6 - Relatorios

1. Usuario acessa o menu `Relatorios`.
2. Pode gerar relatorio geral, por profissional, por periodo, financeiro ou unificado.
3. O relatorio unificado usa `List<Pessoa>` e `instanceof` para diferenciar pacientes e profissionais.

## 6. Observacoes Sobre o Escopo Atual

O projeto contempla os principais requisitos de modelagem orientada a objetos do roteiro: heranca, abstracao, interfaces, polimorfismo, colecoes e relacionamentos.

As excecoes personalizadas citadas no roteiro ainda nao foram implementadas como classes proprias. No estado atual, o tratamento de erros ocorre principalmente por validacoes, mensagens ao usuario e blocos `try/catch` para entradas numericas.

## 7. Como Compilar e Executar

Compilar a partir da raiz do projeto:

```bash
javac src/*.java
```

Executar:

```bash
java -cp src Main
```