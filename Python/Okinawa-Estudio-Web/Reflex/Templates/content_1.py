import reflex as rx

def content_1():
    return rx.center(
        (
            rx.square(rx.vstack(rx.text("Okinawa",font_family="my-font",color="#FFE1E3",font_size="8vw",padding_right="10%"),
                      rx.text("Estudio",font_family="my-font4",color="#FFE1E3",font_size="4vw"),
                      rx.text("Estudio | Maquillaje | Asesoría",font_family="my-font4",color="#FFE1E3",font_size="2.5vw"),
                      rx.link(rx.button("Reserva",bg="#CB3233",color="#FFE1E3",font_family="my-font4",font_size="2vw",color_scheme='red'),href="https://wa.me/50768239041",button=True,width="30%",height="4vh")),
                      border_width="thick",border_color="#FFE1E3",width="42%",height="40vh")
        ),
        class_name="content_1",
        padding_top="12vh"
    )