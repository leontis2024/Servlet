package org.example.leontisservlet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.leontisservlet.dao.*;
import org.example.leontisservlet.model.*;
import org.example.leontisservlet.service.MetodosAuxiliares;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;

//Aqui eu mando ele para pagina de cadastro, porem com um objeto de artista como parametro
//para que ele saiba que deve editar
@WebServlet(name = "editarArtista", value = "/editar-artista")
public class EditarArtista extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int id_museu_adm;
        int idArtista;
        try{
            id_museu_adm = Integer.parseInt(request.getParameter("id_museu_adm"));
            idArtista = Integer.parseInt(request.getParameter("id_artista"));
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
            //Pegando artista
            ArtistaDAO artistaDAO = new ArtistaDAO();
            ResultSet rsArtista = artistaDAO.buscarPorId(idArtista);
            Artista artista = MetodosAuxiliares.pegarArtista(rsArtista);
            if(artista == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }
            //Listando generos do catalogo para pagina de cadastro
            GeneroDAO generoDAO = new GeneroDAO();
            ResultSet rsGeneros = generoDAO.buscarTodos();
            LinkedList<Genero> generos = MetodosAuxiliares.listarGeneros(rsGeneros);
            if(generos == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }


            //<editor-fold desc="Listando generos do artista">
            ArtistaGeneroDAO artistaGeneroDAO = new ArtistaGeneroDAO();
            ResultSet rsArtistaGenero = artistaGeneroDAO.buscarPorIdArtista(artista.getId());
            //Lista com os objetos Artista Genero, para listarmos todos os generos do artista
            LinkedList<ArtistaGenero> artistaGeneroLista = MetodosAuxiliares.listarArtistaGenero(rsArtistaGenero);
            if(artistaGeneroLista == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }
            //Para cada objeto do ArtistaGenero, pegamos o genero desse artista
            LinkedList<Genero> generosArtista = new LinkedList<>();
            for (int i = 0; i < artistaGeneroLista.size(); i++) {
                ResultSet rs = generoDAO.buscarPorId(artistaGeneroLista.get(i).getIdGenero());
                Genero genero = MetodosAuxiliares.pegarGenero(rs);
                //Validando se nao deu erro
                if(genero == null){
                    request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                    return;
                }
                generosArtista.push(genero);
            }
            //</editor-fold>

            //enviando atributos para tela de cadastro
            request.setAttribute("id_museu_adm",id_museu_adm);
            request.setAttribute("museu",museu);
            request.setAttribute("generos",generos);
            request.setAttribute("artista",artista);
            request.setAttribute("generosArtista",generosArtista);
            request.getRequestDispatcher("gerencia/artista/cadastroArtista.jsp").forward(request,response);
        }else{

            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }
}
