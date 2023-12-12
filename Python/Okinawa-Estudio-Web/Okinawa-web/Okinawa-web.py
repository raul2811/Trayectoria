import reflex as rx
from Templates.navbar import navbar
from Templates.content_1 import content_1
from Templates.section_1 import section_1

class State(rx.State):
    "NADA DE MOMENTO XD"


@rx.page(route='/', title='Okinawa Studio')


def index() -> rx.Component:	
  return rx.box(
  	navbar(),
    content_1(),
    section_1(),
    rx.script("document.addEventListener('contextmenu', event => event.preventDefault());"),#elimina la accion del boton derecho del mouse
    class_name="Badground_slider",
		)
  
  
app = rx.App(stylesheets=[
        "fonts/font.css","badground.css",
    ],)


app.add_page(index)
app.compile()