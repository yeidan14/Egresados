<%-- 
    Document   : principal
    Created on : 10/11/2019, 05:42:29 PM
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Meta -->
        <meta name="description" content="Responsive Bootstrap 4 Dashboard and Admin Template">
        <meta name="author" content="ThemePixels">

        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="Librerias/assets/img/favicon.png">

        <title>Principal</title>

        <!-- vendor css -->
        <link href="Librerias/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet">
        <link href="Librerias/ionicons/css/ionicons.min.css" rel="stylesheet">
        <link href="Librerias/jqvmap/jqvmap.min.css" rel="stylesheet">
            <link href="Librerias/prismjs/themes/prism-tomorrow.css" rel="stylesheet">

        <!-- template css -->
        <link rel="stylesheet" href="Librerias/assets/css/cassie.css">
    </head>
    <body>

        <div   id="supe" >
        <div class="sidebar">
      <div class="sidebar-header">
        <div>
          <a href="../index.html" class="sidebar-logo"><span>Egresados</span></a>
          <small class="sidebar-logo-headline">Bienvenido Super Usuario</small>
        </div>
      </div><!-- sidebar-header -->
      <div id="dpSidebarBody" class="sidebar-body">
        <ul class="nav nav-sidebar">
          <li class="nav-label"><label class="content-label">Paginas Necesarias</label></li>
          <li class="nav-item show">
            <a href="" class="nav-link with-sub active"><i data-feather="box"></i> Avenger</a>
            <nav class="nav nav-sub">
              <a href="Registro_Admin.jsp" class="nav-sub-link active">Registrar Administrador</a>
              <a href="Registro_Programa.jsp" class="nav-sub-link">Registrar Programa</a>
             
            </nav>
          </li>
         


      </div><!-- sidebar-body -->
    </div>
        
        </div>
        <div class="content">
            <div class="header">
                <div class="header-left">
                    <a href="" class="burger-menu"><i data-feather="menu"></i></a>


                </div><!-- header-left -->

                
            </div> 
           
            <div class="content-body">
                <form name="agregar" class="parsley-style-1" action="Agregar_Programa.do"  method="get" data-parsley-validate novalidate>
                        
                        

                           
                    


                      
          <h5 id="section4" class="tx-semibold">Registrar Administrador</h5>
          <p class="mg-b-25">Puedes Registrar Administrador</p>

          
          <%String registrado=(String)request.getAttribute("alerta");
             if(registrado=="Creada"){%>
          <div class="alert alert-success" role="alert">
             Perfecto Programa Registrado !
            </div>
             <%}        
             if(registrado=="Existe"){%>
             <div class="alert alert-warning" role="alert">
             El Programa ya esta  Registrado !
            </div>
             <%}         %>
         
             
             
                 
                 
             
          
            <div class="input-group mg-b-10">
            <div class="input-group-prepend">
              <span class="input-group-text">Codigo</span>
                <input type="text" maxlength="15"  name="codigo" class="form-control"  required=""/>
          </div>
          
            <div class="input-group mg-b-10">
            <div class="input-group-prepend">
              <span class="input-group-text">Nombre </span>
                        </div>
                <input type="text" maxlength="50"  name="nombre" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" required=""/>
          </div>
          
          
          
                         

                       <div class="card card-body pd-lg-25">
                           <button type="submit" class="btn btn-primary btn-block">Agregar</button>
            
          </div>
                    </form>

                </div>
            </div><!-- content-body -->
        </div><!-- content -->

        <script src="Librerias/jquery/jquery.min.js"></script>
        <script src="Librerias/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="Librerias/feather-icons/feather.min.js"></script>
        <script src="Librerias/perfect-scrollbar/perfect-scrollbar.min.js"></script>
        <script src="Librerias/js-cookie/js.cookie.js"></script>
        <script src="Librerias/chart.js/Chart.bundle.min.js"></script>
        <script src="Librerias/jquery.flot/jquery.flot.js"></script>
        <script src="Librerias/jquery.flot/jquery.flot.stack.js"></script>
        <script src="Librerias/jquery.flot/jquery.flot.resize.js"></script>
        <script src="Librerias/jquery.flot/jquery.flot.threshold.js"></script>
        <script src="Librerias/jqvmap/jquery.vmap.min.js"></script>
        <script src="Librerias/jqvmap/maps/jquery.vmap.world.js"></script>

        <script src="Librerias/assets/js/cassie.js"></script>
        <script src="Librerias/assets/js/flot.sampledata.js"></script>
        <script src="Librerias/assets/js/vmap.sampledata.js"></script>
        <script src="Librerias/assets/js/dashboard-one.js"></script>
          <script src="Librerias/prismjs/prism.js"></script>
    <script src="Librerias/parsleyjs/parsley.min.js"></script>
    <script src="Librerias/js-cookie/js.cookie.js"></script>

    </body>
</html>
