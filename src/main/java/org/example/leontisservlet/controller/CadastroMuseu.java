package org.example.leontisservlet.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.leontisservlet.dao.*;
import org.example.leontisservlet.model.*;
import org.example.leontisservlet.service.ApiImagem;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Aqui pegamos todos os parametros da pagina de cadastro de museu e:
adicionamos um novo museu no banco ou atualizamos um já existente
com base no id recebido
Depois enviamos o usuario para home do museu
 */
@WebServlet(name = "cadastroMuseu", value = "/cadastro-museu")
@MultipartConfig
public class CadastroMuseu extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int idAdm;
        try{
            idAdm = Integer.parseInt(request.getParameter("id_museu_adm"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //<editor-fold desc="Parametros museu">
        String stId_museu = request.getParameter("id_museu");
        String nome = request.getParameter("nome");
        String desc = request.getParameter("desc");
        String rua = request.getParameter("rua");
        String estado = request.getParameter("estado");
        String cidade = request.getParameter("cidade");
        String pontoRef = request.getParameter("pontoRef");
        String cnpj = request.getParameter("cnpj");
        String cep = request.getParameter("cep");
        String stInauguracao = request.getParameter("dtInauguracao");
        String telefone = request.getParameter("tel");
        Part imagePart = request.getPart("image");

        //Validando e transformando parametros
        int id_museu;
        Date dtInauguracao;
        try {
            id_museu = stId_museu.isEmpty() ? -1 : Integer.parseInt(stId_museu);
            dtInauguracao = stInauguracao.isEmpty()?null:Date.valueOf(stInauguracao);

        }catch (Exception e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

         //</editor-fold>

        //<editor-fold desc="Parametros Dia Funcionamento">

        String aberSeg = request.getParameter("aberSeg");
        String aberTer = request.getParameter("aberTer");
        String aberQua = request.getParameter("aberQua");
        String aberQui = request.getParameter("aberQui");
        String aberSex = request.getParameter("aberSex");
        String aberSab = request.getParameter("aberSab");
        String aberDom = request.getParameter("aberDom");

        String fechSeg = request.getParameter("fechSeg");
        String fechTer = request.getParameter("fechTer");
        String fechQua = request.getParameter("fechQua");
        String fechQui = request.getParameter("fechQui");
        String fechSex = request.getParameter("fechSex");
        String fechSab = request.getParameter("fechSab");
        String fechDom = request.getParameter("fechDom");

        String stIdDom = request.getParameter("id_dom");
        String stIdSeg = request.getParameter("id_seg");
        String stIdTer = request.getParameter("id_ter");
        String stIdQua = request.getParameter("id_qua");
        String stIdQui = request.getParameter("id_qui");
        String stIdSex = request.getParameter("id_sex");
        String stIdSab = request.getParameter("id_sab");


        //Criando variaveis para os valores, caso esteja vazio, substituimos por 0 pois significa que é de graça no dia
        double valorSeg;
        double valorTer;
        double valorQua;
        double valorQui;
        double valorSex;
        double valorSab;
        double valorDom;
        //Pegando os ids dos dias para verificarmos o funcionamento
        int idSeg;
        int idTer;
        int idQua;
        int idQui;
        int idSex;
        int idSab;
        int idDom;
        try{
            valorSeg = Double.parseDouble(request.getParameter("valorSeg").isEmpty()?"0":request.getParameter("valorSeg"));
            valorTer = Double.parseDouble(request.getParameter("valorTer").isEmpty()?"0":request.getParameter("valorTer"));
            valorQua = Double.parseDouble(request.getParameter("valorQua").isEmpty()?"0":request.getParameter("valorQua"));
            valorQui = Double.parseDouble(request.getParameter("valorQui").isEmpty()?"0":request.getParameter("valorQui"));
            valorSex = Double.parseDouble(request.getParameter("valorSex").isEmpty()?"0":request.getParameter("valorSex"));
            valorSab = Double.parseDouble(request.getParameter("valorSab").isEmpty()?"0":request.getParameter("valorSab"));
            valorDom = Double.parseDouble(request.getParameter("valorDom").isEmpty()?"0":request.getParameter("valorDom"));

            idSeg = Integer.parseInt(stIdSeg);
            idTer = Integer.parseInt(stIdTer);
            idQua = Integer.parseInt(stIdQua);
            idQui = Integer.parseInt(stIdQui);
            idSex = Integer.parseInt(stIdSex);
            idSab = Integer.parseInt(stIdSab);
            idDom = Integer.parseInt(stIdDom);
        }catch (NumberFormatException nfe){
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //</editor-fold>

        //<editor-fold desc="Gerando o objeto de cada dia">
        //Essa funcao retorna um objeto de dia de funcionamento caso a abertura e o fechamento tenham sido inseridos
        //ou retorna nulo caso contrario
        DiaFuncionamento segunda = verificarFuncionamento(idSeg,aberSeg,fechSeg,valorSeg,"seg",id_museu);
        DiaFuncionamento terca = verificarFuncionamento(idTer,aberTer,fechTer,valorTer,"ter",id_museu);
        DiaFuncionamento quarta = verificarFuncionamento(idQua,aberQua,fechQua,valorQua,"qua",id_museu);
        DiaFuncionamento quinta = verificarFuncionamento(idQui,aberQui,fechQui,valorQui,"qui",id_museu);
        DiaFuncionamento sexta = verificarFuncionamento(idSex,aberSex,fechSex,valorSex,"sex",id_museu);
        DiaFuncionamento sabado = verificarFuncionamento(idSab,aberSab,fechSab,valorSab,"sab",id_museu);
        DiaFuncionamento domingo = verificarFuncionamento(idDom,aberDom,fechDom,valorDom,"dom",id_museu);
        //</editor-fold>

        //<editor-fold desc="Preparando os Regex">
        Pattern cepRgx = Pattern.compile("^\\d{5}[- ]?\\d{3}$");
        Matcher cepMtch = cepRgx.matcher(cep);
        Pattern cnpjRgx = Pattern.compile("^\\d{2}\\.?\\d{3}\\.?\\d{3}/?000[12]-?\\d{2}$");
        Matcher cnpjMtch = cnpjRgx.matcher(cnpj);
        Pattern telRgx = Pattern.compile("^\\(?\\d{2}\\)? ?9? ?\\d{4}[- ]?\\d{4}$");
        Matcher telMtch = telRgx.matcher(telefone);
        //</editor-fold>

        //Declarando objeto de museu a ser inserido
        //A url_imagem é vazia por agora, pois iremos altera-la depois de inserir
        Museu museuInsert = new Museu(id_museu,nome,desc,rua,estado,cidade,pontoRef,cep,dtInauguracao,telefone,"",cnpj,idAdm);
        //Caso todos os valores sejam validos, podemos executar o codigo
        if(cepMtch.matches() && cnpjMtch.matches() && telMtch.matches()){
            //Arrumando os valores para inserirmos somente numeros no banco
            cep = cep.replaceAll("[^0-9]","");
            cnpj = cnpj.replaceAll("[^0-9]","");
            telefone = telefone.replaceAll("[^0-9]","");
            museuInsert.setCep(cep);
            museuInsert.setCnpj(cnpj);
            museuInsert.setNrTelMuseu(telefone);


            //Validando se estamos inserindo um novo ou atualizando um já existente
            //Caso seja -1, quer dizer que devemos inserir agora o museu
            //Se nao, devemos atualizar um museu que ja existe
            MuseuDAO museuDAO = new MuseuDAO();
            if(museuInsert.getId() > 0) {
                //caso de erro ao alterar, enviamos ele para pagina de erro
                if(museuDAO.alterar(museuInsert,museuInsert.getId()) <= 0){
                    request.setAttribute("erro", "Ocorreu um erro ao alterar o museu. Tente de novo mais tarde.");
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
            }else {
                //caso de erro ao inserir, enviamos ele para pagina de erro
                if(museuDAO.inserir(museuInsert) <= 0){
                    request.setAttribute("erro", "Ocorreu um erro ao alterar o museu. Tente de novo mais tarde.");
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
            }

            //Puxando museu
            ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(museuInsert.getId_adm_museu());
            Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
            //Validando para sabermos se tudo deu certo
            if(museu != null){
                //Agora atualizamos a imagem, com base no novo id
                String urlImagem = ApiImagem.pegarUrlImagem(imagePart,"museu",museu.getId());
                if(urlImagem != null && !urlImagem.isEmpty()){
                   //Mudando a url no objeto e atualizando no banco
                    museu.setUrlImagem(urlImagem);
                    museuDAO.alterarUrlImagem(museu.getUrlImagem(),museu.getId());
                }

                //<editor-fold desc="Registrando dias de funcionamento">
                //Essa função apenas registra se o dia de funcionamento nao for nulo e remove os nulos
                registrarDiaFuncionamento(segunda, museu.getId(), "seg");
                registrarDiaFuncionamento(terca, museu.getId(), "ter");
                registrarDiaFuncionamento(quarta, museu.getId(), "qua");
                registrarDiaFuncionamento(quinta, museu.getId(), "qui");
                registrarDiaFuncionamento(sexta, museu.getId(), "sex");
                registrarDiaFuncionamento(sabado, museu.getId(), "sab");
                registrarDiaFuncionamento(domingo, museu.getId(), "dom");
                //</editor-fold>

                //Agora listando os dias de funcionamento inseridos
                DiaFuncionamentoDAO diaFuncionamentoDAO = new DiaFuncionamentoDAO();
                ResultSet rsDiaFunc = diaFuncionamentoDAO.buscarPorIdMuseu(museu.getId());
                LinkedList<DiaFuncionamento> diasFuncionamento = MetodosAuxiliares.listarDiaFuncionamento(rsDiaFunc);
                //Validando
                if(diasFuncionamento !=null){
                    //Definindo parametros e enviando para a pagina principal
                    request.setAttribute("id_museu_adm",museu.getId_adm_museu());
                    request.setAttribute("dias_func", MetodosAuxiliares.ordenarDiasFuncionamento(diasFuncionamento));
                    request.setAttribute("museu", museu);
                    request.getRequestDispatcher("museu/museu.jsp").forward(request,response);
                }else{
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                }
            }else{
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }
        }
        else {
            //Definindo erros e reenviando para pagina de cadastro
            LinkedList<String> erros = new LinkedList<>();
            if(!cepMtch.matches()){
                erros.push("Digite o cep no modelo XXXXX-XXX");
            }
            if(!cnpjMtch.matches()){
                erros.push("Digite o cnpj no modelo XX.XXX.XXX/000X-XX");
            }
            if(!telMtch.matches()){
                erros.push("Digite o telefone no modelo (XX) XXXX-XXXX");
            }

            //<editor-fold desc="Atributos">
            request.setAttribute("museu",museuInsert);
            request.setAttribute("id_museu_adm",idAdm);
            request.setAttribute("domingo",domingo);
            request.setAttribute("segunda",segunda);
            request.setAttribute("terca",terca);
            request.setAttribute("quarta",quarta);
            request.setAttribute("quinta", quinta);
            request.setAttribute("sexta", sexta);
            request.setAttribute("sabado", sabado);
            request.setAttribute("erros",erros);
            //</editor-fold>
            request.getRequestDispatcher("museu/cadastroMuseu.jsp").forward(request,response);
        }
    }



    public static DiaFuncionamento verificarFuncionamento(int id,String abertura, String fechamento, double valor, String dia_semana, int id_museu){
        //Caso tenha dia de abertura e fechamento, retornamos um dia do funcionamento
        if(!abertura.isEmpty() && !fechamento.isEmpty()) {
            return new DiaFuncionamento(id,abertura,fechamento,valor, dia_semana,id_museu);
        //Caso contrario, retornamos nulo
        }else{
            return null;
        }
    }
    public static void registrarDiaFuncionamento(DiaFuncionamento diaFuncionamento, int id_museu, String dia_semana){
        //Validando se ele é para ser inserido
        DiaFuncionamentoDAO diaFuncionamentoDAO = new DiaFuncionamentoDAO();
        if(diaFuncionamento != null){
            diaFuncionamento.setIdMuseu(id_museu);
            //Verificando se deve inserir ou atualizar
            if(diaFuncionamento.getId() > 0) {
                diaFuncionamentoDAO.alterar(diaFuncionamento, diaFuncionamento.getId());
            }else{
                diaFuncionamentoDAO.inserir(diaFuncionamento);
            }
        }
        else {
            //Vendo se ele existe, para deletarmos
            ResultSet rs = diaFuncionamentoDAO.buscarPorIdMuseuDiaSemana(id_museu,dia_semana);
            try{
                if(rs.next()){
                    diaFuncionamentoDAO.remover(rs.getInt("id"));
                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
        }
    }

}
