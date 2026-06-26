# 🏥 Clínica VidaPlena

> Sistema de gestão clínica em Java, desenvolvido para a evolução AV2 da disciplina de Programação Orientada a Objetos.

A **Clínica VidaPlena** é uma aplicação de console voltada ao gerenciamento de uma clínica multidisciplinar. O sistema cobre o fluxo operacional de pacientes, profissionais, consultas, atendimentos, prontuários, convênios, pagamentos e relatórios.

A proposta desta versão não é reconstruir o projeto do zero, mas evoluir a base anterior com uma modelagem mais limpa, orientada a objetos e preparada para regras de negócio mais complexas.

---

## ✦ Visão do Sistema

A clínica atende quatro áreas principais:

- Clínica Geral
- Fisioterapia
- Psicologia
- Nutrição

O sistema permite executar operações essenciais da rotina clínica:

- Cadastro simplificado e completo de pacientes.
- Complementação e desativação de pacientes.
- Cadastro de profissionais por especialidade.
- Agendamento por profissional ou por especialidade.
- Cancelamento, remarcação e controle de conflitos de horário.
- Registro de atendimento com prontuário.
- Pagamentos por dinheiro/pix, cartão ou convênio.
- Relatórios operacionais, financeiros e cadastrais.
- Exportação textual de dados para conferência.

---

## 🧭 Fluxo Principal

```text
Paciente cadastrado
      ↓
Profissional configurado
      ↓
Consulta agendada
      ↓
Atendimento registrado
      ↓
Prontuário gerado
      ↓
Pagamento processado
      ↓
Relatórios atualizados
```

---

## 🧱 Modelo Orientado a Objetos

A versão AV2 organiza o domínio em hierarquias, contratos e relacionamentos.

```text
Pessoa
├── Paciente
└── Profissional
    ├── Fisioterapeuta
    ├── Psicologo
    ├── Nutricionista
    └── ClinicoGeral

Pagamento
├── PagamentoDinheiro
├── PagamentoCartao
└── PagamentoConvenio

Consulta implements Agendavel, Exportavel
Atendimento implements Exportavel
Pagamento implements Exportavel
```

### Relacionamentos principais

```text
Paciente ─────────── Convenio              associação
Profissional ◇────── HorarioDisponivel     agregação
Atendimento ◆─────── Prontuario            composição
Consulta ─────────── Paciente
Consulta ─────────── Profissional
```

---

## ⚙️ Conceitos Aplicados

| Conceito | Aplicação |
|---|---|
| Encapsulamento | Atributos privados/protegidos com getters e setters. |
| Herança | `Pessoa → Profissional → Especializações`. |
| Classes abstratas | `Pessoa`, `Profissional` e `Pagamento`. |
| Sobrescrita | `exibirResumo()`, `registrarEspecifico()` e `calcularValorFinal()`. |
| Sobrecarga | Construtores e métodos com variações de parâmetros. |
| Polimorfismo | Listas de `Pessoa`, `Pagamento` e `Exportavel`. |
| Ligação dinâmica | Execução do método conforme o tipo real do objeto. |
| Interfaces | `Agendavel` e `Exportavel`. |
| Coleções | `ArrayList`, `HashSet` e `HashMap`. |
| Exceções | Tratamento específico para regras inválidas do domínio. |

---

## 🩺 Especialidades

Cada especialidade possui dados e comportamento clínico próprio.

| Classe | Informação específica |
|---|---|
| `Fisioterapeuta` | Total de sessões previstas. |
| `Psicologo` | Abordagem terapêutica. |
| `Nutricionista` | Plano alimentar. |
| `ClinicoGeral` | Encaminhamentos. |

---

## 💳 Pagamentos

O pagamento é tratado de forma polimórfica. Cada tipo calcula o valor final de maneira própria.

| Tipo | Regra |
|---|---|
| Dinheiro/Pix | Aplica desconto sobre o valor base. |
| Cartão | Permite parcelamento e valida limite de parcelas. |
| Convênio | Aplica cobertura conforme convênio e especialidade. |

Convênios são objetos do sistema, com nome, percentual de cobertura e lista de especialidades cobertas.

---

## ⚠️ Exceções de Negócio

O sistema utiliza exceções personalizadas para manter a aplicação estável durante operações inválidas.

Exemplos:

- `PacienteInativoException`
- `PacienteNaoEncontradoException`
- `ProfissionalNaoEncontradoException`
- `HorarioIndisponivelException`
- `ConsultaNaoEncontradaException`
- `OperacaoInvalidaException`
- `PagamentoInvalidoException`
- `ConvenioNaoCobreException`

As entradas numéricas são tratadas com `try/catch`, evitando encerramento inesperado da aplicação.

---

## 🗃️ Estruturas de Dados

```java
ArrayList<Consulta> consultas;
HashSet<String> cpfsCadastrados;
HashMap<String, Paciente> pacientesPorCpf;
HashMap<String, Profissional> profissionaisPorNome;
```

Uso previsto:

- `ArrayList`: listas editáveis de entidades.
- `HashSet`: controle de CPFs únicos.
- `HashMap`: busca rápida por CPF ou nome.

---

## ▶️ Como Executar

Requisito:

```text
Java JDK 11+
```

Compilar:

```bash
javac *.java
```

Executar:

```bash
java Main
```

---

## 🧪 Roteiro Rápido de Teste

1. Cadastrar paciente.
2. Cadastrar profissional especializado.
3. Configurar horários disponíveis.
4. Agendar consulta.
5. Testar conflito de horário.
6. Registrar atendimento.
7. Gerar prontuário.
8. Processar pagamento.
9. Emitir relatório.
10. Exportar dados.

---

## 📊 Relatórios

O sistema oferece relatórios para acompanhamento operacional e financeiro:

- Consultas gerais.
- Consultas por profissional.
- Consultas por período.
- Pagamentos.
- Cancelamentos e multas.
- Cadastros unificados.
- Dados exportáveis.



## 👥 Integrantes

```text
- Caíque Brito 
- Davi Dantas 
- Kaio Souto 
- Júlia Amaral 
```

---

## 📌 Status

Projeto acadêmico desenvolvido para demonstrar domínio de Programação Orientada a Objetos em Java, com execução via terminal e persistência em memória.

Sem frameworks, banco de dados ou interface gráfica.
