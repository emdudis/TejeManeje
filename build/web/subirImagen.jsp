<%@page import="java.util.*" %>
<%@page import="org.apache.commons.fileupload.*" %>
<%@page import="org.apache.commons.fileupload.disk.*" %>
<%@page import="org.apache.commons.fileupload.servlet.* "%>
<%@page import="java.io.*" %>

//hemos borrado todo el html porque no nos va a hacer falta ya que esta pagina NO se va a ver.
//lo que S� vamos a necesitar poner aqu� son unas librerias
//esas librerias nos las proporciona FormandoCodigo y las subimos boton derecho--> libraries-->
//-->add libraries
//commons-fileupload-1.2.1 
//commons-io-1.4



<%
  //FileItemFactory es una Interfaz para crear objetos de tipo archivo:
  FileItemFactory  file_factory = new DiskFileItemFactory();
  
  //La Clase ServletFileUpload es una Clase que nos permite convertir 
  //los imputFile a FileItem:
  ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
 
  //Con List voy a recibir lo que me est� enviando la p�gina anterior (registrarProducto.jsp),
  //y que en realidad es la foto:
  List items = servlet_up.parseRequest(request);
  
   //A continuaci�n con una variable tipo String llamada "img" voy a guardar el nombre de la foto y la extensi�n, para poder almacenarlo en la base de datos.
   //(ya que cuando yo lo suba al servidor, a la carpeta img (donde guardo las fotos) de la ruta "C:\\Users\\Marivi\\Documents\\NetBeansProjects\\Carrito\\web\\img\\" 
   //ah� lo estoy guardando en f�sico)
  String img="";
  
  //Ahora recorro la lista:
  for(int i=0; i<items.size(); i++){
      
      //Con un objeto de tipo FileItem me va a ir descomponiendo todo lo que tenga la lista items, y que es
      //lo que voy a guardar en el servidor:
      FileItem item = (FileItem) items.get(0);
      
      //aqu� pregunto si el item NO est� vac�o, y si no est� vac�o lo que voy a hacer es ALMACENARLO:
      if(!item.isFormField()){
          //MUY IMPORTANTE tenemos que dar la ruta fisica donde queremos colocar la imagen, as� como su nombre
          //para ello JSP pide usar \\ doble slash:
          File archivo_server = new File ("C:\\Users\\Marivi\\Documents\\NetBeansProjects\\Carrito\\web\\img\\"+item.getName());
          
          //Y aqu� colocamos la foto en el servidor:
          try{
              //item es la imagen(la foto):
              item.write(archivo_server);
          }catch(Exception e){}
          img=item.getName();
      }
      
  }
%>

  //y esta img la voy a enviar a una tercera p�gina (que llamamos registrarProducto2.jsp) que es la que me va a terminar de
  //llenar los datos adicionales del producto para luego, atrav�s del ServletControler, poder guardarlos en la base de datos.
  //Pero al estar en una p�gina JSP no puedo usar el request.getRequestDispatcher (como hago en los Servlet), sino que
  //necesito redireccionarme por medio de etiquetas JSP, �stas:
  <jsp:forward page="registrarProducto2.jsp">
      <jsp:param name="img" value="<%=img%>"></jsp:param>
  </jsp:forward>