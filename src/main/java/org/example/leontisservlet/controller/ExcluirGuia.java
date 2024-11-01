package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.GuiaDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.dao.ObraGuiaDAO;
import org.example.leontisservlet.model.Guia;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

//Aqui eu pego o id de quem sera excluido, excluo e mando para pagina de catalogo
@WebServlet(name = "excluirGuia", value = "/excluir-guia")
public class ExcluirGuia extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int idAdm;
        int idGuia;
        try{
            idAdm = Integer.parseInt(request.getParameter("id_museu_adm"));
            idGuia = Integer.parseInt(request.getParameter("id_guia"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //Buscando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null){
            //Excluindo os registros do guia
            ObraGuiaDAO obraGuiaDAO = new ObraGuiaDAO();
            obraGuiaDAO.removerTodosGuia(idGuia);
            GuiaDAO guiaDAO = new GuiaDAO();
            //Removendo guia
            if(guiaDAO.remover(idGuia) <= 0){
                request.setAttribute("erro","Erro ao excluir guia");
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }
            //Listando guias e enviando
            int idMuseu = museu.getId();
            ResultSet rsGuias = guiaDAO.buscarPorIdMuseu(idMuseu);
            LinkedList<Guia> guias = MetodosAuxiliares.listarGuias(rsGuias);
            //Vendo se nao deu algum erro
            if(guias != null){
                //Enviando atributos para tela de catalogo
                request.setAttribute("id_museu_adm",museu.getId_adm_museu());
                request.setAttribute("guias",guias);
                request.setAttribute("museu",museu);
                request.getRequestDispatcher("gerencia/guia/guia.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }
}
