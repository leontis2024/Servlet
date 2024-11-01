package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;

//Aqui enviamos para pagina de cadastro de guia, para adicionar um novo
@WebServlet(name = "adicionarGuia", value = "/adicionar-guia")
public class AdicionarGuia extends HttpServlet {
    @Override
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

        //Buscando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null) {
            //Definindo atributos e indo para pagina de cadastro
            request.setAttribute("id_museu_adm", idAdm);
            request.setAttribute("museu", museu);
            request.getRequestDispatcher("gerencia/guia/cadastroGuia.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }
}
