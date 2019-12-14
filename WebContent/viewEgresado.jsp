<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

    <title>Egresado - Profile</title>

    <!-- vendor css -->
    <link href="Librerias/@fortawesome/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="Librerias/ionicons/css/ionicons.min.css" rel="stylesheet">

    <!-- template css -->
    <link rel="stylesheet" href="Librerias/assets/css/cassie.css">
</head>
<body>
		
		
		
		
			<div class="modal fade" id="misExperencias" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Registrar Experencia</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form action="estudiosEgresado" method="post">
			    <div class="form-group">
			      <label for="disabledTextInput">Funciones</labiel>
			      <input type="text" id="disabledTextInput" required  name="funcion" class="form-control" placeholder="Funciones">
			    </div>
			    <div class="form-group">
			      <label for="disabledTextInput">Periodo Inicio</label>
			      <input type="date" required name="periodoinicio" class="form-control" placeholder="inicio">
			    </div>
			    
			    <div class="form-group">
			      <label for="disabledTextInput">Periodo Final</label>
			      <input type="date" required name="periodofinal" class="form-control" placeholder="fin">
			    </div>
			    
			     <div class="form-group">
			      <label for="disabledTextInput">Descripcion</label>
			      <textarea class="form-control" rows="5" required name="descripcion"  cols="10"></textarea>
			      <input type="text" id="disabledTextInput" class="form-control" placeholder="Instituto">
			    </div>
	      	</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		        <button type="submit" class="btn btn-primary">Guardar</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>
		
	
	
	
	<div class="modal fade" id="misEstudios" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Registrar Estudio</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form action="estudiosEgresado" method="post">
			    <div class="form-group">
			      <label for="disabledTextInput">Titulo Obtenido</label>
			      <input type="text" id="disabledTextInput" required  name="titulo" class="form-control" placeholder="Titulo">
			    </div>
			    <div class="form-group">
			      <label for="disabledTextInput">Periodo Final</label>
			      <input type="date" required name="periodo" class="form-control" placeholder="Fecha finalizacion">
			    </div>
			    
			     <div class="form-group">
			      <label for="disabledTextInput">Lugar</label>
			      <input type="text" required id="disabledTextInput" name="lugar" class="form-control" placeholder="Instituto">
			    </div>
			    <div class="form-group">
				    <label for="exampleFormControlSelect1">Nivel</label>
				    <select required class="form-control" id="nivelSelectEstudio" name="nivel">
				      <option value="1">Primaria</option>
				      <option value="2">Secundaria</option>
				      <option value="3">Pregrado</option>
				      <option value="4">Maestria</option>
				      <option value="5">PhD</option>
				    </select>
				 </div>
	      	</div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		        <button type="submit" class="btn btn-primary">Guardar</button>
		      </div>
	      </form>
	    </div>
	  </div>
	</div>

	<div class="content">
      <div class="header">
        <div class="header-left">

        </div><!-- header-left -->

        <div class="header-right">
          <a href="" class="header-help-link"><i data-feather="help-circle"></i></a>
          <div class="dropdown dropdown-notification">
            <a href="" class="dropdown-link new" data-toggle="dropdown"><i data-feather="bell"></i></a>
            <div class="dropdown-menu dropdown-menu-right">
              <div class="dropdown-menu-header">
                <h6>Notifications</h6>
                <a href=""><i data-feather="more-vertical"></i></a>
              </div><!-- dropdown-menu-header -->
              <div class="dropdown-menu-body">
                <a href="" class="dropdown-item">
                  <div class="avatar"><span class="avatar-initial rounded-circle text-primary bg-primary-light">s</span></div>
                  <div class="dropdown-item-body">
                    <p><strong>Socrates Itumay</strong> marked the task as completed.</p>
                    <span>5 hours ago</span>
                  </div>
                </a>
                <a href="" class="dropdown-item">
                  <div class="avatar"><span class="avatar-initial rounded-circle tx-pink bg-pink-light">r</span></div>
                  <div class="dropdown-item-body">
                    <p><strong>Reynante Labares</strong> marked the task as incomplete.</p>
                    <span>8 hours ago</span>
                  </div>
                </a>
                <a href="" class="dropdown-item">
                  <div class="avatar"><span class="avatar-initial rounded-circle tx-success bg-success-light">d</span></div>
                  <div class="dropdown-item-body">
                    <p><strong>Dyanne Aceron</strong> responded to your comment on this <strong>post</strong>.</p>
                    <span>a day ago</span>
                  </div>
                </a>
                <a href="" class="dropdown-item">
                  <div class="avatar"><span class="avatar-initial rounded-circle tx-indigo bg-indigo-light">k</span></div>
                  <div class="dropdown-item-body">
                    <p><strong>Kirby Avendula</strong> marked the task as incomplete.</p>
                    <span>2 days ago</span>
                  </div>
                </a>
              </div><!-- dropdown-menu-body -->
              <div class="dropdown-menu-footer">
                <a href="">View All Notifications</a>
              </div>
            </div><!-- dropdown-menu -->
          </div>
          <div class="dropdown dropdown-loggeduser">
            <a href="" class="dropdown-link" data-toggle="dropdown">
              <div class="avatar avatar-sm">
                <img src="https://via.placeholder.com/500/637382/fff" class="rounded-circle" alt="">
              </div><!-- avatar -->
            </a>
            <div class="dropdown-menu dropdown-menu-right">
              <div class="dropdown-menu-header">
                <div class="media align-items-center">
                  <div class="avatar">
                    <img src="https://via.placeholder.com/500/637382/fff" class="rounded-circle" alt="">
                  </div><!-- avatar -->
                  <div class="media-body mg-l-10">
                    <h6>Louise Kate Lumaad</h6>
                    <span>Egresado</span>
                  </div>
                </div><!-- media -->
              </div>
              <div class="dropdown-menu-body">
                <a href="" class="dropdown-item"><i data-feather="user"></i> Mi Perfil</a>
                <a data-toggle="modal" data-target="#misEstudios" class="dropdown-item"><i data-feather="edit-2"></i>Mis Estudios</a>
                <a data-toggle="modal" data-target="#misExperencias" class="dropdown-item"><i data-feather="briefcase"></i> Experiencia </a>
                <a href="" class="dropdown-item"><i data-feather="log-out"></i> Cerrar Sesiont</a>
              </div>
            </div><!-- dropdown-menu -->
          </div>
        </div><!-- header-right -->
      </div><!-- header -->
      <div class="content-header">
        <div>
          <h4 class="content-title content-title-sm">Mi Perfil</h4>
        </div>
      </div><!-- content-header -->
      <div class="content-body">
        <div class="d-flex align-items-center justify-content-between mg-b-15">
          <h6 class="tx-uppercase tx-bold tx-spacing-1 tx-13 mg-b-0">Ultimos Estudios</h6>
          <div class="btn-group">
          </div>
        </div>
        

        <div class="people-list people-list-one">
          <div class="people-list-body">
            <div class="row row-xs">
              <div class="col">
                <div class="card card-people">
                  <div class="card-body media">
                    <div class="avatar avatar-md avatar-online"><img src="https://via.placeholder.com/500/637382/fff" class="rounded-circle" alt=""></div>
                    <div class="media-body">
                      <h6>Ing de Sistemas</h6>
                      <p>Año 2.010 - 2.015</p>
                    </div><!-- media-body -->
                  </div><!-- card-body -->
                  <div class="card-footer">
                    <a href=""><span class="tx-medium"></span></a>
                    <nav class="nav nav-icon">
                      <a href="" class="nav-link text-primary"><i data-feather="message-square"></i><span class="d-none d-sm-inline"> Editar </span></a>
                      <a href="" class="nav-link text-primary"><i data-feather="message-square"></i><span class="d-none d-sm-inline"> Eliminar </span></a>
                      <a href="" class="nav-link"><i data-feather="more-vertical"></i></a>
                    </nav>
                  </div><!-- card-footer -->
                </div><!-- card -->
              </div><!-- col -->
              <div class="col">
                <div class="card card-people">
                  <div class="card-body media">
                    <div class="avatar avatar-md avatar-online"><img src="https://via.placeholder.com/500/637382/fff" class="rounded-circle" alt=""></div>
                    <div class="media-body">
                      <h6>Maestria</h6>
                      <p>Magister SS <a href=""></a></p>
                    </div><!-- media-body -->
                  </div><!-- card-body -->
                  <div class="card-footer">
                    <a href=""><span class="tx-medium"></span></a>
                    <nav class="nav nav-icon">
                      <a href="" class="nav-link text-primary"><i data-feather="user-plus"></i><span class="d-none d-sm-inline">Editar</span></a>
                      <a href="" class="nav-link text-primary"><i data-feather="user-plus"></i><span class="d-none d-sm-inline">Eliminar</span></a>
                      <a href="" class="nav-link"><i data-feather="more-vertical"></i></a>
                    </nav>
                  </div><!-- card-footer -->
                </div><!-- card -->
              </div><!-- col -->
              <div class="col">
                <div class="card card-people">
                  <div class="card-body media">
                    <div class="avatar avatar-md avatar-offline"><img src="https://via.placeholder.com/500/637382/fff" class="rounded-circle" alt=""></div>
                    <div class="media-body">
                      <h6>PhD</h6>
                      <p>PhD in Social Media<a href=""></a></p>
                    </div><!-- media-body -->
                  </div><!-- card-body -->
                  <div class="card-footer">
                    <a href=""><span class="tx-medium"></span> </a>
                    <nav class="nav nav-icon">
                      <a href="" class="nav-link text-primary"><i data-feather="user-plus"></i><span class="d-none d-sm-inline"> Editar</span></a>
                      <a href="" class="nav-link text-primary"><i data-feather="user-plus"></i><span class="d-none d-sm-inline"> Eli</span></a>
                      <a href="" class="nav-link"><i data-feather="more-vertical"></i></a>
                    </nav>
                  </div><!-- card-footer -->
                </div><!-- card -->
              </div><!-- col -->
            </div><!-- row -->
          </div><!-- people-list-body -->
        </div><!-- people-list -->

        <hr class="mg-lg-y-25 op-0">

        <div class="d-flex align-items-center justify-content-between mg-b-15">
          <h6 class="tx-uppercase tx-bold tx-spacing-1 tx-13 mg-b-0">People You May Know</h6>
          <div class="btn-group">
            <button class="btn btn-white btn-xs btn-icon" disabled><i class="icon ion-chevron-left"></i></button>
            <button class="btn btn-white btn-xs btn-icon"><i class="icon ion-chevron-right"></i></button>
          </div>
        </div>

        <div class="people-list people-list-two">
          <div class="people-list-body">
            <div class="row row-xs">
              <div class="col">
                <div class="card card-people-two">
                  <div class="card-header">
                    <div class="pos-absolute a-0">
                      <img src="https://via.placeholder.com/640x426/637382/fff" alt="">
                    </div>
                    <div class="avatar avatar-md"><img src="https://via.placeholder.com/500/637382/fff" class="rounded-circle" alt=""></div>
                  </div>
                  <div class="card-body">
                    <h6>Samantha Virtue</h6>
                    <p>Executive Assistant</p>
                    <p><a href="">Facebook, Inc.</a></p>

                    <div class="follow-info mg-t-20">
                      <div>
                        <h6>269</h6>
                        <span>Followers</span>
                      </div>
                      <div>
                        <h6>18</h6>
                        <span>Following</span>
                      </div>
                    </div>
                  </div><!-- card-body -->
                  <div class="card-footer d-flex">
                    <a href="" class="btn btn-white btn-sm flex-fill"><i class="icon ion-plus tx-12 mg-r-2"></i> Add as Friend</a>
                    <a href="" class="btn btn-light btn-icon btn-sm mg-l-5"><i data-feather="message-square"></i></a>
                  </div><!-- card-footer -->
                </div><!-- card -->
              </div><!-- col -->
              <div class="col">
                <div class="card card-people-two">
                  <div class="card-header">
                    <div class="pos-absolute a-0">
                      <img src="https://via.placeholder.com/1000x563/637382/fff" alt="">
                    </div>
                    <div class="avatar"><span class="avatar-initial rounded-circle bg-teal">a</span></div>
                  </div>
                  <div class="card-body">
                    <h6>Alexander Locke</h6>
                    <p>Senior Front-End Engineer</p>
                    <p><a href="">Themepixels, Inc.</a></p>

                    <div class="follow-info mg-t-20">
                      <div>
                        <h6>178</h6>
                        <span>Followers</span>
                      </div>
                      <div>
                        <h6>20</h6>
                        <span>Following</span>
                      </div>
                    </div>
                  </div><!-- card-body -->
                  <div class="card-footer d-flex">
                    <a href="" class="btn btn-white btn-sm flex-fill"><i class="icon ion-plus tx-12 mg-r-2"></i> Add as Friend</a>
                    <a href="" class="btn btn-light btn-icon btn-sm mg-l-5"><i data-feather="message-square"></i></a>
                  </div><!-- card-footer -->
                </div><!-- card -->
              </div><!-- col -->
              <div class="col">
                <div class="card card-people-two">
                  <div class="card-header">
                    <div class="pos-absolute a-0">
                      <img src="https://via.placeholder.com/1000x563/637382/fff" alt="">
                    </div>
                    <div class="avatar avatar-md"><img src="https://via.placeholder.com/500/637382/fff" class="rounded-circle" alt=""></div>
                  </div>
                  <div class="card-body">
                    <h6>Katherine Movera</h6>
                    <p>Sales Representative</p>
                    <p><a href="">TheCompany, Inc.</a></p>

                    <div class="follow-info mg-t-20">
                      <div>
                        <h6>104</h6>
                        <span>Followers</span>
                      </div>
                      <div>
                        <h6>39</h6>
                        <span>Following</span>
                      </div>
                    </div>
                  </div><!-- card-body -->
                  <div class="card-footer d-flex">
                    <a href="" class="btn btn-white btn-sm flex-fill"><i class="icon ion-plus tx-12 mg-r-2"></i> Add as Friend</a>
                    <a href="" class="btn btn-light btn-sm btn-icon mg-l-5"><i data-feather="message-square"></i></a>
                  </div><!-- card-footer -->
                </div><!-- card -->
              </div><!-- col -->
              <div class="col">
                <div class="card card-people-two">
                  <div class="card-header">
                    <div class="pos-absolute a-0">
                      <img src="https://via.placeholder.com/1000x563/637382/fff" alt="">
                    </div>
                    <div class="avatar"><span class="avatar-initial rounded-circle bg-dark">a</span></div>
                  </div>
                  <div class="card-body">
                    <h6>Dyanne Aceron</h6>
                    <p>Sales Representative</p>
                    <p><a href="">TheCompany, Inc.</a></p>

                    <div class="follow-info mg-t-20">
                      <div>
                        <h6>104</h6>
                        <span>Followers</span>
                      </div>
                      <div>
                        <h6>39</h6>
                        <span>Following</span>
                      </div>
                    </div>
                  </div><!-- card-body -->
                  <div class="card-footer d-flex">
                    <a href="" class="btn btn-white btn-sm flex-fill"><i class="icon ion-plus tx-12 mg-r-2"></i> Add as Friend</a>
                    <a href="" class="btn btn-light btn-sm btn-icon mg-l-5"><i data-feather="message-square"></i></a>
                  </div><!-- card-footer -->
                </div><!-- card -->
              </div><!-- col -->
            </div><!-- row -->
          </div><!-- people-list-body -->
        </div><!-- people-list -->

        <hr class="mg-lg-y-25 op-0">

      

        <div class="people-list people-list-three">
          <div class="people-list-body">
            <div class="row row-xs">
              
              
              
            </div><!-- row -->
          </div><!-- people-list-body -->
        </div><!-- people-list -->
      </div><!-- content-body -->
    </div><!-- content -->

	<script src="Librerias/jquery/jquery.min.js"></script>
    <script src="Librerias/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="Librerias/feather-icons/feather.min.js"></script>
    <script src="Librerias/perfect-scrollbar/perfect-scrollbar.min.js"></script>
</body>
</html>