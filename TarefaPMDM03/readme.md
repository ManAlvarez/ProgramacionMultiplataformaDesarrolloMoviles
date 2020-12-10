Tarefa PMDM03

Nesta tarefa vamos poñer en práctica o visto nesta unidade e imos ir angadindo funcionalidades a app realizada na tarefa anterior.
Descripción Tarefa

Vamos facer unha descripción do funcionamento da app.

Ao abrir a app manteremos a mesma pantalla de login engadindo un botón que sexa rexistrarse.
Este botón abrirá unha pantalla na que poderemos rexistrarnos engadindo o noso nome, apelidos, email, usuario, contrasinal 
e ter que elixir se o usuario é un cliente ou un administrador.
Ao premer no botón de rexistro deberá comprobar que o usuario xa existe, e senón existe gardarao.
Polo tanto, na pantalla de login, cando o usuario se intenta loguear na app teremos que consultar se o usuario existe 
e se coincide o contrasinal. De ser así, a app teranos que enviar a pantalla de cliente ou adminsitrador 
sen necesaidade de que se lle indique que tipo de usuario é.

No parte de cliente:
Na pantalla principal non modificaremos nada, só que agora o nome e apelidos terán que ser os do usuario cos que nos logueamos. 
A foto de perfil polo momento é unha calquera.
O apartado de facer pedidos non se modifica nada agás ao final. Agora non se mostrará nunha Toast o pedido, senón que debe ser gardado.
Agora si que engadiremos funcionalidade ao botón Ver pedidos en trámite. Ao premer veremos unha pantalla con tódolos pedidos en trámite do usuario. 
Para iso utilizade unha RecyclerView. Coidado co scroll se hai moitos pedidos.
Ademáis tamén engadiremos funcionalidade ao botón Ver compras realizadas. Será igual que a anterior pero cas compras realizadas. 
neste punto vamos aclarar os estados dun pedido:
Un usuario realiza un pedido: este pedido está en trámite.
O administrador pode aceptar os pedidos en trámite, polo que este pedido estará aceptado, polo que se mostrará en compras realizadas. 
Tamén poderá rexeitalos e polo tanto non aparecerán en ningunha das dúas pantallas (tramites e compras).

No apartado de adminsitrador este poderá realizar o seguinte:
A pantalla de inicio contará co nome e apelidos e unha foto calquera de momento. Terá dous botóns: Ver pedidos en trámite, Ver pedidos aceptados 
e Ver pedidos rexeitados.
Se preme no primeiro botón terá unha páxina con todos os pedidos en trámite de tódolos usuarios. 
Nunha RecyclerView poderá ver os datos de cada pedido e para cada usuario. Cada pedido terá dous botóns aceptar ou rexeitar, 
que farán o que o propio nome indican.
Se preme nos outros dous botóns, a través dunha Recyclerview poderá ver os pedidos aceptados ou rexeitados segundo o caso.

Outros apuntes importantes:
Antes de realizar un pedido por parte do cliente, aparecerálle unha ventá de diálogo co resumo do pedido. Poderá aceptar o pedido ou rexeitalo.
Engadir para o adminsitrador e usuario unha barra da app cas accións dispoñibles, e unha vez dentro de cada acción, o botón atrás.
Agora debemos usar persistencia de datos. Tedes 3 opcións para realizala:
Utilizar SQLite.
Utilizar Room.
Utilizar Firebase.
Calquera dos 3 métodos para persistencia é válido. SQlite é o máis típico. 
Room proporionanos unha capa de abstracción entre base de datos relacional e clases JAVA. 
Por último Firebase porporcionanos un xeito de gardar os datos na nube e polo tatno axustarse máis a unha app real.

O deseño decidídelo vos igual que na anterior tarefa. 
Intentade que sexa o máis usable posible e en todo o posible utilizar os patróns de deseño de “Material Design”. 
Probade que se vexa correctamente en distintos tipos de pantalla, así como en vertical e horizontal.
