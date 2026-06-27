import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ClinicaServico clinica = new ClinicaServico();

    static List<Paciente> pacientes = clinica.getPacientes();

    static List<Profissional> profissionais = clinica.getProfissionais();

    static List<Consulta> consultas = clinica.getConsultas();

    static List<Atendimento> atendimentos = clinica.getAtendimentos();

    static List<Pagamento> pagamentos = clinica.getPagamentos();

    static List<Double> multas = clinica.getMultas();

    static List<Pessoa> pessoas = clinica.getPessoas();

    static Scanner sc = new Scanner(System.in);

    public static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! Digite um numero inteiro.");
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida! Digite um numero valido.");
            }
        }
    }

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== CLINICA VIDAPLENA ===");
            System.out.println("1 - Pacientes");
            System.out.println("2 - Profissionais");
            System.out.println("3 - Consultas");
            System.out.println("4 - Atendimentos");
            System.out.println("5 - Pagamentos");
            System.out.println("6 - Relatorios");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Escolha: ");

            switch (opcao) {
                case 1: menuPacientes(); break;
                case 2: menuProfissionais(); break;
                case 3: menuConsultas(); break;
                case 4: menuAtendimentos(); break;
                case 5: menuPagamentos(); break;
                case 6: menuRelatorios(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
        System.out.println("Sistema encerrado.");
    }

    // ---- PACIENTES ----

    public static void menuPacientes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PACIENTES ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Complementar cadastro");
            System.out.println("3 - Buscar por CPF");
            System.out.println("4 - Listar todos");
            System.out.println("5 - Desativar");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");

            switch (op) {
                case 1: cadastrarPaciente(); break;
                case 2: complementarPaciente(); break;
                case 3: buscarPaciente(); break;
                case 4: listarPacientes(); break;
                case 5: desativarPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        // verifica se ja existe
        if (buscarIndicePaciente(cpf) != -1) {
            System.out.println("CPF ja cadastrado!");
            return;
        }

        int tipo = lerInteiro("Tipo (1-Minimo / 2-Com idade e tel / 3-Completo): ");

        Paciente paciente;

        if (tipo == 1) {
            paciente = new Paciente(nome, cpf);
        } else if (tipo == 2) {
            int idade = lerInteiro("Idade: ");
            System.out.print("Telefone: ");
            String tel = sc.nextLine();
            paciente = new Paciente(nome, cpf, idade, tel);
        } else {
            int idade = lerInteiro("Idade: ");
            System.out.print("Telefone: ");
            String tel = sc.nextLine();
            System.out.print("Convenio: ");
            String conv = sc.nextLine();
            paciente = new Paciente(nome, cpf, idade, tel, conv);
        }

        if (clinica.cadastrarPaciente(paciente)) {
            System.out.println("Paciente cadastrado com sucesso!");
        } else {
            System.out.println("CPF ja cadastrado!");
        }
    }

    public static void complementarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int idx = buscarIndicePaciente(cpf);
        if (idx == -1) {
            System.out.println("Paciente nao encontrado.");
            return;
        }

        int tipo = lerInteiro("Vai informar convenio? (1-Nao / 2-Sim): ");

        int idade = lerInteiro("Idade: ");
        System.out.print("Telefone: ");
        String tel = sc.nextLine();

        if (tipo == 1) {
            pacientes.get(idx).complementar(idade, tel);
        } else {
            System.out.print("Convenio: ");
            String conv = sc.nextLine();
            pacientes.get(idx).complementar(idade, tel, conv);
        }
        System.out.println("Cadastro atualizado!");
    }

    public static void buscarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int idx = buscarIndicePaciente(cpf);
        if (idx == -1) {
            System.out.println("Paciente nao encontrado.");
        } else {
            System.out.println(pacientes.get(idx).exibirResumo());
        }
    }

    public static void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (Paciente p : pacientes) {
            System.out.println(p.exibirResumo());
        }
    }

    public static void desativarPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        int idx = buscarIndicePaciente(cpf);
        if (idx == -1) {
            System.out.println("Paciente nao encontrado.");
        } else {
            pacientes.get(idx).desativar();
            System.out.println("Paciente desativado.");
        }
    }

    public static int buscarIndicePaciente(String cpf) {
        Paciente paciente = clinica.buscarPacientePorCpf(cpf);
        if (paciente == null) {
            return -1;
        }
        return pacientes.indexOf(paciente);
    }

    // ---- PROFISSIONAIS ----

    public static void menuProfissionais() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PROFISSIONAIS ---");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar cadastro");
            System.out.println("3 - Listar todos");
            System.out.println("4 - Filtrar por especialidade");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");

            switch (op) {
                case 1: cadastrarProfissional(); break;
                case 2: atualizarProfissional(); break;
                case 3: listarProfissionais(); break;
                case 4: filtrarProfissionais(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void cadastrarProfissional() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Especialidade (clinica geral/fisioterapia/psicologia/nutricao): ");
        String esp = sc.nextLine();

        if (!Profissional.especialidadeValida(esp)) {
            System.out.println("Especialidade invalida!");
            return;
        }

        Profissional profissional = null;

        int tipo = lerInteiro("Tipo (1-Minimo / 2-Com registro e valor / 3-Completo): ");

        if (tipo == 1) {
            if (esp.equals("clinica geral")) profissional = new ClinicoGeral(nome);
            else if (esp.equals("fisioterapia")) profissional = new Fisioterapeuta(nome);
            else if (esp.equals("psicologia")) profissional = new Psicologo(nome);
            else if (esp.equals("nutricao")) profissional = new Nutricionista(nome);
        } else if (tipo == 2) {
            System.out.print("Registro: ");
            String reg = sc.nextLine();
            double valor = lerDouble("Valor consulta: ");

            if (esp.equals("clinica geral")) profissional = new ClinicoGeral(nome, reg, valor);
            else if (esp.equals("fisioterapia")) profissional = new Fisioterapeuta(nome, reg, valor);
            else if (esp.equals("psicologia")) profissional = new Psicologo(nome, reg, valor);
            else if (esp.equals("nutricao")) profissional = new Nutricionista(nome, reg, valor);
        } else {
            System.out.print("Registro: ");
            String reg = sc.nextLine();
            double valor = lerDouble("Valor consulta: ");
            int qtd = lerInteiro("Quantos dias atende? ");
            List<String> dias = new ArrayList<>();
            if(qtd > 7){
                System.out.println("A semana só tem 7 dias!");
                return;
            }
            for (int i = 0; i < qtd; i++) {
                System.out.print("Dia " + (i+1) + ": ");
                dias.add(sc.nextLine());
            }
            if (esp.equals("clinica geral")) {
            System.out.print("Encaminhamento: ");
            String enc = sc.nextLine();
            profissional = new ClinicoGeral(nome, "000", 0, "", reg, valor, enc, dias);
            }else if (esp.equals("fisioterapia")){
                System.out.print("Sessões previstas: ");
                int ses = sc.nextInt();
                profissional = new Fisioterapeuta(nome, "000", 0, "", reg, valor, ses, dias);
            }else if (esp.equals("psicologia")){
                System.out.print("Sessões previstas: ");
                String abg = sc.nextLine();
                profissional = new Psicologo(nome, "000", 0, "", reg, valor, abg, dias);
            }else if (esp.equals("nutricao")){
                System.out.print("Plano Alimentar: ");
                String pa = sc.nextLine();
                profissional = new Nutricionista(nome, "000", 0, "", reg, valor, pa, dias);
            }
        }
        clinica.cadastrarProfissional(profissional);
        System.out.println("Profissional cadastrado!");
    }

    public static void atualizarProfissional() {
        System.out.print("Nome do profissional: ");
        String nome = sc.nextLine();
        int idx = buscarIndiceProfissional(nome);
        if (idx == -1) {
            System.out.println("Profissional nao encontrado.");
            return;
        }

        int tipo = lerInteiro("Vai informar dias? (1-Nao / 2-Sim): ");

        System.out.print("Registro: ");
        String reg = sc.nextLine();
        double valor = lerDouble("Valor consulta: ");

        if (tipo == 1) {
            profissionais.get(idx).atualizar(0, "", reg, valor);
        } else {
            int qtd = lerInteiro("Quantos dias? ");
            List<String> dias = new ArrayList<>();
            if(qtd > 7){
                System.out.println("A semana só tem 7 dias!");
                return;
            }
            for (int i = 0; i < qtd; i++) {
                System.out.print("Dia " + (i+1) + ": ");
                dias.add(sc.nextLine());
            }
            profissionais.get(idx).atualizar(0, "", reg, valor, dias);
        }
        System.out.println("Profissional atualizado!");
    }

    public static void listarProfissionais() {
        if (profissionais.isEmpty()) {
            System.out.println("Nenhum profissional cadastrado.");
            return;
        }
        for (Profissional p : profissionais) {
            System.out.println(p.exibirResumo());
        }
    }

    public static void filtrarProfissionais() {
        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        boolean achou = false;
        for (Profissional p : profissionais) {
            if (p.getEspecialidade().equals(esp)) {
                System.out.println(p.exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum profissional com essa especialidade.");
    }

    public static int buscarIndiceProfissional(String nome) {
        Profissional profissional = clinica.buscarProfissionalPorNome(nome);
        if (profissional == null) {
            return -1;
        }
        return profissionais.indexOf(profissional);
    }

    // ---- CONSULTAS ----

    public static void menuConsultas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- CONSULTAS ---");
            System.out.println("1 - Agendar (escolher profissional)");
            System.out.println("2 - Agendar (busca por especialidade)");
            System.out.println("3 - Cancelar");
            System.out.println("4 - Remarcar");
            System.out.println("5 - Listar todas");
            System.out.println("6 - Buscar por CPF");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");

            switch (op) {
                case 1: agendarComProfissional(); break;
                case 2: agendarPorEspecialidade(); break;
                case 3: cancelarConsulta(); break;
                case 4: remarcarConsulta(); break;
                case 5: listarConsultas(); break;
                case 6: buscarConsultasPorPaciente(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void agendarComProfissional() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        int idxPac = buscarIndicePaciente(cpf);
        if (idxPac == -1) {
            System.out.println("Paciente nao encontrado.");
            return;
        }
        if (!pacientes.get(idxPac).getAtivo()) {
            System.out.println("Paciente inativo. Nao e possivel agendar.");
            return;
        }

        System.out.print("Nome do profissional: ");
        String nomeProf = sc.nextLine();
        int idxProf = buscarIndiceProfissional(nomeProf);
        if (idxProf == -1) {
            System.out.println("Profissional nao encontrado.");
            return;
        }
        if (profissionais.get(idxProf).getValorConsulta() == 0) {
            System.out.println("Profissional sem valor definido. Nao pode agendar.");
            return;
        }

        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        // verifica dia da semana
        String diaSemana = descobrirDiaSemana(data);
        if (!profissionais.get(idxProf).atendeNoDia(diaSemana)) {
            System.out.println("Profissional nao atende nesse dia.");
            return;
        }

        // verifica conflito
        if (temConflito(nomeProf, data, horario)) {
            System.out.println("Horario ocupado!");
            String sugestao = sugerirHorario(nomeProf, data);
            if (sugestao.equals("")) {
                System.out.println("Nenhum horario disponivel nesse dia.");
                return;
            }
            System.out.println("Sugestao: " + sugestao);
            int aceita = lerInteiro("Aceita? (1-Sim / 2-Nao): ");
            if (aceita == 1) {
                horario = sugestao;
            } else {
                return;
            }
        }

        int infoTipo = lerInteiro("Informar tipo? (1-Nao / 2-Sim): ");

        if (infoTipo == 1) {
            clinica.adicionarConsulta(new Consulta(cpf, nomeProf, data, horario));
        } else {
            System.out.print("Tipo (inicial/retorno/avaliacao): ");
            String tipo = sc.nextLine();
            clinica.adicionarConsulta(new Consulta(cpf, nomeProf, data, horario, tipo));
        }
        System.out.println("Consulta agendada com sucesso!");
    }

    public static void agendarPorEspecialidade() {
        System.out.print("CPF do paciente: ");
        String cpf = sc.nextLine();
        int idxPac = buscarIndicePaciente(cpf);
        if (idxPac == -1) {
            System.out.println("Paciente nao encontrado.");
            return;
        }
        if (!pacientes.get(idxPac).getAtivo()) {
            System.out.println("Paciente inativo. Nao e possivel agendar.");
            return;
        }

        System.out.print("Especialidade: ");
        String esp = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        String diaSemana = descobrirDiaSemana(data);

        // procura profissional disponivel
        int idxProf = -1;
        for (int i = 0; i < profissionais.size(); i++) {
            if (profissionais.get(i).getEspecialidade().equals(esp)
                    && profissionais.get(i).getValorConsulta() > 0
                    && profissionais.get(i).atendeNoDia(diaSemana)
                    && !temConflito(profissionais.get(i).getNome(), data, horario)) {
                idxProf = i;
                break;
            }
        }

        if (idxProf == -1) {
            System.out.println("Nenhum profissional disponivel.");
            return;
        }

        clinica.adicionarConsulta(new Consulta(cpf, profissionais.get(idxProf).getNome(), data, horario));
        System.out.println("Consulta agendada com " + profissionais.get(idxProf).getNome() + "!");
    }

    public static void cancelarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Horario (HH:MM): ");
        String horario = sc.nextLine();

        // localiza a consulta
        int idx = -1;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getCpfPaciente().equals(cpf) && consultas.get(i).getData().equals(data)
                    && consultas.get(i).getHorario().equals(horario)) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println("Consulta nao encontrada.");
            return;
        }
        if (consultas.get(idx).getStatus().equals("realizada")) {
            System.out.println("Consulta ja realizada. Nao pode cancelar.");
            return;
        }
        if (consultas.get(idx).getStatus().equals("cancelada")) {
            System.out.println("Consulta ja cancelada.");
            return;
        }

        // calculo da multa
        System.out.print("Horario atual (HH:MM): ");
        String horaAtual = sc.nextLine();

        try {
            int hConsulta = Integer.parseInt(horario.substring(0, 2));
            int hAgora = Integer.parseInt(horaAtual.substring(0, 2));
            int diff = hConsulta - hAgora;

            if (diff < 2) {
                System.out.println("Multa de R$50.00 aplicada!");
                clinica.adicionarMulta(50.0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Horario invalido. Multa nao calculada.");
        }

        int temMotivo = lerInteiro("Informar motivo? (1-Nao / 2-Sim): ");

        if (temMotivo == 1) {
            consultas.get(idx).cancelar();
        } else {
            System.out.print("Motivo: ");
            String motivo = sc.nextLine();
            String msg = consultas.get(idx).cancelar(motivo);
            System.out.println(msg);
        }
        System.out.println("Consulta cancelada.");
    }

    public static void remarcarConsulta() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Data original (DD/MM/AAAA): ");
        String dataOrig = sc.nextLine();
        System.out.print("Horario original (HH:MM): ");
        String horarioOrig = sc.nextLine();

        int idx = -1;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getCpfPaciente().equals(cpf) && consultas.get(i).getData().equals(dataOrig)
                    && consultas.get(i).getHorario().equals(horarioOrig)
                    && consultas.get(i).getStatus().equals("agendada")) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            System.out.println("Consulta agendada nao encontrada.");
            return;
        }

        int tipo = lerInteiro("Apenas trocar horario no mesmo dia? (1-Sim / 2-Nao): ");

        String novaData;
        String novoHorario;

        if (tipo == 1) {
            novaData = dataOrig;
            System.out.print("Novo horario: ");
            novoHorario = sc.nextLine();
        } else {
            System.out.print("Nova data (DD/MM/AAAA): ");
            novaData = sc.nextLine();
            System.out.print("Novo horario (HH:MM): ");
            novoHorario = sc.nextLine();
        }

        String nomeProf = consultas.get(idx).getNomeProfissional();
        int idxProf = buscarIndiceProfissional(nomeProf);

        // se mudou de dia, verifica se prof atende
        if (tipo == 2) {
            String dia = descobrirDiaSemana(novaData);
            if (!profissionais.get(idxProf).atendeNoDia(dia)) {
                System.out.println("Profissional nao atende nesse dia.");
                return;
            }
        }

        if (temConflito(nomeProf, novaData, novoHorario)) {
            System.out.println("Horario ocupado. Nao foi possivel remarcar.");
            return;
        }

        consultas.get(idx).remarcar();
        clinica.adicionarConsulta(new Consulta(cpf, nomeProf, novaData, novoHorario, consultas.get(idx).getTipo()));
        System.out.println("Consulta remarcada com sucesso!");
    }

    public static void listarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta.");
            return;
        }
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println("[" + i + "] " + consultas.get(i).exibirResumo());
        }
    }

    public static void buscarConsultasPorPaciente() {
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        boolean achou = false;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getCpfPaciente().equals(cpf)) {
                System.out.println("[" + i + "] " + consultas.get(i).exibirResumo());
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma consulta encontrada.");
    }

    // verifica se ja tem consulta nesse horario com esse profissional
    public static boolean temConflito(String nomeProf, String data, String horario) {
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).getNomeProfissional().equals(nomeProf)
                    && consultas.get(i).getData().equals(data)
                    && consultas.get(i).getHorario().equals(horario)
                    && consultas.get(i).getStatus().equals("agendada")) {
                return true;
            }
        }
        return false;
    }

    // sugere proximo horario livre (de hora em hora, 08h ate 18h)
    public static String sugerirHorario(String nomeProf, String data) {
        for (int h = 8; h <= 18; h++) {
            String teste;
            if (h < 10) {
                teste = "0" + h + ":00";
            } else {
                teste = h + ":00";
            }
            if (!temConflito(nomeProf, data, teste)) {
                return teste;
            }
        }
        return "";
    }

    // descobre dia da semana a partir da data
    // operação opcional
    public static String descobrirDiaSemana(String data) {
        int dia;
        int mes;
        int ano;
        try {
            dia = Integer.parseInt(data.substring(0, 2));
            mes = Integer.parseInt(data.substring(3, 5));
            ano = Integer.parseInt(data.substring(6, 10));
        } catch (NumberFormatException e) {
            return "";
        }

        // ajuste pra formula funcionar com janeiro e fevereiro
        if (mes < 3) {
            mes = mes + 12;
            ano = ano - 1;
        }

        int k = ano % 100;
        int j = ano / 100;

        // formula de zeller
        int resultado = (dia + (13 * (mes + 1)) / 5 + k + k/4 + j/4 - 2*j) % 7;
        if (resultado < 0) resultado = resultado + 7;

        // 0=sabado, 1=domingo, 2=segunda...
        String[] nomes = {"sabado", "domingo", "segunda", "terca", "quarta", "quinta", "sexta"};
        //System.out.println("DEBUG dia semana: " + nomes[resultado]); // pra testar
        return nomes[resultado];
    }

    // ---- ATENDIMENTOS ----

    public static void menuAtendimentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- ATENDIMENTOS ---");
            System.out.println("1 - Registrar atendimento");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");

            if (op == 1) registrarAtendimento();
        }
    }

    public static void registrarAtendimento() {
        int idxConsulta = lerInteiro("Indice da consulta: ");

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }
        if (!consultas.get(idxConsulta).getStatus().equals("agendada")) {
            System.out.println("So pode registrar atendimento em consulta agendada.");
            return;
        }

        System.out.print("Data do registro (dd/mm/aaaa): ");
        String data = sc.nextLine();

        System.out.print("Observacoes: ");
        String obs = sc.nextLine();

        int tipo = lerInteiro("Tipo de registro (1-So obs / 2-Com diagnostico / 3-Completo): ");

        Atendimento atendimento;

        if (tipo == 1) {
            atendimento = new Atendimento(idxConsulta, obs, data);

        } else if (tipo == 2) {
            System.out.print("Diagnostico: ");
            String diag = sc.nextLine();
            atendimento = new Atendimento(idxConsulta, obs, diag, data);

        } else {
            System.out.print("Diagnostico: ");
            String diag = sc.nextLine();

            List<String> procs = new ArrayList<>();
            int forma = lerInteiro("Como informar procedimentos? (1-Um por vez / 2-Todos de uma vez): ");

            if (forma == 1) {
                String proc = "";
                while (!proc.equals("fim")) {
                    System.out.print("Procedimento (ou 'fim'): ");
                    proc = sc.nextLine();
                    if (!proc.equals("fim")) {
                        procs.add(proc);
                    }
                }
            } else {
                int qtd = lerInteiro("Quantos? ");
                for (int i = 0; i < qtd; i++) {
                    System.out.print("Proc " + (i+1) + ": ");
                    procs.add(sc.nextLine());
                }
            }
            atendimento = new Atendimento(idxConsulta, obs, diag, procs, data);
        }

        clinica.adicionarAtendimento(atendimento);

        consultas.get(idxConsulta).realizar();

        System.out.println("\n--- RESUMO ---");
        System.out.println(atendimento.exibirResumo());
        System.out.println("Consulta marcada como realizada.");
    }

    // ---- PAGAMENTOS ----

    public static void menuPagamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Pagamento direto");
            System.out.println("2 - Pagamento automatico");
            System.out.println("3 - Listar pagamentos");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");

            switch (op) {
                case 1: pagamentoDireto(); break;
                case 2: pagamentoAutomatico(); break;
                case 3: listarPagamentos(); break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }

    public static void pagamentoDireto() {
        int idxConsulta = lerInteiro("Indice da consulta: ");

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        double valor = lerDouble("Valor: ");
        System.out.print("Tipo (dinheiro/cartao/convenio): ");
        String tipoPag = sc.nextLine();

        if (tipoPag.equals("cartao")) {
            int parc = lerInteiro("Parcelas (1 a 3): ");
            if (parc < 1) parc = 1;
            if (parc > 3) parc = 3;
            clinica.adicionarPagamento(criarPagamento(idxConsulta, valor, tipoPag, parc));
            if (parc > 1) {
                double vlrParc = Math.round((valor / parc) * 100.0) / 100.0;
                System.out.println("Pagamento em " + parc + "x de R$" + vlrParc);
            }
        } else {
            clinica.adicionarPagamento(criarPagamento(idxConsulta, valor, tipoPag));
        }
        System.out.println("Pagamento registrado!");
    }

    public static void pagamentoAutomatico() {
        int idxConsulta = lerInteiro("Indice da consulta: ");

        if (idxConsulta < 0 || idxConsulta >= consultas.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        // obtem valor do profissional
        String nomeProf = consultas.get(idxConsulta).getNomeProfissional();
        int idxProf = buscarIndiceProfissional(nomeProf);
        double valorBase = profissionais.get(idxProf).getValorConsulta();

        // verifica convenio e tipo
        String cpfPac = consultas.get(idxConsulta).getCpfPaciente();
        int idxPac = buscarIndicePaciente(cpfPac);

        boolean temConvenio = !pacientes.get(idxPac).getConvenio().equals("");
        boolean ehRetorno = consultas.get(idxConsulta).getTipo().equals("retorno");

        double desconto = 0;
        if (ehRetorno) desconto = desconto + 20;
        if (temConvenio) desconto = desconto + 40;

        int temMulta = lerInteiro("Tem multa pendente? (1-Nao / 2-Sim): ");
        double valorMulta = 0;

        double valorFinal;
        if (temMulta == 1 && desconto == 0) {
            valorFinal = Pagamento.calcularValor(valorBase);
        } else if (temMulta == 1) {
            valorFinal = Pagamento.calcularValor(valorBase, desconto);
        } else {
            valorMulta = lerDouble("Valor da multa: ");
            valorFinal = Pagamento.calcularValor(valorBase, desconto, valorMulta);
        }

        // mostra detalhes
        System.out.println("Valor base: R$" + valorBase);
        System.out.println("Desconto: " + desconto + "%");
        if (valorMulta > 0) System.out.println("Multa: R$" + valorMulta);
        double vlrFinalArredondado = Math.round(valorFinal * 100.0) / 100.0;
        System.out.println("Valor final: R$" + vlrFinalArredondado);

        System.out.print("Tipo (dinheiro/cartao/convenio): ");
        String tipoPag = sc.nextLine();

        if (tipoPag.equals("cartao")) {
            int parc = lerInteiro("Parcelas (1 a 3): ");
            if (parc < 1) parc = 1;
            if (parc > 3) parc = 3;
            clinica.adicionarPagamento(criarPagamento(idxConsulta, valorFinal, tipoPag, parc));
            double vlrParc = Math.round((valorFinal / parc) * 100.0) / 100.0;
            System.out.println("Pagamento em " + parc + "x de R$" + vlrParc);
        } else {
            clinica.adicionarPagamento(criarPagamento(idxConsulta, valorFinal, tipoPag));
        }
        System.out.println("Pagamento registrado!");
    }

    public static void listarPagamentos() {
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        for (Pagamento pagamento : pagamentos) {
            System.out.println(pagamento.exibirResumo());
        }
    }

    public static Pagamento criarPagamento(int indiceConsulta, double valor, String tipoPagamento) {
        return criarPagamento(indiceConsulta, valor, tipoPagamento, 1);
    }

    public static Pagamento criarPagamento(int indiceConsulta, double valor, String tipoPagamento, int parcelas) {
        if (tipoPagamento.equals("dinheiro") || tipoPagamento.equals("pix")) {
            return new PagamentoDinheiro(indiceConsulta, valor);
        }
        if (tipoPagamento.equals("cartao")) {
            return new PagamentoCartao(indiceConsulta, valor, parcelas);
        }
        if (tipoPagamento.equals("convenio")) {
            String cpfPaciente = consultas.get(indiceConsulta).getCpfPaciente();
            Paciente paciente = clinica.buscarPacientePorCpf(cpfPaciente);
            if (paciente != null) {
                return new PagamentoConvenio(indiceConsulta, valor, paciente.getConvenioObjeto());
            }
            return new PagamentoConvenio(indiceConsulta, valor, null);
        }
        System.out.println("Tipo de pagamento nao reconhecido. Registrando como dinheiro.");
        return new PagamentoDinheiro(indiceConsulta, valor);
    }

    // ---- RELATORIOS ----

    public static void menuRelatorios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n--- RELATORIOS ---");
            System.out.println("1 - Geral");
            System.out.println("2 - Por profissional");
            System.out.println("3 - Por periodo");
            System.out.println("4 - Resumo financeiro");
            System.out.println("0 - Voltar");
            op = lerInteiro("Opcao: ");

            switch (op) {
                case 1:
                    Relatorio.gerarRelatorio(consultas, atendimentos);
                    break;
                case 2:
                    System.out.print("Nome do profissional: ");
                    String nome = sc.nextLine();
                    Relatorio.gerarRelatorio(consultas, atendimentos, nome);
                    break;
                case 3:
                    System.out.print("Data inicio (DD/MM/AAAA): ");
                    String ini = sc.nextLine();
                    System.out.print("Data fim (DD/MM/AAAA): ");
                    String fim = sc.nextLine();
                    Relatorio.gerarRelatorio(consultas, atendimentos, ini, fim);
                    break;
                case 4:
                    Relatorio.gerarResumoFinanceiro(consultas, pagamentos, multas);
                    break;
                case 0: break;
                default: System.out.println("Opcao invalida!"); break;
            }
        }
    }
}
