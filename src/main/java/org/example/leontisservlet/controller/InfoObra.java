package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.ArtistaDAO;
import org.example.leontisservlet.dao.GeneroDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.dao.ObraDAO;
import org.example.leontisservlet.model.Artista;
import org.example.leontisservlet.model.Genero;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.model.Obra;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;

//Aqui eu pego o id de quem será exibido e mostro a tela com as informações dele
@WebServlet(name = "obraInfo",value = "/obraInfo")
public class InfoObra extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int idAdm;
        int idObra;
        try{
            idAdm = Integer.parseInt(request.getParameter("id_museu_adm"));
            idObra = Integer.parseInt(request.getParameter("id_obra"));
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            return;
        }

        //Pegando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(idAdm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        if(museu!=null){
            //Buscando obra
            ObraDAO obraDAO = new ObraDAO();
            ResultSet rsObra = obraDAO.buscarPorId(idObra);
            Obra obra = MetodosAuxiliares.pegarObra(rsObra);
            //Vendo se nao deu algum erro
            if(obra != null){
                //Buscando artista da obra
                int idArtista = obra.getIdArtista();
                ArtistaDAO artistaDAO = new ArtistaDAO();
                ResultSet rsArtista = artistaDAO.buscarPorId(idArtista);
                Artista artista = MetodosAuxiliares.pegarArtista(rsArtista);
                if(artista == null){
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
                //Buscando genero da obra
                int idGenero = obra.getIdGenero();
                GeneroDAO generoDAO = new GeneroDAO();
                ResultSet rsGenero = generoDAO.buscarPorId(idGenero);
                Genero genero = MetodosAuxiliares.pegarGenero(rsGenero);
                if(genero == null){
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
                //Enviando atributos para tela de info
                request.setAttribute("artista",artista);
                request.setAttribute("obra",obra);
                request.setAttribute("genero",genero);
                request.setAttribute("id_museu_adm", idAdm);
                request.setAttribute("museu",museu);
                request.getRequestDispatcher("gerencia/obra/obraInfo.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
            }
        }


    }
}