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
import java.util.regex.Pattern;

//Aqui alteramos a senha no primeiro login do usuário e mandamos ele para tela de cadastro depois
@WebServlet(name = "alterarSenhaAdm", value = "/alterar-senha")
public class AlterarSenhaAdm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int idAdm;
        try{
            idAdm = Integer.parseInt(request.getParameter("id_museu_adm"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        String novaSenha = request.getParameter("novaSenha");

        request.setAttribute("id_museu_adm", idAdm);
        //Vendo se não é a senha padrão
        if(novaSenha.equals(System.getenv("ADM_DEFAULT_PASSWD"))){
            request.setAttribute("erro","A nova senha não pode ser igual a senha padrão.");
            request.getRequestDispatcher("museu/alterarSenha.jsp").forward(request, response);
        }else if(!Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$").matcher(novaSenha).matches()){
            request.setAttribute("erro", "A nova senha deve ter pelo menos 8 digitos, um número, uma letra maiuscula e um simbolo.");
            request.getRequestDispatcher("museu/alterarSenha.jsp").forward(request, response);
        }else{
            //Alterando a senha no banco
            MuseuAdmDAO museuAdmDAO = new MuseuAdmDAO();
            museuAdmDAO.alterarSenha(novaSenha, idAdm);
            ApiLoginAdm apiLoginAdm = new ApiLoginAdm();
            ResultSet rsMuseuAdm = museuAdmDAO.buscarPorId(idAdm);
            try{
                if(rsMuseuAdm.next()){
                    //Adicionando o usuario, com a senha correta, na api e enviando para tela de cadastro
                    apiLoginAdm.createUser(rsMuseuAdm.getString("email_adm"),novaSenha);
                    request.getRequestDispatcher("museu/cadastroMuseu.jsp").forward(request,response);
                }else{
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request,response);
                }
            }catch (Exception e){
                e.printStackTrace();
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request,response);
            }



        }
    }
}
