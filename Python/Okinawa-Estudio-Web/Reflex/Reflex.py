import reflex as rx
from Reflex.Templates.navbar import navbar
from Reflex.Templates.content_1 import content_1
class State(rx.State):
    """The app state."""

@rx.page(route='/', title='Okinawa Studio')
def index() -> rx.Component:	
  return rx.box(
  	navbar(),
    content_1(),
    class_name="badground_slider"
		)
		
app = rx.App(stylesheets=[
        "fonts/font.css","badground.css"
    ],)  
app.add_page(index)
app.compile()