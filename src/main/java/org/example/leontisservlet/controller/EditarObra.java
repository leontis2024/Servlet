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
import java.util.LinkedList;

//Aqui eu mando ele para pagina de cadastro, porem com um objeto de museu como parametro
//para que ele saiba que deve editar
@WebServlet(name = "editarObra", value = "/editar-obra")
public class EditarObra extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Pegando id do adm
        int id_museu_adm;
        int idObra;
        try{
            id_museu_adm = Integer.parseInt(request.getParameter("id_museu_adm"));
            idObra = Integer.parseInt(request.getParameter("id_obra"));
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
            //Pegando obra
            ObraDAO obraDAO = new ObraDAO();
            ResultSet rsObra = obraDAO.buscarPorId(idObra);
            Obra obra = MetodosAuxiliares.pegarObra(rsObra);
            if(obra == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }

            //Listando artistas do catalogo
            ArtistaDAO artistaDAO = new ArtistaDAO();
            ResultSet rsArtistas = artistaDAO.buscarTodos();
            LinkedList<Artista> artistas = MetodosAuxiliares.listarArtistas(rsArtistas);
            if(artistas == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }

            //Listando generos do catalogo
            GeneroDAO generoDAO = new GeneroDAO();
            ResultSet rsGeneros = generoDAO.buscarTodos();
            LinkedList<Genero> generos = MetodosAuxiliares.listarGeneros(rsGeneros);
            if(generos == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }

            //Pegando artista da obra
            ResultSet rsArtista = artistaDAO.buscarPorId(obra.getIdArtista());
            Artista artista = MetodosAuxiliares.pegarArtista(rsArtista);
            if(artista == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }

            //Pegando genero da obra
            ResultSet rsGenero = generoDAO.buscarPorId(obra.getIdGenero());
            Genero genero = MetodosAuxiliares.pegarGenero(rsGenero);
            if(genero == null){
                request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
                return;
            }

            //enviando atributos para pagina de cadastro
            request.setAttribute("id_museu_adm",id_museu_adm);
            request.setAttribute("museu",museu);
            request.setAttribute("obra",obra);
            request.setAttribute("artistas",artistas);
            request.setAttribute("generos",generos);
            request.setAttribute("artista",artista);
            request.setAttribute("genero",genero);
            request.getRequestDispatcher("gerencia/obra/cadastroObra.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("erros/paginaErro.jsp").forward(request, response);
        }
    }
}
