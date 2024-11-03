package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.*;
import org.example.leontisservlet.model.DiaFuncionamento;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@WebServlet(name = "loginAdm", value = "/login-adm")
public class LoginAdm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando email e senha
        String email = request.getParameter("email");
        String password = request.getParameter("passwd");

        try{
            //Buscando pelo email inserido
            MuseuAdmDAO admDAO = new MuseuAdmDAO();
            ResultSet rsAdm = admDAO.buscarPorEmail(email);
            if(rsAdm.next()){
                //vendo se a senha bate com a senha do banco
                String senhaBanco = rsAdm.getString("senha_adm");
                if(password.equals(senhaBanco)){
                    //Verificando se ele ainda esta com a senha inicial
                    if(senhaBanco.equals(System.getenv("ADM_DEFAULT_PASSWD"))){
                        //Enviando para pagina para trocar senha
                        request.setAttribute("id_museu_adm",rsAdm.getInt("id"));
                        request.getRequestDispatcher("museu/alterarSenha.jsp").forward(request, response);
                        return;
                    }

                    //Procurando museu
                    int id_adm = rsAdm.getInt("id");
                    MuseuDAO museuDAO = new MuseuDAO();
                    ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_adm);
                    Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);

                    //Se tiver museu, envia para pagina inicial do museu
                    if(museu != null){
                        //Listando dias de funcionamento
                        DiaFuncionamentoDAO diaFuncionamentoDAO = new DiaFuncionamentoDAO();
                        ResultSet rsDiaFunc = diaFuncionamentoDAO.buscarPorIdMuseu(museu.getId());
                        LinkedList<DiaFuncionamento> diasFuncionamento = MetodosAuxiliares.listarDiaFuncionamento(rsDiaFunc);
                        //Definindo atributos e enviando
                        request.setAttribute("id_museu_adm",museu.getId_adm_museu());
                        request.setAttribute("dias_func", MetodosAuxiliares.ordenarDiasFuncionamento(diasFuncionamento));
                        request.setAttribute("museu", museu);
                        request.getRequestDispatcher("museu/museu.jsp").forward(request,response);
                    //Se nao, ele envia para pagina de cadastro
                    }else{
                        request.setAttribute("id_museu_adm",rsAdm.getInt("id"));
                        request.getRequestDispatcher("museu/cadastroMuseu.jsp").forward(request,response);
                    }

                }else{
                    request.setAttribute("erro","Usuario ou Senha invalidos");
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                }
            }else{
                request.setAttribute("erro","Usuario ou Senha invalidos");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }


    }


}
