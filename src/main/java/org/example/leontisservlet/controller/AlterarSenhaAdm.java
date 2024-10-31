package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.MuseuAdmDAO;
import org.example.leontisservlet.service.ApiLoginAdm;

import java.io.IOException;
import java.sql.ResultSet;

//Aqui alteramos a senha no primeiro login do usuário e mandamos ele para tela de cadastro depois
@WebServlet(name = "alterarSenhaAdm", value = "/alterar-senha")
public class AlterarSenhaAdm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int id_museu_adm;
        try{
            id_museu_adm = Integer.parseInt(request.getParameter("id_museu_adm"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        String novaSenha = request.getParameter("novaSenha");

        request.setAttribute("id_museu_adm",id_museu_adm);
        //Vendo se não é a senha padrão
        if(novaSenha.equals(System.getenv("ADM_DEFAULT_PASSWD"))){
            request.setAttribute("erro","A nova senha não pode ser igual a senha padrão.");
            request.getRequestDispatcher("museu/alterarSenha.jsp").forward(request, response);
        }else{
            //Alterando a senha no banco
            MuseuAdmDAO museuAdmDAO = new MuseuAdmDAO();
            museuAdmDAO.alterarSenha(novaSenha,id_museu_adm);
            ApiLoginAdm apiLoginAdm = new ApiLoginAdm();
            ResultSet rsMuseuAdm = museuAdmDAO.buscarPorId(id_museu_adm);
            try{
                if(rsMuseuAdm.next()){
                    //Adicionando o usuario, com a senha correta, na api e enviando para tela de cadastro
                    apiLoginAdm.createUser(rsMuseuAdm.getString("email_adm"),novaSenha);
                    request.getRequestDispatcher("museu/cadastroMuseu.jsp").forward(request,response);
                }else{
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request,response);
                }
            }catch (Exception e){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request,response);
            }



        }
    }
}
