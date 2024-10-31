package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.ArtistaDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.model.Artista;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;

//Aqui eu pego o id de quem será exibido e mostro a tela com as informações dele
@WebServlet(name = "artistaInfo",value = "/artistaInfo")
public class InfoArtista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm e do artista
        int id_museu_adm;
        int idArtista;
        try{
            id_museu_adm = Integer.parseInt(request.getParameter("id_museu_adm"));
            idArtista = Integer.parseInt(request.getParameter("id_artista"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //Pegando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_museu_adm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        if(museu!=null){
            //Pegando artista e enviando para pagina de info
            ArtistaDAO artistaDAO = new ArtistaDAO();
            ResultSet rsArtista = artistaDAO.buscarPorId(idArtista);
            Artista artista = MetodosAuxiliares.pegarArtista(rsArtista);
            //Vendo se nao deu algum erro
            if(artista!=null){
                request.setAttribute("artista",artista);
                request.setAttribute("id_museu_adm",id_museu_adm);
                request.setAttribute("museu",museu);
                request.getRequestDispatcher("gerencia/artista/artistaInfo.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }
        }

    }
}
