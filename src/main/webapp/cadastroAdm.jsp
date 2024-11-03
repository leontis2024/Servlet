<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="styles/style.css">
  <link rel="stylesheet" href="styles/cadastroAdm.css">
  <link rel="shortcut icon" href="Resources/leon.png" type="image/jpg">
  <title>Cadastro Administrativo</title>
</head>
<body>
<jsp:include page="navbar/navbarLogin.html"></jsp:include>

<div id="txtBox">
  <h1 id="titulo">Cadastro Administrativo</h1>
  <h2 id="desc">Cadastre-se no Leontis e comece a oferecer experiências inigualáveis aos visitantes.</h2>
</div>

<div id="opcoesContato">
  <div class="contato">
    <h3 class="tituloContato">E-mail</h3>
    <div class="iconContato">
      <img src="Resources/Mail.png" class="icon">
    </div>

    <div class="linkContato">
      <a href="mailto:leontis2024@gmail.com">leontis2024@gmail.com</a>
    </div>

    <div class="descContato">
      Todo acervo é único. Entre em contato conosco hoje e descubra como o Leontis pode se adequar à seu estabelecimento.    </div>
  </div>

  <div class="contato">
    <h3 class="tituloContato">Instagram</h3>
    <div class="iconContato">
      <img src="Resources/Instagram.png" class="icon">
    </div>

    <div class="linkContato">
      <a href="https://www.instagram.com/leontis_app/">@leontis_app</a>
    </div>

    <div class="descContato">
      Nos siga no Instagram! Acompanhe as novidades do nosso aplicativo, interaja conosco e com os usuários
    </div>
  </div>
</div>
</body>
</html>