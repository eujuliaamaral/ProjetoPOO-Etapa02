import java.util.List;

public class Relatorio {

    // mostra todas as consultas
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos) {
        System.out.println("\n=== RELATORIO GERAL ===");
        for (int i = 0; i < consultas.size(); i++) {
            System.out.println(consultas.get(i).exibirResumo());
            // verifica se tem diagnostico
            String diag = buscarDiagnostico(i, atendimentos);
            if (!diag.equals("")) {
                System.out.println("  Diagnostico: " + diag);
            }
            System.out.println("---");
        }
    }

    // filtra por profissional
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos,
                                      String nomeProfissional) {
        System.out.println("\n=== RELATORIO - " + nomeProfissional + " ===");
        boolean achou = false;
        for (int i = 0; i < consultas.size(); i++) {
            if (consultas.get(i).nomeProfissional.equals(nomeProfissional)) {
                System.out.println(consultas.get(i).exibirResumo());
                String diag = buscarDiagnostico(i, atendimentos);
                if (!diag.equals("")) {
                    System.out.println("  Diagnostico: " + diag);
                }
                System.out.println("---");
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma consulta encontrada para esse profissional.");
        }
    }

    // filtra por periodo (data inicio e fim)
    public static void gerarRelatorio(List<Consulta> consultas, List<Atendimento> atendimentos,
                                      String dataInicio, String dataFim) {
        System.out.println("\n=== RELATORIO - " + dataInicio + " a " + dataFim + " ===");
        for (int i = 0; i < consultas.size(); i++) {
            if (estaNoIntervalo(consultas.get(i).data, dataInicio, dataFim)) {
                System.out.println(consultas.get(i).exibirResumo());
                String diag = buscarDiagnostico(i, atendimentos);
                if (!diag.equals("")) {
                    System.out.println("  Diagnostico: " + diag);
                }
                System.out.println("---");
            }
        }
    }

    // resumo financeiro do dia
    public static void gerarResumoFinanceiro(List<Consulta> consultas, List<Pagamento> pagamentos,
                                             List<Double> multas) {
        int realizadas = 0;
        int canceladas = 0;
        double totalFaturado = 0;
        double totalEmMultas = 0;

        for (Consulta consulta : consultas) {
            if (consulta.status.equals("realizada")) realizadas++;
            if (consulta.status.equals("cancelada")) canceladas++;
        }

        for (Pagamento pagamento : pagamentos) {
            totalFaturado = totalFaturado + pagamento.valorFinal;
        }

        for (Double multa : multas) {
            totalEmMultas = totalEmMultas + multa;
        }

        System.out.println("\n=== RESUMO FINANCEIRO ===");
        System.out.println("Atendimentos realizados: " + realizadas);
        System.out.println("Total faturado: R$" + Math.round(totalFaturado * 100.0) / 100.0);
        System.out.println("Cancelamentos: " + canceladas);
        System.out.println("Total em multas: R$" + Math.round(totalEmMultas * 100.0) / 100.0);
    }

    // busca diagnostico de um atendimento pelo indice da consulta
    public static String buscarDiagnostico(int indiceConsulta, List<Atendimento> atendimentos) {
        for (int i = 0; i < atendimentos.size(); i++) {
            if (atendimentos.get(i).indiceConsulta == indiceConsulta) {
                return atendimentos.get(i).getProntuario().getDiagnostico();
            }
        }
        return "";
    }

    // compara datas convertendo pra numero inteiro (AAAAMMDD)
    public static boolean estaNoIntervalo(String data, String inicio, String fim) {
        int valorData = converterDataParaNumero(data);
        int valorInicio = converterDataParaNumero(inicio);
        int valorFim = converterDataParaNumero(fim);
        return valorData >= valorInicio && valorData <= valorFim;
    }

    // converte DD/MM/AAAA pra um numero tipo 20260519 pra poder comparar
    private static int converterDataParaNumero(String data) {
        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(3, 5));
        int ano = Integer.parseInt(data.substring(6, 10));
        return ano * 10000 + mes * 100 + dia;
    }
}
