<%@ page import="java.util.LinkedList" %>
<%@ page import="org.example.leontisservlet.model.Artista" %>
<%@ page import="org.example.leontisservlet.model.Genero" %>
<%@ page import="org.example.leontisservlet.model.Obra" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>

<%
    int id_museu_adm = (int) request.getAttribute("id_museu_adm");
    LinkedList<Artista> artistas = (LinkedList<Artista>) request.getAttribute("artistas");
    LinkedList<Genero> generos = (LinkedList<Genero>) request.getAttribute("generos");

    Obra obra = (Obra) request.getAttribute("obra");
    boolean objObra = obra != null;
    Genero genero = (Genero) request.getAttribute("genero");
    boolean objGenero = genero != null;
    Artista artista = (Artista) request.getAttribute("artista");
    boolean objArtista = artista != null;
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=(objObra && obra.getId() > 0)?"Editando":"Cadastro"%></title>

    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/cadastroObra.css">
    <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
</head>
<body>
<jsp:include page="../../navbarMuseu.jsp"></jsp:include>

<form action="cadastro-obra" method="post" enctype="multipart/form-data">
    <input type="text" name="id_museu_adm" value="<%=request.getAttribute("id_museu_adm")%>" style="display: none">
    <input type="text" name="id_obra" value="<%=objObra?obra.getId():-1%>" style="display: none">

    <div id="formulario">
        <h1 id="titCadastro">
            <%=(objObra && obra.getId() > 0) ? ("Atualizando "+ obra.getNmObra()) : "Cadastro de Obra"%>

        </h1>
        <div id="nome">
            <label for="nomeObra">Nome da Obra:</label>
            <input type="text" name="nomeObra" id="nomeObra" size="45" required value="<%=objObra?obra.getNmObra():""%>">
        </div>

        <div id="inicioProd">
            <label for="ano_inicio">Início da Produção:</label>
            <input type="number" name="ano_inicio" id="ano_inicio" min="0" max="2024" placeholder="Ano de início" required value="<%=objObra?obra.getAnoInicio():""%>">
        </div>

        <div id="fimProd">
            <label for="ano_termino">Fim da Produção:</label>
            <input type="number" name="ano_termino" id="ano_termino" min="0" max="2024" placeholder="Ano de finalização" required value="<%=objObra?obra.getAnoFinal():""%>">
        </div>

        <div id="ftObra">
            <label for="image">Foto da Obra:</label>
            <input type="file" name="image" id="image">
        </div>

        <div id="desc">
            <label for="descricao">Descrição:</label>
            <textarea name="descricao" id="descricao" cols="60" rows="5" ><%=objObra?obra.getDescObra():""%></textarea>
        </div>

        <div id="genero">
            <label for="generoObra">Gênero:</label>
            <%
                int idGeneroSelecionado = objGenero? genero.getId():-1;
            %>
            <select name="generoObra" id="generoObra" required size="4">
                <%
                    for (int i = 0; i < generos.size(); i++) {
                %>
                <option value="<%=generos.get(i).getId()%>" <%=generos.get(i).getId() == idGeneroSelecionado?"selected":""%>><%=generos.get(i).getNmGenero()%></option>
                <%}%>
            </select>
        </div>

        <%
            int idArtistaSelecionado = objArtista? artista.getId():-1;
            System.out.println(idArtistaSelecionado);
        %>
        <div id="autor">
            <label for="artistaObra">Autor:</label>
            <select name="artistaObra" id="artistaObra" required size="4">
                <%
                    for (int i = 0; i < artistas.size(); i++) {
                %>
                <option value="<%=artistas.get(i).getId()%>" <%=artistas.get(i).getId() == idArtistaSelecionado?"selected":""%>><%=artistas.get(i).getNmArtista()%></option>
                <%}%>
            </select>
        </div>

        <div id="finalizar">
            <input type="submit" name="submit" id="submit" value="enviar" class="botao">
            <input type="reset" name="reset" id="reset" class="botao">
        </div>
    </div>
</form>

<script>
    <%
        String erro = (String) request.getAttribute("erro");
        if (erro != null){
    %>
    alert("<%=erro%>")
    <%}%>
</script>
</body>
</html>