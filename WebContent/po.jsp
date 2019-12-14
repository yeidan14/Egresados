<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
 integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<head>
  <title>Listar Egresados</title>
</head>

<script>
  $(document).ready(function () {
    $("#myInput").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#myTable tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
</script>
<script>
  $(document).ready(function () {
    $("#myInput2").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#myTable2 tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
</script>
<script>
  $(document).ready(function () {
    $("#myInput3").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#myTable3 tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
</script>
<script>
  $(document).ready(function () {
    $("#myInput5").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#myTable5 tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
</script>


<body class="bg" style="background-color: #b43432">

  <!-- Inicio navbar !-->
  <nav class="navbar navbar-expand-lg navbar-dark  bg-dark">
    <a class="navbar-brand" href="index.jsp"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <!-- Pesta�a Inicio / Index !-->
        <li class="nav-item active">
          <a class="nav-link" href="listado.html">&nbsp&nbspEgresados&nbsp&nbsp <i class="fas fa-home"></i></a>
        </li>
      </ul>


      <!-- Men� de Usuario !-->

      <form class="form-inline my-2 my-lg-0">
        <div class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
            aria-haspopup="true" aria-expanded="false">
            <img src="img/ingeniero.png">
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="logout">Cerrar sesion</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Sr. ${user.getNombre()}</a>
          </div>
        </div>
        <div class="nav-item active">
          <a class="nav-link" href="#"></a>
        </div>
        <div class="nav-item active">
          <a class="nav-link" href="#"></a>
        </div>
      </form>

    </div>
  </nav>

  <!-- Fin navbar !-->
  <br>
  <br>

  <div class="container bg-light">
    <div class="row">
      <div class="col-sm-3 media border p-3">

        <div class="container">
          <ul class=" nav nav-tabs nav-justified" role="tablist">
            <li class="nav-item">
              <!-- Tabla con la lista de egresados para poder validar -->
              <a class="nav-link" data-toggle="tab" href="#mostrarEgresados">Mostrar Egresados</a>
              <hr>

              <!-- Informacion del Egresado -->
              <a class="nav-link" data-toggle="tab" href="#informacionEgresado">Informacion de egresado</a>
              <hr>

            </li>
          </ul>

        </div>
      </div>


      <div class="col-sm-9 media border p-3 bg-white">
        <div class="container bg-white">
          <!-- Paneles de tabla-->
          <div class="tab-content">

            <!-- Listado de Egresados -->
            <div id="mostrarEgresados" class="container tab-pane fade"><br>
              ${msg}
                <h3 align="center">LISTADO DE LOS EGRESADOS</h3>
                <br>
                <br>
                <div class="form-group">
                  <label>Egresados</label>
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Codigo</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Telefono</th>
                        <th scope="col">CC</th>
                        <th scope="col">...</th>
                      </tr>
                    </thead>
                    <tbody>
                      
                        ${solicitudes}
                     
                    </tbody>
                  </table>
                </div>
                <br>
             
            </div>

            <!-- Informacion de Egresado -->
            <div id="informacionEgresado" class="container tab-pane fade"><br>
              <form action="validarEgresado" method="POST">
                <h3 align="center">LISTADO DE LOS EGRESADOS</h3>
                <br>
                <br>
                <div class="form-group">
                  <label>Egresados</label>
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Codigo</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Telefono</th>
                        <th scope="col">CC</th>
                        <th scope="col">Estudios</th>
                        <th scope="col">Experiencias</th>
                      </tr>
                    </thead>
                    <tbody>
                      
                        ${lista}
                      
                    </tbody>
                  </table>
                </div>
                <br>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>

</html>