<%@ page import="org.example.leontisservlet.model.Genero" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="org.example.leontisservlet.model.Artista" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="../../erros/paginaErro.jsp" %>

<%
  int id_museu_adm = (int) request.getAttribute("id_museu_adm");
  LinkedList<Genero> generosBanco = (LinkedList<Genero>) request.getAttribute("generos");

  Artista artista = (Artista) request.getAttribute("artista");
  boolean objArtista = artista != null;
  LinkedList<Genero> generosArtista = (LinkedList<Genero>) request.getAttribute("generosArtista");
  boolean objGenerosArtista = generosArtista != null;
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>
    <%=(objArtista && artista.getId() > 0)?"Editando":"Cadastro"%>

  </title>
  <link rel="stylesheet" href="styles/style.css">
  <link rel="stylesheet" href="styles/cadastroArtista.css">
  <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
</head>
<body>

<jsp:include page="../../navbar/navbarMuseu.jsp"></jsp:include>
<form action="cadastro-artista" method="post" enctype="multipart/form-data">
  <input type="text" name="id_museu_adm" value="<%=request.getAttribute("id_museu_adm")%>" style="display: none">
  <input type="text" name="id_artista" value="<%=objArtista?artista.getId():-1%>" style="display: none">
  <div id="formulario" >
    <h1 id="titCadastro"><%=(objArtista && artista.getId() > 0) ? ("Atualizando "+ artista.getNmArtista()) : "Cadastro de Artista"%></h1>
    <div id="nome">
      <label for="nomeObra">Nome do Artista</label>
      <input type="text" name="nomeObra" id="nomeObra" size="45" required value="<%=objArtista?artista.getNmArtista():""%>">
    </div>

    <div id="inicioProd">
      <label for="nascimento">Nascimento</label>
      <input type="date" name="nascimento" id="nascimento" required value="<%=objArtista?artista.getDtNascArtista():""%>">
    </div>

    <div id="fimProd">
      <label for="falecimento">Falecimento</label>
      <input type="date" name="falecimento" id="falecimento" value="<%=objArtista?artista.getDtFalecimento():""%>">
    </div>

    <div id="ftArtista">
      <label for="image">Foto do artista</label>
      <input type="file" name="image" id="image">
    </div>

    <div id="desc">
      <label for="descricao">Descrição</label>
      <textarea name="descricao" id="descricao" cols="60" rows="5"><%=objArtista?artista.getDescArtista():""%></textarea>
    </div>
    <div id="localNasc">
      <label for="local_nascimento">Local de Nascimento</label>
      <input type="text" name="local_nascimento" id="local_nascimento" size="45" required value="<%=objArtista?artista.getLocalNasc():""%>">
    </div>
    <div id="localMorte">
      <label for="local_morte">Local de Falecimento</label>
      <input type="text" name="local_morte" id="local_morte" size="45" value="<%=objArtista?artista.getLocalMorte():""%>">
    </div>

    <div id="genero">
      <label for="generoArtista">Gêneros Principais</label>
      <%
        LinkedList<Integer> idsGenerosSelecionados = new LinkedList<>();
        if(objGenerosArtista){
          for (int i = 0; i < generosArtista.size(); i++) {
            idsGenerosSelecionados.push(generosArtista.get(i).getId());
          }
        }
      %>
      <select name="generoArtista" id="generoArtista" required size="7" multiple>
        <%
          for (int i = 0; i < generosBanco.size(); i++) {
        %>
        <option value="<%=generosBanco.get(i).getId()%>" <%=idsGenerosSelecionados.contains(generosBanco.get(i).getId())?"selected":""%>>
          <%=generosBanco.get(i).getNmGenero()%>
        </option>
        <%}%>
      </select>
    </div>

    <div id="finalizar">
      <input type="submit" name="submit" id="submit" value="Enviar" class="botao">
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