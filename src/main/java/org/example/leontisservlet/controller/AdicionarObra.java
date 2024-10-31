package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.ArtistaDAO;
import org.example.leontisservlet.dao.GeneroDAO;
import org.example.leontisservlet.dao.MuseuDAO;
import org.example.leontisservlet.model.Artista;
import org.example.leontisservlet.model.Genero;
import org.example.leontisservlet.model.Museu;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

//Aqui enviamos para pagina de cadastro de obra, para adicionar uma nova
@WebServlet(name = "adicionarObra", value = "/adicionar-obra")
public class AdicionarObra extends HttpServlet {

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

        //Buscando museu
        MuseuDAO museuDAO = new MuseuDAO();
        ResultSet rsMuseu = museuDAO.buscarPorIdMuseuAdm(id_museu_adm);
        Museu museu = MetodosAuxiliares.pegarMuseu(rsMuseu);
        //Vendo se nao deu algum erro
        if(museu != null){
            //Listando artistas do catalogo, para listarmos na pagina de cadastro
            ArtistaDAO artistaDAO = new ArtistaDAO();
            ResultSet rsArtistas = artistaDAO.buscarTodos();
            LinkedList<Artista> artistas = MetodosAuxiliares.listarArtistas(rsArtistas);
            if(artistas == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }
            //Listando generos do catalogo, para listarmos na pagina de cadastro
            GeneroDAO generoDAO = new GeneroDAO();
            ResultSet rsGenero = generoDAO.buscarTodos();
            LinkedList<Genero> generos = MetodosAuxiliares.listarGeneros(rsGenero);
            if(generos == null) {
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }

            //Definindo atributos e indo para pagina de cadastro
            request.setAttribute("id_museu_adm",id_museu_adm);
            request.setAttribute("museu",museu);
            request.setAttribute("artistas",artistas);
            request.setAttribute("generos",generos);
            request.getRequestDispatcher("gerencia/obra/cadastroObra.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }

}
